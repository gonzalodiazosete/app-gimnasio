package gymApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;

public class GestorFicheros {

	public static boolean guardarAgenda (Usuario usuario) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("rutinas.txt"))) {
			bw.write("Perfil," + usuario.getAltura() + "," + usuario.getPesoUsuario() );
			bw.newLine();
			
			for(Map.Entry<String, Rutina> entrada : usuario.getRutinaDiaMap().entrySet()) {
				String dia = entrada.getKey();
				Rutina rutina = entrada.getValue();
				bw.write("Rutina," + dia);
				bw.newLine();
				for(Ejercicio ej : rutina.getListaEjercicios()) {
					bw.write("Ejercicio," + ej.getNombre() + "," + ej.getSeries() + "," + ej.getRepeticiones() + "," + ej.getPeso() + "," + ej.getDescansoMinutos());
					bw.newLine();
				}
			}
			
			return true;
		} catch (Exception e) {
			System.out.println("Error al escribir el archivo " + e.getMessage());
			return false;
		}
	}
	
	
	public static Usuario cargarAgenda() {
		
		File archivo = new File("rutinas.txt");
		
		if(!archivo.exists()) {
			return null;
		}
		
		Usuario usuario = null;
		Rutina rutinaActual = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			
			while((linea = br.readLine()) !=null) {
				String[] partes = linea.split(",");
				
				switch(partes[0]) {
				
				case "Perfil": {
					double altura = Double.parseDouble(partes[1]);
					double peso = Double.parseDouble(partes[2]);
					usuario = new Usuario(altura, peso);
					break;
				}
				
				case "Rutina": {
					String dia = partes[1];
					rutinaActual = new Rutina(dia);
					usuario.asignarRutina(dia, rutinaActual);
					break;
				}
				
				case "Ejercicio": {
					String nombre = partes[1];
					int series = Integer.parseInt(partes[2]);
					int repeticiones = Integer.parseInt(partes[3]);
					double peso = Double.parseDouble(partes[4]);
					double descansoMinutos = Double.parseDouble(partes[5]);
					Ejercicio ej = new Ejercicio(nombre, series, repeticiones, peso, descansoMinutos);
					rutinaActual.agregarEjercicio(ej);
					break;
				}
				}
			}
			
			return usuario;
		} catch (Exception e) {
			System.out.println("Error al leer el archivo " + e.getMessage());
			return null;
		}
		
	}
	
}
