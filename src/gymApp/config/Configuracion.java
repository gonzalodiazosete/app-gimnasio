package gymApp.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {

	private static final String RUTA_CONFIG = "config/application.properties";
	private static final Properties propiedades = new Properties();

	static {
		try (FileInputStream fis = new FileInputStream(RUTA_CONFIG)) {
			propiedades.load(fis);
		} catch (IOException e) {
			System.out.println("No se pudo cargar la configuración: " + e.getMessage());
		}
	}

	public static String get(String clave) {
		String valor = propiedades.getProperty(clave);
		if (valor == null) {
			throw new IllegalStateException("Falta la clave '" + clave + "' en " + RUTA_CONFIG);
		}
		return valor;
	}
}
