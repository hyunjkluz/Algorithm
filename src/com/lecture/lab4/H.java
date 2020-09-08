package com.lecture.lab4;

public class H {

    int H(int n)
    {
        int i = 0;
        int sum = 0;

        if (n <= 1)
            return n;

        for (i = 0; i < n; i++)
            sum = sum + H(i);

        return sum;
    }
}
