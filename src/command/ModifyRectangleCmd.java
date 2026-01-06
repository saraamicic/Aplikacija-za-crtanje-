package command;

import geometry.Point;
import geometry.Rectangle;

public class ModifyRectangleCmd implements Command {

	private Rectangle rectangle; //stari
	private Rectangle newState; //kakav zelimo
	
	private Point p1 = new Point(0,0);
	private Rectangle oldState = new Rectangle(p1,0,0); //pomocni (moramo da mu dodelimo vrednosti inace dobijam null pointer exception
	
	
	public ModifyRectangleCmd(Rectangle rectangle, Rectangle newState)
	{
		this.rectangle = rectangle;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		oldState.getUpperLeftPoint().setXCoordinate(rectangle.getUpperLeftPoint().getXCoordinate());
		oldState.getUpperLeftPoint().setYCoordinate(rectangle.getUpperLeftPoint().getYCoordinate());
		oldState.setHeight(rectangle.getHeight());
		oldState.setWidth(rectangle.getWidth());
		oldState.setBorderColor(rectangle.getBorderColor());
		oldState.setAreaColor(rectangle.getAreaColor());
		
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

}
