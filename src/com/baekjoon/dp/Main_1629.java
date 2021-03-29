package com.baekjoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 17
 * @문제 이름 : 곱셈
 * @문제 링크 : https://www.acmicpc.net/problem/1629
 */
public class Main_1629 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");
    long A = Long.valueOf(info[0]);
    long B = Long.valueOf(info[1]);
    long C = Long.valueOf(info[2]);

    bw.write(pow(A % C, B, C) + "\n");
    bw.flush();
    bw.close();

  }

  static long pow(long a, long b, long c) {
    if (b == 0) {
      return 1;
    }
    if (b == 1) {
      return a;
    }

    // 지수의 값이 홀수이건 짝수이건 b/2로 통일 가능
    long ab = pow(a, b / 2, c) % c;
    long result = (ab * ab) % c;

    if (b % 2 == 0) {
      // 짝수일 때
      return (ab * ab) % c;
    } else {
      // 홀수일 때
      return (result * a) % c;
    }
  }
}
