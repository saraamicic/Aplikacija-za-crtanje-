package mvc;

import geometry.Circle;

import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import adapter.HexagonAdapter;
import command.AddShapeCmd;
import command.BringToBackCmd;
import command.BringToFrontCmd;
import command.Command;
import command.ModifyCircleCmd;
import command.ModifyDonutCmd;
import command.ModifyHexagonCmd;
import command.ModifyLineCmd;
import command.ModifyPointCmd;
import command.ModifyRectangleCmd;
import command.RemoveShapeCmd;
import command.SelectShapeCmd;
import command.ToBackCmd;
import command.ToFrontCmd;
import drawing.DlgCircle;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import mvc.DrawingModel.ShapeTypes;
import observer.UpdateButtons;


public class DrawingController {

	DrawingModel model;
	DrawingFrame frame;
	private Point startPoint = null;
	private Shape shape; 
	public Shape selectedShape;
	Stack <Command> undoStack = new Stack<Command>(); //znaci cuva sve sto implementira interfejs command
	Stack <Command> redoStack = new Stack<Command>();
	JTextArea log;
	public ShapeTypes shapeType = mvc.DrawingModel.ShapeTypes.NONE;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		
		UpdateButtons updateButtons = new UpdateButtons(frame);
		model.addPropertyChangeListener(updateButtons);
		model.setRedo(redoStack.isEmpty());
		model.setUndo(undoStack.isEmpty());
		
