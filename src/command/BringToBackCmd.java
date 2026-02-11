package command;


import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {

	public DrawingModel model;
	public Shape selectedShape;
	public int originalIndex;
	
	
	public BringToBackCmd (Shape selectedShape, DrawingModel model)
	{
		this.model=model;
		this.selectedShape = selectedShape;
	}
	
	@Override
	public void execute() {
		originalIndex = model.getShapes().indexOf(selectedShape); 

				model.getShapes().remove(selectedShape);  
				model.getShapes().add(0, selectedShape); 
				

	}
	
	@Override
	public void unexecute() {
			model.getShapes().remove(0);
			model.getShapes().add(originalIndex, selectedShape);
	}

	public String toString()
	{
		if( selectedShape instanceof Point || selectedShape instanceof Line )
		{
			return "BRING_TO_BACK--" + selectedShape.toString() + ";Color:" + selectedShape.getBorderColor().getRGB();
		}
		else 
		{
			return "BRING_TO_BACK--" + selectedShape.toString() + ";Border color:" + selectedShape.getBorderColor().getRGB() + ";Area color:" + selectedShape.getAreaColor().getRGB();
		}
	}
	
}

