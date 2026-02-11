package command;

import geometry.Circle;

public class ModifyCircleCmd implements Command {

	private Circle circle;
	private Circle newState;
	private Circle oldState;
	
	public ModifyCircleCmd (Circle circle, Circle newState)
	{
		this.circle = circle;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		
		oldState = circle.clone();
		
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
		return "MODIFY--" + oldState.toString() + ";Border color:" + oldState.getBorderColor().getRGB() + ";Area color:" + oldState.getAreaColor().getRGB()
			+ ">>" + newState.toString() + ";Border color:" + newState.getBorderColor().getRGB() + ";Area color:" + newState.getAreaColor().getRGB();
	}

}
