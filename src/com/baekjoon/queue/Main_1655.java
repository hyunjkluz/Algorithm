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
 * @문제 이름 : 가운데를 말해요
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
		// MaxHeap : 내림차순 : [ 2, 1, -99, ... ]
		PriorityQueue<Integer> minDescHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
		// MinHeap : 오름차순 : [ 5, 10, 15, ... ]
		PriorityQueue<Integer> maxAscHeap = new PriorityQueue<Integer>();

		for (int i = 0; i < nums.length; i++) {
			// 그냥 번갈아서 한번씩 넣어줌
			if (minDescHeap.size() == maxAscHeap.size())
				minDescHeap.offer(nums[i]);
			else
				maxAscHeap.offer(nums[i]);

			if (!minDescHeap.isEmpty() && !maxAscHeap.isEmpty()) {
				// 각 힙에서 맨 위의 값을 뽑았을 때
				// 최소힙의 값(작은 애들 중 제일 큰 값)이 최대힙의 값(큰 애들 중에서 제일 작은 값)보다 클 때
				// 둘의 값을 교환
				if (minDescHeap.peek() > maxAscHeap.peek()) {
					int temp = minDescHeap.poll();

					minDescHeap.add(maxAscHeap.poll());
					maxAscHeap.add(temp);
				}
			}

			// 가운데 값은 최대힙의 첫번째 값
			sb.append(minDescHeap.peek() + " \n");
		}

		return sb.toString();

	}

}
