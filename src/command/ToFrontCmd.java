package command;

import geometry.Point;
import mvc.DrawingController;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {

	public DrawingModel model;
	public DrawingController controller;
	public Point p;
	
	@Override
	//execute ce biti da ga u listi pomerimo za jedan indeks
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub

	}

}
