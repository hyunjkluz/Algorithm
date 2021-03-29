package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 06
 * @문제 이름 : 로또
 * @문제 링크 : https://www.acmicpc.net/problem/6603
 */
public class Main_6603 {
  static String[] numbers;
  static StringBuilder answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    answer = new StringBuilder();

    while (true) {
      String[] info = br.readLine().split(" ");
      int k = Integer.valueOf(info[0]);

      if (k == 0) {
        break;
      }

      numbers = Arrays.copyOfRange(info, 1, info.length);

      List<String> pickList = new ArrayList<String>();

      comb(new boolean[numbers.length], 6, 0, pickList);

      for (String s : pickList) {
        answer.append(s);
      }

      answer.append("\n");
    }

    bw.write(answer.toString());
    bw.flush();
    bw.close();
  }

  static void comb(boolean[] pick, int r, int start, List<String> list) {
    if (r == 0) {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < pick.length; i++) {
        if (pick[i]) {
          sb.append(numbers[i] + " ");
        }
      }
      sb.append("\n");

      list.add(sb.toString());
      return;
    }

    for (int i = start; i < numbers.length; i++) {
      pick[i] = true;
      comb(pick, r - 1, i + 1, list);
      pick[i] = false;
    }

  }

}
