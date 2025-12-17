package mvc;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class DrawingFrame extends JFrame {

	DrawingView view = new DrawingView();
	DrawingController controller;
	
	public DrawingFrame() {
		
		setTitle("Sara crta ovde");
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controller.mouseClicked(e);
			}
		});
		
		//ne treba nam ovaj panel koji smo ovde obrisali
		//kada kreiramo objekat klase frame automatski nam se i objekat od view kreira
		getContentPane().add(view, BorderLayout.CENTER);
	}

	public DrawingView getView() {
		return view;
	}

	public void SetController(DrawingController controller) {
		this.controller=controller;
	}

}
