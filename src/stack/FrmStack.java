package stack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class FrmStack extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<Donut> dlm = new DefaultListModel<Donut>();
	JList<Donut> lstElements = new JList<Donut>();
	private Donut donut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmStack frame = new FrmStack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmStack() {
		setTitle("Sara Micic IT58/2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1087, 574);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 227, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 169, 336, -27, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 29, 0, 0, 86, 72, 64, 0, 195, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		/*****/

		JButton btnAdd = new JButton("  Add  ");
		btnAdd.setFont(new Font("Arial Nova", Font.BOLD, 12));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DlgStack dlgStack = new DlgStack();
				dlgStack.setVisible(true);

				if (dlgStack.isOk()) {

					dlm.add(0, dlgStack.getDonut());

				}
			}
		});

		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 4;
		contentPane.add(btnAdd, gbc_btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(253, 202, 240), 3));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		lstElements.setModel(dlm);

		scrollPane.setViewportView(lstElements);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Arial Nova", Font.BOLD, 12));
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (dlm.size() != 0) {

					DlgStack dlgStack2 = new DlgStack();

					dlgStack2.getTextFieldRadius().setText(Integer.toString(dlm.getElementAt(0).getRadius()));
					// uzimamo donut sa liste koji ima indeks 0 i uzimamo njegov radius,
					// konvertujemo ga u string i upisemo u text field radiusa u dialogu
					dlgStack2.getTextFieldInnerRadius().setText(Integer.toString(dlm.getElementAt(0).getInnerRadius()));

					dlgStack2.getTextFieldXCoordinate()
							.setText(Integer.toString(dlm.getElementAt(0).getCenter().getXCoordinate()));

					dlgStack2.getTextFieldYCoordinate()
							.setText(Integer.toString(dlm.getElementAt(0).getCenter().getYCoordinate()));

					dlgStack2.getTextFieldInnerRadius().setEditable(false);
					dlgStack2.getTextFieldRadius().setEditable(false);
					dlgStack2.getTextFieldXCoordinate().setEditable(false);
					dlgStack2.getTextFieldYCoordinate().setEditable(false);

					dlgStack2.setVisible(true);

					if (dlgStack2.isOk()) {
						dlm.remove(0);
						dlgStack2.setVisible(false);
					}

				} else {
					JOptionPane.showMessageDialog(null, "There is no donuts in the stack!", "",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		});

		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 6;
		contentPane.add(btnDelete, gbc_btnDelete);
		/*****/

	}

}
