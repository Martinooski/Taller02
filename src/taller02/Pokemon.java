package taller02;

public class Pokemon {

	private String nombre;
	
	private Double porcentajeaparición;
	private int vida;
	private int ataque;
	private int defensa;
	private int ataque_esp;
	private int defensa_esp;
	private int velocidad;
	private String tipo;
	private Habitat habitat;
	private Boolean vivo;

	public Pokemon(String nombre, Double porcentajeaparición, int vida, int ataque, int defensa, int ataque_esp,
			int defensa_esp, int velocidad, String tipo, Habitat habitat, Boolean vivo) {
		this.nombre = nombre;
		this.porcentajeaparición = porcentajeaparición;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataque_esp = ataque_esp;
		this.defensa_esp = defensa_esp;
		this.velocidad = velocidad;
		this.tipo = tipo;
		this.habitat = habitat;
		this.vivo = vivo;
	}


	public void setVivo(Boolean vivo) {
		this.vivo = vivo;
	}

	public Boolean getVivo() {
		return vivo;
	}



	public String getNombre() {
		return nombre;
	}
	
	
	
	public String getTipo() {
		return tipo;
	}
	
	
	public Double getPorcentajeaparición() {
		return porcentajeaparición;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getStats() {

		return (vida + ataque + defensa + ataque_esp + defensa_esp + velocidad);
	}

}
