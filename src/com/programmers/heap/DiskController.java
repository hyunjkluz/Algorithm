/**
 * 
 */
package com.programmers.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 27, 2020
 * @문제 링크 :
 */
public class DiskController {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 3 }, { 1, 9 }, { 2, 6 } }));
	}

	public static int solution(int[][] jobs) {
		int answer = 0;
		int jobCount = 0;
		int flagJobIdx = 0;
		int end = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((j1, j2) -> j1[1] - j2[1]);

		// 요청시간 오름차순으로 정렬
		Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

		while (jobCount < jobs.length) {

			while (flagJobIdx < jobs.length && jobs[flagJobIdx][0] <= end) {
				pq.add(jobs[flagJobIdx++]);
			}

			// 처음 시작 OR 새로운 요청 왔음
			if (pq.isEmpty()) {
				end = jobs[flagJobIdx][0];
			} else {
				int[] temp = pq.poll();
				answer += temp[1] + end - temp[0];
				end += temp[1];
				jobCount++;
			}
		}

		return (int) Math.floor(answer / jobs.length);
	}

}
