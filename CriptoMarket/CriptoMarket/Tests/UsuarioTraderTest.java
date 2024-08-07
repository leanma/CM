
import unlam.edu.un.UsuarioTrader;
import unlam.edu.un.App;
import unlam.edu.un.CriptoMoneda;
import unlam.edu.un.Mercado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;


class UsuarioTraderTest {


	   UsuarioTrader usuarioTrader;
	    static final String SIMBOLO_VALIDO = "BTC";
	    static final String NOMBRE_CRIPTO_VALIDA = "BITCOIN";
	    static final String SIMBOLO_INVALIDO = "XYZ";
	    static final String NOMBRE_CRIPTO_INVALIDA = "NORCOIN";
	    static final double PRECIO_BTC = 5.0;
	    static final int CAPACIDAD_BTC = 100;
	    
	    @BeforeEach
	    public void inicializaciones() {
	        usuarioTrader = new UsuarioTrader("pipo", 123450, "Galicia", 1000.0);

	        CriptoMoneda btc = new CriptoMoneda(NOMBRE_CRIPTO_VALIDA,SIMBOLO_VALIDO, PRECIO_BTC);
	        Mercado mercadoBtc = new Mercado(SIMBOLO_VALIDO, CAPACIDAD_BTC, 0.0, 0.0);
	        
	        App.criptomonedas = new HashMap<>();
	        App.criptomonedas.put(SIMBOLO_VALIDO, btc);
	        
	        App.mercados = new HashMap<>();
	        App.mercados.put(SIMBOLO_VALIDO, mercadoBtc);
	    }

	    @Test
	    public void testComprarCriptomonedaConEntradaValida() {
	        String input = "\n"+ SIMBOLO_VALIDO + "\n10\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        
	        usuarioTrader.comprarCriptomoneda(new Scanner(in));
	        
	        assertTrue(usuarioTrader.getMapaCriptomonedas().containsKey(SIMBOLO_VALIDO));
	        assertEquals(10, usuarioTrader.getMapaCriptomonedas().get(SIMBOLO_VALIDO));
	        assertEquals(1000.0 - 10 * PRECIO_BTC, usuarioTrader.getSaldo());
	    }

	    @Test
	    public void testComprarCriptomonedaConSimboloInvalido() {
	        String input = SIMBOLO_INVALIDO + "\n" + SIMBOLO_VALIDO + "\n10\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        
	        usuarioTrader.comprarCriptomoneda(new Scanner(in));
	        
	        assertTrue(usuarioTrader.getMapaCriptomonedas().containsKey(SIMBOLO_VALIDO));
	        assertEquals(10, usuarioTrader.getMapaCriptomonedas().get(SIMBOLO_VALIDO));
	        assertEquals(1000.0 - 10 * PRECIO_BTC, usuarioTrader.getSaldo());
	    }

	 /*
	    @Test
	    public void testComprarCriptomonedaConCantidadInvalida() {
	        String input = "\n"+SIMBOLO_VALIDO + "\n-10\n"+SIMBOLO_VALIDO+"\n10\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        
	        usuarioTrader.comprarCriptomoneda(new Scanner(in));

	        assertTrue(usuarioTrader.getMapaCriptomonedas().containsKey(SIMBOLO_VALIDO));
	        assertEquals(10, usuarioTrader.getMapaCriptomonedas().get(SIMBOLO_VALIDO));
	        assertEquals(1000.0 - 10 * PRECIO_BTC, usuarioTrader.getSaldo(),20);
	    }
	    */
	    

}
