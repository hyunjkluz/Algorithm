/**
 * 
 */
package com.programmers.hash;

/**
 * @author : kimhyunjin
 * @CretaedAt : Aug 6, 2020
 * @주요 개념 : 문자열 비교 .startWith: 비교할 문자열이 비교 대상보다 짧을경우 자동으로 false
 * 문제 Link : https://programmers.co.kr/learn/courses/30/lessons/42577
 */
public class PhoneBook {
	public static void main(String[] args) {
		String[] phone_book = { "12", "123", "1235", "567", "88" };
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		boolean answer = true;

		for (int i = 0; i < phone_book.length - 1; i++) {
			for (int j = i + 1; j < phone_book.length; j++) {
				if (phone_book[i].startsWith(phone_book[j])) {
					return false;
				}
				if (phone_book[j].startsWith(phone_book[i])) {
					return false;
				}
			}
		}

		return answer;
	}
}
