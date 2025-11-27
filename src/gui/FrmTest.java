package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JToggleButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class FrmTest extends JFrame { // prosiruje jframe postojecu klasu iz swing paketa

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // contentPane je instanca klase JPanel
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DefaultListModel<String> dlm = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmTest frame = new FrmTest();
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
	public FrmTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 831, 568);
		setTitle("Sara Micic");
		contentPane = new JPanel(); // instanciramo panel
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		GridBagLayout gbl_pnlCenter = new GridBagLayout();
		gbl_pnlCenter.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_pnlCenter.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_pnlCenter.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlCenter.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		pnlCenter.setLayout(gbl_pnlCenter);

		JToggleButton tglbtnCrvena = new JToggleButton("Crvena");
		tglbtnCrvena.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlm.addElement(tglbtnCrvena.getText());
			}
		});
		buttonGroup.add(tglbtnCrvena);
		GridBagConstraints gbc_tglbtnCrvena = new GridBagConstraints();
		gbc_tglbtnCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCrvena.gridx = 0;
		gbc_tglbtnCrvena.gridy = 0;
		pnlCenter.add(tglbtnCrvena, gbc_tglbtnCrvena);

		JLabel lblCrvena = new JLabel("Crvena");
		GridBagConstraints gbc_lblCrvena = new GridBagConstraints();
		gbc_lblCrvena.insets = new Insets(0, 0, 5, 5);
		gbc_lblCrvena.gridx = 1;
		gbc_lblCrvena.gridy = 0;
		pnlCenter.add(lblCrvena, gbc_lblCrvena);

		JToggleButton tglbtnPlava = new JToggleButton("Plava");
		buttonGroup.add(tglbtnPlava);
		GridBagConstraints gbc_tglbtnPlava = new GridBagConstraints();
		gbc_tglbtnPlava.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPlava.gridx = 0;
		gbc_tglbtnPlava.gridy = 1;
		pnlCenter.add(tglbtnPlava, gbc_tglbtnPlava);

		JLabel lblPlava = new JLabel("Plava");
		GridBagConstraints gbc_lblPlava = new GridBagConstraints();
		gbc_lblPlava.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlava.gridx = 1;
		gbc_lblPlava.gridy = 1;
		pnlCenter.add(lblPlava, gbc_lblPlava);

		JToggleButton tglbtnZuta = new JToggleButton("Zuta");
		tglbtnZuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dlm.addElement(tglbtnZuta.getText());
			}
		});
		tglbtnPlava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlm.addElement(tglbtnPlava.getText());
			}
		});
		buttonGroup.add(tglbtnZuta);
		GridBagConstraints gbc_tglbtnZuta = new GridBagConstraints();
		gbc_tglbtnZuta.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnZuta.gridx = 0;
		gbc_tglbtnZuta.gridy = 2;
		pnlCenter.add(tglbtnZuta, gbc_tglbtnZuta);

		JLabel lblZuta = new JLabel("Zuta");
		GridBagConstraints gbc_lblZuta = new GridBagConstraints();
		gbc_lblZuta.insets = new Insets(0, 0, 5, 5);
		gbc_lblZuta.gridx = 1;
		gbc_lblZuta.gridy = 2;
		pnlCenter.add(lblZuta, gbc_lblZuta);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		pnlCenter.add(scrollPane, gbc_scrollPane);

		JList lstBoje = new JList();
		lstBoje.setModel(dlm); // sve sto se doda u dlm da se prikaze u jlisti
		scrollPane.setViewportView(lstBoje);

		JButton btnColor = new JButton("Dodaj boju");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DlgTest dlgDodajBoju = new DlgTest();
				dlgDodajBoju.setVisible(true);

				if (dlgDodajBoju.isOk()) {
					String red = dlgDodajBoju.getTextFieldRed().getText();
					String blue = dlgDodajBoju.getTextFieldBlue().getText();
					String green = dlgDodajBoju.getTextFieldGreen().getText();

					String stringColor = red + " " + green + " " + blue;

					dlm.addElement(stringColor);

					Color color = new Color(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
					pnlCenter.setBackground(color);

				}
			}
		});
		scrollPane.setColumnHeaderView(btnColor);

		JButton btnIzmeniBoju = new JButton("Izmeni boju");
		btnIzmeniBoju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexOfSelectedElement = lstBoje.getSelectedIndex();
				System.out.println(indexOfSelectedElement);
				if (indexOfSelectedElement >= 0) {
					String selectedElement = dlm.getElementAt(indexOfSelectedElement);
					String[] rgbColors = selectedElement.split(" ");
					DlgTest izmenaBoje = new DlgTest();
					izmenaBoje.getTextFieldRed().setText(rgbColors[0]);
					izmenaBoje.getTextFieldGreen().setText(rgbColors[1]);
					izmenaBoje.getTextFieldBlue().setText(rgbColors[2]);
					izmenaBoje.setVisible(true);

					if (izmenaBoje.isOk()) {
						String red = izmenaBoje.getTextFieldRed().getText();
						String blue = izmenaBoje.getTextFieldBlue().getText();
						String green = izmenaBoje.getTextFieldGreen().getText();

						String stringColor = red + "" + green + "" + blue;
						dlm.addElement(stringColor);

						dlm.add(indexOfSelectedElement, stringColor);

						Color color = new Color(Integer.parseInt(red), Integer.parseInt(blue), Integer.parseInt(green));
						pnlCenter.setBackground(color);
						
						

					}

				}

			}
		});

		scrollPane.setRowHeaderView(btnIzmeniBoju);

		JPanel pnlSouth = new JPanel();
		contentPane.add(pnlSouth, BorderLayout.SOUTH);

		JButton btnIspisi = new JButton("Antistres dugme :>");
		btnIspisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "BICE 6 IZ ANALIZE", "Poruka", JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}

}
