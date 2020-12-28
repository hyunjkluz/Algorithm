/**
 * 
 */
package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 28, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/15661
 */
public class Main_15661 {
	static int N;
	static int min = Integer.MAX_VALUE;
	static HashSet<Integer> allPlayer = new HashSet<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] s = new int[N][N];

		for (int i = 0; i < N; i++) {
			allPlayer.add(i);
		}

		for (int i = 0; i < N; i++) {
			s[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		solution(s);
		System.out.println(min);

	}

	public static void solution(int[][] s) {
		for (int i = 1; i <= N / 2; i++) {
			boolean[] pick = new boolean[N];

			pick(new HashSet<Integer>(), pick, i, 0, s);

		}
	}

	public static void pick(HashSet<Integer> startTeam, boolean[] pick, int pickNum, int idx, int[][] s) {
		if (pickNum == 0) {
			HashSet<Integer> linkTeam = new HashSet<Integer>();

			linkTeam.addAll(allPlayer);
			// 차집합 : 전체 선수 집합에서 뽑힌 선수들 제외 = 나머지 팀 선수들
			linkTeam.removeAll(startTeam);

			int startStats = calcStats(startTeam, s);
			int linkStats = calcStats(linkTeam, s);

			min = Math.min(min, Math.abs(startStats - linkStats));
			return;
		}

		// 조합
		for (int i = idx; i < N; i++) {
			startTeam.add(i);
			pick(startTeam, pick, pickNum - 1, i + 1, s);
			startTeam.remove(i);
		}
	}

	public static int calcStats(HashSet<Integer> team, int[][] s) {
		if (team.size() == 1) {
			return 0;
		}

		int total = 0;
		Integer[] player = new Integer[team.size()];
		team.toArray(player);

		for (int i = 0; i < player.length; i++) {
			for (int j = i + 1; j < player.length; j++) {
				total += s[player[i]][player[j]];
				total += s[player[j]][player[i]];
			}
		}

		return total;
	}

}
