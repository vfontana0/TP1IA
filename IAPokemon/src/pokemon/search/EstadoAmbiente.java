package pokemon.search;

import datastructures.Graph;
import domain.Nodo;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState{
 
	 private Graph grafo;
	 private Nodo ubicacion;
	
	public EstadoAmbiente(){
		grafo = new Graph();
		Nodo inicial = new Nodo();
		this.initState();
	}
	
	@Override
	public void initState() {
		/* TODO 
		 * Crear grafo de mapa agregando nodos y vertices
		 * Inicializar nodo inicial del agente, seria un random entre los nodos q se crearon
		 * */
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public Graph getGrafo() {
		return grafo;
	}

	public void setGrafo(Graph grafo) {
		this.grafo = grafo;
	}

	public Nodo getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Nodo ubicacion) {
		this.ubicacion = ubicacion;
	}

	
}
