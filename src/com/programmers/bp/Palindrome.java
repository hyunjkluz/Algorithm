/**
 * 
 */
package com.programmers.bp;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 22, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/12904
 */
public class Palindrome {

	public static void main(String[] args) {
		System.out.println(solution("abcdcba"));
		System.out.println(solution("abacde"));

	}

	public static int solution(String s) {
		int len = s.length();

		// 길이가 최대인 문자열부터 펠린드롭인지 확인
		for (; len > 1; len--) {
			boolean isPalin = true;

			// 시작 인덱스
			for (int start = 0; start <= s.length() - len; start++) {
				int left = start;
				int right = len - 1 + start;
				isPalin = true;

				for (int c = 0; c < len / 2; c++) {
					if (s.charAt(left++) != s.charAt(right--)) {
						isPalin = false;
						// 이 구문이 없으면 효율성에서 떨어짐
						break;
					}
				}

				if (isPalin) {
					return len;
				}
			}

		}

		return 1;
	}
}