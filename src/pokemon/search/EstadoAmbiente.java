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
	 ArrayList<Integer> nroNodoPokebolas = new ArrayList<Integer>();
	 ArrayList<Integer> nroNodoPokemones = new ArrayList<Integer>();


	public EstadoAmbiente(Graph grafo, Integer nodoInicio){
		this.grafo = grafo;
		this.ubicacion = this.grafo.getVertex(nodoInicio);
		this.initState();
		
	}
	

	@Override
	public void initState() {

		//Crea arreglo de habilidades especiales
		poderes = new ArrayList<>();
		poderes.add(new Poder("Rayo Aurora", 3, false));
		poderes.add(new Poder("Rayo Meteorico", 3, false));
		poderes.add(new Poder("Rayo Solar", 3, false));
		poderes.add(new Poder("Satelite", 10, false));
		//posicion inicial del agente --> deberia ser aleatoria

		this.setearMaestro(); //genera al maestro y lo setea en el nodo 11
		this.generarPokebolas(); //genera pokebolas en nodos aleatorios
		this.generarPokemones(); //genera pokemones en nodos aleatorios
		
	}
	
	
	

	private void generarPokemones() {
		Random rand = new Random();
		Integer cont2 = 0;
		while(cont2 < 11) {
			 Integer nroNodo = rand.nextInt(29) + 1;
			if(!nroNodoPokemones.contains(nroNodo) && !nroNodoPokebolas.contains(nroNodo) && nroNodo != 11) {
				System.out.println("Numero de nodos donde hay pokemon" + nroNodo);
				Nodo random = grafo.getVertex(nroNodo);
				Pokemon pk = new Pokemon();
				pk.setActual(random);
				pk.setEnergia(5.0);
				pk.setCiclosParaMoverse(rand.nextInt(3)+1);
				pk.setVivo(true);
				pk.setEsMaestro(false);
				random.setTienePokemon(true);
				random.setPokemon(pk);
				nroNodoPokemones.add(nroNodo);
				cont2++;
			}	
		}
		
	}

	private void generarPokebolas() {
		Random rand = new Random();
		Integer cont = 0;
		while(cont < 5) {
			 Integer nroNodo = rand.nextInt(29) + 1;
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
		
	}

	private void setearMaestro() {
		Pokemon maestro = new Pokemon();
		maestro.setEnergia(9.0);
		maestro.setVivo(true);
		maestro.setCiclosParaMoverse(0);
		maestro.setActual(null);
		maestro.setEsMaestro(true);
		grafo.getVertex(11).setTienePokemon(true);
		grafo.getVertex(11).setPokemon(maestro);
		
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

	public ArrayList<Poder> getPoderes() {
		return poderes;
	}

	public void setPoderes(ArrayList<Poder> poderes) {
		this.poderes = poderes;
	}

	
}


