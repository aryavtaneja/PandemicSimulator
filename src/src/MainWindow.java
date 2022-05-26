/**
 * Main GUI for the game.
 * @author Daniel Welicki 
 */

package src.src;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		
		JMenu susMenu = new JMenu("Suscpetible Groups");
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
		updateSettings.setFont(new Font("Calibri", Font.PLAIN, 14));
		updateSettings.setFocusPainted(false);
		updateSettings.setBorderPainted(false);
		updateSettings.setBackground(Color.WHITE);
		susMenu.add(updateSettings);
		
		JMenu diseaseNameMenu = new JMenu("Disease Name");
		diseaseNameMenu.setMnemonic('6');
		optionsMenu.add(diseaseNameMenu);
		
		diseaseName = new JTextField();
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
		
		
		JButton nextDayButton_1 = new JButton();
		nextDayButton_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		
		JLabel daysLabel = new JLabel("Days to Forward");
		daysLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		configMenu.add(daysLabel);
		JSlider advance = new JSlider();
		advance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				nextDayButton_1.setText("Forward " + advance.getValue() + " Days");
			}
		});
		advance.setMinimum(1);
		advance.setBackground(Color.WHITE);
		advance.setSnapToTicks(true);
		advance.setPaintTicks(true);
		advance.setPaintLabels(true);
		advance.setMaximum(14);
		advance.setMajorTickSpacing(1);
		configMenu.add(advance);
		Game = new JPanel();
		Game.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Game);
		statsScreen = new JPanel();
		statsScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		GroupLayout gl_Game = new GroupLayout(Game);
		gl_Game.setHorizontalGroup(
			gl_Game.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Game.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_Game.setVerticalGroup(
			gl_Game.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Game.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 627, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JButton nextDayButton = new JButton();
		nextDayButton.setText("Next Day");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nextDayButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/img/back.png")));
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(nextDayButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nextDayButton_1, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 674, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(nextDayButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(nextDayButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		Game.setLayout(gl_Game);
	}
}
