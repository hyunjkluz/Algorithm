/**
 * 
 */
package com.programmers.greedy;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 12, 2020
 * @주요 개념 :
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42883
 */

class Card {
	public Integer index, value;

	public Card(Integer index, Integer value) {
		this.index = index;
		this.value = value;
	}
}

public class BigNumber {
	public static void main(String[] args) {
		String number = "4177252841";
		int k = 4;

		System.out.println("Result : " + solution(number, k));
	}

	public static String solution(String number, int k) {
		StringBuilder answer = new StringBuilder();

		Integer pick = number.length() - k;

		Integer maxIdx = 0;
		while (pick > 0) {
			char maxNum = '0';
			for (int i = maxIdx; i <= number.length() - pick; i++) {
				if (number.charAt(i) > maxNum) {
					maxNum = number.charAt(i);
					maxIdx = i;
				}
			}

			answer.append(maxNum);
			maxIdx++;
			pick--;
		}

		return answer.toString();
	}

}
