/**
 * 
 */
package com.programmers.dp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 29, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42895
 */
public class Nnumber {
	static Map<Integer, HashSet<Integer>> nDp = new HashMap<Integer, HashSet<Integer>>();
	static int targetN;

	public static void main(String[] args) {
//		System.out.println(solution(5, 12));
		System.out.println(solution(5, 31168));
	}

	public static int solution(int N, int number) {
		// N을 i번 이용하여 만들 수 있는 수들의 집합을 담은 맵
		targetN = N;
		nDp.put(0, new HashSet<Integer>());

		// 8번까지 활용할 수 있는 수들 계산하여 집합에 넣기
		for (int count = 1; count <= 8; count++) {
			getNSet(count);
			if (nDp.get(count).contains(number))
				return count;
		}

		return -1;
	}

	public static HashSet<Integer> getNSet(int count) {
		if (nDp.containsKey(count) && !nDp.get(count).isEmpty()) {
			return nDp.get(count);
		}

		HashSet<Integer> temp = new HashSet<Integer>();
		int repeatN = 0;
		for (int i = 0; i < count; i++)
			// N을 count번 반복한 수 구함 : NNN, NNNN ...
			repeatN = 10 * repeatN + targetN;

		/*
		 * N을 count번 활용한 수 집합 
		 * 1. N을 count번 이어붙인 수 
		 * 2. N을 i번 활용한 수와 count - i번 활용한 수들의 사칙연산의 결과
		 */

		// 1.
		temp.add(repeatN);

		// 2.
		for (int i = 1; i < count; i++) {
			HashSet<Integer> op1Set = getNSet(i);
			HashSet<Integer> op2Set = getNSet(count - i);

			for (Integer op1 : op1Set) {
				for (Integer op2 : op2Set) {
					temp.add(op1 + op2);
					temp.add(op1 - op2);
					temp.add(op1 * op2);
					if (op2 != 0)
						temp.add(op1 / op2);
				}
			}
		}

		return nDp.put(count, temp);
	}
}
