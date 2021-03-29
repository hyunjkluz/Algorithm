package com.baekjoon.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 27
 * @문제 이름 : 세훈이의 선물 가게
 * @문제 링크 : https://www.acmicpc.net/problem/17225
 */
class Process implements Comparable<Process> {
  int start;
  char color;

  public Process(int start, char color) {
    this.start = start;
    this.color = color;
  }

  @Override
  public int compareTo(Process o) {
    // 시작 시간이 같으면 포장지 색깔이 파란색이 먼저 오도록
    if (this.start == o.start) {
      return o.color - this.color;
    }
    return this.start - o.start;
  }
}

public class Main_17225 {
  static int A, B, N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");

    A = Integer.parseInt(info[0]);
    B = Integer.parseInt(info[1]);
    N = Integer.parseInt(info[2]);

    PriorityQueue<Process> process = new PriorityQueue<>();
    int blueNext = 0;
    int redNext = 0;

    for (int i = 0; i < N; i++) {
      info = br.readLine().split(" ");

      int time = Integer.parseInt(info[0]);
      char giftColor = info[1].charAt(0);
      int giftCnt = Integer.parseInt(info[2]);

      for (int j = 0; j < giftCnt; j++) {
        if (giftColor == 'B') {
          if (blueNext >= time) {
            // 선물 포장 포장 중
            process.add(new Process(blueNext, giftColor));
            blueNext += A;
          } else {
            // 다음 포장 시간 < 주문 받은 현재 시간
            // 선물 포장 멈췄다가 다시 시작
            process.add(new Process(time, giftColor));
            blueNext = time + A;
          }
        } else {
          if (redNext >= time) {
            process.add(new Process(redNext, giftColor));
            redNext += B;
          } else {
            process.add(new Process(time, giftColor));
            redNext = time + B;
          }
        }
      }

    }

    System.out.println(warpUp(process));

  }

  static String warpUp(PriorityQueue<Process> process) {
    StringBuilder sb = new StringBuilder();
    ArrayList<Integer> blue = new ArrayList<Integer>();
    ArrayList<Integer> red = new ArrayList<Integer>();
    int gift = 1;

    while (!process.isEmpty()) {
      Process p = process.poll();

      if (p.color == 'B') {
        blue.add(gift);
      } else {
        red.add(gift);
      }

      gift++;
    }

    sb.append(blue.size() + "\n");
    for (Integer b : blue) {
      sb.append(b + " ");
    }

    sb.append("\n" + red.size() + "\n");
    for (Integer r : red) {
      sb.append(r + " ");
    }

    return sb.toString();
  }
}
