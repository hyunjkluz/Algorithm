/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 5, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2661
 */
public class Main_2661 {
	public static boolean isFin = false;
	public static String minGoodSeq = "";
	public static int flagLength;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		flagLength = Integer.parseInt(br.readLine());
		
		findMinGoodSequence("");
		
		System.out.println(minGoodSeq);

	}

	public static void findMinGoodSequence(String str) {
		if (isFin) {
			return;
		}

		if (str.length() == flagLength) {
			minGoodSeq = str;
			isFin = true;
			return;
		}

		for (int i = 1; i <= 3; i++) {
			if (isGoodSequence(str + i)) {
				findMinGoodSequence(str + i);
			}
		}

	}

	public static boolean isGoodSequence(String str) {
		// 새로운 숫자가 뒤에서 추가되었으니 뒤에서부터 검사
		// 마지막 글자 기준, 문자열 길이를 하나씩 늘려가면서 검사
		for (int i = 1; i <= str.length() / 2; i++) {
			String backStr = str.substring(str.length() - i, str.length());
			String frontStr = str.substring(str.length() - i - i, str.length() - i);

			if (backStr.equals(frontStr)) {
				return false;
			}
		}

		return true;
	}

}
