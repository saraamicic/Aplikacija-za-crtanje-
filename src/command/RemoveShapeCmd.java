package command;

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
		return "DELETE " + shape.toString() + " Color: " + shape.getBorderColor().toString();
	}

}
