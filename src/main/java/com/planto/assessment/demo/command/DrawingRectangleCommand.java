package com.planto.assessment.demo.command;

import com.planto.assessment.demo.DrawingLine;
import com.planto.assessment.demo.model.Command;
import com.planto.assessment.demo.model.Point;

public class DrawingRectangleCommand extends DrawingLine implements Command {
	private Point[][] points;
	
	public DrawingRectangleCommand(Point[][] points) {
		this.points = points;
	}
	
	@Override
	public void execute(char[][] currentCanvas) {
		for(int i = 0; i < points.length; i++) {
			drawLine(currentCanvas, points[i][0], points[i][1], 'x');
		}
	}
}