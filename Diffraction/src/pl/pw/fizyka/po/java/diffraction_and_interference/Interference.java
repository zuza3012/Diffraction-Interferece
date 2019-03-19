package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Interference extends JFrame 
{
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	Interference()
	{
		this.setPreferredSize(new Dimension(1000,1000));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setTitle("Diffraction and interference");
		mainPanel = new JPanel();
		menu = new MenuBar();
		
		//contentPane.add(menu,BorderLayout.PAGE_START);
		
		cWavePanel = new CircularWave();
		wavePanel = new Wave();
		graphPanel = new Graph();
		slidersPanel = new Sliders();
		buttonsPanel = new Buttons();
		graphicsPanel = new JPanel();
		parametersPanel = new JPanel();
		mainPanel = new JPanel();
		menu = new MenuBar();
		
		
		//handling Panels
		graphicsPanel.add(cWavePanel);
		graphicsPanel.add(wavePanel);
		graphicsPanel.add(graphPanel);
		parametersPanel.add(slidersPanel);
		parametersPanel.add(buttonsPanel);
		//this.add(graphicsPanel);
		//this.add(parametersPanel);
		
		//adding Panels to mainPanel
		//mainPanel.add(graphicsPanel);
		//mainPanel.add(parametersPanel);
		this.add(mainPanel);
				
		//Menu
		//this.add(menu, BorderLayout.PAGE_START);
		
		//Layouts
		//graphicsPanel.setLayout(new GridLayout(3,1));
		parametersPanel.setLayout(new GridLayout(2,1));
		//mainPanel.setLayout(new GridLayout(1,2));
/////////////////////////////////////////////////////////
		
		setBounds(100, 100, 507, 355);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(menu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		graphicsPanel.setLayout(null);
		GridBagConstraints gbc_graphicsPanel = new GridBagConstraints();
		gbc_graphicsPanel.gridwidth = 14;
		gbc_graphicsPanel.insets = new Insets(0, 0, 0, 5);
		gbc_graphicsPanel.fill = GridBagConstraints.BOTH;
		gbc_graphicsPanel.gridx = 0;
		gbc_graphicsPanel.gridy = 0;
		contentPane.add(graphicsPanel, gbc_graphicsPanel);
		
		
		graphPanel.setBounds(0, 0, 415, 103);
		graphicsPanel.add(graphPanel);
		
		
		cWavePanel.setBounds(0, 105, 415, 95);
		graphicsPanel.add(cWavePanel);
		
		
		wavePanel.setBounds(0, 201, 415, 82);
		graphicsPanel.add(wavePanel);
		
		
		GridBagConstraints gbc_parametersPanel = new GridBagConstraints();
		gbc_parametersPanel.gridwidth = 2;
		gbc_parametersPanel.insets = new Insets(0, 0, 0, 5);
		gbc_parametersPanel.fill = GridBagConstraints.BOTH;
		gbc_parametersPanel.gridx = 14;
		gbc_parametersPanel.gridy = 0;
		contentPane.add(parametersPanel, gbc_parametersPanel);
		
		
		
	}
	
	
	public static void main(String[] args) 
	{	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interference idframe = new Interference();
					idframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Interference idFrame = new Interference();
		idFrame.setVisible(true);
	}
	
	

	CircularWave cWavePanel;
	Wave wavePanel;
	Graph graphPanel;
	Sliders slidersPanel;
	Buttons buttonsPanel;
	JPanel graphicsPanel;
	JPanel parametersPanel;
	JPanel mainPanel;
	MenuBar menu;

}

//glowna klasa
//git check