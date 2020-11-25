/**
 * 
 */
package com.baekjoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 18, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1655
 */
public class Main_1655 {

	public static void main(String[] agrs) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(solution(nums));
	}

	public static String solution(int[] nums) {
		StringBuilder sb = new StringBuilder();
		// MaxHeap : 내림차순
		PriorityQueue<Integer> minValue = new PriorityQueue<Integer>(Collections.reverseOrder());
		// MinHeap : 오름차순
		PriorityQueue<Integer> maxValue = new PriorityQueue<Integer>();

		for (int i = 0; i < nums.length; i++) {
			if (minValue.size() == maxValue.size())
				minValue.offer(nums[i]);
			else
				maxValue.offer(nums[i]);

			if (!minValue.isEmpty() && !maxValue.isEmpty()) {
				if (minValue.peek() > maxValue.peek()) {
					int temp = minValue.poll();
					minValue.add(maxValue.poll());
					maxValue.add(temp);
				}
			}

			sb.append(minValue.peek() + " \n");
		}

		return sb.toString();

	}

}
