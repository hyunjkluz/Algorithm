package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 20
 * @문제 이름 : 스타트링크
 * @문제 링크 : https://www.acmicpc.net/problem/5014
 */
public class Main_5014 {
  static int F, S, G;
  static int[] button;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    F = Integer.valueOf(info[0]);
    S = Integer.valueOf(info[1]) - 1;
    G = Integer.valueOf(info[2]) - 1;
    // [ U버튼 눌렀을 때 이동할 칸의 개수, D버튼 눌렀을 대 이동할 칸의 개수] 배열
    button = new int[] { Integer.valueOf(info[3]), Integer.valueOf(info[4]) * -1 };

    int pushCount = -1;
    // 만약 강호가 있는 층이 스타트링크가 있는 층이면 버튼 누를 필요 없음
    if (S == G) {
      pushCount = 0;
    } else {
      pushCount = BFS();
    }

    bw.write(pushCount != -1 ? pushCount + " \n" : "use the stairs\n");
    bw.flush();
    bw.close();
  }

  static int BFS() {
    boolean[] stairs = new boolean[F];
    Queue<int[]> queue = new LinkedList<int[]>();

    stairs[S] = true;
    queue.add(new int[] { S, 0 });

    int pushCount = -1;

    while (!queue.isEmpty()) {
      int[] place = queue.poll();
      int stair = place[0];
      int count = place[1];

      // 누를 수 있는 버튼의 수 만큼 반복
      for (int i = 0; i < button.length; i++) {
        int newStair = stair + button[i];
        int newCount = count + 1;

        // 건물의 범위 밖인 층이거나 이미 방문한 층이면 스킵
        if (newStair < 0 || F <= newStair || stairs[newStair])
          continue;

        // 목적지 찾으면 탈출
        if (newStair == G) {
          pushCount = newCount;
          break;
        }

        // 새로 탐색할 층 더해주기
        stairs[newStair] = true;
        queue.add(new int[] { newStair, newCount });
      }
    }

    return pushCount;
  }

}
