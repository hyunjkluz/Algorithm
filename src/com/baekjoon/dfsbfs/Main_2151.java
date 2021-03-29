/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 28, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2151
 */
public class Main_2151 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		char[][] room = new char[N][N];
		for (int i = 0; i < N; i++) {
			room[i] = br.readLine().toCharArray();
		}

	}

	public static void solution(char[][] room) {
	}

}
