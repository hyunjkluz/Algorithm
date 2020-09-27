/**
 * 
 */
package com.baekjoon.dp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 27, 2020
 * @문제 링크 :
 */
public class Main_1003 {
	public static int[] memo = new int[41];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cnt = scan.nextInt();
		List<Integer> nums = new ArrayList<Integer>();

		while (cnt-- > 0) {
			nums.add(scan.nextInt());
		}

		memo[0] = 0;
		memo[1] = 1;

		int max = nums.stream().max((n1, n2) -> {
			return n1.compareTo(n2);
		}).get();

		fibo(max);

		nums.stream().forEach(n -> {
			if (n == 0)
				System.out.println(1 + " " + 0);
			else if (n == 1)
				System.out.println(0 + " " + 1);
			else
				System.out.println(memo[n - 1] + " " + memo[n]);
		});
	}

	public static int fibo(int n) {
		for (int i = 2; i <= n; i++) {
			memo[i] = memo[i - 1] + memo[i - 2];
		}

		return memo[n];
//		if (n <= 1 || memo[n] != 0) {
//			return memo[n];
//		}
//
//		return memo[n] = fibo(n - 1) + fibo(n - 2);
	}

}
