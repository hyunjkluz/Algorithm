package com.lecture.lab3;
import java.util.Scanner;

public class Main_Q3 {
	//1670
    public static long[] memo = new long[10005];
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long[] dp = new long[memo.length];

        for (int i = 0; i < memo.length; i++)
            memo[i] = 0;

        int N = sc.nextInt();
        System.out.println(hankdShacking(N, dp));
	}

	public static long hankdShacking(int N, long[] dp) {
	    if (N == 0) {
	        return memo[0] = 1;
        }
        if (N == 2) {
	        return memo[N] = 1;
        }

        if (dp[N] > 0) {
            return memo[N];
        }

        long ret = 0;

        for (int i = 1; i < N; i += 2) {
            if (memo[i-1] == 0)
                memo[i-1] = hankdShacking(i -1, dp);
            if (memo[N-(i + 1)] == 0)
                memo[N-(i + 1)] = hankdShacking(N-(i +1), dp);

            ret += memo[i-1]* memo[N-(i+1)];
            //ret %= 987654321;
        }
        dp[N] = ret;
        memo[N] = ret;
        return memo[N];
	}

}
