package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author : kimhyunjin
 * @CretaedAt : Feb 10, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2212 센서
 */
public class Main_2212 {
  static int N, K;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    K = Integer.parseInt(br.readLine());

    ArrayList<Integer> sensor = new ArrayList<Integer>();

    for (String s : br.readLine().split(" ")) {
      sensor.add(Integer.parseInt(s));
    }

    int minLen = 0;

    // (전처리 작업) 센서 개수 <= 기지국 개수 : 최소 길이 합 = 0
    // 1 집중국 : 1 센서 가능하기 때문
    if (N > K) {
      minLen = buildStation(sensor);
    }

    System.out.println(minLen);
  }

  static int buildStation(ArrayList<Integer> sensor) {
    int minLen = 0;
    // 1. 센서를 오름 차순으로 정렬
    sensor.sort(null);

    ArrayList<Integer> diff = new ArrayList<Integer>();

    // 2. 근처에 있는 센서들 끼리 거리 차이 계산
    for (int i = 0; i < N - 1; i++) {
      diff.add(sensor.get(i + 1) - sensor.get(i));
    }

    // 3. 거리 차이 오름차순 정렬
    diff.sort(null);

    // 차이가 제일 큰 센서는 집중국 하나씩 배정됨
    // 마지막 집중국 하나는 나머지 센서 모두 담당(거리 차이 얼마 안나는 애들끼리 모아서 담당)
    // 거리 차이 리스트 크기에서 집중국 하나 남긴 값 만큼 뺀 순서까지 반복문 돌림
    for (int i = 0; i < diff.size() - (K - 1); i++) {
      minLen += diff.get(i);
    }

    return minLen;
  }
}