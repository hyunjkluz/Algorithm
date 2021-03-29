package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 25
 * @문제 이름 : 경사로
 * @문제 링크 : https://www.acmicpc.net/problem/14890
 */
public class Main_14890 {
  static int N, L;
  static int[][] map;
  static int roadCnt;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    N = Integer.valueOf(info[0]);
    L = Integer.valueOf(info[1]);
    map = new int[N][N];
    roadCnt = 0;

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    solution();

    bw.write(roadCnt + "\n");
    bw.flush();
    bw.close();
  }

  static void solution() {
    for (int i = 0; i < N; i++) {
      if (roadSolution(map[i].clone())) {
        roadCnt += 1;
      }

      int[] colRoad = new int[N];
      for (int j = 0; j < N; j++) {
        colRoad[j] = map[j][i];
      }

      if (roadSolution(colRoad)) {
        roadCnt += 1;
      }

    }
  }

  static boolean roadSolution(int[] road) {
    boolean[] isInstall = new boolean[N];

    for (int i = 0; i < N - 1; i++) {
      if (Math.abs(road[i] - road[i + 1]) > 1)
        return false;

      if (road[i] == road[i + 1])
        continue;

      // 올라가는 경사로 설치 : 1 -> 2
      if (road[i] + 1 == road[i + 1]) {
        for (int j = i; j > i - L; j--) {
          if (j < 0 || isInstall[j] || road[i] != road[j])
            return false;
          isInstall[j] = true;
        }
      }
      // 내려가는 경사로 설치 : 2 -> 1
      if (road[i] - 1 == road[i + 1]) {
        for (int j = i + 1; j <= i + L; j++) {
          if (j >= N || isInstall[j] || road[j] != road[i + 1])
            return false;
          isInstall[j] = true;
        }
        i += L - 1;
      }
    }

    return true;
  }
}
