package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 16
 * @문제 이름 : 이동하기
 * @문제 링크 : https://www.acmicpc.net/problem/11048
 */
public class Main_11048 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");
    int N = Integer.parseInt(info[0]);
    int M = Integer.parseInt(info[1]);
    int[][] map = new int[N][M];
    int[][] dp = new int[N][M];

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    dp[0][0] = map[0][0];
    // 가로 첫 줄은 올 수 있는 방향이 왼쪽밖에 없으므로 미리 초기화
    for (int j = 1; j < M; j++) {
      dp[0][j] = map[0][j] + dp[0][j - 1];
    }

    // 세로 첫 줄은 올 수 있는 방향이 위쪽밖에 없으므로 미리 초기화
    for (int i = 1; i < N; i++) {
      dp[i][0] = map[i][0] + dp[i - 1][0];
    }

    // 올 수 있는 3가지 방향에서 가장 많이 가져올 수있는 방향 택함
    for (int i = 1; i < N; i++) {
      for (int j = 1; j < M; j++) {
        dp[i][j] = map[i][j];
        dp[i][j] += Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
      }
    }

    System.out.println(dp[N - 1][M - 1]);
  }
}
