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
		description3 = new JLabel("Dlugość fali [nm]");
		waveText = new JTextField();
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
		simSpeed = new JLabel(Integer.toString(simulationSpeed.getValue()));
		
		String[] n = {"1","2","3","4","5"};
		slitsQuantity = new JComboBox<>(n);
		
		
		 
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		
		GridBagConstraints gbc_description3 = new GridBagConstraints();
		gbc_description3.insets = new Insets(0, 0, 5, 5);
		gbc_description3.gridx = 4;
		gbc_description3.gridy = 1;
		add(description3, gbc_description3);
		
		
		waveText.setText("Podaj dlugość fali");
		waveText.setText("Podaj dlugość fali");
		GridBagConstraints gbc_waveText = new GridBagConstraints();
		gbc_waveText.insets = new Insets(0, 0, 5, 5);
		gbc_waveText.fill = GridBagConstraints.HORIZONTAL;
		gbc_waveText.gridx = 4;
		gbc_waveText.gridy = 2;
		add(waveText, gbc_waveText);
		waveText.setColumns(10);
		
		
		GridBagConstraints gbc_screenDistance = new GridBagConstraints();
		gbc_screenDistance.insets = new Insets(0, 0, 5, 5);
		gbc_screenDistance.gridx = 4;
		gbc_screenDistance.gridy = 3;
		add(screenDistance, gbc_screenDistance);
		
		
		GridBagConstraints gbc_scDis = new GridBagConstraints();
		gbc_scDis.insets = new Insets(0, 0, 5, 5);
		gbc_scDis.gridx = 5;
		gbc_scDis.gridy = 3;
		add(scDis, gbc_scDis);
		
	
		GridBagConstraints gbc_description1 = new GridBagConstraints();
		gbc_description1.insets = new Insets(0, 0, 5, 5);
		gbc_description1.gridx = 4;
		gbc_description1.gridy = 4;
		add(description1, gbc_description1);
		
		
		GridBagConstraints gbc_slitsDistance = new GridBagConstraints();
		gbc_slitsDistance.insets = new Insets(0, 0, 5, 5);
		gbc_slitsDistance.gridx = 4;
		gbc_slitsDistance.gridy = 5;
		add(slitsDistance, gbc_slitsDistance);
		
		
		GridBagConstraints gbc_description2 = new GridBagConstraints();
		gbc_description2.gridwidth = 7;
		gbc_description2.insets = new Insets(0, 0, 5, 5);
		gbc_description2.gridx = 3;
		gbc_description2.gridy = 6;
		add(description2, gbc_description2);
		
		
		GridBagConstraints gbc_slDis = new GridBagConstraints();
		gbc_slDis.insets = new Insets(0, 0, 5, 5);
		gbc_slDis.gridx = 5;
		gbc_slDis.gridy = 5;
		add(slDis, gbc_slDis);
		
	
		GridBagConstraints gbc_simulationSpeed = new GridBagConstraints();
		//GridBagConstraints gbc_slider_2 = new GridBagConstraints();
		gbc_simulationSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_simulationSpeed.gridx = 4;
		gbc_simulationSpeed.gridy = 7;
		add(simulationSpeed, gbc_simulationSpeed);
		
		
		GridBagConstraints gbc_description4 = new GridBagConstraints();
		gbc_description4.insets = new Insets(0, 0, 5, 5);
		gbc_description4.gridx = 4;
		gbc_description4.gridy = 8;
		add(description4, gbc_description4);
		
		
		GridBagConstraints gbc_slitsQuantity = new GridBagConstraints();
		gbc_slitsQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_slitsQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_slitsQuantity.gridx = 4;
		gbc_slitsQuantity.gridy = 9;
		add(slitsQuantity, gbc_slitsQuantity);

		 
		
		
		
		
		
		this.setBackground(Color.YELLOW);
		
		screenDistance.addChangeListener(new SliderChangeListener());
		slitsDistance.addChangeListener(new SliderChangeListener());
		simulationSpeed.addChangeListener(new SliderChangeListener());
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
	
	private JTextField waveText;
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
	JLabel simSpeed;
	JComboBox<String> slitsQuantity;
	JLabel label = new JLabel();
	
	//GridBagConstraints gbc = new GridBagConstraints();
	
}
//w tej klasie obslugujemy parametry "symulacyjne" np. zmiana dlugosci fali
