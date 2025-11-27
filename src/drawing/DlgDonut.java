package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DlgDonut extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldDonutRadius;
	private JTextField textFieldInnerRadius;
	private JTextField textFieldXDonutCenter;
	private JTextField textFieldYDonutCenter;
	private boolean isOk;
	private Color borderColor;
	private Color areaColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut(Donut d) {
		setModal(true);
		setTitle("Sara Micic IT58/2023");
		setBounds(100, 100, 816, 434);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 153, 283, 95, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 98, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblDonutRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblDonutRadius = new GridBagConstraints();
			gbc_lblDonutRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblDonutRadius.anchor = GridBagConstraints.EAST;
			gbc_lblDonutRadius.gridx = 4;
			gbc_lblDonutRadius.gridy = 2;
			contentPanel.add(lblDonutRadius, gbc_lblDonutRadius);
		}
		{
			textFieldDonutRadius = new JTextField();
			GridBagConstraints gbc_textFieldDonutRadius = new GridBagConstraints();
			gbc_textFieldDonutRadius.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldDonutRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDonutRadius.gridx = 5;
			gbc_textFieldDonutRadius.gridy = 2;
			contentPanel.add(textFieldDonutRadius, gbc_textFieldDonutRadius);
			textFieldDonutRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner Radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblInnerRadius.gridx = 4;
			gbc_lblInnerRadius.gridy = 3;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			textFieldInnerRadius = new JTextField();
			GridBagConstraints gbc_textFieldInnerRadius = new GridBagConstraints();
			gbc_textFieldInnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldInnerRadius.gridx = 5;
			gbc_textFieldInnerRadius.gridy = 3;
			contentPanel.add(textFieldInnerRadius, gbc_textFieldInnerRadius);
			textFieldInnerRadius.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Choose color");
			btnColor.setFont(new Font("Arial Nova", Font.BOLD, 12));
			btnColor.setBackground(new Color(254, 224, 247));
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					borderColor = JColorChooser.showDialog(null, "Choose border color", Color.black);
					areaColor = JColorChooser.showDialog(null,"Choose area color", Color.white);
					
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 5, 0);
			gbc_btnColor.gridx = 6;
			gbc_btnColor.gridy = 4;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JLabel lblDonutCenter = new JLabel("Center coordinates:");
			GridBagConstraints gbc_lblDonutCenter = new GridBagConstraints();
			gbc_lblDonutCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblDonutCenter.anchor = GridBagConstraints.NORTH;
			gbc_lblDonutCenter.gridx = 5;
			gbc_lblDonutCenter.gridy = 5;
			contentPanel.add(lblDonutCenter, gbc_lblDonutCenter);
		}
		{
			JLabel lblXDonutCenter = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblXDonutCenter = new GridBagConstraints();
			gbc_lblXDonutCenter.anchor = GridBagConstraints.EAST;
			gbc_lblXDonutCenter.insets = new Insets(0, 0, 5, 5);
			gbc_lblXDonutCenter.gridx = 4;
			gbc_lblXDonutCenter.gridy = 6;
			contentPanel.add(lblXDonutCenter, gbc_lblXDonutCenter);
		}
		{
			textFieldXDonutCenter = new JTextField();
			textFieldXDonutCenter.setText(Integer.toString(d.getCenter().getXCoordinate()));
			GridBagConstraints gbc_textFieldXDonutCenter = new GridBagConstraints();
			gbc_textFieldXDonutCenter.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldXDonutCenter.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldXDonutCenter.gridx = 5;
			gbc_textFieldXDonutCenter.gridy = 6;
			contentPanel.add(textFieldXDonutCenter, gbc_textFieldXDonutCenter);
			textFieldXDonutCenter.setColumns(10);
		}
		{
			JLabel lblYDonutCenter = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblYDonutCenter = new GridBagConstraints();
			gbc_lblYDonutCenter.anchor = GridBagConstraints.EAST;
			gbc_lblYDonutCenter.insets = new Insets(0, 0, 0, 5);
			gbc_lblYDonutCenter.gridx = 4;
			gbc_lblYDonutCenter.gridy = 7;
			contentPanel.add(lblYDonutCenter, gbc_lblYDonutCenter);
		}
		{
			textFieldYDonutCenter = new JTextField();
			textFieldYDonutCenter.setText(Integer.toString(d.getCenter().getYCoordinate()));
			GridBagConstraints gbc_textFieldYDonutCenter = new GridBagConstraints();
			gbc_textFieldYDonutCenter.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldYDonutCenter.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldYDonutCenter.gridx = 5;
			gbc_textFieldYDonutCenter.gridy = 7;
			contentPanel.add(textFieldYDonutCenter, gbc_textFieldYDonutCenter);
			textFieldYDonutCenter.setColumns(10);
		}
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
						
						try {
						if( Integer.parseInt(textFieldDonutRadius.getText())>0 && Integer.parseInt(textFieldInnerRadius.getText())>0 )
						{
							if(Integer.parseInt(textFieldInnerRadius.getText())<Integer.parseInt(textFieldDonutRadius.getText()))
							{
								setOk(true);
								setVisible(false);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Inner radius must be smaller then radius!","",JOptionPane.ERROR_MESSAGE);
							}
							
						}
						
						else
						{
							JOptionPane.showMessageDialog(null, "Radius and inner radius have to be positive numbers!","",JOptionPane.ERROR_MESSAGE);
						}}
						catch(Exception e1)
						{
							JOptionPane.showMessageDialog(null, "Enter correct values for radius and inner radius!","",JOptionPane.ERROR_MESSAGE);
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
						d.setSelected(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}

	public JTextField getTextFieldDonutRadius() {
		return textFieldDonutRadius;
	}

	public void setTextFieldDonutRadius(JTextField textFieldDonutRadius) {
		this.textFieldDonutRadius = textFieldDonutRadius;
	}

	public JTextField getTextFieldInnerRadius() {
		return textFieldInnerRadius;
	}

	public void setTextFieldInnerRadius(JTextField textFieldInnerRadius) {
		this.textFieldInnerRadius = textFieldInnerRadius;
	}

	public JTextField getTextFieldXDonutCenter() {
		return textFieldXDonutCenter;
	}

	public void setTextFieldXDonutCenter(JTextField textFieldXDonutCenter) {
		this.textFieldXDonutCenter = textFieldXDonutCenter;
	}

	public JTextField getTextFieldYDonutCenter() {
		return textFieldYDonutCenter;
	}

	public void setTextFieldYDonutCenter(JTextField textFieldYDonutCenter) {
		this.textFieldYDonutCenter = textFieldYDonutCenter;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
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
