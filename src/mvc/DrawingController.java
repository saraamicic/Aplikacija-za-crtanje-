package mvc;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;

import command.BringToBackCmd;
import command.BringToFrontCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import drawing.FrmDrawing.ShapeTypes;
public class DrawingController {

	DrawingModel model;
	DrawingFrame frame;
	public ShapeTypes shapeType = ShapeTypes.NONE;
	private Point startPoint = null;
	private Shape shape = null;
	private Shape currentShape;
	public Shape selectedShape;
	public DrawingController(DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;
		frame.getView().addMouseListener(new MouseAdapter() {
		@Override
		//crtanje
		public void mouseClicked(MouseEvent e) {
			switch(shapeType)
			{
			case POINT:
				Point p = new Point(e.getX(), e.getY());
				DlgPoint dlgPoint = new DlgPoint(p);
				dlgPoint.getTextFieldXCoordinate().setEditable(false);
				dlgPoint.getTextFieldYCoordinate().setEditable(false);
				dlgPoint.setVisible(true);
				if (dlgPoint.isOk()) {

					model.add(p);
					p.setBorderColor(dlgPoint.getColorPoint());
					

				}
				setShapeType(ShapeTypes.NONE);
				frame.getButtonGroupShapes().clearSelection();
				break;
				
			case LINE:
				if (startPoint == null) {
					startPoint = new Point(e.getX(), e.getY());
				} else {
					Line l = new Line(startPoint, new Point(e.getX(), e.getY()));
					DlgLine dlgLine = new DlgLine(l);
					dlgLine.getTextFieldXStartPoint().setEditable(false);
					dlgLine.getTextFieldYStartPoint().setEditable(false);
					dlgLine.getTextFieldXEndPoint().setEditable(false);
					dlgLine.getTextFieldYEndPoint().setEditable(false);
					dlgLine.setVisible(true);
					startPoint = null;
					if (dlgLine.isOk()) {
						l.setBorderColor(dlgLine.getLineColor());
						model.add(l);
						

					}
					setShapeType(ShapeTypes.NONE);
					frame.getButtonGroupShapes().clearSelection();
				}

				break;
				
			case RECTANGLE:
				Point upperLeftPoint = new Point(e.getX(), e.getY());

				Rectangle r = new Rectangle(upperLeftPoint, 0, 0);
				DlgRectangle dlgRectangle = new DlgRectangle(r);

				dlgRectangle.getTextFieldXUpperLeft().setEditable(false);
				dlgRectangle.getTextFieldYUpperLeft().setEditable(false);
				dlgRectangle.setVisible(true);

				if (dlgRectangle.isOk()) {
					int width = Integer.parseInt(dlgRectangle.getTextFieldWidth().getText());
					int height = Integer.parseInt(dlgRectangle.getTextFieldHeight().getText());
					Rectangle r1 = new Rectangle(upperLeftPoint, width, height);
					model.add(r1);
					r1.setBorderColor(dlgRectangle.getBorderColor());
					r1.setAreaColor(dlgRectangle.getAreaColor());
					
				}
				setShapeType(ShapeTypes.NONE);
				frame.getButtonGroupShapes().clearSelection();
				break;
				
			case CIRCLE:
				Point center = new Point(e.getX(), e.getY());

				Circle c = new Circle(center, 0);
				DlgCircle dlgCircle = new DlgCircle(c);

				dlgCircle.getTextFieldXCenter().setEditable(false);
				dlgCircle.getTextFieldYCenter().setEditable(false);
				dlgCircle.setVisible(true);

				if (dlgCircle.isOk()) {
					int radius = Integer.parseInt(dlgCircle.getTextFieldRadius().getText());
					Circle c1 = new Circle(center, radius);
					model.add(c1);
					c1.setBorderColor(dlgCircle.getBorderColor());
					c1.setAreaColor(dlgCircle.getAreaColor());
					
				}
				setShapeType(ShapeTypes.NONE);
				frame.getButtonGroupShapes().clearSelection();
				break;

			case DONUT:
				Point centerD = new Point(e.getX(), e.getY());

				Donut d = new Donut(centerD, 0, 0);
				DlgDonut dlgDonut = new DlgDonut(d);
				dlgDonut.getTextFieldXDonutCenter().setText(Integer.toString(d.getCenter().getXCoordinate()));
				dlgDonut.getTextFieldYDonutCenter().setText(Integer.toString(d.getCenter().getYCoordinate()));
				dlgDonut.getTextFieldXDonutCenter().setEditable(false);
				dlgDonut.getTextFieldYDonutCenter().setEditable(false);
				dlgDonut.setVisible(true);
				if (dlgDonut.isOk()) {
					int radius = Integer.parseInt(dlgDonut.getTextFieldDonutRadius().getText());
					int innerRadius = Integer.parseInt(dlgDonut.getTextFieldInnerRadius().getText());
					Donut d1 = new Donut(centerD, radius, innerRadius);
					model.add(d1);
					d1.setBorderColor(dlgDonut.getBorderColor());
					d1.setAreaColor(dlgDonut.getAreaColor());
					
				}
				setShapeType(ShapeTypes.NONE);
				frame.getButtonGroupShapes().clearSelection();
				break;

			case NONE:
				setShapeType(ShapeTypes.NONE);
				break;

			default:
				setShapeType(ShapeTypes.NONE);
				break;
			}
			
			frame.repaint();
		}
		//selektovanje
		public void mousePressed(MouseEvent e) {
			Point clickedPoint = new Point(e.getX(), e.getY());
			currentShape = getSelected(clickedPoint);
			if (shape != null && currentShape != null) {
				if (shape.equals(currentShape)) {
					currentShape.setSelected(false);
					shape.setSelected(false);
					shape = null;
					frame.repaint();
					return;
				}
			}

			if (currentShape != null) {
				currentShape.setSelected(true);
			}
			if (currentShape == null && shape != null) {
				shape.setSelected(false);
				frame.repaint();
			}
			frame.repaint();
			shape = currentShape;
		}
	});
		
}
	//modifikovanje - u frejmu na klik dugmeta preko kontrolera pozivamo ovu funkciju
	public void Modify()
	{
		if (shape != null) {
			if (shape instanceof Point) {
				Point modifyPoint = (Point) shape;
				DlgPoint dlgPointM = new DlgPoint(modifyPoint);
				dlgPointM.setColorPoint(modifyPoint.getBorderColor());
				dlgPointM.setVisible(true);
				if (dlgPointM.isOk()) {
					modifyPoint.setXCoordinate(Integer.parseInt(dlgPointM.getTextFieldXCoordinate().getText()));
					modifyPoint.setYCoordinate(Integer.parseInt(dlgPointM.getTextFieldYCoordinate().getText()));
					modifyPoint.setBorderColor(dlgPointM.getColorPoint());
					modifyPoint.setSelected(false);
				}
			}

			else if (shape instanceof Line) {
				Line modifyLine = (Line) shape;
				DlgLine dlgLineM = new DlgLine(modifyLine);
				dlgLineM.setLineColor(modifyLine.getBorderColor());
				dlgLineM.setVisible(true);
				if (dlgLineM.isOk()) {
					modifyLine.getStartPoint()
							.setXCoordinate(Integer.parseInt(dlgLineM.getTextFieldXStartPoint().getText()));
					modifyLine.getStartPoint()
							.setYCoordinate(Integer.parseInt(dlgLineM.getTextFieldYStartPoint().getText()));
					modifyLine.getEndPoint()
							.setXCoordinate(Integer.parseInt(dlgLineM.getTextFieldXEndPoint().getText()));
					modifyLine.getEndPoint()
							.setYCoordinate(Integer.parseInt(dlgLineM.getTextFieldYEndPoint().getText()));
					modifyLine.setBorderColor(dlgLineM.getLineColor());
					modifyLine.setSelected(false);
				}
			}

			else if (shape instanceof Rectangle) {
				Rectangle modifyRect = (Rectangle) shape;
				DlgRectangle dlgRectM = new DlgRectangle(modifyRect);

				dlgRectM.getTextFieldWidth().setText(Integer.toString(modifyRect.getWidth()));
				dlgRectM.getTextFieldHeight().setText(Integer.toString(modifyRect.getHeight()));
				dlgRectM.setBorderColor(modifyRect.getBorderColor());
				dlgRectM.setAreaColor(modifyRect.getAreaColor());

				dlgRectM.setVisible(true);

				if (dlgRectM.isOk()) {
					modifyRect.getUpperLeftPoint()
							.setXCoordinate(Integer.parseInt(dlgRectM.getTextFieldXUpperLeft().getText()));
					modifyRect.getUpperLeftPoint()
							.setYCoordinate(Integer.parseInt(dlgRectM.getTextFieldYUpperLeft().getText()));

					modifyRect.setWidth(Integer.parseInt(dlgRectM.getTextFieldWidth().getText()));
					modifyRect.setHeight(Integer.parseInt(dlgRectM.getTextFieldHeight().getText()));
					modifyRect.setBorderColor(dlgRectM.getBorderColor());
					modifyRect.setAreaColor(dlgRectM.getAreaColor());
					modifyRect.setSelected(false);

				}

			} else if (shape instanceof Donut) {
				Donut modifyDonut = (Donut) shape;
				DlgDonut dlgDonutM = new DlgDonut(modifyDonut);
				dlgDonutM.getTextFieldDonutRadius().setText(Integer.toString(modifyDonut.getRadius()));
				dlgDonutM.getTextFieldInnerRadius().setText(Integer.toString(modifyDonut.getInnerRadius()));
				dlgDonutM.setAreaColor(modifyDonut.getAreaColor());
				dlgDonutM.setBorderColor(modifyDonut.getBorderColor());
				dlgDonutM.setVisible(true);
				if (dlgDonutM.isOk()) {
					modifyDonut.getCenter()
							.setXCoordinate(Integer.parseInt(dlgDonutM.getTextFieldXDonutCenter().getText()));
					modifyDonut.getCenter()
							.setYCoordinate(Integer.parseInt(dlgDonutM.getTextFieldYDonutCenter().getText()));
					try {
						modifyDonut.setRadius(Integer.parseInt(dlgDonutM.getTextFieldDonutRadius().getText()));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					modifyDonut.setInnerRadius(Integer.parseInt(dlgDonutM.getTextFieldInnerRadius().getText()));
					modifyDonut.setBorderColor(dlgDonutM.getBorderColor());
					modifyDonut.setAreaColor(dlgDonutM.getAreaColor());
					modifyDonut.setSelected(false);

				}
			} else if (shape instanceof Circle) {
				Circle modifyCircle = (Circle) shape;
				DlgCircle dlgCircleM = new DlgCircle(modifyCircle);
				dlgCircleM.getTextFieldRadius().setText(Integer.toString(modifyCircle.getRadius()));
				dlgCircleM.setBorderColor(modifyCircle.getBorderColor());
				dlgCircleM.setAreaColor(modifyCircle.getAreaColor());
				dlgCircleM.setVisible(true);

				if (dlgCircleM.isOk()) {
					modifyCircle.getCenter()
							.setXCoordinate(Integer.parseInt(dlgCircleM.getTextFieldXCenter().getText()));
					modifyCircle.getCenter()
							.setYCoordinate(Integer.parseInt(dlgCircleM.getTextFieldYCenter().getText()));
					int r = Integer.parseInt(dlgCircleM.getTextFieldRadius().getText());
					try {
						modifyCircle.setRadius(r);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					modifyCircle.setBorderColor(dlgCircleM.getBorderColor());
					modifyCircle.setAreaColor(dlgCircleM.getAreaColor());
					modifyCircle.setSelected(false);
				}

				;}

			frame.getButtonGroupShapes().clearSelection();

		} else {
			JOptionPane.showMessageDialog(null, "Select the shape you want to modify first!", "",
					JOptionPane.ERROR_MESSAGE);
		}

		frame.getButtonGroupShapes().clearSelection();
		shape = null;
		frame.repaint();
	
	}
	
	//brisanje - u frejmu na klik dugmeta preko kontrolera pozivamo ovu funkciju
	public void Delete()
	{
		if(shape != null)
		{
			Shape deleted;
			Iterator<Shape> it = model.getShapes().iterator();
			while (it.hasNext()) {
				deleted = it.next();
				if (deleted.isSelected()) {
					if (JOptionPane.showConfirmDialog(null, "Confirm", "Delete", JOptionPane.YES_NO_CANCEL_OPTION) == 0) {
						model.remove(deleted);
						frame.repaint();
						break;
					} 
					else {
						deleted.setSelected(false);
						break;
					}
				}
			}
		}
		else 
		{
		JOptionPane.showMessageDialog(null, "Select the shape you want to delete!", "", JOptionPane.ERROR_MESSAGE);
		}
		
		frame.repaint();
		frame.getButtonGroupShapes().clearSelection();
	}
	
	
	public ShapeTypes getShapeType() {
		return shapeType;
	}

	public void setShapeType(ShapeTypes shapeType) {
		this.shapeType = shapeType;
	}

	//ovo nam treba radi selektovanja
	public Shape getSelected(Point p) { // vraca selektovan oblik uz prosledjenu tacku klika
		Shape selected = null;
		Shape temp;
		Iterator<Shape> it = model.getShapes().iterator();
		while (it.hasNext()) {
			temp = it.next();
			if (temp.contains(p.getXCoordinate(), p.getYCoordinate())) {
				selected = temp;
			}
			temp.setSelected(false);
		}
		return selected;
	}
	
	
	public void BringToBack()
	{
		selectedShape = model.vratiSelektovani();
		BringToBackCmd btb = new BringToBackCmd(selectedShape,model);
		btb.execute();
		frame.repaint();
	}
	
	public void BringToFront()
	{	
		selectedShape = model.vratiSelektovani();
		BringToFrontCmd btf = new BringToFrontCmd(selectedShape,model);
		btf.execute();
		frame.repaint();
	}
	
	
}


