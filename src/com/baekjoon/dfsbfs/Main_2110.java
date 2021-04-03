package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 04 - 02
 * @문제 이름 : 공유기 설치
 * @문제 링크 : https://www.acmicpc.net/problem/2110
 */
public class Main_2110 {
  static int N, C;
  static int[] home;
  static int answer = -1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    N = Integer.valueOf(info[0]);
    C = Integer.valueOf(info[1]);
    home = new int[N];

    for (int i = 0; i < N; i++) {
      home[i] = Integer.valueOf(br.readLine());
    }

    Arrays.sort(home);

    binarySearch(1, home[N - 1] - home[0]);

    bw.write(answer + "\n");
    bw.flush();
    bw.close();
  }

  static void binarySearch(int start, int end) {
    if (start > end)
      return;

    int mid = (start + end) / 2;
    int count = install(mid);

    if (count >= C) {
      answer = Math.max(answer, mid);
      binarySearch(mid + 1, end);
    } else if (count < C) {
      // 기준보다 더 적게 설치 = 간격이 너무 큼 -> 간격줄이기
      binarySearch(start, mid - 1);
    }
  }

  static int install(int interval) {
    int flagHome = home[0];
    int count = 1;

    for (int i = 1; i < N; i++) {
      if (flagHome + interval <= home[i]) {
        count++;
        flagHome = home[i];
      }
    }

    return count;
  }

}
