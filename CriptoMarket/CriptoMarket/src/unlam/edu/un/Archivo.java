package unlam.edu.un;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


class Archivo {
	
	//////CARGA DE ARCHIVOS/////

    public static Map<String,CriptoMoneda> cargarArchivoCriptomonedas(String archivoCriptomonedas) throws IOException {
        Map<String,CriptoMoneda> criptomonedas = new HashMap<>();
        String nombre;
        String simbolo;
        double precio;
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCriptomonedas))) {				
            String linea;
            while ((linea = br.readLine()) != null) {													
                String[] campos = linea.split(",");															
                nombre = campos[0].trim();
                simbolo = campos[1].trim();
                precio = Double.parseDouble(campos[2].trim());											
                CriptoMoneda criptomoneda = new CriptoMoneda(nombre,simbolo, precio);						
                		         																														
                criptomonedas.put(simbolo,criptomoneda);													
            }
            
        }catch(IOException exc) {																			
        	exc.getMessage();
        }																									
        return criptomonedas;																				
    }

    
    public static Map<String,Mercado> cargarArchivoMercados(String archivoMercados) throws IOException {
        Map<String,Mercado> mercados = new HashMap<>();
        String simbolo;
        double capacidad;
        double volumen24h;
        double variacion7d;
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoMercados))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                simbolo = campos[0].trim();
                capacidad = Double.parseDouble(campos[1].trim());
                volumen24h = Double.parseDouble(campos[2].trim().replace("%", "").replace(",", "."));		
                variacion7d = Double.parseDouble(campos[3].trim().replace("%", "").replace(",", "."));
                Mercado mercado = new Mercado(simbolo, capacidad, volumen24h, variacion7d);
                
                mercados.put(simbolo,mercado);
            }
            
        }catch(IOException exc) {
        	exc.getMessage();
        }
        return mercados;
    }
    
    
    
    public static Map<String,Usuario> cargarArchivoUsuarios(String archivoUsuarios) throws IOException {
        Map<String,Usuario> usuarios = new HashMap<>();
        String nombreUsuario;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length == 2) {  																					
                    nombreUsuario = campos[0].trim();
                    String perfil = campos[1].trim();
                    UsuarioAdmin administrador = new UsuarioAdmin(nombreUsuario, perfil);
                    
                    usuarios.put(nombreUsuario,administrador);
                } else if (campos.length == 4) {  																		
                    nombreUsuario = campos[0].trim();
                    int nroCuentaBancaria = Integer.parseInt(campos[1].trim());
                    String nombreBanco = campos[2].trim();
                    double saldo = Double.parseDouble(campos[3].trim());
                    UsuarioTrader trader = new UsuarioTrader(nombreUsuario, nroCuentaBancaria, nombreBanco, saldo);
                    
                    usuarios.put(nombreUsuario,trader);
                }
            }
            
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return usuarios;
    }

    
    //////CREACION DE ARCHIVOS GENERICOS/////
    
    
    public static void crearArchivoCriptomonedas(String nombreArchivo) {
        String contenido = "Bitcoin,BTC,1245.00\n" +
                "Ethereum,ETH,1028.00\n" +
                "Tether,USDt,724.50\n" +
                "Cardano,ADA,1.21\n" +
                "Solana,SOL,35.50\n" +
                "Polkadot,DOT,19.45\n" +
                "Dogecoin,DOGE,0.31\n" +
                "Chainlink,LINK,24.50\n" +
                "Litecoin,LTC,180.30\n" +
                "Uniswap,UNI,25.10\n";

        if(!Archivo.archivoExiste(nombreArchivo)) {																			
        	try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {										
        		writer.write(contenido);																				
        	}catch (IOException e) {
        		e.printStackTrace();
        	}
        }
    }
    
    public static void crearArchivoMercados(String nombreArchivo) {
        String contenido = "BTC,10.00,34.66%,+13.84%\n" +
                "ETH,450.00,27.91%,+2.59%\n" +
                "USDt,60.00,5.47%,+0.01%\n" +
                "ADA,10.00,8.29%,+5.25%\n" +
                "SOL,3500.00,18.75%,+6.23%\n" +
                "DOT,20.00,10.30%,+7.12%\n" +
                "DOGE,0.00,3.50%,+4.50%\n" +
                "LINK,25.00,12.45%,+8.75%\n" +
                "LTC,185.00,15.80%,+9.12%\n" +
                "UNI,26.00,7.00%,+3.50%\n";
        
        if(!Archivo.archivoExiste(nombreArchivo)) {
        	try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
        		writer.write(contenido);
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }
    }
    
    
    public static void crearArchivoUsuarios(String nombreArchivo) {
        String contenido = "jperez, administrador\n" +
                "hrizo24, 7824621, Banco Río, 78000.00\n" +
                "mlopez, 5821621, Banco Francés, 800000.00\n" +
                "rlopez, administrador";
      
        if(!Archivo.archivoExiste(nombreArchivo)) {
        	try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
        		writer.write(contenido);
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }
    }
    
    
    
    //////GUARDADO DE ARCHIVOS/////
    
    
    
    public static void guardarArchivoCriptomonedas(Map<String, CriptoMoneda> criptomonedas, String nombreArchivo) {
    	
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {																
            for (String c : criptomonedas.keySet()) {																								
                writer.println(criptomonedas.get(c).getDetalle());																				
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void guardarArchivoMercados(Map<String, Mercado> mercados,String nombreArchivo) {
    	
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
        	for(String m : mercados.keySet()) {
                writer.println(mercados.get(m).getDetalle());
            } 
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void guardarArchivoUsuarios(Map<String,Usuario> usuarios,String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
        	for(String u : usuarios.keySet()) {
        		writer.println(usuarios.get(u).getDetalle());
                }
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 
    
    
    
    //////VERIFICACION DE EXISTENCIA DE ARCHIVO/////
    
    public static boolean archivoExiste(String rutaArchivo) {
        boolean existeArchivo = false;
        try {
            File archivo = new File(rutaArchivo);
            if (archivo.exists() && archivo.isFile()) {
                existeArchivo = true;
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error al verificar el archivo: " + e.getMessage());
        }
        return existeArchivo;
    }
    
  
    
    
}
