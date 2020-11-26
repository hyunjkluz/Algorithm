/**
 * 
 */
package com.baekjoon.bf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 26, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/1543
 */
public class Main_1543 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String docs = br.readLine();
		String word = br.readLine();

		int search = 0;
		// 일단 문서의 끝 인덱스까지 돌림
		for (int i = 0; i < docs.length(); i++) {
			// 이동한 i와 만들어진 끝 idx가 원본 문자열 인덱스에서 벗어난다면 탈출
			// for (int i = 0; i < docs.length() - word.length() + 1; i++) 방법도 있음

			if (i + word.length() - 1 >= docs.length()) {
				break;
			}
			if (docs.substring(i, i + word.length()).equals(word)) {
				search += 1;
				i += word.length();
				i--;
			}
		}

		System.out.println(search);

	}

}
