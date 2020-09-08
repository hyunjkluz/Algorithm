/**
 * 
 */
package com.programmers.queue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 8, 2020
 * @주요 개념 :
 */
class Work implements Comparable<Work> {
	public Integer priority;
	public boolean isMine;

	public Work(Integer priority, boolean isMine) {
		this.priority = priority;
		this.isMine = isMine;
	}

	@Override
	public int compareTo(Work target) {
		return this.priority <= target.priority ? 1 : -1;
	}

	@Override
	public String toString() {
		return "Work [priority=" + priority + ", isMine=" + isMine + "]";
	}
}

public class Printer {
	public static void main(String[] agrs) {
		int[] priorities = { 2, 1, 3, 2 };
//		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 2;

		System.out.println("Result : " + solution(priorities, location));
	}

	public static int solution(int[] priorities, int location) {
		// 작업 우선순위를 우선순위대로 뽑은 우선순위 큐
		PriorityQueue<Work> workPriority = new PriorityQueue<Work>();
		// 작업 우선순위를 적은 큐
		Queue<Work> workQu = new LinkedList<Work>();

		for (int i = 0; i < priorities.length; i++) {
			Work w = new Work(priorities[i], false);
			if (i == location) {
				w.isMine = true;
			}
			workPriority.add(w);
			workQu.add(w);
		}

		Integer cnt = 0;

		while (!workPriority.isEmpty()) {
			cnt++;
			Work doWork = null;

			if (workPriority.peek().priority != workQu.peek().priority) {

				while (true) {
					if (workPriority.peek().priority == workQu.peek().priority) {
						break;
					} else {
						workQu.add(workQu.poll());
					}
				}

				doWork = workQu.poll();
			} else {
				doWork = workQu.poll();
			}

			workPriority.poll();

			if (doWork.isMine) {
				break;
			}

		}

		return cnt;
	}
}
