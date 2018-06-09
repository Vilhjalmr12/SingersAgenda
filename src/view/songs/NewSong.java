package view.songs;

import javax.swing.JDialog;
import view.main.MainMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class NewSong extends JDialog {
	//Attributes
	private static final long serialVersionUID = 1L;
	private CtrlSongs cont = new CtrlSongs();
	private JTextField titleField;
	private JTextField artistField;
	private JTextField rangeField;

	//Create the Dialog
	public NewSong(MainMenu caller) {
		getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		getContentPane().setBackground(new Color(69, 69, 69));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBackground(new Color(69, 69, 69));
		setBounds(100, 100, 666, 570);
		setMinimumSize(new Dimension(546, 450));
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				caller.setEnabled(true);
				dispose();
			}
		});
		
		//Initial SetUp
		titleField = new JTextField();
		titleField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		titleField.setForeground(new Color(252, 252, 252));
		titleField.setColumns(10);
		titleField.setBackground(new Color(36, 36, 36));
		JLabel titleLbl = new JLabel(" Título:");
		titleLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		titleLbl.setForeground(new Color(252, 252, 252));
		JLabel artistLbl = new JLabel(" Artista:");
		artistLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		artistLbl.setForeground(new Color(252, 252, 252));
		artistField = new JTextField();
		artistField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		artistField.setForeground(new Color(252, 252, 252));
		artistField.setColumns(10);
		artistField.setBackground(new Color(36, 36, 36));
		JSeparator sep2 = new JSeparator();
		JSeparator sep3 = new JSeparator();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBackground(new Color(69, 69, 69));
		JTextArea notesArea = new JTextArea();
		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);
		notesArea.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		notesArea.setForeground(new Color(252, 252, 252));
		notesArea.setBackground(new Color(36, 36, 36));
		notesArea.setBorder(null);
		scrollPane.setViewportView(notesArea);
		JLabel rangeLbl = new JLabel("Rango:");
		rangeLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		rangeLbl.setForeground(new Color(252, 252, 252));
		rangeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		rangeLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		rangeField = new JTextField();
		rangeField.setHorizontalAlignment(SwingConstants.CENTER);
		rangeField.setForeground(new Color(252, 252, 252));
		rangeField.setBackground(new Color(36, 36, 36));
		rangeField.setColumns(10);
		JLabel gapLbl = new JLabel("lll");
		gapLbl.setOpaque(true);
		gapLbl.setForeground(new Color(27, 27, 27));
		gapLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		gapLbl.setBackground(new Color(27, 27, 27));
		scrollPane.setRowHeaderView(gapLbl);
		
		//Save Button + Pressed Button Listener
		JButton saveBtn = new JButton("Guardar");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (titleField.getText().trim().equals("") || titleField.getText() == null) {
					cont.errorMessage(NewSong.this, "Debes introducir un nombre");
					titleField.setText("");
				} else {
					if (!cont.checkOnlyLetters(titleField.getText())) {
						cont.errorMessage(NewSong.this, "El nombre solo puede contener letras");
						titleField.setText("");
					} else {
						if (artistField.getText().trim().equals("") || artistField.getText() == null) {
							cont.errorMessage(NewSong.this, "Debes introducir un artista");
						} else {
							if (!cont.checkOnlyLetters(artistField.getText())) {
								cont.errorMessage(NewSong.this, "El artista solo puede contener letras");
								artistField.setText("");
							} else {
								if (!cont.check(NewSong.this, titleField.getText(), artistField.getText())) {
									cont.errorMessage(NewSong.this, "Esta canción ya ha sido registrada");
								} else {
									if (rangeField.getText().trim().equals("") || rangeField.getText() == null) {
										cont.errorMessage(NewSong.this, "Debes introducir el rango de la canción");
									} else {
										if (rangeField.getText().contains(" - ")) {
											rangeField.setText(rangeField.getText().replaceAll(" - ", " -> "));
										}
										if (notesArea.getText().trim().equals("") || notesArea.getText() == null) {
											notesArea.setText("#");
										}
										if (cont.createNew(NewSong.this, titleField.getText(), artistField.getText(), rangeField.getText(), notesArea.getText())) {
											cont.setSongs(caller);
											cont.rightMessage(NewSong.this, "Nueva Canción introducida correctamente!");
											dispose();
										} else {
											cont.errorMessage(NewSong.this, "Internal Error!");
										}
									}
								}
							}
						}
					}
				}
			}
		});
		
		//Cancel Button + Pressed Button Listener
		JButton cancelBtn = new JButton("Cancelar");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caller.setEnabled(true);
				dispose();
			}
		});
		
		//Elements Distribution
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(sep2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
						.addComponent(sep3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(artistLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(titleLbl, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(artistField, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
								.addComponent(titleField, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rangeLbl, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addComponent(rangeField, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(titleLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(rangeLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(titleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(artistField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(artistLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(rangeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(sep2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sep3, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveBtn)
						.addComponent(cancelBtn))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
