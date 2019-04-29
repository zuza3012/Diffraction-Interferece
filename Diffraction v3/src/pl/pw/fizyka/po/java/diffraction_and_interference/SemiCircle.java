package pl.pw.fizyka.po.java.diffraction_and_interference;


import java.awt.Color;
import java.awt.Graphics;

public class SemiCircle
{
	//default parameters
    private int xPos = 0;
	private int yPos = 0;
    private int radius =1;
    private Color color = Color.BLACK;
    private int yVel = 1;
    
    public int getX() {
		return xPos;
	}

	public void setX(int xPos) {
		this.xPos = xPos;
	}

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getRadius(){
        return radius;
    } 


	public void setRadius(int radius) {
		this.radius = radius;
	}


    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setVelY(int VelY)
	{
		this.yVel = VelY;
	}
	
	public int getVelY()
	{
		return this.yVel;
	}
		
	public void paint(Graphics g){
        g.setColor(getColor());
        g.drawOval(getX()-getRadius(), getY()-getRadius(), getRadius()*2, getRadius()*2);
    }
	

}

//mo¿na by to zrobiæ dziedzicz¹c z klasy Figura i tak samo Rectangle
