/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 12, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/14395
 */
class Operation {
	long value;
	StringBuilder op;

	public Operation(long value, String op) {
		this.value = value;
		this.op = new StringBuilder(op);
	}

	public Operation(long value) {
		this.value = value;
		this.op = new StringBuilder();
	}

	@Override
	public String toString() {
		return "Operation [value=" + value + ", op=" + op + "]";
	}

}

public class Main_14395 {
	static long s, t;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		s = info[0];
		t = info[1];

		if (s == t) {
			System.out.println(0);
		} else if (t == 0) {
			System.out.println("-");
		} else if (t == 1) {
			System.out.println("/");
		} else {
			System.out.println(solution());
		}

	}

	public static String solution() {
		Queue<Operation> queue = new LinkedList<Operation>();
		ArrayList<String> resultList = new ArrayList<String>();
		// long을 담아야 하기 때문에 배열 인덱스로 체크 불가능, set 사용
		HashSet<Long> calced = new HashSet<>();
		// s, t가 모두 0이 될 수 없기 때문에 + 사전에 0에 대한 처리 해두었기 때문에 -연산을 뺌
		// 빼면 틀림, 왜?
		char[] opChar = new char[] { '*', '+', '-', '/' };

		queue.add(new Operation(s));
//		queue.add(new Operation(1, "/"));
		calced.add(s);
		calced.add((long) 0);
//		calced.add((long) 1);

		while (!queue.isEmpty()) {
			Operation current = queue.poll();

			if (current.value == t) {
				resultList.add(current.op.toString());
				continue;
			}

			for (int i = 0; i < 4; i++) {
				long temp = calculate(current.value, i);

				if (1 <= temp && !calced.contains(temp)) {
					queue.add(new Operation(temp, current.op.append(opChar[i]).toString()));
					calced.add(temp);
					current.op.deleteCharAt(current.op.length() - 1);
				}
			}

		}

		Collections.sort(resultList);

		return resultList.size() > 0 ? resultList.get(0) : "-1";

	}

	public static long calculate(long n, int opNum) {
		switch (opNum) {
		case 0:
			return n * n;
		case 1:
			return n + n;
		case 2:
			return n - n;
		case 3:
			return n / n;
		}
		return 0;
	}

}
