package command;

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	public DrawingModel model;
	public Shape shape;
	private int orgInd;
	
	public RemoveShapeCmd(Shape shape, DrawingModel model)
	{
		this.model = model;
		this.shape = shape;
	}

	public void execute() {
		orgInd = model.getShapes().indexOf(shape);
		model.remove(shape);
		model.removeSelected(shape);
	}

	public void unexecute() {
	    model.getShapes().add(orgInd, shape);

	    shape.setSelected(true);

	    if (!model.getSelectedShapes().contains(shape)) {
	        model.getSelectedShapes().add(shape);
	    }
	}
	
	public String toString()
	{
		if( shape instanceof Point || shape instanceof Line )
		{
			return "DELETE--" + shape.toString() + ";Color:" + shape.getBorderColor().getRGB();
		}
		else 
		{
			return "DELETE--" + shape.toString() + ";Border color:" + shape.getBorderColor().getRGB() + ";Area color:" + shape.getAreaColor().getRGB();
		}
		
	}

}
