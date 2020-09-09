/**
 * 
 */
package com.programmers.sort;

import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 9, 2020
 * @주요 개념 :
 */
public class KthNumber {
	public static void main(String[] args) {
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };

		solution(array, commands);
	}

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for (Integer turn = 0; turn < commands.length; turn++) {
			int[] sliceArray = Arrays.copyOfRange(array, commands[turn][0] - 1, commands[turn][1]);
			Arrays.sort(sliceArray);

			answer[turn] = sliceArray[commands[turn][2] - 1];
		}
		return answer;
	}

}
