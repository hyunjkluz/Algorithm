package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 01
 * @문제 이름 : LCS
 * @문제 링크 : https://www.acmicpc.net/problem/9251
 * @참고 자료 :
 *     https://ko.wikipedia.org/wiki/%EC%B5%9C%EC%9E%A5_%EA%B3%B5%ED%86%B5_%EB%B6%80%EB%B6%84_%EC%88%98%EC%97%B4
 */
public class Main_9251 {
  static String C, R;
  static Integer[][] lcs;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    C = br.readLine();
    R = br.readLine();

    int n = C.length();
    int m = R.length();

    lcs = new Integer[n][m];

    // LCS 알고리즘의 점화식을 그대로 구현
    System.out.println(makeLCS(n - 1, m - 1));
  }

  static int makeLCS(int i, int j) {
    // 인덱스 범위를 벗어나면 0
    if (i < 0 || j < 0) {
      return 0;
    }

    // 탐색하지 않은 인덱스일 때
    // (체크 안해주면 시간초과 남)
    if (lcs[i][j] == null) {
      lcs[i][j] = 0;

      if (C.charAt(i) == R.charAt(j)) {
        // 같은 문자로 끝나면 그 문자 제외하고 부분 수열 구함
        // 구한 부분 수열에 현재 비교한 문자(같은 문자) 더하면 긴 부분 수열 만들어지기 때문
        lcs[i][j] = makeLCS(i - 1, j - 1) + 1;
      } else {
        // 다른 문자로 끝나면 상대방 문자 길이를 하나씩 줄인 값과 비교해서 더 긴 값을 구함
        // 만약 구해진 수열의 길이가 같지만 (수열의 내용)값이 같지 않다면 둘 다 구해짐
        lcs[i][j] = Math.max(makeLCS(i, j - 1), makeLCS(i - 1, j));
      }

    }
    return lcs[i][j];
  }

}
