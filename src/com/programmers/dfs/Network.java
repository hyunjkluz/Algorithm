/**
 * 
 */
package com.programmers.dfs;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 16, 2020
 * @주요 개념 :
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/43162
 */
class Graph {
	public LinkedList<Integer> adj[];

	public Graph(int n) {
		adj = new LinkedList[n];
		for (int i = 0; i < n; ++i)
			adj[i] = new LinkedList();
	}

	public void addEdge(int v, int c) {
		adj[v].add(c);
	}
}

public class Network {
	public static boolean[] visited;

	public static void main(String[] args) {
		int n = 8;
		int[][] computers = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };

		System.out.println("Result : " + solution(n, computers));

	}

	public static int solution(int n, int[][] computers) {
		Integer answer = 0;
		visited = new boolean[n];

		for (int i = 0; i < computers.length; i++) {
			if (!visited[i]) {
				System.out.println("[ " + i + " ]");
				DFS(i, computers);
				System.out.println();
				answer++;
			}
		}

		return answer;
	}

	public static void DFS(int v, int[][] computers) {
		visited[v] = true;

		for (int i = 0; i < computers[v].length; i++) {
			if (v != i && computers[v][i] == 1 && !visited[i]) {
				System.out.print(i + " ");
				visited[i] = true;
				DFS(i, computers);
			}
		}
	}

	public static int solution2(int n, int[][] computers) {
		Integer answer = 0;
		visited = new boolean[n];

		Graph graph = new Graph(n);

		for (int i = 0; i < computers.length; i++) {
			for (int j = 0; j < computers[i].length; j++) {
				if (computers[i][j] == 1) {
					graph.addEdge(i, j);
				}
			}
		}
		for (int i = 0; i < computers.length; i++) {
			if (!visited[i]) {
				DFS2(n, i, graph);
				answer++;
			}
		}

		return answer;
	}

	public static void DFS2(int n, int v, Graph g) {
		visited[v] = true;

		Iterator<Integer> i = g.adj[v].listIterator();
		while (i.hasNext()) {
			Integer network = i.next();

			if (!visited[network]) {
				visited[network] = true;
				DFS2(n, network, g);
			}
		}
	}

}
