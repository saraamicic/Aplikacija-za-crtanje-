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
				
				frame.getButtonGroupShapes().clearSelection();
				frame.repaint();
				break;
			case LINE:
				break;
			
			
			
			}
		}
	});
	
}

	
}


