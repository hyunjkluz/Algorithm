package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 18
 * @문제 이름 : 잃어버린 괄호
 * @문제 링크 : https://www.acmicpc.net/problem/1541
 */
public class Main_1541 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String exp = br.readLine();
    ArrayList<Integer> minusIdx = new ArrayList<Integer>();

    // - 연산자가 위치하는 인덱스 모음
    for (int i = 0; i < exp.length(); i++) {
      if (exp.charAt(i) == '-') {
        minusIdx.add(i);
      }
    }

    int result = 0;

    if (minusIdx.size() > 0) {
      // 제일 작은 수가 나오게 하려면 최대한 많이 빼면 됨
      // - 연산자 시작 지점부터 다음 - 연산자가 나오기 전까지의 수를 더해서 뺌
      result = calcPlus(exp.substring(0, minusIdx.get(0)));

      for (int i = 0; i < minusIdx.size(); i++) {
        int start = minusIdx.get(i);
        int end = i + 1 == minusIdx.size() ? exp.length() : minusIdx.get(i + 1);

        // + 연산자로만 구성된 부분 문자열 결과 값 계산 후 빼줌
        result -= calcPlus(exp.substring(start + 1, end));
      }
    } else {
      // - 연산자가 없으면 그냥 식 전체 계산
      result = calcPlus(exp);
    }

    System.out.println(result);

  }

  static int calcPlus(String subExp) {
    int result = 0;
    StringBuilder sb = new StringBuilder();

    // 계산하는 종료 조건에 걸리기 위해 마지막에 + 연산자를 더함
    subExp += "+";

    for (int i = 0; i < subExp.length(); i++) {
      if (subExp.charAt(i) != '+') {
        sb.append(subExp.charAt(i));
      } else {
        result += Integer.parseInt(sb.toString());
        sb = new StringBuilder();
      }
    }

    return result;
  }

}
