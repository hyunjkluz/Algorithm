package com.baekjoon.queue;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author : kimhyunjin
 * @CretaedAt : Feb 9, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/3190 뱀
 */
public class Main_3190 {
  static int N, K, L;
  static int[][] map;
  static int[] commandSec;
  static char[] commandDir;
  static Deque<Point> snake;
  static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 }; // 북-동-남-서

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());
    map = new int[N][N];

    for (int i = 0; i < K; i++) {
      int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      // 지도에 사과 표시
      map[pos[0] - 1][pos[1] - 1] = 2;
    }

    L = Integer.parseInt(br.readLine());

    commandSec = new int[L];
    commandDir = new char[L];

    for (int i = 0; i < L; i++) {
      String[] command = br.readLine().split(" ");

      commandSec[i] = Integer.parseInt(command[0]);
      commandDir[i] = command[1].toCharArray()[0];
    }

    System.out.println(solution());

  }

  static int solution() {
    int gameSec = 0;
    // 뱀 위치 초기화
    // 앞(꼬리) - 뒤(머리)
    snake = new ArrayDeque<Point>();
    snake.add(new Point(0, 0));
    map[0][0] = 1;

    // 뱀이 가는 방향을 가지고 있는 변수 (북 0, 동 1, 남 2, 서 3)
    int flowDir = 1;
    // 방향 바꾸는 카운트를 세는 변수
    int turn = 0;

    while (true) {
      gameSec++;

      // 예상 - 머리 이동
      Point head = snake.peekLast();
      int nx = head.x + dx[flowDir];
      int ny = head.y + dy[flowDir];

      // 범위를 벗어나거나 몸통에 부딪혔을 때
      if (!isIn(nx, ny) || map[nx][ny] == 1)
        break;

      // 사과를 발견 못 했을 때 : 꼬리 자르기
      // 머리부터 이동하고 사과를 찾으면 사과가 머리에 의해서 사라지기 때문에
      // 사과부터 먹고 실제로 머리 이동해야 함
      if (map[nx][ny] != 2) {
        Point tail = snake.poll();
        map[tail.x][tail.y] = 0;
      }

      // 실제 - 머리 이동
      snake.addLast(new Point(nx, ny));
      map[nx][ny] = 1;

      if (turn < L && gameSec == commandSec[turn]) {
        flowDir = changeDir(flowDir, commandDir[turn]);
        turn++;
      }
    }

    return gameSec;
  }

  static int changeDir(int flowDir, char flagDir) {
    // 방향 90도 바꾸는 공식! (북동남서 기준)
    if (flagDir == 'D') {
      // 오른쪽으로 돌리기
      return (flowDir + 1) % 4;
    } else {
      // 왼쪽으로 돌리기
      return (flowDir + 3) % 4;
    }

    // switch (flowDir) {
    // case 0:
    // if (flagDir == 'L')
    // return 3;
    // else
    // return 2;
    // case 1:
    // if (flagDir == 'L')
    // return 0;
    // else
    // return 1;
    // case 2:
    // if (flagDir == 'L')
    // return 1;
    // else
    // return 0;
    // default:
    // if (flagDir == 'L')
    // return 2;
    // else
    // return 3;
    // }
  }

  static boolean isIn(int x, int y) {
    if (0 <= x && x < N && 0 <= y && y < N) {
      return true;
    }
    return false;
  }
}