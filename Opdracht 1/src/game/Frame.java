package game;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class Frame extends JFrame {
	private static final long serialVersionUID = 6514260112096607082L;

	public Frame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snek The Game");
		setResizable(false);
		setLayout(new GridLayout(1,1,0,0));
		Screen s = new Screen();
		add(s);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new Frame();
	}
}
