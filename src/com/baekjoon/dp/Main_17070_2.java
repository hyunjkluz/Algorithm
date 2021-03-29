package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * @문제 풀이 해설 :
 *     https://velog.io/@hyunjkluz/%EB%B0%B1%EC%A4%8017070-%ED%8C%8C%EC%9D%B4%ED%94%84-%EC%98%AE%EA%B8%B0%EA%B8%B0-1Java
 */

public class Main_17070_2 {
  static int N;
  static int[][] map;
  static int[][][] dp;
  final static int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.valueOf(br.readLine());
    map = new int[N][N];
    // [행][열][가로, 세로, 대각선]
    dp = new int[3][N][N];

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    dp[HORIZONTAL][0][1] = 1;
    int result = solution();
    bw.write(result + "\n");
    bw.flush();
    bw.close();
  }

  static int solution() {
    for (int i = 0; i < N; i++) {
      for (int j = 2; j < N; j++) {
        if (map[i][j] == 1)
          continue;

        // 가로 파이프 놓기
        dp[HORIZONTAL][i][j] = dp[HORIZONTAL][i][j - 1] + dp[DIAGONAL][i][j - 1];

        // 맨 윗줄은 가로 파이프만 놓을 수 있음
        if (i == 0)
          continue;

        // 세로 파이프 놓기
        dp[VERTICAL][i][j] = dp[VERTICAL][i - 1][j] + dp[DIAGONAL][i - 1][j];

        // 대각선으로 파이프를 놓을 때 파이프가
        // 새로 놓이는 점을 기준으로 그 왼쪽과 위쪽이 빈칸이여야 함
        if (map[i - 1][j] == 1 || map[i][j - 1] == 1)
          continue;

        // 대각선 파이프 놓기
        dp[DIAGONAL][i][j] = dp[HORIZONTAL][i - 1][j - 1] + dp[VERTICAL][i - 1][j - 1] + dp[DIAGONAL][i - 1][j - 1];

      }
    }
    return dp[HORIZONTAL][N - 1][N - 1] + dp[VERTICAL][N - 1][N - 1] + dp[DIAGONAL][N - 1][N - 1];
  }

}
