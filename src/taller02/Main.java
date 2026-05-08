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

	// se va a leer el archivo Registros.txt y cargar el usuario, medallas y equipo
	public static void leerArchivo() {
		// a
	}

	// se va a guardar el progreso en Registros.txt
	public static void guardar() {
		// a
	}

	// se va a mostrar el equipo actual del jugador
	public static void revisarEquipo() {
		// a
	}

	// se van a curar todos los pokemons del equipo
	public static void curarPokemon() {
		// a
	}

	// se va a mostrar menu de zonas y permitir al jugador elegir donde capturar
	public static void capturarPokemon() {
		// a
	}

	// se va a generar un pokemon salvaje segun probabilidades de la zona
	public static void modoCaptura(Habitat zona) {
		// a
	}

	// se va a mostrar el PC con todos los pokemons
	public static void accesoPC() {
		// a
	}

	// se van a intercambiar dos pokemons de posicion en el equipo segun indice ingresado
	public static void cambiarPokemon() {
		// a
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
		// TODO
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

	// se va a buscar un pokemon en la pokedex por nombre y retornarlo
	public static Pokemon buscarPokemon(String nombre) {
		// a
		return null;
	}

	// se va a contar cuantos pokemons del equipo activo (primeros 6) estan debilitados
	public static int contarDerrotadosEquipoActivo() {
		// a
		return 0;
	}

	// se va a revisar si hay al menos un pokemon vivo en los primeros 6 del equipo
	public static boolean hayPokemonVivoEnEquipo() {
		// a
		return false;
	}

	// se va a leer el txt y cargar todos los pokemons en el ArrayList pokedex
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

	// se va a leer el txt y cargar los lideres con sus pokemons
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

	// se va a leer el txt y cargar los miembros con sus pokemons
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
