/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 20, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/1939
 */
class Bridge {
	int end, weight;

	public Bridge(int end, int weight) {
		super();
		this.end = end;
		this.weight = weight;
	}

}

public class Main_1939 {
	static int N, M;
	static ArrayList<Bridge>[] bridges;
	static int maxWeight = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");

		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		bridges = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			bridges[i] = new ArrayList<Bridge>();
		}

		for (int i = 0; i < M; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			// 양방향 그래프 표현
			bridges[line[0]].add(new Bridge(line[1], line[2]));
			bridges[line[1]].add(new Bridge(line[0], line[2]));

			// 이분 탐색을 위한 최대 중량 갱신
			maxWeight = Math.max(maxWeight, line[2]);
		}

		int[] startInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(binarySearch(startInfo[0], startInfo[1]));

	}

	public static int binarySearch(int start, int end) {
		int low = 1;
		int high = maxWeight;
		int result = 0;

		// 이분 탐색
		while (low <= high) {
			int mid = (high + low) / 2;

			// 이동 가능한 중량을 중간값으로 설정
			if (bfs(start, end, mid)) {
				result = Math.max(result, mid);
				// 이동 가능할 때 더 큰 값도 가능한지 알아보기 위해 범위 올림
				low = mid + 1;
			} else {
				// 범위 내림
				high = mid - 1;
			}
		}

		return result;
	}

	//
	public static boolean bfs(int start, int end, int weight) {
		boolean[] visited = new boolean[N + 1];
		// 다음 이동지를 넣을 큐
		Queue<Integer> queue = new LinkedList<Integer>();

		visited[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (Bridge next : bridges[current]) {
				// 최대 중량을 찾는 것이기 때문에 기준 중량 이상으로 물건을 보낼 수 있는 지 판별
				if (!visited[next.end] && next.weight >= weight) {
					// 끝 지점에 도달하였을 때
					if (next.end == end) {
						return true;
					}
					queue.add(next.end);
					visited[next.end] = true;
				}
			}
		}

		return false;
	}

}
