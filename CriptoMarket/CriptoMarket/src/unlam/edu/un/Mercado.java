package unlam.edu.un;

import java.util.Map;
import java.util.Objects;

public class Mercado {
	
    private String simbolo;
    private double capacidad;
    private double volumen24h;
    private double variacion7d;
 
    public Mercado(String simbolo, double capacidad, double volumen24h, double variacion7d) {
        this.simbolo = simbolo;
        this.capacidad = capacidad;
        this.volumen24h = volumen24h;
        this.variacion7d = variacion7d;
    }
 
    public String getSimbolo() {
        return simbolo;
    }
 
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
 
    public double getCapacidad() {
        return capacidad;
    }
 
    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }
 
    public double getVolumen24h() {
        return volumen24h;
    }
 
    public void setVolumen24h(double volumen24h) {
        this.volumen24h = volumen24h;
    }
 
 
    public double getVariacion7d() {
        return variacion7d;
    }
 
    public void setVariacion7d(double variacion7d) {
        this.variacion7d = variacion7d;
    }
 
    @Override
    public String toString() {
        return "Mercado \t[" + "simbolo= " + simbolo  +"   \t|" + 
                " capacidad= " + capacidad + "   \t|" +
                " volumen24h= " + String.format("%.2f", volumen24h) + "   \t|" +
                " variacion7d= " +String.format("%.2f", variacion7d)  +
                "\t]";
	}
    
    public String getDetalle() {
    	return this.simbolo + ", " + this.capacidad + ", " + this.volumen24h + ", " + this.variacion7d;
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
		Mercado other = (Mercado) obj;
		return Objects.equals(simbolo, other.simbolo);
	}
	
	public static void mostrarMapa(Map<String, Mercado> mercados) {
		for(String m : mercados.keySet()) {
			System.out.println(mercados.get(m));
		}
	}

    
}	

