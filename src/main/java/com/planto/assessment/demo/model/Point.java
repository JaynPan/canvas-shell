package com.planto.assessment.demo.model;

public class Point {
	private int[] point = new int[2];

	public Point(int x, int y) {
		point[0] = x;
		point[1] = y;
	}
	
	public int[] getPoint() {
		return point;
	}
}
