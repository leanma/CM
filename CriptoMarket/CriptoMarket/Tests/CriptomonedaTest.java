import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unlam.edu.un.CriptoMoneda;
import unlam.edu.un.Mercado;

class CriptomonedaTest {

	CriptoMoneda c1 = new CriptoMoneda("Bitcoin", "BTC", 500);
	@Test
	void testGetNombre() {
	assertEquals("Bitcoin", c1.getNombre());
	}
	
	@Test
	void testGetSimbolo() {
		assertEquals("BTC", c1.getSimbolo());
	}
	@Test
	void testGetPrecio() {
		assertEquals(500, c1.getPrecio());
		}
	@Test
	void getDetalleTest() {
		assertEquals("Bitcoin, BTC, 500.0", c1.getDetalle());
	}
	@Test
	void testSetPrecio() {
		c1.setPrecio(756.2);
		assertEquals(756.2, c1.getPrecio());
		}
	@Test
	void testSetNombre() {
	c1.setNombre("Doge");
	assertEquals("Doge", c1.getNombre());
	}
	
	@Test
	void testSetSimbolo() {
		c1.setSimbolo("DG");
		assertEquals("DG", c1.getSimbolo());
	}
	@Test
	void testequals() {
		CriptoMoneda c2 = new CriptoMoneda("BROTEHER", "BTC", 500);
		CriptoMoneda c3 = c1;
		CriptoMoneda c4 = null;
		Mercado m1 = new Mercado("BTC", 500, 222, 333);
		assertEquals(true, c1.equals(c2));
		assertEquals(true, c1.equals(c3));
		assertEquals(false, c1.equals(m1));
		assertEquals(false, c1.equals(c4));
	}

}
