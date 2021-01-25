/**
 * 
 */
package com.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author : kimhyunjin
 * @CretaedAt : Jan 22, 2021
 * @문제 링크 : https://www.acmicpc.net/problem/10775
 */
public class Main_10775 {
	static int G, P;
	static int[] parentGate;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 게이트 수
		G = Integer.parseInt(br.readLine());
		// 비행기 수
		P = Integer.parseInt(br.readLine());
		// 도착하는 비행기
		int[] airplanes = new int[P];
		// 부모 게이트를 저장하는 배열
		parentGate = new int[G + 1];

		for (int i = 1; i <= G; i++) {
			parentGate[i] = i;
		}

		for (int i = 0; i < P; i++) {
			airplanes[i] = Integer.parseInt(br.readLine());
		}

		int countPlane = 0;

		for (int airplane : airplanes) {
			int rootGate = findRootGate(airplane);

			if (rootGate != 0) {
				countPlane += 1;

				merge(rootGate, rootGate - 1);
			} else {
				// 도킹 실패 시 공항 바로 폐쇠
				break;
			}
		}

		System.out.println(countPlane);
	}

	// union-find 알고리즘
	public static void merge(int g1, int g2) {
		int p1 = findRootGate(g1);
		int p2 = findRootGate(g2);

		parentGate[p1] = p2;
	}

	public static int findRootGate(int g) {
		if (parentGate[g] == g) {
			return g;
		}
		return parentGate[g] = findRootGate(parentGate[g]);
	}

}
