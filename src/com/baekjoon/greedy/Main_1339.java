/**
 * 
 */
package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 30, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1339
 */
public class Main_1339 {
	static String[] nums;
	static int maxSum = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		nums = new String[N];

//		ArrayList<Character> list = new ArrayList<Character>();

		for (int i = 0; i < N; i++) {
			nums[i] = br.readLine();

//			for (int j = 0; j < nums[i].length(); j++) {
//				if (!list.contains(nums[i].charAt(j))) {
//					list.add(nums[i].charAt(j));
//				}
//			}
		}

//		solution1(new int[list.size()], new boolean[10], 0, list);
		solution2();
		System.out.println(maxSum);

		br.close();
	}

	/**
	 * @Method Name : solution1 
	 * Desc : 순열(백트레킹 : 부분수열 구하기) 
	 * 나왔던 알파벳의 개수대로 0 ~ 9까지
	 * 차례대로 뽑아서 단어에 대응한 뒤 결과 값 구함
	 */
	public static void solution1(int[] bucket, boolean[] picked, int count, ArrayList<Character> list) {
		if (bucket.length == count) {
			calc1(bucket, list);
			return;
		}

		for (int i = 9; i >= 0; i--) {
			if (!picked[i]) {
				bucket[count] = i;
				picked[i] = true;
				solution1(bucket, picked, count + 1, list);
				picked[i] = false;
			}
		}
	}

	public static void calc1(int[] bucket, ArrayList<Character> list) {
		int answer = 0;

		for (int i = 0; i < nums.length; i++) {
			int sum = 0;

			for (int j = 0; j < nums[i].length(); j++) {
				int value = bucket[list.indexOf(nums[i].charAt(j))];
				sum += value * Math.pow(10, nums[i].length() - 1 - j);
			}

			answer += sum;
		}

		maxSum = Math.max(maxSum, answer);
	}

	/**
	 * @Method Name : solution2 
	 * Desc : 수학적 접근 단어가 나온 자리수를 더하여 큰 숫자대로 높은 숫자 부여 
	 * 많이, 높은 자리수에서 나올수록 큰 숫자 부여받음
	 * 
	 * 단지 높은 자리수부터 큰 숫자 부여하면 반례 존재
	 */
	public static void solution2() {
		HashMap<Character, Integer> dict = new HashMap<Character, Integer>();

		for (String number : nums) {
			for (int i = 0; i < number.length(); i++) {
				int value = dict.getOrDefault(number.charAt(i), 0);

				value += Math.pow(10, number.length() - 1 - i);
				dict.put(number.charAt(i), value);
			}
		}

		// 내림차순으로 정렬
		List<Character> keySetList = new ArrayList<Character>(dict.keySet());
		Collections.sort(keySetList, (o1, o2) -> (dict.get(o2).compareTo(dict.get(o1))));

		dict.clear();
		int value = 9;

		// 높은 자리에 많이 나온 순서대로 번호 부여
		for (Character ch : keySetList) {
			dict.put(ch, value--);
		}

		calc2(dict);

	}

	public static void calc2(HashMap<Character, Integer> dict) {
		int total = 0;

		for (String number : nums) {
			int sum = 0;
			for (int j = 0; j < number.length(); j++) {
				sum *= 10;
				sum += dict.get(number.charAt(j));
			}
			total += sum;
		}

		maxSum = total;
	}

}
