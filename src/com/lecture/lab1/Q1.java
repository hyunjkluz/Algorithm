package com.lecture.lab1;


import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int x = scan.nextInt();
		int y = scan.nextInt();
		int carry = 0;
		int cnt = 0;
		
		while (x != 0 || y != 0) {
			int s = x % 10;
			int t = y % 10;
			
			carry = ( s + t + carry) / 10;
			
			if (carry == 1)
				cnt++;
			
			x /= 10; //���� (�������� ����°� ���ϱ�)
			y /= 10;			
		}
		
		System.out.println(cnt);
	}
	
	//2���� ��ȯ �Լ�
//	public static int toBinary(int n) {
//		int change = 0;
//		int cnt = 0;
//		
//		while () {
//			//2�� ������ ������
//			//2�� ���� ���� �� ����
//			//�������� �� Ƚ���� �þ ���� * 10
//			change = ( n % 2 ) * ()
//		}
//		
//	}

}
