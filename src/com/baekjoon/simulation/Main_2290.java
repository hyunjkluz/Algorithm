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
 * @CretaedAt : Nov 12, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/2290
 */
class Info {
	int top, bottom, mid, left, right;

	public Info(int top, int bottom, int mid, int left, int right) {
		super();
		this.top = top;
		this.bottom = bottom;
		this.mid = mid;
		this.left = left;
		this.right = right;
	}

	public void next(int s, int i) {
		this.right = (s + 3) * i;
		this.left = this.right + s + 1;
	}

	@Override
	public String toString() {
		return "[top=" + top + ", bottom=" + bottom + ", mid=" + mid + ", right=" + right + ", left=" + left + "]";
	}

}

public class Main_2290 {
	static int S;
	static Info info;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] line = bf.readLine().split(" ");
		S = Integer.parseInt(line[0]);
		int[] numbers = Arrays.stream(line[1].split("")).mapToInt(Integer::parseInt).toArray();

		info = new Info(0, 2 * S + 2, S + 1, 0, S + 1);
		solution(numbers);
	}

	public static void solution(int[] numbers) {
		String[][] LCD = new String[2 * S + 3][(S + 3) * numbers.length];
		for (int i = 0; i < LCD.length; i++) {
			Arrays.fill(LCD[i], " ");
		}

		for (int i = 0; i < numbers.length; i++) {
			int n = numbers[i];
			info.next(S, i);

			// 위
			if (n != 1 && n != 4) {
				for (int j = info.right + 1; j < info.left; j++) {
					LCD[info.top][j] = "-";
				}
			}

			// 아래
			if (n != 1 && n != 4 && n != 7) {
				for (int j = info.right + 1; j < info.left; j++) {
					LCD[info.bottom][j] = "-";
				}
			}

			// 가운데
			if (n != 1 && n != 7 && n != 0) {
				for (int j = info.right + 1; j < info.left; j++) {
					LCD[info.mid][j] = "-";
				}
			}

			// 왼쪽 위
			if (n != 1 && n != 2 && n != 3 && n != 7) {
				for (int j = info.top + 1; j < info.mid; j++) {
					LCD[j][info.right] = "|";
				}
			}

			// 왼쪽 아래
			if (n != 1 && n != 3 && n != 4 && n != 5 && n != 7 && n != 9) {
				for (int j = info.mid + 1; j < info.bottom; j++) {
					LCD[j][info.right] = "|";
				}
			}

			// 오른쪽 위
			if (n != 5 && n != 6) {
				for (int j = info.top + 1; j < info.mid; j++) {
					LCD[j][info.left] = "|";
				}
			}

			// 오른쪽 아래
			if (n != 2) {
				for (int j = info.mid + 1; j < info.bottom; j++) {
					LCD[j][info.left] = "|";
				}
			}

		}
		printLCD(LCD);
	}

	public static void printLCD(String[][] LCD) {
		for (int i = 0; i < LCD.length; i++) {
			for (int j = 0; j < LCD[i].length; j++) {
				System.out.print(LCD[i][j]);
			}
			System.out.println("");
		}
	}

}
