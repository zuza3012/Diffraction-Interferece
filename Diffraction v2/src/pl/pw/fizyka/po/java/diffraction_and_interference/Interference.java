package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jxl.read.biff.BiffException;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Interference extends JFrame 
{
	private static final long serialVersionUID = 1L;

	Interference() throws BiffException, IOException
	{
		this.setSize(1500,1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Diffraction and interference");
			
		cWavePanel = new CircularWave();
		wavePanel = new Wave();
		graphPanel = new Graph();
		slidersPanel = new Sliders(this);
		buttonsPanel = new Buttons();
		graphicsPanel = new JPanel();
		parametersPanel = new JPanel();
		mainPanel = new JPanel();
		menu = new MenuBar(graphPanel);
		
		
		//handling Panels
		graphicsPanel.add(graphPanel);
		graphicsPanel.add(cWavePanel);
		graphicsPanel.add(wavePanel);
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
		
		//simulation speed
		vel = 1;
		
		//wave color
		plainWaveColor = Color.BLACK;
	}
	
	public void setSimulationSpeed(int speed)
	{
		Interference.vel = speed;
		System.out.println("Ustawiono predkosc" + vel);
	}	
	
	public static void main(String[] args) throws BiffException, IOException 
	{
		
		
		SwingUtilities.invokeLater(new Runnable() 
		{

			public void run()
			{
				Interference idFrame;
				try 
				{
					idFrame = new Interference();
					Wave panel = new Wave();
					
					
					panel.addRectangle(0, 600, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 550, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 500, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 450, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 400, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 350, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 300, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 250, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 200, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 150, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 100, 1000, 10, plainWaveColor,vel);
					panel.addRectangle(0, 50, 1000, 10, plainWaveColor,vel);
					
					idFrame.wavePanel.add(panel);
					idFrame.setVisible(true);
					
					ExecutorService exec = Executors.newFixedThreadPool(1);
					exec.execute(panel);
				} 
				catch (BiffException | IOException e)
				{
					e.printStackTrace();
				}

			}
		});
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
	static int vel;
	static Color plainWaveColor;
}

//nie mo¿na na razie zmieniæ ani koloru ani szybkosci symulacji bo nie mozna zmienic parametrow prostokata po tym jak ruszyl