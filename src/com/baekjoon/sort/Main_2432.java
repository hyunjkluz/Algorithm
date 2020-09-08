package com.baekjoon.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main_2432 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int change[][] = new int[n][2];
		int cnt = selectionSort(arr, change);
		
		for (int i = 0; i < n; i++) {
			bw.write(arr[i] + "\n");
		}
		
		bw.write("\n\n");
		
		for (int i = 0; i < cnt; i++) {
			bw.write(change[i][0] + " " + change[i][1] + "\n");
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static int selectionSort(int[] arr, int change[][]) throws Exception{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int cnt = 0;
		
		
		for (int i = 0; i < arr.length; i++) {
			int j = 1;
			int startFlag = i;
			int endFlag = 0;
			
			for (; j < arr.length; j++) {
				if (arr[j - 1] > arr[j]) {
					startFlag = j;
					change[cnt][0] = startFlag;
					break;
				}
			}
			
			for(int k = startFlag; k >= 0; k--) {
				if (arr[j] > arr[k] || k == 0) {
					endFlag = k;
					break;
				}
			}
			
			
		}
		bw.flush();
		
		return cnt;
	}
	
}