		log = frame.getTextArea();
		frame.getView().addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				switch (shapeType) {
				case POINT:
					Point p = new Point(e.getX(), e.getY());
					DlgPoint dlgPoint = new DlgPoint(p);
					dlgPoint.getTextFieldXCoordinate().setEditable(false);
					dlgPoint.getTextFieldYCoordinate().setEditable(false);
					dlgPoint.setColorPoint(frame.defaultBorderColor);//default boja
					dlgPoint.setVisible(true);
					if (dlgPoint.isOk()) {
						p.setBorderColor(dlgPoint.getColorPoint());
						AddShapeCmd addShapePoint = new AddShapeCmd(p, model);
						addShapePoint.execute(); // dodajemo tacku u listu
						log.append(addShapePoint.toString()+ "\n");
						undoStack.add(addShapePoint);
						redoStack.clear();
						
						model.setUndo(undoStack.isEmpty());
						model.setRedo(redoStack.isEmpty());
						
						
						
						
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
						dlgLine.setLineColor(frame.defaultBorderColor);
						dlgLine.setVisible(true);
						startPoint = null;
						if (dlgLine.isOk()) {
							l.setBorderColor(dlgLine.getLineColor());
							AddShapeCmd addShapeLine = new AddShapeCmd(l, model);
							addShapeLine.execute();
							log.append(addShapeLine.toString()+ "\n");
							undoStack.add(addShapeLine);
							redoStack.clear();
							
							model.setUndo(undoStack.isEmpty());
							model.setRedo(redoStack.isEmpty());
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
					dlgRectangle.setBorderColor(frame.getDefaultBorderColor());
					dlgRectangle.setAreaColor(frame.getDefaultAreaColor());
					dlgRectangle.setVisible(true);

					if (dlgRectangle.isOk()) {
						int width = Integer.parseInt(dlgRectangle.getTextFieldWidth().getText());
						int height = Integer.parseInt(dlgRectangle.getTextFieldHeight().getText());
						Rectangle r1 = new Rectangle(upperLeftPoint, width, height);
						r1.setBorderColor(dlgRectangle.getBorderColor());
						r1.setAreaColor(dlgRectangle.getAreaColor());
						AddShapeCmd addShapeRectangle = new AddShapeCmd(r1, model);
						addShapeRectangle.execute();
						log.append(addShapeRectangle.toString()+ "\n" );
						undoStack.add(addShapeRectangle);
						redoStack.clear();
						
						model.setUndo(undoStack.isEmpty());
						model.setRedo(redoStack.isEmpty());
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
					dlgCircle.setBorderColor(frame.getDefaultBorderColor());
					dlgCircle.setAreaColor(frame.getDefaultAreaColor());
					dlgCircle.setVisible(true);

					if (dlgCircle.isOk()) {
						int radius = Integer.parseInt(dlgCircle.getTextFieldRadius().getText());
						Circle c1 = new Circle(center, radius);
						c1.setBorderColor(dlgCircle.getBorderColor());
						c1.setAreaColor(dlgCircle.getAreaColor());
						AddShapeCmd addShapeCircle = new AddShapeCmd(c1, model);
						addShapeCircle.execute();
						log.append(addShapeCircle.toString() + "\n");
						undoStack.add(addShapeCircle);
						redoStack.clear();
						
						model.setUndo(undoStack.isEmpty());
						model.setRedo(redoStack.isEmpty());

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
					dlgDonut.setBorderColor(frame.getDefaultBorderColor());
					dlgDonut.setAreaColor(frame.getDefaultAreaColor());
					dlgDonut.setVisible(true);
					if (dlgDonut.isOk()) {
						int radius = Integer.parseInt(dlgDonut.getTextFieldDonutRadius().getText());
						int innerRadius = Integer.parseInt(dlgDonut.getTextFieldInnerRadius().getText());
						Donut d1 = new Donut(centerD, radius, innerRadius);
						d1.setBorderColor(dlgDonut.getBorderColor());
						d1.setAreaColor(dlgDonut.getAreaColor());
						AddShapeCmd addShapeDonut = new AddShapeCmd(d1, model);
						addShapeDonut.execute();
						log.append(addShapeDonut.toString() + "\n");
						undoStack.add(addShapeDonut);
						redoStack.clear();
						
						model.setUndo(undoStack.isEmpty());
						model.setRedo(redoStack.isEmpty());

					}
					setShapeType(ShapeTypes.NONE);
					frame.getButtonGroupShapes().clearSelection();
					break;
				
				case HEXAGON:
					Point centerH = new Point(e.getX(),e.getY());
					HexagonAdapter hex = new HexagonAdapter(centerH.getXCoordinate(),centerH.getYCoordinate(),0);
					DlgHexagon dlgHex = new DlgHexagon(hex);
					dlgHex.getTextFieldXCenter().setEditable(false);
					dlgHex.getTextFieldYCenter().setEditable(false);
					dlgHex.setBorderColor(frame.getDefaultBorderColor());
					dlgHex.setAreaColor(frame.getDefaultAreaColor());
					dlgHex.setVisible(true);
					
					
					if (dlgHex.isOk()) {
						int radius = Integer.parseInt(dlgHex.getTextFieldRadius().getText());
						HexagonAdapter hex1 = new HexagonAdapter(centerH.getXCoordinate(),centerH.getYCoordinate(),radius);
						hex1.setBorderColor(dlgHex.getBorderColor());
						hex1.setAreaColor(dlgHex.getAreaColor());
						
						AddShapeCmd addShapeHex = new AddShapeCmd(hex1, model);
						addShapeHex.execute();
						log.append(addShapeHex.toString() + "\n");
						undoStack.add(addShapeHex);
						redoStack.clear();
						
						model.setUndo(undoStack.isEmpty());
						model.setRedo(redoStack.isEmpty());

					}
					setShapeType(ShapeTypes.NONE);
					frame.getButtonGroupShapes().clearSelection();
					break;
					
				case NONE:
					setShapeType(ShapeTypes.NONE);
					//selektovanje
						
						Point clickedPoint = new Point(e.getX(), e.getY());
						Shape clickedShape = getSelected(clickedPoint);
						if(clickedShape != null)
						{
							SelectShapeCmd select = new SelectShapeCmd(clickedShape, model);
						    select.execute();
						    
						    log.append(select.toString() + "\n");
						    undoStack.push(select);
						    redoStack.clear();
						    
						    model.setUndo(undoStack.isEmpty());
							model.setRedo(redoStack.isEmpty());
							
							model.setSelection(model.getSelectedShapes().size());
							
							frame.repaint();
						}
						
					
					
					
					break;

				default:
					setShapeType(ShapeTypes.NONE);
					break;
				}

				frame.repaint();
			}
		});

	}	
	
