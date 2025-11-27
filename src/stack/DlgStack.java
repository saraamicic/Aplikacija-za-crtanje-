package stack;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class DlgStack extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldXCoordinate;
	private JTextField textFieldYCoordinate;
	private JTextField textFieldRadius;
	private JTextField textFieldInnerRadius;
	private boolean isOk;
	private Donut donut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgStack dialog = new DlgStack();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgStack() {

		setModal(true);
		setTitle("Sara Micic IT58/2023");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] {};
		gridBagLayout.rowWeights = new double[] {};
		getContentPane().setLayout(gridBagLayout);
		setBounds(100, 100, 1056, 394);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblXCoordinate = new JLabel("X coordinate:");
			GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
			gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblXCoordinate.gridx = 1;
			gbc_lblXCoordinate.gridy = 1;
			contentPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		}
		{
			textFieldXCoordinate = new JTextField();
			GridBagConstraints gbc_textFieldXCoordinate = new GridBagConstraints();
			gbc_textFieldXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldXCoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldXCoordinate.gridx = 2;
			gbc_textFieldXCoordinate.gridy = 1;
			contentPanel.add(textFieldXCoordinate, gbc_textFieldXCoordinate);
			textFieldXCoordinate.setColumns(10);
		}
		{
			JLabel lblYCoordinate = new JLabel("Y coordinate:");
			GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
			gbc_lblYCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblYCoordinate.gridx = 1;
			gbc_lblYCoordinate.gridy = 4;
			contentPanel.add(lblYCoordinate, gbc_lblYCoordinate);
		}
		{
			textFieldYCoordinate = new JTextField();
			GridBagConstraints gbc_textFieldYCoordinate = new GridBagConstraints();
			gbc_textFieldYCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldYCoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldYCoordinate.gridx = 2;
			gbc_textFieldYCoordinate.gridy = 4;
			contentPanel.add(textFieldYCoordinate, gbc_textFieldYCoordinate);
			textFieldYCoordinate.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius:");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.anchor = GridBagConstraints.EAST;
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 1;
			gbc_lblRadius.gridy = 8;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			textFieldRadius = new JTextField();
			GridBagConstraints gbc_textFieldRadius = new GridBagConstraints();
			gbc_textFieldRadius.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldRadius.gridx = 2;
			gbc_textFieldRadius.gridy = 8;
			contentPanel.add(textFieldRadius, gbc_textFieldRadius);
			textFieldRadius.setColumns(10);
		}
		{
			JLabel lblInnerRadius = new JLabel("Inner radius:");
			GridBagConstraints gbc_lblInnerRadius = new GridBagConstraints();
			gbc_lblInnerRadius.anchor = GridBagConstraints.EAST;
			gbc_lblInnerRadius.insets = new Insets(0, 0, 0, 5);
			gbc_lblInnerRadius.gridx = 1;
			gbc_lblInnerRadius.gridy = 11;
			contentPanel.add(lblInnerRadius, gbc_lblInnerRadius);
		}
		{
			textFieldInnerRadius = new JTextField();
			GridBagConstraints gbc_textFieldInnerRadius = new GridBagConstraints();
			gbc_textFieldInnerRadius.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldInnerRadius.gridx = 2;
			gbc_textFieldInnerRadius.gridy = 11;
			contentPanel.add(textFieldInnerRadius, gbc_textFieldInnerRadius);
			textFieldInnerRadius.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(254, 224, 247));
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							int xCoordinate = Integer.parseInt(textFieldXCoordinate.getText());
							int yCoordinate = Integer.parseInt(textFieldYCoordinate.getText());
							int radius = Integer.parseInt(textFieldRadius.getText());
							int innerRadius = Integer.parseInt(textFieldInnerRadius.getText());

							if ((radius > 0) && (innerRadius > 0)) {

								if (innerRadius > radius) {
									JOptionPane.showMessageDialog(null, "Inner radius has to be smaller than radius",
											"", JOptionPane.ERROR_MESSAGE);

								}

								else {
									isOk = true;
									Point center = new Point(xCoordinate, yCoordinate);
									donut = new Donut(center, radius, innerRadius);
									setVisible(false);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Radius and inner radius have to be bigger than 0",
										"", JOptionPane.ERROR_MESSAGE);

							}

						} catch (NumberFormatException e1) {

							JOptionPane.showMessageDialog(null, "Error occured with data input", "",
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
				cancelButton.setBackground(new Color(254, 224, 247));
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						setVisible(false);
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

	public JTextField getTextFieldXCoordinate() {
		return textFieldXCoordinate;
	}

	public void setTextFieldXCoordinate(JTextField textFieldXCoordinate) {
		this.textFieldXCoordinate = textFieldXCoordinate;
	}

	public JTextField getTextFieldYCoordinate() {
		return textFieldYCoordinate;
	}

	public void setTextFieldYCoordinate(JTextField textFieldYCoordinate) {
		this.textFieldYCoordinate = textFieldYCoordinate;
	}

	public JTextField getTextFieldRadius() {
		return textFieldRadius;
	}

	public void setTextFieldRadius(JTextField textFieldRadius) {
		this.textFieldRadius = textFieldRadius;
	}

	public JTextField getTextFieldInnerRadius() {
		return textFieldInnerRadius;
	}

	public void setTextFieldInnerRadius(JTextField textFieldInnerRadius) {
		this.textFieldInnerRadius = textFieldInnerRadius;
	}

	public Donut getDonut() {
		return donut;
	}

	public void setDonut(Donut donut) {
		this.donut = donut;
	}

}
