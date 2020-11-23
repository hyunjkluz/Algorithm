/**
 * 
 */
package com.baekjoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 23, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1766
 */
public class Main_1766 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		// 위상 정렬 : 해당 노드를 가르키는 간선의 개수 담음
		int[] indegree = new int[NM[0] + 1];
		// 그래프의 관계를 나타냄
		ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < NM[0] + 1; i++) {
			nodes.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < NM[1]; i++) {
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			nodes.get(info[0]).add(info[1]);
			indegree[info[1]] += 1;
		}

		System.out.println(solution(NM[0], indegree, nodes));

	}

	public static String solution(int n, int[] indegree, ArrayList<ArrayList<Integer>> nodes) {
		StringBuilder sb = new StringBuilder();
		// 쉬운 문제 먼저 풀기 때문에 우선선위 큐 사용 : 간선의 개수가 0이면 큐에 넣는데 새로 들어온게 제일 쉬운 문제일 수 있기 때문에.
		PriorityQueue<Integer> ques = new PriorityQueue<Integer>();

		for (int i = 1; i < n + 1; i++) {
			if (indegree[i] == 0) {
				ques.add(i);
			}
		}

		while (!ques.isEmpty()) {
			int before = ques.poll();
			sb.append(before + " ");

			for (int after : nodes.get(before)) {
				indegree[after]--;

				if (indegree[after] == 0) {
					ques.add(after);
				}
			}
		}

		return sb.toString();
	}

}
