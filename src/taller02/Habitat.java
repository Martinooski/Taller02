package taller02;

import java.util.ArrayList;

public class Habitat {

	private String nombre;
	private ArrayList<Pokemon> habitat = new ArrayList<Pokemon>();
	private ArrayList<Double> probabilidades = new ArrayList<Double>();

	public Habitat(String nombre) {
		this.nombre = nombre;
	}
	
	public static Habitat obtenerOCrear(String nombre) {
		for (Habitat h : habitats) {
			if (h.getNombre().equals(nombre)) {
				return h;
			}
		}
		Habitat nuevo = new Habitat(nombre);
		habitats.add(nuevo);
		return nuevo;
	}
 
	public static ArrayList<Habitat> getHabitats() {
		return habitats;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregarPokemon(Pokemon pokemon) {
		habitat.add(pokemon);
	}
	
	public Pokemon getPokemon(int index) {
		return habitat.get(index);
	}
	
	public ArrayList<Double> getProbabilidades() {
		return probabilidades;
	}

	public void addProbabilidades() { 
		if (probabilidades.isEmpty()) { 

			for (int i = 0; i < habitat.size(); i++) {
				double porcentajeaparición = habitat.get(i).getPorcentajeaparición();
				if (probabilidades.isEmpty()) {
					probabilidades.add(porcentajeaparición);
				} else {
					probabilidades.add(probabilidades.get(i - 1) + porcentajeaparición);
				}
			}
		}
	}

}