package command;

import geometry.Circle;
import geometry.Point;

public class ModifyCircleCmd implements Command {

	private Circle circle;
	private Circle newState;
	private Point p = new Point(0,0);
	private Circle oldState = new Circle(p,0);
	
	public ModifyCircleCmd (Circle circle, Circle newState)
	{
		this.circle = circle;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		oldState.getCenter().setXCoordinate(circle.getCenter().getXCoordinate());
		oldState.getCenter().setYCoordinate(circle.getCenter().getYCoordinate());
		try {
			oldState.setRadius(circle.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldState.setBorderColor(circle.getBorderColor());
		oldState.setAreaColor(circle.getAreaColor());
		
		circle.getCenter().setXCoordinate(newState.getCenter().getXCoordinate());
		circle.getCenter().setYCoordinate(newState.getCenter().getYCoordinate());
		try {
			circle.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setBorderColor(newState.getBorderColor());
		circle.setAreaColor(newState.getAreaColor());
		
	}

	@Override
	public void unexecute() {
		
		circle.getCenter().setXCoordinate(oldState.getCenter().getXCoordinate());
		circle.getCenter().setYCoordinate(oldState.getCenter().getYCoordinate());
		try {
			circle.setRadius(oldState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		circle.setBorderColor(oldState.getBorderColor());
		circle.setAreaColor(oldState.getAreaColor());
	}
	
	public String toString()
	{
		return "MODIFY new values: " + newState.toString() + " Border color: " + newState.getBorderColor().toString() 
				+ " Area color: " + newState.getAreaColor().toString();	
	}

}
