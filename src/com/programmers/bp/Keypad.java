/**
 * 
 */
package com.programmers.bp;

import java.awt.Point;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 25, 2020
 * @문제 링크 :
 */
public class Keypad {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 }, "right"));
		System.out.println(solution(new int[] { 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 }, "left"));
		System.out.println(solution(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 }, "right"));
	}

	public static String solution(int[] numbers, String hand) {
		Point[] points = new Point[10];
		points[0] = new Point(3, 1);
		points[1] = new Point(0, 0);
		points[2] = new Point(0, 1);
		points[3] = new Point(0, 2);
		points[4] = new Point(1, 0);
		points[5] = new Point(1, 1);
		points[6] = new Point(1, 2);
		points[7] = new Point(2, 0);
		points[8] = new Point(2, 1);
		points[9] = new Point(2, 2);
		Point L = new Point(3, 0);
		Point R = new Point(3, 2);

		StringBuilder answer = new StringBuilder();

		for (int num : numbers) {
			if (num == 1 || num == 4 || num == 7) {
				answer.append("L");
				L = points[num];
			} else if (num == 3 || num == 6 || num == 9) {
				answer.append("R");
				R = points[num];
			} else {
				Integer leftDiff = Math.abs(L.x - points[num].x) + Math.abs(L.y - points[num].y);
				Integer rightDiff = Math.abs(R.x - points[num].x) + Math.abs(R.y - points[num].y);

				if (leftDiff == rightDiff) {
					answer.append(hand.equals("right") ? "R" : "L");

					if (hand.equals("right")) {
						R = points[num];
					} else {
						L = points[num];
					}
				} else if (leftDiff < rightDiff) {
					answer.append("L");
					L = points[num];
				} else {
					answer.append("R");
					R = points[num];
				}
			}
		}
		return answer.toString();

	}

}
