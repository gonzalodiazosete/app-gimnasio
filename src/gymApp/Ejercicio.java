package gymApp;

public class Ejercicio {

		private String nombre;
		private int series;
		private int repeticiones;
		private double peso;
		private double descansoMinutos;
		
		public Ejercicio(String nombre, int serie, int repeticiones, double peso, double descansoMinutos) {
			this.nombre = nombre;
			this.series = serie;
			this.repeticiones = repeticiones;
			this.peso = peso;
			this.descansoMinutos = descansoMinutos;
		}
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public int getSeries() {
			return series;
		}
		public void setSeries(int series) {
			this.series = series;
		}
		public int getRepeticiones() {
			return repeticiones;
		}
		public void setRepeticiones(int repeticiones) {
			this.repeticiones = repeticiones;
		}
		public double getPeso() {
			return peso;
		}
		public void setPeso(double peso) {
			this.peso = peso;
		}
		
		public double getDescansoMinutos() {
			return descansoMinutos;
		}
		public void setDescansoMinutos(double descansoMinutos) {
			this.descansoMinutos = descansoMinutos;
		}
		
		@Override
		public String toString() {
		    return nombre + " -> " + series + " series " + repeticiones + " reps " + peso + " kg ";
		}
		
		
	}

