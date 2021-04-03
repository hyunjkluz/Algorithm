package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_8980 {
  static class Delivery implements Comparable<Delivery> {
    int to, from, count;

    public Delivery(int to, int from, int count) {
      this.to = to;
      this.from = from;
      this.count = count;
    }

    @Override
    public int compareTo(Delivery arg0) {
      if (to == arg0.to) {
        return from - arg0.from;
      }
      return to - arg0.to;
    }
  }

  static List<Delivery> village;
  static int N, C, M;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    N = Integer.valueOf(info[0]);
    C = Integer.valueOf(info[1]);
    M = Integer.valueOf(br.readLine());
    village = new ArrayList<Delivery>();

    for (int i = 0; i < M; i++) {
      info = br.readLine().split(" ");

      int from = Integer.valueOf(info[0]);
      int to = Integer.valueOf(info[1]);
      int count = Integer.valueOf(info[2]);

      village.add(new Delivery(to, from, count));
    }

    // 시작점 기준으로 오른차순 하면
    // 첫 마을 -> 끝 마을 : 최대용량이면 아무것도 나머지 못담음
    // 끝 마을 기준 오름차순, 시작 마을 기준 오름차순
    // 두 마을이 가까이 있어야 빠르게 주고 내릴 수 있음
    Collections.sort(village);

    bw.write(solution() + "\n");
    bw.flush();
    bw.close();
  }

  static int solution() {
    int total = 0;
    int[] truck = new int[N + 1];

    Arrays.fill(truck, C);

    for (Delivery info : village) {
      int maxCarry = Integer.MAX_VALUE;

      // from과 to 사이 남은 용량 중 가장 작은거 선택
      // 큰 거 고르면 제일 용량 안남은 시점의 트럭이 초과되기 때문
      for (int i = info.from; i < info.to; i++) {
        maxCarry = Math.min(maxCarry, truck[i]);
      }

      // 용량 넉넉하면 : 넣으려했던 만큼 다 넣을 수 있으면
      if (maxCarry >= info.count)
        maxCarry = info.count;

      for (int i = info.from; i < info.to; i++) {
        truck[i] -= maxCarry;
      }

      total += maxCarry;
    }

    return total;
  }

}
