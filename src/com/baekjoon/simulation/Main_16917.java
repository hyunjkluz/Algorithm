package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 11
 * @문제 이름 : 양념 반 후라이드 반
 * @문제 링크 : https://www.acmicpc.net/problem/16917
 */
public class Main_16917 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int[] chicken = new int[] { info[0], info[1], info[2] };
    int X = info[3];
    int Y = info[4];
    int total = 0;
    int min = Math.min(X, Y);

    // 이 방법이 8ms 빠름
    // 한 마리를 만든 방법 중 더 싼 방법을 구해서
    // 최대한 공통적으로 구입할 수 있는 마리 수로 가격 구함
    if (chicken[0] + chicken[1] > chicken[2] * 2) {
      total += (chicken[2] * 2) * min;
    } else {
      total += (chicken[0] + chicken[1]) * min;
    }

    X -= min;
    Y -= min;

    if (X > 0) {
      // 최소 X 마리이기 때문에 더 구입해도 상관없음
      // 이번과 같이 반반으로 만든 한마리랑 그냥 한마리랑 가격 비교하여 계산
      if (chicken[0] > chicken[2] * 2) {
        total += (chicken[2] * 2) * X;
      } else {
        total += chicken[0] * X;
      }
    } else {
      if (chicken[1] > chicken[2] * 2) {
        total += (chicken[2] * 2) * Y;
      } else {
        total += chicken[1] * Y;
      }
    }

    System.out.println(total);
  }

  static void solution2(int[] chicken, int X, int Y) {
    int total = 0;
    // 양념과 후라이드의 개수 차
    int diff = Math.abs(X - Y);
    // 양념과 후라이드의 개수 중 작은 값
    int min = Math.min(X, Y);

    // 개수가 많은 쪽의 남는 개수는 반반으로 채우면 남는 한쪽이 있으므로
    // 어쩔 수 없기 같이 사버리게 된 나머지를 빼주고 다시 계산
    // 생각해보면 두 방법이 똑같은 것 같기도 ...
    // 작은 값보다 차이가 더 크면 뺄 때 음수가 나오기 때문에 그 전에 멈춤
    while (diff >= min) {
      if (chicken[0] + chicken[1] > chicken[2] * 2) {
        total += (chicken[2] * 2) * diff;
      } else {
        total += (chicken[0] + chicken[1]) * diff;
      }

      X -= diff;
      Y -= diff;

      diff = Math.abs(X - Y);
      min = Math.min(X, Y);
    }

    // 위의 방법과 동일
    if (chicken[0] + chicken[1] > chicken[2] * 2) {
      total += (chicken[2] * 2) * min;
    } else {
      total += (chicken[0] + chicken[1]) * min;
    }

    X -= min;
    Y -= min;

    if (X > 0) {
      if (chicken[0] > chicken[2] * 2) {
        total += (chicken[2] * 2) * X;
      } else {
        total += chicken[0] * X;
      }
    } else {
      if (chicken[1] > chicken[2] * 2) {
        total += (chicken[2] * 2) * Y;
      } else {
        total += chicken[1] * Y;
      }
    }

    System.out.println(total);
  }

}
