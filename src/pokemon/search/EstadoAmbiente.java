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
	 private Boolean maestroMuerto;
	 


	public EstadoAmbiente(Graph grafo){
		this.grafo = grafo;
		this.initState();
	}
	
	@Override
	public void initState() {

		
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
		Nodo inicial = grafo.getVertex(3);
		ubicacion = inicial; //nodo en el q aparece el agente
		
		System.out.println("Hasta antes de generar pokebolas llega");
		
		ArrayList<Integer> nroNodoPokebolas = new ArrayList<Integer>();
		Integer cont = 0;
		while(cont < 5) {
			 Integer nroNodo = rand.nextInt(29) + 1;
			//System.out.println("Numero de nodo elegido al azar:" + nroNodo);
			if(!nroNodoPokebolas.contains(nroNodo) && nroNodo != 11) {
				Nodo random = grafo.getVertex(nroNodo);
				Pokebola pk = new Pokebola();
				pk.setPosicion(random); //le setea a la pokebola el nodo donde va a estar
				pk.setPuntos(Math.random()%6+5); //cant de puntos entre 5 y 10
				random.setPokebola(pk);
				random.setTienePokebola(true);
				nroNodoPokebolas.add(nroNodo);
				cont++;
			}
			
			
		}
		//posiblemente no necuentra ninguna accion pq con ninguna allega al objetivo
		System.out.println("Genera la aparicion de las pokebolas");
		//Aparicion inicial de pokemones
		ArrayList<Integer> nroNodoPokemones = new ArrayList<Integer>();
		Integer cont2 = 0;
		while(cont2 < 11) {
			 Integer nroNodo = rand.nextInt(29) + 1;
			if(!nroNodoPokemones.contains(nroNodo) && !nroNodoPokebolas.contains(nroNodo) && nroNodo != 11) {
				System.out.println("Numero de nodos donde hay pokemon" + nroNodo);
				Nodo random = grafo.getVertex(nroNodo);
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
		maestro.setEnergia(1.0);
		maestro.setVivo(true);
		maestro.setCiclosParaMoverse(0);
		maestro.setActual(null);
		maestro.setEsMaestro(true);
		grafo.getVertex(11).setTienePokemon(true);
		grafo.getVertex(11).setPokemon(maestro);
		System.out.println("Nodo 11 tiene pokemon? " + grafo.getVertex(11).getTienePokemon());
		
		
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
	
	public Boolean getMaestroMuerto() {
		return maestroMuerto;
	}

	public void setMaestroMuerto(Boolean maestroMuerto) {
		this.maestroMuerto = maestroMuerto;
	}


}