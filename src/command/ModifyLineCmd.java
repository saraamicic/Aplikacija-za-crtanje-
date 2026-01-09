package command;

import geometry.Line;
import geometry.Point;

public class ModifyLineCmd implements Command {

	private Line line; //stara linija
	private Line newState; //kakva hocemo da bude linija
	
	private Point p1 = new Point(0,0);
	private Point p2 = new Point(0,0);
	private Line oldState = new Line(p1,p2); //pomocna	
	
	
	public ModifyLineCmd(Line line, Line newState) {
		this.line = line;
		this.newState = newState;
	}

	@Override
	public void execute() {
		//pomocnoj dodelimo vrednosti stare linije da zapamti
		oldState.getStartPoint().setXCoordinate(line.getStartPoint().getXCoordinate());
		oldState.getStartPoint().setYCoordinate(line.getStartPoint().getYCoordinate());
		oldState.getEndPoint().setXCoordinate(line.getEndPoint().getXCoordinate());
		oldState.getEndPoint().setYCoordinate(line.getEndPoint().getYCoordinate());
		oldState.setBorderColor(line.getBorderColor());
		
		//staroj dodelimo vrednosti kakve zelimo da ima
		line.getStartPoint().setXCoordinate(newState.getStartPoint().getXCoordinate());
		line.getStartPoint().setYCoordinate(newState.getStartPoint().getYCoordinate());
		line.getEndPoint().setXCoordinate(newState.getEndPoint().getXCoordinate());
		line.getEndPoint().setYCoordinate(newState.getEndPoint().getYCoordinate());
		line.setBorderColor(newState.getBorderColor());
	}

	@Override
	public void unexecute() {
		//staroj liniji vratimo vrednosti koje smo zapamtili u oldState tj pomocnoj
		line.getStartPoint().setXCoordinate(oldState.getStartPoint().getXCoordinate());
		line.getStartPoint().setYCoordinate(oldState.getStartPoint().getYCoordinate());
		line.getEndPoint().setXCoordinate(oldState.getEndPoint().getXCoordinate());
		line.getEndPoint().setYCoordinate(oldState.getEndPoint().getYCoordinate());
		line.setBorderColor(oldState.getBorderColor());
	}

	public String toString()
	{
		return "MODIFY new values: " + newState.toString() + " Color: " + newState.getBorderColor().toString();	
	}
}
