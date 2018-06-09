package view.exercises;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import view.main.MainMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
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
import java.awt.Dimension;

public class NewExercise extends JDialog {
	//Attributes
	private static final long serialVersionUID = 1L;
	private CtrlExercises cont = new CtrlExercises();
	private JTextField nameField;
	private JTextField typeField;
	private JTextField fileField;
	private File audioFile = null;

	//Create the Dialog
	public NewExercise(MainMenu caller) {
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
		typeField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		typeField.setForeground(new Color(252, 252, 252));
		typeField.setColumns(10);
		typeField.setBackground(new Color(36, 36, 36));
		JLabel fileLbl = new JLabel(" Archivo:");
		fileLbl.setForeground(new Color(252, 252, 252));
		fileLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		fileField = new JTextField();
		fileField.setFocusable(false);
		fileField.setEditable(false);
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
		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);
		notesArea.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		notesArea.setForeground(new Color(252, 252, 252));
		notesArea.setBackground(new Color(36, 36, 36));
		notesArea.setBorder(null);
		scrollPane.setViewportView(notesArea);
		JLabel gapLbl = new JLabel("lll");
		gapLbl.setBackground(new Color(27, 27, 27));
		gapLbl.setOpaque(true);
		gapLbl.setForeground(new Color(27, 27, 27));
		gapLbl.setFont(new Font("SansSerif", Font.PLAIN, 15));
		scrollPane.setRowHeaderView(gapLbl);
		
		//Save Button + Pressed Button Listener
		JButton saveBtn = new JButton("Guardar");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nameField.getText().trim().equals("") || nameField.getText() == null) {
					cont.errorMessage(NewExercise.this, "Debes introducir un nombre");
					nameField.setText("");
				} else {
					if (!cont.checkOnlyLetters(nameField.getText())) {
						cont.errorMessage(NewExercise.this, "El nombre solo puede contener letras");
						nameField.setText("");
					} else {
						if (typeField.getText().trim().equals("") || typeField.getText() == null) {
							cont.errorMessage(NewExercise.this, "Debes introducir un tipo");
						} else {
							if (!cont.checkOnlyLetters(typeField.getText())) {
								cont.errorMessage(NewExercise.this, "El tipo solo puede contener letras");
								typeField.setText("");
							} else {
								if (!cont.check(NewExercise.this, nameField.getText(), typeField.getText())) {
									cont.errorMessage(NewExercise.this, "Este ejercicio ya ha sido registrado");
								} else {
									if (notesArea.getText().trim().equals("") || notesArea.getText() == null) {
										notesArea.setText("#");
									}
									if (!(audioFile == null)) {
										if (cont.saveFile(NewExercise.this, nameField.getText(), typeField.getText(), audioFile)) {
											if (cont.createNew(NewExercise.this, nameField.getText(), typeField.getText(), audioFile.getName(), notesArea.getText())) {
												cont.setExercises(caller);
												cont.rightMessage(NewExercise.this, "Nuevo ejercicio introducido correctamente!");
												dispose();
											} else {
												cont.errorMessage(NewExercise.this, "Internal Error!");
											}
										} else {
											cont.errorMessage(NewExercise.this, "Internal Error!");
										}
									} else {
										if (cont.createNew(NewExercise.this, nameField.getText(), typeField.getText(), "#", notesArea.getText())) {
											cont.setExercises(caller);
											cont.rightMessage(NewExercise.this, "Nuevo ejercicio introducido correctamente!");
											dispose();
										} else {
											cont.errorMessage(NewExercise.this, "Internal Error!");
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
		
		JButton browseBtn = new JButton("Seleccionar");
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				/*FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files (.mp3; .m4a)", "mp3", "m4a");
				chooser.setFileFilter(filter);*/
				chooser.setMultiSelectionEnabled(false);
				int option = chooser.showOpenDialog(NewExercise.this);
		        if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					/*String path = file.getPath();
					if (path.endsWith(".mp3") || path.endsWith(".m4a")) {
						audioField.setText(file.getPath());
						audioFile = file;
					} else {
						audioFile = null;
						audioField.setText("");
						cont.errorMessage(NewExercise.this, "Incorrect file format");
					}*/
					fileField.setText(file.getName());
					audioFile = file;
		        }
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
							.addComponent(scrollPane)
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
											.addComponent(fileField, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
										.addComponent(typeField, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
									.addGap(1)))
							.addGap(5))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(browseBtn)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sep2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sep3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveBtn)
						.addComponent(cancelBtn))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
}
