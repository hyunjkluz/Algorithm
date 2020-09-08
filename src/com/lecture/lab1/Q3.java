package com.lecture.lab1;


import java.util.Scanner;

public class Q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c = 1;
		int t = n;
		
		while (!hasAllOnes(t)) {
			c++;
			t = n * c; //�׳� ���� ���ڸ� �� �ѹ� ���ϸ� ��
		}
		
		System.out.println(countOnes(t));

	}
	
	public static boolean hasAllOnes(int n) {
		
		while (n != 0) {
			if (n % 10 != 1)
				return false;
			
			n = n/10;
		}
		return true;
	}
	
	public static int countOnes(int n) {
		int cnt = 0;
		
		while( n != 0) {
			n /= 10;
			cnt++;
		}
		
		return cnt;
	}
		
}

		