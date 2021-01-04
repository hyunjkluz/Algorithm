/**
 * 
 */
package com.baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 4, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/5373
 */
public class Main_5373 {
	// 저번에 풀었던것 처럼 전개도 배열가지고 풀면 안됨
	// 한쪽 면을 돌렸을 때, 접한 부분의 색깔도 회전할 뿐만 아니라 해당 면 자체의 색깔 배치도 돌아가야함
	// 6개의 면, 각각 3*3 배열을 가지고 있음
	public static char[][][] cube = new char[6][3][3];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < T; i++) {
			clearCube();
			int n = Integer.parseInt(br.readLine());
			String[] consoleLine = br.readLine().split(" ");

			for (String console : consoleLine) {
				simulation(console);
			}

			answer.append(getTopSide());
		}

		System.out.println(answer.toString());

	}

	public static char[][] turnTargetSide(char[][] side, char d) {
		char[][] newSide = new char[3][3];
		newSide[1][1] = side[1][1];
		if (d == '+') {
			newSide[0][0] = side[2][0];
			newSide[0][1] = side[1][0];
			newSide[0][2] = side[0][0];
			newSide[1][2] = side[0][1];
			newSide[2][2] = side[0][2];
			newSide[2][1] = side[1][2];
			newSide[2][0] = side[2][2];
			newSide[1][0] = side[2][1];
			
//			for(int i = 0; i < 3; i++) {
//				for(int j = 0; j < 3; j++) {
//					newSide[j][2-i] = side[i][j];
//				}
//			}
		} else {
			newSide[0][0] = side[0][2];
			newSide[0][1] = side[1][2];
			newSide[0][2] = side[2][2];
			newSide[1][2] = side[2][1];
			newSide[2][2] = side[2][0];
			newSide[2][1] = side[1][0];
			newSide[2][0] = side[0][0];
			newSide[1][0] = side[0][1];
			
//			for(int i = 0; i < 3; i++) {
//				for(int j = 0; j < 3; j++) {
//					newSide[2-j][i] = side[i][j];
//				}
//			}
		}
		return newSide;
	}

	public static void simulation(String console) {
		char side = console.charAt(0);
		char dir = console.charAt(1);

		// 윗면
		if (side == 'U') {
			// 인접해있는 면 돌리기
			if (dir == '+') { // 시계 방향
				for (int i = 0; i < 3; i++) {
					char tmp = cube[4][i][2];
					cube[4][i][2] = cube[1][0][i];
					cube[1][0][i] = cube[5][2 - i][0];
					cube[5][2 - i][0] = cube[3][2][2 - i];
					cube[3][2][2 - i] = tmp;
				}
			} else { // 반시계 방향
				for (int i = 0; i < 3; i++) {
					char tmp = cube[3][2][2 - i];
					cube[3][2][2 - i] = cube[5][2 - i][0];
					cube[5][2 - i][0] = cube[1][0][i];
					cube[1][0][i] = cube[4][i][2];
					cube[4][i][2] = tmp;
				}
			}

			// 해당 면 돌리기
			cube[0] = turnTargetSide(cube[0], dir);
		} else if (side == 'D') { // 아랫면
			if (dir == '+') {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[4][2 - i][0];
					cube[4][2 - i][0] = cube[3][0][i];
					cube[3][0][i] = cube[5][i][2];
					cube[5][i][2] = cube[1][2][2 - i];
					cube[1][2][2 - i] = tmp;
				}
			} else {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[1][2][2 - i];
					cube[1][2][2 - i] = cube[5][i][2];
					cube[5][i][2] = cube[3][0][i];
					cube[3][0][i] = cube[4][2 - i][0];
					cube[4][2 - i][0] = tmp;
				}
			}

			cube[2] = turnTargetSide(cube[2], dir);
		} else if (side == 'F') { // 앞면
			if (dir == '+') {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[4][2][2 - i];
					cube[4][2][2 - i] = cube[2][0][i];
					cube[2][0][i] = cube[5][2][2 - i];
					cube[5][2][2 - i] = cube[0][2][2 - i];
					cube[0][2][2 - i] = tmp;
				}
			} else {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[0][2][2 - i];
					cube[0][2][2 - i] = cube[5][2][2 - i];
					cube[5][2][2 - i] = cube[2][0][i];
					cube[2][0][i] = cube[4][2][2 - i];
					cube[4][2][2 - i] = tmp;
				}
			}

			cube[1] = turnTargetSide(cube[1], dir);
		} else if (side == 'B') { // 뒷면
			if (dir == '+') {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[4][0][i];
					cube[4][0][i] = cube[0][0][i];
					cube[0][0][i] = cube[5][0][i];
					cube[5][0][i] = cube[2][2][2 - i];
					cube[2][2][2 - i] = tmp;
				}
			} else {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[2][2][2 - i];
					cube[2][2][2 - i] = cube[5][0][i];
					cube[5][0][i] = cube[0][0][i];
					cube[0][0][i] = cube[4][0][i];
					cube[4][0][i] = tmp;
				}
			}

			cube[3] = turnTargetSide(cube[3], dir);
		} else if (side == 'L') { // 왼쪽면
			if (dir == '+') {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[2][2 - i][0];
					cube[2][2 - i][0] = cube[1][2 - i][0];
					cube[1][2 - i][0] = cube[0][2 - i][0];
					cube[0][2 - i][0] = cube[3][2 - i][0];
					cube[3][2 - i][0] = tmp;
				}
			} else {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[3][2 - i][0];
					cube[3][2 - i][0] = cube[0][2 - i][0];
					cube[0][2 - i][0] = cube[1][2 - i][0];
					cube[1][2 - i][0] = cube[2][2 - i][0];
					cube[2][2 - i][0] = tmp;
				}
			}

			cube[4] = turnTargetSide(cube[4], dir);
		} else { // 오른쪽 면
			if (dir == '+') {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[0][i][2];
					cube[0][i][2] = cube[1][i][2];
					cube[1][i][2] = cube[2][i][2];
					cube[2][i][2] = cube[3][i][2];
					cube[3][i][2] = tmp;
				}
			} else {
				for (int i = 0; i < 3; i++) {
					char tmp = cube[3][i][2];
					cube[3][i][2] = cube[2][i][2];
					cube[2][i][2] = cube[1][i][2];
					cube[1][i][2] = cube[0][i][2];
					cube[0][i][2] = tmp;
				}
			}

			cube[5] = turnTargetSide(cube[5], dir);
		}
	}

	public static void clearCube() {
		// 위, 앞, 아래, 뒤, 왼, 오
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[0][i][j] = 'w';
				cube[1][i][j] = 'r';
				cube[2][i][j] = 'y';
				cube[3][i][j] = 'o';
				cube[4][i][j] = 'g';
				cube[5][i][j] = 'b';
			}
		}
	}

	public static String getTopSide() {
		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				sb.append(cube[0][x][y]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}
