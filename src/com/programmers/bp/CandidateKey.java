package com.programmers.bp;

import java.util.HashSet;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 04 - 06
 * @문제 이름 : 후보키
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42890
 */
public class CandidateKey {
  static int rows, columns;
  static String[][] table;
  static HashSet<HashSet<Integer>> candidate;

  public static void main(String[] args) {
    String[][] relation = new String[][] { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
        { "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
        { "600", "apeach", "music", "2" } };

    System.out.println(solution(relation));
  }

  static int solution(String[][] relation) {
    rows = relation.length;
    columns = relation[0].length;
    table = relation;
    candidate = new HashSet<HashSet<Integer>>();

    for (int pick = 1; pick <= columns; pick++) {
      getKeySet(0, pick, new HashSet<Integer>());
    }

    return candidate.size();
  }

  static void getKeySet(int start, int pick, HashSet<Integer> set) {
    if (pick == 0) {

      // 유일성을 만족하지 않으면 후보키 될 수 없음
      if (!isUnique(set))
        return;

      for (HashSet<Integer> part : candidate) {
        HashSet<Integer> temp = new HashSet<>(part);
        temp.removeAll(set);

        if (temp.size() == 0)
          return;
      }

      candidate.add(set);

      return;
    }

    for (int i = start; i < columns; i++) {
      HashSet<Integer> newSet = new HashSet<Integer>(set);
      newSet.add(i);
      getKeySet(i + 1, pick - 1, newSet);
    }
  }

  static boolean isUnique(HashSet<Integer> set) {
    HashSet<String> setResult = new HashSet<String>();

    for (String[] row : table) {
      String value = "";

      for (Integer idx : set) {
        value += row[idx];
      }
      if (!setResult.add(value))
        return false;
    }

    return true;
  }
}
