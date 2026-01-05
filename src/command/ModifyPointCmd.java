package command;

import geometry.Point;

public class ModifyPointCmd implements Command {

	private Point point; //kakav nam je point sad
	private Point newState; //kakav hocemo da bude posle apdejta
	private Point original = new Point(); //pomocni
	
	
	public ModifyPointCmd(Point point, Point newState)
	{
		this.point=point;
		this.newState=newState;
	}

	public void execute() {
		//u original pamtimo kakav je nas prvobitni point bio
			original.setXCoordinate(point.getXCoordinate());
			original.setYCoordinate(point.getYCoordinate());
			original.setBorderColor(point.getBorderColor());
			
			//ovde pamtimo u nas point ovo novo stanje
			point.setXCoordinate(newState.getXCoordinate());
			point.setYCoordinate(newState.getYCoordinate());
			point.setBorderColor(newState.getBorderColor());
	}
	//iz new state ga vratimo u point
	public void unexecute() {
		point.setXCoordinate(original.getXCoordinate()); //dodelimo mu staro stanje koje smo zapamtili u pomocnom
		point.setYCoordinate(original.getYCoordinate());
		point.setBorderColor(original.getBorderColor());
		
	}

}
