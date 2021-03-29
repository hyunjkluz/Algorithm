/**
 * 
 */
package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 14, 2021
 * @문제 이름 : Cubeditor - KMP 알고리즘의 LPS Table
 * @문제 링크 : https://www.acmicpc.net/problem/1701
 * @참고 링크 : https://gusdnd852.tistory.com/172
 */
public class Main_1701 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String whole = br.readLine();

		int answer = Integer.MIN_VALUE;

		for (int i = 0; i < whole.length(); i++) {
			// 문자열 안에서의 부분 문자열안에서의 특정 문자열이 몇개나 있는지 확인
			// KMP 알고리즘에서 LPS Table 활용
			int[] table = makeLPSTable(whole.substring(i, whole.length()));
			int findMax = Integer.MIN_VALUE;

			for (int lps : table) {
				findMax = Math.max(findMax, lps);
			}

			answer = Math.max(answer, findMax);

		}

		System.out.println(answer);

	}

	public static int[] makeLPSTable(String pattern) {
		int[] table = new int[pattern.length()];

		// i=1, j=0으로 한칸 차이나게 포개어놓고 시작
		for (int i = 1, j = 0; i < pattern.length(); i++) {
			// i : 부분 문자열의 길이를 하나씩 늘려감(끝을 가리키고 있음)
			// j : 접두사와 접미사가 같을 때, 접두사의 끝을 가리키고 있음
			// 해당 문자열 길이에 해당하는 최대 길이의 부분 문자열 찾음

			// 문자열이 일치하지 않았다면
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				// 가장 마지막에 일치한 문자의 LPS 값으로 된다
				j = table[j - 1];
			}

			// 문자열이 일치하였으면
			if (pattern.charAt(i) == pattern.charAt(j)) {
				// j를 증가시키고, 증가된 j값이 LPS값이 됨.
				table[i] = ++j;
			}
		}

		return table;
	}

}
