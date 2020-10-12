package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 9, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2606
 */
public class Main_2606 {
	public static int count = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int connect = Integer.parseInt(br.readLine());

		int[][] network = new int[N + 1][N + 1];
		boolean[] visited = new boolean[N + 1];

		while (connect-- > 0) {
			int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			network[nums[0]][nums[1]] = 1;
		}

//		System.out.println(bfs(network, visited, 1));
		dfs(network, visited, 1);
		System.out.println(count);
	}

	public static void dfs(int[][] network, boolean[] visited, int c) {
		visited[c] = true;

		for (int i = 1; i < network.length; i++) {
			if (network[c][i] == 1 && !visited[i]) {
				dfs(network, visited, i);
				count++;
			}
		}
	}

	public static int bfs(int[][] network, boolean[] visited, int c) {
		int cnt = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(c);

		while (!q.isEmpty()) {
			int x = q.poll();
			visited[x] = true;

			for (int i = 1; i < network.length; i++) {
				if (network[x][i] == 1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		return cnt;

	}

}
