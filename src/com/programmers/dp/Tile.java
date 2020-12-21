/**
 * 
 */
package com.programmers.dp;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 29, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/12900
 */
public class Tile {

	public static void main(String[] args) {
		System.out.println(solution(4));
	}

	public static int solution(int n) {
		int n1 = 1, n2 = 1, n3 = 1;

		// DP[i] = DP[i - 1] + DP[i-2];
		// 0과 1일 때 빼고, 2일때 부터 시작한다고 가정
		for (int i = 2; i <= n; i++) {
			n3 = (n1 + n2) % 1000000007;
			System.out.println(n3);

			n1 = n2;
			n2 = n3;
		}

		return n3;
	}

}
