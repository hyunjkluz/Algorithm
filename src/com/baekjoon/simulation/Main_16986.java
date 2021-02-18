package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 15
 * @문제 이름 : 인싸들의 가위바위보
 * @문제 링크 : https://www.acmicpc.net/problem/16986
 */
public class Main_16986 {
  static int N, K;
  static int[][] map;
  static int[][] action;
  static boolean canWin = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");

    N = Integer.parseInt(info[0]);
    K = Integer.parseInt(info[1]);
    map = new int[N + 1][N + 1];
    action = new int[3][];

    for (int i = 1; i <= N; i++) {
      // 1, 1부터 담아야 하기 때문에 밑의 방식으로 하면 1, 0 / 2, 0부터 담기게 됨 ... 인덱스 생각 좀 하자..
      // map[i]=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

      info = br.readLine().split(" ");
      for (int j = 1; j <= N; j++) {
        map[i][j] = Integer.parseInt(info[j - 1]);
      }
    }

    action[0] = new int[N];
    action[1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    action[2] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    // 이겨야 하는 판의 수가 손동작의 개수보다 많으면 무조건 동작의 중복이 일어나기 때문에
    // 손동작의 개수가 같거나 많아야 함
    if (N >= K) {
      // 비기거나 져도 상관없음, 이기는 개수만 채우면 되기 때문에
      // 지우가 낼 수 있는 손동작의 경우의 수를 뽑아서 시뮬레이션 돌림
      pickAction(0, new boolean[N + 1]);
    }

    System.out.println(canWin ? 1 : 0);
  }

  static void pickAction(int n, boolean[] selected) {
    if (n == N) {
      // 진행되는 손동작의 위치를 담고 있는 배열
      // 인덱스에 해당하는 사람이 몇번 째 판을 하고 있는지도 알 수 있음
      int[] idx = new int[3];
      // 각 사람이 몇 번 이겼는지 기록하는 배열
      int[] score = new int[3];

      if (simulation(0, 1, idx, score)) {
        canWin = true;
      }

      return;
    }

    // 입력 받는 손동작들은 1부터 시작하기 때문에 1부터 값 넣음
    for (int i = 1; i <= N; i++) {
      if (!selected[i]) {
        selected[i] = true;
        action[0][n] = i;
        pickAction(n + 1, selected);
        selected[i] = false;
      }
    }
  }

  static boolean simulation(int p1, int p2, int[] idx, int[] score) {
    int winner = p1;
    int p1Action = action[p1][idx[p1]];
    int p2Action = action[p2][idx[p2]];

    System.out.printf("p1(%d) : %d, p2(%d) : %d\n", p1, p1Action, p2, p2Action);

    // 게임 결과에 따라 승자를 정함
    switch (map[p1Action][p2Action]) {
      case 2:
        winner = p1;
        break;
      case 1:
        // 비겼을 경우 (고정되어있는)게임 진행 순서 상 뒤에 있는 사람이 이김 = 인덱스 큰 사람이 이김
        winner = Math.max(p1, p2);
        break;
      case 0:
        winner = p2;
        break;
    }

    score[winner]++;
    idx[p1]++;
    idx[p2]++;

    if (score[1] == K || score[2] == K)
      return false;

    if (score[0] == K)
      return true;

    // 진우가 게임 하는 판이 내야하는 손동작보다 크면 무조건 중복 일어남
    if (idx[0] >= N)
      return false;

    // 이번 판 승자와 겨룰 다음 타자 구하기
    int nextPlayer = 0;
    for (int i = 0; i < 3; i++) {
      if (i != p1 && i != p2) {
        nextPlayer = i;
      }
    }

    return simulation(winner, nextPlayer, idx, score);
  }
}
