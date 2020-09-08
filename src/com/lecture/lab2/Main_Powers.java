package com.lecture.lab2;

public class Main_Powers {
    public static String[] data = {"a", "b", "c", "d", "e", "f"};
    public static boolean[] include = new boolean[data.length];
    public static int n = 4;  //���� � ��������

    public static void main(String[] args) {
        powerSet(0);

    }

    public static void powerSet(int k) {    //������
        if (k == data.length) {
            for(int i = 0; i < data.length; i++) {
                if (include[i])
                    System.out.print(data[i] + "\t");
            }
            System.out.println();
            return;
        } else {
            include[k] = false;
            powerSet(k+1);
            include[k] = true;
            powerSet(k+1);
        }
    }

    public static void perm(int k) {    //����
        if (k == n) {
            for (int i = 0; i < n; i++)
                System.out.print(data[i]);
            System.out.println();
            return;
        }
        for (int i = k; i < n; i++) {
            swap(k, i);
            perm(k+1);
            swap(k, i);
        }
    }

    public static void swap(int i, int k) {
        String temp = data[i];
        data[i] = data[k];
        data[k] = temp;
        return;
    }
}
