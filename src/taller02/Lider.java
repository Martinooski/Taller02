package taller02;

import java.util.ArrayList;

public class Lider {
	private String nombre;
	private Boolean derrotado;
	private int numerogym;
	private ArrayList<Pokemon> liderpokemons = new ArrayList<Pokemon>();
	
	public Lider(String nombre, Boolean derrotado, int numerogym) {
		super();
		this.nombre = nombre;
		this.derrotado = false;
		this.numerogym = numerogym;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Boolean getDerrotado() {
		return derrotado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDerrotado(Boolean derrotado) {
		this.derrotado = derrotado;
	}
	
	public void agregarPokemon(Pokemon pokemon) {
		liderpokemons.add(pokemon);
	}
	
	public ArrayList<Pokemon> getLiderpokemons() {
		return liderpokemons;
	}

	public void setPokemons_lider(ArrayList<Pokemon> liderpokemons) {
		this.liderpokemons = liderpokemons;
	}

	public int getNumerogym() {
		return numerogym;
	}

	public void setNumerogym(int numerogym) {
		this.numerogym = numerogym;
	}
	
	
}
