package view.main;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Information extends JDialog {
	//Attributes
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	//Create the Dialog
	public Information(MainMenu caller) {
		setResizable(false);
		setBounds(100, 100, 570, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(69, 69, 69));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				caller.setEnabled(true);
				dispose();
			}
		});
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		//Close Button + Pressed Button Listener
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caller.setEnabled(true);
				dispose();
			}
		});
		
		JScrollPane infoScroll = new JScrollPane();
		JTextArea infoArea = new JTextArea();
		infoArea.setFocusable(false);
		infoArea.setEditable(false);
		infoArea.setForeground(new Color(252, 252, 252));
		infoArea.setBackground(new Color(54, 54, 54));
		infoArea.setLineWrap(true);
		infoArea.setWrapStyleWord(true);
		infoArea.setText("Programa para la gestion de las clases y alumnos de Raul Roca \n\n\n Ultima modificación: 21/07/2017 - Guillermo Martinez");
		infoScroll.setViewportView(infoArea);
		
		//Elements Distribution
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(infoScroll, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
						.addComponent(btnCerrar))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(4)
					.addComponent(infoScroll, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCerrar)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
