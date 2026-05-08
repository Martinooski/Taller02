package taller02;
 
import java.util.ArrayList;
 
public class AltoMando {
 
	private String nombre;
	private int numeromando;
	private Boolean derrotado;
	private ArrayList<Pokemon> mandopokemons = new ArrayList<Pokemon>();
	
	public AltoMando(String nombre, int numeromando, Boolean derrotado) {
		super();
		this.nombre = nombre;
		this.numeromando = numeromando;
		this.derrotado = derrotado;
	}
	
	public void agregarPokemon(Pokemon pokemon) {
		mandopokemons.add(pokemon);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeromando() {
		return numeromando;
	}

	public void setNumeromando(int numeromando) {
		this.numeromando = numeromando;
	}

	public Boolean getDerrotado() {
		return derrotado;
	}

	public void setDerrotado(Boolean derrotado) {
		this.derrotado = derrotado;
	}
 
	public ArrayList<Pokemon> getMandopokemons() {
		return mandopokemons;
	}
 
	public void setPokemons_mando(ArrayList<Pokemon> mandopokemons) {
		this.mandopokemons = mandopokemons;
	} 
}
 













