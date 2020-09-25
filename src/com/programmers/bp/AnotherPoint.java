/**
 * 
 */
package com.programmers.bp;

import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 25, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/18/lessons/1878
 */

public class AnotherPoint {

	public static void main(String[] args) {
		int[][] v = { { 1, 1 }, { 2, 2 }, { 1, 2 } };
//		int[][] v = { { 1, 4 }, { 3, 4 }, { 3, 10 } };
		System.out.println(Arrays.toString(solution(v)));
	}

	public static int[] solution(int[][] v) {
		int[] answer = new int[2];

		for (int i = 0; i < v.length; i++) {
			if (v[0][i] == v[1][i]) {
				answer[i] = v[2][i];
			} else if (v[0][i] == v[2][i]) {
				answer[i] = v[1][i];
			} else if (v[1][i] == v[2][i]) {
				answer[i] = v[0][i];
			}
		}

		return answer;
	}

}
