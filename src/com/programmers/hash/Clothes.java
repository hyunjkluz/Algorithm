/**
 * 
 */
package com.programmers.hash;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Aug 7, 2020
 * @주요 개념 : 모든 종류에 대한 경우의 수 구하기 
 */
public class Clothes {
	public static void main(String[] args) {
//		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		String[][] clothes = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
		
		System.out.println(Integer.toString(solution(clothes)));
	}

	public static int solution(String[][] clothes) {
		int answer = 1;

		HashMap<String, Integer> clothe = new HashMap<String, Integer>();

		for (int i = 0; i < clothes.length; i++) {
			clothe.put(clothes[i][1], clothe.getOrDefault(clothes[i][1], 0) + 1);
		}

		for(String key : clothe.keySet()) {
			answer *= clothe.get(key) + 1;
		}
		
		answer -= 1;	// 아무것도 안입었을 대 경우의 수 빼주기
		
		return answer;
	}
}
