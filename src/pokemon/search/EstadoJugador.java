package pokemon.search;

import java.util.ArrayList;

import datastructures.Graph;
import domain.Nodo;
import domain.Poder;
import domain.Pokebola;
import domain.Pokemon;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgentState;

public class EstadoJugador extends SearchBasedAgentState {
	private Nodo ubicacion;
	private Graph grafo;
	private double energia;
	private double energiaInicial;
	private int nivel;
	private double energiaGanada;
	private ArrayList<Poder> poderes;
	private Boolean huyoUltimoNodo;
	private Boolean maestroMuerto;
	
	public EstadoJugador(Graph grafo) {
		this.energiaInicial = ((int) Math.random()) % 10 + 10;
		this.energia = energiaInicial;
		this.nivel = 1;
		this.energiaGanada = 0;
		this.grafo = grafo;
		poderes = new ArrayList<>();
		poderes.add(new Poder("Rayo Aurora", 3, false));
		poderes.add(new Poder("Rayo Meteorico", 3, false));
		poderes.add(new Poder("Rayo Solar", 3, false));
		poderes.add(new Poder("Satelite", 10, false));
		this.initState();
	}
	
	 @Override
	 public SearchBasedAgentState clone() {
		 //primitivos
	    EstadoJugador nuevoEstado = new EstadoJugador(this.getMapa().clone());
	    nuevoEstado.setEnergia(this.getEnergia());
	    nuevoEstado.setEnergiaGanada(this.getEnergiaGanada());
	    nuevoEstado.setEnergiaInicial(this.getEnergiaInicial()); 
	    nuevoEstado.setHuyoUltimoNodo(this.getHuyoUltimoNodo());
	    nuevoEstado.setMaestroMuerto(this.getMaestroMuerto());
	    nuevoEstado.setNivel(this.getNivel());
	    //poderes
	    ArrayList<Poder> poderesNuevo = new ArrayList<>();
	    for(Poder p: this.getPoderes()) {
	    	poderesNuevo.add(p.clone());
	    }
	    nuevoEstado.setPoderes(poderesNuevo);
	    //ubicacion
	    nuevoEstado.setUbicacion(nuevoEstado.getMapa().getVertex(this.getUbicacion().getNumero()));
	    
	    return nuevoEstado;
	    
	 }
	 
		@Override
		public boolean equals(Object obj) {
			EstadoJugador est = (EstadoJugador) obj;
			return this.ubicacion.equals(est.getUbicacion()) 
					&& this.getEnergia() == est.getEnergia() 
					&& this.getEnergiaGanada() == est.getEnergiaGanada(); 

//se esta en el mismo estado si esta dos veces en la misma ubicacion, con la misma energia y misma energia ganada.
		}

		@Override
		public void updateState(Perception p) {
			//actualizar estado en base a las percepciones
			PokemonPerception per = (PokemonPerception) p;
			this.getUbicacion().setTienePokemon(per.getHayPokemonNodoActual());
			this.getUbicacion().setTienePokebola(per.getHayPokebolaNodoActual());
			System.out.println("Estoy percibiendo el nodo" + this.getUbicacion().toString());
			if(per.getHayPokemonNodoActual()) {
				System.out.println("Estoy percibiendo " + this.getUbicacion().toString() + " y hay pokemon");
				Pokemon pokemon = new Pokemon();
				pokemon.setEnergia(per.getEnergiaPokemonNodoActual());
				pokemon.setEsMaestro(per.getPokemonEsMaestro());
				this.getUbicacion().setPokemon(pokemon); //creo un pokemon solo con la info d la percepcion
				this.getMapa().getVertex(this.getUbicacion().getNumero()).setPokemon(pokemon);
				this.getMapa().getVertex(this.getUbicacion().getNumero()).setTienePokemon(true);
			}
			if(per.getHayPokebolaNodoActual()) {
				Pokebola pokebola = new Pokebola();
				pokebola.setPuntos(per.getEnergiaPokebolaNodoActual()); //creo una pokebola con la info d la percepcion
				this.getUbicacion().setPokebola(pokebola);
				this.getMapa().getVertex(this.getUbicacion().getNumero()).setPokebola(pokebola);
				this.getMapa().getVertex(this.getUbicacion().getNumero()).setTienePokebola(true);
				//this.setEnergia(this.getEnergia()+pokebola.getPuntos()); //incremento automaticamente si hay pokeboal
			}
		}

		@Override
		public String toString() {
			return "Ubicacion: " + this.getUbicacion() + " Energia: " + this.getEnergia();
		}

	 
	 @Override
		public void initState() {
			this.maestroMuerto=false;
			this.ubicacion = grafo.getVertex(3);
			//Nodo inicial = nodos.get(Integer.valueOf((int) Math.random()) % 29 + 1); como hacemos eso?
		}
	 

	public Nodo getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Nodo ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Graph getMapa() {
		return grafo;
	}

	public void setMapa(Graph mapa) {
		this.grafo = mapa;
	}

	public double getEnergia() {
		return energia;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

	public double getEnergiaInicial() {
		return energiaInicial;
	}

	public void setEnergiaInicial(double energiaInicial) {
		this.energiaInicial = energiaInicial;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public double getEnergiaGanada() {
		return energiaGanada;
	}

	public void setEnergiaGanada(double energiaGanada) {
		this.energiaGanada = energiaGanada;
	}



	public ArrayList<Poder> getPoderes() {
		return poderes;
	}

	public void setPoderes(ArrayList<Poder> poderes) {
		this.poderes = poderes;
	}

	public Boolean getHuyoUltimoNodo() {
		return huyoUltimoNodo;
	}

	public void setHuyoUltimoNodo(Boolean huyoUltimoNodo) {
		this.huyoUltimoNodo = huyoUltimoNodo;
	}

	public Boolean getMaestroMuerto() {
		return maestroMuerto;
	}

	public void setMaestroMuerto(Boolean maestroMuerto) {
		this.maestroMuerto = maestroMuerto;
	}
	
	
	
	
	
	
	 
	 
}
