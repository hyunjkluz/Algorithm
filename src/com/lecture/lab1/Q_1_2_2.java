package com.lecture.lab1;


import java.util.Scanner;

public class Q_1_2_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int x = scan.nextInt();
		int y = scan.nextInt();
		int carry = 0;
		int cnt = 0;
		int[] binaryX = new int[50];
		int binaryXLength = toBinary(x, binaryX);
		int[] binaryY = new int[50];
		int binaryYLength = toBinary(y, binaryY);
		int[] binarySum = new int[50];
		
		int maxLength = 0;
		
//		printBinary(binaryX, binaryXLength);
//		printBinary(binaryY, binaryYLength);
		
		if (binaryXLength >= binaryYLength) {
			maxLength = binaryXLength;
		} else {
			maxLength = binaryYLength;
		}
		
		int i = 0;
		while (i <= binaryXLength || i <= binaryYLength ) {
			carry = binaryX[i] + binaryY[i] + carry;
			
			if (carry < 2) {
				binarySum[i] = carry;
				carry = 0;
			} else {
				binarySum[i] = 0;
				carry = 1;
				cnt++;
			}
			i++;
		}
		binarySum[i] = carry; 
		
		//printBinary(binarySum, i);
		System.out.println(cnt);;

	}
	
	public static int toBinary(int n, int bn[]) {
		int change = 0;
		int cnt = 0;
		int i = 0;
		
		while (n != 1) {
			bn[i++] = n % 2;
			n = n / 2;			
		}
		bn[i] = n; //�������� ����Ǿ�����
		
		return i;
		
	}

	public static void printBinary(int[] bn, int length) {
		for (int i = length; i >= 0; i--) {
			System.out.print(bn[i] + " ");
		}
		System.out.println();
	}
}
