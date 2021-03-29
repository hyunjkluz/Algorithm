/**
 * 
 */
package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 5, 2021
 * @문제 이름 : 문자열 폭발
 * @문제 링크 : https://www.acmicpc.net/problem/9935
 */
public class Main_9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		String explosion = br.readLine();

		System.out.println(solution3(target, explosion));

	}

	// 1%에서 메모리 초과
	public static String solution1(String target, String explosion) {
		StringBuffer newTarget = new StringBuffer(target);
		ArrayList<Integer> idxList = new ArrayList<Integer>();

		while (newTarget.length() >= explosion.length()) {
			idxList.clear();

			for (int i = 0; i + explosion.length() <= newTarget.length(); i++) {
				if (newTarget.substring(i, i + explosion.length()).equals(explosion)) {
					idxList.add(i);
				}
			}

			if (idxList.size() == 0) {
				break;
			}

			for (int i = idxList.size() - 1; i >= 0; i--) {
				int idx = idxList.get(i);
				newTarget.replace(idx, idx + explosion.length(), "");
			}
		}

		return newTarget.length() == 0 ? "FRULA" : target.toString();
	}

	// 45%에서 메모리 초과
	public static String solution2(String target, String explosion) {
		while (target.length() >= explosion.length()) {
			int pastLength = target.length();
			// replaceAll은 메모리를 많이 사용함 : 메소드를 만들 때 이미 잡아놓은 메모리가 있을거기 때문에
			target = target.replaceAll(explosion, "");

			if (pastLength == target.length()) {
				break;
			}

		}

		return target.length() == 0 ? "FRULA" : target.toString();
	}

	public static String solution3(String target, String explosion) {
		StringBuilder answer = new StringBuilder();
		Stack<Character> stack = new Stack<Character>();

		// 뒤에서부터 스택에 넣음
		for (int i = target.length() - 1; i >= 0; i--) {
			stack.push(target.charAt(i));

			// 스택의 크기가 폭발 단어의 길이와 같거나 클 때
			// + 넣은 단어가 폴발 단어의 시작 글자와 같을 때
			// = 길이가 길다고해서 다 폭발 단어는 아니기 때문에 가능성 있을때만 검사
			if (stack.size() >= explosion.length() && stack.peek().equals(explosion.charAt(0))) {
				boolean flag = true;

				// 이미 앞에서 첫글자는 같은걸로 판별했으니깐 2번째 글자부터 판별
				for (int j = 1; j < explosion.length(); j++) {
					if (!stack.get(stack.size() - 1 - j).equals(explosion.charAt(j))) {
						flag = false;
						break;
					}
				}

				// 폭발 단어가 있으면
				if (flag) {
					// 단어 길이 만큼 스택에서 제거
					for (int j = 0; j < explosion.length(); j++) {
						stack.pop();
					}
				}
			}
		}

		if (stack.isEmpty()) {
			answer.append("FRULA");
		} else {
			while (!stack.isEmpty()) {
				answer.append(stack.pop());
			}
		}

		return answer.toString();
	}
}
