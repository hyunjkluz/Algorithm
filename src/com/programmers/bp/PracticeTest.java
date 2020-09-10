/**
 * 
 */
package com.programmers.bp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 10, 2020
 * @주요 개념 :
 */
class Test implements Comparable<Test> {
	public Integer student;
	public Integer correct;

	public Test(Integer student, Integer correct) {
		this.student = student;
		this.correct = correct;
	}

	@Override
	public int compareTo(Test obj) {
		return this.correct < obj.correct ? 1 : -1;
	}

}

public class PracticeTest {
	public static void main(String[] args) {
		int[] answers = { 1, 3, 2, 4, 2 };
		printArray(solution(answers));
	}

	public static int[] solution(int[] answers) {
		int[][] students = { { 1, 2, 3, 4, 5 }, { 2, 1, 2, 3, 2, 4, 2, 5 }, { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 } };

		List<Integer> corrects = new ArrayList<Integer>();

		for (int s = 0; s < students.length; s++) {
			int cnt = sum(students[s], answers, 0, 0, 0);
			corrects.add(cnt);
		}

		int max = Collections.max(corrects);
		List<Integer> topStudent = new ArrayList<Integer>();

		for (int i = 0; i < corrects.size(); i++) {
			if (corrects.get(i) == max) {
				topStudent.add(i + 1);
			}
		}

		int[] answer = new int[topStudent.size()];
		for (int i = 0; i < topStudent.size(); i++) {
			answer[i] = topStudent.get(i);
		}

		return answer;

	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static int sum(int[] student, int[] answers, int cnt, int turn, int start) {
		if (start == answers.length) {
			return cnt;
		}

		if (turn == student.length) {
			turn = 0;
		}

		if (answers[start] == student[turn]) {
			cnt++;
		}

		return sum(student, answers, cnt, turn + 1, start + 1);
	}

}
