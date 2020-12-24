/**
 * 
 */
package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 24, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/12871
 */
public class Main_12871 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		boolean possible = false;

		if (str1.length() == str2.length()) {
			if (str1.equals(str2)) {
				possible = true;
			}
		} else {
			String minStr = str1.length() < str2.length() ? str1 : str2;
			String maxStr = str1.length() < str2.length() ? str2 : str1;

			possible = solution2(minStr, maxStr);
		}

		System.out.println(possible ? 1 : 0);

	}

	public static boolean solution(String minStr, String maxStr) {
		boolean possible = false;
		System.out.println("minStr : " + minStr + ", maxStr : " + maxStr);

		// 큰 문자열을 작은 문자열 크기만큼 잘라서 비교
		for (int i = 0; i + minStr.length() <= maxStr.length(); i += minStr.length()) {
			System.out.println("-- i : " + i);
			if (maxStr.substring(i, i + minStr.length()).equals(minStr)) {
				possible = true;
			} else {
				possible = false;
				break;
			}
		}

		// 비교 후 비교하지 못한 남은 문자열이 있으면
		if (possible && maxStr.length() % minStr.length() != 0) {
			int beginIndex = (maxStr.length() / minStr.length()) * minStr.length();

			// 최소 길이 문자 = 최대 문자열에서 비교하고 남은 문자열들
			// 최대 길이 문자 = 최소 길이 문자
			return solution(maxStr.substring(beginIndex, maxStr.length()), minStr);
		} else {
			return possible;
		}
	}

	// 최대공약수 구하기 - 유클리드 호제법
	public static int gcd(int a, int b) {
		while (a > 0) {
			int temp = a;
			a = b % a;
			b = temp;
		}
		return b;
	}

	// 최소 공배수 구하기
	public static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}

	public static boolean solution2(String minStr, String maxStr) {
		// 두 문자열 길이의 최소 공배수 구하기
		int lcm = lcm(minStr.length(), maxStr.length());

		StringBuffer fs = new StringBuffer();
		StringBuffer ft = new StringBuffer();

		// 최소 공배수 길이만큼 문자열 붙이기
		for (int i = 1; i <= lcm / minStr.length(); i++) {
			fs.append(minStr);
		}

		for (int i = 1; i <= lcm / maxStr.length(); i++) {
			ft.append(maxStr);
		}

		// 비교
		if (fs.toString().equals(ft.toString())) {
			return true;
		}
		return false;
	}

}
