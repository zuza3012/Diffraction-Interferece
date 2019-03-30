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