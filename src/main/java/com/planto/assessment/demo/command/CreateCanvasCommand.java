package com.planto.assessment.demo.command;

import com.planto.assessment.demo.DrawingLine;
import com.planto.assessment.demo.model.Command;
import com.planto.assessment.demo.model.Point;

public class CreateCanvasCommand extends DrawingLine implements Command {
	private int width;
	private int height;
	
	public CreateCanvasCommand(int width, int height) {
    	if(width < 1 || height < 1) {
    		throw new IllegalArgumentException("canvas width or height shouold greater than 0");
    	}
		
		this.width = width;
		this.height = height;
	}

	@Override
	public void execute(char[][] canvas) {
    	Point[][] horizontalLines = {
    			{new Point(0, 0), new Point(width + 1, 0)}, // top-left to top-right
    			{new Point(0, height + 1), new Point(width + 1, height + 1)}, // bottom-left to bottom right
    		};
    	
    	Point[][] verticalLines = {
    			{new Point(0, 1), new Point(0, height)}, // top-left to bottom-left
    			{new Point(width + 1, height), new Point(width + 1, 1)}, // bottom-right to top-right
    	};
    	
    	for(int i = 0; i < horizontalLines.length; i++) {
    		drawLine(canvas, horizontalLines[i][0], horizontalLines[i][1], '-');
    	}
    	
    	for(int i = 0; i < verticalLines.length; i++) {
    		drawLine(canvas, verticalLines[i][0], verticalLines[i][1], '|');
    	}
	}
}