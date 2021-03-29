package com.programmers.greedy;

import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 02
 * @문제 이름 : 최솟값 만들기
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/12941
 */
public class MakeMinValue {
  public static void main(String[] args) {
    System.out.println(solution(new int[] { 1, 2, 4 }, new int[] { 5, 4, 4 }));
  }

  static int solution(int[] A, int[] B) {
    int answer = 0;

    Arrays.sort(A);
    Arrays.sort(B);

    for (int i = 0; i < A.length; i++) {
      answer += A[i] * B[B.length - 1 - i];
    }

    return answer;
  }

}
