package com.programmers.simulation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 01
 * @문제 이름 : 기둥과 보
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/60061
 */
public class PillarAndBeam {
  static boolean[][] pillar, beam;

  public static void main(String[] args) {
    int n = 5;
    int[][] build_frame = new int[][] { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2, 2, 1, 1 }, { 5, 0, 0, 1 },
        { 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };

    System.out.println(Arrays.toString(solution(n, build_frame)));
  }

  static int[][] solution(int n, int[][] build_frame) {
    ArrayList<int[]> answerList = new ArrayList<>();
    int[][] answer = {};
    // 기둥 설치 지도
    pillar = new boolean[n + 1][n + 1];
    // 보 설치 지도
    beam = new boolean[n + 1][n + 1];

    for (int i = 0; i < build_frame.length; i++) {
      int x = build_frame[i][1];
      int y = build_frame[i][0];
      boolean isPillar = build_frame[i][2] == 0 ? true : false;
      boolean isInstall = build_frame[i][3] == 1 ? true : false;

      // 기둥
      if (isPillar) {
        if (isInstall) {
          // - 설치
          pillar[x][y] = installPillar(x, y);
        } else {
          // - 삭제
          pillar[x][y] = false;
          // 존재하고 + 설치할 수 없으면 삭제 못함
          if ((isPillar(x + 1, y) && !installPillar(x + 1, y)) // 위에 기둥 검사
              || (isBeam(x + 1, y) && !installBeam(x + 1, y)) // 바로 위의 오른쪽 보 검사
              || (isBeam(x + 1, y - 1) && !installBeam(x + 1, y - 1))) { // 바로 위 왼쪽 보 검사
            pillar[x][y] = true;
          }
        }
      }
      // 보
      else {
        if (isInstall) {
          // - 설치
          beam[x][y] = installBeam(x, y);
        } else {
          // - 삭제
          beam[x][y] = false;
          if ((isPillar(x, y) && !installPillar(x, y)) // 보 왼쪽 기둥
              || (isPillar(x, y + 1) && !installPillar(x, y + 1)) // 보 오른쪽 기둥
              || (isBeam(x, y + 1) && !installBeam(x, y + 1)) // 오른쪽 보
              || (isBeam(x, y - 1) && !installBeam(x, y - 1))) { // 왼쪽 보
            beam[x][y] = true;
          }
        }
      }
    }

    for (int i = 0; i < pillar.length; i++) {
      for (int j = 0; j < beam.length; j++) {
        if (pillar[i][j]) {
          answerList.add(new int[] { j, i, 0 });
        }
        if (beam[i][j]) {
          answerList.add(new int[] { j, i, 1 });
        }
      }
    }

    answer = new int[answerList.size()][3];
    for (int i = 0; i < answer.length; i++) {
      int[] list = answerList.get(i);
      answer[i][0] = list[0];
      answer[i][1] = list[1];
      answer[i][2] = list[2];

    }

    Arrays.sort(answer, (o1, o2) -> {
      if (o1[0] == o2[0]) {
        if (o1[1] == o2[1]) {
          return o1[2] - o2[2];
        } else {
          return o1[1] - o2[1];
        }
      } else {
        return o1[0] - o2[0];
      }
    });

    return answer;
  }

  // 해당 좌표에 기둥이 있는지
  static boolean isPillar(int x, int y) {
    if (0 <= x && x <= pillar.length && 0 <= y && y <= pillar.length) {
      return pillar[x][y];
    }
    return false;
  }

  // 해당 좌표에 보가 있는지
  static boolean isBeam(int x, int y) {
    if (0 <= x && x <= beam.length && 0 <= y && y <= beam.length) {
      return beam[x][y];
    }
    return false;
  }

  // x, y 위치에 설치(존재)할 수 있는지
  static boolean installPillar(int x, int y) {
    // 바닥 위 || 바로 밑에 기둥이 있는지 || 아래 왼쪽에 보가 있는지 || 아래 오른쪽에 보가 있는지
    if (x == 0 || isPillar(x - 1, y) || isBeam(x, y - 1) || isBeam(x, y)) {
      return true;
    }
    return false;
  }

  static boolean installBeam(int x, int y) {
    // 아래 왼쪽에 기둥이 있는지 || 아래 오른쪽에 기둥이 있는지 || 양 옆으로 보가 있는지
    if (isPillar(x - 1, y) || isPillar(x - 1, y + 1) || (isBeam(x, y - 1) && isBeam(x, y + 1))) {
      return true;
    }
    return false;

  }
}
