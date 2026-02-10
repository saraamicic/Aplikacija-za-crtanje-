package command;

import geometry.Line;
import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

public class SelectShapeCmd implements Command {

	private Shape shape;
	private DrawingModel model;
	private Boolean state;
	
	public SelectShapeCmd(Shape shape,DrawingModel model)
	{
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void execute() {
		
		state = model.getSelectedShapes().contains(shape); //true ako je bio selektovan, false ako nije
		
		if(state == true)
		{
			model.getSelectedShapes().remove(shape);
			shape.setSelected(false);
		}
		if (state == false && shape!=null)
		{
			model.getSelectedShapes().add(shape);
			shape.setSelected(true);
		}
	}
	
	@Override
	public void unexecute() {
		
		if(state == true)
		{
			shape.setSelected(true);
			model.getSelectedShapes().add(shape);
		}
		if (state == false && shape!=null)
		{
			model.getSelectedShapes().remove(shape);
			shape.setSelected(false);
		}
	}

	
	public String toString()
	{
		if (state == false)
		{
			if( shape instanceof Point || shape instanceof Line )
			{
				return "SELECTED--" + shape.toString() + ";Color:" + shape.getBorderColor().getRGB();
			}
			else 
			{
				return "SELECTED--" + shape.toString() + ";Border color:" + shape.getBorderColor().getRGB() +";Area color:" + shape.getAreaColor().getRGB();
			}
		}
		else
		{
			if( shape instanceof Point || shape instanceof Line )
			{
				return "DESELECTED--" + shape.toString() + ";Color:" + shape.getBorderColor().getRGB();
			}
			else 
			{
				return "DESELECTED--" + shape.toString() + ";Border color:" + shape.getBorderColor().getRGB() + ";Area color:" + shape.getAreaColor().getRGB();
			}
		}
	
	}
}
