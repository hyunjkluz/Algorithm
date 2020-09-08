package com.baekjoon.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class Main_1427 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String n = br.readLine();
		String[] nSplit = n.split("");
		Integer[] arr = new Integer[nSplit.length];
		
		for (int i = 0; i < nSplit.length; i++) {
			arr[i] = (Integer.parseInt(nSplit[i]));
		}
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		for (int item : arr) {
			bw.write(Integer.toString(item));
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	
	public static void printArray(int arr[]) {
		for (int item : arr) {
			System.out.print(item + " ");
		}
	}
}
