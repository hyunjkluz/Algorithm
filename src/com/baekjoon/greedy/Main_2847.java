package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Feb 10, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2847 게임을 만든 동준이
 */
public class Main_2847 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] levels = new int[N];

    for (int i = 0; i < N; i++) {
      levels[i] = Integer.parseInt(br.readLine());
    }

    int discount = 0;

    for (int i = N - 1; i > 0; i--) {
      // 낮은 레벨로 갈수록 점수가 떨어져야 함
      // 이전 레벨 점수가 같거나 더 크면 안됨
      if (levels[i] <= levels[i - 1]) {
        // 최소 1점의 차이만 나도 됨
        discount += (int) Math.abs((levels[i] - 1) - levels[i - 1]);
        levels[i - 1] = levels[i] - 1;
      }
    }

    System.out.println(discount);

  }
}
