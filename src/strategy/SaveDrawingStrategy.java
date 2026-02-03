package strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
		//celu listu oblika ubacu, oos upisuje objekte u tok podataka 
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(model.getShapes()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void Open(File file) {
		// ois čita objekate iz toka podataka
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			ArrayList<Shape> loadedShapes = (ArrayList<Shape>) ois.readObject();
			model.getShapes().clear(); // brisem trenutne oblike
			model.getShapes().addAll(loadedShapes); // ubacim ove učitane
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
