package unlam.edu.un;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class App {
																
	public static Map<String,CriptoMoneda> criptomonedas = new HashMap<>();								
	public static Map<String,Mercado> mercados = new HashMap<>();
	public static Map<String,Usuario> usuarios = new HashMap<>();
	
	
	public static void main(String[] args) {																										

	    Scanner scan = new Scanner(System.in);
	    String nombreUsuario;
	    Usuario u1 = null;
	    boolean entradaValida = true;
	    
	    																									
		Archivo.crearArchivoCriptomonedas("criptomonedas.csv");											
		Archivo.crearArchivoMercados("mercados.csv");
        Archivo.crearArchivoUsuarios("usuarios.csv");
		
		
        try {
        	
        	criptomonedas = Archivo.cargarArchivoCriptomonedas("criptomonedas.csv");					
        	mercados= Archivo.cargarArchivoMercados("mercados.csv");
            usuarios=Archivo.cargarArchivoUsuarios("usuarios.csv");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        
        
        System.out.println("Ingrese el nombre de usuario para inicar el sistema:");
        
        do {
        
	        nombreUsuario = scan.next();	  															  
	        scan.nextLine();																			
	        
	        try {	
	        	
	        	if (nombreUsuario == null || !nombreUsuario.matches("[a-zA-Z]+")) {							
	                throw new MiExcepcion("El valor ingresado no es alfabético.");
	            }
	        	
		        if(usuarios.containsKey(nombreUsuario)) {												
		        	u1 = usuarios.get(nombreUsuario);
		        }else {
		        	System.out.println("Usuario no regisrado en el sistema, pasando a crear usuario.");
		        	u1 = UsuarioTrader.crearUsuario(scan, nombreUsuario);  								
		        }
		        entradaValida = true;
		        
	        } catch (MiExcepcion e) {
                System.out.println(e.getMessage());
            }
	        
        } while (!entradaValida);
        
        
        System.out.println(u1);																			
        u1.getMenu(scan);																				
        
      
        Archivo.guardarArchivoCriptomonedas(criptomonedas, "criptomonedas.csv");					
        Archivo.guardarArchivoMercados(mercados, "mercados.csv");
        Archivo.guardarArchivoUsuarios(usuarios, "usuarios.csv");
        
        System.out.println("Se guardo el archivo de criptomonedas, se procede a salir del sistema");

	}
}


