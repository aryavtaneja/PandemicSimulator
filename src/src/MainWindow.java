/**
 * Main GUI for the game.
 * @author Daniel Welicki 
 */

package src;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
<<<<<<< HEAD
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JMenuBar;
=======
import javax.swing.JFrame;
import javax.swing.JLabel;
>>>>>>> 0550d4ff0c207cd18524cb54c4c6b645b7d667d8
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
<<<<<<< HEAD
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.DropMode;
=======
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
>>>>>>> 0550d4ff0c207cd18524cb54c4c6b645b7d667d8

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // Not necessary, but convention to keep this 

	private static Simulator game = new Simulator();

	private JPanel Game;
	private JPanel statsScreen;
	private JTextField diseaseName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setFont(new Font("Calibri", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/icon.png")));
		setTitle("Pestilence Corporation - Pandemic Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 720);
		
		JMenuBar diseaseConfig = new JMenuBar();
		diseaseConfig.setToolTipText("Game Config");
		setJMenuBar(diseaseConfig);
		
		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic('1');
		diseaseConfig.add(optionsMenu);
		
		JMenu resistanceMenu = new JMenu("Resistance");
		resistanceMenu.setMnemonic('1');
		optionsMenu.add(resistanceMenu);
		
		JSlider resistance = new JSlider();
		resistance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				game.updateResistance(resistance.getValue());

			}
		});
		resistanceMenu.add(resistance);
		resistance.setValue(0);
		resistance.setPaintTicks(true);
		resistance.setPaintLabels(true);
		resistance.setMinorTickSpacing(1);
		resistance.setMaximum(10);
		resistance.setMajorTickSpacing(2);
		
		JMenu incubationMenu = new JMenu("Incubation Period");
		incubationMenu.setMnemonic('2');
		optionsMenu.add(incubationMenu);
		
		JSlider incubationPeriodSlider = new JSlider();
		incubationPeriodSlider.setFont(new Font("Tahoma", Font.PLAIN, 5));
		incubationPeriodSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				game.updateIncubation(incubationPeriodSlider.getValue());

			}
		});
		incubationPeriodSlider.setMinimum(1);
		incubationMenu.add(incubationPeriodSlider);
		incubationPeriodSlider.setValue(0);
		incubationPeriodSlider.setPaintTicks(true);
		incubationPeriodSlider.setPaintLabels(true);
		incubationPeriodSlider.setMinorTickSpacing(1);
		incubationPeriodSlider.setMaximum(31);
		incubationPeriodSlider.setMajorTickSpacing(2);
		
		JMenu mortalityMenu = new JMenu("Mortality");
		mortalityMenu.setMnemonic('3');
		optionsMenu.add(mortalityMenu);
		
		JSlider mortality = new JSlider();
		mortality.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				game.updateMortality(mortality.getValue());

			}
		});
		mortalityMenu.add(mortality);
		mortality.setValue(0);
		mortality.setPaintTicks(true);
		mortality.setPaintLabels(true);
		mortality.setMinorTickSpacing(1);
		mortality.setMaximum(10);
		mortality.setMajorTickSpacing(2);
		
		JMenu diseaseOptions = new JMenu("Infectivity");
		diseaseOptions.setMnemonic('4');
		optionsMenu.add(diseaseOptions);
		
		JSlider infectability = new JSlider();
		infectability.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				game.updateInfectability(infectability.getValue());

			}
		});
		diseaseOptions.add(infectability);
		infectability.setToolTipText("Infectivity");
		infectability.setSnapToTicks(true);
		infectability.setValue(0);
		infectability.setPaintTicks(true);
		infectability.setPaintLabels(true);
		infectability.setMinorTickSpacing(1);
		infectability.setMaximum(10);
		infectability.setMajorTickSpacing(2);
		
		JMenu susMenu = new JMenu("Susceptible Groups");
		susMenu.setMnemonic('5');
		optionsMenu.add(susMenu);
		
		JCheckBox youthBox = new JCheckBox("Youth");
		susMenu.add(youthBox);
		
		JCheckBox infantBox = new JCheckBox("Infant");
		susMenu.add(infantBox);
		
		JCheckBox adultBox = new JCheckBox("Adult");
		susMenu.add(adultBox);
		
		JCheckBox boomerBox = new JCheckBox("Boomer");
		susMenu.add(boomerBox);
		
		JButton updateSettings = new JButton("Update Settings");
		updateSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean[] sus = {infantBox.isSelected(), youthBox.isSelected(), adultBox.isSelected(), boomerBox.isSelected()};
				game.updateSus(sus);				
			}
		});
		updateSettings.setVerticalAlignment(SwingConstants.BOTTOM);
		updateSettings.setToolTipText("Update Settings");
		updateSettings.setOpaque(false);
		updateSettings.setHorizontalAlignment(SwingConstants.LEFT);
		updateSettings.setFont(new Font("Tahoma", Font.PLAIN, 11));
		updateSettings.setFocusPainted(false);
		updateSettings.setBorderPainted(false);
		updateSettings.setBackground(Color.WHITE);
		susMenu.add(updateSettings);
		
		JMenu diseaseNameMenu = new JMenu("Disease Name");
		diseaseNameMenu.setMnemonic('6');
		optionsMenu.add(diseaseNameMenu);
		
		diseaseName = new JTextField();
		diseaseName.setText("PAX-12");
		diseaseName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				game.updateName(diseaseName.getText());
			}
		});
		diseaseName.setToolTipText("Disease Name");
		diseaseName.setColumns(10);
		diseaseNameMenu.add(diseaseName);
		
		JMenu configMenu = new JMenu("Game Config");
		diseaseConfig.add(configMenu);
		
		
		JButton fastForward = new JButton();
		fastForward.setBounds(215, 566, 200, 37);
		fastForward.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JMenu mnNewMenu = new JMenu("Simulation State");
		configMenu.add(mnNewMenu);
		
		JButton endSimulation = new JButton("End Simulation");
		mnNewMenu.add(endSimulation);
		
		JButton restartSimulation = new JButton("Restart Simulation");
		mnNewMenu.add(restartSimulation);
		
		JMenu ffConfig = new JMenu("Fast Forward Config");
		configMenu.add(ffConfig);
		
		
		JLabel daysLabel = new JLabel("Days to Forward");
		ffConfig.add(daysLabel);
		daysLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		JSlider advance = new JSlider();
		ffConfig.add(advance);
		advance.setFont(new Font("Tahoma", Font.PLAIN, 7));
		advance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fastForward.setText("Forward " + advance.getValue() + " Days");
			}
		});
		advance.setMinimum(1);
		advance.setBackground(Color.WHITE);
		advance.setSnapToTicks(true);
		advance.setPaintTicks(true);
		advance.setPaintLabels(true);
		advance.setMaximum(14);
		advance.setMajorTickSpacing(1);
		
		JMenu helpMenu = new JMenu("Help");
		diseaseConfig.add(helpMenu);
		
		JMenu howToPlay = new JMenu("Description");
		helpMenu.add(howToPlay);
		helpMenu.setBounds(new Rectangle(200,250));
		
		JTextArea welcome = new JTextArea();
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		welcome.setWrapStyleWord(true);
		welcome.setColumns(2);
		welcome.setEditable(false);
		welcome.setDropMode(DropMode.INSERT);
		welcome.setLineWrap(true);
		welcome.setText("Welcome to Pestilence Corporation. Pestilence Corporation is a pandemic simulator, operating off of several main variables.  Create your virus by setting the starting conditions, and see where the game will take you! Go to game config to edit how fast your fast forward option can be. Options are dynamically updatable through the course of a simulation. Go to simulation state to end the simulation.   ");
		welcome.setBounds(new Rectangle(200,250));
		howToPlay.add(welcome);
		
		JMenu variablesMenu = new JMenu("Variables");
		helpMenu.add(variablesMenu);
		
		JMenu infectivityDropdown = new JMenu("Infectivity");
		variablesMenu.add(infectivityDropdown);
		
		JTextArea welcome_1 = new JTextArea();
		welcome_1.setWrapStyleWord(true);
		welcome_1.setText("The probability that a person who interacts with an infected person also gets infected.  Based on a scale of 1-10, with 10 being the greatest.");
		welcome_1.setLineWrap(true);
		welcome_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		welcome_1.setEditable(false);
		welcome_1.setDropMode(DropMode.INSERT);
		welcome_1.setColumns(2);
		welcome_1.setBounds(new Rectangle(0, 0, 200, 250));
		infectivityDropdown.add(welcome_1);
		
		JMenu mortalityDropdown = new JMenu("Mortality");
		variablesMenu.add(mortalityDropdown);
		
		JTextArea welcome_2 = new JTextArea();
		welcome_2.setWrapStyleWord(true);
		welcome_2.setText("A modifier for how lethal the disease is, although final chance of death is also a factor of age group and preexisting conditions. Based on a scale of 1-10, with 10 being the greatest.");
		welcome_2.setLineWrap(true);
		welcome_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		welcome_2.setEditable(false);
		welcome_2.setDropMode(DropMode.INSERT);
		welcome_2.setColumns(2);
		welcome_2.setBounds(new Rectangle(0, 0, 200, 250));
		mortalityDropdown.add(welcome_2);
		
		JMenu susDropdown = new JMenu("Susceptibilities ");
		variablesMenu.add(susDropdown);
		
		JTextArea txtrWhichAgeGroups = new JTextArea();
		txtrWhichAgeGroups.setWrapStyleWord(true);
		txtrWhichAgeGroups.setText("Which age groups (children, adults, seniors) are more susceptible to the disease than others. Multiple are possible (i.e. children and seniors), so check off all groups that are susceptible to infection. ");
		txtrWhichAgeGroups.setLineWrap(true);
		txtrWhichAgeGroups.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrWhichAgeGroups.setEditable(false);
		txtrWhichAgeGroups.setDropMode(DropMode.INSERT);
		txtrWhichAgeGroups.setColumns(2);
		txtrWhichAgeGroups.setBounds(new Rectangle(0, 0, 200, 250));
		susDropdown.add(txtrWhichAgeGroups);
		
		JMenu incubationDropdown = new JMenu("Incubation Period");
		variablesMenu.add(incubationDropdown);
		
		JTextArea txtrTheNumberOf = new JTextArea();
		txtrTheNumberOf.setWrapStyleWord(true);
		txtrTheNumberOf.setText("The number of days which the virus incubates in an infected person. The person is able to spread the disease in this time, but isn't able to die during this time. Based on a scale of 1-31 days.\r\n");
		txtrTheNumberOf.setLineWrap(true);
		txtrTheNumberOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrTheNumberOf.setEditable(false);
		txtrTheNumberOf.setDropMode(DropMode.INSERT);
		txtrTheNumberOf.setColumns(2);
		txtrTheNumberOf.setBounds(new Rectangle(0, 0, 200, 250));
		incubationDropdown.add(txtrTheNumberOf);
		
		JMenu resistanceDropdown = new JMenu("Resistance Period");
		variablesMenu.add(resistanceDropdown);
		
		JTextArea txtrTheNumberOf_1 = new JTextArea();
		txtrTheNumberOf_1.setWrapStyleWord(true);
		txtrTheNumberOf_1.setText("The number of days that the person \"feels symptoms\" and is able to die. Every day of this period, they have a chance of death based on mortality, age and preexisting conditions. If they survive these many days, they are considered recovered and cannot be infected again. Based on a scale of 1-10, with 10 being the greatest.");
		txtrTheNumberOf_1.setLineWrap(true);
		txtrTheNumberOf_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrTheNumberOf_1.setEditable(false);
		txtrTheNumberOf_1.setDropMode(DropMode.INSERT);
		txtrTheNumberOf_1.setColumns(2);
		txtrTheNumberOf_1.setBounds(new Rectangle(0, 0, 200, 250));
		resistanceDropdown.add(txtrTheNumberOf_1);
		Game = new JPanel();
		Game.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Game);
		statsScreen = new JPanel();
		statsScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel mainGame = new JPanel();
		GroupLayout gl_Game = new GroupLayout(Game);
		gl_Game.setHorizontalGroup(
			gl_Game.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_Game.createSequentialGroup()
					.addContainerGap(68, Short.MAX_VALUE)
					.addComponent(mainGame, GroupLayout.PREFERRED_SIZE, 732, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_Game.setVerticalGroup(
			gl_Game.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Game.createSequentialGroup()
					.addComponent(mainGame, GroupLayout.PREFERRED_SIZE, 627, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		JButton nextDayButton = new JButton();
		nextDayButton.setBounds(10, 566, 195, 37);
		nextDayButton.setText("Next Day");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nextDayButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel worldBackground = new JLabel("");
		worldBackground.setBounds(0, 11, 676, 544);
		worldBackground.setIcon(new ImageIcon(MainWindow.class.getResource("/img/back.png")));
		mainGame.setLayout(null);
		mainGame.add(nextDayButton);
		mainGame.add(fastForward);
		mainGame.add(worldBackground);
		
		JTextArea txtrWelcomeToPestilence = new JTextArea();
		txtrWelcomeToPestilence.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 13));
		txtrWelcomeToPestilence.setLineWrap(true);
		txtrWelcomeToPestilence.setText("Welcome to Pestilence Corporation. Craft your disease by going  to the Options, and let the infection begin. ");
		txtrWelcomeToPestilence.setOpaque(false);
		txtrWelcomeToPestilence.setBounds(10, 60, 663, 495);
		mainGame.add(txtrWelcomeToPestilence);
		Game.setLayout(gl_Game);
	}
}
