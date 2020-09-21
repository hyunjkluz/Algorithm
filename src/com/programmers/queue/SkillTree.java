/**
 * 
 */
package com.programmers.queue;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 21, 2020
 * @주요 개념 :
 */
public class SkillTree {
	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };

		System.out.println("Result : " + solution(skill, skill_trees));
	}

	public static int solution(String skill, String[] skill_trees) {
		int answer = 0;
		String[] skillArray = skill.split("");
		Queue<Character> skills = new LinkedList<Character>();
		Queue<Character> newSkills = new LinkedList<Character>();

		for (char s : skill.toCharArray()) {
			newSkills.add(s);
		}

		for (String tree : skill_trees) {
			skills = new LinkedList(newSkills);
			boolean pre = true;

			for (int i = 0; i < tree.length(); i++) {
				char s = tree.charAt(i);

				if (skills.contains(s)) {
					if (!skills.isEmpty() && !skills.peek().equals(s)) {
						pre = false;
						break;
					}
					if (!skills.isEmpty() && skills.peek().equals(s)) {
						skills.poll();
						pre = true;
					}
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
