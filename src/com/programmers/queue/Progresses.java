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
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42586
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
		// int[] progresses = { 93, 30, 55 };
		// int[] speeds = { 1, 30, 5 };

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
			// 맨 앞에 위치한 작업이 끝나려면 걸리는 일 수
			int flagDays = (int) Math.ceil((double) flagProgress / (double) progressQ.peek().speed);

			int cnt = 0;

			// 맨 앞의 일이 끝나는 날 나머지 일들의 진행도 업데이트
			for (Progress progress : progressQ) {
				progress.progress = progress.progress + flagDays * progress.speed;
			}

			for (Progress progress : progressQ) {
				// 진행도가 100 이상인 작업 개수 카운트
				if (progress.progress >= 100) {
					cnt++;
				} else {
					break;
				}
			}

			// 같이 끝난 작업 큐에서 제거
			for (int i = 0; i < cnt; i++) {
				progressQ.poll();
			}

			// 맨 앞이 일 배포 시 볓개가 같이 배포되었는지 더함
			answer.add(cnt);
		}

		int[] returnAnswer = new int[answer.size()];
		for (int x = 0; x < answer.size(); x++) {
			returnAnswer[x] = answer.get(x).intValue();
		}

		return returnAnswer;

	}

}
