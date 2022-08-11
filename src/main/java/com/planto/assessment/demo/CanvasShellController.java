package com.planto.assessment.demo;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.shell.Availability;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent 
public class CanvasShellController {
	int width;
	int height;
	char[][] canvas;
    
    @ShellMethod(value = "create a new canvas of width w and height h", key = "C")
    public StringBuilder createCanvas(int w, int h) {
    	width = w;
    	height = h;
    	canvas = new char[height + 2][width + 2];
    	initilizeCanvas();
    	return printCanvas();
	}
    

    @ShellMethod(value = "exit program", key = "Q")
    public void quit() {
        throw new ExitRequest();
    }

    @ShellMethod(value = "create a new line from (x1,y1) to (x2,y2)", key = "L")
    @ShellMethodAvailability({ "doesCanvasExist" })
    public StringBuilder drawNewLine(int x1, int y1, int x2, int y2) {
    	if(x1 != x2 && y1 != y2) {
    		throw new IllegalArgumentException("should be either a horizontal or vertical line");
    	}
    	
    	validteIsPointOutOfBundary(x1, y1);
    	validteIsPointOutOfBundary(x2, y2);
    	drawLine(x1, y1, x2, y2, 'x');
    	return printCanvas();
    }
    
    @ShellMethod(value = "create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2)", key = "R")
    @ShellMethodAvailability({ "doesCanvasExist" })
    public StringBuilder drawNewRectangle(int x1, int y1, int x2, int y2 ) {
    	validteIsPointOutOfBundary(x1, y1);
    	validteIsPointOutOfBundary(x2, y2);
    	drawLine(x1, y1, x2, y1, 'x');
    	drawLine(x1, y1, x1, y2, 'x');
    	drawLine(x2, y2, x2, y1, 'x');
    	drawLine(x2, y2, x1, y2, 'x');
    	return printCanvas();
    }
    
    @ShellMethod(value = "fill the entire area connected to (x,y) with \"colour\" c", key = "B")
    @ShellMethodAvailability({ "doesCanvasExist" })
    public StringBuilder bucketFill(int x, int y, char color) {
    	validteIsPointOutOfBundary(x, y);
    	
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
    	
    	return printCanvas();
    }
    
    public Availability doesCanvasExist() {
        return  width != 0 && height != 0 ? Availability.available() : Availability.unavailable("you should create a canvas first");
    }
    
    private void validteIsPointOutOfBundary(int x, int y) {
    	if(x < 1 || x > width || y < 1 || y > height) {
    		throw new IllegalArgumentException("points out of boundary");
    	}
    }
    
    private void drawLine(int x1, int y1, int x2, int y2, char color) {
    	boolean isVerticalLine = x1 == x2;
    	
    	if(isVerticalLine) {
    		int top = Math.max(y1, y2);
    		int bottom = Math.min(y1, y2);
    		
    		while(top >= bottom) {
    			canvas[top][x1] = color;
    			canvas[bottom][x1] = color;
    			top--;
    			bottom++;
    		}
    	} else { // Horizontal line
    		int right = Math.max(x1, x2);
    		int left = Math.min(x1, x2);
    		
    		while(right >= left) {
    			canvas[y1][left] = color;
    			canvas[y1][right] = color;
    			right--;
    			left++;
    		}
    	}
    }
    
    private void initilizeCanvas() {
    	drawLine(0, 0, width + 1, 0, '-'); // top-left to top-right
    	drawLine(0, 1, 0, height, '|'); // top-left to bottom-left
    	drawLine(0, height + 1, width + 1, height + 1, '-'); // bottom-left to bottom right
    	drawLine(width + 1, height, width + 1, 1, '|'); // bottom-right to top-right
    }
    
    private StringBuilder printCanvas() {
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i = 0; i < height + 2; i++) {
    		for(int j = 0; j < width + 2; j++) {
    			sb.append(canvas[i][j] == 0 ? " " : canvas[i][j]);
    		}
    		
    		sb.append("\n");
    	}
    	
    	return sb;
    }
}
