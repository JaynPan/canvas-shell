package com.planto.assessment.demo.command;

import com.planto.assessment.demo.DrawingLine;
import com.planto.assessment.demo.model.Command;
import com.planto.assessment.demo.model.Point;

public class DrawingLineCommand extends DrawingLine implements Command {
	private Point point1;
	private Point point2;
	
	public DrawingLineCommand(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}
	
	@Override
	public void execute(char[][] currentCanvas) {
		drawLine(currentCanvas, point1, point2, 'x');
	}
}