package gymApp.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import gymApp.dao.IRutinaDAO;
import gymApp.model.Ejercicio;
import gymApp.model.Rutina;

public class RutinaDAO implements IRutinaDAO {

    // De momento fijos; más adelante irán a application.properties
    private static final String RUTA = "rutinas.txt";
    private static final String SEPARADOR = ";";

    @Override
    public boolean guardar(Rutina rutina) {
        Map<String, Rutina> rutinas = cargarTodas();
        rutinas.put(rutina.getDia().toLowerCase(), rutina); // sustituye si ya existía
        return escribirTodas(rutinas);
    }

    @Override
    public Rutina obtenerPorDia(String dia) {
        return cargarTodas().get(dia.toLowerCase());
    }

    @Override
    public List<Rutina> obtenerTodas() {
        return new ArrayList<>(cargarTodas().values());
    }

    @Override
    public boolean eliminar(String dia) {
        Map<String, Rutina> rutinas = cargarTodas();
        if (rutinas.remove(dia.toLowerCase()) == null) {
            return false; // no había rutina ese día
        }
        return escribirTodas(rutinas);
    }

    private Map<String, Rutina> cargarTodas() {
        Map<String, Rutina> rutinas = new LinkedHashMap<>();
        File archivo = new File(RUTA);
        if (!archivo.exists()) {
            return rutinas;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Rutina actual = null;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(SEPARADOR);
                switch (partes[0]) {
                case "Rutina":
                    actual = new Rutina(partes[1]);
                    rutinas.put(partes[1].toLowerCase(), actual);
                    break;
                case "Ejercicio":
                    if (actual != null) {
                        String nombre = partes[1];
                        int series = Integer.parseInt(partes[2]);
                        int repeticiones = Integer.parseInt(partes[3]);
                        double peso = Double.parseDouble(partes[4]);
                        double descanso = Double.parseDouble(partes[5]);
                        actual.agregarEjercicio(new Ejercicio(nombre, series, repeticiones, peso, descanso));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer las rutinas: " + e.getMessage());
        }
        return rutinas;
    }

    private boolean escribirTodas(Map<String, Rutina> rutinas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            for (Rutina rutina : rutinas.values()) {
                bw.write("Rutina" + SEPARADOR + rutina.getDia());
                bw.newLine();
                for (Ejercicio ej : rutina.getListaEjercicios()) {
                    bw.write("Ejercicio" + SEPARADOR + ej.getNombre() + SEPARADOR + ej.getSeries()
                            + SEPARADOR + ej.getRepeticiones() + SEPARADOR + ej.getPeso()
                            + SEPARADOR + ej.getDescansoMinutos());
                    bw.newLine();
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar las rutinas: " + e.getMessage());
            return false;
        }
    }
}
