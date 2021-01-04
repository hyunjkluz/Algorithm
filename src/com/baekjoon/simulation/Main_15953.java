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
 * @CretaedAt : Jan 4, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/15953
 */
public class Main_15953 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] imagine = new int[T][2];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < T; i++) {
			imagine[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			sb.append(((reward2017(imagine[i][0]) + reward2018(imagine[i][1])) * 10000) + "\n");
		}

		System.out.println(sb.toString());

	}

	// 왜 이렇게하면 안될까..?
	public static int reward2017_1(int rank) {
		if (rank == 0) {
			return 0;
		} else if (rank < 2) {
			return 500;
		} else if (rank < 4) {
			return 300;
		} else if (rank < 7) {
			return 200;
		} else if (rank < 11) {
			return 50;
		} else if (rank < 15) {
			return 30;
		} else if (rank < 22) {
			return 10;
		} else {
			return 0;
		}

	}

	public static int reward2017(int rank) {
		if (rank == 0) {
			return 0;
		} else if (rank == 1) {
			return 500;
		} else if (2 <= rank && rank < 4) {
			return 300;
		} else if (4 <= rank && rank < 7) {
			return 200;
		} else if (7 <= rank && rank < 11) {
			return 50;
		} else if (11 <= rank && rank < 16) {
			return 30;
		} else if (16 <= rank && rank < 22) {
			return 10;
		} else {
			return 0;
		}

	}

	public static int reward2018_1(int rank) {
		if (rank == 0) {
			return 0;
		} else if (rank < Math.pow(2, 1)) {
			return 512;
		} else if (rank < Math.pow(2, 2)) {
			return 256;
		} else if (rank < Math.pow(2, 3)) {
			return 128;
		} else if (rank < Math.pow(2, 4)) {
			return 64;
		} else if (rank < Math.pow(2, 5)) {
			return 32;
		} else {
			return 0;
		}

	}

	public static int reward2018(int rank) {
		if (rank == 0) {
			return 0;
		} else if (rank == 1) {
			return 512;
		} else if (2 <= rank && rank < 4) {
			return 256;
		} else if (4 <= rank && rank < 8) {
			return 128;
		} else if (8 <= rank && rank < 16) {
			return 64;
		} else if (16 <= rank && rank < 32) {
			return 32;
		} else {
			return 0;
		}

	}

}
