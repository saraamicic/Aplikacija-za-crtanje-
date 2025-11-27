package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	public static void main(String[] args) {

		// pravimo okvir panela
		JFrame frame = new JFrame("Drawing Sara");
		frame.setSize(800, 600);

		Drawing crtez = new Drawing();
		frame.getContentPane().add(crtez); // dodamo u content pane nas crtez i postavljamo da vidimo okvir
		frame.setVisible(true);
	}

	public Drawing() {

	}

	public void paint(Graphics g) {
		Point p1 = new Point(30, 60);
		// p1.draw(g);

		g.setColor(Color.PINK);
		Line l1 = new Line(new Point(50, 70), new Point(90, 150));
		// l1.draw(g);

		Circle c1 = new Circle(new Point(150, 150), 70);
		// c1.draw(g);

		Donut d1 = new Donut(new Point(350, 350), 60, 30);
		// d1.draw(g);

		g.setColor(Color.DARK_GRAY);

		Rectangle r1 = new Rectangle(new Point(300, 30), 70, 40);
		// r1.draw(g);

		
		 /* //zadatak 1 ArrayList<Shape> shapes = new ArrayList<Shape>();
		 * 
		 * shapes.add(p1); 
		 * shapes.add(l1); 
		 * shapes.add(c1); 
		 * shapes.add(d1);
		 * shapes.add(r1);
		 * 
		 * Iterator<Shape> it = shapes.iterator(); 
		 * while(it.hasNext())
		 * {Shape currentShape = it.next();  //pomocna promenljiva jer next bi odma uzeo naredni
		 * element za pomeranje g.setColor(Color.DARK_GRAY); 
		 * currentShape.draw(g);
		 * g.setColor(Color.CYAN); 
		 * currentShape.moveBy(10,0); 
		 * currentShape.draw(g); }
		 * //zadatak 2 
		 * g.setColor(Color.PINK); 
		 * shapes.get(3).draw(g);
		 * shapes.get(shapes.size()-1).draw(g); 
		 * shapes.remove(1); //bila bi i dalje nacrtana kad pokrenemo 
		 * g.setColor(Color.BLUE); 
		 * shapes.get(1).draw(g);
		 * shapes.get(3).draw(g); 
		 * shapes.add(3, l1); //shapes.get(150) - izuzetak jer nemamo 150 elem 
		  try { c1.setRadius(-50);
			} catch(Exception e){
		  System.out.println(e.getMessage());
		  System.out.println("Doslo je do izuzetka"); }
		 
		  System.out.println(c1.getRadius());
		 */
		p1.setSelected(true);
		p1.draw(g);
		p1.draw(g);
		l1.setSelected(false); // linija iscrtana bez plavih
		l1.draw(g);
		c1.setSelected(true);
		c1.draw(g);
		d1.setSelected(true);
		d1.draw(g);
		r1.setSelected(true);
		r1.draw(g);
	}

}
