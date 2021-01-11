/**
 * 
 */
package com.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 23, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/11779
 */

public class Main_11779 {
	static int N;
	static ArrayList<Bus>[] busInfo;
	static int[] past;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		busInfo = new ArrayList[N + 1];
		past = new int[N + 1];

		for (int i = 0; i < busInfo.length; i++) {
			busInfo[i] = new ArrayList<Bus>();
		}

		while (m-- > 0) {
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			busInfo[info[0]].add(new Bus(info[1], info[2]));
		}

		int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(find(start[0], start[1]));
		System.out.println(getRoute(start[1]));

	}

	public static int find(int start, int end) {
		int[] dist = new int[N + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		past[start] = 0;

		PriorityQueue<Bus> pq = new PriorityQueue<Bus>();
		pq.add(new Bus(start, 0));

		while (!pq.isEmpty()) {
			Bus current = pq.poll();

			// 이해를 못하겠음
//			if (current.weight > dist[current.end])
//				continue;

			for (Bus next : busInfo[current.end]) {
				int nextEnd = next.end;
				int newWeight = dist[current.end] + next.weight;

				// 현재까지의 비용 + 이동 비용 < 이동할 노드의 비용
				if (newWeight < dist[nextEnd]) {
					dist[nextEnd] = newWeight;
					// 최소 비용이 갱신될 때 어디서 온 것인지 저장
					past[nextEnd] = current.end;
					pq.add(new Bus(nextEnd, newWeight));
				}
			}

		}

		return dist[end];
	}

	public static String getRoute(int end) {
		ArrayList<Integer> route = new ArrayList<Integer>();
		StringBuilder answer = new StringBuilder();
		int count = 0;

		while (end != 0) {
			route.add(end);
			end = past[end];
			count++;
		}

		answer.append(count + "\n");

		for (int i = route.size() - 1; i >= 0; i--) {
			answer.append(route.get(i) + " ");
		}

		return answer.toString();
	}
}
