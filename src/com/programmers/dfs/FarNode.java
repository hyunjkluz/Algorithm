/**
 * 
 */
package com.programmers.dfs;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 28, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/49189
 */
public class FarNode {

	public static void main(String[] args) {
		System.out.println(
				solution(6, new int[][] { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } }));
	}

	public static int solution(int n, int[][] edge) {
		int answer = 0;
		Queue<Integer> que = new LinkedList<Integer>();
		ArrayList<Integer>[] list = new ArrayList[n + 1]; // 그래프 구현
		boolean[] visited = new boolean[edge.length + 1]; // 방문 여부 판단 위해
		int[] dist = new int[edge.length + 1]; // 원점으로부터의 거리 값 저장 위해

		que.add(1);
		visited[0] = visited[1] = true;
		dist[1] = 1;

		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int[] e : edge) {
			list[e[0]].add(e[1]);
			list[e[1]].add(e[0]);
		}

		int maxCnt = -1;
		while (!que.isEmpty()) {
			int start = que.poll();

			for (Integer node : list[start]) {

				if (!visited[node]) {
					visited[node] = true;
					dist[node] = dist[start] + 1;
					que.add(node);

					maxCnt = maxCnt < dist[node] ? dist[node] : maxCnt;
				}
			}

		}

		for (int i = 1; i < dist.length; i++) {
			if (dist[i] == maxCnt)
				answer++;
		}

		return answer;
	}

}
