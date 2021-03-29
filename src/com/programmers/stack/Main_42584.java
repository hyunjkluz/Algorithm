package com.programmers.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author : kimhyunjin
 * @CretaedAt : 2021 - 02 - 27
 * @문제 이름 : 주식 가격
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42584
 * @참고 자료 : https://codevang.tistory.com/313
 */
public class Main_42584 {
  public static void main(String[] args) {
    System.out.println(Arrays.toString(solution(new int[] { 1, 2, 3, 2, 3 })));
  }

  static int[] solution(int[] prices) {
    int[] answer = new int[prices.length];
    // stack[0] : 주식 값, stack[1] : 해당 주식 값이 있는 초
    Stack<int[]> stack = new Stack<int[]>();

    for (int i = prices.length - 2; i >= 0; i--) {
      // 다음 주식 값이 현재보다 작다 = 주식 값이 떨어진다
      if (prices[i] > prices[i + 1]) {
        // 주식 값 떨어진다 = 유지 1초
        answer[i] = 1;
        // 본인보다 작은 값 스택에 넣음
        // 작은 숫자의 인덱스만 알면 됨
        stack.add(new int[] { prices[i + 1], i + 1 });
      } else {
        // 다음 주식 값이 현재와 같거나 작을 때
        // = 주식 시간 유지
        // = 이보다 작아지는 때를 알아내자

        // 현재보다 주식 값이 떨어질 때 까지 stack 에서 빼기
        while (!stack.isEmpty() && stack.peek()[0] >= prices[i]) {
          stack.pop();
        }

        // 스택이 비었다 = 현재 값 뒤로 작아지는 값 없음
        if (stack.isEmpty()) {
          answer[i] = prices.length - i - 1;
        } else {
          // 비어있지 않다 = 현재보다 낮은 숫자가 남아있다
          // 해당 인덱스(초)와 현재의 인덱스(초)를 이용해 계산
          answer[i] = stack.peek()[1] - i;
        }
      }
    }

    return answer;
  }

}
