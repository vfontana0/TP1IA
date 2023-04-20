package domain;

import java.util.List;

public class Nodo {

	Boolean tienePokemon; //true si hay un pokemon en el nodo
	Pokemon pokemon; //objeto pokemon (null si tienePokemon es falso)
	Boolean tienePokebola; //true si hay una pokebola en el nodo
	Pokebola pokebola; //objeto pokebola (null si tienePokebola es falso)
	List<Nodo> nodosAdyacentes; //lista de nodos vecinos al actual.
	
	
	
	
	public Boolean getTienePokemon() {
		return tienePokemon;
	}
	public void setTienePokemon(Boolean tienePokemon) {
		this.tienePokemon = tienePokemon;
	}
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	public Boolean getTienePokebola() {
		return tienePokebola;
	}
	public void setTienePokebola(Boolean tienePokebola) {
		this.tienePokebola = tienePokebola;
	}
	public Pokebola getPokebola() {
		return pokebola;
	}
	public void setPokebola(Pokebola pokebola) {
		this.pokebola = pokebola;
	}
	public List<Nodo> getNodosAdyacentes() {
		return nodosAdyacentes;
	}
	public void setNodosAdyacentes(List<Nodo> nodosAdyacentes) {
		this.nodosAdyacentes = nodosAdyacentes;
	}
	
	
	
	
}
