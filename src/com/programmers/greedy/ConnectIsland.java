/**
 * 
 */
package com.programmers.greedy;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : kimhyunjin
 * @CretaedAt : Dec 16, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42861
 */
class Node implements Comparable<Node> {
	int from, to, cost;

	public Node(int n1, int n2, int cost) {
		this.from = n1;
		this.to = n2;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}

	@Override
	public String toString() {
		return "Node [n1=" + from + ", n2=" + to + ", cost=" + cost + "]";
	}

}

public class ConnectIsland {
	static int[] parent;

	public static void main(String[] args) {
		System.out
				.println(solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } }));

	}

	public static int solution(int n, int[][] costs) {
		int answer = 0;
		ArrayList<Node> nodes = new ArrayList<>();
		parent = new int[n + 1];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < costs.length; i++) {
			nodes.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
		}

		// 비용 오름차순 정렬
		Collections.sort(nodes);

		for (Node node : nodes) {
			int fromRoot = findParent(node.from);
			int toRoot = findParent(node.to);

			if (fromRoot == toRoot) {
				continue;
			}

			union(node.from, node.to);
			answer += node.cost;
		}

		return answer;
	}

	public static int findParent(int n) {
		if (parent[n] == n) {
			return parent[n];
		}

		parent[n] = findParent(parent[n]);
		return parent[n];
	}

	public static void union(int from, int to) {
		int fromRoot = findParent(from);
		int toRoot = findParent(to);

		if (fromRoot != toRoot) {
			parent[toRoot] = from;
		}
		return;

	}

}
