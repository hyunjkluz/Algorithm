/**
 * 
 */
package com.programmers.bp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 23, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/68644
 */
public class SumTwoNum {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] { 2, 1, 3, 4, 1 })));
		System.out.println(Arrays.toString(solution(new int[] { 5, 0, 2, 7 })));
	}

	public static int[] solution(int[] numbers) {
		Set<Integer> sums = new HashSet<Integer>();

		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				sums.add(numbers[i] + numbers[j]);
			}
		}

		return sums.stream().sorted().mapToInt(x -> x).toArray();
	}

}
