/**
 * 
 */
package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 29, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/4796
 */
public class Main_4796 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int i = 1;; i++) {
			String input = br.readLine();

			if (input.equals("0 0 0")) {
				System.out.println(sb.toString());
				break;
			}

			int[] LPV = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
			int days = (LPV[2] / LPV[1]) * LPV[0] + Math.min(LPV[0], LPV[2] % LPV[1]);

			sb.append("Case " + i + ": " + days + "\n");
		}

	}

}
