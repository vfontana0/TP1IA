package pokemon.search;

import java.util.ArrayList;

import datastructures.Graph;
import domain.Nodo;
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
	
	public EstadoJugador() {
		
		EstadoAmbiente estado = new EstadoAmbiente();
		
		this.grafo = estado.getGrafo();
		this.ubicacion = estado.getUbicacion();
		this.energiaInicial = ((int) Math.random()) % 10 + 10;
		this.energia = energiaInicial;
		this.nivel = 1;
		this.energiaGanada = 0;
		poderes.add(new Poder("Rayo Aurora", 3, false));
		poderes.add(new Poder("Rayo Meteorico", 3, false));
		poderes.add(new Poder("Rayo Solar", 3, false));
		poderes.add(new Poder("Satelite", 10, false));
	}
	
	 @Override
	 public SearchBasedAgentState clone() {
	    EstadoJugador nuevoEstado = new EstadoJugador();
	    
	    nuevoEstado.setEnergia(this.getEnergia());
	    Graph grafo = new Graph();
	    
	    
	    return nuevoEstado;
	 }
	 
		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void updateState(Perception p) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return null;
		}

	 
	 
	 
	 
	 
	 @Override
		public void initState() {
			/* TODO 
			 * Crear grafo de mapa agregando nodos y vertices
			 * Inicializar nodo inicial del agente, seria un random entre los nodos q se crearon
			 * */
			ArrayList<Nodo> nodos = new ArrayList<>();
			 
			for(int i=1; i<29; i++) {
				Nodo actual = new Nodo();
				nodos.add(actual);
			}
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

			grafo.addEdge(nodos.get(1), nodos.get(2));
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
	
	
	
	
	 
	 
}
