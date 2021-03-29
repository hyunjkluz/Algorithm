package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 22
 * @문제 이름 : 게리맨더링
 * @문제 링크 : https://www.acmicpc.net/problem/17471
 */
public class Main_17471 {
  static int N, minDiff;
  static int[] population;
  static int[][] map;
  static Set<Integer> nodes;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    // 총 지역 수
    N = Integer.valueOf(br.readLine());
    // 지역의 인구 수 배열
    population = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    // 연결되어있는 지역을 표현하기위한 배열
    map = new int[N][N];
    // 전체 지역 번호 잡합
    nodes = new HashSet<Integer>();
    // 나눠진 선거구의 인구 차이 최소값을 담기 위한 변수
    minDiff = Integer.MAX_VALUE;

    for (int i = 0; i < N; i++) {
      nodes.add(i);
      String[] info = br.readLine().split(" ");

      // 양방향 그래프 2차차원 배열로 표시
      if (Integer.valueOf(info[0]) > 0) {
        for (int j = 1; j < info.length; j++) {
          int dest = Integer.valueOf(info[j]) - 1;
          map[i][dest] = 1;
          map[dest][i] = 1;
        }
      }
    }

    // 선거구 내 지역이 1개 ~ (N+1)/2개 일 때의 지역 조합 구함
    for (int pick = 1; pick <= (N + 1) / 2; pick++) {
      solution(pick, 0, new boolean[N]);
    }

    minDiff = minDiff == Integer.MAX_VALUE ? -1 : minDiff;
    bw.write(minDiff + "\n");
    bw.flush();
    bw.close();
  }

  static void solution(int pick, int start, boolean[] visited) {
    if (pick == 0) {
      // 지역구 A 모음
      Set<Integer> A = new HashSet<Integer>();

      for (int i = 0; i < N; i++) {
        if (visited[i])
          A.add(i);
      }

      // 지역구 B 모음
      Set<Integer> B = new HashSet<Integer>(nodes);
      B.removeAll(A);

      // 지역구 A가 서로 이어져있는지 확인
      int aValue = isConnected(A, B);
      if (aValue == -1)
        return;

      // 지역구 B가 이어져있는지 확인
      int bValue = isConnected(B, A);
      if (bValue == -1)
        return;

      // 두 선거구의 인구차를 최소 인구차와 비교하여 갱신
      minDiff = Math.min(minDiff, Math.abs(aValue - bValue));

      return;
    }

    for (int i = start; i < N; i++) {
      visited[i] = true;
      solution(pick - 1, i + 1, visited);
      visited[i] = false;
    }

  }

  static int isConnected(Set<Integer> set, Set<Integer> diffSet) {
    // 연결된 구역의 총 인구를 담는 변수
    int connectValue = 0;
    // 이미 방문한 구역인지 확인하기위한 배열
    boolean[] visited = new boolean[N];

    // 연결된 선거구 = 임의의 한 점에서 BFS하면 모든 구역을 방문할 수 있어야 함
    // BFS 후, 방문 못한 구역이 있으면 연결되어있지 않다는 의미
    Iterator<Integer> iter = set.iterator();
    int start = iter.next();

    Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int current = queue.poll();

      for (int i = 0; i < N; i++) {
        // 서로 연결된 구역이고, 방문한 적이 없고, 다른 선거구에 포함되어있지 않는 구역이라면 탐색
        if (map[current][i] == 1 && !visited[i] && !diffSet.contains(i)) {
          queue.add(i);
          visited[i] = true;
        }
      }
    }

    // 임의의 한 지역을 기준으로 방문 탐색하였을 때
    // 선거구에 포함된 지역이 방문되어있지 않으면 연결 X
    for (Integer n : set) {
      if (!visited[n])
        return -1;
      connectValue += population[n];
    }

    // 모두 방문되었으면 제대로된 선거구의 인구수 리턴
    return connectValue;
  }
}
