package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 11
 * @문제 이름 : 스타트와 링크
 * @문제 링크 : https://www.acmicpc.net/problem/14889
 */
public class Main_14889 {
  static int N;
  static int[][] S;
  static int DIFF = Integer.MAX_VALUE;
  static HashSet<Integer> allPlayer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    N = Integer.valueOf(br.readLine());
    S = new int[N][N];
    allPlayer = new HashSet<Integer>();

    for (int i = 0; i < N; i++) {
      allPlayer.add(i);
      S[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    pickTeam(new HashSet<Integer>(), 0, 0);
    bw.write(DIFF + "\n");
    bw.flush();
    bw.close();
  }

  static void pickTeam(HashSet<Integer> startTeam, int current, int pick) {
    if (pick == N / 2) {
      HashSet<Integer> linkTeam = new HashSet<Integer>(allPlayer);
      linkTeam.removeAll(startTeam);

      int startStats = calcTeamStats(startTeam);
      int linkStats = calcTeamStats(linkTeam);

      DIFF = Math.min(DIFF, (int) Math.abs(startStats - linkStats));

      return;
    }

    if (current < N) {
      startTeam.add(current);
      pickTeam(startTeam, current + 1, pick + 1);
      startTeam.remove(current);
      pickTeam(startTeam, current + 1, pick);
    }

  }

  static int calcTeamStats(HashSet<Integer> team) {
    int stats = 0;
    ArrayList<Integer> player = new ArrayList<Integer>(team);

    for (int i = 0; i < player.size() - 1; i++) {
      for (int j = i + 1; j < player.size(); j++) {
        int p1 = player.get(i);
        int p2 = player.get(j);

        stats += S[p1][p2] + S[p2][p1];
      }
    }

    return stats;
  }

}
