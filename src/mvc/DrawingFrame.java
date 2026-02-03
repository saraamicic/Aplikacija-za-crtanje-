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
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.MenuBar;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DrawingFrame extends JFrame {
	DrawingController controller;
	DrawingView view = new DrawingView();
	private final ButtonGroup buttonGroupShapes = new ButtonGroup();
	JTextArea textAreaLog;
	Color defaultBorderColor;
	Color defaultAreaColor;
	JButton btnUndo;
	JButton btnRedo;
	JButton btnDelete;
	JButton btnModify;
	JButton btnBringToBack;
	JButton btnBringToFront;
	JButton btnToBack;
	JButton btnToFront;
	
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
		tglbtnPoint.setBounds(22, 23, 70, 26);
		getContentPane().add(tglbtnPoint);
		
		JToggleButton tglbtnLine = new JToggleButton("Line");
		tglbtnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.LINE);
			}
		});
		buttonGroupShapes.add(tglbtnLine);
		tglbtnLine.setBounds(102, 23, 70, 26);
		getContentPane().add(tglbtnLine);
		
		JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.RECTANGLE);
			}
		});
		buttonGroupShapes.add(tglbtnRectangle);
		tglbtnRectangle.setBounds(182, 23, 84, 26);
		getContentPane().add(tglbtnRectangle);
		
		JToggleButton tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.CIRCLE);
			}
		});
		buttonGroupShapes.add(tglbtnCircle);
		tglbtnCircle.setBounds(276, 23, 70, 26);
		getContentPane().add(tglbtnCircle);
		
		JToggleButton tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.DONUT);
			}
		});
		buttonGroupShapes.add(tglbtnDonut);
		tglbtnDonut.setBounds(356, 23, 70, 26);
		getContentPane().add(tglbtnDonut);
		
		JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
		tglbtnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setShapeType(ShapeTypes.HEXAGON);
			}
		});
		buttonGroupShapes.add(tglbtnHexagon);
		tglbtnHexagon.setBounds(436, 23, 84, 26);
		getContentPane().add(tglbtnHexagon);
		
		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Modify();
			}
		});
		buttonGroupShapes.add(btnModify);
		btnModify.setBounds(891, 23, 84, 26);
		getContentPane().add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.Delete();
			}
		});
		//buttonGroupShapes.add(tglbtnDelete);
		btnDelete.setBounds(985, 23, 84, 26);
		getContentPane().add(btnDelete);
		
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
		
		btnToFront = new JButton("To Front");
		btnToFront.setEnabled(false);
		btnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.ToFront();
			}
		});
		btnToFront.setBounds(661, 530, 96, 23);
		getContentPane().add(btnToFront);
		
		btnToBack = new JButton("To Back");
		btnToBack.setEnabled(false);
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.ToBack();
				btnToBack.setSelected(false);
			}
		});
		btnToBack.setBounds(774, 530, 96, 23);
		getContentPane().add(btnToBack);
		

		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.BringToBack();
				btnBringToBack.setSelected(false);
			}
		});
		btnBringToBack.setBounds(880, 528, 127, 23);
		getContentPane().add(btnBringToBack);
		
		btnBringToFront = new JButton("Bring To Front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.BringToFront();
				btnBringToFront.setSelected(false);
			}
		});
		btnBringToFront.setBounds(1017, 528, 126, 23);
		getContentPane().add(btnBringToFront);
		
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
		btnDefaultBorderColor.setBounds(661, 23, 108, 26);
		getContentPane().add(btnDefaultBorderColor);
		
		JButton btnDefaultAreaColor = new JButton("Area Color");
		btnDefaultAreaColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				defaultAreaColor = JColorChooser.showDialog(null, "Choose default area color", Color.black);
				btnDefaultAreaColor.setBackground(defaultAreaColor);
			}
		});
		btnDefaultAreaColor.setBounds(779, 23, 102, 26);
		getContentPane().add(btnDefaultAreaColor);
		
		//za strategy
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setBounds(39, 23, 101, 22);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnSave = new JMenu("Save");
		mnFile.add(mnSave);
		
		JMenuItem mnOpen = new JMenuItem("Open");
		mnFile.add(mnOpen);
		
		JMenuItem mnSaveDrawing = new JMenuItem("Save Drawing (.bin)");
		mnSaveDrawing.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (controller != null) {
		            controller.saveDrawing();
		        }
		    }
		});
		mnSave.add(mnSaveDrawing);

		JMenuItem mnSaveLog = new JMenuItem("Save Log (.txt)");
		mnSaveLog.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (controller != null) {
		            controller.saveLog();
		        }
		    }
		});
		mnSave.add(mnSaveLog);
		
		
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
	
	public JButton getBtnDelete()
	{
		return btnDelete;
	}
	
	public JButton getBtnModify()
	{
		return btnModify;
	}
	
	public JButton getBtnBringToBack()
	{
		return btnBringToBack;
	}
	
	public JButton getBtnBringToFront()
	{
		return btnBringToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}


	public JButton getBtnToFront() {
		return btnToFront;
	}
}