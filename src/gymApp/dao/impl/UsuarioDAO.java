package gymApp.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import gymApp.config.Configuracion;
import gymApp.dao.IUsuarioDAO;
import gymApp.model.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

    // De momento fijos; más adelante irán a application.properties
	private static final String RUTA = Configuracion.get("ruta.usuario");
	private static final String SEPARADOR = Configuracion.get("separador");

    @Override
    public Usuario obtener() {
        File archivo = new File(RUTA);
        if (!archivo.exists()) {
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            if (linea == null) {
                return null;
            }
            String[] partes = linea.split(SEPARADOR);
            double altura = Double.parseDouble(partes[0]);
            double peso = Double.parseDouble(partes[1]);
            return new Usuario(altura, peso);
        } catch (Exception e) {
            System.out.println("Error al leer el usuario: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean guardar(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            bw.write(usuario.getAltura() + SEPARADOR + usuario.getPesoUsuario());
            bw.newLine();
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            return false;
        }
    }
}
