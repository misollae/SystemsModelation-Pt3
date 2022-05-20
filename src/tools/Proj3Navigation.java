package tools;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import fractals.ChaosGameApp;
import fractals.LSystemApp;
import fractals.LindSystemsApp;
import fractals.MandelbrotApp;
import processing.core.PApplet;
import setup.IProcessingApp;
import setup.ProcessingSetup;

import java.awt.event.*;

public class Proj3Navigation {
	private JFrame frame;
	private int selection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proj3Navigation window = new Proj3Navigation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Proj3Navigation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("*.+");
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(14, 8, 36));
		
		JLabel text = new JLabel("Selecione o projeto:");
		text.setForeground(new Color(125, 127, 201));
		text.setBounds(32, 47, 116, 13);
		frame.getContentPane().add(text);
		
		JButton openProjectBttn = new JButton("Open!");
		openProjectBttn.setBackground(new Color(107, 101, 169));
		openProjectBttn.setBorder(new LineBorder(new Color(86, 80, 141)));
		openProjectBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {	
					// Vemos qual o projeto selecionado e vemos quais as opções que devem ser especificadas
					switch (selection) {
					case 0:
						IProcessingApp app = new ChaosGameApp();
						ProcessingSetup setUp = new ProcessingSetup();
						setUp.setApp(app);
						PApplet.main(setUp.getClass());
						break;
					case 1:
						IProcessingApp app1 = new LindSystemsApp();
						ProcessingSetup setUp1 = new ProcessingSetup();
						setUp1.setApp(app1);
						PApplet.main(setUp1.getClass());
						break;
					case 2:
						IProcessingApp app2 = new MandelbrotApp();
						ProcessingSetup setUp2 = new ProcessingSetup();
						setUp2.setApp(app2);
						PApplet.main(setUp2.getClass());
						break;
					}
				} catch (Exception e2) {
				}
			}
		});
		openProjectBttn.setBounds(119, 88, 69, 21);
		frame.getContentPane().add(openProjectBttn);
		
		JLabel lblNewLabel = new JLabel("- MSSN -");
		lblNewLabel.setBounds(80, 10, 80, 13);
		lblNewLabel.setForeground(new Color(125, 127, 201));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Projeto 3");
		lblNewLabel_1.setForeground(new Color(125, 127, 201));
		lblNewLabel_1.setBounds(80, 23, 67, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label2 = new JLabel("New label");
		label2.setForeground(new Color(125, 127, 201));
		label2.setBounds(21, 162, 167, 13);
		frame.getContentPane().add(label2);
		frame.setBounds(100, 100, 229, 186);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		label2.setText("-");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {" B *. Chaos Game", " C *. Lindenmayer", " D *. Julia & Mandelbrot"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selection = comboBox.getSelectedIndex();
//				if (selection == 2 || selection == 1) {textField1.setEnabled(false); textField2.setEnabled(false);}
//				if (selection == 0 || selection == 3) {textField1.setEnabled(true); textField2.setEnabled(false);}
				switch (selection) {
					case 0, 1:
						break;
					}
				}
		});
		comboBox.setBounds(32, 61, 156, 21);
		frame.getContentPane().add(comboBox);
	}
}
