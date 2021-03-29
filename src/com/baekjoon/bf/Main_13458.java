package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 22
 * @문제 이름 : 시험 감독
 * @문제 링크 : https://www.acmicpc.net/problem/13458
 */
public class Main_13458 {
  static int N, B, C;
  static int[] A;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.valueOf(br.readLine());
    A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    String[] info = br.readLine().split(" ");
    B = Integer.valueOf(info[0]);
    C = Integer.valueOf(info[1]);

    bw.write(solution() + "\n");
    bw.flush();
    bw.close();
  }

  static long solution() {
    long supervisor = 0;

    for (int i = 0; i < N; i++) {
      supervisor++;
      A[i] -= B;

      if (A[i] > 0) {
        supervisor += A[i] / C;
        supervisor += A[i] % C == 0 ? 0 : 1;
      }
    }

    return supervisor;
  }

}
