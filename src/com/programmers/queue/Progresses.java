/**
 * 
 */
package com.programmers.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 7, 2020
 * @주요 개념 :
 */

class Progress {
	public Integer progress, speed;

	public Progress(Integer progress, Integer speed) {
		this.progress = progress;
		this.speed = speed;
	}

}

public class Progresses {

	public static void main(String[] args) {
		int[] progresses = { 95, 90, 99, 99, 80, 99 };
		int[] speeds = { 1, 1, 1, 1, 1, 1 };
//		int[] progresses = { 93, 30, 55 };
//		int[] speeds = { 1, 30, 5 };

		solution(progresses, speeds).toString();
	}

	public static int[] solution(int[] progresses, int[] speeds) {
		ArrayList<Integer> answer = new ArrayList<>();

		Queue<Progress> progressQ = new LinkedList<>();

		// 작업 목록 큐에 넣기
		for (int i = 0; i < progresses.length; i++) {
			progressQ.add(new Progress(progresses[i], speeds[i]));
		}

		while (!progressQ.isEmpty()) {
			Integer flagProgress = 100 - progressQ.peek().progress;
			int flagDays = (int) Math.ceil((double) flagProgress / (double) progressQ.peek().speed);

			int cnt = 0;

			for (Progress progress : progressQ) {
				progress.progress = progress.progress + flagDays * progress.speed;
			}

			for (Progress progress : progressQ) {
				if (progress.progress >= 100) {
					cnt++;
				} else {
					break;
				}
			}

			for (int i = 0; i < cnt; i++) {
				progressQ.poll();
			}

			answer.add(cnt);
		}

		int[] returnAnswer = new int[answer.size()];
		for (int x = 0; x < answer.size(); x++) {
			returnAnswer[x] = answer.get(x).intValue();
		}

		return returnAnswer;

	}

}
