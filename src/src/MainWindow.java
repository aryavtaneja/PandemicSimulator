/**
 * Main GUI for the game.
 * @author Daniel Welicki 
 */

package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.event.ChangeEvent;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.border.EmptyBorder;
import java.awt.Point;
import javax.swing.JProgressBar;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // Not necessary, but convention to keep this

	private static Simulator game = new Simulator();

	private JPanel Game;
	private JPanel statsScreen;
	private static JTextField diseaseName;
	private int times = 0;
	private JPanel mainGame;
	private JTextArea gameText;
	private Day currentDay;
	private JButton nextDayButton;
	private JButton doubleSpeed;
	private JButton fastForward;
	private static DecimalFormat numberFormat = new DecimalFormat("#.00");
	private static JProgressBar infectionsProgressBar;
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

	
	public static boolean updateText(Day currentDay, JTextArea gameText) {
		if (game.getInfectedPeople().size() == 0) {
			gameText.setBounds(50, 300, 620, 384);
			gameText.setText(
					" Disease " + getDiseaseName().getText() + " has run it's course." +
					"\n It took " + currentDay.dayNumber() + " days." +
					"\n The initial population was " + game.getPopulation().length + " people." +
					"\n " + Day.totalDeaths() + " people died." + 
					"\n " + Day.totalRecoveries() + " people recovered." + 
					"\n " + Day.totalCases() + "  people were infected." +
					"\n " + numberFormat.format(((double) Day.totalDeaths() / Day.totalCases()) * 100) + "% of cases died." + 
					"\n " + numberFormat.format(((double) Day.totalRecoveries() / Day.totalCases()) * 100) + "% of cases recovered." +
					"\n The peak day of infections was Day " + Simulator.getMaxDay().dayNumber() + "."
					
);
			
			
			return true;
		} else { 
			infectionsProgressBar.setValue(Day.totalCases());
			gameText.setText(
					" Day " + currentDay.dayNumber() +
							"\n New cases: " + currentDay.cases() +
							"\n New recoveries: " + currentDay.recoveries() +
							"\n Susceptible people: " + game.getSusceptibleCount() + 
							"\n Total active cases: " + game.getInfectedPeople().size() +
							"\n Total deaths: " + Day.totalDeaths() +
							"\n Total recoveries: " + Day.totalRecoveries() +
							"\n Total infections: " + Day.totalCases() + 
							"\n Current peak day: " + Simulator.getMaxDay().dayNumber()
							);
			return false;
			
		}
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

		JMenu resistanceMenu = new JMenu("Resistance Period");
		resistanceMenu.setMnemonic('1');
		optionsMenu.add(resistanceMenu);

		JSlider resistance = new JSlider();
		resistance.setFont(new Font("Tahoma", Font.PLAIN, 6));
		resistance.setMinimum(1);
		resistance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				game.updateResistance(resistance.getValue());

			}
		});
		resistanceMenu.add(resistance);
		resistance.setValue(7);
		resistance.setPaintTicks(true);
		resistance.setPaintLabels(true);
		resistance.setMinorTickSpacing(1);
		resistance.setMaximum(31);
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
		incubationPeriodSlider.setValue(7);
		incubationPeriodSlider.setPaintTicks(true);
		incubationPeriodSlider.setPaintLabels(true);
		incubationPeriodSlider.setMinorTickSpacing(1);
		incubationPeriodSlider.setMaximum(31);
		incubationPeriodSlider.setMajorTickSpacing(2);

		JMenu mortalityMenu = new JMenu("Mortality");
		mortalityMenu.setMnemonic('3');
		optionsMenu.add(mortalityMenu);

		JSlider mortality = new JSlider();
		mortality.setMaximum(10);
		mortality.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				game.updateMortality(mortality.getValue());

			}
		});
		mortalityMenu.add(mortality);
		mortality.setValue(1);
		mortality.setPaintTicks(true);
		mortality.setPaintLabels(true);
		mortality.setMinorTickSpacing(1);
		mortality.setMajorTickSpacing(2);

		JMenu diseaseOptions = new JMenu("Infectivity");
		diseaseOptions.setMnemonic('4');
		optionsMenu.add(diseaseOptions);

		JSlider infectability = new JSlider();
		infectability.setMinimum(1);
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

		JMenu mnNewMenu = new JMenu("Simulation State");
		configMenu.add(mnNewMenu);

		JButton restartSimulation = new JButton("End Simulation");
		restartSimulation.setEnabled(false);

		restartSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				gameText.setBounds(50, 300, 620, 384);

				gameText.setText(
						
						" Disease " + getDiseaseName().getText() + " has run it's course." +
								"\n It took " + currentDay.dayNumber() + " days." +
								"\n The initial population was " + game.getPopulation().length + " people." +
								"\n " + Day.totalDeaths() + " people died." + 
								"\n " + Day.totalRecoveries() + " people recovered." +
								"\n " + Day.totalCases() + " people were infected." +
								"\n " + numberFormat.format(((double) Day.totalDeaths() / Day.totalCases()) * 100) + "% of cases died." + 
								"\n " + numberFormat.format(((double) Day.totalRecoveries() / Day.totalCases()) * 100) + "% of cases recovered." +
								"\n The peak day of infections was Day " + Simulator.getMaxDay().dayNumber() + "."

								
								);
				
				nextDayButton.setEnabled(false);
				fastForward.setEnabled(false);
				doubleSpeed.setEnabled(false);
				restartSimulation.setEnabled(false);

				mainGame.removeAll();
				setContentPane(statsScreen);
				statsScreen.add(gameText);
			}
		});
		mnNewMenu.add(restartSimulation);

		JMenu ffConfig = new JMenu("Fast Forward Config");
		configMenu.add(ffConfig);

		fastForward = new JButton();
		doubleSpeed = new JButton();

		JLabel daysLabel = new JLabel("Days to Forward");
		ffConfig.add(daysLabel);
		daysLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		JSlider advance = new JSlider();
		ffConfig.add(advance);
		advance.setFont(new Font("Tahoma", Font.PLAIN, 7));
		advance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				fastForward.setText("Forward " + advance.getValue() + " Days");
				doubleSpeed.setText("Forward " + advance.getValue() * 2 + " Days");
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
		helpMenu.setBounds(new Rectangle(200, 250));

		JTextArea welcome = new JTextArea();
		welcome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		welcome.setWrapStyleWord(true);
		welcome.setColumns(2);
		welcome.setEditable(false);
		welcome.setDropMode(DropMode.INSERT);
		welcome.setLineWrap(true);
		welcome.setText(
				"Welcome to Pestilence Corporation. Pestilence Corporation is a pandemic simulator, operating off of several main variables.  Create your virus by setting the starting conditions, and see where the game will take you! Go to game config to edit how fast your fast forward option can be. Options are dynamically updatable through the course of a simulation. ");
		welcome.setBounds(new Rectangle(200, 250));
		howToPlay.add(welcome);

		JMenu variablesMenu = new JMenu("Variables");
		helpMenu.add(variablesMenu);

		JMenu infectivityDropdown = new JMenu("Infectivity");
		variablesMenu.add(infectivityDropdown);
      
		JTextArea infectivityText = new JTextArea();
		infectivityText.setWrapStyleWord(true);
		infectivityText.setText(
				"The probability that a person who interacts with an infected person also gets infected.  Based on a scale of 1-10, with 10 being the greatest.");
		infectivityText.setLineWrap(true);
		infectivityText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		infectivityText.setEditable(false);
		infectivityText.setDropMode(DropMode.INSERT);
		infectivityText.setColumns(2);
		infectivityText.setBounds(new Rectangle(0, 0, 200, 250));
		infectivityDropdown.add(infectivityText);

		JMenu mortalityDropdown = new JMenu("Mortality");
		variablesMenu.add(mortalityDropdown);

		JTextArea mortalityText = new JTextArea();
		mortalityText.setWrapStyleWord(true);
		mortalityText.setText(
				"A modifier for how lethal the disease is, although final chance of death is also a factor of age group and preexisting conditions. Based on a scale of 1-10, with 10 being the greatest.");
		mortalityText.setLineWrap(true);
		mortalityText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		mortalityText.setEditable(false);
		mortalityText.setDropMode(DropMode.INSERT);
		mortalityText.setColumns(2);
		mortalityText.setBounds(new Rectangle(0, 0, 200, 250));
		mortalityDropdown.add(mortalityText);

		JMenu incubationDropdown = new JMenu("Incubation Period");
		variablesMenu.add(incubationDropdown);

		JTextArea incubationText = new JTextArea();
		incubationText.setWrapStyleWord(true);
		incubationText.setText(
				"The number of days which the virus incubates in an infected person. The person is able to spread the disease in this time, but isn't able to die during this time. Based on a scale of 1-31 days.\r\n");
		incubationText.setLineWrap(true);
		incubationText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		incubationText.setEditable(false);
		incubationText.setDropMode(DropMode.INSERT);
		incubationText.setColumns(2);
		incubationText.setBounds(new Rectangle(0, 0, 200, 250));
		incubationDropdown.add(incubationText);

		JMenu resistanceDropdown = new JMenu("Resistance Period");
		variablesMenu.add(resistanceDropdown);

		JTextArea resistanceText = new JTextArea();
		resistanceText.setWrapStyleWord(true);
		resistanceText.setText(
				"The number of days that the person \"feels symptoms\" and is able to die. Every day of this period, they have a chance of death based on mortality, age and preexisting conditions. If they survive these many days, they are considered recovered and cannot be infected again. Based on a scale of 1-10, with 10 being the greatest.");
		resistanceText.setLineWrap(true);
		resistanceText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		resistanceText.setEditable(false);
		resistanceText.setDropMode(DropMode.INSERT);
		resistanceText.setColumns(2);
		resistanceText.setBounds(new Rectangle(0, 0, 200, 250));
		resistanceDropdown.add(resistanceText);

		JMenu goodiesMenu = new JMenu("Goodies ");
      goodiesMenu.setLayout(null);
		helpMenu.add(goodiesMenu);

		JLabel icon = new JLabel("");
		icon.setPreferredSize(new Dimension(250, 250));

		icon.setIcon(new ImageIcon(MainWindow.class.getResource("/img/icon_small.png")));

		goodiesMenu.add(icon);
		Game = new JPanel();
		Game.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Game);
		
      
      
        JFormattedTextField easterEggField = new JFormattedTextField();
        

        easterEggField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(easterEggField.getText().toLowerCase().equals("wwssadadba")) {
                    icon.setIcon(new ImageIcon(MainWindow.class.getResource("/img/morb.jpg")));
                    goodiesMenu.remove(easterEggField);
                    
                    JTextArea morbiusLove = new JTextArea();
                    morbiusLove.setLineWrap(true);
                    morbiusLove.setText("If Morbius has a million fans, I am one of them. If Morbius has 5 fans, I am one of them. If Morbius has one fan, That one is me. If Morbius has no fans, I am no longer alive. If the world is against Morbius, I am against the world. Till my last breath, I'll love Morbius (2022).");
                    morbiusLove.setPreferredSize(new Dimension(250,100));

                    goodiesMenu.add(morbiusLove);


                }
            }
        });
        easterEggField.setText("Any konami fans in the chat?");
        goodiesMenu.add(easterEggField);
        easterEggField.setColumns(5);

		
		statsScreen = new JPanel();
		statsScreen.setBounds(0, 0, 0, 0);
		statsScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		

		mainGame = new JPanel();
		Game.setLayout(null);
		GroupLayout gl_Game = new GroupLayout(Game);
		gl_Game.setHorizontalGroup(
			gl_Game.createParallelGroup(Alignment.LEADING)
				.addGap(0, 696, Short.MAX_VALUE)
		);
		gl_Game.setVerticalGroup(
			gl_Game.createParallelGroup(Alignment.LEADING)
				.addGap(0, 651, Short.MAX_VALUE)
		);
		gl_Game.setHorizontalGroup(
			gl_Game.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Game.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(mainGame, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)
					.addGap(766))
		);
		gl_Game.setVerticalGroup(
			gl_Game.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Game.createSequentialGroup()
					.addComponent(mainGame, GroupLayout.PREFERRED_SIZE, 627, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);

		JLabel worldBackground = new JLabel("");
		worldBackground.setBounds(0, 11, 676, 544);
		worldBackground.setIcon(new ImageIcon(MainWindow.class.getResource("/img/back.png")));
		mainGame.setLayout(null);
		mainGame.add(fastForward);
		mainGame.add(worldBackground);

		gameText = new JTextArea();
		gameText.setWrapStyleWord(true);
		gameText.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 25));
		gameText.setLineWrap(true);
		gameText.setText(
				"Welcome to Pestilence Corporation. Craft your disease by going  to the Options, and let the infection begin. ");
		gameText.setOpaque(false);
		gameText.setBounds(33, 89, 620, 384);
		gameText.setEditable(false);
		mainGame.add(gameText);
		Game.setLayout(gl_Game);
		
		fastForward.setEnabled(false);
		doubleSpeed.setEnabled(false);
		
		nextDayButton = new JButton();
		nextDayButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		mainGame.add(nextDayButton);
		nextDayButton.setBounds(10, 566, 200, 37);
		nextDayButton.setText("Start Simulation (Day 0)");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (times == 0) {
					infectionsProgressBar.setIndeterminate(false);
					nextDayButton.setText("Next Day");
					game.initPatientZero();
					times++;
					fastForward.setEnabled(true);
					doubleSpeed.setEnabled(true);
					restartSimulation.setEnabled(true);


				}
				currentDay = game.simulate();

				setTitle("Pestilence Corporation - Day " + currentDay.dayNumber());
				if (updateText(currentDay, gameText)) {
					nextDayButton.setEnabled(false);
					fastForward.setEnabled(false);
					doubleSpeed.setEnabled(false);
					restartSimulation.setEnabled(false);
					mainGame.removeAll();
					setContentPane(statsScreen);
					statsScreen.add(gameText);
				}
				
			}
		});

		fastForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < advance.getValue(); i++) {
					currentDay = game.simulate();
					if(currentDay.infect() == 0) {
						break;
					}
				}
				setTitle("Pestilence Corporation - Day " + currentDay.dayNumber());
				if (updateText(currentDay, gameText)) {
					nextDayButton.setEnabled(false);
					fastForward.setEnabled(false);
					doubleSpeed.setEnabled(false);
					mainGame.removeAll();
					setContentPane(statsScreen);
					statsScreen.add(gameText);
				}
			}
		});
		fastForward.setBounds(236, 565, 200, 37);
		fastForward.setFont(new Font("Calibri", Font.PLAIN, 14));

		doubleSpeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < advance.getValue() * 2; i++) {
					currentDay = game.simulate();
					if(currentDay.infect() == 0) {
						break;
					}
				}
				setTitle("Pestilence Corporation - Day " + currentDay.dayNumber());
				if (updateText(currentDay, gameText)) {
					nextDayButton.setEnabled(false);
					fastForward.setEnabled(false);
					doubleSpeed.setEnabled(false);
					mainGame.removeAll();
					setContentPane(statsScreen);
					statsScreen.add(gameText);
				}
				
				
			}
		});
		doubleSpeed.setFont(new Font("Calibri", Font.PLAIN, 14));
		doubleSpeed.setBounds(458, 565, 200, 37);
		mainGame.add(doubleSpeed);
		
		infectionsProgressBar = new JProgressBar();
		infectionsProgressBar.setIndeterminate(true);
		infectionsProgressBar.setStringPainted(true);
		infectionsProgressBar.setMaximum(game.getPopulation().length);
		infectionsProgressBar.setBounds(33, 28, 608, 31);
		mainGame.add(infectionsProgressBar);
		statsScreen.setLayout(null);
		Game.add(statsScreen);

		
		JLabel graphScreen = new JLabel("");
		graphScreen.setIcon(new ImageIcon(MainWindow.class.getResource("/img/icon_small.png")));
		graphScreen.setLocation(new Point(200, 0));
		graphScreen.setPreferredSize(new Dimension(500, 500));
		graphScreen.setBounds(225, -100, 500, 500);
		statsScreen.add(graphScreen);
		
		JButton csvButton = new JButton("Download .csv of data");
		csvButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	            choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = choose.showOpenDialog(null);

	            if(returnValue == JFileChooser.APPROVE_OPTION){
	                File file = choose.getSelectedFile();
	                csvButton.setText("Folder Selected: " + file.getName() + " - File saved as " + getDiseaseName().getText() + ".csv");
	                try {
						ExcelWriter.writeCsv(getDiseaseName().getText(), file.getAbsolutePath());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						csvButton.setText("File unable to be written.");
					}
	             }else{
	                csvButton.setText("Open command canceled");

	             } }});
		csvButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		csvButton.setBounds(40, 575, 600, 50);
		statsScreen.add(csvButton);

	}
	
	public static JTextField getDiseaseName() {
		return diseaseName;
	}
	
}