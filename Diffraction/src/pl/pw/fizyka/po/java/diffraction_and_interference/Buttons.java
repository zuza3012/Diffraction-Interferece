package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import java.awt.*;

public class Buttons extends JPanel 
{
	Buttons()
	{
		bgColor = new JButton("Wybierz kolor tla");
		waveColor = new JButton("Wybierz kolor fali");
		power = new JButton("ON/OFF");
		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		GridBagConstraints gbc_bgColor = new GridBagConstraints();
		gbc_bgColor.insets = new Insets(0, 0, 5, 0);
		gbc_bgColor.gridx = 8;
		gbc_bgColor.gridy = 2;
		add(bgColor, gbc_bgColor);
		
	
		GridBagConstraints gbc_waveColor = new GridBagConstraints();
		gbc_waveColor.insets = new Insets(0, 0, 5, 0);
		gbc_waveColor.gridx = 8;
		gbc_waveColor.gridy = 3;
		add(waveColor, gbc_waveColor);
		
		
		GridBagConstraints gbc_power = new GridBagConstraints();
		gbc_power.fill = GridBagConstraints.VERTICAL;
		gbc_power.gridx = 8;
		gbc_power.gridy = 5;
		add(power, gbc_power);
		
		
		
		this.add(bgColor);
		this.add(waveColor);
		this.add(power);
		
		this.setLayout(new GridLayout(3,1));
		this.setBackground(Color.PINK);
		
	}
	
	JButton bgColor;
	JButton waveColor;
	JButton power;
}
//w tej klasie obslugujemy parametry "programowe" np. zmiana koloru tla
//git check from bash