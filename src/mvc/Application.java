package mvc;

import javax.swing.JFrame;

public class Application {

	//gotovo 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingModel model = new DrawingModel();
		DrawingFrame frame = new DrawingFrame();
		frame.getView().setModel(model);
		DrawingController controller = new DrawingController(model,frame);
		frame.SetController(controller);
		
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
