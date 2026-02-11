package command;

import geometry.Rectangle;

public class ModifyRectangleCmd implements Command {

	private Rectangle rectangle; //stari
	private Rectangle newState; //kakav zelimo
	private Rectangle oldState; //pomocni (moramo da mu dodelimo vrednosti inace dobijam null pointer exception
	
	
	public ModifyRectangleCmd(Rectangle rectangle, Rectangle newState)
	{
		this.rectangle = rectangle;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		
		oldState = rectangle.clone();
		
		rectangle.getUpperLeftPoint().setXCoordinate(newState.getUpperLeftPoint().getXCoordinate());
		rectangle.getUpperLeftPoint().setYCoordinate(newState.getUpperLeftPoint().getYCoordinate());
		rectangle.setHeight(newState.getHeight());
		rectangle.setWidth(newState.getWidth());
		rectangle.setBorderColor(newState.getBorderColor());
		rectangle.setAreaColor(newState.getAreaColor());

		
	}

	@Override
	public void unexecute() {
		rectangle.getUpperLeftPoint().setXCoordinate(oldState.getUpperLeftPoint().getXCoordinate());
		rectangle.getUpperLeftPoint().setYCoordinate(oldState.getUpperLeftPoint().getYCoordinate());
		rectangle.setHeight(oldState.getHeight());
		rectangle.setWidth(oldState.getWidth());
		rectangle.setBorderColor(oldState.getBorderColor());
		rectangle.setAreaColor(oldState.getAreaColor());
	}
	public String toString()
	{
		return "MODIFY--" + oldState.toString() + ";Border color:" + oldState.getBorderColor().getRGB() + ";Area color:" + oldState.getAreaColor().getRGB()
				+ ">>" + newState.toString() + ";Border color:" + newState.getBorderColor().getRGB() + ";Area color:" + newState.getAreaColor().getRGB();
	}
}
