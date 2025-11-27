package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DlgRectangle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldXUpperLeft;
	private JTextField textFieldYUpperLeft;
	private JTextField textFieldWidth;
	private JTextField textFieldHeight;
	private Color borderColor;
	private Color areaColor;


	private boolean isOk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle(Rectangle r) {
		setModal(true);
		setTitle("Sara Micic IT58/2023");
		setBounds(100, 100, 798, 351);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 343, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblWidth = new JLabel("Width:");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.anchor = GridBagConstraints.EAST;
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 3;
			gbc_lblWidth.gridy = 1;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			textFieldWidth = new JTextField();
			GridBagConstraints gbc_textFieldWidth = new GridBagConstraints();
			gbc_textFieldWidth.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldWidth.gridx = 4;
			gbc_textFieldWidth.gridy = 1;
			contentPanel.add(textFieldWidth, gbc_textFieldWidth);
			textFieldWidth.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.anchor = GridBagConstraints.EAST;
			gbc_lblHeight.gridx = 3;
			gbc_lblHeight.gridy = 2;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			textFieldHeight = new JTextField();
			GridBagConstraints gbc_textFieldHeight = new GridBagConstraints();
			gbc_textFieldHeight.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldHeight.gridx = 4;
			gbc_textFieldHeight.gridy = 2;
			contentPanel.add(textFieldHeight, gbc_textFieldHeight);
			textFieldHeight.setColumns(10);
		}
		{
			JLabel lblUpperLeftPoint = new JLabel("Upper left point coordinates:");
			GridBagConstraints gbc_lblUpperLeftPoint = new GridBagConstraints();
			gbc_lblUpperLeftPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperLeftPoint.gridx = 4;
			gbc_lblUpperLeftPoint.gridy = 3;
			contentPanel.add(lblUpperLeftPoint, gbc_lblUpperLeftPoint);
		}
		{
			JLabel lblXUpperLeft = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblXUpperLeft = new GridBagConstraints();
			gbc_lblXUpperLeft.anchor = GridBagConstraints.EAST;
			gbc_lblXUpperLeft.insets = new Insets(0, 0, 5, 5);
			gbc_lblXUpperLeft.gridx = 3;
			gbc_lblXUpperLeft.gridy = 4;
			contentPanel.add(lblXUpperLeft, gbc_lblXUpperLeft);
		}
		{
			textFieldXUpperLeft = new JTextField();
			textFieldXUpperLeft.setText(Integer.toString(r.getUpperLeftPoint().getXCoordinate()));
			GridBagConstraints gbc_textFieldXUpperLeft = new GridBagConstraints();
			gbc_textFieldXUpperLeft.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldXUpperLeft.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldXUpperLeft.gridx = 4;
			gbc_textFieldXUpperLeft.gridy = 4;
			contentPanel.add(textFieldXUpperLeft, gbc_textFieldXUpperLeft);
			textFieldXUpperLeft.setColumns(10);
		}
		{
			JLabel lblYUpperLeft = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblYUpperLeft = new GridBagConstraints();
			gbc_lblYUpperLeft.anchor = GridBagConstraints.EAST;
			gbc_lblYUpperLeft.insets = new Insets(0, 0, 5, 5);
			gbc_lblYUpperLeft.gridx = 3;
			gbc_lblYUpperLeft.gridy = 5;
			contentPanel.add(lblYUpperLeft, gbc_lblYUpperLeft);
		}
		{
			textFieldYUpperLeft = new JTextField();
			textFieldYUpperLeft.setText(Integer.toString(r.getUpperLeftPoint().getYCoordinate()));
			GridBagConstraints gbc_textFieldYUpperLeft = new GridBagConstraints();
			gbc_textFieldYUpperLeft.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldYUpperLeft.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldYUpperLeft.gridx = 4;
			gbc_textFieldYUpperLeft.gridy = 5;
			contentPanel.add(textFieldYUpperLeft, gbc_textFieldYUpperLeft);
			textFieldYUpperLeft.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Choose color");
			btnColor.setFont(new Font("Arial Nova", Font.BOLD, 12));
			btnColor.setBackground(new Color(254, 224, 247));
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					borderColor = JColorChooser.showDialog(null, "Choose border color", Color.black);
					areaColor = JColorChooser.showDialog(null, "Choose area color", Color.white);
					
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.gridwidth = 6;
			gbc_btnColor.gridx = 0;
			gbc_btnColor.gridy = 8;
			contentPanel.add(btnColor, gbc_btnColor);
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

							if (Integer.parseInt(textFieldWidth.getText()) > 0
									&& Integer.parseInt(textFieldHeight.getText()) > 0) {
								setOk(true);
								setVisible(false);
							}

							else {
								JOptionPane.showMessageDialog(null, "Width and height have to be positive numbers!", "",
										JOptionPane.ERROR_MESSAGE);

							}
						}

						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Input the correct data type!", "",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(240, 240, 240));
				cancelButton.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
						r.setSelected(false);
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	public JTextField getTextFieldXUpperLeft() {
		return textFieldXUpperLeft;
	}

	public void setTextFieldXUpperLeft(JTextField textFieldXUpperLeft) {
		this.textFieldXUpperLeft = textFieldXUpperLeft;
	}

	public JTextField getTextFieldYUpperLeft() {
		return textFieldYUpperLeft;
	}

	public void setTextFieldYUpperLeft(JTextField textFieldYUpperLeft) {
		this.textFieldYUpperLeft = textFieldYUpperLeft;
	}

	public JTextField getTextFieldWidth() {
		return textFieldWidth;
	}

	public void setTextFieldWidth(JTextField textFieldWidth) {
		this.textFieldWidth = textFieldWidth;
	}

	public JTextField getTextFieldHeight() {
		return textFieldHeight;
	}

	public void setTextFieldHeight(JTextField textFieldHeight) {
		this.textFieldHeight = textFieldHeight;
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
