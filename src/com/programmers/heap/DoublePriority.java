/**
 * 
 */
package com.programmers.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 24, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42628
 */
public class DoublePriority {

	public static void main(String[] args) {
		String[] operations = { "I 7", "I 5", "I -5", "D -1" };
		System.out.println("Result : " + Arrays.toString(solution(operations)));

	}

	public static int[] solution(String[] operations) {
		int[] answer = { 0, 0 };
		PriorityQueue<Integer> numbers = new PriorityQueue<Integer>();

		for (String op : operations) {
			String[] ops = op.split(" ");

			if (ops[0].equals("I")) {
				numbers.add(Integer.parseInt(ops[1]));
			} else if (!numbers.isEmpty()) {
				if (ops[0].equals("D") && ops[1].startsWith("-")) {
					numbers.poll();
				} else {
					numbers = removeMax(numbers);
				}
			}
		}

		if (numbers.isEmpty()) {
			return answer;
		} else if (numbers.size() == 1) {
			answer[0] = numbers.poll();
			answer[1] = answer[0];
		} else {
			answer = findMax(numbers);
		}

		return answer;
	}

	public static PriorityQueue<Integer> removeMax(PriorityQueue<Integer> pq) {
		PriorityQueue<Integer> newPq = new PriorityQueue<Integer>();

		while (pq.size() != 1) {
			newPq.add(pq.poll());
		}

		return newPq;
	}

	public static int[] findMax(PriorityQueue<Integer> pq) {
		int[] maxMin = new int[2];

		maxMin[1] = pq.poll();

		while (pq.size() != 1) {
			pq.poll();
		}

		maxMin[0] = pq.poll();

		return maxMin;
	}

}
