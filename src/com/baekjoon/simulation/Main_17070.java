package com.baekjoon.simulation;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 23
 * @문제 이름 : 파이프 옮기기 1
 * @문제 링크 : https://www.acmicpc.net/problem/17070
 */

public class Main_17070 {
  static class Pipe {
    int dir;
    Point end, start;

    public Pipe() {
      this.dir = HORIZONTAL;
      this.start = new Point(0, 1);
      this.end = new Point(0, 0);
    }

    public Pipe(int dir, Point start, Point end) {
      this.dir = dir;
      this.start = new Point(start.x, start.y);
      this.end = new Point(end.x, end.y);
    }

  }

  static int N, solCnt;
  static int[][] map;
  final static int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.valueOf(br.readLine());
    map = new int[N][N];
    solCnt = 0;

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    solution(new Pipe());
    bw.write(solCnt + "\n");
    bw.flush();
    bw.close();
  }

  static void solution(Pipe pipe) {
    Point nEnd = new Point(pipe.start.x, pipe.start.y);
    Point nStart = new Point();

    if (pipe.dir == HORIZONTAL || pipe.dir == DIAGONAL) {
      // 가로로 움직이기
      nStart.setLocation(nEnd.x, nEnd.y + 1);

      if (canMove(nStart)) {
        if (isFin(nStart)) {
          solCnt += 1;
        } else {
          solution(new Pipe(HORIZONTAL, nStart, nEnd));
        }
      }
    }

    if (pipe.dir == VERTICAL || pipe.dir == DIAGONAL) {
      // 세로로 움직이기
      nStart.setLocation(nEnd.x + 1, nEnd.y);

      if (canMove(nStart)) {
        if (isFin(nStart)) {
          solCnt += 1;
        } else {
          solution(new Pipe(VERTICAL, nStart, nEnd));
        }
      }
    }

    // 대각선으로 움직이기 : 모두 공통
    nStart.setLocation(nEnd.x + 1, nEnd.y + 1);

    // 시작점을 대각선으로 옮길 수 있으면 && 파이프 주위의 빈 공간이 만들어질 수 있을 때
    if (canMove(nStart) && checkDiagonal(nStart)) {
      if (isFin(nStart)) {
        solCnt += 1;
      } else {
        solution(new Pipe(DIAGONAL, nStart, nEnd));
      }
    }
  }

  static boolean canMove(Point p) {
    if (0 <= p.x && p.x < N && 0 <= p.y && p.y < N && map[p.x][p.y] == 0) {
      return true;
    }
    return false;
  }

  static boolean isFin(Point p) {
    if (p.x == N - 1 && p.y == N - 1) {
      return true;
    }
    return false;
  }

  static boolean checkDiagonal(Point p) {
    // 대각선으로 파이프를 이동시키기 위해서는
    // 파이프의 머리 부분의 왼쪽과 위쪽칸이 벽이 아니여야 한다
    if (canMove(new Point(p.x, p.y - 1)) && canMove(new Point(p.x - 1, p.y))) {
      return true;
    }
    return false;
  }
}
