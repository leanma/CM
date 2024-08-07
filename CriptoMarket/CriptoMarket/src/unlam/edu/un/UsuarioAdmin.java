package unlam.edu.un;


import java.util.Scanner;


public class UsuarioAdmin extends Usuario {
	private String perfil;
	
	public UsuarioAdmin(String nombUsuario, String perfil) {
		super(nombUsuario);
		this.perfil = perfil;
	}
	
    public String getPerfil() {
        return perfil;
    }
    
    
    @Override
    public String toString() {
        return "Usuario \t[perfil= "+ this.perfil +"\t| "+ "nombre= " + super.getNombUsuario()  + "\t]";
    }
    
    public String getDetalle() {
        return super.getNombUsuario() + ", "  +this.perfil ;
    }
    
    
    
    
    /////MENU USUARIO ADMIN/////
    

	public void getMenu(Scanner scan){
		int opcionElegida;
		
	
		do {
			
		do {
			System.out.println("1) Crear Criptomoneda");
			System.out.println("2) Modificar Criptomoneda");
			System.out.println("3) Eliminar Criptomoneda");
			System.out.println("4) Consultar Criptomoneda");
			System.out.println("5) Consultar estado actual del Mercado");
			System.out.println("6) Salir");
			
			System.out.println("Elija una opcion para continuar (1 - 6): ");
	        opcionElegida = scan.nextInt();
	        if (opcionElegida < 1 || opcionElegida > 6) {
	                System.out.println("Opción no válida. Por favor, intente de nuevo.");
	    	        scan.nextLine();
	         }

			
		}while(opcionElegida > 6 || opcionElegida<1);
       
	    System.out.println("Se eligio la opcion numero: "+ opcionElegida);
        
        switch (opcionElegida-1) {
             case 0:
            	 crearCripto(scan);
                 break;
             case 1:
            	 modificarCripto(scan);
                 break;
             case 2:
            	 eliminarCripto(scan);
            	 break;
             case 3:
                consultarCripto(scan);
                break;
             case 4:
                consultarMercado();
                break;
             case 5:
            	 System.out.println("Saliendo del menu");
            	 break;
            }
        
	}while(opcionElegida!=6);
			
		
	}
	
	
	
    /////CREAR CRIPTOMONEDA/////
	
	public void crearCripto(Scanner scan) {
		String nombCripto;
		String simbolo;
		int op;
		double precio;
		CriptoMoneda c1;
		Mercado m1;
		
		
		System.out.println("Ingrese el simbolo que representa a esa criptomoneda:");
		simbolo = scan.next();
		simbolo = simbolo.toUpperCase();
		scan.nextLine();
		
        if (App.criptomonedas.containsKey(simbolo)) {
            System.out.println("La criptomoneda con el símbolo " + simbolo + " ya existe.");
            System.out.println("Ingrese 1 para modificarla, cualquier otro valor para finalizar operacion.");
            op=scan.nextInt();
            scan.nextLine();
            if(op==1)
            	modificarCripto(scan,simbolo);
        }else {
    		System.out.println("Ingrese el nombre de la criptomoneda que desea agregar:");
    		nombCripto = scan.next();
    		scan.nextLine();

    		do {
        		System.out.println("Ingrese el precio de la criptomoneda en cuestion:");
        		precio = scan.nextDouble();
        		scan.nextLine();
        		if(precio<=0)
        			System.out.println("Precio de la criptomoneda no valido");
    		}while(precio<=0);

    		
    		c1 = new CriptoMoneda(nombCripto, simbolo, precio);
    		m1 = new Mercado(simbolo, 500.00,1.0,1.0);
    		
    		App.criptomonedas.put(simbolo, c1);
    		App.mercados.put(simbolo, m1);
    		System.out.println("La criptomoneda con el símbolo " + simbolo + " fue creada.");
        }

	}
	
	
	public void crearCripto(CriptoMoneda c1,Mercado m1) {
		
        if (App.criptomonedas.containsKey(c1.getSimbolo())) {
            System.out.println("La criptomoneda con el símbolo " + c1.getSimbolo() + " ya existe.");
        }else {
    		
    		App.criptomonedas.put(c1.getSimbolo(), c1);
    		App.mercados.put(m1.getSimbolo(), m1); 		
        }
	}
	
	
	
	
	
	
	
	
	
