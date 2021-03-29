package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 03
 * @문제 이름 : 부분수열의 합
 * @문제 링크 : https://www.acmicpc.net/problem/1182
 */
public class Main_1182 {
  static int N, S, answer;
  static int[] num;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");
    N = Integer.parseInt(info[0]);
    S = Integer.parseInt(info[1]);
    answer = 0;
    num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    calcDFS(0, 0);
    // 합이 0일 경우 공집합도 포함되므로 그 수를 하나 빼주고 출력
    System.out.println(S == 0 ? --answer : answer);
  }

  static void calcDFS(int sum, int depth) {
    if (depth == N && sum == S) {
      answer++;
      return;
    }

    if (depth < N) {
      calcDFS(sum + num[depth], depth + 1);
      calcDFS(sum, depth + 1);
    }
  }

}
