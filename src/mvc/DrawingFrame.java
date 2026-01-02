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
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawingFrame extends JFrame {
	DrawingController controller;
	DrawingView view = new DrawingView();


	
	public DrawingFrame() {
		getContentPane().setBackground(new Color(192, 192, 192));
		
		setTitle("Sara crta ovde");
		getContentPane().setSize(200, 200);
		getContentPane().setLayout(null);
		
		JToggleButton tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.setBounds(32, 23, 70, 26);
		getContentPane().add(tglbtnPoint);
		
		JPanel view = new JPanel();
		view.setBounds(20, 69, 1049, 484);
		view.setBackground(new Color(255, 255, 255));
		getContentPane().add(view);
		
		JToggleButton tglbtnLine = new JToggleButton("Line");
		tglbtnLine.setBounds(112, 23, 70, 26);
		getContentPane().add(tglbtnLine);
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.setBounds(189, 23, 84, 26);
		getContentPane().add(tglbtnRectangle);
		
		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.setBounds(283, 23, 70, 26);
		getContentPane().add(tglbtnCircle);
		
		JToggleButton Donut = new JToggleButton("Donut");
		Donut.setBounds(363, 23, 70, 26);
		getContentPane().add(Donut);
		
		JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnHexagon.setBounds(443, 23, 84, 26);
		getContentPane().add(tglbtnHexagon);
		
		JToggleButton tglbtnModify = new JToggleButton("Modify");
		tglbtnModify.setBounds(852, 23, 84, 26);
		getContentPane().add(tglbtnModify);
		
		JToggleButton tglbtnDelete = new JToggleButton("Delete");
		tglbtnDelete.setBounds(946, 23, 84, 26);
		getContentPane().add(tglbtnDelete);
		setVisible(true);
		
		
	}

	
	
	
	
	
	
	
	
	
	
	public DrawingView getView() {
		return view;
	}

	public void SetController(DrawingController controller) {
		this.controller=controller;
	}
}
