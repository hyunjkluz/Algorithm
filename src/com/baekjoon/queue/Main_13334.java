/**
 * 
 */
package com.baekjoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 14, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/13334
 */
class Line implements Comparable<Line> {
	int a, b, len;

	public Line(String[] AB) {
		this.a = Math.min(Integer.parseInt(AB[0]), Integer.parseInt(AB[1]));
		this.b = Math.max(Integer.parseInt(AB[0]), Integer.parseInt(AB[1]));
		this.len = this.b - this.a;
	}

	@Override
	public int compareTo(Line o) {
		return Integer.compare(o.a, this.a);
	}

	@Override
	public String toString() {
		return "[a=" + a + ", b=" + b + ", len=" + len + "]";
	}

}

public class Main_13334 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Line> lines = new ArrayList<Line>();

		for (int i = 0; i < n; i++) {
			lines.add(new Line(br.readLine().split(" ")));
		}

		int d = Integer.parseInt(br.readLine());

		// 왼쪽(a)( = 다리 놓는 시작 점) 내림차순 정렬
		Collections.sort(lines);

		System.out.println(solution(d, lines));

	}

	public static int solution(int range, ArrayList<Line> lines) {
		// 다리 범위 안에 들어오는 점들의 종료 지점을 내림차순으로 우선순위 큐에 넣음
		// 범위에 안들어온 지점부터 빼려고
		PriorityQueue<Integer> endPoints = new PriorityQueue<Integer>(Collections.reverseOrder());
		int maxCnt = 0;

		for (Line l : lines) {
			// 기준 line에서 다리를 놓았을 때 조건 안에 들어오는지, 들어오면 큐 안에 넣기
			if (l.b <= l.a + range) {
				endPoints.add(l.b);
			}

			while (!endPoints.isEmpty()) {
				// 기준에 맞지 않음 = 시작점에서 다리를 놓았을 때 구간에 들어오지 않음
				// 큐 안에는 기준 line에서 다리를 놓았을 때 안에 들어올 수 있는 구간만 남게 됨
				// 끝 지점 내림차순 정렬 : 구간 안에 들어오는지 알 수 있음
				if (endPoints.peek() > l.a + range) {
					endPoints.poll();
				} else {
					break;
				}
			}

			maxCnt = Math.max(maxCnt, endPoints.size());
		}

		return maxCnt;
	}

}
