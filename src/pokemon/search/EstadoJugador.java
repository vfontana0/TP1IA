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
	
	public EstadoJugador() {
		this.energiaInicial = ((int) Math.random()) % 10 + 10;
		this.energia = energiaInicial;
		this.nivel = 1;
		this.energiaGanada = 0;
		grafo = new Graph();
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
	    EstadoJugador nuevoEstado = new EstadoJugador();
	    nuevoEstado.setEnergia(this.getEnergia());
	    nuevoEstado.setEnergiaGanada(this.getEnergiaGanada());
	    nuevoEstado.setEnergiaInicial(this.getEnergiaInicial());
	    nuevoEstado.setHuyoUltimoNodo(this.getHuyoUltimoNodo());
	    nuevoEstado.setNivel(this.getNivel());
	    //poderes
	    ArrayList<Poder> poderesNuevo = new ArrayList<>();
	    for(Poder p: this.getPoderes()) {
	    	poderesNuevo.add(p.clone());
	    }
	    nuevoEstado.setPoderes(poderesNuevo);
	  
	    
	    //grafo
	    nuevoEstado.setMapa(this.getMapa().clone());
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
			if(per.getHayPokemonNodoActual()) {
				Pokemon pokemon = new Pokemon();
				pokemon.setEnergia(per.getEnergiaPokemonNodoActual());
				this.getUbicacion().setPokemon(pokemon); //creo un pokemon solo con la info d la percepcion
			}
			if(per.getHayPokebolaNodoActual()) {
				Pokebola pokebola = new Pokebola();
				pokebola.setPuntos(per.getEnergiaPokebolaNodoActual()); //creo una pokebola con la info d la percepcion
			}
			// TODO: Tener una percepcion q diga si el pokemon es maestro o no
		}

		@Override
		public String toString() {
			return "Ubicacion: " + this.getUbicacion() + " Energia: " + this.getEnergia();
		}

	 
	 @Override
		public void initState() {
			ArrayList<Nodo> nodos = new ArrayList<>();		
			nodos.add(0, null);
			for(int i=1; i<=29; i++) {
				Nodo actual = new Nodo();
				actual.setNumero(i);
				actual.setTienePokebola(false);
				actual.setPokebola(null);
				actual.setTienePokemon(false);
				actual.setPokemon(null);
				nodos.add(i, actual);
				grafo.addVertex(actual);
			}
			this.agregarConexiones(nodos);
			this.ubicacion = nodos.get(3);
			//Nodo inicial = nodos.get(Integer.valueOf((int) Math.random()) % 29 + 1); como hacemos eso?
		}
	 
	private void agregarConexiones(ArrayList<Nodo> nodos) {
		grafo.addEdge(nodos.get(1), nodos.get(2));
		grafo.addEdge(nodos.get(2), nodos.get(3));
		grafo.addEdge(nodos.get(2), nodos.get(9));
		grafo.addEdge(nodos.get(3), nodos.get(8));
		grafo.addEdge(nodos.get(3), nodos.get(4));
		grafo.addEdge(nodos.get(4), nodos.get(12));
		grafo.addEdge(nodos.get(4), nodos.get(5));
		grafo.addEdge(nodos.get(4), nodos.get(7));
		grafo.addEdge(nodos.get(5), nodos.get(6));
		grafo.addEdge(nodos.get(6), nodos.get(7));
		grafo.addEdge(nodos.get(8), nodos.get(12));
		grafo.addEdge(nodos.get(9), nodos.get(10));
		grafo.addEdge(nodos.get(9), nodos.get(11));
		grafo.addEdge(nodos.get(9), nodos.get(12));
		grafo.addEdge(nodos.get(10), nodos.get(11));
		grafo.addEdge(nodos.get(10), nodos.get(14));
		grafo.addEdge(nodos.get(11), nodos.get(15));
		grafo.addEdge(nodos.get(12), nodos.get(13));
		grafo.addEdge(nodos.get(13), nodos.get(16));
		grafo.addEdge(nodos.get(14), nodos.get(20));
		grafo.addEdge(nodos.get(15), nodos.get(16));
		grafo.addEdge(nodos.get(15), nodos.get(20));
		grafo.addEdge(nodos.get(16), nodos.get(17));
		grafo.addEdge(nodos.get(16), nodos.get(20));
		grafo.addEdge(nodos.get(17), nodos.get(18));
		grafo.addEdge(nodos.get(18), nodos.get(19));
		grafo.addEdge(nodos.get(18), nodos.get(28));
		grafo.addEdge(nodos.get(19), nodos.get(21));
		grafo.addEdge(nodos.get(20), nodos.get(21));
		grafo.addEdge(nodos.get(20), nodos.get(22));
		grafo.addEdge(nodos.get(21), nodos.get(25));
		grafo.addEdge(nodos.get(21), nodos.get(29));
		grafo.addEdge(nodos.get(22), nodos.get(23));
		grafo.addEdge(nodos.get(22), nodos.get(24));
		grafo.addEdge(nodos.get(24), nodos.get(25));
		grafo.addEdge(nodos.get(25), nodos.get(26));
		grafo.addEdge(nodos.get(26), nodos.get(27));
		grafo.addEdge(nodos.get(27), nodos.get(28));
		grafo.addEdge(nodos.get(28), nodos.get(29));

		
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
	
	
	
	
	 
	 
}
