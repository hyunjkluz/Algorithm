package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 19
 * @문제 이름 : 괄호 추가하기
 * @문제 링크 : https://www.acmicpc.net/problem/16637
 * @참고 링크 : https://steady-coding.tistory.com/36
 */
public class Main_16637 {
  static int N, MAX = Integer.MIN_VALUE;
  static ArrayList<Integer> nums;
  static ArrayList<Character> ops;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    String exp = br.readLine();

    nums = new ArrayList<Integer>();
    ops = new ArrayList<Character>();

    for (int i = 0; i < exp.length(); i++) {
      if (i % 2 == 0) {
        nums.add(Character.getNumericValue(exp.charAt(i)));
      } else {
        ops.add(exp.charAt(i));
      }
    }

    addBracket(nums.get(0), 0);

    System.out.println(MAX);

  }

  // DFS
  static void addBracket(int subExpResult, int opIdx) {
    // 주어진 연산자의 개수를 초과하였을 경우.
    if (opIdx >= ops.size()) {
      MAX = Math.max(MAX, subExpResult);
      return;
    }

    // 괄호가 없는 경우
    int res1 = calcExp(ops.get(opIdx), subExpResult, nums.get(opIdx + 1));
    addBracket(res1, opIdx + 1);

    // 뒤에서부터 괄호를 생성함

    // 괄호가 있는 경우
    // 마지막 연산만 남겨놨을 경우 괄호를 만들 수 없기 때문에
    // 남은 연산이 2개 이상일 때 부터 괄호 만들 수 있음
    if (opIdx + 1 < ops.size()) {
      // 괄호 칠 연산자 기준으로 양 옆의 수를 더함
      // = 괄호 친거 계산
      int res2 = calcExp(ops.get(opIdx + 1), nums.get(opIdx + 1), nums.get(opIdx + 2));

      // 현재 result와 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘김.
      addBracket(calcExp(ops.get(opIdx), subExpResult, res2), opIdx + 2);
    }
  }

  static int calcExp(char op, int n1, int n2) {
    int result = 0;

    switch (op) {
      case '+':
        result = n1 + n2;
        break;
      case '-':
        result = n1 - n2;
        break;
      case '*':
        result = n1 * n2;
        break;

    }

    return result;
  }

}
