package com.programmers.graph;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 04 - 05
 * @문제 이름 : 합승 택시 요금
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/72413
 */
public class SharedTaxi {
  static class Node implements Comparable<Node> {
    //
    int idx, dist;

    public Node(int idx, int dist) {
      this.idx = idx;
      this.dist = dist;
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(this.dist, o.dist);
    }
  }

  static int N, INF;
  static int[][] graph;

  public static void main(String[] args) {
    int[][] fares = new int[][] { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
        { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };
    System.out.println(solution(6, 4, 6, 2, fares));

  }

  static int solution(int n, int s, int a, int b, int[][] fares) {
    N = n;
    INF = 987654321;
    graph = new int[N + 1][N + 1];

    for (int i = 0; i <= N; i++) {
      Arrays.fill(graph[i], INF);
    }

    for (int[] far : fares) {
      int n1 = far[0];
      int n2 = far[1];
      int dist = far[2];

      graph[n1][n2] = dist;
      graph[n2][n1] = dist;
    }

    // 각자 따로 가기 : S -> A, S -> B
    int[] sDist = dijkstra(s);
    int[] aDist = dijkstra(a);
    int[] bDist = dijkstra(b);

    // 합승해서 가기
    int mix = Integer.MAX_VALUE;
    for (int i = 1; i <= N; i++) {
      if (sDist[i] == INF || aDist[i] == INF || bDist[i] == INF)
        continue;

      mix = Math.min(mix, sDist[i] + aDist[i] + bDist[i]);
    }

    return Math.min(sDist[a] + sDist[b], mix);
  }

  public static int[] dijkstra(int from) {
    PriorityQueue<Node> pq = new PriorityQueue<Node>();
    int[] dist = new int[N + 1];
    boolean[] visited = new boolean[N + 1];

    Arrays.fill(dist, INF);

    dist[from] = 0;
    pq.add(new Node(from, dist[from]));

    while (!pq.isEmpty()) {
      int here = pq.peek().idx;
      int cost = pq.peek().dist;
      pq.poll();

      if (cost > dist[here])
        continue;

      for (int i = 1; i <= N; i++) {
        if (graph[here][i] != INF && !visited[i]) {
          if (dist[i] > (dist[here] + graph[here][i])) {
            dist[i] = dist[here] + graph[here][i];
            pq.add(new Node(i, dist[i]));
          }
        }
      }

      visited[here] = true;
    }

    return dist;
  }

}
