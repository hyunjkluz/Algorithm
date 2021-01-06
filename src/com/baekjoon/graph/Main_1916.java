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
 * @문제 링크 : https://www.acmicpc.net/problem/1916
 */
class Bus implements Comparable<Bus> {
	int end, weight;

	public Bus(int end, int cost) {
		this.end = end;
		this.weight = cost;
	}

	@Override
	public int compareTo(Bus o) {
		return this.weight = o.weight;
	}

	@Override
	public String toString() {
		return "Bus [end=" + end + ", weight=" + weight + "]";
	}

}

public class Main_1916 {
	static int N;
	static ArrayList<Bus>[] busInfo;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		busInfo = new ArrayList[N + 1];

		for (int i = 0; i < busInfo.length; i++) {
			busInfo[i] = new ArrayList<Bus>();
		}

		while (m-- > 0) {
			int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			busInfo[info[0]].add(new Bus(info[1], info[2]));
		}

		int[] start = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(find(start[0], start[1]));

	}

	public static int find(int start, int end) {
		int[] dist = new int[N + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;

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
					pq.add(new Bus(nextEnd, newWeight));
				}
			}

		}

		return dist[end];
	}
}
