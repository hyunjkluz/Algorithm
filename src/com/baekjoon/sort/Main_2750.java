package com.baekjoon.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author : kimhyunjin
 * @CretaedAt :
 * @문제 이름 : 수 정렬하기
 * @문제 링크 : https://www.acmicpc.net/problem/2750
 */
public class Main_2750 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		mergeSort(arr, 0, n - 1);

		for (int i = 0; i < n; i++) {
			bw.write(arr[i] + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

	public static void selectionSort(int arr[]) throws Exception {
		for (int i = 0; i < arr.length - 1; i++) {
			int flagIdx = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j])
					flagIdx = j;
			}
			int flag = arr[i];
			arr[i] = arr[flagIdx];
			arr[flagIdx] = flag;
		}
	}

	public static void mergeSort(int arr[], int m, int n) throws Exception {
		if (m < n) {
			int p = (m + n) / 2;
			mergeSort(arr, m, p);
			mergeSort(arr, p + 1, n);
			merge(arr, m, p, n);
		}
	}

	public static void merge(int arr[], int m, int p, int n) throws Exception {
		int[] temp = new int[arr.length];
		int a = m;
		int i = m;
		int j = p + 1;

		while (i <= p && j <= n) {
			if (arr[i] <= arr[j]) {
				temp[a++] = arr[i++];
			} else { // (arr[j] < arr[i])
				temp[a++] = arr[j++];
			}
		}

		if (i > p) {
			for (; j < n; j++, a++) {
				temp[a] = arr[j];
			}
		} else {
			for (; i < p + 1; i++, a++) {
				temp[a] = arr[i];
			}
		}

		for (i = m; i < a; i++) {
			arr[i] = temp[i];
		}
	}
}
