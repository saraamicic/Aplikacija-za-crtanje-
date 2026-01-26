package command;

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class RemoveShapeCmd implements Command {

	public DrawingModel model;
	public Shape shape;
	
	public RemoveShapeCmd(Shape shape, DrawingModel model)
	{
		this.model = model;
		this.shape = shape;
	}
	
	//uklanja oblik iz liste
	public void execute() {
		model.remove(shape);

	}

	public void unexecute() {
		model.add(shape);
	}
	
	public String toString()
	{
		if( shape instanceof Point || shape instanceof Line )
		{
			return "DELETE " + shape.toString() + " Color: " + shape.getBorderColor().getRGB();
		}
		else 
		{
			return "DELETE " + shape.toString() + " Border color: " + shape.getBorderColor().getRGB() + " Area color: " + shape.getAreaColor().getRGB();
		}
		
	}

}
