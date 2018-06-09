package view.songs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import view.main.MainMenu;

public class EditSong extends JDialog {
	//Attributes
	private static final long serialVersionUID = 1L;
	private CtrlSongs cont = new CtrlSongs();
	private JTextField titleField;
	private JTextField artistField;
	private JTextField rangeField;
	private String mode = "View", song;
	private JButton deleteBtn;
	private JButton cancelBtn;
	
	//Create the Dialog
	public EditSong(MainMenu caller, String s) {
		this.song = s;
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
		titleField.setFocusable(false);
		titleField.setEditable(false);
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
		artistField.setFocusable(false);
		artistField.setEditable(false);
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
		notesArea.setFocusable(false);
		notesArea.setEditable(false);
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
		rangeField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		rangeField.setFocusable(false);
		rangeField.setEditable(false);
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
		String[] info = cont.getInfo(EditSong.this, song);
		titleField.setText(info[0]);
		artistField.setText(info[1]);
		rangeField.setText(info[2]);
		notesArea.setText(info[3]);
		
		//Save Button + Pressed Button Listener
		JButton actionBtn = new JButton("Editar");
		actionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode.equals("View")) {
					titleField.setEditable(true);
					titleField.setFocusable(true);
					artistField.setEditable(true);
					artistField.setFocusable(true);
					rangeField.setEditable(true);
					rangeField.setFocusable(true);
					notesArea.setEditable(true);
					notesArea.setFocusable(true);
					mode = "Edit";
					deleteBtn.setVisible(true);
					actionBtn.setText("Guardar");
					cancelBtn.setText("Cancelar");
				} else if (mode.equals("Edit")) {
					if (titleField.getText().trim().equals("") || titleField.getText() == null) {
						cont.errorMessage(EditSong.this, "Debes introducir un nombre");
						titleField.setText("");
					} else {
						if (!cont.checkOnlyLetters(titleField.getText())) {
							cont.errorMessage(EditSong.this, "El título solo puede contener letras");
							titleField.setText("");
						} else {
							if (artistField.getText().trim().equals("") || artistField.getText() == null) {
								cont.errorMessage(EditSong.this, "Debes introducir un artista");
							} else {
								if (!cont.checkOnlyLetters(artistField.getText())) {
									cont.errorMessage(EditSong.this, "El artista solo puede contener letras");
									artistField.setText("");
								} else {
									if (rangeField.getText().trim().equals("") || rangeField.getText() == null) {
										cont.errorMessage(EditSong.this, "Debes introducir el rango de la canción");
									} else {
										if (rangeField.getText().contains(" - ")) {
											rangeField.setText(rangeField.getText().replaceAll(" - ", " -> "));
										}
										if (notesArea.getText().trim().equals("") || notesArea.getText() == null) {
											notesArea.setText("#");
										}
										if (!((artistField.getText() + " - " + titleField.getText()).trim().replaceAll(" ", "").toLowerCase().equals(song.trim().replaceAll(" ", "").toLowerCase())) 
												&& !(cont.check(EditSong.this, titleField.getText(), artistField.getText()))) {
											cont.errorMessage(EditSong.this, "Ya hay otra canción con este nombre");
										} else {
											cont.remove(EditSong.this, song);
											if (cont.createNew(EditSong.this, titleField.getText(), artistField.getText(), rangeField.getText(), notesArea.getText())) {
												cont.setSongs(caller);
												titleField.setEditable(false);
												titleField.setFocusable(false);
												artistField.setEditable(false);
												artistField.setFocusable(false);
												rangeField.setEditable(false);
												rangeField.setFocusable(false);
												notesArea.setEditable(false);
												notesArea.setFocusable(false);
												mode = "View";
												deleteBtn.setVisible(false);
												actionBtn.setText("Editar");
												cancelBtn.setText("Salir");
												song = artistField.getText() + " - " + titleField.getText();
												cont.rightMessage(EditSong.this, "Canción editada correctamente!");
											} else {
												cont.errorMessage(EditSong.this, "Internal Error!");
											}
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
		cancelBtn = new JButton("Salir");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caller.setEnabled(true);
				dispose();
			}
		});
		
		//Delete Button + Pressed Button Listener
		deleteBtn = new JButton("Eliminar");
		deleteBtn.setVisible(false);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveSong rem = new RemoveSong(caller, EditSong.this, song);
				rem.setAlwaysOnTop(true);
				rem.setVisible(true);
				caller.setEnabled(false);
				setEnabled(false);
			}
		});
		
		//Elements Distribution
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(sep2, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
						.addComponent(sep3, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(actionBtn, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
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
						.addComponent(actionBtn)
						.addComponent(cancelBtn)
						.addComponent(deleteBtn))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
