/**
 * 
 */
package com.programmers.bp;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 25, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/64065
 */

public class Tuple {

	public static void main(String[] args) {
		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		System.out.println(Arrays.toString(solution(s)));
	}

	public static int[] solution(String s) {
		LinkedHashSet<Integer> answer = new LinkedHashSet<Integer>();
		String[] list = s.substring(1, s.length() - 1).split("[\\\\{\\\\}]");

		List<String> list2 = Arrays.asList(list)
				.stream()
				.filter(m -> !m.equals(","))
				.sorted((a, b) -> {
					if (a.length() < b.length()) {
						return -1;
					}
					return 1;
				}).collect(Collectors.toList());

		for (String tuple : list2) {
			if (!tuple.isEmpty()) {
				for (String t : tuple.split(",")) {
					answer.add(Integer.parseInt(t));
				}
			}
		}

		return answer.stream().mapToInt(i -> i).toArray();
	}
}
