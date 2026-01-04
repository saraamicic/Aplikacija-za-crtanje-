package mvc;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import drawing.FrmDrawing.ShapeTypes;
import geometry.Point;
import geometry.Shape;
//u modelu cuvamo podatke - nasu listu sa oblicima

public class DrawingModel {
	public Shape s;
	public enum ShapeTypes {
		POINT, LINE, RECTANGLE, CIRCLE, DONUT, NONE
	}
	
	public ShapeTypes shapeType = ShapeTypes.NONE;
	
	private ArrayList<Shape> shapes = new ArrayList<>();
	
	public void add(Shape shape) //da mozemo samo da pozovemo model.add a ne celu listu itd
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
	
	public Shape vratiSelektovani()
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
}
