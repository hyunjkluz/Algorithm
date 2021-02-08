package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Feb 8, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/15683 감시
 */
class Camera {
  Point pos;
  int num;
  int[] dir;

  public Camera(int x, int y, int num) {
    this.pos = new Point(x, y);
    this.num = num;
    this.dir = new int[4];

    Arrays.fill(this.dir, -1);

    switch (this.num) {
      case 1:
        dir[0] = 0;
        break;
      case 2:
        dir[0] = 0;
        dir[1] = 2;
        break;
      case 3:
        dir[0] = 0;
        dir[1] = 3;
        break;
      case 4:
        dir[0] = 0;
        dir[1] = 2;
        dir[2] = 3;
        break;
      default: // 5번 카메라
        dir[0] = 0;
        dir[1] = 1;
        dir[2] = 2;
        dir[3] = 3;
        break;
    }
  }

}

public class Main_15683 {
  static int N, M;
  static int[][] map;
  static ArrayList<Camera> cameras;
  static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 }; // 동, 남, 서, 북
  static int minBS = Integer.MAX_VALUE; // 전체 최소 사각지대 개수 담을 변수

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] nm = br.readLine().split(" ");

    N = Integer.parseInt(nm[0]);
    M = Integer.parseInt(nm[1]);
    map = new int[N][M];
    cameras = new ArrayList<Camera>();

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

      for (int j = 0; j < M; j++) {
        if (map[i][j] != 0 && map[i][j] != 6) {
          cameras.add(new Camera(i, j, map[i][j]));
        }
      }
    }

    turnCameraDFS(0);

    System.out.println(minBS);
  }

  static void turnCameraDFS(int depth) {
    // 모두 다 돌렸을 때
    if (depth == cameras.size()) {
      // 돌려진 CCTV로 감시 구역 표시
      int[][] newMap = monitor();
      // 사각 지대 개수 계산
      int blindSpot = countBlindSpot(newMap);

      // 최솟값 판별
      minBS = Math.min(minBS, blindSpot);
    } else {
      // 아직 돌려야하는 CCTV 가 남았을 때
      // depth 위치에 있는 카메라 뽑음
      Camera now = cameras.get(depth);
      int[] originalDir = now.dir.clone();

      if (now.num != 5) {
        // num = 2 는 두번만 회전시키면 됨(나머지는 중복)
        // num = 1, 3, 4 는 4방향 회전이 모두 방향이 다름
        int turnCount = now.num == 2 ? 2 : 4;

        for (int i = 0; i < turnCount; i++) {
          for (int j = 0; j < now.dir.length && now.dir[j] != -1; j++) {
            now.dir[j] = (now.dir[j] + i) % 4;
          }
          // DFS 돌리기
          turnCameraDFS(depth + 1);

          // DFS가 끝나고 방향 원래대로 돌리기
          now.dir = originalDir.clone();
        }
      } else {
        // 5번 카메라는 돌릴 필요 없음
        turnCameraDFS(depth + 1);
      }
    }
  }

  static int[][] monitor() {
    // 원본 map 복제
    int[][] newMap = copyMap();

    for (Camera c : cameras) {
      Point position = c.pos;
      int[] dir = c.dir;

      // 방향이 입력되어있는 칸 대상으로 반복문
      for (int i = 0; i < dir.length && c.dir[i] != -1; i++) {
        int nx = position.x + dx[dir[i]];
        int ny = position.y + dy[dir[i]];

        // 같은 방향으로 움직이면서 감시 가능 표시
        while (isIn(nx, ny)) {
          if (newMap[nx][ny] == 0)
            newMap[nx][ny] = -1;

          if (newMap[nx][ny] == 6)
            break;

          nx += dx[dir[i]];
          ny += dy[dir[i]];
        }
      }
    }

    return newMap;
  }

  static int countBlindSpot(int[][] newMap) {
    int blindSpot = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (newMap[i][j] == 0) {
          blindSpot += 1;
        }
      }
    }

    return blindSpot;
  }

  static int[][] copyMap() {
    int[][] newMap = new int[N][M];
    for (int i = 0; i < N; i++) {
      System.arraycopy(map[i], 0, newMap[i], 0, M);
    }
    return newMap;
  }

  static boolean isIn(int x, int y) {
    if (0 <= x && x < N && 0 <= y && y < M) {
      return true;
    }
    return false;
  }

}