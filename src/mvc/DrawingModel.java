package mvc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import geometry.Shape;


public class DrawingModel {
	public Shape s;
	public enum ShapeTypes {
		POINT, LINE, RECTANGLE, CIRCLE, DONUT, HEXAGON, NONE
	}
	
	PropertyChangeSupport prop;
	
	public DrawingModel()
	{
		prop = new PropertyChangeSupport(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		prop.addPropertyChangeListener(listener); 
	}
	
	public ShapeTypes shapeType = ShapeTypes.NONE;
	
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<Shape> selectedShapes = new ArrayList<>();
	
	public void add(Shape shape) 
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
		int size = selectedShapes.size();
		prop.firePropertyChange("Selection", null, selectedShapes.size());

		if(size == 1)
		{
			Shape s = selectedShapes.get(0);
			int index = shapes.indexOf(s);
			int maxIndex = shapes.size()-1;
			prop.firePropertyChange("BringToBack", null, index > 0); //ovime mu saljem boolean da li je indeks veci od nule
			prop.firePropertyChange("BringToFront", null, index < maxIndex);
		}
		else {
	        prop.firePropertyChange("BringToBack", null, false);
	        prop.firePropertyChange("BringToFront", null, false);
	    }

	}
	
	
	
}