/**
 * 
 */
package com.programmers.bp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 23, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/68645
 */
public class TriangleSnail {

	public static void main(String[] args) {
//		System.out.println(Arrays.toString(solution(4)));
//		System.out.println(Arrays.toString(solution(5)));
		System.out.println(Arrays.toString(solution(6)));
	}

	public static int[] solution(int n) {
		int[][] snail = new int[n][n];
		snail[0] = new int[] { -1 };
		int size = 0;

		for (int i = 0; i < n; i++) {
			snail[i] = new int[i + 1];
			snail[i][0] = i + 1;
			size += i + 1;
		}

		int fin = -1;

		while (!isFull(snail)) {
			fin++;
			add(fin, snail);
		}

		int[] answer = new int[size];
		int flag = 0;
		for (int i = 0; i < snail.length; i++) {
			for (int j = 0; j < snail[i].length; j++) {
				answer[flag++] = snail[i][j];
			}
		}

		return answer;

	}

	public static boolean isFull(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static void add(int cnt, int[][] arr) {
		int num = 1;
		int start = cnt * 2;
		int fin = arr.length - cnt - 1;

		if (start != 0) {
			num = arr[start - 1][arr[start - 1].length - 1];
			num += 1;
		}

		int i = start;
		do {
			arr[i++][cnt] = num++;
		} while (i <= fin);

		for (int j = cnt + 1; j < arr[fin].length - cnt; j++) {
			arr[fin][j] = num++;
		}

		fin -= 1;
		for (int x = arr[fin].length - 1 - cnt; fin >= start + 1; fin--, x--) {
			arr[fin][x] = num++;
		}

		return;
	}

	public static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
}
