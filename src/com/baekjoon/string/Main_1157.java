package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 18
 * @문제 이름 : 단어 공부
 * @문제 링크 : https://www.acmicpc.net/problem/1157
 */
public class Main_1157 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String word = br.readLine();
    Map<Character, Integer> map = new HashMap<Character, Integer>();

    // 대소문자를 구분하지 않기 때문에 바로 하나로 통일
    word = word.toUpperCase();

    for (int i = 0; i < word.length(); i++) {
      map.put(word.charAt(i), map.getOrDefault(word.charAt(i), 0) + 1);
    }

    // 키 값을 기준으로 map 정렬
    Map<Character, Integer> sorted = map.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    Character maxChar = ' ';
    int maxCnt = 0;
    boolean find = false;

    for (Character key : sorted.keySet()) {
      int value = sorted.get(key);

      if (maxCnt == value) {
        // 2. 그 다음 큰 단어랑 value 값이 같으면 가장 많이 사용되는 알파벳 여러개 존재한다는 의미
        maxChar = '?';
        break;
      }
      if (!find) {
        // 1. value 값이 가장 큰 key를 구함
        maxCnt = value;
        maxChar = key;
        find = true;
      } else {
        break;
      }
    }

    System.out.println(maxChar);
  }

}
