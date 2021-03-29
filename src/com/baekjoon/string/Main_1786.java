/**
 * 
 */
package com.baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 14, 2021
 * @문제 이름 : 찾기 - KMP 알고리즘
 * @문제 링크 : https://www.acmicpc.net/problem/1786
 */
public class Main_1786 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String whole = br.readLine();
		String part = br.readLine();

		ArrayList<Integer> startIndex = KMP(whole, part);
		StringBuilder answer = new StringBuilder();

		answer.append(startIndex.size() + "\n");
		startIndex.stream().forEach(x -> answer.append(x + " "));

		System.out.println(answer.toString());

	}

	public static int[] makeLPSTable(String pattern) {
		int[] table = new int[pattern.length()];

		// i=1, j=0으로 한칸 차이나게 포개어놓고 시작
		for (int i = 1, j = 0; i < pattern.length(); i++) {

			// 문자열이 일치하지 않았다면
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = table[j - 1];
			}

			// 문자열이 일치하였으면
			if (pattern.charAt(i) == pattern.charAt(j)) {
				// j를 증가시키고, 증가된 j값이 LPS값이 됨.
				table[i] = ++j;
			}
		}

		return table;
	}

	public static ArrayList<Integer> KMP(String whole, String part) {
		// 전체 문자열에서 부분 문자열이 있는 시작 인덱스를 모아놓은 리스트
		ArrayList<Integer> startIndex = new ArrayList<Integer>();
		// 접두사, 접미사가 일치하는 LPS 테이블 만듦
		int[] table = makeLPSTable(part);

		// 전체 문자열과 찾을 문자열을 처음부터 순회
		// w = 전체 문자열 순회 변수
		// p = 부분 문자열 순회 변수
		for (int w = 0, p = 0; w < whole.length(); w++) {

			// 하나글자씩 비교하는 과정에서 두 글자가 다를 때
			while (p > 0 && whole.charAt(w) != part.charAt(p)) {
				// 두 문자가 다를 때 다른 부분이 나오기 이전에 일치하는 접두사가 있을 수 있기 때문에
				// 만약 일치하는 접두사가 있으면 그 다음부터 다시 비교하면 됨
				// 일치하는 부분이 없으면(table[p-1] = 0) 찾을 문자열의 처음부터전체 문자열과 비교 시작
				p = table[p - 1];
			}

			// 만약 비교하는 두 문자가 같으면
			if (whole.charAt(w) == part.charAt(p)) {
				// 순회 변수가 끝까지 다다랐을 때 = 찾을 문자열의 길이와 같을 때
				if (p == part.length() - 1) {
					// 현재의 위치는 끝 위치니까 전체 문자열에서 문자열 길이만큼 빼면 시작 인덱스 알 수 있음
					startIndex.add(w - p + 1);

					// 그리고 p는 현재 위치까지 전부 맞았으니
					// p를 일치되는 prefix의 맨 끝으로 이동시켜서 (LPS테이블)
					// 일치하는 prefix의 뒤쪽부터 다시 비교를 시작하게 만듬.
					p = table[p];
				} else {
					// 아직 검사 중
					// 찾을 문자열의 순회 변수를 하나 증가하여 다음 문자와 비교
					p++;
				}
			}
		}

		return startIndex;

	}

}
