package pokemon.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import datastructures.Graph;
import domain.Nodo;
import pokemon.search.*;
import domain.PercepcionNodo;
import domain.Poder;
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
	public Boolean gano = false; //algo de la gui
	
	public EstadoJugador(Graph grafo, Integer nodoInicio, Double energia) {
		this.grafo = grafo;
		this.ubicacion = this.grafo.getVertex(nodoInicio);
		this.energia = energia;
		this.energiaInicial = energia;
		this.initState();
	}
	
	 @Override
	 public SearchBasedAgentState clone() {
		 //primitivos
	    EstadoJugador nuevoEstado = new EstadoJugador(this.getMapa().clone(), this.getUbicacion().getNumero(), this.getEnergia());
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
	    //ubicacion
	    nuevoEstado.setUbicacion(nuevoEstado.getMapa().getVertex(this.getUbicacion().getNumero()));
	    
	    return nuevoEstado;
	    
	 }
	 
		@Override
		public boolean equals(Object obj) {
			EstadoJugador est = (EstadoJugador) obj;
			return this.ubicacion.equals(est.getUbicacion()) 
					&& this.getEnergia() == est.getEnergia(); 

//se esta en el mismo estado si esta dos veces en la misma ubicacion, con la misma energia y misma energia ganada.
		}

		@Override
		public void updateState(Perception p) {
	    
			//actualizar estado en base a las percepciones
			PokemonPerception per = (PokemonPerception) p;
			HashMap<Integer, PercepcionNodo> percepciones = per.getPercepcionesAdyacentes(); // obtengo percepciones
			
			for(Integer nroNodo : percepciones.keySet()) { //para cada numero de nodo
				this.grafo.getVertex(nroNodo).actualizar(percepciones.get(nroNodo)); //actualizo cada nodo vecino con info de la percepcion
			}
		    this.actualizarCiclos();
			
		}
			 
		
		private void actualizarCiclos() {
			//Se ejecuta una vez por ciclo percepcion-accion
			for(Poder p: this.getPoderes()) { //para cada poder
				if(p.getCantCiclos() > 0) //si faltan ciclos para poder usar
					p.setCantCiclos(p.getCantCiclos()-1); //si es mayor a 0 reduzco en uno los ciclos
				else {
					p.setPuedoUsar(true);
				}
			}
		}
		

		@Override
		public String toString() {
			return " [ Ubicacion: " + this.getUbicacion() + " Energia: " + this.getEnergia() + " Energia ganada (relativa a inicial): " + this.getEnergiaGanada()/this.getEnergiaInicial() + "]";
		}

	 
	 @Override
		public void initState() {
		    this.nivel = 1;
			this.energiaGanada = 0;
			poderes = new ArrayList<>();
			poderes.add(new Poder("Rayo Aurora", 3, false));
			poderes.add(new Poder("Rayo Meteorico", 3, false));
			poderes.add(new Poder("Rayo Solar", 3, false));
			poderes.add(new Poder("Satelite", 10, false));
			this.huyoUltimoNodo = false;
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