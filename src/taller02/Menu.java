package taller02;
 
import java.util.ArrayList;
 
public class Menu {
 
	public static void mostrarMenuInicial() {
		System.out.println("\n==============================");
		System.out.println("  Bienvenido al Mundo Pokemon  ");
		System.out.println("==============================");
		System.out.println("1) Continuar");
		System.out.println("2) Nueva Partida");
		System.out.println("3) Salir");
		System.out.print("Ingrese Opcion: ");
	}
 
	public static void mostrarMenuJuego(String usuario) {
		System.out.println("\n" + usuario + ", que deseas hacer?\n");
		System.out.println("1) Revisar equipo.");
		System.out.println("2) Salir a capturar.");
		System.out.println("3) Acceso al PC (cambiar Pokemon del equipo).");
		System.out.println("4) Retar un gimnasio.");
		System.out.println("5) Desafio al Alto Mando.");
		System.out.println("6) Curar Pokemon.");
		System.out.println("7) Guardar.");
		System.out.println("8) Guardar y Salir.");
		System.out.print("Ingrese Opcion: ");
	}
 
	public static void mostrarMenuZonas(ArrayList<Habitat> habitats) {
		System.out.println("\nDonde deseas ir a explorar?\n\nZonas disponibles:\n");
		for (int i = 0; i < habitats.size(); i++) {
			System.out.println((i + 1) + ") " + habitats.get(i).getNombre());
		}
		System.out.println((habitats.size() + 1) + ") Volver al menu.");
		System.out.print("Ingrese Zona: ");
	}
 
	public static void mostrarMenuCaptura(String nombrePokemon) {
		System.out.println("\nOh!! Ha aparecido un increible " + nombrePokemon + "!!\n");
		System.out.println("Que deseas hacer?\n");
		System.out.println("1) Capturar");
		System.out.println("2) Huir");
		System.out.print("Ingrese Opcion: ");
	}
	
	public static void mostrarMenuPC(ArrayList<Pokemon> equipo) {
		System.out.println("\nTodos tus Pokemon:");
		for (int i = 0; i < equipo.size(); i++) {
			Pokemon p = equipo.get(i);
			String estado = p.getVivo() ? "Vivo" : "Debilitado";
			String ubicacion = (i < 6) ? "[Equipo]" : "[PC]";
			System.out.println((i + 1) + ") " + p.getNombre() + " | " + p.getTipo()
					+ " | Stats: " + p.getStats() + " | " + estado + " " + ubicacion);
		}
		System.out.println("\n1) Cambiar Pokemon.");
		System.out.println("2) Salir.");
		System.out.print("Ingrese Opcion: ");
	}
 
	public static void mostrarMenuGimnasios(ArrayList<Lider> lideres) {
		System.out.println("\nA cual Lider deseas retar??\n");
		for (int i = 0; i < lideres.size(); i++) {
			String estado = lideres.get(i).getDerrotado() ? "Derrotado" : "Sin derrotar";
			System.out.println((i + 1) + ") " + lideres.get(i).getNombre() + " - Estado: " + estado);
		}
		System.out.println((lideres.size() + 1) + ") Volver al menu.");
		System.out.print("Ingrese Opcion: ");
	}
	public static void mostrarMenuBatalla() {
		System.out.println("\nQue deseas hacer?");
		System.out.println("1) Atacar");
		System.out.println("2) Cambiar de pokemon");
		System.out.println("3) Rendirse");
		System.out.print("Ingrese Opcion: ");
	}
	
	public static void mostrarMenuCambioBatalla(ArrayList<Pokemon> equipo) {
		int limite = Math.min(equipo.size(), 6);
		System.out.println("\nElige tu pokemon:");
		for (int i = 0; i < limite; i++) {
			Pokemon p = equipo.get(i);
			if (p.getVivo()) {
				System.out.println((i + 1) + ") " + p.getNombre() + " | " + p.getTipo() + " | Stats: " + p.getStats());
			} else {
				System.out.println((i + 1) + ") " + p.getNombre() + " [Debilitado]");
			}
		}
		System.out.print("Ingrese numero: ");
	}
}
