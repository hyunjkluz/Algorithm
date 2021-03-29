package com.programmers.bp;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 03 - 13
 * @문제 이름 : 멀쩡한 사각형
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/62048
 * @참고 링크 : https://taesan94.tistory.com/55
 */
public class FineSquare {
  public static void main(String[] args) {
    System.out.println(solution(8, 12));
    System.out.println(solution(7, 14));
  }

  static long solution(int w, int h) {
    long wl = Long.parseLong(String.valueOf(w));
    long hl = Long.parseLong(String.valueOf(h));
    long gcd = getGCD(wl, hl);

    // 최대 공약수로 나눈 가로, 세로의 크기를 가지는 사각형을 몇번 반복할 것인가

    // 가로, 세로의 최대 공약수가 1인 사각형에서 잘라지는 사각형의 수 : w + h - 1
    // 최대 공약수만큼 반복해서
    // 전체 사각형에서 빼줌

    // gcd * ((w / gcd) + (h / gcd) - 1)
    // = w + h - gcd
    return wl * hl - (wl + hl - gcd);
  }

  // GCD = greatest common divisor
  static long getGCD(long x, long y) {
    long a = Math.max(x, y);
    long b = Math.min(x, y);

    while (b != 0) {
      long r = a % b;
      a = b;
      b = r;
    }

    return a;
  }
}