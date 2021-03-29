package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 25
 * @문제 이름 : 탈옥
 * @문제 링크 : https://www.acmicpc.net/problem/9376
 * @참고 링크 : https://guy-who-writes-sourcecode.tistory.com/34?category=824552 실패한
 *     문제
 */
class Prisoner implements Comparable<Prisoner> {
  int x, y, openDoor;

  public Prisoner(int x, int y) {
    this.x = x;
    this.y = y;
    this.openDoor = 0;
  }

  public Prisoner(int x, int y, int openDoor) {
    this.x = x;
    this.y = y;
    this.openDoor = openDoor;
  }

  @Override
  public int compareTo(Prisoner o) {
    return Integer.compare(this.openDoor, o.openDoor);
  }
}

public class Main_9376 {
  static int h, w;
  static char[][] map;
  static Point[] prisoner;
  static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int testCase = Integer.parseInt(br.readLine());
    StringBuilder answer = new StringBuilder();

    while (testCase-- > 0) {
      String[] info = br.readLine().split(" ");

      h = Integer.parseInt(info[0]);
      w = Integer.parseInt(info[1]);
      map = new char[h + 2][w + 2];
      prisoner = new Point[2];

      Arrays.fill(map[0], '.');
      Arrays.fill(map[map.length - 1], '.');

      int pIdx = 0;

      for (int i = 1; i < map.length - 1; i++) {
        char[] line = br.readLine().toCharArray();

        for (int j = 0; j < map[i].length; j++) {
          if (j == 0 || j == map[i].length - 1) {
            map[i][j] = '.';
            continue;
          }

          map[i][j] = line[j - 1];

          if (map[i][j] == '$') {
            prisoner[pIdx++] = new Point(i, j);
          }
        }
      }

      // 상근이는 맨 앞에서 부터 시작
      int[][] sangDist = distDFS(new Point(0, 0));
      int[][] p1Dist = distDFS(prisoner[0]);
      int[][] p2Dist = distDFS(prisoner[1]);
      int minWallCnt = sumOpenWallCount(sangDist, p1Dist, p2Dist);

      answer.append(minWallCnt + "\n");
    }

    System.out.println(answer.toString());
  }

  static int[][] distDFS(Point p) {
    // 우선 순위 큐로 문을 가장 적게 연 사람을 우선적으로 탐색
    PriorityQueue<Prisoner> queue = new PriorityQueue<>();
    boolean[][] visited = new boolean[h + 2][w + 2];
    // 각 지점에서 최소로 열어온 문의 개수
    int[][] dist = new int[h + 2][w + 2];

    visited[p.x][p.y] = true;
    dist[p.x][p.y] = 0;
    queue.add(new Prisoner(p.x, p.y));

    while (!queue.isEmpty()) {
      Prisoner cur = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];

        if (!isIn(nx, ny) || map[nx][ny] == '*' || visited[nx][ny])
          continue;

        visited[nx][ny] = true;

        // 만약 문이라면 문의 개수 증가
        int newDist = map[nx][ny] == '#' ? cur.openDoor + 1 : cur.openDoor;
        dist[nx][ny] = newDist;

        queue.add(new Prisoner(nx, ny, newDist));
      }
    }
    return dist;
  }

  static int sumOpenWallCount(int[][] d1, int[][] d2, int[][] d3) {
    int minWallCnt = Integer.MAX_VALUE;
    int[][] distSum = new int[h + 2][w + 2];

    for (int i = 0; i < h + 2; i++) {
      for (int j = 0; j < w + 2; j++) {
        if (map[i][j] == '*') {
          continue; // 벽은 못가니 고려 x
        }

        // 합산의 의미 = 특정 위치까지 세 명이 문을 열어온 개수를 확인
        // 만났을 때 문을 열어온 최소 개수 확인
        distSum[i][j] = d1[i][j] + d2[i][j] + d3[i][j];

        if (map[i][j] == '#') {
          // 합산하는 위치에 문이 있다면 3명 중에 한명만 열면 되기 때문에 -2를 해줌
          distSum[i][j] -= 2;
        }

        minWallCnt = Math.min(minWallCnt, distSum[i][j]);
      }
    }

    return minWallCnt;
  }

  static boolean isIn(int x, int y) {
    if (0 <= x && x < h + 2 && 0 <= y && y < w + 2) {
      return true;
    }
    return false;
  }
}