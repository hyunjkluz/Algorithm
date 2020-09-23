/**
 * 
 */
package com.programmers.heap;

import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 23, 2020
 * @문제 링 :
 */
public class Scoville {
	public static void main(String[] args) {
		int[] scoville = { 10, 1, 9, 2, 3, 12 };
		int K = 7;

		System.out.println("Result : " + solution(scoville, K));
	}

	public static int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> food = new PriorityQueue<Integer>();

		for (int s : scoville) {
			food.add(s);
		}

		while (food.peek() < K) {
			food.add(food.poll() + (food.poll() * 2));
			answer++;

			if (food.size() == 1) {
				return -1;
			}
		}
		return answer == 0 ? -1 : answer;
	}
}
