package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

public class Sliders extends JPanel
{
	Sliders()
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
		
		simulationSpeed = new JSlider(JSlider.HORIZONTAL,10,100,50);
		simulationSpeed.setMajorTickSpacing(10);
		simulationSpeed.setMinorTickSpacing(5);
		simulationSpeed.setPaintTicks(true);
		simulationSpeed.setPaintLabels(true);
		
		scDis = new JLabel(Integer.toString(screenDistance.getValue()));
		slDis = new JLabel(Integer.toString(slitsDistance.getValue()));
		
		String[] n = {"1","2","3","4","5"};
		slitsQuantity = new JComboBox<>(n);
		
		this.add(waveLength);
		this.add(description3);
		this.add(screenDistance);
		this.add(description1);
		this.add(scDis);
		this.add(slitsDistance);
		this.add(slDis);
		this.add(description2);
		this.add(simulationSpeed);
		this.add(description4);
		this.add(slitsQuantity);
		this.add(description5);
		
		this.setLayout(new GridLayout(12,1));
		this.setBackground(Color.YELLOW);
		
		screenDistance.addChangeListener(new SliderChangeListener());
		slitsDistance.addChangeListener(new SliderChangeListener());
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
		}
	}
	
	JTextField waveLength;
	JSlider screenDistance;
	JSlider slitsDistance;
	JSlider simulationSpeed;
	JLabel description1;
	JLabel description2;
	JLabel description3;
	JLabel description4;
	JLabel description5;
	JLabel scDis;
	JLabel slDis;
	JComboBox<String> slitsQuantity;
	JLabel label = new JLabel();
	
}
//w tej klasie obslugujemy parametry "symulacyjne" np. zmiana dlugosci fali