	//ovo nam treba radi selektovanja
	public Shape getSelected(Point p) {
	    for (int i = model.getShapes().size() - 1; i >= 0; i--) { //idemo od poslednjeg
	        Shape s = model.get(i);
	        if (s.contains(p.getXCoordinate(), p.getYCoordinate())) {
	            return s;
	        }
	    }
	   return null;
	}

	// modifikovanje - u frejmu na klik dugmeta preko kontrolera pozivamo ovu funkciju
	public void Modify() {
			shape = model.getSelectedShapes().get(0);
			if (shape instanceof Point) {
				Point modifyPoint = (Point) shape; //ovo nam je stari point
				DlgPoint dlgPointM = new DlgPoint(modifyPoint);
				dlgPointM.setColorPoint(modifyPoint.getBorderColor());
				dlgPointM.setVisible(true);
				
				if (dlgPointM.isOk()) {
					Point newStatePoint = new Point(); //znaci ovo je point na kakav updatujemo nas stari point
					newStatePoint.setXCoordinate(Integer.parseInt(dlgPointM.getTextFieldXCoordinate().getText()));
					newStatePoint.setYCoordinate(Integer.parseInt(dlgPointM.getTextFieldYCoordinate().getText()));
					newStatePoint.setBorderColor(dlgPointM.getColorPoint());
					
					ModifyPointCmd modifPoint = new ModifyPointCmd(modifyPoint,newStatePoint);
					modifPoint.execute();
					log.append(modifPoint.toString() + "\n");
					undoStack.push(modifPoint);
					redoStack.clear();
					
					model.setUndo(undoStack.isEmpty());
					model.setRedo(redoStack.isEmpty());
					
					frame.repaint();
					
				}
			}

			else if (shape instanceof Line) {
				Line modifyLine = (Line) shape; //ovo nam je stara linija
				DlgLine dlgLineM = new DlgLine(modifyLine);
				dlgLineM.setLineColor(modifyLine.getBorderColor());
				dlgLineM.setVisible(true);
				if (dlgLineM.isOk()) {
					//moramo ovako tacke da napravimo zbog nullpointerexceptiona
					Point stPoint = new Point(Integer.parseInt(dlgLineM.getTextFieldXStartPoint().getText()),Integer.parseInt(dlgLineM.getTextFieldYStartPoint().getText()));
					Point enPoint = new Point(Integer.parseInt(dlgLineM.getTextFieldXEndPoint().getText()),Integer.parseInt(dlgLineM.getTextFieldYEndPoint().getText()));
					
					Line newStateLine = new Line(stPoint,enPoint); //ovakvu liniju zelimo posle updejta, znaci iz dijaloga preuzmemo vrednosti
					newStateLine.setBorderColor(dlgLineM.getLineColor());
					
					ModifyLineCmd modifLine = new ModifyLineCmd(modifyLine,newStateLine);
					modifLine.execute();
					log.append(modifLine.toString() + "\n");
					undoStack.push(modifLine);
					redoStack.clear();
					
					model.setUndo(undoStack.isEmpty());
					model.setRedo(redoStack.isEmpty());
					
					frame.repaint();

					
				}
			}

			else if (shape instanceof Rectangle) {
				Rectangle modifyRect = (Rectangle) shape;//stari pravougaonik
				DlgRectangle dlgRectM = new DlgRectangle(modifyRect);

				dlgRectM.getTextFieldWidth().setText(Integer.toString(modifyRect.getWidth()));
				dlgRectM.getTextFieldHeight().setText(Integer.toString(modifyRect.getHeight()));
				dlgRectM.setBorderColor(modifyRect.getBorderColor());
				dlgRectM.setAreaColor(modifyRect.getAreaColor());

				dlgRectM.setVisible(true);

				if (dlgRectM.isOk()) {
					
					Point upLeft = new Point(Integer.parseInt(dlgRectM.getTextFieldXUpperLeft().getText()),Integer.parseInt(dlgRectM.getTextFieldYUpperLeft().getText()));
					
					Rectangle newStateRect = new Rectangle();
					newStateRect.setUpperLeftPoint(upLeft);
					newStateRect.setHeight(Integer.parseInt(dlgRectM.getTextFieldHeight().getText()));
					newStateRect.setWidth(Integer.parseInt(dlgRectM.getTextFieldWidth().getText()));
					newStateRect.setBorderColor(dlgRectM.getBorderColor());
					newStateRect.setAreaColor(dlgRectM.getAreaColor());
			
					ModifyRectangleCmd modifRect = new ModifyRectangleCmd(modifyRect, newStateRect);
					modifRect.execute();
					log.append(modifRect.toString() + "\n");
					undoStack.push(modifRect);
					redoStack.clear();
					
					model.setUndo(undoStack.isEmpty());
					model.setRedo(redoStack.isEmpty());
					
					frame.repaint();
					
				}

			} else if (shape instanceof Donut) {
				Donut modifyDonut = (Donut) shape; //stari donut
				DlgDonut dlgDonutM = new DlgDonut(modifyDonut);
				dlgDonutM.getTextFieldDonutRadius().setText(Integer.toString(modifyDonut.getRadius()));
				dlgDonutM.getTextFieldInnerRadius().setText(Integer.toString(modifyDonut.getInnerRadius()));
				dlgDonutM.setAreaColor(modifyDonut.getAreaColor());
				dlgDonutM.setBorderColor(modifyDonut.getBorderColor());
				dlgDonutM.setVisible(true);
				if (dlgDonutM.isOk()) {
					
					Point p1 = new Point(Integer.parseInt(dlgDonutM.getTextFieldXDonutCenter().getText()),Integer.parseInt(dlgDonutM.getTextFieldYDonutCenter().getText()));
					Donut newStateDonut = new Donut(); //kakav zelimo da bude
					newStateDonut.setCenter(p1);
					try {
						newStateDonut.setRadius(Integer.parseInt(dlgDonutM.getTextFieldDonutRadius().getText()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					newStateDonut.setInnerRadius(Integer.parseInt(dlgDonutM.getTextFieldInnerRadius().getText()));
					newStateDonut.setBorderColor(dlgDonutM.getBorderColor());
					newStateDonut.setAreaColor(dlgDonutM.getAreaColor());
					
					ModifyDonutCmd modifDonut = new ModifyDonutCmd(modifyDonut, newStateDonut);
					modifDonut.execute();
					log.append(modifDonut.toString() + "\n");
					
					undoStack.push(modifDonut);
					redoStack.clear();
					model.setRedo(redoStack.isEmpty());
					model.setUndo(undoStack.isEmpty());
	
					
					frame.repaint();
					

				}
			} else if (shape instanceof Circle) {
				Circle modifyCircle = (Circle) shape;
				DlgCircle dlgCircleM = new DlgCircle(modifyCircle);
				dlgCircleM.getTextFieldRadius().setText(Integer.toString(modifyCircle.getRadius()));
				dlgCircleM.setBorderColor(modifyCircle.getBorderColor());
				dlgCircleM.setAreaColor(modifyCircle.getAreaColor());
				dlgCircleM.setVisible(true);

				if (dlgCircleM.isOk()) {
					Point p1 = new Point(Integer.parseInt(dlgCircleM.getTextFieldXCenter().getText()),Integer.parseInt(dlgCircleM.getTextFieldYCenter().getText()));
					Circle newStateCircle = new Circle();
					newStateCircle.setCenter(p1);
					try {
						newStateCircle.setRadius(Integer.parseInt(dlgCircleM.getTextFieldRadius().getText()));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					newStateCircle.setBorderColor(dlgCircleM.getBorderColor());
					newStateCircle.setAreaColor(dlgCircleM.getAreaColor());
					ModifyCircleCmd modifCirc = new ModifyCircleCmd (modifyCircle,newStateCircle);
					modifCirc.execute();
					log.append(modifCirc.toString() + "\n");
					undoStack.push(modifCirc);
					redoStack.clear();
					
					model.setUndo(undoStack.isEmpty());
					model.setRedo(redoStack.isEmpty());
					
					frame.repaint();
					
				}
			frame.getButtonGroupShapes().clearSelection();

		} 
			else if (shape instanceof HexagonAdapter) {
				HexagonAdapter modifyHex = (HexagonAdapter) shape;
				DlgHexagon dlgHexM = new DlgHexagon(modifyHex);
				dlgHexM.getTextFieldRadius().setText(Integer.toString(modifyHex.getRadius()));
				dlgHexM.setBorderColor(modifyHex.getBorderColor());
				dlgHexM.setAreaColor(modifyHex.getAreaColor());
				dlgHexM.setVisible(true);

				if (dlgHexM.isOk()) {
					
					Point p1 = new Point(Integer.parseInt(dlgHexM.getTextFieldXCenter().getText()), Integer.parseInt(dlgHexM.getTextFieldYCenter().getText())  );
					HexagonAdapter newStateHex = new HexagonAdapter(p1.getXCoordinate(),p1.getYCoordinate(), Integer.parseInt( dlgHexM.getTextFieldRadius().getText()) );
					
					newStateHex.setBorderColor(dlgHexM.getBorderColor());
					newStateHex.setAreaColor(dlgHexM.getAreaColor());
					ModifyHexagonCmd modifHex = new ModifyHexagonCmd(modifyHex, newStateHex);
					
					modifHex.execute();
					log.append(modifHex.toString() + "\n");
					redoStack.clear();
					
					model.setUndo(undoStack.isEmpty());
					model.setRedo(redoStack.isEmpty());
					
					frame.repaint();
				}
			frame.getButtonGroupShapes().clearSelection();

		} 
		model.setSelection(model.getSelectedShapes().size());	
		frame.getButtonGroupShapes().clearSelection();
		frame.repaint();
	}

	public void Delete() {
		
		Iterator <Shape> it = model.getSelectedShapes().iterator();
			while(it.hasNext())
			{
				RemoveShapeCmd remove = new RemoveShapeCmd(it.next(),model);
				remove.execute();
				log.append(remove.toString() + "\n");
					
				undoStack.push(remove);
				redoStack.clear();
					
				model.setUndo(undoStack.isEmpty());
				model.setRedo(redoStack.isEmpty());
					
				frame.repaint();
			}

		model.getSelectedShapes().clear(); //moram da ispraznim sad i listu selektovanih
		model.setSelection(model.getSelectedShapes().size());	

		frame.repaint();
		frame.getButtonGroupShapes().clearSelection();
	}

	public ShapeTypes getShapeType() {
		return shapeType;
	}

	public void setShapeType(ShapeTypes shapeType) {
		this.shapeType = shapeType;
	}

	public void BringToBack() {
		
		if(model.getSelectedShapes().size()==1)
		{
			selectedShape = model.returnOneSelected();
			BringToBackCmd btb = new BringToBackCmd(selectedShape, model);
			btb.execute();
			log.append(btb.toString() + "\n");
			undoStack.push(btb);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			
			
			frame.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Select one shape first","",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	public void BringToFront() {
		
		if(model.getSelectedShapes().size()==1)
		{
			selectedShape = model.returnOneSelected();
			BringToFrontCmd btf = new BringToFrontCmd(selectedShape, model);
			btf.execute();
			log.append(btf.toString() + "\n");
			undoStack.push(btf);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			
			frame.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Select one shape first","",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	public void ToBack() {
		
		if(model.getSelectedShapes().size()==1)
		{
			selectedShape = model.returnOneSelected();
			ToBackCmd tb = new ToBackCmd(selectedShape, model);
			tb.execute();
			log.append(tb.toString() + "\n");
			undoStack.push(tb);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			
			frame.repaint();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Select one shape first","",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void ToFront() {

		if(model.getSelectedShapes().size()==1)
		{
			selectedShape = model.returnOneSelected();
			ToFrontCmd tf = new ToFrontCmd(selectedShape, model);
			tf.execute();
			log.append(tf.toString() + "\n");
			undoStack.push(tf);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			
			frame.repaint();
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Select one shape first","",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void Undo() { 
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop(); //cuvam popovanu u cmd
            cmd.unexecute();
            log.append("UNDO " + cmd.toString() + "\n");
            redoStack.push(cmd);
            
            model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection(model.getSelectedShapes().size());	
            
            frame.repaint();
        }
    }

    public void Redo() { 
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            log.append("REDO " + cmd.toString() + "\n");
            undoStack.push(cmd);
            
            model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection(model.getSelectedShapes().size());	
            
            frame.repaint();
        }
    }

}
