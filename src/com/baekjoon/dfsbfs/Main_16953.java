/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 27, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/16953
 */
public class Main_16953 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] AB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(solution(AB[0], AB[1]));
	}

	public static long solution(int A, int B) {
		Queue<long[]> queue = new LinkedList<long[]>();
		queue.add(new long[] { A, 0 });

		while (!queue.isEmpty()) {
			long[] nums = queue.poll();

			if (nums[0] == B) {
				return nums[1] + 1;
			}

			// 객체 일단 다 넣고 뽑았을 때 검사 : 메모리(22736KB), 시간(168ms)
//			if (nums[0] > B) {
//				continue;
//			}

			// 객체 입력 부분에서 걸러주었을 때 : 메모리도 더 적게 사용(18616KB) + (근소하지만)시간도 빠름(152KB)
			if (nums[0] * 2 <= B) {
				queue.add(new long[] { nums[0] * 2, nums[1] + 1 });
			}

			if (nums[0] * 10 + 1 <= B) {
				queue.add(new long[] { nums[0] * 10 + 1, nums[1] + 1 });
			}
		}

		return -1;
	}

}
