/**
 * 
 */
package com.baekjoon.bs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 4, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1920
 */
public class Main_1920 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(bf.readLine());
		int[] find = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		StringBuilder sb = new StringBuilder();

		Arrays.sort(arr);

		Arrays.stream(find).forEach(num -> sb.append((solution(num, arr, 0, N - 1) ? 1 : 0) + "\n"));
		System.out.println(sb.toString());

	}

	public static boolean solution(int find, int[] arr, int start, int end) {
		if (start > end) {
			return false;
		}

		int mid = (start + end) / 2;

		if (find == arr[mid]) {
			return true;
		} else if (find < arr[mid]) {
			return solution(find, arr, start, mid - 1);
		} else {
			return solution(find, arr, mid + 1, end);
		}

	}

}
