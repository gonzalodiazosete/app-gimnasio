package gymApp.model;

import java.util.ArrayList;

public class Rutina {
	private String dia;
	private ArrayList<Ejercicio> listaEjercicios;
	
	public String getDia() {
	    return dia;
	}
	
	public Rutina (String dia) {
		this.dia = dia;
		this.listaEjercicios = new ArrayList<>();
	}
	
	public void agregarEjercicio(Ejercicio ej) {
		this.listaEjercicios.add(ej);
	}
	
	public int getTotalSeries() {
		int sumaSeries = 0;
		for(Ejercicio ej : listaEjercicios) {
			sumaSeries += ej.getSeries();
			
		}
		return sumaSeries;
	}
	
	public double getTiempoTotal() {
		double tiempoTotal = 0;
		
		for(Ejercicio ej : listaEjercicios) {
			double tiempoEjecucion = ej.getSeries() * 0.75;
			double tiempoDescanso = (ej.getSeries() - 1) * ej.getDescansoMinutos();
			tiempoTotal += tiempoEjecucion + tiempoDescanso;
		}
		
		return tiempoTotal;
	}
	
	public ArrayList<Ejercicio> getListaEjercicios() {
	    return listaEjercicios;
	}
	
	public void mostrarRutina() {
		System.out.println("---Rutina del " + dia + "---");
		if(listaEjercicios.isEmpty()) {
			System.out.println("No hay ejercicios en esta rutina");
		}
		else {
			for(Ejercicio ej : listaEjercicios) {
				System.out.println("- " + ej.getNombre() + ": " + ej.getSeries() + " series x " 
                        + ej.getRepeticiones() + " reps | " + ej.getPeso() + "kg | " 
                        + ej.getDescansoMinutos() + " min descanso");
			}
		}
	}
}
