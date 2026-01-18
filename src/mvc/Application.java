package mvc;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model); //veza izmedju drawing viewa i modela, 
		//ovde i resavamo ono sto smo namestili tamo u frejmu (ono gde smo pravili jos jedan model
		//radi ucitavanja
		DrawingController controller = new DrawingController(model,frame);
		frame.SetController(controller);
		
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
