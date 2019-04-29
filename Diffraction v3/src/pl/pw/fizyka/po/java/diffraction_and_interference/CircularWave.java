package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.*;

import pl.pw.fizyka.po.java.diffraction_and_interference.SemiCircle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircularWave extends JPanel implements Runnable
{
	
	private static final long serialVersionUID = 1L;
	
	public CircularWave()
	{
		this.setBackground(bgColor);
		vel=1;
	}
	
	
	public void addSemiCircle(int x, int y, int radius, Color clr,int vel){
		SemiCircle c = new SemiCircle();
		c.setX(x);
		c.setY(y);
		c.setRadius(radius);
		c.setColor(clr);
		c.setVelY(vel);
		SemiCircleList.add(c);	
	}
	
	public void addColumnOfCircles(int x, int y)
	{
		addSemiCircle(x, y, -50, Color.black, Sliders.simulationSpeed.getValue()); //druga
		addSemiCircle(x, y, -100, Color.black, Sliders.simulationSpeed.getValue()); //trzecia
		addSemiCircle(x, y, -150, Color.black, Sliders.simulationSpeed.getValue()); //czwarta
		addSemiCircle(x, y, -200, Color.black, Sliders.simulationSpeed.getValue()); //piata
		addSemiCircle(x, y, -250, Color.black, Sliders.simulationSpeed.getValue()); //piata
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		for (SemiCircle sc : SemiCircleList) 
		{
			sc.paint(g);
		}
	}
	
	public Dimension getPreferredSize() 
	{
		return new Dimension(1000, 1000);
	}
	
	public void run() 
	{
		
		while(true)
		{
			this.setBackground(bgColor);
			for(int i=0;i<SemiCircleList.size();i++)
			{
				SemiCircle c=new SemiCircle();
				c=SemiCircleList.get(i);
				c.setRadius(c.getRadius()+c.getVelY());
				if(c.getRadius()>320) 	//zmieniajac ten warunek, fale beda znikaly pozniej, wiec zeby bylo plynnie trzeba dodac wiecej kolek w kolumnie
				{
					c.setRadius(0);
					
				}
				c.setVelY(vel);
				c.setColor(waveColor);
				repaint();
			
				try
				{
					Thread.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	
	List<SemiCircle> SemiCircleList = new ArrayList<SemiCircle>();
	public static int vel;
	public static Color bgColor = Color.WHITE;
	public static Color waveColor = Color.BLACK;
	
	
}
//w tej klasie bedzie rysowana fala kulista