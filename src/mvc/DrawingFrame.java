package mvc;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import mvc.DrawingModel.ShapeTypes;

import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DrawingFrame extends JFrame {
	DrawingController controller;
	DrawingView view = new DrawingView();
	private final ButtonGroup buttonGroupShapes = new ButtonGroup();
	JTextArea textAreaLog;
	Color defaultBorderColor;
	Color defaultAreaColor;
	JButton btnUndo;
	JButton btnRedo;
	
	public DrawingFrame() {
		getContentPane().setBackground(new Color(192, 192, 192));
		setTitle("Sara crta ovde");
		getContentPane().setSize(200, 200);
		getContentPane().setLayout(null);
		view.setBounds(409, 69, 734, 449);
		view.setBackground(new Color(255, 255, 255));
		getContentPane().add(view);
		defaultBorderColor = Color.BLACK;
		defaultAreaColor = Color.WHITE;
		
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
		tglbtnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.HEXAGON);
			}
		});
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
		
		btnUndo = new JButton("Undo");
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Undo();
				btnUndo.setSelected(false);
			}
		});
		btnUndo.setBounds(409, 528, 70, 26);
		getContentPane().add(btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Redo();
				btnRedo.setSelected(false);
				
			}
		});
		btnRedo.setBounds(489, 528, 76, 26);
		getContentPane().add(btnRedo);
		
		JToggleButton tglbtnToFront = new JToggleButton("To Front");
		tglbtnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.ToFront();
			}
		});
		tglbtnToFront.setBounds(661, 530, 96, 23);
		getContentPane().add(tglbtnToFront);
		
		JToggleButton tglbtnToBack = new JToggleButton("To Back");
		tglbtnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.ToBack();
				tglbtnToBack.setSelected(false);
			}
		});
		tglbtnToBack.setBounds(774, 530, 96, 23);
		getContentPane().add(tglbtnToBack);
		

		JToggleButton tglbtnBringToBack = new JToggleButton("Bring To Back");
		tglbtnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.BringToBack();
				tglbtnBringToBack.setSelected(false);
			}
		});
		tglbtnBringToBack.setBounds(880, 528, 127, 23);
		getContentPane().add(tglbtnBringToBack);
		
		JToggleButton tglbtnBringToFront = new JToggleButton("Bring To Front");
		tglbtnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.BringToFront();
				tglbtnBringToFront.setSelected(false);
			}
		});
		tglbtnBringToFront.setBounds(1017, 528, 126, 23);
		getContentPane().add(tglbtnBringToFront);
		
		textAreaLog = new JTextArea();
		textAreaLog.setBackground(new Color(235, 235, 235));
		textAreaLog.setBounds(22, 69, 240, 449);
		
		setVisible(true);
		textAreaLog.setEnabled(false);
		textAreaLog.setDisabledTextColor(Color.black);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 69, 355, 449);
		getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(textAreaLog);
		
		JLabel lblLog = new JLabel("Command Log:");
		lblLog.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblLog);
		
		JButton btnDefaultBorderColor = new JButton("Border Color");
		btnDefaultBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				defaultBorderColor = JColorChooser.showDialog(null, "Choose default border color", Color.black);
				btnDefaultBorderColor.setBackground(defaultBorderColor);
			}
		});
		btnDefaultBorderColor.setBounds(649, 23, 108, 26);
		getContentPane().add(btnDefaultBorderColor);
		
		JButton btnDefaultAreaColor = new JButton("Area Color");
		btnDefaultAreaColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				defaultAreaColor = JColorChooser.showDialog(null, "Choose default area color", Color.black);
				btnDefaultAreaColor.setBackground(defaultAreaColor);
			}
		});
		btnDefaultAreaColor.setBounds(767, 23, 102, 26);
		getContentPane().add(btnDefaultAreaColor);
		
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
	public JTextArea getTextArea()
	{
		return textAreaLog;
	}

	public Color getDefaultBorderColor() {
		return defaultBorderColor;
	}


	public void setDefaultBorderColor(Color defaultBorderColor) {
		this.defaultBorderColor = defaultBorderColor;
	}


	public Color getDefaultAreaColor() {
		return defaultAreaColor;
	}


	public void setDefaultAreaColor(Color defaultAreaColor) {
		this.defaultAreaColor = defaultAreaColor;
	}
	
	public JButton getBtnUndo()
	{
		return btnUndo;
	}
	
	public JButton getBtnRedo()
	{
		return btnRedo;
	}
	
}
