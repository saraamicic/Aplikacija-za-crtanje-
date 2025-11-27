package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Circle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgCircle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldXCenter;
	private JTextField textFieldYCenter;
	private JTextField textFieldRadius;
	private boolean isOk;
	
	private Color borderColor;
	private Color areaColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgCircle dialog = new DlgCircle(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgCircle(Circle c) {
		setModal(true);
		setTitle("Sara Micic IT58/2023");
		setBounds(100, 100, 649, 652);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 146, 0, 0, 0, -15, -43, 0};
		gbl_contentPanel.rowHeights = new int[]{128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 4;
			gbc_lblRadius.gridy = 1;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			textFieldRadius = new JTextField();
			GridBagConstraints gbc_textFieldRadius = new GridBagConstraints();
			gbc_textFieldRadius.gridwidth = 5;
			gbc_textFieldRadius.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldRadius.gridx = 5;
			gbc_textFieldRadius.gridy = 1;
			contentPanel.add(textFieldRadius, gbc_textFieldRadius);
			textFieldRadius.setColumns(10);
		}
		{
			JLabel lblCenter = new JLabel("Center coordinates:");
			GridBagConstraints gbc_lblCenter = new GridBagConstraints();
			gbc_lblCenter.gridwidth = 5;
			gbc_lblCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenter.gridx = 5;
			gbc_lblCenter.gridy = 6;
			contentPanel.add(lblCenter, gbc_lblCenter);
		}
		{
			JLabel lblXCenter = new JLabel("X Coordinate:");
			GridBagConstraints gbc_lblXCenter = new GridBagConstraints();
			gbc_lblXCenter.anchor = GridBagConstraints.EAST;
			gbc_lblXCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCenter.gridx = 4;
			gbc_lblXCenter.gridy = 7;
			contentPanel.add(lblXCenter, gbc_lblXCenter);
		}
		{
			textFieldXCenter = new JTextField();
			textFieldXCenter.setText(Integer.toString(c.getCenter().getXCoordinate()));
			GridBagConstraints gbc_textFieldXCenter = new GridBagConstraints();
			gbc_textFieldXCenter.gridwidth = 5;
			gbc_textFieldXCenter.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldXCenter.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldXCenter.gridx = 5;
			gbc_textFieldXCenter.gridy = 7;
			contentPanel.add(textFieldXCenter, gbc_textFieldXCenter);
			textFieldXCenter.setColumns(10);
		}
		{
			JLabel lblYCenter = new JLabel("Y Coordinate:");
			GridBagConstraints gbc_lblYCenter = new GridBagConstraints();
			gbc_lblYCenter.anchor = GridBagConstraints.EAST;
			gbc_lblYCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCenter.gridx = 4;
			gbc_lblYCenter.gridy = 10;
			contentPanel.add(lblYCenter, gbc_lblYCenter);
		}
		{
			{
				textFieldYCenter = new JTextField();
				textFieldYCenter.setText(Integer.toString(c.getCenter().getYCoordinate()));
				GridBagConstraints gbc_textFieldYCenter = new GridBagConstraints();
				gbc_textFieldYCenter.gridwidth = 5;
				gbc_textFieldYCenter.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldYCenter.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldYCenter.gridx = 5;
				gbc_textFieldYCenter.gridy = 10;
				contentPanel.add(textFieldYCenter, gbc_textFieldYCenter);
				textFieldYCenter.setColumns(10);
			}
		}
		JButton btnColor = new JButton("Choose color");
		btnColor.setBackground(new Color(254, 224, 247));
		btnColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				borderColor = JColorChooser.showDialog(null, "Choose border color", Color.black);
				areaColor = JColorChooser.showDialog(null, "Choose area color", Color.white);
				
			}
		});
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.gridheight = 5;
		gbc_btnColor.gridwidth = 15;
		gbc_btnColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnColor.gridx = 0;
		gbc_btnColor.gridy = 15;
		contentPanel.add(btnColor, gbc_btnColor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(254, 224, 247));
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						try
						{
							if(Integer.parseInt(textFieldRadius.getText()) > 0 )
							{
								setOk(true);
								setVisible(false);
							}
							
							else
							{
								JOptionPane.showMessageDialog(null, "Radius has to be a positive number!","",JOptionPane.ERROR_MESSAGE);
							}
							
						}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Error with value of radius!","",JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(254, 224, 247));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
						c.setSelected(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public JTextField getTextFieldXCenter() {
		return textFieldXCenter;
	}

	public void setTextFieldXCenter(JTextField textFieldXCenter) {
		this.textFieldXCenter = textFieldXCenter;
	}

	public JTextField getTextFieldYCenter() {
		return textFieldYCenter;
	}

	public void setTextFieldYCenter(JTextField textFieldYCenter) {
		this.textFieldYCenter = textFieldYCenter;
	}

	public JTextField getTextFieldRadius() {
		return textFieldRadius;
	}

	public void setTextFieldRadius(JTextField textFieldRadius) {
		this.textFieldRadius = textFieldRadius;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
	
}
