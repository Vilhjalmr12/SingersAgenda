package view.exercises;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import view.main.JarPath;
import view.main.MainMenu;

import java.awt.Dimension;

public class EditExercise extends JDialog {
	//Attributes
	private static final long serialVersionUID = 1L;
	private CtrlExercises cont = new CtrlExercises();
	private JTextField nameField;
	private JTextField typeField;
	private JTextField fileField;
	private String mode = "View", exercise;
	private JButton actionBtn;
	private JButton deleteBtn;
	private JButton fileBtn;
	private JButton cancelBtn;
	private File audioFile = null, newFile = null;

	//Create the Dialog
	public EditExercise(MainMenu caller, String exer) {
		this.exercise = exer;
		setMinimumSize(new Dimension(546, 450));
		getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		getContentPane().setBackground(new Color(69, 69, 69));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBackground(new Color(69, 69, 69));
		setBounds(100, 100, 546, 570);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				caller.setEnabled(true);
				dispose();
			}
		});
		
		//Initial SetUp
		nameField = new JTextField();
		nameField.setEditable(false);
		nameField.setFocusable(false);
		nameField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		nameField.setForeground(new Color(252, 252, 252));
		nameField.setColumns(10);
		nameField.setBackground(new Color(36, 36, 36));
		JLabel nameLbl = new JLabel(" Nombre:");
		nameLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		nameLbl.setForeground(new Color(252, 252, 252));
		JLabel typeLbl = new JLabel(" Tipo:");
		typeLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		typeLbl.setForeground(new Color(252, 252, 252));
		typeField = new JTextField();
		typeField.setEditable(false);
		typeField.setFocusable(false);
		typeField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		typeField.setForeground(new Color(252, 252, 252));
		typeField.setColumns(10);
		typeField.setBackground(new Color(36, 36, 36));
		JLabel fileLbl = new JLabel(" Archivo:");
		fileLbl.setForeground(new Color(252, 252, 252));
		fileLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		fileField = new JTextField();
		fileField.setEditable(false);
		fileField.setFocusable(false);
		fileField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		fileField.setForeground(new Color(252, 252, 252));
		fileField.setBackground(new Color(36, 36, 36));
		fileField.setColumns(10);
		JSeparator sep2 = new JSeparator();
		JSeparator sep3 = new JSeparator();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBackground(new Color(69, 69, 69));
		JTextArea notesArea = new JTextArea();
		notesArea.setEditable(false);
		notesArea.setFocusable(false);
		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);
		notesArea.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		notesArea.setForeground(new Color(252, 252, 252));
		notesArea.setBackground(new Color(36, 36, 36));
		notesArea.setBorder(null);
		scrollPane.setViewportView(notesArea);
		JLabel gapLbl = new JLabel("lll");
		gapLbl.setOpaque(true);
		gapLbl.setForeground(new Color(27, 27, 27));
		gapLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		gapLbl.setBackground(new Color(27, 27, 27));
		scrollPane.setRowHeaderView(gapLbl);
		String[] info = cont.getInfo(EditExercise.this, exercise);
		nameField.setText(info[0]);
		typeField.setText(info[1]);
		fileField.setText(info[2]);
		notesArea.setText(info[3]);
		if (fileField.getText().equals("#")) {
			audioFile = null;
		} else {
			String[] aux = fileField.getText().split("\\.");
	    	String ext = aux[aux.length - 1];
	    	ext = "." + ext;
			audioFile = new File(JarPath.getPath() + "Exercises/aaFiles/" + (typeField.getText() + " - " 
					+ nameField.getText() + ext).trim().replace(" ", "").toLowerCase());
		}
		
		//Save Button + Pressed Button Listener
		actionBtn = new JButton("Editar");
		actionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode.equals("View")) {
					nameField.setEditable(true);
					nameField.setFocusable(true);
					typeField.setEditable(true);
					typeField.setFocusable(true);
					notesArea.setEditable(true);
					notesArea.setFocusable(true);
					mode = "Edit";
					deleteBtn.setVisible(true);
					actionBtn.setText("Guardar");
					fileBtn.setText("Seleccionar");
					cancelBtn.setText("Cancelar");
					newFile = null;
				} else if (mode.equals("Edit")) {
					if (nameField.getText().trim().equals("") || nameField.getText() == null) {
						cont.errorMessage(EditExercise.this, "Debes introducir un nombre");
						nameField.setText("");
					} else {
						if (!cont.checkOnlyLetters(nameField.getText())) {
							cont.errorMessage(EditExercise.this, "El nombre solo puede contener letras");
							nameField.setText("");
						} else {
							if (typeField.getText().trim().equals("") || typeField.getText() == null) {
								cont.errorMessage(EditExercise.this, "Debes introducir un tipo");
							} else {
								if (!cont.checkOnlyLetters(typeField.getText())) {
									cont.errorMessage(EditExercise.this, "El tipo solo puede contener letras");
									typeField.setText("");
								} else {
									if (notesArea.getText().trim().equals("") || notesArea.getText() == null) {
										notesArea.setText("#");
									}
									if (!((typeField.getText() + " - " + nameField.getText()).trim().replaceAll(" ", "").toLowerCase()
											.equals(exercise.trim().replaceAll(" ", "").toLowerCase())) 
											&& !(cont.check(EditExercise.this, nameField.getText(), typeField.getText()))) {
										cont.errorMessage(EditExercise.this, "Ya hay otro ejercicio con este nombre");
									} else {
										if (!(newFile == null)) {
											cont.remove(EditExercise.this, exercise, audioFile);
											if (cont.saveFile(EditExercise.this, nameField.getText(), typeField.getText(), newFile)) {
												if (cont.createNew(EditExercise.this, nameField.getText(), typeField.getText(),
														newFile.getName(), notesArea.getText())) {
													newFile = null;
													String[] aux = fileField.getText().split("\\.");
											    	String ext = aux[aux.length - 1];
											    	ext = "." + ext;
													audioFile = new File(JarPath.getPath() + "Exercises/aaFiles/" + (typeField.getText() + " - " 
															+ nameField.getText() + ext).trim().replace(" ", "").toLowerCase());
													cont.setExercises(caller);
													cont.rightMessage(EditExercise.this, "Ejercicio editado correctamente!");
												} else {
													cont.errorMessage(EditExercise.this, "Internal Error!");
												}
											} else {
												cont.errorMessage(EditExercise.this, "Internal Error!");
											}
										} else {
											cont.remove(EditExercise.this, exercise, null);
											if (audioFile == null) {
												if (cont.createNew(EditExercise.this, nameField.getText(), typeField.getText(), 
														"#", notesArea.getText())) {
													cont.setExercises(caller);
													cont.rightMessage(EditExercise.this, "Ejercicio editado correctamente!");
												} else {
													cont.errorMessage(EditExercise.this, "Internal Error!");
												}
											} else if (exercise.equals(typeField.getText() + " - " + nameField.getText())) {
												if (cont.createNew(EditExercise.this, nameField.getText(), typeField.getText(),
														audioFile.getName(), notesArea.getText())) {
													cont.setExercises(caller);
													cont.rightMessage(EditExercise.this, "Ejercicio editado correctamente!");
												} else {
													cont.errorMessage(EditExercise.this, "Internal Error!");
												}
											} else {
												newFile = new File(audioFile.getPath());
												if (cont.saveFile(EditExercise.this, nameField.getText(), typeField.getText(), newFile)) {
													if (cont.createNew(EditExercise.this, nameField.getText(), typeField.getText(),
															newFile.getName(), notesArea.getText())) {
														cont.removeOriginalFile(EditExercise.this, audioFile);
														newFile = null;
														String[] aux = fileField.getText().split("\\.");
												    	String ext = aux[aux.length - 1];
												    	ext = "." + ext;
														audioFile = new File(JarPath.getPath() + "Exercises/aaFiles/" + (typeField.getText() + " - " 
																+ nameField.getText() + ext).trim().replace(" ", "").toLowerCase());
														cont.setExercises(caller);
														cont.rightMessage(EditExercise.this, "Ejercicio editado correctamente!");
													} else {
														cont.errorMessage(EditExercise.this, "Internal Error!");
													}
												} else {
													cont.errorMessage(EditExercise.this, "Internal Error!");
												}
											}
										}
										nameField.setEditable(false);
										nameField.setFocusable(false);
										typeField.setEditable(false);
										typeField.setFocusable(false);
										notesArea.setEditable(false);
										notesArea.setFocusable(false);
										mode = "View";
										deleteBtn.setVisible(false);
										actionBtn.setText("Editar");
										fileBtn.setText("Abrir");
										cancelBtn.setText("Salir");
										exercise = typeField.getText() + " - " + nameField.getText();
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
		
		fileBtn = new JButton("Abrir");
		fileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode.equals("View")) {
					if (!(audioFile == null)) {
						cont.openFile(EditExercise.this, audioFile);
					}
				} else if (mode.equals("Edit")) {
					JFileChooser chooser = new JFileChooser();
					chooser.setMultiSelectionEnabled(false);
					int option = chooser.showOpenDialog(EditExercise.this);
			        if (option == JFileChooser.APPROVE_OPTION) {
						File file = chooser.getSelectedFile();
						fileField.setText(file.getName());
						newFile = file;
			        }
				}
			}
		});
		
		//Delete Button + Pressed Button Listener
		deleteBtn = new JButton("Eliminar");
		deleteBtn.setVisible(false);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveExercise rem = new RemoveExercise(caller, EditExercise.this, exercise, audioFile);
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(sep3, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(sep2, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(fileLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(1)
											.addComponent(typeLbl, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(fileField, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(fileBtn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
										.addComponent(typeField, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE))
									.addGap(1)))
							.addGap(5))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(actionBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(typeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(typeLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(fileLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(fileField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(fileBtn)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sep2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sep3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
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
