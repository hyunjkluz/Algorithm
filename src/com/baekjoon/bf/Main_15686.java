package com.baekjoon.bf;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 26
 * @문제 이름 : 치킨 배달
 * @문제 링크 : https://www.acmicpc.net/problem/15686
 */
public class Main_15686 {
  static int N, M;
  static int[][] map, chickenMap;
  static ArrayList<Point> home, store;
  static ArrayList<Integer> chickenStreet;
  static int minStreet = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] info = br.readLine().split(" ");

    N = Integer.parseInt(info[0]);
    M = Integer.parseInt(info[1]);
    // 도시 지도를 담는 배열
    map = new int[N][N];
    // 집들의 위치를 담는 리스트
    home = new ArrayList<Point>();
    // 치킨집의 위치를 담는 리스트
    store = new ArrayList<Point>();
    // 집과 치킨집의 최종(최소) 치킨 거리를 담는 리스트
    chickenStreet = new ArrayList<Integer>();

    for (int i = 0; i < N; i++) {
      info = br.readLine().split(" ");

      for (int j = 0; j < N; j++) {
        map[i][j] = Integer.parseInt(info[j]);

        if (map[i][j] == 1) {
          home.add(new Point(i, j));
        } else if (map[i][j] == 2) {
          store.add(new Point(i, j));
        }
      }
    }

    // 치킨집 M개를 뽑았을 때 도시의 치킨거리(=각 집의 치킨 거리의 합)가 가장 적게 될 때를 구함
    pickStore(new boolean[store.size()], 0, M);

    System.out.println(minStreet);

  }

  static void pickStore(boolean[] pick, int start, int flag) {
    if (flag == 0) {
      ArrayList<Point> pickedStore = new ArrayList<Point>();

      for (int i = 0; i < pick.length; i++) {
        if (pick[i]) {
          pickedStore.add(store.get(i));
        }
      }

      // 뽑힌 치킨집과 일반집의 치킨 거리 구함
      createChickenMap(pickedStore);

      // 도시의 치킨 거리 = 모든 집의 치킨 거리 합
      int minFlag = 0;
      for (int street : chickenStreet) {
        minFlag += street;
      }

      // 제일 작은 도시 치킨 거리 갱신
      minStreet = Math.min(minStreet, minFlag);

      return;
    }

    for (int i = start; i < store.size(); i++) {
      pick[i] = true;
      pickStore(pick, i + 1, flag - 1);
      pick[i] = false;
    }
  }

  static void createChickenMap(ArrayList<Point> pickedStore) {
    chickenMap = new int[home.size()][pickedStore.size()];
    chickenStreet.clear();

    for (int i = 0; i < home.size(); i++) {
      Point h = home.get(i);
      int min = Integer.MAX_VALUE;

      for (int j = 0; j < pickedStore.size(); j++) {
        Point s = pickedStore.get(j);

        chickenMap[i][j] = (int) Math.abs(h.x - s.x) + (int) Math.abs(h.y - s.y);
        // 집과 치킨집 사이의 거리를 구하면서 그 중 제일 작을 값 구함
        min = Math.min(min, chickenMap[i][j]);
      }

      // 최소값 = 해당 집의 치킨 거리가 됨
      chickenStreet.add(min);
    }
  }
}
