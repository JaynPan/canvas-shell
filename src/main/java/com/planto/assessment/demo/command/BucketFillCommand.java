package com.planto.assessment.demo.command;

import java.util.LinkedList;
import java.util.Queue;

import com.planto.assessment.demo.model.Command;
import com.planto.assessment.demo.model.Point;

public class BucketFillCommand implements Command {
	private Point point;
	private char color;
	private int width;
	private int height;
	
	public BucketFillCommand(Point point, char color, int width, int height) {
		this.point = point;
		this.color = color;
		this.width = width;
		this.height = height;
	}

	@Override
	public void execute(char[][] canvas) {
		int x = point.getPoint()[0];
		int y = point.getPoint()[1];
    	char target = canvas[y][x];
    	Queue<int[]> queue = new LinkedList<>();
    	
    	int[] task = {y, x};
    	queue.add(task);

    	while(!queue.isEmpty()) {
    		int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    		int[] dequeue = queue.poll();
    		int row = dequeue[0];
    		int col = dequeue[1];
    		
    		canvas[row][col] = color;
    		
    		for(int i = 0; i < neighbors.length; i++) {
    			int offsetY = neighbors[i][0] + row;
    			int offsetX = neighbors[i][1] + col;
    			    			
    			if(canvas[offsetY][offsetX] != target || offsetX < 1 || offsetX > width || offsetY < 1 || offsetY > height) {
    				continue;
    			}
    			
    			int[] newTask = {offsetY, offsetX};
    			queue.add(newTask);
    		}
    	}
	}
}