import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unlam.edu.un.Mercado;

class MercadoTest {
	
	Mercado m1 = new Mercado("BTC", 500 , 25.70, 23.5);

	@Test
	void testGetSimbolo() {
		assertEquals("BTC", m1.getSimbolo());
	}
	
	@Test
	void testGetCapacidad() {
		assertEquals(500, m1.getCapacidad());
	}
	
	@Test
	void testGetVolumen24H() {
		assertEquals(25.70, m1.getVolumen24h());
	}
	
	@Test
	void testGetVariacion7D() {
		assertEquals(23.5, m1.getVariacion7d());
	}
	
	@Test
	void testSetSimbolo() {
		m1.setSimbolo("DG");
		assertEquals("DG", m1.getSimbolo());
	}
	
	@Test
	void testSetCapacidad() {
		m1.setCapacidad(700);
		assertEquals(700, m1.getCapacidad());
	}
	
	@Test
	void testSetVolumen24H() {
		m1.setVolumen24h(22.30);
		assertEquals(22.30, m1.getVolumen24h());
	}
	
	@Test
	void testSetVariacion7D() {
		m1.setVariacion7d(23.7);
		assertEquals(23.7, m1.getVariacion7d());
	}
	

}
