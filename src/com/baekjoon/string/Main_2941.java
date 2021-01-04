/**
 * 
 */
package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 4, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/2941
 */
public class Main_2941 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		ArrayList<String> startIdx = new ArrayList<>(Arrays.asList(new String[] { "c", "d", "l", "n", "s", "z" }));
		ArrayList<String> wordList = new ArrayList<>(
				Arrays.asList(new String[] { "c=", "c-", "d-", "lj", "nj", "s=", "z=" }));
		int answer = 0;

		for (int i = 0; i < word.length(); i++) {
			if (startIdx.contains(Character.toString(word.charAt(i)))) {
				if (i + 2 <= word.length() && wordList.contains(word.substring(i, i + 2))) {
					i += 1;
					answer++;
					continue;
				}

				if (i + 3 <= word.length()) {
					if (word.substring(i, i + 3).equals("dz=")) {
						i += 2;
						answer++;
						continue;
					}
				}
				answer++;
			} else {
				answer++;
			}
		}

		System.out.println(answer);

	}

}
