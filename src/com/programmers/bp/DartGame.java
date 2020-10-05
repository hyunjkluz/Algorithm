/**
 * 
 */
package com.programmers.bp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 5, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/17682
 */
public class DartGame {
	public static void main(String[] args) {
		System.out.println(solution("1S2D*3T"));
		System.out.println(solution("1D2S#10S"));
		System.out.println(solution("1D2S0T"));
		System.out.println(solution("1S*2T*3S"));
		System.out.println(solution("1D#2S*3S"));
		System.out.println(solution("1T2D3D#"));
		System.out.println(solution("1D2S3T*"));
	}

	public static int solution(String dartResult) {
		int answer = 0;
		Stack<Integer> score = new Stack<Integer>();
		int lastStringIdx = -1;

		for (int i = 1; i < dartResult.length(); i++) {
			if (dartResult.charAt(i) == 'D' || dartResult.charAt(i) == 'S' || dartResult.charAt(i) == 'T') {
				int num = Integer.parseInt(dartResult.substring(lastStringIdx + 1, i));

				switch (dartResult.substring(i, i + 1)) {
				case "S":
					num = (int) Math.pow(num, 1);
					break;
				case "D":
					num = (int) Math.pow(num, 2);
					break;
				case "T":
					num = (int) Math.pow(num, 3);
					break;
				}

				if (i + 1 < dartResult.length() && dartResult.charAt(i + 1) == '*') {
					if (!score.isEmpty()) {
						score.add(score.pop() * 2);
					}
					score.add(num * 2);
					lastStringIdx = i + 1;
				} else if (i + 1 < dartResult.length() && dartResult.charAt(i + 1) == '#') {
					score.add(num * -1);
					lastStringIdx = i + 1;
				} else {
					score.add(num);
					lastStringIdx = i;
				}

			}
		}

		while (!score.isEmpty()) {
			answer += score.pop();
		}
		return answer;
	}

}
