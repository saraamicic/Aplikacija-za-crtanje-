package command;

import javax.swing.JOptionPane;

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	public DrawingModel model;
	public int lastIndex;
	public int originalIndex;
	public Shape selectedShape;
	
	public BringToFrontCmd (Shape selectedShape, DrawingModel model)
	{
		this.selectedShape = selectedShape;
		this.model = model;
	}
	
	
	@Override
	public void execute() {
		originalIndex = model.getShapes().indexOf(selectedShape);
		lastIndex = model.getShapes().size() - 1; 
		if(selectedShape != null)
		{
			model.getShapes().remove(selectedShape);
			model.getShapes().add(lastIndex, selectedShape); 
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select a shape first","",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void unexecute() {
		model.remove(selectedShape);
		model.getShapes().add(originalIndex, selectedShape);

	}
	
	public String toString()
	{
		if( selectedShape instanceof Point || selectedShape instanceof Line )
		{
			return "BRING_TO_FRONT--" + selectedShape.toString() + ";Color:" + selectedShape.getBorderColor().getRGB();
		}
		else 
		{
			return "BRING_TO_FRONT--" + selectedShape.toString() + ";Border color:" + selectedShape.getBorderColor().getRGB() + ";Area color:" + selectedShape.getAreaColor().getRGB();
		}
	}

}
