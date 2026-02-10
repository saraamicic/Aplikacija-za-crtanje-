package strategy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import mvc.DrawingController;
import mvc.DrawingFrame;

public class SaveLogStrategy implements FileStrategy {

	DrawingFrame frame;
	DrawingController controller;
	private List<String> logLines = new ArrayList<>();
	public SaveLogStrategy(DrawingFrame frame, DrawingController controller)
	{
		this.frame = frame;
		this.controller=controller;
	}
	
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
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            logLines.clear(); 
            
            while ((line = br.readLine()) != null) {
                logLines.add(line);
            }
            
            // salje listu linija
            controller.setLoadedLines(logLines); 
            
            // sad moze next
            frame.getBtnNext().setEnabled(true);
            
            JOptionPane.showMessageDialog(null, "Click Next to execute");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading log", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}


