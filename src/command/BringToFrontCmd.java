package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingModel;

public class BringToFrontCmd implements Command {
	public DrawingModel model;
	public int lastIndex;
	public Shape selectedShape;
	
	public BringToFrontCmd (Shape selectedShape, DrawingModel model)
	{
		this.selectedShape = selectedShape;
		this.model = model;
	}
	
	
	@Override
	//trebamo oblik da premestimo na kraj liste 
	public void execute() {
		lastIndex = model.getShapes().size() - 1; //da znamo koji je indeks poslednjeg elementa
		if(selectedShape != null)
		{
			model.getShapes().remove(selectedShape);
			model.getShapes().add(lastIndex, selectedShape); //dodamo ga kao poslednjeg
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select a shape first","",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	@Override
	public void unexecute() {

	}

}
