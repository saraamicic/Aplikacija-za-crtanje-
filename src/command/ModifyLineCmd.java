package command;

import geometry.Line;

public class ModifyLineCmd implements Command {

	private Line line; 
	private Line newState; 
	private Line oldState; 
	
	public ModifyLineCmd(Line line, Line newState) {
		this.line = line;
		this.newState = newState;
	}

	@Override
	public void execute() {
		
		oldState = line.clone();
		
		line.getStartPoint().setXCoordinate(newState.getStartPoint().getXCoordinate());
		line.getStartPoint().setYCoordinate(newState.getStartPoint().getYCoordinate());
		line.getEndPoint().setXCoordinate(newState.getEndPoint().getXCoordinate());
		line.getEndPoint().setYCoordinate(newState.getEndPoint().getYCoordinate());
		line.setBorderColor(newState.getBorderColor());
	}

	@Override
	public void unexecute() {
		line.getStartPoint().setXCoordinate(oldState.getStartPoint().getXCoordinate());
		line.getStartPoint().setYCoordinate(oldState.getStartPoint().getYCoordinate());
		line.getEndPoint().setXCoordinate(oldState.getEndPoint().getXCoordinate());
		line.getEndPoint().setYCoordinate(oldState.getEndPoint().getYCoordinate());
		line.setBorderColor(oldState.getBorderColor());
	}

	public String toString()
	{
		return "MODIFY--" + oldState.toString() + ";Color:" + oldState.getBorderColor().getRGB() + 
				">>" + newState.toString() + ";Color:" + newState.getBorderColor().getRGB();	
	}
}