    /////ELIMINAR CRIPTOMONEDA/////
	
	public void eliminarCripto(Scanner scan) {
		String simbolo;
		
		System.out.println("Ingrese el simbolo que representa a esa criptomoneda:");
		simbolo = scan.next();
		simbolo = simbolo.toUpperCase();
		scan.nextLine();
		
        if (!App.criptomonedas.containsKey(simbolo)) {
            System.out.println("La criptomoneda con el símbolo " + simbolo + " no existe.");
        }else {
    		App.criptomonedas.remove(simbolo);
    		App.mercados.remove(simbolo);
    		System.out.println("La criptomoneda con el símbolo " + simbolo + " fue eliminada.");
        }
        
		
	}
	
	
	
	public void eliminarCripto(String simbolo) {
		
        if (!App.criptomonedas.containsKey(simbolo)) {
            System.out.println("La criptomoneda con el símbolo " + simbolo + " no existe.");
        }else {
    		App.criptomonedas.remove(simbolo);
    		App.mercados.remove(simbolo);
        }	
	}
	
	
	
	
	
	
	
	
	
    /////MODIFICAR CRIPTOMONEDA/////
	
	public void modificarCripto(Scanner scan) {
		int opcionElegida;
		String simbolo;
		String nombCripto;
		Double precio;
		CriptoMoneda c1;
		Mercado m1;
		
		
		System.out.println("Ingrese el simbolo que representa a esa criptomoneda:");
		simbolo = scan.next();
		simbolo = simbolo.toUpperCase();
		scan.nextLine();
		
        if (!App.criptomonedas.containsKey(simbolo)) {
            System.out.println("La criptomoneda con el símbolo " + simbolo + " no existe.");
        }else {
        	
        	System.out.println(App.criptomonedas.get(simbolo));
        	
        	System.out.println("Ingrese la opcion de modificacion:");
        	
        	do {
    			
        		do {
        			System.out.println("1) Modificar Nombre");
        			System.out.println("2) Modificar Simbolo");
        			System.out.println("3) Modificar Precio");
        			System.out.println("4) Salir");
        			
        			System.out.println("Elija una opcion para continuar (1 - 4): ");
        	        opcionElegida = scan.nextInt();
        	        if (opcionElegida < 1 || opcionElegida > 4) {
        	                System.out.println("Opción no válida. Por favor, intente de nuevo.");
        	    	        scan.nextLine();
        	         }

        			
        		}while(opcionElegida > 4 || opcionElegida<1);
               
        	    System.out.println("Se eligio la opcion numero: "+ opcionElegida);
                
                switch (opcionElegida-1) {
                     case 0:
                 			c1=App.criptomonedas.get(simbolo);
                 			m1=App.mercados.get(simbolo);
                 			eliminarCripto(simbolo);
                 			
                 			System.out.println("Ingrese el nuevo nombre: ");
                 			nombCripto = scan.next();
                    		scan.nextLine();
                    		
                    		c1.setNombre(nombCripto);
                    		crearCripto(c1,m1);
                 			break;
                     case 1:
                    	 	c1=App.criptomonedas.get(simbolo);
                    	 	m1=App.mercados.get(simbolo);
                    	 	eliminarCripto(simbolo);
              			
                    	 	do {
                    	 		System.out.println("Ingrese el nuevo simbolo: ");
                        	 	simbolo = scan.next();
                        	 	scan.nextLine();
                    	 		
                        	 	if(App.criptomonedas.containsKey(simbolo))
                        	 		System.out.println("Ya existe una criptomoneda con ese simbolo.");
                    	 	}while(App.criptomonedas.containsKey(simbolo));

                    	 	c1.setSimbolo(simbolo);
                    	 	m1.setSimbolo(simbolo);
                    	 	crearCripto(c1,m1);
                    	 	break;
                     case 2:
                 	 		c1=App.criptomonedas.get(simbolo);
                 	 		m1=App.mercados.get(simbolo);
                 	 		eliminarCripto(simbolo);
           			
                 	 	do {
                 	 		System.out.println("Ingrese el nuevo precio: ");
                     	 	precio = scan.nextDouble();
                     	 	scan.nextLine();
                 	 		
                     	 	if(precio<=0)
                     	 		System.out.println("Precio no valido.");
                 	 	}while(precio<=0);

                 	 	c1.setPrecio(precio);
                 	 	crearCripto(c1,m1);
                 	 	break;
                     case 3:
                    	 System.out.println("Saliendo del menu");
                        break;
                    }
                
        	}while(opcionElegida!=4);

        }
		
	}
	
	
	
