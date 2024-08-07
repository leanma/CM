package unlam.edu.un;

import java.util.Map;
import java.util.Objects;

public class CriptoMoneda {
	private String nombre;
	private String simbolo;
	private double precio;

	public CriptoMoneda(String nombre, String simbolo, double precio) {
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Criptomoneda \t[nombre= " + nombre +"    \t| "+ "simbolo= " + simbolo +"   \t| "+ " precio= " + precio + "   \t]";
	}
	
    public String getDetalle() {
    	return this.nombre + ", " + this.simbolo + ", " + this.precio;
    }
	

	@Override
	public int hashCode() {
		return Objects.hash(simbolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CriptoMoneda other = (CriptoMoneda) obj;
		return Objects.equals(simbolo, other.simbolo);
	}
	
	public static void mostrarMapa(Map<String, CriptoMoneda> criptomonedas) {
		for(String c : criptomonedas.keySet()) {
			System.out.println(criptomonedas.get(c));
		}
	}
}
