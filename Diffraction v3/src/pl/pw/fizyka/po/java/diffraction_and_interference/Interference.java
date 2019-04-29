package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;

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
		
		
		//wave color
		defaultColor = Color.BLACK;
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
					CircularWave panel2 = new CircularWave();
					
					
					panel.addRectangle(0, 600, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 550, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 500, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 450, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 400, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 350, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 300, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 250, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 200, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 150, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 100, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					panel.addRectangle(0, 50, 1000, 1, defaultColor,Sliders.simulationSpeed.getValue());
					
					
					panel2.addSemiCircle(350, 320, 0, defaultColor, Sliders.simulationSpeed.getValue()); //pierwsza
					panel2.addColumnOfCircles(350, 320);
					
					panel2.addSemiCircle(400, 320, 0, defaultColor, Sliders.simulationSpeed.getValue());
					panel2.addColumnOfCircles(400, 320);
					
					panel2.addSemiCircle(450, 320, 0, defaultColor, Sliders.simulationSpeed.getValue());
					panel2.addColumnOfCircles(450, 320);
					
					panel2.addSemiCircle(500, 320, 0, defaultColor, Sliders.simulationSpeed.getValue()); //srodek
					panel2.addColumnOfCircles(500, 320);
					
					panel2.addSemiCircle(550, 320, 0, defaultColor, Sliders.simulationSpeed.getValue());
					panel2.addColumnOfCircles(550, 320);
					
					panel2.addSemiCircle(600, 320, 0, defaultColor, Sliders.simulationSpeed.getValue());
					panel2.addColumnOfCircles(600, 320);
					
					panel2.addSemiCircle(650, 320, 0, defaultColor, Sliders.simulationSpeed.getValue());
					panel2.addColumnOfCircles(650, 320);
					
					
					idFrame.wavePanel.add(panel);
					idFrame.cWavePanel.add(panel2);
					idFrame.setVisible(true);
					
					ExecutorService exec = Executors.newFixedThreadPool(2);
					exec.execute(panel);
					exec.execute(panel2);
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

	static Color defaultColor;
}

//przez to ze rysuje duzo kolek, trzeba pomyslec o odpowiednim dobraniu predkosci fali kolowej do fali plaskiej, zeby ich predkosci 
//byly zgodne (to zeby sie zaczynalo rysowac kolko rowno z tym jak "uderzy" w szczeline fala plaska jest drugorzedne)