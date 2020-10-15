package com.baekjoon.dfsbfs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 15, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/7576
 */
public class Main_7576 {
	public static int[][] calc = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] N = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] box = new int[N[1]][N[0]];

		for (int i = 0; i < box.length; i++) {
			box[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		System.out.println(solution(box));
	}

	public static int solution(int[][] box) {
		int remain = isRipe(box);
		if (remain == 0) {
			return 0;
		}

		Queue<Point> tomato = new LinkedList<Point>();

		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[i].length; j++) {
				if (box[i][j] == 1) {
					tomato.add(new Point(i, j));
				}
			}
		}

		int n = tomato.size();
		int days = 0;
		int ii = 0;

		while (!tomato.isEmpty()) {
			ii++;
			Point t = tomato.poll();

			for (int i = 0; i < calc.length; i++) {
				int newX = t.x + calc[i][0];
				int newY = t.y + calc[i][1];

				if (0 <= newX && newX < box.length && 0 <= newY && newY < box[newX].length) {
					if (box[newX][newY] == 0) {
						box[newX][newY] = 1;
						tomato.add(new Point(newX, newY));
					}
				}
			}

			if (ii == n) {
				ii = 0;
				days++;
				n = tomato.size();
			}

		}

		if (isRipe(box) != 0) {
			return -1;
		}

		return days - 1;
	}

	public static int isRipe(int[][] box) {
		int zeroCnt = 0;
		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[i].length; j++) {
				if (box[i][j] == 0) {
					zeroCnt++;
				}
			}
		}
		return zeroCnt;
	}

}
