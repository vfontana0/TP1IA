package src.domain;

public class PercepcionNodo {

	Pokemon pokemon;
	Pokebola pokebola;
	Boolean tienePokemon;
	Boolean tienePokebola;
	
	public PercepcionNodo(Nodo n) {
		this.tienePokemon = n.getTienePokemon();
		this.tienePokebola = n.getTienePokebola();
		if(n.getTienePokebola())
			this.pokebola = n.pokebola;
		if(n.getTienePokemon())
			this.pokemon = n.pokemon;
	}
	
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	public Pokebola getPokebola() {
		return pokebola;
	}
	public void setPokebola(Pokebola pokebola) {
		this.pokebola = pokebola;
	}
	public Boolean getTienePokemon() {
		return tienePokemon;
	}
	public void setTienePokemon(Boolean tienePokemon) {
		this.tienePokemon = tienePokemon;
	}
	public Boolean getTienePokebola() {
		return tienePokebola;
	}
	public void setTienePokebola(Boolean tienePokebola) {
		this.tienePokebola = tienePokebola;
	}
	
    @Override
    public String toString() {
        return "PercepcionNodo{" +
                ", tienePokemon=" + tienePokemon +
                ", tienePokebola=" + tienePokebola +
                '}';
    }
}
