package com.lecture.lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_Q3 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number : ");
        String num = input.next();

        ArrayList<Integer> arr = new ArrayList<Integer>();
        reverse(num, arr);

        ArrayList<Integer> sum = new ArrayList<Integer>();
        for(int i = 0; i < arr.size(); i++) { sum.add(arr.get(i)); }

        while(!hasAllOnes(sum))
        {
            int carry = 0;
            int i;

            for(i = 0; i < arr.size(); i++)
            {
                int tmp = (arr.get(i) + sum.get(i) + carry)%10;
                carry = (arr.get(i) + sum.get(i) + carry)/10;
                sum.set(i, tmp);
            }

            for(; i < sum.size(); i++)
            {
                if(carry == 0)
                    break;
                int tmp = (sum.get(i) + carry)%10;
                carry = (sum.get(i)+carry)/10;
                sum.set(i, tmp);
            }

            if(carry > 0)
                sum.add(carry);
        }

        System.out.println(sum.size());

    }

    public static void reverse(String num, ArrayList<Integer> arr)
    {
        for(int i = num.length()-1; i >= 0; i--)
        {
            arr.add(num.charAt(i)-'0');
        }
    }

    public static boolean hasAllOnes(ArrayList<Integer> arr)
    {
        for(int i = 0; i < arr.size(); i++)
        {
            if(arr.get(i) != 1)
                return false;
        }
        return true;
    }
}
