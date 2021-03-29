package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 17
 * @문제 이름 : 행렬 제곱
 * @문제 링크 : https://www.acmicpc.net/problem/10830
 */
public class Main_10830 {
  static int N;
  static long B;
  static int[][] matrix, unitMatrix;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    N = Integer.valueOf(info[0]);
    B = Long.valueOf(info[1]);
    matrix = new int[N][N];
    unitMatrix = new int[N][N];

    // 기본 행렬 입력받음
    for (int i = 0; i < N; i++) {
      info = br.readLine().split(" ");

      for (int j = 0; j < N; j++) {
        matrix[i][j] = Integer.valueOf(info[j]) % 1000;

        // 단위행렬 생성
        if (i == j) {
          unitMatrix[i][j] = 1;
        }
      }
    }

    int[][] result = pow(B);

    bw.write(print(result) + "\n");
    bw.flush();
    bw.close();
  }

  static int[][] pow(long b) {
    // 지수의 값이
    if (b == 0) {
      // 단위행렬 반환
      return unitMatrix;
    }
    if (b == 1) {
      // 기본행렬 반환
      return matrix;
    }

    int[][] ab = pow(b / 2);
    int[][] temp = powMatrix(ab, ab);

    if (b % 2 == 0) {
      // 짝수일 때
      return temp;
    } else {
      // 홀수일 때
      return powMatrix(temp, matrix);
    }

  }

  static int[][] powMatrix(int[][] a, int[][] b) {
    int[][] result = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          result[i][j] += (a[i][k] * b[k][j]) % 1000;
        }
        result[i][j] %= 1000;
      }
    }

    return result;
  }

  static String print(int[][] a) {
    StringBuilder answer = new StringBuilder();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        answer.append(a[i][j] + " ");
      }
      answer.append(("\n"));
    }

    return answer.toString();
  }
}
