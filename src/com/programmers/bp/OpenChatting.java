/**
 * 
 */
package com.programmers.bp;

import java.util.*;

/**
 * @author : kimhyunjin
 * @CretaedAt : Oct 5, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42888
 */

public class OpenChatting {

	public static void main(String[] args) {
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		System.out.println(solution(record).toString());
	}

	public static String[] solution(String[] record) {
		List<String> answer = new ArrayList<String>();
		HashMap<String, String> userList = new HashMap<String, String>();

		for (String log : record) {
			String[] logs = log.split(" ");
			String nickName = null;

			if (logs[0].equals("Enter") || logs[0].equals("Change")) {
				nickName = userList.putIfAbsent(logs[1], logs[2]);
				if (nickName != null) {
					userList.replace(logs[1], logs[2]);
				}
			}
			
		}

		for (String log : record) {
			String[] logs = log.split(" ");

			switch (logs[0]) {
			case "Enter":
				answer.add(userList.get(logs[1]) + "님이 들어왔습니다.");
				break;
			case "Leave":
				answer.add(userList.get(logs[1]) + "님이 나갔습니다.");
				break;
			}
		}

		return answer.stream().toArray(String[]::new);
	}

}
