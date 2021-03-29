package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 12
 * @문제 이름 : 이차원 배열과 연산
 * @문제 링크 : https://www.acmicpc.net/problem/17140
 */
public class Main_17140 {
  static int r, c, k;
  static int MAX_SIZE = 100, COL_SIZE = 3, ROW_SIZE = 3;
  static int[][] matrix;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    r = Integer.valueOf(info[0]) - 1;
    c = Integer.valueOf(info[1]) - 1;
    k = Integer.valueOf(info[2]);
    matrix = new int[MAX_SIZE][MAX_SIZE];

    for (int i = 0; i < 3; i++) {
      info = br.readLine().split(" ");

      for (int j = 0; j < 3; j++) {
        matrix[i][j] = Integer.valueOf(info[j]);
      }
    }

    bw.write(solution() + "\n");
    bw.flush();
    bw.close();
  }

  static int solution() {
    int time = 0;

    while (matrix[r][c] != k) {
      if (ROW_SIZE >= COL_SIZE) {
        doRCalc();
      } else {
        doCCalc();
      }

      if (++time > 100)
        break;
    }

    return time > 100 ? -1 : time;
  }

  // 모든 행 정렬
  static void doRCalc() {
    int newColSize = COL_SIZE;

    for (int x = 0; x < ROW_SIZE; x++) {
      // 각 수의 등장 횟수를 저장하기 위한 Map 생성
      HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();

      // 배열의 값 == 0 : 빈칸
      // "0이 나올 때 까지" 로 종료 조건을 두면
      // 두번째 행이 첫번째 행보다 길 경우
      // 열 정렬 때 두번재부터 값이 있는 첫번째에서 0을 만나서 종료해버림
      // = 0 값을 제외하는 것은 반복문 안에서
      for (int y = 0; y < COL_SIZE; y++) {
        if (matrix[x][y] == 0)
          continue;

        dict.put(matrix[x][y], dict.getOrDefault(matrix[x][y], 0) + 1);
        // 행의 길이가 작아질 것을 대비하여 모두 0으로 초기화
        matrix[x][y] = 0;
      }

      // Value(등장횟수)를 기준으로 Map 정렬
      List<Entry<Integer, Integer>> dictEntry = mapSortByValue(dict);

      // Map의 크기 = 등장하는 숫자의 개수
      // 크기 * 2 = 정렬된 행의 길이
      // 100이 넘지 않기 위해 정렬된 행의 길이가 100보다 클 경우 방지
      int colSize = dictEntry.size() * 2 > 100 ? 100 : dictEntry.size() * 2;
      for (int i = 0; i < colSize; i++) {
        // 정렬된 순서대로 배열에 값 넣어줌
        Entry<Integer, Integer> value = dictEntry.remove(0);
        matrix[x][i] = value.getKey();
        matrix[x][++i] = value.getValue();
      }

      // 정렬된 행의 길이 중에서 제일 큰 값 고름
      newColSize = Math.max(newColSize, colSize);
    }

    // 열의 최대 길이 갱신
    COL_SIZE = Math.max(ROW_SIZE, newColSize);
  }

  static void doCCalc() {
    int newRowSize = ROW_SIZE;

    for (int y = 0; y < COL_SIZE; y++) {
      // 각 수의 등장 횟수를 저장하기 위한 Map 생성
      HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();

      // 배열의 값 == 0 : 빈칸
      for (int x = 0; x < ROW_SIZE; x++) {
        if (matrix[x][y] == 0)
          continue;

        dict.put(matrix[x][y], dict.getOrDefault(matrix[x][y], 0) + 1);
        matrix[x][y] = 0;
      }

      // Value(등장횟수)를 기준으로 Map 정렬
      List<Entry<Integer, Integer>> dictEntry = mapSortByValue(dict);

      int rowSize = dictEntry.size() * 2 > 100 ? 100 : dictEntry.size() * 2;
      for (int i = 0; i < rowSize; i++) {
        Entry<Integer, Integer> value = dictEntry.remove(0);
        matrix[i][y] = value.getKey();
        matrix[++i][y] = value.getValue();
      }

      // 정렬된 열의 길이 중에서 제일 큰 값 고름
      newRowSize = Math.max(newRowSize, rowSize);
    }

    // 열의 길이 = 행의 사이즈
    // 행의 최대 길이 갱신
    ROW_SIZE = Math.max(ROW_SIZE, newRowSize);
  }

  static List<Entry<Integer, Integer>> mapSortByValue(HashMap<Integer, Integer> dict) {
    List<Entry<Integer, Integer>> dictEntry = new ArrayList<Entry<Integer, Integer>>(dict.entrySet());

    Collections.sort(dictEntry, new Comparator<Entry<Integer, Integer>>() {

      @Override
      public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
        // 수의 등장 횟수가 같으면
        if (Integer.compare(o1.getValue(), o2.getValue()) == 0) {
          // 수 기준 오름차순
          return Integer.compare(o1.getKey(), o2.getKey());
        }
        // 수의 등장횟수 오름차순
        return Integer.compare(o1.getValue(), o2.getValue());
      }

    });

    return dictEntry;
  }

}
