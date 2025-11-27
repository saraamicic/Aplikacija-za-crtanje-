package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DlgLine extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldXStartPoint;
	private JTextField textFieldYStartPoint;
	private JTextField textFieldXEndPoint;
	private JTextField textFieldYEndPoint;
	private Line l;
	private boolean isOk;
	private Color lineColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine(Line l) {
		setModal(true);
		setTitle("Sara Micic IT58/2023");
		setBounds(100, 100, 645, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 47, 114, 0, 0, 0, 0, 0, -143, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartPoint = new JLabel("Start point:");
			GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
			gbc_lblStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartPoint.anchor = GridBagConstraints.NORTH;
			gbc_lblStartPoint.gridx = 4;
			gbc_lblStartPoint.gridy = 1;
			contentPanel.add(lblStartPoint, gbc_lblStartPoint);
		}
		{
			JLabel lblXStartPoint = new JLabel("X Coordinate:");
			GridBagConstraints gbc_lblXStartPoint = new GridBagConstraints();
			gbc_lblXStartPoint.anchor = GridBagConstraints.EAST;
			gbc_lblXStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblXStartPoint.gridx = 2;
			gbc_lblXStartPoint.gridy = 2;
			contentPanel.add(lblXStartPoint, gbc_lblXStartPoint);
		}
		{
			textFieldXStartPoint = new JTextField();
			textFieldXStartPoint.setText(Integer.toString(l.getStartPoint().getXCoordinate()));
			GridBagConstraints gbc_textFieldXStartPoint = new GridBagConstraints();
			gbc_textFieldXStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldXStartPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldXStartPoint.gridx = 4;
			gbc_textFieldXStartPoint.gridy = 2;
			contentPanel.add(textFieldXStartPoint, gbc_textFieldXStartPoint);
			textFieldXStartPoint.setColumns(10);
		}
		{
			JLabel lblYStartPoint = new JLabel("Y Coordinate:");
			GridBagConstraints gbc_lblYStartPoint = new GridBagConstraints();
			gbc_lblYStartPoint.anchor = GridBagConstraints.EAST;
			gbc_lblYStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblYStartPoint.gridx = 2;
			gbc_lblYStartPoint.gridy = 3;
			contentPanel.add(lblYStartPoint, gbc_lblYStartPoint);
		}
		{
			textFieldYStartPoint = new JTextField();
			textFieldYStartPoint.setText(Integer.toString(l.getStartPoint().getYCoordinate()));
			GridBagConstraints gbc_textFieldYStartPoint = new GridBagConstraints();
			gbc_textFieldYStartPoint.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldYStartPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldYStartPoint.gridx = 4;
			gbc_textFieldYStartPoint.gridy = 3;
			contentPanel.add(textFieldYStartPoint, gbc_textFieldYStartPoint);
			textFieldYStartPoint.setColumns(10);
		}
		{
			JButton btnColor = new JButton("Choose color");
			btnColor.setBackground(new Color(254, 224, 247));
			btnColor.setFont(new Font("Arial Nova", Font.BOLD, 12));
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					 lineColor = JColorChooser.showDialog(null, "Choose line color", Color.black);
					
				}
			});
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor.gridx = 6;
			gbc_btnColor.gridy = 9;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			JLabel lblEndPoint = new JLabel("End point:");
			GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
			gbc_lblEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndPoint.gridx = 4;
			gbc_lblEndPoint.gridy = 12;
			contentPanel.add(lblEndPoint, gbc_lblEndPoint);
		}
		{
			JLabel lblXEndPoint = new JLabel("X Coordinate:");
			GridBagConstraints gbc_lblXEndPoint = new GridBagConstraints();
			gbc_lblXEndPoint.anchor = GridBagConstraints.EAST;
			gbc_lblXEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblXEndPoint.gridx = 2;
			gbc_lblXEndPoint.gridy = 13;
			contentPanel.add(lblXEndPoint, gbc_lblXEndPoint);
		}
		{
			textFieldXEndPoint = new JTextField();
			textFieldXEndPoint.setText(Integer.toString(l.getEndPoint().getXCoordinate()));
			GridBagConstraints gbc_textFieldXEndPoint = new GridBagConstraints();
			gbc_textFieldXEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldXEndPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldXEndPoint.gridx = 4;
			gbc_textFieldXEndPoint.gridy = 13;
			contentPanel.add(textFieldXEndPoint, gbc_textFieldXEndPoint);
			textFieldXEndPoint.setColumns(10);
		}
		{
			JLabel lblYEndPoint = new JLabel("Y Coordinate:");
			GridBagConstraints gbc_lblYEndPoint = new GridBagConstraints();
			gbc_lblYEndPoint.anchor = GridBagConstraints.EAST;
			gbc_lblYEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_lblYEndPoint.gridx = 2;
			gbc_lblYEndPoint.gridy = 14;
			contentPanel.add(lblYEndPoint, gbc_lblYEndPoint);
		}
		{
			textFieldYEndPoint = new JTextField();
			textFieldYEndPoint.setText(Integer.toString(l.getEndPoint().getYCoordinate()));
			GridBagConstraints gbc_textFieldYEndPoint = new GridBagConstraints();
			gbc_textFieldYEndPoint.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldYEndPoint.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldYEndPoint.gridx = 4;
			gbc_textFieldYEndPoint.gridy = 14;
			contentPanel.add(textFieldYEndPoint, gbc_textFieldYEndPoint);
			textFieldYEndPoint.setColumns(10);
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
						l.setSelected(false);				}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTextFieldXStartPoint() {
		return textFieldXStartPoint;
	}

	public void setTextFieldXStartPoint(JTextField textFieldXStartPoint) {
		this.textFieldXStartPoint = textFieldXStartPoint;
	}

	public JTextField getTextFieldYStartPoint() {
		return textFieldYStartPoint;
	}

	public void setTextFieldYStartPoint(JTextField textFieldYStartPoint) {
		this.textFieldYStartPoint = textFieldYStartPoint;
	}

	public JTextField getTextFieldXEndPoint() {
		return textFieldXEndPoint;
	}

	public void setTextFieldXEndPoint(JTextField textFieldXEndPoint) {
		this.textFieldXEndPoint = textFieldXEndPoint;
	}

	public JTextField getTextFieldYEndPoint() {
		return textFieldYEndPoint;
	}

	public void setTextFieldYEndPoint(JTextField textFieldYEndPoint) {
		this.textFieldYEndPoint = textFieldYEndPoint;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	
	
}
