package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 03
 * @문제 이름 : 부분수열의 합2
 * @문제 링크 : https://www.acmicpc.net/problem/1208
 * @참고 링크 : https://kohen.tistory.com/19
 */
public class Main_1208 {
  static int N, S;
  static int[] num;
  static ArrayList<Integer> leftSum, rightSum;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");
    N = Integer.parseInt(info[0]);
    S = Integer.parseInt(info[1]);
    num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    leftSum = new ArrayList<>();
    rightSum = new ArrayList<>();

    // N의 개수가 40까지 이르게 되면 2^40은 아주 큰 숫자가 되기 때문에 시간초과가 발생
    // 배열을 반으로 나눠 각각 부분합들을 구해준 후 투포인트 알고리즘으로 해결
    // = 2^20의 시간복잡도를 갖는 두개의 배열을 만들어서 풀이
    makeSumList(0, 0, N / 2, leftSum);
    makeSumList(0, N / 2, N, rightSum);

    // 양쪽 리스트를 오름차순으로 정렬
    Collections.sort(leftSum);
    Collections.sort(rightSum);

    System.out.println(findMatchSum());

  }

  // 범위에 해당하는 모든 부분수열의 합을 저장
  static void makeSumList(int sum, int depth, int end, ArrayList<Integer> list) {
    if (depth >= end) {
      list.add(sum);
      return;
    }

    // 현재 값 선택 O
    makeSumList(sum + num[depth], depth + 1, end, list);
    // 현재 값 선택 X
    makeSumList(sum, depth + 1, end, list);
  }

  static long findMatchSum() {
    long answer = 0;
    int lIdx = 0;
    int rIdx = rightSum.size() - 1;

    while (lIdx < leftSum.size() && rIdx >= 0) {
      int lSum = leftSum.get(lIdx);
      int rSum = rightSum.get(rIdx);

      if (lSum + rSum == S) {
        long lSumCnt = 0;
        for (; lIdx < leftSum.size() && leftSum.get(lIdx) == lSum; lIdx++, lSumCnt++)
          ;

        long rSumCnt = 0;
        for (; rIdx >= 0 && rightSum.get(rIdx) == rSum; rIdx--, rSumCnt++)
          ;

        answer += lSumCnt * rSumCnt;
      } else if (lSum + rSum < S) {
        lIdx++;
      } else {
        rIdx--;
      }

    }
    // 합계가 0일때는 아무것도 선택 안했을 경우를 빼줘야함
    return S == 0 ? --answer : answer;
  }
}
