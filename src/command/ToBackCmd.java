package command;

import javax.swing.JOptionPane;

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class ToBackCmd implements Command {
	
	public int originalIndex;
	public Shape selectedShape;
	public DrawingModel model;
	
	public ToBackCmd (Shape selectedShape, DrawingModel model)
	{
		this.selectedShape = selectedShape;
		this.model = model;
	}
	
	
	@Override
	//ovo nam je da se samo za jedno mesto unazad pomeri, znaci za jedan indeks manje (ako moze)
	public void execute() {
		if(selectedShape != null)
		{
			originalIndex = model.getShapes().indexOf(selectedShape);//njegov originalni indeks
			if(originalIndex >= 1) //da indeks ne ode na -1
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

	@Override
	//ovo mi je da ga stavim na njegov originalni indeks + 1 tj da ga pomerim za jedno napred mesto (gde je pre bio)
	public void unexecute() {
		if(selectedShape != null)
		{
			originalIndex = model.getShapes().indexOf(selectedShape);//njegov originalni indeks

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

	public String toString()
	{
		if( selectedShape instanceof Point || selectedShape instanceof Line )
		{
			return "To Back shape: " + selectedShape.toString() + " Color: " + selectedShape.getBorderColor().getRGB();
		}
		else 
		{
			return "To Back shape " + selectedShape.toString() + " Border color: " + selectedShape.getBorderColor().getRGB() + " Area color: " + selectedShape.getAreaColor().getRGB();
		}
		
	}
}
