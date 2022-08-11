package com.planto.assessment.demo;

import com.planto.assessment.demo.model.Point;

public class DrawingLine {
    public void drawLine(char[][] canvas, Point point1, Point point2, char color) {
    	int x1 = point1.getPoint()[0];
    	int y1 = point1.getPoint()[1];
    	int x2 = point2.getPoint()[0];
    	int y2 = point2.getPoint()[1];
    	
    	if(x1 != x2 && y1 != y2) {
    		throw new IllegalArgumentException("should either be vertical or horizontal line");
    	}
    	
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
}
