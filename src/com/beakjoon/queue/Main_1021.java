/**
 * 
 */
package com.beakjoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 29, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1021
 */
public class Main_1021 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] pick = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(solution(nm[0], nm[1], pick));
	}

	public static int solution(int n, int m, int[] pick) {
		LinkedList<Integer> list = IntStream.rangeClosed(1, n).boxed()
				.collect(Collectors.toCollection(LinkedList::new));
		int count = 0;

		for (int M = 0; M < m; M++) {
			int mid = list.size() % 2 == 0 ? (list.size() / 2) - 1 : list.size() / 2;
			boolean find = false;

			while (!find) {
				if (list.get(0) == pick[M]) {
					list.remove(0);
					break;
				} else if (list.indexOf(pick[M]) <= mid) {
					list.add(list.size() - 1, list.remove(0));
				} else {
					list.add(0, list.remove(list.size() - 1));
				}
				count++;
			}
		}

		return count;
	}

}
