package com.baekjoon.simulation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 25
 * @문제 이름 : 달팽이
 * @문제 링크 : https://www.acmicpc.net/problem/1913
 */
public class Main_1913 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    // 달팽이에서 찾으려는 수
    int find = Integer.parseInt(br.readLine());
    // 999의 제곱의 수 까지 담아야 하니깐 2차원 배열 타입을 long으로
    long[][] map = new long[N][N];
    // 한 바퀴 돌리는 껍질의 수
    int layer = (N / 2) + 1;
    // N 제곱의 수
    long nn = (long) Math.pow(N, 2);
    // 찾으려하는 수의 좌표를 담는 변수
    Point findPoint = new Point(0, 0);
    StringBuilder answer = new StringBuilder();

    // 가장 바깥의 껍질부터 하나씩 들어감
    for (int l = 0; l <= layer; l++) {
      // l 번째 껍질의 가로 크기
      int n = N - l - 1;

      // 맨 안쪽의 껍질 = 1
      if (l == layer) {
        map[layer - 1][layer - 1] = 1;
        break;
      }

      // 1 4 4 4
      // 1 3
      // 1 3
      // 2 2 2 3

      // 1번
      for (int x = l; x < n; x++) {
        // 숫자를 map에 넣을 때 찾으려는 숫자인지 확인하고 맞으면 좌표 값 갱신
        if (nn == find)
          findPoint.setLocation(x, l);
        map[x][l] = nn--;
      }

      // 2번
      for (int y = l; y < n; y++) {
        if (nn == find)
          findPoint.setLocation(n, y);
        map[n][y] = nn--;
      }

      // 3번
      for (int x = n; x > l; x--) {
        if (nn == find)
          findPoint.setLocation(x, n);
        map[x][n] = nn--;
      }

      // 4번
      for (int y = n; y > l; y--) {
        if (nn == find)
          findPoint.setLocation(l, y);
        map[l][y] = nn--;
      }
    }

    // 화면 출력용
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        answer.append(map[i][j] + " ");
      }
      answer.append("\n");
    }

    // 문제는 좌표가 1, 1부터 시작으로 하기 때문에 1씩 증가
    answer.append((findPoint.x + 1) + " " + (findPoint.y + 1));

    System.out.println(answer.toString());

  }
}
