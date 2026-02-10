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
		originalIndex = model.getShapes().indexOf(selectedShape); //pamtimo njegov prvobitan indeks

				model.getShapes().remove(selectedShape);   //  ukloni sa stare pozicije
				model.getShapes().add(0, selectedShape);   //  ubaci na indeks 0
				

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

