package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 24
 * @문제 이름 : 아기 상어
 * @문제 링크 : https://www.acmicpc.net/problem/16236
 */
public class Main_16236 {
  static int N;
  static int[][] map, dist;
  static Point shark, target;
  static int minDist, eatCnt, sharkSize;
  static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    dist = new int[N][N];

    int fishCnt = 0;

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      for (int j = 0; j < N; j++) {
        // 상어 발견하면
        if (map[i][j] == 9) {
          shark = new Point(i, j);
          map[i][j] = 0;
          sharkSize = 2;
          eatCnt = 0;
        }
        // 상어가 아닌 물고기 전체 개수 계산
        else if (map[i][j] != 0) {
          fishCnt++;
        }
      }
    }

    System.out.println(fishCnt == 0 ? 0 : eatFish());
  }

  static int eatFish() {
    int time = 0;

    while (true) {
      clearDist();

      findNearFish(shark.x, shark.y);

      // 먹을 수 있는 물고기 못찾았으면 종료
      if (target.x == -1 && target.y == -1)
        break;

      // 시간 증가
      time += dist[target.x][target.y];

      // 물고기 먹음
      map[target.x][target.y] = 0;
      eatCnt++;

      // 만약 먹은 물고기 수가 상어의 크기와 같다면
      if (eatCnt == sharkSize) {
        // 상어 크기 키우고 먹은 개수 초기화
        sharkSize++;
        eatCnt = 0;
      }

      // 상어 위치 초기화
      shark.setLocation(target.x, target.y);
    }

    return time;
  }

  static void clearDist() {
    minDist = Integer.MAX_VALUE;
    target = new Point(-1, -1);

    for (int i = 0; i < N; i++) {
      Arrays.fill(dist[i], -1);
    }
  }

  static void findNearFish(int x, int y) {
    Queue<Point> queue = new LinkedList<Point>();

    queue.add(new Point(x, y));
    dist[x][y] = 0;

    while (!queue.isEmpty()) {
      Point cur = queue.poll();

      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];

        // 배열의 범위를 벗어나거나
        // 이동하려는 칸의 물고기 값이 아기 상어보다 크거나
        // 이미 이동한 흔적이 있으면 이동하지 않음
        if (!isIn(nx, ny) || map[nx][ny] > sharkSize || dist[nx][ny] != -1)
          continue;

        // 이동 거리 측정
        dist[nx][ny] = dist[cur.x][cur.y] + 1;

        // 상어의 크기보다 작은 물고기 발견하면
        // 먹어서 타겟을 바꿔줘야함
        if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
          // 최소 이동거리에 해당되면
          if (minDist > dist[nx][ny]) {
            // 잡아먹을 물고기 갱신
            target.setLocation(nx, ny);
            minDist = dist[nx][ny];
          }
          // 최소 이동거리와 같은 물고기 발견하면 좀 더 위/왼쪽에 있는 물고기 선택
          else if (minDist == dist[nx][ny]) {
            // 좀 더 위에 있는 : x 값이 더 작음
            // 좀 더 왼쪽에 있는 : x 는 같은데 y가 더 작음
            if ((nx < target.x) || (nx == target.x && ny < target.y)) {
              target.setLocation(nx, ny);
            }
          }
        }

        queue.add(new Point(nx, ny));
      }
    }
  }

  static boolean isIn(int x, int y) {
    if (0 <= x && x < N && 0 <= y && y < N) {
      return true;
    }
    return false;
  }

}
