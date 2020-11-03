/**
 * 
 */
package com.baekjoon.dfsbfs;

import java.awt.Point;

/**
 * @author : kimhyunjin
 * @CretaedAt : Nov 3, 2020
 * @문제 링크 : 
 */
public class Points {
	Point red, blue;
	int turn;
	StringBuilder route;

	public Points(Point red, Point blue, int turn) {
		this.red = red;
		this.blue = blue;
		this.turn = turn;
		this.route = new StringBuilder();
	}

	public Points(Point red, Point blue, int turn, String route) {
		this.red = red;
		this.blue = blue;
		this.turn = turn;
		this.route = new StringBuilder().append(route);
	}

	public int getTurnPlus1() {
		return this.turn + 1;
	}

	public void addRoute(char dirChar) {
		this.route.append(dirChar);
	}

}
