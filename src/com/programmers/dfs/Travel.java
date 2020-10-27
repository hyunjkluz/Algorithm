/**
 * 
 */
package com.programmers.dfs;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 25, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/43164
 */
class Destination {
	public int idx;
	public String name;

	public Destination(int idx, String name) {
		this.idx = idx;
		this.name = name;
	}
}

public class Travel {
	public static List<String> routeList = new ArrayList<String>();
	public static boolean[] visited;
	public static StringBuilder route;

	public static void main(String[] args) {
		String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
				{ "ATL", "SFO" } };
		System.out.println(Arrays.toString(solution(tickets)));
	}

	public static String[] solution(String[][] tickets) {
		visited = new boolean[tickets.length];

		// 처음부터 정렬시켜서 찾으려 하지 말고 다 구한 뒤 정렬해서 찾자
		for (int i = 0; i < tickets.length; i++) {
			if (tickets[i][0].equals("ICN")) {
				route = new StringBuilder();
				route.append("ICN");
				visited[i] = true;
				dfs(route, 1, tickets[i][1], tickets);
				visited[i] = false;
			}
		}

		routeList.sort(null);

		return routeList.get(0).split(",");

	}

	public static void dfs(StringBuilder routes, int passed, String end, String[][] tickets) {
		routes.append("," + end);

		if (passed == tickets.length) {
			routeList.add(routes.toString());
			return;
		}

		for (int i = 0; i < tickets.length; i++) {
			if (end.equals(tickets[i][0]) && !visited[i]) {
				visited[i] = true;
				dfs(routes, passed + 1, tickets[i][1], tickets);
				visited[i] = false;

				route.replace(0, route.length(), route.substring(0, route.length() - 4));
			}
		}
	}

}
