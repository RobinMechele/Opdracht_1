package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SnakeTest {
	
	Snake testSnake1;
	Snake testSnake2;
	
	@Before
	public void setUp() throws Exception {
		testSnake1 = new Snake(10,10,10);
		testSnake2 = new Snake(42,16,10);
	}

	@Test
	public void testGetX() {
		assertEquals(10,testSnake1.getxCoor());
		assertEquals(42,testSnake2.getxCoor());
	}
	
	@Test
	public void testGetY(){
		assertEquals(10,testSnake1.getyCoor());
		assertEquals(16,testSnake2.getyCoor());
	}

}
