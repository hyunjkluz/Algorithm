package com.programmers.simulation;

import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 01
 * @문제 이름 : 삼각 달팽이
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/68645
 */
public class TrianglSnail {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(4)));
    System.out.println(Arrays.toString(solution(5)));
    System.out.println(Arrays.toString(solution(6)));
  }

  static int[] solution(int n) {
    // 정답 배열의 길이 = 맨 마지막 숫자
    // = 등차 수열의 합
    int[] answer = new int[(n * (n + 1)) / 2];
    int[][] map = new int[n][n];
    int num = 1;
    int x = -1, y = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (i % 3 == 0) {
          // 아래로 내려가기
          x++;
        } else if (i % 3 == 1) {
          // 왼쪽으로 가기
          y++;
        } else {
          // i % 3 == 2
          // 대각선 위로 올라가기
          x--;
          y--;
        }

        map[x][y] = num++;
      }
    }

    int idx = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j] == 0)
          break;
        answer[idx++] = map[i][j];
      }
    }

    return answer;
  }
}
