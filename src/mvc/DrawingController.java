package mvc;

import geometry.Circle;

import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import hexagon.Hexagon;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import strategy.FileStrategy;
import strategy.SaveDrawingStrategy;
import strategy.SaveLogStrategy;


public class DrawingController {

	DrawingModel model;
	DrawingFrame frame;
	private Point startPoint = null;
	private Shape shape; 
	public Shape selectedShape;
	Stack <Command> undoStack = new Stack<Command>(); //znaci cuva sve sto implementira interfejs command
	Stack <Command> redoStack = new Stack<Command>();
	JTextArea log;
	private List<String> loadedLines = new ArrayList<>();
	private int currentLineIndex = 0;
	public ShapeTypes shapeType = mvc.DrawingModel.ShapeTypes.NONE;

	public DrawingController(DrawingModel model, DrawingFrame frame) {
		this.model = model;
		this.frame = frame;
		
		UpdateButtons updateButtons = new UpdateButtons(frame);
		model.addPropertyChangeListener(updateButtons);
		model.setRedo(redoStack.isEmpty());
		model.setUndo(undoStack.isEmpty());
		model.setSelection();
		
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
							
							model.setSelection();
							
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
					undoStack.push(modifHex);
					redoStack.clear();
					
					model.setUndo(undoStack.isEmpty());
					model.setRedo(redoStack.isEmpty());
					
					frame.repaint();
				}
			frame.getButtonGroupShapes().clearSelection();

		} 
		//model.setSelection();	
		frame.getButtonGroupShapes().clearSelection();
		frame.repaint();
	}
	
	public void Delete() {

	    for (int i = model.getSelectedShapes().size() - 1; i >= 0; i--) {
	        Shape s = model.getSelectedShapes().get(i);
	        RemoveShapeCmd remove = new RemoveShapeCmd(s, model);
	        
	        remove.execute(); // deselektuje i brise iz obe liste  u modelu
	        
	        undoStack.push(remove);
	        log.append(remove.toString() + "\n");
	    }
	    
	    //kad je sve obrisano
	    redoStack.clear();
	    model.setUndo(undoStack.isEmpty());
	    model.setRedo(redoStack.isEmpty());
	    model.setSelection(); 
	    frame.repaint();
	}
	
	public ShapeTypes getShapeType() {
		return shapeType;
	}

	public void setShapeType(ShapeTypes shapeType) {
		this.shapeType = shapeType;
	}

	public void BringToBack() {
		
			selectedShape = model.returnOneSelected();
			if(model.get(0).equals(selectedShape))
				return;
				
			BringToBackCmd btb = new BringToBackCmd(selectedShape, model);
			btb.execute();
			log.append(btb.toString() + "\n");
			undoStack.push(btb);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection();
			
			frame.repaint();

	}

	public void BringToFront() {
		
		if(model.getSelectedShapes().size()==1)
		{
			selectedShape = model.returnOneSelected();
			if(model.get(model.getShapes().size()-1).equals(selectedShape))
				return;
			BringToFrontCmd btf = new BringToFrontCmd(selectedShape, model);
			btf.execute();
			log.append(btf.toString() + "\n");
			undoStack.push(btf);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection();
			
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
			if(model.get(0).equals(selectedShape))
				return;
			ToBackCmd tb = new ToBackCmd(selectedShape, model);
			tb.execute();
			log.append(tb.toString() + "\n");
			undoStack.push(tb);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection();
			
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
			if(model.get(model.getShapes().size()-1).equals(selectedShape))
				return;
			ToFrontCmd tf = new ToFrontCmd(selectedShape, model);
			tf.execute();
			log.append(tf.toString() + "\n");
			undoStack.push(tf);
			redoStack.clear();
			
			model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection();
			
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
            log.append("UNDO-->" + cmd.toString() + "\n");
            redoStack.push(cmd);
            
            model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection();
            
            frame.repaint();
        }
    }

    public void Redo() { 
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            log.append("REDO-->" + cmd.toString() + "\n");
            undoStack.push(cmd);
            
            model.setUndo(undoStack.isEmpty());
			model.setRedo(redoStack.isEmpty());
			model.setSelection();
            
            frame.repaint();
        }
    }
    
    public void saveDrawing()
    {
    	JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setDialogTitle("Sačuvaj crtež kao .bin");
    	
    	if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            // mora da ima .bin ekst
            if (!file.getName().toLowerCase().endsWith(".bin")) {
                file = new File(file.getAbsolutePath() + ".bin");
            }

            SaveDrawingStrategy strategy = new SaveDrawingStrategy(model);
            strategy.Save(file);
            
        }
    	
    }
    
    public void saveLog()
    {
    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sačuvaj log kao .txt");

        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //samo sa txt ekst
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }

            SaveLogStrategy strategy = new SaveLogStrategy(frame);
            strategy.Save(file);
            
        }
    	
    }
    
    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Otvori fajl");
        
        // prikazujemo samo .bin i .txt
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("(.bin)", "bin"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("(.txt)", "txt"));

        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            FileStrategy strategy; 

            //u zavisnosti od ekstenzije pozivam funk
            if (file.getName().toLowerCase().endsWith(".bin")) {
                strategy = new SaveDrawingStrategy(model);
                log.setText("");
                frame.getBtnUndo().setEnabled(false);
            } else if (file.getName().toLowerCase().endsWith(".txt")) { //za log
                strategy = new SaveLogStrategy(frame,this);
                
            } else {
                return;
            }

            strategy.Open(file);
            frame.repaint();
        }
    }
    
    
    public void executeNextLine() {
        if (currentLineIndex < loadedLines.size()) {
            String line = loadedLines.get(currentLineIndex);
            parseAndExecute(line);
            log.append(line + "\n");
            currentLineIndex++;
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "No more lines to execute");
            frame.getBtnNext().setEnabled(false); 
        }
        frame.repaint();
    }
    
    private void parseAndExecute(String line) {
        String[] parts = line.split("--");
        String command = parts[0]; // add, delete, selected itd
        String detail = parts[1];  // circle x,y;radius=r itd

        if (command.equals("ADD")) {
            if (detail.startsWith("POINT")) {
            	String[] data = detail.split(";");
            	
                String coordsPart = data[0].split(" ")[1]; 
                int x = Integer.parseInt(coordsPart.split(",")[0]);
                int y = Integer.parseInt(coordsPart.split(",")[1]);
             
                Color borderColor = new Color(Integer.parseInt(data[1].split(":")[1]));
                
                Point p = new Point(x, y);
                p.setBorderColor(borderColor);
                
                AddShapeCmd addCmd = new AddShapeCmd(p, model);
                addCmd.execute(); 
                undoStack.push(addCmd);
                redoStack.clear();
                frame.repaint();
            	
            }
            else if(detail.startsWith("CIRCLE"))
            {
            	String[] data = detail.split(";");
            	
                String coordsPart = data[0].split(" ")[1]; 
                int x = Integer.parseInt(coordsPart.split(",")[0]);
                int y = Integer.parseInt(coordsPart.split(",")[1]);
             
                int radius = Integer.parseInt(data[1].split("=")[1]);
                Color borderColor = new Color(Integer.parseInt(data[2].split(":")[1]));
                Color areaColor = new Color(Integer.parseInt(data[3].split(":")[1]));
                
                Circle c = new Circle(new Point(x, y), radius);
                c.setBorderColor(borderColor);
                c.setAreaColor(areaColor);
                
                AddShapeCmd addCmd = new AddShapeCmd(c, model);
                addCmd.execute(); 
                undoStack.push(addCmd);
                redoStack.clear();
                frame.repaint();
            	
            }
            else if(detail.startsWith("LINE"))
            {
            	String[] data = detail.split(";");
            	
                String coordsPart = data[0].split(" ")[1]; 
                int xs = Integer.parseInt(coordsPart.split(",")[0]);
                int ys = Integer.parseInt(coordsPart.split(",")[1]);
                
                String coordsPart2 = data[1];
                int xe = Integer.parseInt(coordsPart2.split(",")[0]);
                int ye = Integer.parseInt(coordsPart2.split(",")[1]);
             
                Color borderColor = new Color(Integer.parseInt(data[2].split(":")[1]));
                
                Line l = new Line(new Point(xs, ys), new Point(xe, ye));
                l.setBorderColor(borderColor);
                
                AddShapeCmd addCmd = new AddShapeCmd(l, model);
                addCmd.execute(); 
                undoStack.push(addCmd);
                redoStack.clear();
                frame.repaint();
            }
            else if(detail.startsWith("RECTANGLE"))
            {
            	String[] data = detail.split(";");
            	
                String coordsPart = data[0].split(" ")[1]; 
                int x = Integer.parseInt(coordsPart.split(",")[0]);
                int y = Integer.parseInt(coordsPart.split(",")[1]);
             
                int width = Integer.parseInt(data[1].split("=")[1]);
                int height = Integer.parseInt(data[2].split("=")[1]);
         
                Color borderColor = new Color(Integer.parseInt(data[3].split(":")[1]));
                Color areaColor = new Color(Integer.parseInt(data[4].split(":")[1]));
                
                Rectangle r = new Rectangle(new Point(x,y), width, height);
                r.setBorderColor(borderColor);
                r.setAreaColor(areaColor);
                
                AddShapeCmd addCmd = new AddShapeCmd(r, model);
                addCmd.execute(); 
                undoStack.push(addCmd);
                redoStack.clear();
                frame.repaint();
            }
            else if(detail.startsWith("DONUT"))
            {
            	String[] data = detail.split(";");
            	
                String coordsPart = data[0].split(" ")[1]; 
                int x = Integer.parseInt(coordsPart.split(",")[0]);
                int y = Integer.parseInt(coordsPart.split(",")[1]);
             
                int radius = Integer.parseInt(data[1].split("=")[1]);
                int innerRadius = Integer.parseInt(data[2].split("=")[1]);
                Color borderColor = new Color(Integer.parseInt(data[3].split(":")[1]));
                Color areaColor = new Color(Integer.parseInt(data[4].split(":")[1]));
                
                Donut d = new Donut(new Point(x,y), radius, innerRadius);
                d.setBorderColor(borderColor);
                d.setAreaColor(areaColor);
                
                AddShapeCmd addCmd = new AddShapeCmd(d, model);
                addCmd.execute(); 
                undoStack.push(addCmd);
                redoStack.clear();
                frame.repaint();
            }
            else if(detail.startsWith("HEXAGON"))
            {
            	String[] data = detail.split(";");
            	
                String coordsPart = data[0].split(" ")[1]; 
                int x = Integer.parseInt(coordsPart.split(",")[0]);
                int y = Integer.parseInt(coordsPart.split(",")[1]);
             
                int radius = Integer.parseInt(data[1].split("=")[1]);
                Color borderColor = new Color(Integer.parseInt(data[2].split(":")[1]));
                Color areaColor = new Color(Integer.parseInt(data[3].split(":")[1]));
                
                HexagonAdapter h  = new HexagonAdapter(x, y, radius);
                h.setBorderColor(borderColor);
                h.setAreaColor(areaColor);
                
                AddShapeCmd addCmd = new AddShapeCmd(h, model);
                addCmd.execute(); 
                undoStack.push(addCmd);
                redoStack.clear();
                frame.repaint();
            }
        }
        else if (command.equals("SELECTED") || command.equals("DESELECTED")) {
        	//saljem detalje da nadjem u modelu takav
            Shape shapeToSelect = findShapeInModel(detail);
                SelectShapeCmd select = new SelectShapeCmd(shapeToSelect, model);
                select.execute();
                undoStack.push(select);
                redoStack.clear();
                frame.repaint();
        }

        else if (command.equals("DELETE")) {
            Shape shapeToDelete = findShapeInModel(detail);
                RemoveShapeCmd remove = new RemoveShapeCmd(shapeToDelete, model);
                remove.execute();
                undoStack.push(remove);
                redoStack.clear();
                frame.repaint();
        }
        
        else if (command.equals("BRING_TO_BACK")) {
            Shape shape = findShapeInModel(detail);
            if (shape != null) {
                BringToBackCmd btb = new BringToBackCmd(shape, model);
                btb.execute();
                undoStack.push(btb);
                redoStack.clear();
                frame.repaint();
            }
        }
        
        else if (command.equals("TO_BACK")) {
            Shape shape = findShapeInModel(detail);
            if (shape != null) {
                ToBackCmd tb = new ToBackCmd(shape, model);
                tb.execute();
                undoStack.push(tb);
                redoStack.clear();
                frame.repaint();
            }
        }
        
        else if (command.equals("BRING_TO_FRONT")) {
            Shape shape = findShapeInModel(detail);
            if (shape != null) {
                BringToFrontCmd btf = new BringToFrontCmd(shape, model);
                btf.execute();
                undoStack.push(btf);
                redoStack.clear();
                frame.repaint();
            }
        }
        else if (command.equals("TO_FRONT")) {
            Shape shape = findShapeInModel(detail);
            if (shape != null) {
                ToFrontCmd tf = new ToFrontCmd(shape, model);
                tf.execute();
                undoStack.push(tf);
                redoStack.clear();
                frame.repaint();
            }
        }
        
        else if(command.equals("UNDO"))
        {
        	Command cmd = undoStack.pop();
            cmd.unexecute();
            redoStack.push(cmd);
            model.setUndo(undoStack.isEmpty());
            model.setRedo(redoStack.isEmpty());
            frame.repaint();
        }
        else if(command.equals("REDO"))
        {
        	Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
            model.setUndo(undoStack.isEmpty());
            model.setRedo(redoStack.isEmpty());
            frame.repaint();
        }



    }
    
    public void setLoadedLines(List<String> lines) {
        this.loadedLines = new ArrayList<>(lines);
        this.currentLineIndex = 0;
    }
    
    private Shape findShapeInModel(String shapeStringFromLog) {
        for (Shape s : model.getShapes()) {
            String modelShapeString = "";
            
            // rekonst toStringa
            if (s instanceof Point || s instanceof Line) {
                modelShapeString = s.toString() + ";Color:" + s.getBorderColor().getRGB();
            } else {
                modelShapeString = s.toString() + ";Border color:" + s.getBorderColor().getRGB() + ";Area color:" + s.getAreaColor().getRGB();
            }

            // nasla ako su isti
            if (modelShapeString.equals(shapeStringFromLog)) {
                return s;
            }
        }
        return null;
    }
    
    
}