package command;

import javax.swing.JOptionPane;

import geometry.Shape;
import mvc.DrawingController;
import mvc.DrawingModel;

public class BringToBackCmd implements Command {

	public DrawingModel model;
	public DrawingController controller;
	public Shape shape;
	
	
	public BringToBackCmd (DrawingModel model)
	{
		this.model = model;
	}
	
	public void bringToBack(Shape shape) {
	    model.getShapes().remove(shape);   //  ukloni sa stare pozicije
	    model.getShapes().add(0, shape);   //  ubaci na indeks 0
	}

	@Override
	public void execute() {
		shape = model.getSelectedShape();

        if (shape != null) {
            bringToBack(shape);
        }
        else
        {
        	JOptionPane.showMessageDialog(null, "Niste selektovali oblik","",JOptionPane.ERROR_MESSAGE);
        }
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub

	}

}
