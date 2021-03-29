package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 03
 * @문제 이름 : 확장 게임
 * @문제 링크 : https://www.acmicpc.net/problem/16920
 */
public class Main_16920 {
  static int N, M, P;
  static int[] move;
  static char[][] map;
  static int[] dx = new int[] { 0, 0, 1, -1 }, dy = new int[] { 1, -1, 0, 0 };
  static Queue<Point> castle[];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");

    N = Integer.parseInt(info[0]);
    M = Integer.parseInt(info[1]);
    P = Integer.parseInt(info[2]);
    move = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    map = new char[N][M];
    castle = new LinkedList[P];

    // 각 플레이어의 확장할 성의 좌표를 담을 큐 배열
    for (int i = 0; i < P; i++) {
      castle[i] = new LinkedList<Point>();
    }

    for (int i = 0; i < N; i++) {
      map[i] = br.readLine().toCharArray();

      for (int j = 0; j < M; j++) {
        if (map[i][j] != '.' && map[i][j] != '#') {
          int value = Character.getNumericValue(map[i][j]);

          // 배열 인덱스에 맞게 성을 지정하기 위해 -1 한 값을 map에 다시 넣어줌
          map[i][j] = Character.forDigit(value - 1, 10);
          castle[value - 1].add(new Point(i, j));
        }
      }
    }

    // 성 확장
    extendCastle();

    int[] own = getOwnCastle();
    StringBuilder answer = new StringBuilder();

    for (int cnt : own) {
      answer.append(cnt + " ");
    }

    System.out.println(answer.toString());
  }

  static void extendCastle() {
    while (!isFinish()) {
      // 플레이어 수 만큼 반복문 돌림
      for (int player = 0; player < P; player++) {

        // 이 부분에서 큐가 0일 경우 다음 플레이어로 넘기는 조건문을 쓸 경우 = 시간초과
        // cnt번 돌리는 과정에서도 큐가 0이 될 수 있기 때문에(1 ≤ cnt ≤ 109)
        // 반복문 안에서 아무것도 하지 않다고 끝까지 돌아가는 것 자체가 시간 오버
        for (int cnt = 0; cnt < move[player]; cnt++) {
          Queue<Point> cast = castle[player];
          int expandSize = cast.size();

          // cnt번 움질일 때 마다 큐의 사이즈 확인 후 조건문 탈출
          if (expandSize == 0)
            break;

          // 큐 끝 좌표까지 확장하는게 아니라 돌릴 범위 안의 좌표만 확장해줌
          while (expandSize-- > 0) {
            Point cur = cast.poll();

            for (int i = 0; i < 4; i++) {
              int nx = cur.x + dx[i];
              int ny = cur.y + dy[i];

              if (!isIn(nx, ny) || map[nx][ny] != '.')
                continue;

              map[nx][ny] = Character.forDigit(player, 10);
              cast.add(new Point(nx, ny));
            }
          }
        }
      }
    }

  }

  static boolean isFinish() {
    for (Queue<Point> q : castle) {
      if (q.size() != 0) {
        return false;
      }
    }
    return true;
  }

  static int[] getOwnCastle() {
    int[] own = new int[P];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] != '.' && map[i][j] != '#') {
          int value = Character.getNumericValue(map[i][j]);

          own[value] += 1;
        }
      }
    }

    return own;
  }

  static boolean isIn(int x, int y) {
    if (0 <= x && x < N && 0 <= y && y < M) {
      return true;
    }
    return false;
  }

  static void printCastle() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(map[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
