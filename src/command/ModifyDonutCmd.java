package command;

import geometry.Donut;
import geometry.Point;

public class ModifyDonutCmd implements Command {

	private Donut donut;
	private Donut newState;
	private Point p1 = new Point(0,0);
	private Donut oldState = new Donut(p1,0,0);
	
	public ModifyDonutCmd (Donut donut, Donut newState)
	{
		this.donut = donut;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		oldState.getCenter().setXCoordinate(donut.getCenter().getXCoordinate());
		oldState.getCenter().setYCoordinate(donut.getCenter().getYCoordinate());
		try {
			oldState.setRadius(donut.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldState.setInnerRadius(donut.getInnerRadius());
		oldState.setBorderColor(donut.getBorderColor());
		oldState.setAreaColor(donut.getAreaColor());
		
		donut.getCenter().setXCoordinate(newState.getCenter().getXCoordinate());
		donut.getCenter().setYCoordinate(newState.getCenter().getYCoordinate());
		try {
			donut.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(newState.getInnerRadius());
		donut.setBorderColor(newState.getBorderColor());
		donut.setAreaColor(newState.getAreaColor());
		
	}

	@Override
	public void unexecute() {
		
		donut.getCenter().setXCoordinate(oldState.getCenter().getXCoordinate());
		donut.getCenter().setYCoordinate(oldState.getCenter().getYCoordinate());
		try {
			donut.setRadius(oldState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		donut.setInnerRadius(oldState.getInnerRadius());
		donut.setBorderColor(oldState.getBorderColor());
		donut.setAreaColor(oldState.getAreaColor());
	}
	public String toString()
	{
		return "MODIFY old values: " + donut.toString() + " Border Color: " + donut.getBorderColor().getRGB() + " Area Color: " + donut.getAreaColor().getRGB()
				+ ", new values: " + newState.toString() + " Border Color: " + newState.getBorderColor().getRGB() + " Area Color: " + newState.getAreaColor().getRGB();
	}

}
