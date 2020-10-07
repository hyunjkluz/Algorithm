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
 * @CretaedAt : Sep 29, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/18258
 */
public class Main_18258 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<String> queue = new LinkedList<String>();
		// Deque<String deque = new ArrayDeque<String>();
		StringBuilder print = new StringBuilder();

		int runCnt = Integer.parseInt(br.readLine());

		String last = "";

		while (runCnt-- > 0) {
			String[] command = br.readLine().split(" ");

			if (command[0].equals("size")) {
				print.append(queue.size() + "\n");
				continue;
			}
			if (command[0].equals("empty")) {
				print.append(queue.isEmpty() == true ? "1\n" : "0\n");
				continue;
			}
			if (command[0].equals("push")) {
				last = command[1];
				queue.add(command[1]);
				continue;
			}

			if (!queue.isEmpty()) {
				if (command[0].equals("pop")) {
					print.append(queue.poll() + "\n");
					continue;
				}
				if (command[0].equals("front")) {
					print.append(queue.peek() + "\n");
					continue;
				}
				if (command[0].equals("back")) {
					print.append(last + "\n");
					continue;
				}
			}

			print.append("-1\n");
		}
		System.out.println(print.toString());
	}

}
