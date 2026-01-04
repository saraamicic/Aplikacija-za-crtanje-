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
			if (originalIndex != 0) { //ako mu indeks nije 0
				model.getShapes().remove(selectedShape);   //  ukloni sa stare pozicije
				model.getShapes().add(0, selectedShape);   //  ubaci na indeks 0
				}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Please select a shape first","",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void unexecute() {
		int originalIndex = model.getShapes().indexOf(selectedShape);
		//znaci ako je nas selektovani oblik skroz pozadi, na nultom indeksu, vratimo ga na prvobitni indeks i obrisemo sa nultog
		if(model.getShapes().indexOf(selectedShape)==0)
		{
			model.getShapes().remove(0);
			model.getShapes().add(originalIndex, selectedShape);
		}
		

	}

}
