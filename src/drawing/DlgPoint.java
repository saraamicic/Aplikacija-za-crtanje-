package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;


import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


public class DlgPoint extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldXCoordinate;
	private JTextField textFieldYCoordinate;
	private boolean ok;
	
	private Color colorPoint;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public DlgPoint(Point p) {
		
		setBackground(new Color(240, 240, 240));
		setModal(true);
		setTitle("Sara Micic IT58/2023");
		setBounds(100, 100, 746, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 240, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 133, 412, -71, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblXCoordinate = new JLabel("X Coordinate:");
			GridBagConstraints gbc_lblXCoordinate = new GridBagConstraints();
			gbc_lblXCoordinate.anchor = GridBagConstraints.EAST;
			gbc_lblXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_lblXCoordinate.gridx = 1;
			gbc_lblXCoordinate.gridy = 1;
			contentPanel.add(lblXCoordinate, gbc_lblXCoordinate);
		}
		{
			textFieldXCoordinate = new JTextField();
			textFieldXCoordinate.setText(Integer.toString(p.getXCoordinate()));
			GridBagConstraints gbc_textFieldXCoordinate = new GridBagConstraints();
			gbc_textFieldXCoordinate.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldXCoordinate.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldXCoordinate.gridx = 2;
			gbc_textFieldXCoordinate.gridy = 1;
			contentPanel.add(textFieldXCoordinate, gbc_textFieldXCoordinate);
			textFieldXCoordinate.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Choose color");
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					colorPoint = JColorChooser.showDialog(null, "Choose point color", Color.black);

				}
			});
			{
				JLabel lblYCoordinate = new JLabel("Y Coordinate:");

				GridBagConstraints gbc_lblYCoordinate = new GridBagConstraints();
				gbc_lblYCoordinate.anchor = GridBagConstraints.EAST;
				gbc_lblYCoordinate.insets = new Insets(0, 0, 5, 5);
				gbc_lblYCoordinate.gridx = 1;
				gbc_lblYCoordinate.gridy = 4;
				contentPanel.add(lblYCoordinate, gbc_lblYCoordinate);
			}
			{
				textFieldYCoordinate = new JTextField();
				textFieldYCoordinate.setText(Integer.toString(p.getYCoordinate()));
				GridBagConstraints gbc_textFieldYCoordinate = new GridBagConstraints();
				gbc_textFieldYCoordinate.insets = new Insets(0, 0, 5, 5);
				gbc_textFieldYCoordinate.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldYCoordinate.gridx = 2;
				gbc_textFieldYCoordinate.gridy = 4;
				contentPanel.add(textFieldYCoordinate, gbc_textFieldYCoordinate);
				textFieldYCoordinate.setColumns(10);
			}
			btnColor.setBackground(new Color(254, 224, 247));
			btnColor.setFont(new Font("Arial Nova", Font.BOLD, 13));
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.gridwidth = 4;
			gbc_btnColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnColor.gridx = 0;
			gbc_btnColor.gridy = 7;
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
						setOk(true);
						setVisible(false);
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
						p.setSelected(false);
						
						
					}
				});

				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

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

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean isOk) {
		this.ok = isOk;
	}

	public Color getColorPoint() {
		return colorPoint;
	}

	public void setColorPoint(Color colorPoint) {
		this.colorPoint = colorPoint;
	}


}
