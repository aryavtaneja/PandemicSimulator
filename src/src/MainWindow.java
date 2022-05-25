package src;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
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

public class MainWindow extends JFrame {

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\danie\\Downloads\\unknown.png"));
		setTitle("Pestilence Corporation - Pandemic Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DiseaseName = new JTextField();
		DiseaseName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		DiseaseName.setToolTipText("Disease Name");
		DiseaseName.setColumns(10);
		
		JSlider mortality = new JSlider();
		mortality.setMinorTickSpacing(1);
		mortality.setMajorTickSpacing(2);
		mortality.setPaintLabels(true);
		mortality.setPaintTicks(true);
		mortality.setValue(0);
		mortality.setMaximum(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Infant");
		
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
		
		JLabel MortalityLabel_1 = new JLabel("Mortality Level");
		MortalityLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(chckbxNewCheckBox, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxAdult, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(1060))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(chckbxYouth, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxBoomer, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(mortality, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(868, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(MortalityLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(1093, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(infectability, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(868, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(DiseaseName, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
									.addGap(848))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(infectivityLbael, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(1093, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(MortalityLabel_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(1093, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(incubationPeriodSlider, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(868, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(Sucspet, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(1093, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(diseaseNameLabel, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
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
					.addComponent(MortalityLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(chckbxNewCheckBox)
						.addComponent(chckbxAdult))
					.addContainerGap(228, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
