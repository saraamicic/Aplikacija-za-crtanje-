package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import drawing.PnlDrawing;
import drawing.FrmDrawing.ShapeTypes;
import javax.swing.border.LineBorder;

import command.BringToBackCmd;

import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class DrawingFrame extends JFrame {
	DrawingController controller;
	DrawingView view = new DrawingView();
	private final ButtonGroup buttonGroupShapes = new ButtonGroup();
	
	public DrawingFrame() {
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Sara crta ovde");
		getContentPane().setSize(200, 200);
		getContentPane().setLayout(null);
		view.setBounds(20, 69, 1049, 449);
		view.setBackground(new Color(255, 255, 255));
		getContentPane().add(view);
		
		JToggleButton tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				controller.setShapeType(ShapeTypes.POINT);
			}
		});
		buttonGroupShapes.add(tglbtnPoint);
		tglbtnPoint.setBounds(32, 23, 70, 26);
		getContentPane().add(tglbtnPoint);
		
		
		JToggleButton tglbtnLine = new JToggleButton("Line");
		tglbtnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.LINE);
			}
		});
		buttonGroupShapes.add(tglbtnLine);
		tglbtnLine.setBounds(112, 23, 70, 26);
		getContentPane().add(tglbtnLine);
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.RECTANGLE);
			}
		});
		buttonGroupShapes.add(tglbtnRectangle);
		tglbtnRectangle.setBounds(189, 23, 84, 26);
		getContentPane().add(tglbtnRectangle);
		
		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.CIRCLE);
			}
		});
		buttonGroupShapes.add(tglbtnCircle);
		tglbtnCircle.setBounds(283, 23, 70, 26);
		getContentPane().add(tglbtnCircle);
		
		JToggleButton tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.DONUT);
			}
		});
		buttonGroupShapes.add(tglbtnDonut);
		tglbtnDonut.setBounds(363, 23, 70, 26);
		getContentPane().add(tglbtnDonut);
		
		JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
		buttonGroupShapes.add(tglbtnHexagon);
		tglbtnHexagon.setBounds(443, 23, 84, 26);
		getContentPane().add(tglbtnHexagon);
		
		JToggleButton tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Modify();
			}
		});
		buttonGroupShapes.add(tglbtnModify);
		tglbtnModify.setBounds(891, 23, 84, 26);
		getContentPane().add(tglbtnModify);
		
		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Delete();
			}
		});
		buttonGroupShapes.add(tglbtnDelete);
		tglbtnDelete.setBounds(985, 23, 84, 26);
		getContentPane().add(tglbtnDelete);
		
		JToggleButton tglbtnUndo = new JToggleButton("Undo");
		tglbtnUndo.setBounds(723, 23, 70, 26);
		getContentPane().add(tglbtnUndo);
		
		JToggleButton tglbtnRedo = new JToggleButton("Redo");
		tglbtnRedo.setBounds(803, 23, 76, 26);
		getContentPane().add(tglbtnRedo);
		
		JToggleButton tglbtnToFront = new JToggleButton("To Front");
		tglbtnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.ToFront();
			}
		});
		tglbtnToFront.setBounds(606, 528, 96, 23);
		getContentPane().add(tglbtnToFront);
		
		JToggleButton tglbtnToBack = new JToggleButton("To Back");
		tglbtnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.ToBack();
			}
		});
		tglbtnToBack.setBounds(712, 528, 96, 23);
		getContentPane().add(tglbtnToBack);
		

		JToggleButton tglbtnBringToBack = new JToggleButton("Bring To Back");
		tglbtnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.BringToBack();
			}
		});
		tglbtnBringToBack.setBounds(818, 528, 127, 23);
		getContentPane().add(tglbtnBringToBack);
		
		JToggleButton tglbtnBringToFront = new JToggleButton("Bring To Front");
		tglbtnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.BringToFront();
			}
		});
		tglbtnBringToFront.setBounds(955, 528, 114, 23);
		getContentPane().add(tglbtnBringToFront);
		setVisible(true);
		
		
	}
	
	
	public ButtonGroup getButtonGroupShapes() {
		return buttonGroupShapes;
	}

	public DrawingView getView() {
		return view;
	}

	public void SetController(DrawingController controller) {
		this.controller=controller;
	}



}
