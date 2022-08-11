package com.planto.assessment.demo;

import org.springframework.shell.Availability;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import com.planto.assessment.demo.command.BucketFillCommand;
import com.planto.assessment.demo.command.CreateCanvasCommand;
import com.planto.assessment.demo.command.DrawingLineCommand;
import com.planto.assessment.demo.command.DrawingRectangleCommand;
import com.planto.assessment.demo.model.Command;
import com.planto.assessment.demo.model.Point;


@ShellComponent 
public class CanvasShellController {
	int width;
	int height;
	char[][] canvas;
	
	private void executeCommand(Command command) {
    	command.execute(canvas);
	}
	    
    @ShellMethod(value = "create a new canvas of width w and height h", key = "C")
    public StringBuilder createCanvas(int w, int h) {
    	width = w;
    	height = h;
    	canvas = new char[height + 2][width + 2];
    	
    	Command command = new CreateCanvasCommand(width, height);
    	executeCommand(command);
    	return printCanvas();
	}
    
    @ShellMethod(value = "exit program", key = "Q")
    public void quit() {
        throw new ExitRequest();
    }

    @ShellMethod(value = "create a new line from (x1,y1) to (x2,y2)", key = "L")
    @ShellMethodAvailability({ "doesCanvasExist" })
    public StringBuilder drawNewLine(int x1, int y1, int x2, int y2) {   	
    	validateIsPointOutOfBoundary(x1, y1);
    	validateIsPointOutOfBoundary(x2, y2);
    	
    	Command command = new DrawingLineCommand(new Point(x1, y1), new Point(x2, y2));
    	executeCommand(command);
    	return printCanvas();
    }
    
    @ShellMethod(value = "create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2)", key = "R")
    @ShellMethodAvailability({ "doesCanvasExist" })
    public StringBuilder drawNewRectangle(int x1, int y1, int x2, int y2 ) {
    	validateIsPointOutOfBoundary(x1, y1);
    	validateIsPointOutOfBoundary(x2, y2);
    	
    	Point[][] lines = {
				{ new Point(x1, y1), new Point(x1, y2)},
				{ new Point(x1, y1), new Point(x2, y1)},
				{ new Point(x2, y2), new Point(x2, y1)},
				{ new Point(x2, y2), new Point(x1, y2)}
			};

		Command command = new DrawingRectangleCommand(lines);
		executeCommand(command);
    	return printCanvas();
    }
    
    @ShellMethod(value = "fill the entire area connected to (x,y) with \"colour\" c", key = "B")
    @ShellMethodAvailability({ "doesCanvasExist" })
    public StringBuilder bucketFill(int x, int y, char color) {
    	validateIsPointOutOfBoundary(x, y);
    	
    	Command command = new BucketFillCommand(new Point(x, y), color, width, height);
    	executeCommand(command);
    	return printCanvas();
    }
    
    public Availability doesCanvasExist() {
        return  width != 0 && height != 0 ? Availability.available() : Availability.unavailable("you should create a canvas first");
    }
    
    private void validateIsPointOutOfBoundary(int x, int y) {
    	if(x < 1 || x > width || y < 1 || y > height) {
    		throw new IllegalArgumentException("points out of boundary");
    	}
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


