package drawing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JToggleButton;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.LineBorder;

import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.DrawingView;

import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.UIManager;

public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroupShapes = new ButtonGroup();
	private Point startPoint = null;
	private Shape shape = null;
	private Shape currentShape;
	private DrawingView view;

	
	public enum ShapeTypes {
		POINT, LINE, RECTANGLE, CIRCLE, DONUT, NONE
	}// enum je skup konstanti

	public ShapeTypes shapeType = ShapeTypes.NONE;

	public ShapeTypes getShapeType() {
		return shapeType;
	}

	public void setShapeType(ShapeTypes shapeType) {
		this.shapeType = shapeType;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		setBackground(new Color(255, 255, 255));

		setTitle("Sara Micic IT58/2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1185, 653);
		contentPane = new JPanel();
		
		contentPane.setBackground(new Color(254, 224, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_view = new GridBagLayout();
		gbl_view.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 521, 0 };
		gbl_view.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 124, 0 };
		gbl_view.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gbl_view.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_view);
		setVisible(true);

		PnlDrawing pnlDrawing = new PnlDrawing();
		contentPane.add(pnlDrawing);
		setVisible(true);
		pnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				switch (shapeType) {
				case POINT:

					Point p = new Point(e.getX(), e.getY());
					DlgPoint dlgPoint = new DlgPoint(p);
					dlgPoint.getTextFieldXCoordinate().setEditable(false);
					dlgPoint.getTextFieldYCoordinate().setEditable(false);
					dlgPoint.setVisible(true);
					if (dlgPoint.isOk()) {

						pnlDrawing.addShape(p);
						p.setBorderColor(dlgPoint.getColorPoint());
						

					}
					setShapeType(ShapeTypes.NONE);
					buttonGroupShapes.clearSelection();
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
							pnlDrawing.addShape(l);
							

						}
						setShapeType(ShapeTypes.NONE);
						buttonGroupShapes.clearSelection();
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
						pnlDrawing.addShape(r1);
						r1.setBorderColor(dlgRectangle.getBorderColor());
						r1.setAreaColor(dlgRectangle.getAreaColor());
						
					}
					setShapeType(ShapeTypes.NONE);
					buttonGroupShapes.clearSelection();
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
						pnlDrawing.addShape(c1);
						c1.setBorderColor(dlgCircle.getBorderColor());
						c1.setAreaColor(dlgCircle.getAreaColor());
						
					}
					setShapeType(ShapeTypes.NONE);
					buttonGroupShapes.clearSelection();
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
						pnlDrawing.addShape(d1);
						d1.setBorderColor(dlgDonut.getBorderColor());
						d1.setAreaColor(dlgDonut.getAreaColor());
						
					}
					setShapeType(ShapeTypes.NONE);
					buttonGroupShapes.clearSelection();
					break;

				case NONE:
					setShapeType(ShapeTypes.NONE);
					break;

				default:
					setShapeType(ShapeTypes.NONE);
					break;

				}
				repaint();

			}

			public void mousePressed(MouseEvent e) {
				Point clickedPoint = new Point(e.getX(), e.getY());
				currentShape = pnlDrawing.getSelected(clickedPoint);
				if (shape != null && currentShape != null) {
					if (shape.equals(currentShape)) {
						currentShape.setSelected(false);
						shape.setSelected(false);
						shape = null;
						repaint();
						return;
					}
				}

				if (currentShape != null) {
					currentShape.setSelected(true);
				}
				if (currentShape == null && shape != null) {
					shape.setSelected(false);
					pnlDrawing.repaint();
				}
				repaint();
				shape = currentShape;
			}
		});

		JToggleButton tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.setFont(new Font("Arial Nova", Font.BOLD, 12));
		tglbtnPoint.setBackground(UIManager.getColor("Button.background"));
		tglbtnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				setShapeType(shapeType.POINT);

			}
		});
		buttonGroupShapes.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPoint.gridx = 3;
		gbc_tglbtnPoint.gridy = 0;
		contentPane.add(tglbtnPoint, gbc_tglbtnPoint);

		JToggleButton tglbtnLine = new JToggleButton("Line");
		tglbtnLine.setFont(new Font("Arial Nova", Font.BOLD, 12));
		tglbtnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setShapeType(shapeType.LINE);

			}
		});
		buttonGroupShapes.add(tglbtnLine);
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnLine.gridx = 4;
		gbc_tglbtnLine.gridy = 0;
		contentPane.add(tglbtnLine, gbc_tglbtnLine);

		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.setFont(new Font("Arial Nova", Font.BOLD, 12));
		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setShapeType(shapeType.RECTANGLE);

			}
		});
		buttonGroupShapes.add(tglbtnRectangle);
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnRectangle.gridx = 5;
		gbc_tglbtnRectangle.gridy = 0;
		contentPane.add(tglbtnRectangle, gbc_tglbtnRectangle);

		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.setFont(new Font("Arial Nova", Font.BOLD, 12));
		tglbtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setShapeType(shapeType.CIRCLE);

			}
		});
		buttonGroupShapes.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCircle.gridx = 6;
		gbc_tglbtnCircle.gridy = 0;
		contentPane.add(tglbtnCircle, gbc_tglbtnCircle);

		JToggleButton tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.setFont(new Font("Arial Nova", Font.BOLD, 12));
		tglbtnDonut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setShapeType(shapeType.DONUT);

			}
		});
		buttonGroupShapes.add(tglbtnDonut);
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnDonut.gridx = 7;
		gbc_tglbtnDonut.gridy = 0;
		contentPane.add(tglbtnDonut, gbc_tglbtnDonut);

		JToggleButton tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setFont(new Font("Arial Nova", Font.BOLD, 12));
		tglbtnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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

					buttonGroupShapes.clearSelection();

				} else {
					JOptionPane.showMessageDialog(null, "Select the shape you want to modify first!", "",
							JOptionPane.ERROR_MESSAGE);
				}

				buttonGroupShapes.clearSelection();
				shape = null;
				repaint();
			}
		});
	
		buttonGroupShapes.add(tglbtnModify);
		GridBagConstraints gbc_tglbtnModify = new GridBagConstraints();
		gbc_tglbtnModify.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnModify.gridx = 12;
		gbc_tglbtnModify.gridy = 0;
		contentPane.add(tglbtnModify, gbc_tglbtnModify);

		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setFont(new Font("Arial Nova", Font.BOLD, 12));
		tglbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (shape != null) {
					pnlDrawing.delete();
					shape = null;
				} else {
					JOptionPane.showMessageDialog(null, "Select the shape you want to delete!", "",
							JOptionPane.ERROR_MESSAGE);
				}
				repaint();
				buttonGroupShapes.clearSelection();

			}
		});
		buttonGroupShapes.add(tglbtnDelete);
		GridBagConstraints gbc_tglbtnDelete = new GridBagConstraints();
		gbc_tglbtnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnDelete.gridx = 13;
		gbc_tglbtnDelete.gridy = 0;
		contentPane.add(tglbtnDelete, gbc_tglbtnDelete);
		setVisible(true);

		pnlDrawing.setBorder(new LineBorder(new Color(219, 198, 214), 3, true));
		pnlDrawing.setBackground(Color.WHITE);
		GridBagConstraints gbc_pnlDrawing = new GridBagConstraints();
		gbc_pnlDrawing.gridwidth = 15;
		gbc_pnlDrawing.gridheight = 13;
		gbc_pnlDrawing.gridx = 0;
		gbc_pnlDrawing.gridy = 2;
		getContentPane().add(pnlDrawing, gbc_pnlDrawing);
		
		GridBagLayout gbl_pnlDrawing = new GridBagLayout();
		gbl_pnlDrawing.columnWidths = new int[] { 0 };
		gbl_pnlDrawing.rowHeights = new int[] { 0 };
		gbl_pnlDrawing.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_pnlDrawing.rowWeights = new double[] { Double.MIN_VALUE };
		pnlDrawing.setLayout(gbl_pnlDrawing);
		setVisible(true);

	}

	public ButtonGroup getButtonGroupShapes() {
		return buttonGroupShapes;
	}

	

}
