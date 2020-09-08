/**
 * 
 */
package com.programmers.hash;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Aug 6, 2020
 * @주요 개념 : getOrDefault method / hashmap key: 출전 선수 이름, value: 중복 이름이 있으면 증가
 * 문제 Link : https://programmers.co.kr/learn/courses/30/lessons/42576
 */
public class CompletePlayer {

	public static void main(String[] args) {
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"kiki", "eden"};
		
		System.out.println(solution(participant, completion));
	}

	public static String solution(String[] participant, String[] completion) {
		String answer = "";

		Map<String, Integer> map = new HashMap<>();

		for (String part : participant) {
			map.put(part, map.getOrDefault(part, 0) + 1);
		}

		for (String comp : completion) {
			map.put(comp, map.get(comp) - 1);
		}

		for (String key : map.keySet()) {
			if (map.get(key) != 0) {
				answer = key;
				break;
			}
		}

		return answer;
	}
}
