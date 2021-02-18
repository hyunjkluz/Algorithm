package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 18
 * @문제 이름 : 그룹 단어 체커
 * @문제 링크 : https://www.acmicpc.net/problem/1316
 */
public class Main_1316 {

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int cnt = 0;

    while (N-- > 0) {
      String word = br.readLine();
      Set<Character> set = new HashSet<Character>();

      boolean isGroupWord = true;
      char pastWord = word.charAt(0);
      set.add(pastWord);

      for (int i = 1; i < word.length(); i++) {
        char nextWord = word.charAt(i);

        if (pastWord != nextWord) {
          if (set.contains(nextWord)) {
            isGroupWord = false;
            break;
          } else {
            set.add(nextWord);
            pastWord = nextWord;
          }
        }
      }

      if (isGroupWord)
        cnt++;
    }

    System.out.println(cnt);
  }

}
