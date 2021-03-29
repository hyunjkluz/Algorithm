package com.programmers.greedy;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 02
 * @문제 이름 : 구명보트
 * @문제 링크 :
 *     https://programmers.co.kr/learn/courses/30/lessons/42885?language=java
 */
public class LifeBoat {
  public static void main(String[] args) {
    System.out.println(solution(new int[] { 70, 50, 80, 50 }, 100));
  }

  static int solution(int[] people, int limit) {
    int answer = 0;
    ArrayList<Integer> list = new ArrayList<Integer>();

    for (int p : people) {
      list.add(p);
    }

    // 몸무게가 가장 많이 나가는 순서대로 정렬
    list.sort(Comparator.reverseOrder());

    int p1 = 0;
    int p2 = list.size() - 1;
    while (p1 < p2) {
      // 최고 + 최저 가 범위 안에 들면 같이 타고 나가기
      if (list.get(p1) + list.get(p2) <= limit) {
        p2--;
      }

      // 범위 안에 들지 않으면 최고 무게 사람만 나가기
      p1++;
      answer++;
    }

    // 계산되지 않은 숫자 남아있으면 태워 보내기
    if (p1 == p2)
      answer++;
    return answer;
  }

}
