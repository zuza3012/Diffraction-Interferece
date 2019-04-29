package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sliders extends JPanel
{
	Sliders(Interference idFrame)
	{	
		
		waveLength = new JTextField("576");
		description1 = new JLabel("Odleglosc od ekranu [m]");
		description2 = new JLabel("Odleglosc szczelin od siebie [mm]");
		description3 = new JLabel("Dlugosc fali [nm]");
		description4 = new JLabel("Szybkosc symulacji");
		description5 = new JLabel("Ilosc szczelin");
		
		
		screenDistance = new JSlider(JSlider.HORIZONTAL,10,100,50);
		screenDistance.setMajorTickSpacing(10);
		screenDistance.setMinorTickSpacing(5);
		screenDistance.setPaintTicks(true);
		screenDistance.setPaintLabels(true);
		
		slitsDistance = new JSlider(JSlider.HORIZONTAL,10,100,50);
		slitsDistance.setMajorTickSpacing(10);
		slitsDistance.setMinorTickSpacing(5);
		slitsDistance.setPaintTicks(true);
		slitsDistance.setPaintLabels(true);
		
		simulationSpeed = new JSlider(JSlider.HORIZONTAL,1,5,1);
		simulationSpeed.setMajorTickSpacing(2);
		simulationSpeed.setMinorTickSpacing(1);
		simulationSpeed.setPaintTicks(true);
		simulationSpeed.setPaintLabels(true);
		
		scDis = new JLabel(Integer.toString(screenDistance.getValue()));
		slDis = new JLabel(Integer.toString(slitsDistance.getValue()));
		simSpeed = new JLabel(Integer.toString(simulationSpeed.getValue()));
		
		String[] n = {"1","2","3","4","5"};
		slitsQuantity = new JComboBox<>(n);
		
		

		/*
		GridLayout layout = new GridLayout(0,2);
		layout.setWidth("100%");
		layout.setColumnExpandRatio(0, 0.5f);
		layout.setColumnExpandRatio(1, 0.5f);
		layout.addComponent(new Label("foo"), 0, 0);
		layout.addComponent(new Label("foo"), 1, 0);
		layout.setSpacing(true);
		*/

		setLayout(new GridBagLayout());
		
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
				
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(description3,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		waveLength.setColumns(17);
		this.add(waveLength,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(description1,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.add(screenDistance,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(scDis, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(description2,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(slitsDistance,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(slDis, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(description4,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		this.add(simulationSpeed,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(simSpeed,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(description5,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		this.add(slitsQuantity,gbc);
		
		
		//this.setLayout(new GridLayout(12,1));
		this.setBackground(Color.YELLOW);
		
		ChangeListener speedListener = new ChangeListener() 
		{
			public void stateChanged(ChangeEvent e) 
			{
				Wave.vel = (-1)*simulationSpeed.getValue();
				CircularWave.vel = simulationSpeed.getValue();
				simSpeed.setText(String.format("%d", simulationSpeed.getValue()));
			}
		};
		
		screenDistance.addChangeListener(new SliderChangeListener());
		slitsDistance.addChangeListener(new SliderChangeListener());
		//simulationSpeed.addChangeListener(new SliderChangeListener());
		simulationSpeed.addChangeListener(speedListener);
		
		
	}
	
	public class SliderChangeListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent arg0) 
		{
			String value = String.format("%d", screenDistance.getValue());
			scDis.setText(value);
			
			String value2 = String.format("%d", slitsDistance.getValue());
			slDis.setText(value2);
			
			String value3 = String.format("%d", simulationSpeed.getValue());
			simSpeed.setText(value3);
		}
	}
	

	
	JTextField waveLength;
	JSlider screenDistance;
	JSlider slitsDistance;
	static JSlider simulationSpeed;
	
	JLabel description1;
	JLabel description2;
	JLabel description3;
	JLabel description4;
	JLabel description5;
	JLabel scDis;
	JLabel slDis;
	JLabel simSpeed;
	JComboBox<String> slitsQuantity;
	JLabel label = new JLabel();
	
	GridBagConstraints gbc = new GridBagConstraints();
	
}
//w tej klasie obslugujemy parametry "symulacyjne" np. zmiana dlugosci fali
