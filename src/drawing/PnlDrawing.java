/*package drawing;
/*
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Point;
import geometry.Shape;
import mvc.DrawingModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PnlDrawing extends JPanel {
	private FrmDrawing frmDrawing;
	private ArrayList<Shape> shapes = new ArrayList<>();
	public DrawingModel model = new DrawingModel();
	private static final long serialVersionUID = 1L;


	/**
	 * Create the panel.
	 *//*
	public PnlDrawing() {
		setPreferredSize(new Dimension(1000, 500));
	}
	//ovo ce se obrisati jer smo premestili u drawingView
	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext())
			it.next().draw(g);
	}
	
	
	//ne znam sta sa ovime ???
	public Shape getSelected(Point p) { // vraca selektovan oblik uz prosledjenu tacku klika
		Shape selected = null;
		Shape temp;
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			temp = it.next();
			if (temp.contains(p.getXCoordinate(), p.getYCoordinate())) {
				selected = temp;
			}
			temp.setSelected(false);
		}
		return selected;
	}

	public void delete() {
		Shape deleted;
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			deleted = it.next();
			if (deleted.isSelected()) {
				if (JOptionPane.showConfirmDialog(null, "Confirm", "Delete", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
					shapes.remove(deleted);
					repaint();
					break;
				} else {
					deleted.setSelected(false);
					break;
				}

			}
		}
	}
//TREBA DA SE OBRISE VALJDA
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public void addShape(Shape shape) {
		this.shapes.add(shape);
		repaint();
	}
}

*/
