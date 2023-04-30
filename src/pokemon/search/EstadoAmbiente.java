package pokemon.search;

import java.util.ArrayList;
import java.util.Random;

import datastructures.Graph;
import domain.Nivel;
import domain.Nodo;
import domain.Poder;
import domain.Pokebola;
import domain.Pokemon;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState{
 
	 private Graph grafo;
	 private Nodo ubicacion;
	 private ArrayList<Poder> poderes;
	 private Boolean falla;
	 
	public EstadoAmbiente(){
		grafo = new Graph();
		Nodo inicial = new Nodo();
		this.initState();
		
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
		System.out.println("Crea los nodos del estado del ambiente");
		this.agregarConexionesGrafo(nodos);
		System.out.println("Agrega las conexiones");
	poderes = new ArrayList<>();
	poderes.add(new Poder("Rayo Aurora", 3, false));
	poderes.add(new Poder("Rayo Meteorico", 3, false));
	poderes.add(new Poder("Rayo Solar", 3, false));
	poderes.add(new Poder("Satelite", 10, false));
	/*En el clone (ver si es asi) verificar la cantidad del ciclos, si es 0 mandar mapa al agente o 
	mover al pokemon, esas cosas y disminuir en uno*/
	
		//Crear grafos del ambiente
		Random rand = new Random();
	
		//Nodo inicial = nodos.get(rand.nextInt(29)+1);
		Nodo inicial = nodos.get(3);
		ubicacion = inicial; //nodo en el q aparece el agente
		
		System.out.println("Hasta antes de generar pokebolas llega");
		
		ArrayList<Integer> nroNodoPokebolas = new ArrayList<Integer>();
		Integer cont = 0;
		while(cont < 5) {
			 Integer nroNodo = rand.nextInt(29) + 1;
			System.out.println("Numero de nodo elegido al azar:" + nroNodo);
			if(!nroNodoPokebolas.contains(nroNodo) && nroNodo != 11) {
				Nodo random = nodos.get(nroNodo);
				Pokebola pk = new Pokebola();
				pk.setPosicion(random); //le setea a la pokebola el nodo donde va a estar
				pk.setPuntos(Math.random()%6+5); //cant de puntos entre 5 y 10
				random.setPokebola(pk);
				random.setTienePokebola(true);
				nroNodoPokebolas.add(nroNodo);
				cont++;
			}
		}
		System.out.println("Genera la aparicion de las pokebolas");
		//Aparicion inicial de pokemones
		ArrayList<Integer> nroNodoPokemones = new ArrayList<Integer>();
		Integer cont2 = 0;
		while(cont2 < 11) {
			 Integer nroNodo = rand.nextInt(29) + 1;
			if(!nroNodoPokemones.contains(nroNodo) && !nroNodoPokebolas.contains(nroNodo) && nroNodo != 11) {
				Nodo random = nodos.get(nroNodo);
				Pokemon pk = new Pokemon();
				pk.setActual(random);
				pk.setEnergia(5.0);
				pk.setCiclosParaMoverse((int) (Math.random() % 3 +1));
				pk.setVivo(true);
				pk.setEsMaestro(false);
				random.setTienePokemon(true);
				random.setPokemon(pk);
				nroNodoPokemones.add(nroNodo);
				cont2++;
			}	
		}
		
	
		Pokemon maestro = new Pokemon();
		maestro.setEnergia(10.0);
		maestro.setVivo(true);
		maestro.setActual(nodos.get(11));
		maestro.setEsMaestro(true);
		
	
	
	}
	
    private void agregarConexionesGrafo(ArrayList<Nodo> nodos) {
    	

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

	public boolean agentFailed(Action actionReturned) {
    	boolean failed = false;

 

        return failed;
    }

	@Override
	public String toString() {
		return "Ubicacion: " + this.getUbicacion().toString(); 
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