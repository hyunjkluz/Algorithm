/**
 * 
 */
package com.baekjoon.bs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 4, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/10816
 */
public class Main_10816 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(bf.readLine());
		int[] find = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		System.out.println(solution(arr, find));

	}

	public static String solution(int[] arr, int[] find) {
		Map<Integer, Integer> cardMap = new HashMap<Integer, Integer>();
		StringBuilder sb = new StringBuilder();

		for (int num : arr) {
			cardMap.put(num, cardMap.getOrDefault(num, 0) + 1);
		}

		for (int target : find) {
			if (cardMap.containsKey(target)) {
				sb.append(cardMap.get(target) + " ");
			} else {
				sb.append(0 + " ");
			}
		}

		return sb.toString();
	}

}
