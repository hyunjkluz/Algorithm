package com.baekjoon.A_B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.*;

public class Main_15552 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String max = br.readLine();
		int t = Integer.parseInt(max);

		if (t > 0 && t <= 1000000) {
			for (int i = 0; i < t; i++) {
				String[] arr = br.readLine().split(" ");
				int a = Integer.parseInt(arr[0]);
				int b = Integer.parseInt(arr[1]);

				if (a > 0 && a <= 1000 && b > 0 && b <= 1000) {
					bw.write(a + b + "\n");
				}

			}
		}

		bw.flush();
		br.close();
		bw.close();
	}
}
