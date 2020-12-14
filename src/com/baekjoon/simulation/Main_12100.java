/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 7, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/12100
 */
class MapInfo {
	int count;
	int[][] map;

	public MapInfo(int[][] map) {
		this.count = 0;
		this.map = map;
	}

	public MapInfo(int count, int[][] map) {
		this.count = count;
		this.map = map;
	}
}

public class Main_12100 {
	static int maxBlock = 0;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		MapInfo first = new MapInfo(map);
		solution(first);

		System.out.println(maxBlock);

	}

	public static void solution(MapInfo mapInfo) {
		Queue<MapInfo> queue = new LinkedList<MapInfo>();
		queue.add(mapInfo);

		while (!queue.isEmpty()) {
			MapInfo mi = queue.poll();

			if (mi.count == 5) {
				calcMax(mi.map);
				continue;
			}

			int newCount = mi.count + 1;

			queue.add(new MapInfo(newCount, up(mi.map)));
			queue.add(new MapInfo(newCount, down(mi.map)));
			queue.add(new MapInfo(newCount, left(mi.map)));
			queue.add(new MapInfo(newCount, right(mi.map)));
		}

	}

	public static int[][] up(int[][] map) {
		// 이동 후 새로운 맵을 저장하기 위해 = 이전의 맵에 변화 없도록 깊은 복사하기
		int[][] newMap = deepCopy(map);

		// 빈 공간 없도록 움직이고
		move(0, newMap);

		// 근처에 있는 수 더하고
		// 위에서 아래로 탐색
		for (int y = 0; y < N; y++) {
			int prev = 0;
			for (int x = 0; x < N; x++) {
				// 값이 0이거나, 이전의 값과는 다를 때 : 이전 값 갱신
				if (prev == 0 || (prev != 0 && prev != newMap[x][y]))
					prev = newMap[x][y];
				else if (prev == newMap[x][y]) {
					newMap[x][y] += prev;
					// 빈 공간이 없기 때문에 이전 인덱스에서 왔다는거 확실 -> 이전 위지 0으로 만들어줌
					newMap[x - 1][y] = 0;
					prev = 0;
				}
			}
		}

		// 다시 빈 공간 없애기
		move(0, newMap);

		return newMap;
	}

	public static int[][] down(int[][] map) {
		int[][] newMap = deepCopy(map);

		move(1, newMap);

		for (int y = N - 1; y >= 0; y--) {
			int prev = 0;
			for (int x = N - 1; x >= 0; x--) {
				if (prev == 0 || (prev != 0 && prev != newMap[x][y]))
					prev = newMap[x][y];
				else if (prev == newMap[x][y]) {
					newMap[x][y] += prev;
					newMap[x + 1][y] = 0;
					prev = 0;
				}
			}
		}

		move(1, newMap);

		return newMap;
	}

	public static int[][] left(int[][] map) {
		int[][] newMap = deepCopy(map);

		move(2, newMap);

		for (int x = 0; x < N; x++) {
			int prev = 0;
			for (int y = 0; y < N; y++) {
				if (prev == 0 || (prev != 0 && prev != newMap[x][y]))
					prev = newMap[x][y];
				else if (prev == newMap[x][y]) {
					newMap[x][y] += prev;
					newMap[x][y - 1] = 0;
					prev = 0;
				}
			}
		}

		move(2, newMap);

		return newMap;
	}

	public static int[][] right(int[][] map) {
		int[][] newMap = deepCopy(map);
		move(3, newMap);

		for (int x = N - 1; x >= 0; x--) {
			int prev = 0;
			for (int y = N - 1; y >= 0; y--) {
				if (prev == 0 || (prev != 0 && prev != newMap[x][y]))
					prev = newMap[x][y];
				else if (prev == newMap[x][y]) {
					newMap[x][y] += prev;
					newMap[x][y + 1] = 0;
					prev = 0;
				}
			}
		}

		move(3, newMap);

		return newMap;
	}

	public static void move(int dir, int[][] map) {
		// 빈공간의 인덱스를 담아두는 큐
		Queue<Integer> zeroSpace = new LinkedList<Integer>();

		switch (dir) {
		case 0:
			// 위
			for (int y = 0; y < N; y++) {
				zeroSpace.clear();

				for (int x = 0; x < N; x++) {
					if (map[x][y] == 0) {
						// 빈 공간이면 인덱스를 큐에 담음
						zeroSpace.add(x);
					} else if (map[x][y] != 0 && !zeroSpace.isEmpty()) {
						// 해당 칸이 빈 공간이 아니고 + 이전에 빈 공간이 있으면
						int zeroX = zeroSpace.poll();

						// 빈 공간에 값 넣어주고
						map[zeroX][y] = map[x][y];
						// 해당 칸 빈 공간으로 셋팅
						map[x][y] = 0;
						// 빈 공간 큐에 넣어주기
						zeroSpace.add(x);
					}
				}
			}

			break;

		case 1:
			// 아래
			for (int y = 0; y < N; y++) {
				zeroSpace.clear();

				for (int x = N - 1; x >= 0; x--) {
					if (map[x][y] == 0) {
						zeroSpace.add(x);
					} else if (map[x][y] != 0 && !zeroSpace.isEmpty()) {
						int zeroX = zeroSpace.poll();

						map[zeroX][y] = map[x][y];
						map[x][y] = 0;
						zeroSpace.add(x);
					}
				}
			}

			break;
		case 2:
			// 왼쪽
			for (int x = 0; x < N; x++) {
				zeroSpace.clear();

				for (int y = 0; y < N; y++) {
					if (map[x][y] == 0) {
						zeroSpace.add(y);
					} else if (map[x][y] != 0 && !zeroSpace.isEmpty()) {
						int zeroY = zeroSpace.poll();

						map[x][zeroY] = map[x][y];
						map[x][y] = 0;
						zeroSpace.add(y);
					}
				}
			}

			break;
		case 3:
			// 오른쪽
			for (int x = 0; x < N; x++) {
				zeroSpace.clear();

				for (int y = N - 1; y >= 0; y--) {
					if (map[x][y] == 0) {
						zeroSpace.add(y);
					} else if (map[x][y] != 0 && !zeroSpace.isEmpty()) {
						int zeroY = zeroSpace.poll();

						map[x][zeroY] = map[x][y];
						map[x][y] = 0;
						zeroSpace.add(y);
					}
				}
			}

			break;
		}

		return;
	}

	public static void calcMax(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				maxBlock = Math.max(maxBlock, map[i][j]);
			}
		}

	}

	public static int[][] deepCopy(int[][] map) {
		int[][] newMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, N);
		}

		return newMap;
	}

	public static void print(int[][] map) {
		for (int i = 0; i < N; i++) {

			System.out.println(Arrays.toString(map[i]));
		}

		System.out.println();
	}

}
