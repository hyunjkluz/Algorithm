/**
 * 
 */
package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 21, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2916
 */
public class Main_2916 {
	static boolean[] angles = new boolean[361];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] NK = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int[] know = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ArrayList<Integer> knows = new ArrayList<Integer>();
		for (int i = 0; i < know.length; i++) {
			angles[know[i]] = true;
			knows.add(know[i]);
		}
		angles[0] = true;

		int[] questions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(solution(knows, questions));
	}

	public static void makeAllAngles(ArrayList<Integer> knows) {
		// 모든 값 더하기 빼기
		while (true) {
			boolean flag = false;
			for (int i = 0; i < knows.size(); i++) {
				for (int j = 0; j < knows.size(); j++) {
					// 더한 값이 360보다 클 수 있음
					// 만약에 크다면 "큰 각 - 360"한 값을 알 수 있으니 -> 나머지 연산으로 계산

					// 더하기
					int tmp = (knows.get(i) + knows.get(j)) % 360;

					if (!angles[tmp]) {
						flag = true;
						angles[tmp] = true;

						// 새로운 각을 추가
						knows.add(tmp);
					}

					// 빼기 : 음수가 나올 수 있으니 절댓값으로 구해야함
					tmp = Math.abs(knows.get(i) - knows.get(j) % 360);

					if (!angles[tmp]) {
						flag = true;
						angles[tmp] = true;

						// 새로운 각을 추가
						knows.add(tmp);
					}
				}
			}

			// 새로운 값이 추가되지 않았으면 끝냄
			// 새로운 값이 추가되었으면 그 값을 위해 계산 다시 수행
			if (!flag)
				break;
		}

	}

	public static String solution(ArrayList<Integer> knows, int[] questions) {
		StringBuilder sb = new StringBuilder();

		makeAllAngles(knows);

		for (int q : questions) {
			sb.append(angles[q] ? "YES\n" : "NO\n");
		}

		return sb.toString();
	}

}
