package view.students;

import java.awt.Color;
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
import java.awt.Dimension;

public class EditElev extends JDialog {
	//Attributes
	private static final long serialVersionUID = 1L;
	private CtrlElev cont = new CtrlElev();
	private JTextField nameField;
	private JTextField telfField;
	private JTextField mailField;
	private JTextField rangeField;
	private String mode = "View", elev = "";
	private JButton actionBtn;
	private JButton deleteBtn;
	private JButton cancelBtn;

	//Create the Dialog
	public EditElev(MainMenu caller, String ele) {
		this.elev = ele;
		setMinimumSize(new Dimension(546, 450));
		getContentPane().setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		getContentPane().setBackground(new Color(69, 69, 69));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBackground(new Color(69, 69, 69));
		setBounds(100, 100, 666, 570);
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
		JLabel telfLbl = new JLabel(" Teléfono:");
		telfLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		telfLbl.setForeground(new Color(252, 252, 252));
		telfField = new JTextField();
		telfField.setEditable(false);
		telfField.setFocusable(false);
		telfField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		telfField.setForeground(new Color(252, 252, 252));
		telfField.setColumns(10);
		telfField.setBackground(new Color(36, 36, 36));
		JLabel mailLbl = new JLabel(" Mail:");
		mailLbl.setForeground(new Color(252, 252, 252));
		mailLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		mailField = new JTextField();
		mailField.setEditable(false);
		mailField.setFocusable(false);
		mailField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		mailField.setForeground(new Color(252, 252, 252));
		mailField.setBackground(new Color(36, 36, 36));
		mailField.setColumns(10);
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
		JLabel rangeLbl = new JLabel("Rango:");
		rangeLbl.setVerticalAlignment(SwingConstants.BOTTOM);
		rangeLbl.setForeground(new Color(252, 252, 252));
		rangeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		rangeLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		rangeField = new JTextField();
		rangeField.setEditable(false);
		rangeField.setFocusable(false);
		rangeField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
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
		String[] info = cont.getInfo(EditElev.this, elev);
		nameField.setText(info[0]);
		telfField.setText(info[1]);
		mailField.setText(info[2]);
		rangeField.setText(info[3]);
		notesArea.setText(info[4]);
		
		//Save Button + Pressed Button Listener
		actionBtn = new JButton("Editar");
		actionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mode.equals("View")) {
					nameField.setEditable(true);
					nameField.setFocusable(true);
					telfField.setEditable(true);
					telfField.setFocusable(true);
					mailField.setEditable(true);
					mailField.setFocusable(true);
					rangeField.setEditable(true);
					rangeField.setFocusable(true);
					notesArea.setEditable(true);
					notesArea.setFocusable(true);
					mode = "Edit";
					deleteBtn.setVisible(true);
					actionBtn.setText("Guardar");
					cancelBtn.setText("Cancelar");
				} else if (mode.equals("Edit")) {
					if (nameField.getText().trim().equals("") || nameField.getText() == null) {
						cont.errorMessage(EditElev.this, "Debes introducir un nombre");
						nameField.setText("");
					} else {
						if (!cont.checkOnlyLetters(nameField.getText())) {
							cont.errorMessage(EditElev.this, "El nombre solo puede contener letras");
							nameField.setText("");
						} else {
							if (telfField.getText().trim().equals("") || telfField.getText() == null) {
								cont.errorMessage(EditElev.this, "Debes introducir un teléfono");
							} else {
								boolean correct = true;
								for (int i=0; i<telfField.getText().length(); ++i) {
									if (!((telfField.getText().charAt(i) >= '0' && telfField.getText().charAt(i) <= '9')
											|| telfField.getText().charAt(i) == ' ')) {
										correct = false;
									}
								}
								if (!correct) {
									cont.errorMessage(EditElev.this, "El teléfono solo puede contener números");
									telfField.setText("");
								} else {
									if (mailField.getText().trim().equals("") || mailField.getText() == null) {
										cont.errorMessage(EditElev.this, "Debes introducir un correo electrónico");
										mailField.setText("");
									} else {
										if (rangeField.getText().trim().equals("") || rangeField.getText() == null) {
											cont.errorMessage(EditElev.this, "Introduce el rango del alumno");
										} else {
											if (rangeField.getText().contains(" - ")) {
												rangeField.setText(rangeField.getText().replaceAll(" - ", " -> "));
											}
											if (notesArea.getText().trim().equals("") || notesArea.getText() == null) {
												notesArea.setText("#");
											}
											if (!(nameField.getText().trim().replaceAll(" ", "").toLowerCase().equals(elev.trim().replaceAll(" ", "").toLowerCase())) 
													&& !(cont.check(EditElev.this, nameField.getText()))) {
												cont.errorMessage(EditElev.this, "Ya hay otro alumno con este nombre");
											} else {
												cont.remove(EditElev.this, elev);
												if (cont.createNew(EditElev.this, nameField.getText(), telfField.getText(), mailField.getText(), rangeField.getText(), notesArea.getText())) {
													cont.setStudents(caller);
													nameField.setEditable(false);
													nameField.setFocusable(false);
													telfField.setEditable(false);
													telfField.setFocusable(false);
													mailField.setEditable(false);
													mailField.setFocusable(false);
													rangeField.setEditable(false);
													rangeField.setFocusable(false);
													notesArea.setEditable(false);
													notesArea.setFocusable(false);
													mode = "View";
													deleteBtn.setVisible(false);
													actionBtn.setText("Editar");
													cancelBtn.setText("Salir");
													elev = nameField.getText();
													cont.rightMessage(EditElev.this, "Alumno editado correctamente!");
												} else {
													cont.errorMessage(EditElev.this, "Internal Error!");
												}
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
		
		//Eliminar Button + Pressed Button Listener
		deleteBtn = new JButton("Eliminar");
		deleteBtn.setVisible(false);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveElev rem = new RemoveElev(caller, EditElev.this, elev);
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
							.addComponent(sep3, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(sep2, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rangeLbl, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(mailLbl, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
											.addGap(5)
											.addComponent(mailField, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
											.addGap(242))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(1)
											.addComponent(telfLbl)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(telfField, GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(rangeField, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
									.addGap(13)))
							.addGap(5))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(deleteBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
							.addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(actionBtn, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(nameLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(rangeLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(telfField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(telfLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(rangeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(mailLbl, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(mailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sep2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
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
