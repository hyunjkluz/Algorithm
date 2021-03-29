package com.programmers.queue;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 29
 * @문제 이름 : 순위
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/49191
 */

class Rank {
  static class Info implements Comparable<Info> {
    int player;
    HashSet<Integer> winner, looser;

    public Info(int player) {
      this.player = player;
      winner = new HashSet<Integer>();
      looser = new HashSet<Integer>();
    }

    @Override
    public int compareTo(Info o) {
      return Integer.compare(o.winner.size() + o.looser.size(), this.winner.size() + this.looser.size());
    }
  }

  public static void main(String[] args) {
    int n = 5;
    int[][] results = new int[][] { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };
    // System.out.println(solution(n, results));
    System.out.println(solution2(n, results));
  }

  static int solution(int n, int[][] results) {
    int answer = 0;
    ArrayList<Info> graph = new ArrayList<Info>();

    for (int i = 0; i < n; i++) {
      graph.add(new Info(i));
    }

    // 승리 list
    for (int[] r : results) {
      int win = r[0] - 1;
      int lose = r[1] - 1;

      Info winnerInfo = graph.get(win);
      Info looserInfo = graph.get(lose);

      // 승자의 패자 사람 집합에 패자 넣음
      winnerInfo.looser.add(lose);
      // 승자의 패자 집합에 패자에게 진 사람 집합 다 넣음
      winnerInfo.looser.addAll(looserInfo.looser);

      // 패자의 승자 집합에 승자 넣음
      looserInfo.winner.add(win);
      // 패자의 승자 집합에 승자에게 이긴 사람 집합 다 넣음
      looserInfo.winner.addAll(winnerInfo.winner);
    }

    // 가장 많은 결과를 알고 있는 선수 순서대로 우선순위 큐 생성
    PriorityQueue<Info> pq = new PriorityQueue<Info>(graph);

    while (!pq.isEmpty()) {
      Info player = pq.poll();

      // 내 승자들의 패배자 집합에 나의 패배자 넣음
      for (Integer win : player.winner) {
        graph.get(win).looser.addAll(player.looser);
      }

      // 내 패배자들의 승자 집합에 나의 승자 넣음
      for (Integer lose : player.looser) {
        graph.get(lose).winner.addAll(player.winner);
      }
    }

    for (Info player : graph) {
      if (player.winner.size() + player.looser.size() == n - 1)
        answer++;
    }

    return answer;

  }

  static int solution2(int n, int[][] results) {
    int INF = 987654321;
    int[][] score = new int[n][n];
    int answer = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (i == j)
          score[i][j] = 0;
        else
          score[i][j] = INF;
      }

    }

    for (int[] r : results) {
      int win = r[0] - 1;
      int lose = r[1] - 1;

      // 단방향 그래프 : win -> lose
      score[win][lose] = 1;
    }

    // i -> j로 가는 최단 경로 저장
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          score[i][j] = Math.min(score[i][j], score[i][k] + score[k][j]);
        }
      }
    }

    System.out.println();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.printf("%10d ", score[i][j]);
      }
      System.out.println();
    }
    // 모든 플레이어와 경기를 했다 가정
    boolean[] match = new boolean[n];
    Arrays.fill(match, true);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        // 자기 자신과의 경기 제외
        if (i == j)
          continue;

        // 경기 결과가 초기화 상태 그대로 = 경기 하지 않음
        if (score[i][j] >= INF && score[j][i] >= INF) {
          match[i] = false;
          break;
        }
      }
    }

    // 모든 사람과의 경기 결과를 알 수 있는 참가자들만 카운트
    for (boolean m : match) {
      if (m)
        answer++;
    }
    return answer;
  }
}
