package game;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	
	private int xCoor;
	private int yCoor;
	private int width;
	private int height;
	
	public Apple(int xCoor, int yCoor, int tileSize){
		
		//Constructor
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width=tileSize;
		height=tileSize;
	}
	
	public void draw(Graphics g){
		/*
		 * Deze code dient om het in te korten in Screen.java;
		 * Hierbij zal hij eerst de volledige achtergrond van de grid zwart kleuren,
		 * daarna zal hij rood kleuren, maar kleiner zodat je een zwarte rand krijgt.
		 */
		
		g.setColor(Color.BLACK);
		g.fillRect(xCoor*width, yCoor*height, width, height);
		g.setColor(Color.RED);
		g.fillRect(xCoor*width + 1, yCoor*height +1, width-2, height-2);
	}
	
	
	//Dit zijn getters(generated) om te gebruiken in de Screen klasse.
	public int getxCoor() {
		return xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}
	
}
