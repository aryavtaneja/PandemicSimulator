package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSlider;
import java.awt.List;
import java.awt.Choice;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class MainWindow extends JFrame {
	
	private static Simulator game = new Simulator();

	private JPanel contentPane;
	private JTextField DiseaseName;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("\\unknown.png"));
		setTitle("Pestilence Corporation - Pandemic Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DiseaseName = new JTextField();
		DiseaseName.setToolTipText("Disease Name");
		DiseaseName.setColumns(10);
		
		JSlider mortality = new JSlider();
		mortality.setMinorTickSpacing(1);
		mortality.	setMajorTickSpacing(2);
		mortality.setPaintLabels(true);
		mortality.setPaintTicks(true);
		mortality.setValue(0);
		mortality.setMaximum(10);
		
		JCheckBox chckbxInfant = new JCheckBox("Infant");
		
		JCheckBox chckbxYouth = new JCheckBox("Youth");
		
		JCheckBox chckbxAdult = new JCheckBox("Adult");
		
		JCheckBox chckbxBoomer = new JCheckBox("Boomer");

 
		
		JSlider infectability = new JSlider();
		infectability.setValue(0);
		infectability.setPaintTicks(true);
		infectability.setPaintLabels(true);
		infectability.setMinorTickSpacing(1);
		infectability.setMaximum(10);
		infectability.setMajorTickSpacing(2);
		
		JLabel Sucspet = new JLabel("Susceptible Groups");
		Sucspet.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel MortalityLabel = new JLabel("Mortality Level");
		MortalityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel infectivityLbael = new JLabel("Infectivity");
		infectivityLbael.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel diseaseNameLabel = new JLabel("Disease Name");
		diseaseNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JSlider incubationPeriodSlider = new JSlider();
		incubationPeriodSlider.setValue(0);
		incubationPeriodSlider.setPaintTicks(true);
		incubationPeriodSlider.setPaintLabels(true);
		incubationPeriodSlider.setMinorTickSpacing(1);
		incubationPeriodSlider.setMaximum(31);
		incubationPeriodSlider.setMajorTickSpacing(2);
		
		JLabel incubationPeriod = new JLabel("Incubation Period");
		incubationPeriod.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton startButton = new JButton("Update");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simulator.updateInfectability(infectability.getValue());
				Simulator.updateName(DiseaseName.getText());
				Simulator.updateMortality(mortality.getValue());
				boolean[] sus = new boolean[4];
				sus[0] = chckbxInfant.isSelected();
				sus[1] = chckbxYouth.isSelected();
				sus[2] = chckbxAdult.isSelected();
				sus[3] = chckbxBoomer.isSelected();
				Simulator.updateSus(sus);
				Simulator.updateIncubation(incubationPeriodSlider.getValue());
				

			}
		});
		
		JButton nextDayButton = new JButton("Next");
		nextDayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nextDayButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JSlider advance = new JSlider();
		advance.setMajorTickSpacing(1);
		advance.setSnapToTicks(true);
		advance.setMaximum(14);
		advance.setPaintTicks(true);
		advance.setPaintLabels(true);
		
		JLabel daysLabel = new JLabel("Days to Forward");
		daysLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(chckbxYouth, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxBoomer, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(MortalityLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1093, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(infectivityLbael, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1093, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(incubationPeriod, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1093, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Sucspet, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1093, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(diseaseNameLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1093, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chckbxInfant, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxAdult, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
							.addGap(1060))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(incubationPeriodSlider, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
								.addComponent(mortality, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
								.addComponent(infectability, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
								.addComponent(DiseaseName, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
							.addGap(848))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(advance, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
							.addGap(848))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(nextDayButton, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1060, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(daysLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1093, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addComponent(diseaseNameLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(DiseaseName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(infectivityLbael, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(infectability, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(MortalityLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(mortality, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(incubationPeriod, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(incubationPeriodSlider, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(Sucspet)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxYouth)
						.addComponent(chckbxBoomer))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxInfant)
						.addComponent(chckbxAdult))
					.addGap(18)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(daysLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(nextDayButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(advance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
