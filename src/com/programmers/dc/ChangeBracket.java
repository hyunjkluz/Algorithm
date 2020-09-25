/**
 * 
 */
package com.programmers.dc;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 24, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/60058
 */
public class ChangeBracket {

	public static void main(String[] args) {
		String p = "(()())()";

		System.out.println(solution(p));
	}

	public static String solution(String p) {
		return makeItRight(p);
	}

	public static String makeItRight(String p) {
		String[] uv = makeBalance(p);

		if (uv[0].isEmpty()) {
			return "";
		}

		if (checkRight(uv[0])) {
			return uv[0] + makeItRight(uv[1]);
		} else {
			StringBuilder newString = new StringBuilder();
			newString.append("(");
			newString.append(makeItRight(uv[1]));
			newString.append(")");

			if (uv[0].length() > 2) {
				newString.append(reverse(uv[0]));
			}

			return newString.toString();
		}

	}

	public static String[] makeBalance(String p) {
		int[] cnt = new int[2];
		String[] answer = new String[2];
		Arrays.fill(answer, "");

		if (p.isEmpty()) {
			return answer;
		}

		int i = 0;
		for (; i < p.length(); i++) {
			if (Character.toString(p.charAt(i)).equals("(")) {
				cnt[0] += 1;
			} else {
				cnt[1] += 1;
			}

			if (cnt[0] == cnt[1]) {
				break;
			}

		}

		answer[0] = p.substring(0, i + 1);
		answer[1] = p.substring(i + 1, p.length());

		return answer;
	}

	public static boolean checkRight(String p) {
		Stack<String> op = new Stack<String>();

		for (char b : p.toCharArray()) {
			if (op.isEmpty() && Character.toString(b).equals(")")) {
				return false;
			} else if (!op.isEmpty() && Character.toString(b).equals(")")) {
				op.pop();
			} else {
				op.add(Character.toString(b));
			}
		}

		if (op.isEmpty()) {
			return true;
		}

		return false;
	}

	public static String reverse(String u) {
		StringBuilder newU = new StringBuilder();

		for (int i = 1; i < u.length() - 1; i++) {
			newU.append(Character.toString(u.charAt(i)).equals("(") ? ")" : "(");
		}

		return newU.toString();
	}
}
