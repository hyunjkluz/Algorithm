/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 12, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/15662
 */
public class Main_15662 {
	static int T, K;
	static StringBuilder[] gears;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		gears = new StringBuilder[T + 1];
		// 널포인터 방지를 위한 초기화
		gears[0] = new StringBuilder();

		for (int i = 1; i <= T; i++) {
			// 톱니바퀴 입력받음
			gears[i] = new StringBuilder(br.readLine());
		}

		K = Integer.parseInt(br.readLine());
		int[][] op = new int[K][2];
		for (int i = 0; i < K; i++) {
			// 연산 입력받음
			op[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		for (int i = 0; i < K; i++) {
			// 움직일 톱니바퀴와 방향
			int flagGear = op[i][0];
			int flagDir = op[i][1];

			// 각 톱니바퀴의 움직일 방향을 담는 배열
			int[] dir = new int[T + 1];
			dir[flagGear] = flagDir;

			// 각 톱니바퀴에서 움직일 방향 저장
			makeDir(flagGear, flagDir, dir);

			// 만들어진 방향에 따라 톱니바퀴 회전 = 문자열 이동
			spinGear(dir);

		}

		System.out.println(countS());

	}

	public static void makeDir(int flagGear, int flagDir, int[] dir) {
		// 앞 톱니바퀴에서 회전을 멈췄는지 확인하기 위한 변수
		boolean isStop = false;
		// 앞 톱니바퀴에서 회전한 방향을 저장하기위한 변수
		int tempDir = flagDir;

		// 2(왼) - 3(오), 1 - 2 ... 비교
		for (int l = flagGear - 1; l > 0; l--) {
			// 극이 같거나 앞에서 회전이 멈췄으면 회전하지 않음
			if (isStop || gears[l].charAt(2) == gears[l + 1].charAt(6)) {
				dir[l] = 0;
				isStop = true;
			} else {
				tempDir = tempDir == 1 ? -1 : 1;
				dir[l] = tempDir;
			}
		}

		isStop = false;
		tempDir = flagDir;

		// 4(오) - 3(왼), 3 - 2 ... 비교
		for (int r = flagGear + 1; r <= T; r++) {
			if (isStop || gears[r].charAt(6) == gears[r - 1].charAt(2)) {
				dir[r] = 0;
				isStop = true;
			} else {
				tempDir = tempDir == 1 ? -1 : 1;
				dir[r] = tempDir;
			}
		}
	}

	public static void spinGear(int[] dir) {
		for (int d = 1; d <= T; d++) {
			if (dir[d] == 1) {
				gears[d].insert(0, gears[d].charAt(gears[d].length() - 1));
				gears[d].deleteCharAt(gears[d].length() - 1);
				continue;
			}

			if (dir[d] == -1) {
				gears[d].append(gears[d].charAt(0));
				gears[d].deleteCharAt(0);
				continue;
			}
		}
	}

	public static int countS() {
		int cnt = 0;
		for (int i = 1; i <= T; i++) {
			if (gears[i].charAt(0) == '1') {
				cnt += 1;
			}
		}

		return cnt;

	}
}
