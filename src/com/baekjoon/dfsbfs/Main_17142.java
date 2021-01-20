/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 19, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/17142
 */
public class Main_17142 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> virus;
	static int[] dx = new int[] { 0, 0, 1, -1 }, dy = new int[] { 1, -1, 0, 0 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");

		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		map = new int[N][N];
		virus = new ArrayList<Point>();

		int zeroSpace = 0;

		// 바이러스가 없고 벽만 촌재하는 초기화된 지도 생성
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					// 바이러스일 경우 바이러스 리스트에 넣음
					// 지도에는 확살될 수 있는 빈칸으로 바꿈
					virus.add(new Point(i, j));
					map[i][j] = 0;
				} else if (map[i][j] == 1) {
					// 시간 증가를 계산하기 위해 벽은 -1로 초기화
					map[i][j] = -1;
				} else {
					// 빈 공간이 있는지 확인
					zeroSpace++;
				}
			}
		}

		// 빈공간 없음 = 바이러스가 이미 다 차있는 상태
		if (zeroSpace == 0) {
			System.out.println(0);
		} else {
			// 바이러스 리스트 중에서 활성 바이러스를 M개 뽑음 : 조합(순서에 신경쓰지 않고 뽑음)
			pickVirus(new boolean[virus.size()], 0, virus.size(), M);
			System.out.println(result == Integer.MAX_VALUE ? -1 : result);
		}

	}

	public static void pickVirus(boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			// 다 뽑았을 때
			// 바이러스틑 퍼트린 지도 가져옴
			int[][] copyMap = spreadVirus(visited);
			// 바이러스가 퍼진 시간을 담고있는 지도에서 최대 시간을 받아옴
			// 받아진 최대 시간 중 최소를 고름
			result = Math.min(result, checkVirus(copyMap));

			return;
		} else {
			for (int i = start; i < n; i++) {
				visited[i] = true;
				pickVirus(visited, i + 1, n, r - 1);
				visited[i] = false;
			}
		}
	}

	public static int[][] spreadVirus(boolean[] visited) {
		Queue<Point> queue = new LinkedList<Point>();
		int[][] copyMap = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < visited.length; i++) {
			Point pick = virus.get(i);

			if (visited[i]) {
				// 뽑힌 바이러스에 대해 지도에 표시 + 작업 큐에 넣음
				copyMap[pick.x][pick.y] = -2;
				queue.add(pick);
			}
		}

		while (!queue.isEmpty()) {
			Point curV = queue.poll();

			for (int i = 0; i < 4; i++) {
				int newX = curV.x + dx[i];
				int newY = curV.y + dy[i];
				int newValue = copyMap[curV.x][curV.y] == -2 ? 1 : copyMap[curV.x][curV.y] + 1;

				// 바이러스가 퍼질 수 있는 공간이면 바이러스 퍼트림
				if (isIn(newX, newY) && copyMap[newX][newY] == 0) {
					copyMap[newX][newY] = newValue;
					queue.add(new Point(newX, newY));
				}
			}
		}

		return copyMap;
	}

	public static int checkVirus(int[][] copyMap) {
		int sec = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 바이러스가 아닌 구역에서
				// 비활성화된 바이러스를 지나갈 순 있지만 비활성화된 바이러스가 있는 칸으로 시간 갱신하면 안됨 : 왜?
				// 바이러스가 존재하지 않는 칸에서만 시간 갱신
				if (!virus.contains(new Point(i, j))) {
					if (copyMap[i][j] == 0) {
						// 만약 빈 공간이 하나라도 있으면 틀림
						// 여기서 -1을 보내면 안됨
						// 최솟값으로 result를 계산하기 때문에 -1을 리턴하면 다른 조합에서 최소값이 나와도 결과값이 갱신되지 않음
						return Integer.MAX_VALUE;
					}
					sec = Math.max(sec, copyMap[i][j]);
				}
			}
		}

		return sec;
	}

	public static boolean isIn(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N) {
			return true;
		}
		return false;
	}

}
