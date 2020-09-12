/**
 * 
 */
package com.programmers.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 12, 2020
 * @주요 개념 :
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42862
 */
public class GymSuit {

	public static void main(String[] args) {
		int n = 10;
		int[] lost = { 3, 7 };
		int[] reserve = { 7, 4, 5 };

		System.out.println("Result : " + Integer.toString(solution(n, lost, reserve)));

	}

	public static int solution(int n, int[] lost, int[] reserve) {
		List<Integer> lostList = new ArrayList<Integer>();
		List<Integer> reserveList = new ArrayList<Integer>();

		for (Integer r : reserve) {
			reserveList.add(r);
		}

		for (Integer l : lost) {
			lostList.add(l);
		}

		List<Integer> saveList = new ArrayList<Integer>();

		for (Integer ll : lostList) {
			if (reserveList.contains(ll)) {
				saveList.add(ll);
			}
		}

		for (Integer s : saveList) {
			reserveList.remove(reserveList.indexOf(s));
			lostList.remove(lostList.indexOf(s));
		}

		Integer save = 0;

		for (Integer ll : lostList) {
			if (reserveList.contains(ll + 1)) {
				save++;
				reserveList.remove(reserveList.indexOf((ll + 1)));
			} else if (reserveList.contains(ll - 1)) {
				save++;
				reserveList.remove(reserveList.indexOf((ll - 1)));
			}

		}
		return n - lostList.size() + save;

	}

}
