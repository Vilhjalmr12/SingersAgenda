package view.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.Color;
import view.exercises.CtrlExercises;
import view.exercises.EditExercise;
import view.exercises.NewExercise;
import view.songs.CtrlSongs;
import view.songs.EditSong;
import view.songs.NewSong;
import view.students.CtrlElev;
import view.students.EditElev;
import view.students.NewElev;
import zMyUtils.Messages.RightMessage;
import zMyUtils.Signature.GulleSignature;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class MainMenu extends JFrame {
	//Attributes
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titleLbl = new JLabel("");
	private JScrollPane displayScroll = new JScrollPane();
	private JButton viewStudentsBtn = new JButton("Seleccionar");
	private JButton newStudentBtn = new JButton("Añadir");
	private JButton newSongBtn = new JButton("Añadir");
	private JButton viewSongsBtn = new JButton("Seleccionar");
	private JButton viewExercisesBtn = new JButton("Seleccionar");
	private JButton newExerciseBtn = new JButton("Añadir");

	//Messages
  	public void rightMessage(String text) {
  		RightMessage msg = new RightMessage(this, text);
  		msg.setAlwaysOnTop(true);
  		msg.setVisible(true);
  		setEnabled(false);
  	}
	
	//Setters
	public void setMainDisplayTitle(String text) {
		titleLbl.setText(text);
	}
	
	public void setStudents(String[] students) {
		Arrays.sort(students);
		JList<String> studentsList = new JList<String>(students);
		studentsList.setBackground(new Color(69, 69, 69));
		studentsList.setForeground(new Color(252, 252, 252));
		studentsList.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		studentsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				     e.consume();
				     EditElev editS = new EditElev(MainMenu.this, studentsList.getSelectedValue());
				     editS.setVisible(true);
				}
			}
		});
		displayScroll.setViewportView(studentsList);
	}
	
	public void setSongs(String[] songs) {
		Arrays.sort(songs);
		JList<String> songsList = new JList<String>(songs);
		songsList.setBackground(new Color(69, 69, 69));
		songsList.setForeground(new Color(252, 252, 252));
		songsList.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		songsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				    e.consume();
				    EditSong editS = new EditSong(MainMenu.this, songsList.getSelectedValue());
					editS.setVisible(true);
				}
			}
		});
		displayScroll.setViewportView(songsList);
	}
	
	public void setExercises(String[] exercises) {
		Arrays.sort(exercises);
		JList<String> exercisesList = new JList<String>(exercises);
		exercisesList.setBackground(new Color(69, 69, 69));
		exercisesList.setForeground(new Color(252, 252, 252));
		exercisesList.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		exercisesList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
				    e.consume();
				    EditExercise editE = new EditExercise(MainMenu.this, exercisesList.getSelectedValue());
					editE.setVisible(true);
				}
			}
		});
		displayScroll.setViewportView(exercisesList);
	}
	
	//Create the Frame
	public MainMenu() {
		setMinimumSize(new Dimension(747, 540));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 540);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		displayScroll.setBackground(new Color(69, 69, 69));
		displayScroll.setBorder(null);
		
		//Initial SetUp
		JLabel welcomeLbl = new JLabel("");
		welcomeLbl.setForeground(new Color(252, 252, 252));
		welcomeLbl.setBackground(new Color(69, 69, 69));
		welcomeLbl.setOpaque(true);
		welcomeLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		titleLbl.setBackground(new Color(69, 69, 69));
		titleLbl.setForeground(new Color(252, 252, 252));
		titleLbl.setOpaque(true);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		titleLbl.setText(dtf.format(localDate));
		displayScroll.setColumnHeaderView(titleLbl);
		displayScroll.setViewportView(welcomeLbl);
		
		//Menu Bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Archivo");
		menuBar.add(fileMenu);
		JPanel runePanel = new JPanel();
		runePanel.setOpaque(false);
		runePanel.setBorder(null);
		JLabel runeLbl = new JLabel("");
		runeLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		runeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(runePanel);
		JMenuItem informationBtn = new JMenuItem("Infomación");
		fileMenu.add(informationBtn);
		informationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Information info = new Information(MainMenu.this);
				info.setAlwaysOnTop(true);
				info.setVisible(true);
				setEnabled(false);
			}
		});
		JButton button = new JButton("ᛒ");
		button.setFont(new Font("SansSerif", Font.PLAIN, 15));
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GulleSignature rune = new GulleSignature(MainMenu.this);
				rune.setAlwaysOnTop(true);
				setEnabled(false);
				rune.setVisible(true);
			}
		});
		
		//Buttons Panel
		JPanel panel = new JPanel();
		panel.setBackground(new Color(54, 54, 54));
		JLabel lblClientes = new JLabel("Alumnos");
		lblClientes.setForeground(new Color(252, 252, 252));
		JLabel eventosLbl = new JLabel("Canciones");
		eventosLbl.setForeground(new Color(252, 252, 252));
		JLabel tasksLbl = new JLabel("Ejercicios");
		tasksLbl.setForeground(new Color(252, 252, 252));
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(138, 138, 138));
		viewStudentsBtn.setHorizontalAlignment(SwingConstants.LEFT);
		
		//All Clients Button + Pressed Button Listener
		viewStudentsBtn.setIcon(new ImageIcon(MainMenu.class.getResource("/view/main/view.png")));
		viewStudentsBtn.setBackground(new Color(66, 99, 126));
		viewStudentsBtn.setBorder(null);
		viewStudentsBtn.setContentAreaFilled(false);
		viewStudentsBtn.setForeground(new Color(252, 252, 252));
		viewStudentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectBtn(viewStudentsBtn);
				CtrlElev cont = new CtrlElev();
				cont.setStudents(MainMenu.this);
			}
		});
		
		//Add Client Button + Pressed Button Listener
		newStudentBtn.setIcon(new ImageIcon(MainMenu.class.getResource("/view/main/add.png")));
		newStudentBtn.setBackground(new Color(66, 99, 126));
		newStudentBtn.setBorder(null);
		newStudentBtn.setHorizontalAlignment(SwingConstants.LEFT);
		newStudentBtn.setContentAreaFilled(false);
		newStudentBtn.setForeground(new Color(252, 252, 252));
		newStudentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectBtn(newStudentBtn);
				NewElev client = new NewElev(MainMenu.this);
				client.setVisible(true);
			}
		});
		
		//New Event Button + Pressed Button Listener
		newSongBtn.setIcon(new ImageIcon(MainMenu.class.getResource("/view/main/add.png")));
		newSongBtn.setForeground(new Color(252, 252, 252));
		newSongBtn.setContentAreaFilled(false);
		newSongBtn.setBorder(null);
		newSongBtn.setHorizontalAlignment(SwingConstants.LEFT);
		newSongBtn.setBackground(new Color(66, 99, 126));
		newSongBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectBtn(newSongBtn);
				NewSong song = new NewSong(MainMenu.this);
				song.setVisible(true);
			}
		});
		
		//Show Events + Pressed Button Listener
		viewSongsBtn.setIcon(new ImageIcon(MainMenu.class.getResource("/view/main/view.png")));
		viewSongsBtn.setForeground(new Color(252, 252, 252));
		viewSongsBtn.setContentAreaFilled(false);
		viewSongsBtn.setBorder(null);
		viewSongsBtn.setHorizontalAlignment(SwingConstants.LEFT);
		viewSongsBtn.setBackground(new Color(66, 99, 126));
		viewSongsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectBtn(viewSongsBtn);
				CtrlSongs cont = new CtrlSongs();
				cont.setSongs(MainMenu.this);
			}
		});
		
		//Show Tasks Button + Pressed Button Listener
		viewExercisesBtn.setIcon(new ImageIcon(MainMenu.class.getResource("/view/main/view.png")));
		viewExercisesBtn.setForeground(new Color(252, 252, 252));
		viewExercisesBtn.setContentAreaFilled(false);
		viewExercisesBtn.setBorder(null);
		viewExercisesBtn.setHorizontalAlignment(SwingConstants.LEFT);
		viewExercisesBtn.setBackground(new Color(66, 99, 126));
		viewExercisesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectBtn(viewExercisesBtn);
				CtrlExercises cont = new CtrlExercises();
				cont.setExercises(MainMenu.this);
			}
		});
		
		//Create New Task Button + Pressed Button Listener
		newExerciseBtn.setIcon(new ImageIcon(MainMenu.class.getResource("/view/main/add.png")));
		newExerciseBtn.setForeground(new Color(252, 252, 252));
		newExerciseBtn.setContentAreaFilled(false);
		newExerciseBtn.setBorder(null);
		newExerciseBtn.setHorizontalAlignment(SwingConstants.LEFT);
		newExerciseBtn.setBackground(new Color(66, 99, 126));
		newExerciseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectBtn(newExerciseBtn);
				NewExercise exercise = new NewExercise(MainMenu.this);
				exercise.setVisible(true);
			}
		});
		
		//Elements Distribution
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(displayScroll, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
				.addComponent(displayScroll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
		);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClientes, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(newStudentBtn, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(viewStudentsBtn, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(eventosLbl, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(newSongBtn, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(viewSongsBtn, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(tasksLbl, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(newExerciseBtn, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(viewExercisesBtn, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClientes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newStudentBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(viewStudentsBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eventosLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newSongBtn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(viewSongsBtn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tasksLbl)
					.addGap(6)
					.addComponent(newExerciseBtn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(viewExercisesBtn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(266, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		GroupLayout gl_runePanel = new GroupLayout(runePanel);
		gl_runePanel.setHorizontalGroup(
			gl_runePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_runePanel.createSequentialGroup()
					.addContainerGap(592, Short.MAX_VALUE)
					.addComponent(runeLbl, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(2))
		);
		gl_runePanel.setVerticalGroup(
			gl_runePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_runePanel.createParallelGroup(Alignment.BASELINE)
					.addComponent(runeLbl, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
		);
		runePanel.setLayout(gl_runePanel);
		setContentPane(contentPane);
	}
	
	private void selectBtn(JButton b) {
		viewStudentsBtn.setEnabled(false);
		newStudentBtn.setEnabled(false);
		newSongBtn.setEnabled(false);
		viewSongsBtn.setEnabled(false);
		viewExercisesBtn.setEnabled(false);
		newExerciseBtn.setEnabled(false);
		
		viewStudentsBtn.setOpaque(false);
		newStudentBtn.setOpaque(false);
		newSongBtn.setOpaque(false);
		viewSongsBtn.setOpaque(false);
		viewExercisesBtn.setOpaque(false);
		newExerciseBtn.setOpaque(false);
		
		b.setOpaque(true);
		
		viewStudentsBtn.setEnabled(true);
		newStudentBtn.setEnabled(true);
		newSongBtn.setEnabled(true);
		viewSongsBtn.setEnabled(true);
		viewExercisesBtn.setEnabled(true);
		newExerciseBtn.setEnabled(true);
	}
}
