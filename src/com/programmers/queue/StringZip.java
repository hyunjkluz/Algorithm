/**
 * 
 */
package com.programmers.queue;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 22, 2020
 * @주요 개념 :
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/60057
 */
public class StringZip {

	public static void main(String[] args) {
		String s = "abcabcabcabcdededededede";

		System.out.println("Result : " + solution(s));
	}

	public static int solution(String s) {
		Queue<String> stringQue = new LinkedList<String>();
		int minLength = s.length();

		for (int i = 1; i < s.length(); i++) {
			int j = 0, end = j + i;

			for (; end <= s.length(); j = end, end = j + i) {
				stringQue.offer(s.substring(j, end));
			}

			StringBuilder zip = new StringBuilder();

			while (!stringQue.isEmpty()) {
				String flagString = stringQue.poll();
				int cnt = 1;

				while (!stringQue.isEmpty()) {
					if (flagString.equals(stringQue.peek())) {
						cnt++;
						stringQue.poll();
					} else {
						break;
					}
				}

				if (cnt == 1) {
					zip.append(flagString);
				} else {
					zip.append(Integer.toString(cnt) + flagString);
				}
			}

			String remainder = s.substring(j, s.length());
			if (remainder.length() > 0) {
				zip.append(remainder);
			}

			if (zip.length() < minLength) {
				minLength = zip.length();
			}

		}
		return minLength;
	}
}
