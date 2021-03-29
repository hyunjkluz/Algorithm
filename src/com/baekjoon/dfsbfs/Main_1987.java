package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 08
 * @문제 이름 : 알파벳
 * @문제 링크 : https://www.acmicpc.net/problem/1987
 */
public class Main_1987 {
  static int C, R;
  static char[][] map;
  static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };
  static int maxDepth = -1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    String[] info = br.readLine().split(" ");

    R = Integer.valueOf(info[0]);
    C = Integer.valueOf(info[1]);
    map = new char[R][C];

    for (int i = 0; i < map.length; i++) {
      map[i] = br.readLine().toCharArray();
    }

    // 지나왔던 칸의 알파벳을 담고, 다음 이동 시 중복을 막기 위한 Set 사용
    Set<Character> set = new HashSet<Character>();

    set.add(map[0][0]);

    // 전체 map 탐색
    // 말이 지나는 칸은 좌측 상단의 칸도 포하기 때문에 depth는 1부터 시작
    move(0, 0, set, 1);

    bw.write(maxDepth + "\n");
    bw.flush();
    bw.close();
  }

  static void move(int x, int y, Set<Character> set, int depth) {
    for (int i = 0; i < 4; i++) {
      // 새로 이동할 좌표
      int nx = x + dx[i];
      int ny = y + dy[i];

      // 배열의 범위를 벗어난 좌표이거나
      // 이미 이전에 방문을 했거나, 이미 방문한 좌표의 알파벳과 같은 값이라면
      // = 이동할 수 없음
      if (!isIn(nx, ny) || set.contains(map[nx][ny])) {
        // 최대 이동값 갱신 + 다음 좌표 탐색
        maxDepth = Math.max(maxDepth, depth);
        continue;
      }

      // 위 조건과 만족하지 않으면 = 해당 칸으로 이동할 수 있음
      // 집합에 알파벳 넣기
      set.add(map[nx][ny]);

      // 다음 칸으로 이동
      move(nx, ny, set, depth + 1);

      // 백트레킹
      // 다음 좌표로 이동이 끝났으면
      // 기준 좌표에서 다른 방향으로의 이동을 위해 방문했던 알파벳 집합에서 지우기
      set.remove(map[nx][ny]);
    }

  }

  // 배열의 칸 안에 존재하는 좌표인지 확인하는 함수
  static boolean isIn(int x, int y) {
    if (0 <= x && x < R && 0 <= y && y < C) {
      return true;
    }
    return false;
  }

}
