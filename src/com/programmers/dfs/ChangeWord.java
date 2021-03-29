/**
 * 
 */
package com.programmers.dfs;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 17, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/43163
 */
public class ChangeWord {
	public static int answer;

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };

		System.out.println("Result : " + solution(begin, target, words));
	}

	public static int solution(String begin, String target, String[] words) {
		answer = 10000;
		List<String> wordList = new ArrayList<String>(Arrays.asList(words));

		if (!wordList.contains(target)) {
			return 0;
		}

		boolean[] visited = new boolean[words.length];
		dfs(begin, target, words, 0, visited);

		return answer;
	}

	public static void dfs(String flag, String target, String[] words, int count, boolean[] visited) {
		if (flag.equals(target)) {
			answer = answer > count ? count : answer;
			return;
		}

		for (int i = 0; i < words.length; i++) {
			if (!visited[i] && canTranslate(flag, words[i])) {
				visited[i] = true;
				dfs(words[i], target, words, count + 1, visited);
				visited[i] = false;
			}
		}
	}

	public static boolean canTranslate(String flag, String s) {
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			if (flag.charAt(i) == s.charAt(i)) {
				cnt++;
			}
		}

		return cnt == flag.length() - 1 ? true : false;
	}

}
