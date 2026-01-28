package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import geometry.Shape;
//u modelu cuvamo podatke - nasu listu sa oblicima

public class DrawingModel {
	public Shape s;
	public enum ShapeTypes {
		POINT, LINE, RECTANGLE, CIRCLE, DONUT, HEXAGON, NONE
	}
	
	PropertyChangeSupport prop;
	
	public DrawingModel()
	{
		prop = new PropertyChangeSupport(this); //jer mi treba za bas moj model
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		prop.addPropertyChangeListener(listener); //update button je meni listener on slusa promene
	}
	
	public ShapeTypes shapeType = ShapeTypes.NONE;
	
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<Shape> selectedShapes = new ArrayList<>();
	
	public void add(Shape shape) //da mozemo samo da pozovemo model.add a ne celu listu itd
	{
		shapes.add(shape);
	}
	
	public void remove(Shape shape)
	{
		shapes.remove(shape);
	}
	
	public void removeSelected(Shape shape)
	{
		selectedShapes.remove(shape);
	}
	
	public Shape get (int index)
	{
		return shapes.get(index);
	}
	
	public ArrayList <Shape> getShapes()
	{
		return shapes;
	}
	
	public Shape returnOneSelected()
	{
		for(int i=0;i<shapes.size();i++)
		{
			s = shapes.get(i);
			if(s.isSelected()==true)
			{
				return s;
			}
		}
		return null;
	}
	
	
	public ArrayList<Shape> getSelectedShapes()
	{
		return selectedShapes;
	}
	
	
	public void setUndo (boolean isEmpty)
	{
		prop.firePropertyChange("Undo", null, isEmpty);
		
	}
	
	public void setRedo (boolean isEmpty)
	{
		prop.firePropertyChange("Redo", null, isEmpty);
		
	}
	
	public void setSelection ()
	{
		prop.firePropertyChange("Selection", null, selectedShapes.size());
		
	}
	
	
	
}