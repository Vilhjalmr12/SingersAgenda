package view.songs;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import view.main.MainMenu;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoveSong extends JDialog {
	//Attributes
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	//Create the Dialog
	public RemoveSong(MainMenu menu, JDialog caller, String song) {
		setBounds(100, 100, 420, 108);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(69, 69, 69));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				caller.setEnabled(true);
				menu.setEnabled(true);
				dispose();
			}
		});
		
		//Initial SetUp
		JLabel questionLbl = new JLabel("Seguro que deseas eliminar esta canción?");
		questionLbl.setForeground(new Color(252, 252, 252));
		questionLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		questionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel iconLbl = new JLabel("");
		iconLbl.setHorizontalAlignment(SwingConstants.CENTER);
		iconLbl.setIcon(new ImageIcon(MainMenu.class.getResource("/zMyUtils/Messages/warning.png")));
		
		//Remove Button + Pressed Button listener
		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CtrlSongs cont = new CtrlSongs();
				if (cont.remove(RemoveSong.this, song)) {
					cont.rightMessage(RemoveSong.this, "Canción eliminada correctamente!");
				} else {
					cont.errorMessage(RemoveSong.this, "Internal Error!");
				}
				caller.dispose();
				menu.setEnabled(true);
				cont.setSongs(menu);
				dispose();
			}
		});
		
		//Cancel Button + Pressed Button listener
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caller.setEnabled(true);
				menu.setEnabled(true);
				dispose();
			}
		});
		
		//Elements Distribution
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(iconLbl, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(95)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 102, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(questionLbl, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)))
					.addGap(410))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(iconLbl, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(questionLbl, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(btnCancelar))))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
}
