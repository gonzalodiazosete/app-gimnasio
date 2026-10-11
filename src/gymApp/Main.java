package gymApp;

import java.util.Scanner;

import gymApp.dao.IRutinaDAO;
import gymApp.dao.IUsuarioDAO;
import gymApp.dao.impl.RutinaDAO;
import gymApp.dao.impl.UsuarioDAO;
import gymApp.model.Ejercicio;
import gymApp.model.Rutina;
import gymApp.model.Usuario;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		IUsuarioDAO usuarioDAO = new UsuarioDAO();
		IRutinaDAO rutinaDAO = new RutinaDAO();

		Usuario usuario = usuarioDAO.obtener();

		if(usuario == null) {
			System.out.println("Vamos a crear un usuario");

			System.out.println("Dime tu altura: ");
			double altura = sc.nextDouble();

			System.out.println("Dime tu peso: ");
			double pesoUsuario = sc.nextDouble();

			usuario = new Usuario(altura, pesoUsuario);
			usuarioDAO.guardar(usuario);
		}
		else {
			System.out.println("Bienvenido a la APP GYM");
		}

		int opcion = 0;


		do {
			System.out.println();
			System.out.println("----- Menu Principal -----");
			System.out.println("1 - Crear Rutina y añadir ejercicios");
			System.out.println("2 - Calcular calorias quemadas hoy(pasos + entreno)");
			System.out.println("3 - Ver perfil de usuario");
			System.out.println("4 - Ver rutina de un dia");
			System.out.println("5 - Salir");

			opcion = sc.nextInt();

			switch (opcion) {
			case 1: {
				System.out.println("Has elegido crear rutina.");
				System.out.println("Dime que dia es: ");
				String dia = sc.next();
				Rutina rutina = new Rutina(dia);
				System.out.println("Dime cuantos ejercicios vas a hacer: ");
				int num = sc.nextInt();

				sc.nextLine();

				for(int i = 0; i < num;i++) {

					System.out.println("Nombre ejercicio: ");
					String nombre = sc.nextLine();

					System.out.println("Series: ");
					int series = sc.nextInt();

					System.out.println("Descanso entre series(minutos): ");
					double descanso = sc.nextDouble();

					System.out.println("Repeticiones: ");
					int repeticiones = sc.nextInt();

					System.out.println("Peso: ");
					double peso = sc.nextDouble();

					sc.nextLine();

					Ejercicio ej = new Ejercicio(nombre, series, repeticiones, peso, descanso);
					rutina.agregarEjercicio(ej);
				}

				if(rutinaDAO.guardar(rutina)) {
					System.out.println("Rutina guardada correctamente");
				} else {
					System.out.println("Error al guardar la rutina");
				}
				break;
			}

			case 2:
				System.out.println("Has elegido calcular calorias.");
				System.out.println("Que dia quieres buscar: ");
				String diaBusqueda = sc.next();

				Rutina rutinaEncontrada = rutinaDAO.obtenerPorDia(diaBusqueda);

				if(rutinaEncontrada == null)
					System.out.println("Primero tienes que crear una rutina");
				else {
					System.out.println("Introduce los pasos del dia: ");
					int pasos = sc.nextInt();
					double totalCalorias = usuario.calcularCaloriasTotales(pasos, (int) rutinaEncontrada.getTiempoTotal(), rutinaEncontrada.getTotalSeries());
					System.out.println("Tus calorias quemadas del dia: " + totalCalorias);
				}
				break;

			case 3:
				System.out.println("Tu perfil.");
				System.out.println("Altura: " + usuario.getAltura());
				System.out.println("Peso: " + usuario.getPesoUsuario());
				break;

			case 4:
				System.out.println("De que dia quieres ver la rutina?");
				String diaVer = sc.next();

				Rutina rutinaDiaVer = rutinaDAO.obtenerPorDia(diaVer);

				if(rutinaDiaVer == null) {
					System.out.println("No tienes rutina ese dia " + diaVer);
				}
				else {
					rutinaDiaVer.mostrarRutina();
				}
				break;

			case 5:
				System.out.println("Hasta luego!!!!!!");
				break;

			default:
				System.out.println("Opcion no valida, intenta de nuevo.");
			}

		} while(opcion != 5);

		sc.close();
	}

}