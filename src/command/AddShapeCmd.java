package command;

import geometry.Shape;
import mvc.DrawingModel;

public class AddShapeCmd implements Command {

	public DrawingModel model;
	public Shape shape;
	
	public AddShapeCmd(Shape shape, DrawingModel model)
	{
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	//execute ce biti da se doda oblik u listu
	public void execute() {
		model.add(shape);

	}

	@Override
	//unexecute je da nam obrise taj oblik sa liste
	public void unexecute() {
		model.remove(shape);

	}

}
