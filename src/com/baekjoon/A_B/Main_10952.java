package com.baekjoon.A_B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main_10952 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		System.out.println(new BigInteger(arr[0]).add(new BigInteger(arr[1])));
		br.close();
	}

	public static void main2() throws Exception {
		Scanner sc = new Scanner(System.in);
		
		BigInteger a = sc.nextBigInteger();
		BigInteger b = sc.nextBigInteger();
		System.out.println(a.add(b));
		sc.close();
		
	}
}
