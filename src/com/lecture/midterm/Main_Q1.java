package com.lecture.midterm;

public class Main_Q1 {
    public static void main(String[] args) {
        int[] A = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(max_subArray(A, 0, A.length - 1));
    }

    public static int max_subArray(int[] A, int left, int right) {
        if (left == right)
            return A[left];
        int mid = (left + right) / 2;

        int leftMax = max_subArray(A, left, mid);
        int rightMax = max_subArray(A, mid + 1, right);
        int maxValue = (leftMax > rightMax) ? leftMax : rightMax;

        int lMaxValue = 0, rMaxValue = 0;
        int value = 0;

        for (int i = mid; i >= left; i--) {
            value += A[i];
            if (value > lMaxValue)
                lMaxValue = value;
        }
        value = 0;
        for (int i = mid + 1; i <= right; i++){
            value += A[i];
            if (value > rMaxValue)
                rMaxValue = value;
        }
        int totalMax = lMaxValue + rMaxValue;

        maxValue = (totalMax > maxValue) ? totalMax : maxValue;

        return maxValue;
    }

    public static int max_subArray_2(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            int local_max = 0;
            for(int j = i; j < A.length; j++) {
                local_max += A[j];
                if (local_max > max)
                    max = local_max;
            }
        }
        return max;
    }
}
