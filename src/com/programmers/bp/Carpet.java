/**
 * 
 */
package com.programmers.bp;

import java.util.Stack;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 11, 2020
 * @주요 개념 :
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42842
 */
public class Carpet {
	public static void main(String[] args) {
		int brown = 10;
		int yellow = 2;

		int[] result = solution(brown, yellow);
		System.out.println("가로 : " + result[0] + " / 세로 : " + result[1]);
	}

	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int area = brown + yellow;

		// 가로 길이는 최소 3
		for (int i = 3; i < area; i++) {
			int width = area / i;
			int height = area / width;

			if ((width - 2) * (height - 2) == yellow && width >= height) {
				return new int[] { width, height };
			}
		}

		return answer;
	}

}
