/**
 * 
 */
package com.programmers.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 7, 2020
 * @문제 링크 : https://programmers.co.kr/learn/courses/30/lessons/42583
 */
class Truck {
	public Integer weight;
	public Integer entry;

	public Truck(Integer weight, Integer entry) {
		this.weight = weight;
		this.entry = entry;
	}
}

public class TruckBridge {
	public static void main(String[] args) {
		// int bridge_length = 2;
		// int weight = 10;
		// int[] truck_weights = { 7, 4, 5, 6 };

		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

		System.out.println("Result : " + solution(bridge_length, weight, truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int time = 0;

		Queue<Truck> truckQu = new LinkedList<>();
		Queue<Truck> entryTruckQu = new LinkedList<>();

		// 다리를 지나야 하는 트럭을 큐에 넣음
		for (int i = 0; i < truck_weights.length; i++) {
			truckQu.offer(new Truck(truck_weights[i], 0));
		}

		Integer totalWeight = 0;
		while (!truckQu.isEmpty() || !entryTruckQu.isEmpty()) {
			time++;

			// 다리를 지나는 트럭이 이 있을 때
			if (!entryTruckQu.isEmpty()) {
				// 현재 시간 - 트럭이 다리로 들어온 시간 >= 다리 길이
				if (time - entryTruckQu.peek().entry >= bridge_length) {
					// 다리를 지나는 큐에서 빼고
					Truck pass = entryTruckQu.poll();
					// 현재 다리의 무게에서 트럭의 무게 뺌
					totalWeight -= pass.weight;
				}
			}

			// 다리를 지나려는 트럭이 아직 남아있을 때
			if (!truckQu.isEmpty()) {
				Truck t = truckQu.peek();

				// 다리에 진입하려는 트럭의 무게 + 현재 다리 위의 무게 <= 다리가 들 수 있응 최고 무게
				// 트럭이 다리 건널 수 있음
				if (t.weight + totalWeight <= weight) {
					// 트럭 출입 시간 초기화
					t.entry = time;
					// 다리 위의 무게에 크럭 무게 더함
					totalWeight += t.weight;
					// 대기 큐에서 빼서 다리 위에 있는 큐에 넣고
					entryTruckQu.offer(truckQu.poll());
				}
			}

		}

		return time;
	}
}
