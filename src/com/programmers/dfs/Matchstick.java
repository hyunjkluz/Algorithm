/**
 * 
 */
package com.programmers.dfs;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 26, 2020
 * @문제 링크 :
 */
public class Matchstick {
	public static long cnt = 0;
	public static int[] numbers = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };

	public static void main(String[] args) {
		System.out.println(Long.toString(solution(5)));
		System.out.println(Long.toString(solution(6)));
		System.out.println(Long.toString(solution(11)));
		System.out.println(Long.toString(solution(1)));
	}

	public static long solution(int k) {
		cnt = 0;

		if (k < 2) {
			return 0;
		}

		if (numbers[0] == k) {
			cnt += 1;
		}

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > k) {
				continue;
			}

			make(numbers[i], k);
		}
		return cnt;
	}

	public static void make(int used, int k) {
		if (used == k) {
			cnt += 1;
		}

		if (used > k) {
			return;
		}

		for (int next = 0; next < numbers.length; next++) {
			if (used + numbers[next] <= k) {
				make(used + numbers[next], k);
			}
		}
	}

}
