/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 8, 2020
 * @문제 링크 : https://www.acmicpc.net/problem/16964
 */
public class Main_12869 {
	public static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] lines = new ArrayList[N + 1];
		Queue<Integer> order = new LinkedList<Integer>();

		for (int i = 0; i < N + 1; i++)
			lines[i] = new ArrayList<Integer>();

		for (int i = 0; i < N - 1; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			lines[line[0]].add(line[1]);
			lines[line[1]].add(line[0]);
		}

		int[] predict = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.stream(predict).forEach(x -> order.add(x));

		if (order.poll() != 1) {
			System.out.println(0);
		} else {
			System.out.println(solution(lines, order) ? 1 : 0);
		}

	}

	public static boolean solution(ArrayList<Integer>[] lines, Queue<Integer> order) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[N + 1];
		Set<Integer> possibleNode = new HashSet<Integer>();
		stack.add(1);

		while (!stack.isEmpty() && !order.isEmpty()) {
			possibleNode.clear();
			int pick = stack.pop();
			visited[pick] = true;

			// 해당 노드에서 이동 가능한 노드 담음
			for (int node : lines[pick]) {
				if (!visited[node]) {
					possibleNode.add(node);
				}
			}
			System.out.printf("pick : %d, poll : %d\n", pick, order.peek());
			System.out.println(possibleNode.toString());

			if (possibleNode.isEmpty()) {
				stack.add(order.poll());
				continue;
			}

			// 올바른 깊이 우선 탐색으로 되어있다면 이 다음 노드는 가능한 범위내에 있어야함
			if (possibleNode.contains(order.peek())) {
				// 가능한 노드 범위 안에 있다면 = 순서가 맞았으면 해당 순서의 깊이 우선 탐색 시작
				stack.add(order.poll());
			} else {
				return false;
			}

		}

		return true;

	}

}
