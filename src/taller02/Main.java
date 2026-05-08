package taller02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static Scanner teclado = new Scanner(System.in);
	public static String usuario;
	public static ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> equipo = new ArrayList<Pokemon>();
	public static ArrayList<String> medallas = new ArrayList<String>();
	public static ArrayList<AltoMando> altoMando = new ArrayList<AltoMando>();
	public static ArrayList<Lider> lideres = new ArrayList<Lider>();
	public static int pkRival = 0;
	public static int pkJugador = 0;

	public static void main(String[] args) {
		// Martin Alvarado Lafferte 22.330.833-3 ICCI

		cargarPokedex();
		cargarLideres();
		cargarAltoMando();
		leerArchivo();

		String op = "";

		do {
			Menu.mostrarMenuInicial();
			op = teclado.nextLine().trim();

			switch (op) {
			case "1":
				continuar();
				break;
			case "2":
				nuevaPartida();
				System.out.println("¡Bienvenido " + usuario + "!!");
				menuJuego();
				break;
			case "3":
				System.out.println("Nos vemos entrenador...");
				break;
			default:
				System.out.println("Opcion invalida");
				break;
			}
		} while (!op.equals("3"));
	}

	public static void continuar() {
		if (usuario == null) {
			System.out.println("No hay ninguna partida guardada.");
		} else {
			System.out.println("¡Bienvenido " + usuario + "!!");
			menuJuego();
		}
	}

	public static void nuevaPartida() {
		equipo.clear();
		medallas.clear();

		for (int k = 0; k < lideres.size(); k++) {
			lideres.get(k).setDerrotado(false);
		}

		System.out.print("Ingrese Apodo: ");
		usuario = teclado.nextLine().trim();

		try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Registros.txt"))) {
			escritor.write(usuario + ";none");
		} catch (IOException e) {
			System.out.println("Error al crear el archivo de partida.");
		}
	}

	public static void menuJuego() {
		String op = "";

		do {
			Menu.mostrarMenuJuego(usuario);
			op = teclado.nextLine().trim();

			switch (op) {
			case "1":
				revisarEquipo();
				break;
			case "2":
				capturarPokemon();
				break;
			case "3":
				accesoPC();
				break;
			case "4":
				retarGimnasio();
				break;
			case "5":
				retarAltoMando();
				break;
			case "6":
				curarPokemon();
				break;
			case "7":
				guardar();
				System.out.println("Progreso guardado!");
				break;
			case "8":
				guardar();
				System.out.println("Nos vemos entrenador...");
				break;
			default:
				System.out.println("Opcion invalida");
				break;
			}
		} while (!op.equals("8"));
	}

	public static void leerArchivo() {
		File archivo = new File("Registros.txt");
		Scanner lector;
		try {
			lector = new Scanner(archivo);

			if (!lector.hasNextLine()) {
				usuario = null;
				lector.close();
				return;
			}

			String linea = lector.nextLine().trim();
			String[] datos = linea.split(";");
			usuario = datos[0];

			if (datos.length > 1 && !datos[1].equals("none")) {
				for (int i = 1; i < datos.length; i++) {
					medallas.add(datos[i]);
					for (int j = 0; j < lideres.size(); j++) {
						if (lideres.get(j).getNombre().equals(datos[i])) {
							lideres.get(j).setDerrotado(true);
							break;
						}
					}
				}
			}

			while (lector.hasNextLine()) {
				linea = lector.nextLine().trim();
				if (linea.isEmpty()) continue;
				String[] datosPk = linea.split(";");
				Pokemon aux = buscarPokemon(datosPk[0]);
				if (aux != null) {
					equipo.add(aux);
					if (datosPk.length > 1 && datosPk[1].equals("Debilitado")) {
						aux.setVivo(false);
					} else {
						aux.setVivo(true);
					}
				}
			}
			lector.close();

		} catch (FileNotFoundException e) {
			usuario = null;
		}
	}

	public static void guardar() {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter("Registros.txt"))) {
			escritor.write(usuario);
			if (medallas.isEmpty()) {
				escritor.write(";none");
			} else {
				for (int k = 0; k < medallas.size(); k++) {
					escritor.write(";" + medallas.get(k));
				}
			}

			for (int k = 0; k < equipo.size(); k++) {
				Pokemon pk = equipo.get(k);
				escritor.newLine();
				String estado = pk.getVivo() ? "Vivo" : "Debilitado";
				escritor.write(pk.getNombre() + ";" + estado);
			}

		} catch (IOException e) {
			System.out.println("Error al guardar la partida.");
		}
	}

	public static void revisarEquipo() {
		if (equipo.isEmpty()) {
			System.out.println("No tienes pokemons en tu equipo.");
			return;
		}
		System.out.println("\nEquipo Actual:");
		for (int k = 0; k < equipo.size(); k++) {
			Pokemon pk = equipo.get(k);
			String estado = pk.getVivo() ? "Vivo" : "Debilitado";
			String ubicacion = (k < 6) ? "(Equipo)" : "(PC)";
			System.out.println((k + 1) + ") " + pk.getNombre() + "|" + pk.getTipo()
					+ "|Stats totales: " + pk.getStats() + " [" + estado + "] " + ubicacion);
		}
	}

	public static void curarPokemon() {
		if (equipo.isEmpty()) {
			System.out.println("No tienes pokemons.");
			return;
		}
		for (int k = 0; k < equipo.size(); k++) {
			equipo.get(k).setVivo(true);
		}
		System.out.println("Tu equipo se ha recuperado!");
	}

	public static void capturarPokemon() {
		ArrayList<Habitat> zonas = Habitat.getHabitats();
		int totalZonas = zonas.size();

		while (true) {
			Menu.mostrarMenuZonas(zonas);
			String op = teclado.nextLine().trim();
			try {
				int opcion = Integer.parseInt(op);
				if (opcion >= 1 && opcion <= totalZonas) {
					modoCaptura(zonas.get(opcion - 1));
					return;
				} else if (opcion == totalZonas + 1) {
					return;
				} else {
					System.out.println("Opcion invalida");
				}
			} catch (NumberFormatException e) {
				System.out.println("Por favor escriba un numero.");
			}
		}
	}

	public static void modoCaptura(Habitat zona) {
		zona.agregarProbabilidades();
		double valor = Math.random();
		Pokemon salvaje = null;
		ArrayList<Double> probs = zona.getProbabilidades();

		for (int k = 0; k < probs.size(); k++) {
			if (valor <= probs.get(k)) {
				salvaje = zona.getPokemon(k);
				break;
			}
		}

		if (salvaje == null) {
			salvaje = zona.getPokemon(probs.size() - 1);
		}

		Menu.mostrarMenuCaptura(salvaje.getNombre());
		String op = "";

		do {
			op = teclado.nextLine().trim();

			switch (op) {
			case "1":
				boolean repetido = false;
				for (int k = 0; k < equipo.size(); k++) {
					if (equipo.get(k).getNombre().equals(salvaje.getNombre())) {
						repetido = true;
						break;
					}
				}
				if (repetido) {
					System.out.println("Ya tienes ese pokemon, no puedes capturarlo de nuevo.");
				} else {
					equipo.add(salvaje);
					salvaje.setVivo(true);
					System.out.println(salvaje.getNombre() + " capturado con exito!!");
					if (equipo.size() <= 6) {
						System.out.println(salvaje.getNombre() + " ha sido agregado a tu equipo!");
					} else {
						System.out.println(salvaje.getNombre() + " ha sido enviado al PC.");
					}
				}
				op = "2";
				break;
			case "2":
				System.out.println("Huiste del combate.");
				break;
			default:
				System.out.println("Opcion invalida");
				System.out.print("Ingrese Opcion: ");
				op = "";
			}
		} while (!op.equals("2"));
	}

	public static void accesoPC() {
		if (equipo.size() < 2) {
			System.out.println("No hay pokemons suficientes para intercambiar.");
			return;
		}

		String op = "";
		do {
			Menu.mostrarMenuPC(equipo);
			op = teclado.nextLine().trim();

			switch (op) {
			case "1":
				cambiarPokemon();
				break;
			case "2":
				break;
			default:
				System.out.println("Opcion invalida");
			}
		} while (!op.equals("2"));
	}

	public static void cambiarPokemon() {
		int idx1 = 0;
		int idx2 = 0;

		try {
			System.out.print("¿Que pokemon desea cambiar? (numero de la lista): ");
			idx1 = Integer.parseInt(teclado.nextLine().trim());
			System.out.print("¿Por que pokemon lo desea cambiar? (numero de la lista): ");
			idx2 = Integer.parseInt(teclado.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Opcion invalida, ingrese un numero.");
			return;
		}

		if (idx1 < 1 || idx1 > equipo.size() || idx2 < 1 || idx2 > equipo.size()) {
			System.out.println("Numero fuera de rango.");
			return;
		}

		if (idx1 == idx2) {
			System.out.println("Son el mismo pokemon.");
			return;
		}

		Pokemon aux = equipo.get(idx1 - 1);
		equipo.set(idx1 - 1, equipo.get(idx2 - 1));
		equipo.set(idx2 - 1, aux);
		System.out.println("Pokemon intercambiados correctamente.");
	}

	// se va a mostrar menu de gimnasios y permitir retar a un lider
	public static void retarGimnasio() {
		// a
	}

	// se va a verificar si el jugador puede retar al gimnasio segun medallas
	public static void poderBatallar(int indiceGym) {
		// a
	}

	// se va a simular la batalla contra el lider del gimnasio
	public static void pelearLider(int indiceGim) {
		// a
	}

	// se va a verificar que el jugador tenga 8 medallas y pokemons vivos
	public static void retarAltoMando() {
		// a
	}

	// se va a combatir contra cada miembro del alto mando en orden
	public static void pelearAltoMando() {
		// a
	}

	// se va a resolver un turno de batalla entre dos pokemons
	public static void atacar(Pokemon rival, Pokemon mio) {
		// a
	}

	// se va a calcular el multiplicador de efectividad entre dos pokemons
	public static double efectividad(Pokemon rival, Pokemon mio) {
		// a
		return 1.0;
	}

	// se va a retornar el indice de columna/fila en la tabla segun el tipo del pokemon
	public static int encontrarTipo(Pokemon pk) {
		// a
		return 0;
	}

	// se va a mostrar los pokemons del equipo activo disponibles para cambiar en batalla
	public static void cambiarPokemonBatalla() {
		// a
	}

	public static Pokemon buscarPokemon(String nombre) {
		for (int k = 0; k < pokedex.size(); k++) {
			if (pokedex.get(k).getNombre().equals(nombre)) {
				return pokedex.get(k);
			}
		}
		return null;
	}

	public static int contarDerrotadosEquipoActivo() {
		int limite = Math.min(equipo.size(), 6);
		int caidos = 0;
		for (int k = 0; k < limite; k++) {
			if (!equipo.get(k).getVivo()) {
				caidos++;
			}
		}
		return caidos;
	}

	public static boolean hayPokemonVivoEnEquipo() {
		int limite = Math.min(equipo.size(), 6);
		for (int k = 0; k < limite; k++) {
			if (equipo.get(k).getVivo()) return true;
		}
		return false;
	}

	public static void cargarPokedex() {
		File archivo = new File("Pokedex.txt");
		Scanner lector;
		try {
			lector = new Scanner(archivo);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				if (linea.isEmpty()) continue;
				String[] datos = linea.split(";");

				String nombre    = datos[0];
				String zona      = datos[1];
				double aparicion = Double.parseDouble(datos[2]);
				int vida         = Integer.parseInt(datos[3]);
				int ataque       = Integer.parseInt(datos[4]);
				int defensa      = Integer.parseInt(datos[5]);
				int atqEsp       = Integer.parseInt(datos[6]);
				int defEsp       = Integer.parseInt(datos[7]);
				int velocidad    = Integer.parseInt(datos[8]);
				String tipo      = datos[9];

				Habitat habitat = zona.equals("none") ? null : Habitat.obtenerOCrear(zona);

				Pokemon pk = new Pokemon(nombre, aparicion, vida, ataque, defensa,
						atqEsp, defEsp, velocidad, tipo, habitat, true);
				pokedex.add(pk);

				if (habitat != null) {
					habitat.agregarPokemon(pk);
				}
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo cargar Pokedex.txt");
		}
	}

	public static void cargarLideres() {
		File archivo = new File("Gimnasios.txt");
		Scanner lector;
		try {
			lector = new Scanner(archivo);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				if (linea.isEmpty()) continue;
				String[] datos = linea.split(";");

				int numGim        = Integer.parseInt(datos[0]);
				String nombre     = datos[1];
				boolean derrotado = datos[2].equals("Derrotado");

				Lider gym = new Lider(nombre, derrotado, numGim);
				lideres.add(gym);

				int cantidad = Integer.parseInt(datos[3]);
				for (int k = 0; k < cantidad; k++) {
					Pokemon aux = buscarPokemon(datos[4 + k]);
					if (aux != null) gym.agregarPokemon(aux);
				}
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo cargar Gimnasios.txt");
		}
	}

	public static void cargarAltoMando() {
		File archivo = new File("Alto Mando.txt");
		Scanner lector;
		try {
			lector = new Scanner(archivo);
			while (lector.hasNextLine()) {
				String linea = lector.nextLine().trim();
				if (linea.isEmpty()) continue;
				String[] datos = linea.split(";");

				int numMando  = Integer.parseInt(datos[0]);
				String nombre = datos[1];

				AltoMando miembro = new AltoMando(nombre, numMando, false);
				altoMando.add(miembro);

				for (int k = 2; k < datos.length; k++) {
					Pokemon aux = buscarPokemon(datos[k]);
					if (aux != null) miembro.agregarPokemon(aux);
				}
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo cargar Alto Mando.txt");
		}
	}
}
