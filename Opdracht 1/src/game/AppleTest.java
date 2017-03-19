package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppleTest {

	Apple testApple1;
	Apple testApple2;
	@Before
	public void setUp() throws Exception {
		testApple1 = new Apple(30,30,10);
		testApple2 = new Apple (37,23,10);
	}

	@Test
	public void testGetX() {
		assertEquals(30, testApple1.getxCoor());
		assertEquals(37, testApple2.getxCoor());
	}
	
	@Test
	public void testGetY() {
		assertEquals(30, testApple1.getyCoor());
		assertEquals(23, testApple2.getyCoor());
	}

}
