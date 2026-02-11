package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import geometry.Shape;
import mvc.DrawingModel;

public class SaveDrawingStrategy implements FileStrategy {
	
	DrawingModel model;
	
	public SaveDrawingStrategy(DrawingModel model)
	{
		this.model = model;
	}

	@Override
	public void Save(File file) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(model.getShapes()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Open(File file) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			ArrayList<Shape> loadedShapes = (ArrayList<Shape>) ois.readObject();
			model.getShapes().clear(); 
			Iterator<Shape>it = loadedShapes.iterator();
			while(it.hasNext())
			{
				Shape s = it.next();
				s.setSelected(false); 
	            model.add(s);
			}
			model.getSelectedShapes().clear();
			model.getShapes().addAll(loadedShapes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
