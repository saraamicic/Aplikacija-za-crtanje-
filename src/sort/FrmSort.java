package sort;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;

import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;

public class FrmSort extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<Donut> dlm = new DefaultListModel<Donut>();
	JList<Donut> lstSort = new JList<Donut>();
	private Donut donut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSort frame = new FrmSort();
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
	public FrmSort() {
		setTitle("Sara Micic IT58/2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 992, 631);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 224, 247));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 122, 122, 305, 150, 0 };
		gbl_contentPane.rowHeights = new int[] { 59, 0, 0, -15, 2, 240, 124, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Arial Nova", Font.BOLD, 12));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				DlgSort dlgSort = new DlgSort();
				dlgSort.setVisible(true);

				if (dlgSort.isOk()) {

					dlm.add(0, dlgSort.getDonut());
				}

			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 0;
		contentPane.add(btnAdd, gbc_btnAdd);

		JButton btnSort = new JButton("Sort");
		btnSort.setFont(new Font("Arial Nova", Font.BOLD, 12));
		btnSort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (dlm.size() != 0) {

					if (dlm.size() > 1) {
						// sortiramo po bubble sortu
						for (int i = 0; i < dlm.size() - 1; i++) {
							for (int j = 0; j < dlm.size() - i - 1; j++) {
								if (dlm.getElementAt(j).area() < (dlm.getElementAt(j + 1).area())) {

									Donut pomocni = new Donut();

									pomocni = dlm.getElementAt(j);
									dlm.setElementAt(dlm.getElementAt(j + 1), j);
									dlm.setElementAt(pomocni, j + 1);

								}

							}
						}
					}

				} else {
					JOptionPane.showMessageDialog(null, "There is no donuts to sort", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnSort = new GridBagConstraints();
		gbc_btnSort.insets = new Insets(0, 0, 5, 5);
		gbc_btnSort.gridx = 2;
		gbc_btnSort.gridy = 0;
		contentPane.add(btnSort, gbc_btnSort);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		contentPane.add(scrollPane, gbc_scrollPane);
		lstSort.setModel(dlm);

		scrollPane.setViewportView(lstSort);
	}

}
