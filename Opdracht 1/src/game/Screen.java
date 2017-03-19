package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable{
	
	/*
	 * Ik gebruik Runnable, omdat ik niet in staat was om de Scanner of Timer dingen te
	 * gebruiken. Runnable werkt hier wel.
	 */
	
	private static final long serialVersionUID = -3700061653820668797L;
	
	public static final int WIDTH=500;
	public static final int HEIGHT=500; //Variabelen voor hoogte en breedte scherm.
	public Thread thread; //Dit word gebruikt omdat ik ook Runnable gebruik. 
	private boolean running = false; 
	private int xCoor = 10;
	private int yCoor = 10;
	private int size = 1;
	
	//Deze variabelen worden gebruikt bij Key, maar ook bij tick().
	private boolean right = true;
	private boolean left = false;
	private boolean down = false;
	private boolean up = false;
	
	private int ticks = 0; 
	private final int playticks = 750000;
	/*
	 * Door ticks te gebruiken zal hij de code zeer snel overlopen.
	 * Hierbij wordt later ook rekening mee gehouden.
	 * Playticks wordt gebruikt omdat het spel trager zal werken omdat slang groter wordt.
	 * 
	 */
	
	//Het toevoegen van de apart-gemaakte klasses om de code van Screen in te korten.
	private Snake body;
	private Apple apple;
	private ArrayList<Apple> apples;
	private ArrayList<Snake> snake;
	
	//Interne klasse (helemaal onderaan de code)
	//Word gebruikt voor user-input.
	private Key key;
	
	public Screen(){
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		snake = new ArrayList<Snake>();
		apples = new ArrayList<Apple>();
		start();
	}
	
	public void tick(){
		
		/*
		 * Als er nog geen slang is, dan wordt er 1 gemaakt.
		 * Hierbij geldt hetzelfde voor de appel. Als er geen is, dan moet er 1 komen
		 * op het scherm.
		 */
		
		if(snake.size() == 0){
			body = new Snake(xCoor, yCoor, 10);
			snake.add(body);
		}
		
		if(apples.size() == 0){
			int xCoor = (int) (Math.random()*49);
			int yCoor = (int) (Math.random()*49);
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}
		
		for(int i = 0;i<apples.size();i++){
			
			/*
			 * Dit stuk code zal dus de appel verwijderen van het bord als de slang deze opeet.
			 * Hierbij zal de grootte van de slang met 1 vergroten.
			 */
			
			if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()){
				size++;
				apples.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i <snake.size();i++){
			
			/*
			 * Dit stukje code zal ervoor zorgen dat als de slang in zichzelf komt, hij hierbij
			 * dood zal gaan en dus het spel op stop zet.
			 */
			
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()){
				if(i != snake.size() - 1){
					stop();
				}
			}
		}
		
		if(xCoor <0 || xCoor>49 || yCoor <0 || yCoor >49){
			
			/*
			 * Slang raakt de rand van je scherm = slang dood = stop().
			 */
			
			stop();
		}
		
		ticks++;
		if(ticks >playticks){ //controls
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down)yCoor++;
			
			//ticks terug op 0 zetten om je loop te herstarten
			ticks = 0;
			body = new Snake(xCoor, yCoor, 10);
			snake.add(body);
			
			//Zonder dit stukje code zal je slang blijven groeien zonder te stoppen.
			if(snake.size()>size){
				snake.remove(0);
			}
		}
	}
	
	public void paint(Graphics g){
		/*
		 * Hierbij wordt de staart verwijderd van het bord, zodat het lijkt dat de slang
		 * met een zelfde grootte vooruit beweegt.
		 */
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		//Grid layout tekenen
		//Gebruikt om bugs uit de appel en slang movement te halen.
		/*for(int i = 0; i<WIDTH/10;i++){
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		for(int i = 0; i<HEIGHT/10;i++){
			g.drawLine(0, i*10, WIDTH, i*10);
		}*/
		
		//Slang zelf tekenen
		for(int i = 0; i<snake.size();i++){
			snake.get(i).draw(g);
		}
		//Appel tekenen
		for(int i = 0;i<apples.size();i++){
			apples.get(i).draw(g);
		}
	}
	
	public void start(){
		//Wordt in het begin gebruikt om het spel te starten.
		running = true;
		thread = new Thread(this, "Gameloop");
		thread.start();
	}
	
	public void stop(){
		//Als stop wordt uitgevoerd zal het scherm gewoon stilstaan.
		running = false;
	}
	
	public void run(){
		
		/*
		 * De run zal dus hierbij altijd tick() en repaint() uitvoeren, zolang dat er
		 * geen collision is met de omranding of met de slang zelf.
		 */
		
		while(running){
			tick();
			repaint();
		}
	}
	
	private class Key implements KeyListener{
		/*
		 * Dit is een interne klasse, waarbij KeyListener wordt gebruikt.
		 * Deze zal er dan voor zorgen dat de input van je toetsenbord wordt geregistreerd
		 * en zal daardoor de slang bestuurbaar worden.
		 * 
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			/*
			 * Bij snake is het niet de bedoeling om door jezelf terug te keren, dus
			 * hierbij word er niet links gebruikt als je naar rechts wilt.
			 * Door dit te gebruiken voorkom je dat je door jezelf gaat in tegengestelde
			 * richting
			 */
			
			if(key == KeyEvent.VK_RIGHT && !left){
				up=false;
				down= false;
				right = true;
			}
			
			if(key == KeyEvent.VK_LEFT && !right){
				up=false;
				down= false;
				left = true;
			}
			
			if(key == KeyEvent.VK_UP && !down){
				up=true;
				left= false;
				right = false;
			}
			
			if(key == KeyEvent.VK_DOWN && !up){
				down=true;
				left=false;
				right=false;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			/*
			 * Hier moet geen code komen, omdat alleen keyPressed nodig is.
			 */
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			/*
			 * Hier moet geen code komen, omdat alleen keyPressed nodig is.
			 */
		}
	}
}
