package com.baekjoon.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Arrays;

public class Main_2108 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int total = 0;

		for (int i = 0; i < n; i++) {
			arr[i] = (Integer.parseInt(br.readLine()));
			total += arr[i];
		}

		Arrays.sort(arr);
//		printArray(arr);
//		System.out.println("---------------");

		// 1. 산술 평균 입력
		System.out.println((int) Math.round((double) total / n));

		// 2. 중앙값 출력
		System.out.println(arr[arr.length / 2]);

		// 3. 최빈값 출력
		System.out.println(getFrequency(arr));

		// 4. 범위 출력
		System.out.println(getRange(arr));

		br.close();
	}

	public static int getFrequency(int arr[]) throws Exception {
		LinkedHashMap<Integer, Integer> count = new LinkedHashMap<>();

		for (int item : arr) {
			if (count.containsKey(item)) {
				int c = count.get(item);
				count.put(item, c + 1);
			} else {
				count.put(item, 1);
			}
		}

		int maxFreqLen = 1;
		int[][] maxFreq = new int[2][2];
		int maxCnt = -1;

//		System.out.println();
//		for (Integer key : count.keySet()) {
//			Integer value = count.get(key);
//			System.out.println(key + " : " + value);
//		}
//		System.out.println();

		for (Integer key : count.keySet()) {
			Integer value = count.get(key);

			if (value > maxCnt) {
				maxCnt = value;
				maxFreqLen = 1;
				maxFreq[maxFreqLen - 1][0] = key;
				maxFreq[maxFreqLen - 1][1] = value;
			} else if (value == maxCnt && maxFreqLen == 1) {
				maxFreqLen = 2;
				maxFreq[maxFreqLen - 1][0] = key;
				maxFreq[maxFreqLen - 1][1] = value;
			}
		}

		return maxFreq[maxFreqLen - 1][0];
	}

	public static int getRange(int arr[]) throws Exception {
		return Math.abs(arr[arr.length - 1] - arr[0]);
	}

	public static void printArray(int arr[]) {
		for (int item : arr) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
}
