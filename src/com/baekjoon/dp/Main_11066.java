/**
 * 
 */
package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 7, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/11066
 */
public class Main_11066 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int pages = Integer.parseInt(br.readLine());
			int[] costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			sb.append(solution(pages, costs) + "\n");
		}

		System.out.println(sb.toString());
	}

	public static int solution(int pages, int[] files) {
		// x~y까지 페이지를 합칠 때의 최소 비용 저장
		int[][] dp = new int[files.length][files.length];
		int[] sumFiles = new int[files.length];

		sumFiles[0] = files[0];

		/*
		 * 0 ~ N까지 파일의 합 값의 합이 아닌 파일이 합쳐질 때 소요되는 비용이 필요한거기 때문에 사용됨
		 */
		for (int i = 1; i < files.length; i++)
			sumFiles[i] = sumFiles[i - 1] + files[i];

		// (1,2)(2,3)(3,4) 등 인접한 파일들이 합쳐지는것을 미리 초기화
		for (int i = 0; i < files.length - 1; i++)
			dp[i][i + 1] = files[i] + files[i + 1];

		for (int j = 2; j < files.length; j++) { // 열
			for (int i = 0; i + j < files.length; i++) { // 행
				dp[i][i + j] = Integer.MAX_VALUE;

				for (int k = i; k < i + j; k++) {
					/*
					 * dp : 파일 합쳐질 때 비용들의 합 i(start)와 j(end)사이의 어느 한 부분 k를 사이에 두고 나눠서 i ~ k, k + 1 ~
					 * j을 합치는데 최소 비용을 구하고 이 최소 비용과 마지막 파일을 합친 값(비용)을 더함 = fileSum
					 * 
					 * dp[1][2]가 70 dp[3][4]가 80이면 dp[1][4]가 150이 아닌 dp[1][2]+dp[3][4]+[1]부터[4]까지의합
					 * = 300이 되어야한다.
					 */
					dp[i][i + j] = Math.min(dp[i][k] + dp[k + 1][i + j] + fileSum(i, i + j, sumFiles), dp[i][i + j]);
				}
			}
		}

		return dp[0][files.length - 1];
	}

	public static Integer fileSum(int i, int j, int[] sumFiles) {
		if (i == 0) {
			return sumFiles[j];
		} else {
			return sumFiles[j] - sumFiles[i - 1];
		}
	}

}
