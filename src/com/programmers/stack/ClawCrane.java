/**
 * 
 */
package com.programmers.stack;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 24, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/64061
 */
public class ClawCrane {

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };

		System.out.println("Result : " + solution2(board, moves));

	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;

		ArrayList<Stack<Integer>> boards = new ArrayList<Stack<Integer>>();
		Stack<Integer> box = new Stack<Integer>();

		for (int i = 0; i < board.length; i++) {
			Stack<Integer> stack = new Stack<Integer>();

			for (int j = board[i].length - 1; j >= 0; j--) {
				if (board[j][i] != 0) {
					stack.add(board[j][i]);
				}
			}

			boards.add(stack);
		}

		for (int pick : moves) {
			if (!boards.get(pick - 1).isEmpty()) {
				int newDoll = boards.get(pick - 1).pop();

				if (!box.empty() && box.peek().equals(newDoll)) {
					box.pop();
					answer += 2;
				} else {
					box.add(newDoll);
				}
			}
		}

		return answer;
	}

	public static int solution2(int[][] board, int[] moves) {
		int answer = 0;

		Stack<Integer> box = new Stack<Integer>();

		for (int move : moves) {
			for (int pick = 0; pick < board.length; pick++) {
				if (board[pick][move - 1] != 0) {
					if (!box.isEmpty() && box.peek().equals(board[pick][move - 1])) {
						box.pop();
						answer += 2;
					} else {
						box.push(board[pick][move - 1]);
					}

					board[pick][move - 1] = 0;
					break;
				}
			}
		}

		return answer;
	}

}
