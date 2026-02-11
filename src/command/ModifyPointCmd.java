package command;

import geometry.Point;

public class ModifyPointCmd implements Command {

	private Point point;
	private Point newState; 
	private Point oldState; 
	
	
	public ModifyPointCmd(Point point, Point newState)
	{
		this.point=point;
		this.newState=newState;
	}

	public void execute() {
			oldState = point.clone();
			
			point.setXCoordinate(newState.getXCoordinate());
			point.setYCoordinate(newState.getYCoordinate());
			point.setBorderColor(newState.getBorderColor());
	}
	//iz new state ga vratimo u point
	public void unexecute() {
		
		point.setXCoordinate(oldState.getXCoordinate()); 
		point.setYCoordinate(oldState.getYCoordinate());
		point.setBorderColor(oldState.getBorderColor());
		
	}
	
	public String toString()
	{
		return "MODIFY--" + oldState.toString() + ";Color:" + oldState.getBorderColor().getRGB() + 
				">>" + newState.toString() + ";Color:" + newState.getBorderColor().getRGB();
				
	}

}
