package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 10
 * @문제 이름 : 카드 합체 놀이
 * @문제 링크 : https://www.acmicpc.net/problem/15903
 */
public class Main_15903 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] nm = br.readLine().split(" ");
    String[] cards = br.readLine().split(" ");
    PriorityQueue<Long> mixCard = new PriorityQueue<Long>();

    for (String card : cards) {
      mixCard.add(Long.parseLong(card));
    }

    for (int i = 0; i < Integer.parseInt(nm[1]); i++) {
      long mix = mixCard.poll() + mixCard.poll();
      mixCard.add(mix);
      mixCard.add(mix);
    }

    long total = 0;
    while (!mixCard.isEmpty()) {
      total += mixCard.poll();
    }

    System.out.println(total);
  }
}
