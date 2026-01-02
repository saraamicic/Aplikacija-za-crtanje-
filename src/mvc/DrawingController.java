package mvc;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	public ShapeTypes getShapeType() {
		return shapeType;
	}

	public void setShapeType(ShapeTypes shapeType) {
		this.shapeType = shapeType;
	}

	
	public DrawingController(DrawingModel model, DrawingFrame frame)
	{
		this.model = model;
		this.frame = frame;
		
		frame.getView().addMouseListener(new MouseAdapter() {
		@Override
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
	});
	
}

	
}


