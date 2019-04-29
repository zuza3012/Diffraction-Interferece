package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buttons extends JPanel 
{
	private static final long serialVersionUID = 1L;
	Buttons()
	{
		bgColor = new JButton("Wybierz kolor tla");
		bgColor.addActionListener(bgColorListener);
		waveColor = new JButton("Wybierz kolor fali");
		waveColor.addActionListener(waveColorListener);
		power = new JButton("ON/OFF");
		
		this.add(bgColor);
		this.add(waveColor);
		this.add(power);
		
		this.setLayout(new GridLayout(3,1));
		this.setBackground(Color.PINK);
		
	}
	
	ActionListener waveColorListener = new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{ 
			Color wc;
			wc = JColorChooser.showDialog(null, "Choose color", CircularWave.waveColor);
			CircularWave.waveColor = wc;
			Wave.waveColor = wc;				
		}
	};
	
	ActionListener bgColorListener = new ActionListener() 
	{
		public void actionPerformed(ActionEvent e) 
		{ 
			Color bgc = JColorChooser.showDialog(null, "Choose color", CircularWave.bgColor);;
			CircularWave.bgColor = bgc;
			Wave.bgColor = bgc;
            repaint();					
		}
	};
	
	
	JButton bgColor;
	JButton waveColor;
	JButton power;
}
//w tej klasie obslugujemy parametry "programowe" np. zmiana koloru tla
//git check from bash