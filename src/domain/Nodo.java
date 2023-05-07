package domain;

import java.util.List;

public class Nodo {

	Boolean tienePokemon; //true si hay un pokemon en el nodo
	Integer numero;
	Pokemon pokemon; //objeto pokemon (null si tienePokemon es falso)
	Boolean tienePokebola; //true si hay una pokebola en el nodo
	Pokebola pokebola; //objeto pokebola (null si tienePokebola es falso)
	
	
	
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
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Nodo clone() {
		Nodo retorno = new Nodo();
		retorno.setNumero(this.getNumero());
		retorno.setTienePokebola(this.getTienePokebola());
		retorno.setTienePokemon(this.getTienePokemon());
		if(this.getTienePokebola())
			retorno.setPokebola(this.getPokebola().clone());
		if(this.getTienePokemon())
			retorno.setPokemon(this.getPokemon().clone());

		return retorno;
	}
	

    @Override
	public boolean equals(Object obj) {
    	Nodo aComparar = (Nodo) obj;
		return this.numero == aComparar.getNumero(); //dos nodos son el mismo si tienen el mismo num
	}
    
    @Override
    public String toString() {
    	return "Nodo " + this.getNumero();
    }
    
    
	public void actualizar(PercepcionNodo percepcionNodo) {
		this.tienePokemon = percepcionNodo.getTienePokemon();
		this.tienePokebola = percepcionNodo.getTienePokebola();
		if(percepcionNodo.getTienePokemon())
			this.pokemon = percepcionNodo.getPokemon();
		if(percepcionNodo.getTienePokebola())
			this.pokebola = percepcionNodo.getPokebola();
		
	}
	public void actualizar(Nodo n) {
		
		this.tienePokemon = n.getTienePokemon();
		this.tienePokebola = n.getTienePokebola();
		if(n.getTienePokemon())
			this.pokemon = n.getPokemon();
		if(n.getTienePokebola())
			this.pokebola = n.getPokebola();
	}
		
	
}
