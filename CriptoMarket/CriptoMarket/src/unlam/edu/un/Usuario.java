package unlam.edu.un;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public abstract class Usuario {
	
	protected String nombUsuario;
	
	protected Usuario(String nombUsuario) {
		this.nombUsuario = nombUsuario;
	}
	

	public String getNombUsuario() {
        return nombUsuario;
    }
	
	public String getDetalle() {
		return " ";
	}
	
	
	
	
	protected void consultarCripto(Scanner scan ) {
		String simbolo;
		
		scan.nextLine();
		System.out.println("\nIngrese el simbolo de la cripto a consultar: ");
		simbolo=scan.next();
		simbolo=simbolo.toUpperCase();
		
		if(App.criptomonedas.containsKey(simbolo) && App.mercados.containsKey(simbolo)) {										
			System.out.println("\nDatos de la critomoneda: ");
			System.out.println(App.criptomonedas.get(simbolo));

			System.out.println("\nDatos del mercado: ");
			System.out.println(App.mercados.get(simbolo));
		}else {
			System.out.println("La cripto ingresada no existe");
		}
		
	}
	
	
	
	protected void consultarMercado() {
		System.out.println("Estado del mercado actual: ");
		for(String simbolo: App.mercados.keySet()) {
			System.out.println(App.mercados.get(simbolo));
		}
	}
	
	


	@Override
	public int hashCode() {
		return Objects.hash(nombUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombUsuario, other.nombUsuario);
	}
	
	public static void mostrarMapa(Map<String, Usuario> usuarios) {
		for(String u : usuarios.keySet()) {
			System.out.println(usuarios.get(u));
		}
	}

	
	protected abstract void getMenu(Scanner scan);

	
}
