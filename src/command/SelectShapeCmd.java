package command;

import geometry.Shape;

public class SelectShapeCmd implements Command {

	Shape clickedShape;
	Shape oldShape;
	
	public SelectShapeCmd(Shape clickedShape,Shape shape)
	{
		this.clickedShape = clickedShape;
		this.oldShape = shape;
	}
	
	@Override
	//selektujem oblik
	public void execute() {
		 // Ako vec postoji selektovan oblik deselektujemo ga
		if (oldShape != null) {
			oldShape.setSelected(false);
	    }

	    // Ako kliknemo na oblik koji NIJE bio selektovan selektujemo ga
	    if (clickedShape != null && clickedShape != oldShape) {
	        clickedShape.setSelected(true);
	    }

	}
	
	@Override
	public void unexecute() {
		 if (clickedShape != null) {
	            clickedShape.setSelected(false);
	        }

	        // vrati staru selekciju
	        if (oldShape != null) {
	            oldShape.setSelected(true);
	        }
	}

	public String toString()
	{
			return "SELECTED "  + clickedShape.toString() + " Color: " + clickedShape.getBorderColor().toString();
	
		
	}
}