	public void modificarCripto(Scanner scan,String simbolo) {
		int opcionElegida;
		String nombCripto;
		Double precio;
		CriptoMoneda c1;
		Mercado m1;
		
        	
        System.out.println(App.criptomonedas.get(simbolo));
        	
        System.out.println("Ingrese la opcion de modificacion:");
        	
        	do {
    			
        		do {
        			System.out.println("\n1) Modificar Nombre");
        			System.out.println("2) Modificar Simbolo");
        			System.out.println("3) Modificar Precio");
        			System.out.println("4) Salir");
        			
        			System.out.println("Elija una opcion para continuar (1 - 4): ");
        	        opcionElegida = scan.nextInt();
        	        if (opcionElegida < 1 || opcionElegida > 4) {
        	                System.out.println("Opción no válida. Por favor, intente de nuevo.");
        	    	        scan.nextLine();
        	         }

        			
        		}while(opcionElegida > 4 || opcionElegida<1);
               
        	    System.out.println("Se eligio la opcion numero: "+ opcionElegida);
                
                switch (opcionElegida-1) {
                     case 0:
                 			c1=App.criptomonedas.get(simbolo);
                 			m1=App.mercados.get(simbolo);
                 			eliminarCripto(simbolo);
                 			
                 			System.out.println("Ingrese el nuevo nombre: ");
                 			nombCripto = scan.next();
                    		scan.nextLine();
                    		
                    		c1.setNombre(nombCripto);
                    		crearCripto(c1,m1);
                 			break;
                     case 1:
                    	 	c1=App.criptomonedas.get(simbolo);
                    	 	m1=App.mercados.get(simbolo);
                    	 	eliminarCripto(simbolo);
              			
                    	 	do {
                    	 		System.out.println("Ingrese el nuevo simbolo: ");
                        	 	simbolo = scan.next();
                        	 	scan.nextLine();
                    	 		
                        	 	if(App.criptomonedas.containsKey(simbolo))
                        	 		System.out.println("Ya existe una criptomoneda con ese simbolo.");
                    	 	}while(App.criptomonedas.containsKey(simbolo));

                    	 	c1.setSimbolo(simbolo);
                    	 	m1.setSimbolo(simbolo);
                    	 	crearCripto(c1,m1);
                    	 	break;
                     case 2:
                 	 		c1=App.criptomonedas.get(simbolo);
                 	 		m1=App.mercados.get(simbolo);
                 	 		eliminarCripto(simbolo);
           			
                 	 	do {
                 	 		System.out.println("Ingrese el nuevo precio: ");
                     	 	precio = scan.nextDouble();
                     	 	scan.nextLine();
                 	 		
                     	 	if(precio<=0)
                     	 		System.out.println("Precio no valido.");
                 	 	}while(precio<=0);

                 	 	c1.setPrecio(precio);
                 	 	crearCripto(c1,m1);
                 	 	break;
                     case 3:
                    	 System.out.println("Saliendo del menu");
                        break;
                    }
                
        	}while(opcionElegida!=4);

        }
		
	
	

	

}
