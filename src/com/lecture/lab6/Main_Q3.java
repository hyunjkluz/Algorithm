package com.lecture.lab6;

import java.util.Scanner;

public class Main_Q3 {
    final public static int SYNC_CLOCKS = 16;           //      ����ȭ�� �ð������ �̷�� �ִ� �ð��� ����
    final public static int MAX_SWITCH = 10;            //      ����ȭ�� �ð������ �����ϴ� ����ġ�� ����
    final public static int MAX_REACTION = 6;           //      ����ġ�� �����ϴ� �ִ� �ð��� ����
    final public static int MAX_PUSH = MAX_SWITCH * 3;  //      ������ ��� ���÷� ����µ��� �� ������ ���� �ʴ´�.

    private static int minCount; //      �۾��� �߻��� ���� ���� Ƚ���� ��


    public static int[][] switches = new int[MAX_SWITCH][MAX_REACTION];
    public static int[] clocks = new int[16];
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] argv) {
        init();
        solve();
    }

    public static void init() {
        //����ġ �ʱ�ȭ
        for (int i = 0; i < MAX_SWITCH; i++) {
            int num = sc.nextInt();
            int cnt = sc.nextInt();

            for (int j = 0; j < MAX_REACTION; j++) {
                if (cnt-- > 0) {
                    switches[num][j] = sc.nextInt();
                } else {
                    switches[num][j] = -1;
                }
            }
            //System.out.println(num + "��° ����ġ �Է� �Ϸ�");
        }
        //System.out.println("�ð� �ʱ�ȭ");
        //�ð� �ʱ�ȭ
        for (int i = 0; i < SYNC_CLOCKS; i++) {
            clocks[i] = sc.nextInt();
        }

    }

    public static void solve() {
        minCount = MAX_PUSH + 1;
        backTracking(0, 0);
        if (minCount == MAX_PUSH + 1) {
            System.out.println("-1");
        }
        else {
            System.out.println(minCount);
        }

    }

    private static void backTracking(final int index, final int count) {
        if (isAllNoon()) {
            minCount = count;
            return;
        }
        if (count > minCount || count == MAX_PUSH || index >= MAX_SWITCH)
            return;
        for (int i = 0; i < 4; i++) {
            backTracking(index + 1, count + i);
            pushSwitch(index);
        }
    }

    private static void pushSwitch(int switchNum) {
        for(int i = 0; i < MAX_REACTION; i++) {
            //����ġ �� ���� ture�� ��� -> ������ �ð谡 �ִ� -> �ٽ� �ǵ��������
            if(switches[switchNum][i] >= 0) {
                clocks[switches[switchNum][i]] += 3;

                if(clocks[switches[switchNum][i]] > 12)
                    clocks[switches[switchNum][i]] = 3;
            }
            else
                break;
        }
    }

    private static boolean isAllNoon() {
        for (int i = 0; i < SYNC_CLOCKS; i++) {
            if (clocks[i] != 12) {
                return false;
            }
        }
        return true;
    }

}
