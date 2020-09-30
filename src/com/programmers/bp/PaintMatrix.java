/**
 * 
 */
package com.programmers.bp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 30, 2020
 * @문제 링크 : NHN pre-test 기출 : 0과 1로이루어진 2차원 배열에서 값이 1인 칸의 상/하/좌/우 탐색하면서 0으로 바꾸고 각 영역의 크기를 내림차순으로 출력
 */
public class PaintMatrix {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N][N];

		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		List<Integer> fields = solution(matrix);

		System.out.println(fields.size());
		if (fields.size() > 0) {
			fields.stream().sorted().forEach(x -> System.out.print(x + " "));
		}
	}

	public static List<Integer> solution(int[][] matrix) {
		List<Integer> fields = new ArrayList<Integer>();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					int fieldCnt = paint(i, j, matrix);
					fields.add(fieldCnt);
				}
			}
		}

		return fields;
	}

	public static int paint(int i, int j, int[][] matrix) {
		if (matrix[i][j] == 0) {
			return 0;
		}

		matrix[i][j] = 0;
		int fieldsCnt = 1;

		if (j == 0) {
			fieldsCnt += paint(i, j + 1, matrix);
		} else if (j == matrix.length - 1) {
			fieldsCnt += paint(i, j - 1, matrix);
		} else {
			fieldsCnt += paint(i, j + 1, matrix);
			fieldsCnt += paint(i, j - 1, matrix);
		}

		if (i == 0) {
			fieldsCnt += paint(i + 1, j, matrix);
		} else if (i == matrix.length - 1) {
			fieldsCnt += paint(i - 1, j, matrix);
		} else {
			fieldsCnt += paint(i + 1, j, matrix);
			fieldsCnt += paint(i - 1, j, matrix);
		}
		return fieldsCnt;
	}

}
