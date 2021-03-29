package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 09
 * @문제 이름 : 나무 재테크
 * @문제 링크 : https://www.acmicpc.net/problem/16235
 */

public class Main_16235 {
  static class Land {
    // 땅의 양분
    int food;
    // 1칸의 땅에 심긴 나무의 나이
    PriorityQueue<Integer> trees;

    public Land() {
      this.food = 5;
      trees = new PriorityQueue<Integer>();
    }

  }

  static int N, M, K;
  static int[][] A;
  static Land[][] map;
  static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 }, dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    N = Integer.valueOf(info[0]);
    M = Integer.valueOf(info[1]);
    K = Integer.valueOf(info[2]);
    A = new int[N][N];
    map = new Land[N][N];

    // 각 칸에 추가되는 양분의 양을 담은 배열 입력받음
    for (int i = 0; i < N; i++) {
      A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    // 땅 지도 초기화
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j] = new Land();
      }
    }

    for (int i = 0; i < M; i++) {
      info = br.readLine().split(" ");

      int x = Integer.valueOf(info[0]);
      int y = Integer.valueOf(info[1]);
      int value = Integer.valueOf(info[2]);

      // 나무 심기
      map[x - 1][y - 1].trees.add(value);
    }

    int treeCnt = 0;
    for (int i = 0; i < K; i++) {
      treeCnt = oneYearFlow();

      // 살아있는 나무가 없으면 반복문 종료
      if (treeCnt == 0)
        break;
    }

    bw.write(treeCnt + " \n");
    bw.flush();
    bw.close();
  }

  static int oneYearFlow() {
    int[][] breeding = springAndSummer();

    fall(breeding);
    winter();

    return countAliveTree();
  }

  static int[][] springAndSummer() {
    // 각 칸에서 가을에 번식 가능한 나무의 수를 담을 배열
    int[][] breeding = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        PriorityQueue<Integer> newTree = new PriorityQueue<>();
        Land land = map[i][j];
        // 번식 가능한 나무의 수
        int breedingCnt = 0;

        // 봄 - 나무 존재하고, 나이만큼 양분 먹을 수 있으면
        while (!land.trees.isEmpty() && land.food - land.trees.peek() >= 0) {
          int age = land.trees.poll();

          // 나이만큼 양분 먹음
          land.food -= age;
          // 나이 증가
          newTree.add(age + 1);

          // 가을 - 증가한 나이가 5의 배수이면 번식할 수 있음
          if ((age + 1) % 5 == 0) {
            breedingCnt += 1;
          }
        }

        // 큐가 비어있지 않음 = 양분 못먹은 나무 존재 = 죽어야 함
        // 여름 - 죽은 나무 양분으로 전환
        int summerFood = 0;
        while (!land.trees.isEmpty()) {
          summerFood += (int) Math.floor(land.trees.poll() / 2);
        }

        // 새로운 나무 정보 저장
        land.trees = newTree;

        // 죽은 나무 양분 저장
        land.food += summerFood;

        // 번식 가능한 나무 개수 저장
        breeding[i][j] = breedingCnt;
      }
    }

    return breeding;
  }

  static void fall(int[][] breeding) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (breeding[i][j] == 0)
          continue;

        // 번식 가능한 나무가 있으면
        // 주위 8칸으로 나무 번식
        for (int k = 0; k < 8; k++) {
          int nx = i + dx[k];
          int ny = j + dy[k];
          int newTreeCnt = breeding[i][j];

          // 주위의 칸이 범위 밖이면 넘김
          if (!isIn(nx, ny))
            continue;

          // 번식 가능한 나무의 개수대로 나이가 1인 나무 심음
          while (newTreeCnt-- > 0) {
            map[nx][ny].trees.add(1);
          }
        }
      }
    }
  }

  static void winter() {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        map[i][j].food += A[i][j];
      }
    }
  }

  static int countAliveTree() {
    int alive = 0;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        alive += map[i][j].trees.size();
      }
    }

    return alive;
  }

  static boolean isIn(int x, int y) {
    if (0 <= x && x < N && 0 <= y && y < N) {
      return true;
    }
    return false;
  }

}
