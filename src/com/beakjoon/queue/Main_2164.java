/**
 * 
 */
package com.beakjoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 29, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2164
 */
public class Main_2164 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		System.out.println(solution(n));
	}

	public static int solution(int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean step = true;

		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}

		while (queue.size() > 1) {
			if (step) {
				queue.poll();
			} else {
				queue.add(queue.poll());
			}
			step = !step;
		}

		return queue.peek();
	}

}
