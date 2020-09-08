/**
 * 
 */
package com.programmers.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : kimhyunjin
 * @CretaedAt : Sep 7, 2020
 * @주요 개념 :
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
//		int bridge_length = 2;
//		int weight = 10;
//		int[] truck_weights = { 7, 4, 5, 6 };

		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

		System.out.println("Result : " + solution(bridge_length, weight, truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int time = 0;

		Queue<Truck> truckQu = new LinkedList<>();
		Queue<Truck> entryTruckQu = new LinkedList<>();

		for (int i = 0; i < truck_weights.length; i++) {
			truckQu.offer(new Truck(truck_weights[i], 0));
		}

		Integer totalWeight = 0;
		while (!truckQu.isEmpty() || !entryTruckQu.isEmpty()) {
			time++;

			if (!entryTruckQu.isEmpty()) {
				if (time - entryTruckQu.peek().entry >= bridge_length) {
					Truck pass = entryTruckQu.poll();
					totalWeight -= pass.weight;
				}
			}

			if (!truckQu.isEmpty()) {
				Truck t = truckQu.peek();

				if (t.weight + totalWeight <= weight) {
					t.entry = time;
					entryTruckQu.offer(t);
					truckQu.poll();

					totalWeight += t.weight;
				}
			}

		}

		return time;
	}
}
