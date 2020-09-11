package com.programmers.bp;

import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 11, 2020
 * @주요 개념 :
 */
public class CombPermTest {

	public static String[] BOX = { "A", "B", "C", "D" };

	public static void main(String[] args) {
		int n = BOX.length;
		int r = 2;

		boolean[] visited = new boolean[n];
		Arrays.fill(visited, false);

		System.out.println("[조합]");
		combination(new String[r], n, r, 0, 0);

		System.out.println("\n[중복조합]");
		repeatedCombination(new String[r], n, r, 0, 0);

		System.out.println("\n[순열]");
		permutation(new String[r], n, r, 0, visited);

		System.out.println("\n[중복순열]");
		repeatedPermutation(new String[r], n, r, 0);

	}

	/**
	 * 조합
	 * 
	 * @param comb    조합의 결과를 담을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 * @param start   그 다음 반복문 시작 값
	 */
	public static void combination(String[] comb, int n, int r, int current, int start) {
		if (r == current) {
			System.out.println(Arrays.toString(comb));
			return;
		}
		for (int i = start; i < n; i++) {
			comb[current] = BOX[i];
			combination(comb, n, r, current + 1, start + 1);
		}
	}

	/**
	 * 중복조합
	 * 
	 * @param comb    조합의 결과를 담을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 * @param start   그 다음 반복문 시작 값
	 */
	public static void repeatedCombination(String[] comb, int n, int r, int current, int start) {
		if (r == current) {
			System.out.println(Arrays.toString(comb));
			return;
		}
		for (int i = start; i < n; i++) {
			comb[current] = BOX[i];
			combination(comb, n, r, current + 1, i); // 나왔던 값이 또 나와도 되기 때문에 start 값 증가 X
		}
	}

	/**
	 * 순열
	 * 
	 * @param perm    순열의 결과를 답을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 * @param visited 해당 위치의 값을 뽑았는지 아닌지(방문 여부)를 확인하기 위한 배열
	 */
	public static void permutation(String[] perm, int n, int r, int current, boolean[] visited) {
		if (r == current) {
			System.out.println(Arrays.toString(perm));
			return;
		}
		for (int i = 0; i < n; i++) { // 뽑는 순서가 상관 없기 때문에 처음부터 뽑음
			if (!visited[i]) { // 방문 여부 판단
				visited[i] = true;
				perm[current] = BOX[i];
				permutation(perm, n, r, current + 1, visited);
				visited[i] = false;
			}
		}
	}

	/**
	 * 중복순열
	 * 
	 * @param perm    순열의 결과를 답을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 */
	public static void repeatedPermutation(String[] perm, int n, int r, int current) {
		if (r == current) {
			System.out.println(Arrays.toString(perm));
			return;
		}
		for (int i = 0; i < n; i++) {
			perm[current] = BOX[i];
			repeatedPermutation(perm, n, r, current + 1);
		}
	}
}
