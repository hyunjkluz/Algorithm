/**
 * 
 */
package com.programmers.greedy;

import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 27, 2020
 * @문제 링크 :
 */
public class Camera {

	public static void main(String[] args) {

		System.out.println(solution(new int[][] { { -20, 15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } }));
	}

	public static int solution(int[][] routes) {
		int cnt = 0;
		int camera = -30001;

		Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

		for (int[] route : routes) {
			if (camera < route[0]) {
				camera = route[1];
				cnt++;
			}
		}

		return cnt;

	}

}
