package unlam.edu.un;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class UsuarioTrader extends Usuario{
	
	private int nroCuentaBancaria;
	private String nombreBanco;
	private double saldo;
	private Map<String, Double> mapaCriptomonedas = new HashMap<String,Double>();
	
	public UsuarioTrader(String nombreUsuario, int nroCuentaBando, String nombreBanco, double saldo) {
		super(nombreUsuario);
		this.nroCuentaBancaria = nroCuentaBando;
		this.nombreBanco = nombreBanco;
		this.saldo = saldo;
	}
	

	public Map<String, Double> getMapaCriptomonedas() {
		return mapaCriptomonedas;
	}


	public void setMapaCriptomonedas(Map<String, Double> mapaCriptomonedas) {
		this.mapaCriptomonedas = mapaCriptomonedas;
	}


	@Override
    public String toString() {
        return "Usuario \t[nombre= "+ super.getNombUsuario() +"   \t| "+ "nroCuentaBancaria= " + this.nroCuentaBancaria  +"   \t| "+ "nombreBanco= " + this.nombreBanco + "  \t| "+ "saldo= " + this.saldo +"\t]";
    }
    
	public String getDetalle() {
        return super.getNombUsuario() + ", " + this.nroCuentaBancaria  +", " + this.nombreBanco + ", " + this.saldo ;
    }
	

	public double getSaldo() {
		return saldo;
	}
	
    
    
    
    
    /////MENU USUARIO TRADER/////
	
	public void getMenu(Scanner scan){
		int opcionElegida;
		
		obtenerHistorico();
		ordenarHistorico();
	
		do {
			
		do {
			System.out.println("\n1) Comprar Criptomonedas");
			System.out.println("2) Vender Criptomonedas");
			System.out.println("3) Consultar Criptomoneda");
			System.out.println("4) Recomendar Criptomoneda");
			System.out.println("5) Consultar estado actual del Mercado");
			System.out.println("6) Visualizar archivo de transacciones (Historico)");
			System.out.println("7) Salir");
			
			System.out.println("Elija una opcion para continuar (1 - 7): ");
	        opcionElegida = scan.nextInt();
	        
	        if (opcionElegida < 1 || opcionElegida > 7) {
	                System.out.println("Opción no válida. Por favor, intente de nuevo.");
	    	        scan.nextLine();
	         }

			
		}while(opcionElegida > 7 || opcionElegida<1);

	    System.out.println("Se eligio la opcion numero: "+ opcionElegida);
        
        switch (opcionElegida-1) {
        	
             case 0:
            	comprarCriptomoneda(scan);
            	ordenarHistorico();
                break;
             case 1:
            	venderCriptomoneda(scan);
                break;
             case 2:
                consultarCripto(scan);
                break;
             case 3:
                recomendarCripto();
                break;
             case 4:
                consultarMercado();
                break;
             case 5:
            	visualizarHistorico();
            	break;
             case 6:
            	System.out.println("Saliendo del menu");
            	guardarHistoricoUsuario();
            	break;
            }
       
        
	}while(opcionElegida!=7);
			
		
	}
	

	
	
	
	
	/////CREAR USUARIO/////
	
	public static Usuario crearUsuario(Scanner scanner, String nombreUsuario) {
        int nroCuentaBancaria = 0;
        String nombreBanco = "";
        double saldo = 0.0;
        boolean entradaValida;

        																																			
        do {
        	if(nombreUsuario.trim().isEmpty()) {
        		System.out.println("Error: El nombre de usuario no puede estar vacío. Por favor, intente de nuevo.");
        		nombreUsuario = scanner.nextLine();
        	}
        		
            entradaValida = !nombreUsuario.trim().isEmpty();
        } while (!entradaValida);
        
        																																			
        do {
            System.out.print("Ingrese el número de cuenta bancaria: ");
            try {
                nroCuentaBancaria = Integer.parseInt(scanner.nextLine());
                entradaValida = nroCuentaBancaria > 0;
                if (!entradaValida) {
                    System.out.println("Error: El número de cuenta bancaria debe ser un número positivo. Por favor, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                entradaValida = false;
                System.out.println("Error: El número de cuenta bancaria debe ser un valor numérico. Por favor, intente de nuevo.");
            }
        } while (!entradaValida);

        																																			
        do {
            System.out.print("Ingrese el nombre del banco: ");
            
            try {
            	
	            nombreBanco = scanner.nextLine();
	            
	        	if (nombreBanco == null || !nombreBanco.matches("[a-zA-Z]+")) {
	                throw new MiExcepcion("El valor ingresado no es alfabético.");
	            }
	  
	            entradaValida = !nombreBanco.trim().isEmpty();
	            if (!entradaValida) {
	                System.out.println("Error: El nombre del banco no puede estar vacío. Por favor, intente de nuevo.");
	            }
	            
            } catch (MiExcepcion e) {
                entradaValida = false;
                System.out.println(e.getMessage());
            }

        } while (!entradaValida);

        																																				
        do {
            System.out.print("Ingrese el saldo: ");
            try {
                saldo = Double.parseDouble(scanner.nextLine());
                entradaValida = saldo >= 0.0;
                if (!entradaValida) {
                    System.out.println("Error: El saldo debe ser un número no negativo. Por favor, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                entradaValida = false;
                System.out.println("Error: El saldo debe ser un valor numérico. Por favor, intente de nuevo.");
            }
        } while (!entradaValida);
        
        Usuario u1= new UsuarioTrader(nombreUsuario, nroCuentaBancaria, nombreBanco, saldo);
        App.usuarios.put(nombreUsuario, u1);
    	System.out.println("Usuario creado con exito");
    	
        return (UsuarioTrader) u1;
        
    }
	
	

	
	
	/////COMPRAR CRIPTOMONEDA/////
	
    public void comprarCriptomoneda(Scanner scanner) {

    	scanner.nextLine();
        System.out.println("Ingrese el símbolo de la criptomoneda que desea comprar:");
        String simbolo = scanner.nextLine();
        simbolo=simbolo.toUpperCase();
	
        																														
        if (!App.criptomonedas.containsKey(simbolo)) {
            System.out.println("La criptomoneda con el símbolo " + simbolo + " no existe.");
            return;
        }
    	
        CriptoMoneda criptoMoneda = App.criptomonedas.get(simbolo);
        Mercado mercado = App.mercados.get(simbolo);
        double precioCripto = criptoMoneda.getPrecio();
        double capacidadMercado = mercado.getCapacidad();
        
        System.out.println("La capacidad del mercado de la criptomoneda: " + simbolo + " es de " + capacidadMercado + " y su precio es de " + precioCripto);

        
        
        System.out.println("Ingrese la cantidad de " + criptoMoneda.getNombre() + " que desea comprar: ");
        String cantidadStr = scanner.nextLine();

        
        double cantidad;
        try {
            cantidad = Double.parseDouble(cantidadStr);
        } catch (NumberFormatException e) {
            System.out.println("Valor ingresado no valido");
            return;
        }

      																																	
        if (cantidad <= 0 || cantidad > capacidadMercado) {
            System.out.println("La cantidad ingresada no es válida o el mercado no posee la cantidad suficiente.");
            return;
        }

        																																
        double costoTotal = precioCripto * cantidad;
        if (this.saldo < costoTotal) {
            System.out.println("No tienes suficiente saldo para comprar la cantidad ingresada de " + criptoMoneda.getNombre() + ".");
            return;
        }

        this.saldo -= costoTotal;

        																																	
        mercado.setCapacidad(capacidadMercado - cantidad);
        mercado.setVariacion7d(mercado.getVariacion7d()*1.05);
        mercado.setVolumen24h(mercado.getVolumen24h()*1.05);
        
        if(cantidad>1000) {
        	criptoMoneda.setPrecio(criptoMoneda.getPrecio()*1.1);
        }
        
        
        
        System.out.println("\nCompra exitosa de " + cantidad + " " + criptoMoneda.getNombre() + ".");
        
        																																	
        if(mapaCriptomonedas.containsKey(simbolo)) {
        	cantidad=mapaCriptomonedas.get(simbolo)+ cantidad;
        }
        
        mapaCriptomonedas.put(simbolo, cantidad);
        
        System.out.println("Saldo restante: " + saldo);
        
        return;
    }



    
    
    
    
    /////VENDER CRIPTOMONEDA/////
    
    public void venderCriptomoneda(Scanner scanner) {
    	
    	if(mapaCriptomonedas.isEmpty()) {
    		System.out.println("\nNo posee criptomonedas para vender.");
    		return;
    	}

        scanner.nextLine();
        System.out.print("Ingrese el símbolo de la criptomoneda que desea vender: ");
        String simbolo = scanner.nextLine();
        simbolo=simbolo.toUpperCase();

        																																	
        if (!App.criptomonedas.containsKey(simbolo)) {
            System.out.println("La criptomoneda con el símbolo " + simbolo + " no existe.");
            return;
        }
        																																
        if (!mapaCriptomonedas.containsKey(simbolo)) {
            System.out.println("\nEl usuario no posee criptomoneda con el símbolo " + simbolo +" para vender.\n");
            return;
        }
    
        CriptoMoneda criptoMoneda = App.criptomonedas.get(simbolo);
        Mercado mercado = App.mercados.get(simbolo);
        
        System.out.println("Se puede vender: " + mapaCriptomonedas.get(simbolo) + " de la criptomononeda " + simbolo);
        
       
        double precioCripto = criptoMoneda.getPrecio();

        System.out.print("Ingrese la cantidad de " + criptoMoneda.getNombre() + " que desea vender: ");
        String cantidadStr = scanner.nextLine();

        																																	
        double cantidad;
        try {
            cantidad= Double.parseDouble(cantidadStr);
        } catch (NumberFormatException e) {
            System.out.println("La cantidad ingresada no es válida.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad ingresada no es válida.");
            return;
        }

        if (!tieneCriptomoneda(simbolo, cantidad)) {
            System.out.println("No tienes suficiente cantidad de " + criptoMoneda.getNombre() + " para vender.");
            return;
        }

        																																		
        double montoTotal = precioCripto * cantidad;
        this.saldo += montoTotal;
        if(mapaCriptomonedas.get(simbolo)==cantidad) {
        	mapaCriptomonedas.remove(simbolo);
        }else {
        	reducirCantidadCriptomoneda(simbolo, cantidad);
        }
        	

        																																		
        mercado.setCapacidad(mercado.getCapacidad() + cantidad);
        mercado.setVariacion7d(mercado.getVariacion7d()*0.93);
        mercado.setVolumen24h(mercado.getVolumen24h()*0.93);

        System.out.println("Venta exitosa de " + cantidad + " " + criptoMoneda.getNombre() + ".");
        System.out.println("Saldo actualizado: " + saldo);
        return;
    }

    
    public boolean tieneCriptomoneda(String simbolo, double cantidad) {
        return mapaCriptomonedas.getOrDefault(simbolo, 0.0) >= cantidad;
    }

    public void reducirCantidadCriptomoneda(String simbolo, double cantidad) {
        double cantidadActual = mapaCriptomonedas.getOrDefault(simbolo, 0.0);
        mapaCriptomonedas.put(simbolo, cantidadActual - cantidad);
    }


    public void agregarCriptomoneda(String simbolo, double cantidad) {
        double cantidadActual = mapaCriptomonedas.getOrDefault(simbolo, 0.0);
        mapaCriptomonedas.put(simbolo, cantidadActual + cantidad);
    }
    
    
    
    
    
    
    
    /////RECOMENDAR CRIPTOMONEDA/////
    
    public void recomendarCripto() {
    	String simboloMayor=null;
    	Double porcentajeMayor=0.0;
    	
    	Double porcentaje;
    	
    	for(String simbolo:App.criptomonedas.keySet()) {
    		porcentaje=(App.mercados.get(simbolo).getCapacidad()/App.criptomonedas.get(simbolo).getPrecio())*100;				
    		if(porcentaje>porcentajeMayor) {
    			simboloMayor=simbolo;
    			porcentajeMayor=porcentaje;
    		}
    		
    	}
    	
    	if(simboloMayor!=null) {
        	System.out.println("\nSe recomienda la " + App.criptomonedas.get(simboloMayor));
        	System.out.println("Tiene un porcentaje de : %" + String.format("%.2f", porcentajeMayor));	
    	}else {
        	System.out.println("no hay criptomonedas");
    	}

    }
    
     
    
    
    
    
    
    
    /////HISTORICO/////
    
    private void visualizarHistorico() {
    	if(mapaCriptomonedas.isEmpty()) {
    		System.out.println("\nEl usuario no posee criptomonedas.\n");
    	}else {
    		mostrarCriptos();
    	}
    	
    }
    
   
    
    public void mostrarCriptos() {
    	System.out.println();
    	if(!mapaCriptomonedas.isEmpty()) {
    		System.out.println("Criptos del usuario: ");
    		for(String u: mapaCriptomonedas.keySet())
    			System.out.println(u + " , " + mapaCriptomonedas.get(u));
    	}
    	System.out.println();
    }
    
    
    public void obtenerHistorico() {
        String simbolo;
        double cant;
    	String nombreArch= this.nombUsuario + "_historico.csv";
    	
    	if(Archivo.archivoExiste(nombreArch)) {
            try (BufferedReader br = new BufferedReader(new FileReader(nombreArch))) {		
                String linea;
                while ((linea = br.readLine()) != null) {														
                    String[] campos = linea.split(",");															
                    simbolo = campos[0].trim();
                    cant = Double.parseDouble(campos[1].trim());															
                    		         																														
                    mapaCriptomonedas.put(simbolo, cant);
                }
            }catch(IOException exc) {																			
            	exc.getMessage();
            }																									
        }
    	
    }
    
    
    public void guardarHistoricoUsuario() {
    	String nombreArch= this.nombUsuario + "_historico.csv";
    	
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArch))) {
            for (String simbolo: mapaCriptomonedas.keySet()) {																	
                writer.println(simbolo + "," + mapaCriptomonedas.get(simbolo));																							
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	
    }
    
    
    
    public void ordenarHistorico() {
    	
    	List<Map.Entry<String, Double>> listaEntradas = new ArrayList<>(mapaCriptomonedas.entrySet()); 
    	listaEntradas.sort(Map.Entry.comparingByValue()); 
    	
    	Map<String, Double> mapaOrdenado = new LinkedHashMap<>(); 
    	for (Map.Entry<String, Double> entrada : listaEntradas) { 
    		mapaOrdenado.put(entrada.getKey(), entrada.getValue()); 
    	} 
    	mapaCriptomonedas.clear();
        mapaCriptomonedas.putAll(mapaOrdenado);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(mapaCriptomonedas, nombreBanco, nroCuentaBancaria, saldo);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioTrader other = (UsuarioTrader) obj;
		return Objects.equals(mapaCriptomonedas, other.mapaCriptomonedas)
				&& Objects.equals(nombreBanco, other.nombreBanco) && nroCuentaBancaria == other.nroCuentaBancaria
				&& Double.doubleToLongBits(saldo) == Double.doubleToLongBits(other.saldo);
	}
	
	
    

}
