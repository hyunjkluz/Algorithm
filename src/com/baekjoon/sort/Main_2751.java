package com.baekjoon.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : kimhyunjin
 * @CretaedAt :
 * @문제 이름 : 수 정렬하기2
 * @문제 링크 : https://www.acmicpc.net/problem/2751
 */
public class Main_2751 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(arr);

		for (int i = 0; i < arr.size(); i++) {
			bw.write(arr.get(i) + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
