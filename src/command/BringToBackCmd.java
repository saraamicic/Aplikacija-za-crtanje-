package command;

import javax.swing.JOptionPane;

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
		if(selectedShape != null)
		{
				model.getShapes().remove(selectedShape);   //  ukloni sa stare pozicije
				model.getShapes().add(0, selectedShape);   //  ubaci na indeks 0
				
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select a shape first","",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void unexecute() {
			model.getShapes().remove(0);
			model.getShapes().add(originalIndex, selectedShape);
	}

	public String toString()
	{
		return "Bring To Back shape: " + selectedShape.toString();
	}
	
}

