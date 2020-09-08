package com.lecture.lab2;
import java.util.Scanner;

public class Main_Q2 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ī�带 �Է¹ް� answers �迭�� ����� �� ���� �����غ���
		int[][] cards = new int[4][];
		String[] answers;
		
		for (int i = 0; i < cards.length; i++) {
			System.out.print("Card " + i + "�Է�");
			inputCard(cards[i]);
		}
		
		System.out.println("16���� ���� �� �����ϱ�");
		
		for (int i = 0; i < cards.length; i++) {
			System.out.print("card " + i);
			printCard(cards[i]);
			
			String answer = sc.next();
		}
		
	}
	
	public static void printCard(int[] c) {
		for(int i = 0; i < c.length; i++)
			System.out.print(c[i] + "  ");
		System.out.println();
	}
	
	public static void inputCard(int[] c) {
		for (int i = 0; i < 8; i++) {
			c[i] = sc.nextInt();
		}
		return;
	}

	public static void sortCard(int[] c) {
		//�����ϱ�
	}
	
	public static void makeAnswer(int[][] cards) {
//		for (int i = 0; i)
	}
}
