package pl.pw.fizyka.po.java.diffraction_and_interference;

import javax.swing.JPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wave extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	public Wave()
	{
		this.setBackground(new Color(208, 241, 247));
	}
	
	public void addRectangle(int x, int y, int width, int height, Color c,int vel){
		Rectangle p = new Rectangle();
		p.setX(x);
		p.setY(y);
		p.setWidth(width);
		p.setHeight(height);
		p.setColor(c);
		p.setVelY(-vel);
		rectangleList.add(p);	
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		for (Rectangle pr : rectangleList) 
		{
			pr.paint(g);
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
			for(int i=0;i<rectangleList.size();i++)
			{
				Rectangle p=new Rectangle();
				p=rectangleList.get(i);
				if((p.getY()+p.getHeight()+p.getVelY())<600+p.getHeight()+1 && (p.getY()+p.getVelY())>0)
				{
					p.setY(p.getY()+p.getVelY());
				}
				else
				{
					p.setY(600);
					//p.setVelY(p.getVelY());
				}
				repaint();
			
				try
				{
					Thread.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				//System.out.println("Prostokat nr " + i + " yPos = " + p.getY());
			}
		}
	}
	
	
	List<Rectangle> rectangleList = new ArrayList<Rectangle>();
}

//w tej klasie jest rysowana fala plaska