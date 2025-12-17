package mvc;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import geometry.Point;
import geometry.Shape;

public class DrawingModel {
	private ArrayList<Shape> shapes = new ArrayList<>();
	
	public void add(Shape shape) //da mozemo samo da pozovemo model.add
	{
		shapes.add(shape);
	}
	
	public void remove(Shape shape)
	{
		shapes.remove(shape);
	}
	
	public Shape get (int index)
	{
		return shapes.get(index);
	}
	
	public ArrayList <Shape> getShapes()
	{
		return shapes;
	}
	
	
	
}
