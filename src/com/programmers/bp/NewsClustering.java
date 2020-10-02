/**
 * 
 */
package com.programmers.bp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 2, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/17677
 */
public class NewsClustering {

	public static void main(String[] args) {
		System.out.println(solution("FRANCE", "french"));
		System.out.println(solution("handshake", "shake hands"));
		System.out.println(solution("aa1+aa2", "AAAA12"));
		System.out.println(solution("E=M*C^2", "e=m*c^2"));
	}

	public static int solution(String str1, String str2) {
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();

		if (str1.equals(str2)) {
			return 65536;
		}

		List<String> set1 = makeSet(str1);
		List<String> set2 = makeSet(str2);

		if (set1.size() == 0 && set2.size() == 0) {
			return 65536;
		}

		double jaccard = 0;
		int union = 0;
		int intersection = 0;

		// 교집합 수
		for (String s : set1) {
			if (set2.contains(s)) {
				intersection += 1;
				set2.remove(set2.indexOf(s));
			}
		}

		// 합집합 수
		union = set1.size() + set2.size();

		if (jaccard != 1) {
			jaccard = (double) intersection / (double) union;
		}

		return (int) Math.floor(jaccard * 65536);
	}

	public static List<String> makeSet(String str) {
		List<String> set = new ArrayList<String>();

		for (int i = 0; i < str.length() - 1; i++) {
			if (97 <= str.charAt(i) && str.charAt(i) <= 122 && 97 <= str.charAt(i + 1) && str.charAt(i + 1) <= 122) {
				set.add(str.substring(i, i + 2));
			}
		}

		return set;
	}

}
