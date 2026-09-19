package gymApp;

import java.util.HashMap;

public class Usuario {
	private double altura;
	private double pesoUsuario;
	private HashMap<String, Rutina> rutinaDia = new HashMap<>();
	
	public Usuario(double altura, double pesoUsuario) {
		this.altura = altura;
		this.pesoUsuario = pesoUsuario;
		this.rutinaDia = new HashMap<>();
	}
	
	public double getAltura() {
		return altura;
	}
	
	public void setAltura (double altura) {
		this.altura = altura;
	}
	
	public double getPesoUsuario() {
		return pesoUsuario;
	}
	
	public void setPesoUsuario(double pesoUsuario) {
		this.pesoUsuario = pesoUsuario;
	}
	
	public double calcularCaloriasPasos(int pasos) {
		double caloriasP = pasos * 0.04;
		return caloriasP;
	}
	
	public double calcularCaloriasEntreno(int minutos, int totalSeries) {
		double caloriasE = (minutos * 5) + (totalSeries * 2);
		return caloriasE;
	}
	
	public double calcularCaloriasTotales(int pasos, int minutos, int totalSeries) {
		double caloriasTotales = calcularCaloriasPasos(pasos) + calcularCaloriasEntreno(minutos, totalSeries);
		return caloriasTotales;
	}
	
	public void asignarRutina(String dia, Rutina rutina) {
		rutinaDia.put( dia, rutina);
	}
	
	public Rutina getRutinaDia(String dia) {
		Rutina rutina = rutinaDia.get(dia);
		return rutina;
	}
	
	public HashMap<String, Rutina> getRutinaDiaMap() {
	    return rutinaDia;
	}
}
