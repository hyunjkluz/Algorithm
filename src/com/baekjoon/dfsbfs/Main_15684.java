package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 17
 * @문제 이름 : 사다리 조작
 * @문제 링크 : https://www.acmicpc.net/problem/15684
 */
public class Main_15684 {
  static int N, M, H;
  static int[][] map;
  static ArrayList<Point> ladder;
  static boolean finish = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");

    N = Integer.parseInt(info[0]);
    M = Integer.parseInt(info[1]);
    H = Integer.parseInt(info[2]);
    map = new int[H + 1][N + 1];
    ladder = new ArrayList<Point>();

    while (M-- > 0) {
      info = br.readLine().split(" ");
      makeLine(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
    }

    // 사다리를 만드는 개수를 기준으로 DFS
    int flag = 0;
    for (; flag <= 3; flag++) {
      makeLadder(1, 0, flag);
      if (finish)
        break;
    }

    System.out.println(finish ? flag : -1);
  }

  static void makeLine(int x, int y) {
    // x -> y : 오른쪽으로 가는 선 있음
    map[x][y] = 1;
    // x <- y : 왼쪽으로 가는 선 있음
    map[x][y + 1] = 2;
  }

  static void deleteLine(int x, int y) {
    map[x][y] = map[x][y + 1] = 0;
  }

  static void makeLadder(int x, int ladder, int flag) {
    if (finish)
      return;

    if (flag == ladder) {
      if (checkDest())
        finish = true;

      return;
    }

    // 가로 축 기준으로 가로 선 만듬
    for (int i = x; i <= H; i++) {
      for (int j = 1; j < N; j++) {
        // 선은 무조건 오른쪽으로 가는 선이 생기는 거니깐
        // j + 1 옆에 만들어도 됨(j + 1에 선이 있어도 어차피 왼쪽으로 가는 선이니까)
        if (map[i][j] == 0 && map[i][j + 1] == 0) {
          makeLine(i, j);
          makeLadder(i, ladder + 1, flag);
          deleteLine(i, j);
        }
      }
    }
  }

  static boolean checkDest() {
    for (int y = 1; y <= N; y++) {
      int moveY = y;
      int x = 1;

      while (x <= H) {
        if (map[x][moveY] == 1)
          moveY++;
        else if (map[x][moveY] == 2)
          moveY--;

        x++;
      }

      if (y != moveY) {
        return false;
      }
    }

    return true;
  }

}
