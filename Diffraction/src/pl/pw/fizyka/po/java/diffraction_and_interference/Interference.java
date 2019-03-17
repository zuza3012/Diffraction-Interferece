package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import java.awt.*;

public class Interference extends JFrame 
{
	private static final long serialVersionUID = 1L;

	Interference()
	{
		this.setSize(1280,720);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setTitle("Diffraction and interference");
			
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
		this.add(graphicsPanel);
		this.add(parametersPanel);
		
		//adding Panels to mainPanel
		mainPanel.add(graphicsPanel);
		mainPanel.add(parametersPanel);
		this.add(mainPanel);
				
		//Menu
		this.add(menu, BorderLayout.PAGE_START);
		
		//Layouts
		graphicsPanel.setLayout(new GridLayout(3,1));
		parametersPanel.setLayout(new GridLayout(2,1));
		mainPanel.setLayout(new GridLayout(1,2));
	}
	
	
	public static void main(String[] args) 
	{
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
