/**
 * 
 */
package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 25, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1436
 */
public class Main_1436 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		System.out.println(solution(N));
	}

	public static int solution(int n) {
		int num = 666;

		while (n > 0) {
			if (Integer.toString(num).contains("666")) {
				n--;
			}
			num++;
		}

		return num - 1;
	}

}
