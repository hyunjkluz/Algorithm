/**
 * 
 */
package com.programmers.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 21, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/49993
 */
public class SkillTree {
	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };

		System.out.println("Result : " + solution(skill, skill_trees));
	}

	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;
		Queue<Character> skills = new LinkedList<Character>();
		Queue<Character> newSkills = new LinkedList<Character>();

		for (char s : skill.toCharArray()) {
			newSkills.add(s);
		}

		// 하나의 스킬에 대한 검사
		for (String tree : skill_trees) {
			// 선행 스킬 순서대로 큐 제작
			skills = new LinkedList<>(newSkills);
			boolean pre = true;

			// 검사할 스킬트리 안의 스킬을 하나씩 검사
			for (int i = 0; i < tree.length(); i++) {
				char s = tree.charAt(i);

				// 선행 스킬 순서 안에 스킬이 존재하면
				if (skills.contains(s)) {
					// 선행 스킬이 비어있지 않고 선행 스킬 맨 위가 현재의 스킬과 다르면
					// = 선행 순서와 다름
					if (!skills.isEmpty() && !skills.peek().equals(s)) {
						pre = false;
						break;
					}

					// 선행 스킬이 비어있지 않고 선행 스킬 맨 위가 현재의 스킬과 같으면
					// = 선행 순서와 맞음
					if (!skills.isEmpty() && skills.peek().equals(s)) {
						skills.poll();
						pre = true;
					}

					// 선행 스킬이 끝났으면 어떤 스킬을 쓰던 상관 없음
					// = 가능한 스킬 트리
					if (skills.isEmpty()) {
						pre = true;
						break;
					}
				}
			}

			if (pre) {
				answer++;
			}
		}

		return answer;
	}

}
