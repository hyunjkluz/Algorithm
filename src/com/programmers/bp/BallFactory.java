/**
 * 
 */
package com.programmers.bp;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 26, 2020
 * @문제 링크 :
 */
public class BallFactory {

	public static void main(String[] args) {

	}

	public static String solution(int n, int[] p, int[] c) {
		int left = 0;
		int total = 0;
		int miss = 0;
		int i = 0;

		for (; i < n; i++) {
			if (left + p[i] >= c[i]) {
				left = left + p[i] - c[i];
				total += miss == 0 ? c[i] * 100 : (100 / Math.pow(2, miss));
				miss = 0;
			} else {
				miss += 1;
				left += p[i];

				if (miss == 3) {
					break;
				}
			}
		}

		i = i == n ? i : i + 1;

		double avg = (double) total / (double) i;
		return String.format("$,.2f", avg);
	}
}
