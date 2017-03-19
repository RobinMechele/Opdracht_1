package game;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	
	public Snake(int xCoor, int yCoor, int size){
		
		//Constructor
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = size;
		height = size;
	}
	
	
	public void draw(Graphics g){
		/*
		 * Deze code dient om het in te korten in Screen.java;
		 * Hierbij zal hij eerst de volledige achtergrond van de grid zwart kleuren,
		 * daarna zal hij groen kleuren, maar kleiner zodat je een zwarte rand krijgt.
		 */
		g.setColor(Color.black);
		g.fillRect(xCoor*width, yCoor*height, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xCoor*width + 1, yCoor*height +1, width-2, height-2);
		
	}

	//Dit zijn getters en setter (generated) om te gebruiken in de Screen klasse.

	public int getxCoor() {
		return xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}
	
}
