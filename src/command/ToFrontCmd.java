package command;

import javax.swing.JOptionPane;

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class ToFrontCmd implements Command {
	
	public int originalIndex;
	public Shape selectedShape;
	public DrawingModel model;
	
	public ToFrontCmd (Shape selectedShape, DrawingModel model)
	{
		this.selectedShape = selectedShape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		if(selectedShape != null)
		{
			originalIndex = model.getShapes().indexOf(selectedShape);

			if(originalIndex < model.getShapes().size()- 1)
			{
				model.remove(selectedShape);
				model.getShapes().add(originalIndex + 1, selectedShape);
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Please select a shape first","",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void unexecute() {
		if(selectedShape != null)
		{
			originalIndex = model.getShapes().indexOf(selectedShape);
			if(originalIndex >= 1) 
			{
				model.remove(selectedShape);
				model.getShapes().add(originalIndex - 1, selectedShape);
			}
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Please select a shape first","",JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public String toString()
	{
		if( selectedShape instanceof Point || selectedShape instanceof Line )
		{
			return "TO_FRONT--" + selectedShape.toString() + ";Color:" + selectedShape.getBorderColor().getRGB();
		}
		else 
		{
			return "TO_FRONT--" + selectedShape.toString() + ";Border color:" + selectedShape.getBorderColor().getRGB() + ";Area color:" + selectedShape.getAreaColor().getRGB();
		}
	}

}
