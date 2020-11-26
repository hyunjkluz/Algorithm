/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 25, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2947
 */
public class Main_2947 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] pieces = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		System.out.println(solution(pieces));

	}
	
	public static String solution(int[] pieces) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = pieces.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (pieces[j] > pieces[j + 1]) {
					int temp = pieces[j];
					pieces[j] = pieces[j + 1];
					pieces[j + 1] = temp;
					
					Arrays.stream(pieces).forEach(x -> sb.append(x + " "));
					sb.append("\n");
				}
			}
		}
		
		return sb.toString();
		
	}

}
