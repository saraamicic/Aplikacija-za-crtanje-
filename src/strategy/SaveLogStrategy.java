package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mvc.DrawingFrame;

public class SaveLogStrategy implements FileStrategy {

	DrawingFrame frame;
	public SaveLogStrategy(DrawingFrame frame)
	{
		this.frame = frame;
	}
	
	
	@Override
	public void Save(File file) {
		// writer pise tekst u stream
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(frame.getTextArea().getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void Open(File file) {
		
		// reader cita tekst liniju po liniju
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            frame.getTextArea().setText(""); // obrisemo sve iz loga prvo
            String line;
            while ((line = reader.readLine()) != null) {
                frame.getTextArea().append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        
        
        
        
        
    }
}


