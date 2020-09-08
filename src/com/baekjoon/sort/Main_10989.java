package com.baekjoon.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_10989 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = (Integer.parseInt(br.readLine()));
		}
		
		int[] sort = countingSort(arr);
		
		for (int item : sort) {
			bw.write(item + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static int[] countingSort(int arr[]) throws Exception {
		int max = findMax(arr);
		int[] count = new int[max + 1];
		int[] result = new int[arr.length];
		Arrays.fill(count,  0);	
		
		
		// 각 원소 개수 계
		for (int i = 0; i < arr.length; i++) {
			count[arr[i]] += 1;
		}
		
		// 누적 합 계산
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
		
		// 누적합을 이용해 정
		for (int i = arr.length - 1; i >= 0; i--) {
			result[--count[arr[i]]] = arr[i];
		}
		
		return result;
	}
	
	public static int findMax(int arr[]) {
		int flag = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (flag < arr[i]) {
				flag = arr[i];
			}
		}
		
		return flag;
	}
	
	public static void printArray(int arr[]) {
		for (int item : arr) {
			System.out.print(item + " ");
		}
	}
}
