package com.programmers.bp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 29
 * @문제 이름 : 메뉴 리뉴얼
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/72411
 */
public class MenuRenewal {

  static HashMap<String, Integer> courseComb;

  static HashMap<Integer, Integer> lenFrequency;

  public static void main(String[] args) {
    String[] orders = new String[] { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
    int[] courses = new int[] { 2, 3, 4 };

    System.out.println(Arrays.toString(solution(orders, courses)));

    orders = new String[] { "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD" };
    courses = new int[] { 2, 3, 5 };
    System.out.println(Arrays.toString(solution(orders, courses)));

    orders = new String[] { "XYZ", "XWY", "WXA" };
    courses = new int[] { 2, 3, 4 };
    System.out.println(Arrays.toString(solution(orders, courses)));

  }

  static String[] solution(String[] orders, int[] course) {
    // 정답 문자열 담을 리스트
    ArrayList<String> answer = new ArrayList<String>();
    // <단품메뉴 조합으로 얻은 코스요리 메뉴, 해당 메뉴가 나온 빈도수>를 담은 map
    courseComb = new HashMap<String, Integer>();
    // <코스요리 메뉴구성 문자열 길이, 해당 길이를 가진 메뉴의 빈도수 중 제일 큰 값>을 담은 map
    lenFrequency = new HashMap<Integer, Integer>();

    // 구해야하는 메뉴구성 길이 map에 초기화
    for (int c : course) {
      lenFrequency.put(c, 0);
    }

    // 각 손님이 주문한 단품메뉴들로
    for (String order : orders) {
      // 코스요리 구성하는 메뉴 개수만큼 조합을 구함
      for (int courseLength : course) {
        // 단품메뉴의 개수(길이)보다 구성해야하는 코스요리의 개수(길이)가 더 길면 제외
        if (order.length() >= courseLength) {
          comb(new boolean[order.length()], 0, courseLength, order);
        }
      }
    }

    // 구해진 단품메뉴의 모든 조합을 비교
    for (String newCourse : courseComb.keySet()) {
      int length = newCourse.length();

      // 해당 메뉴구성의 빈도수가 2 이상
      // && 같은 문자열의 길이를 가진 메뉴들 중 빈도수가 가장 높은 메뉴구성이 일때
      if (lenFrequency.get(length) >= 2 && lenFrequency.get(length) == courseComb.get(newCourse)) {
        answer.add(newCourse);
      }
    }

    // 메뉴 구성 문자열 오름자순 정렬
    answer.sort(null);
    return answer.toArray(new String[answer.size()]);
  }

  static void comb(boolean[] visited, int start, int pick, String order) {
    if (pick == 0) {
      StringBuilder newMenu = new StringBuilder();

      // 조합으로 뽑힌 메뉴들로 코스요리 메뉴(문자열) 구함
      for (int i = 0; i < visited.length; i++) {
        if (visited[i])
          newMenu.append(order.charAt(i));
      }

      // "AB"나 "BA"는 같은 코스요리이기 때문에
      // 뽑힌 코스요리 메뉴는 모두 오름차순 정렬해서 비교
      String sortedNewMenu = sortedString(newMenu.toString());

      // 전체 조합 Map에 뽑힌 코스요리 메뉴의 빈도수 갱신
      courseComb.put(sortedNewMenu, courseComb.getOrDefault(sortedNewMenu, 0) + 1);

      // 해당 코스요리 메뉴(조합)가 몇 번 나왔는지
      int frequency = courseComb.get(sortedNewMenu);
      // 코스요리 메뉴(조합)의 길이
      int length = sortedNewMenu.length();

      // 해당 길이(단품메뉴의 개수)의 최대값 알기 위해
      // 코스요리 메뉴 길이의 빈도수 갱신
      lenFrequency.put(length, Math.max(frequency, lenFrequency.get(length)));

      return;
    }

    // 조합
    for (int i = start; i < order.length(); i++) {
      visited[i] = true;
      comb(visited, i + 1, pick - 1, order);
      visited[i] = false;
    }

  }

  // 문자열을 오름차순 정렬한 값을 리턴
  static String sortedString(String s) {
    char[] stringChar = s.toCharArray();

    Arrays.sort(stringChar);

    return new String(stringChar);
  }

}
