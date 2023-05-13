package pokemon.search;

import java.util.ArrayList;
import java.util.Random;

import datastructures.Graph;
import domain.Nodo;
import domain.Poder;
import domain.Pokebola;
import domain.Pokemon;
import frsf.cidisi.faia.state.EnvironmentState;

public class EstadoAmbiente extends EnvironmentState{
 
	 private Graph grafo;
	 private Nodo ubicacion;
	 private ArrayList<Poder> poderes;
	 private Double energia;
	 ArrayList<Integer> nroNodoPokebolas = new ArrayList<Integer>();
	 ArrayList<Integer> nroNodoPokemones = new ArrayList<Integer>();


	public EstadoAmbiente(Graph grafo){
		this.grafo = grafo;
		this.energia = Datos.energiaJugador;
		this.ubicacion = this.grafo.getVertex(Datos.nodoInicio);
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

		this.setearMaestro(); //genera al maestro y lo setea en el nodo del maestro
		this.generarPokebolas(); //genera pokebolas en nodos aleatorios
		this.generarPokemones(); //genera pokemones en nodos aleatorios
		
	}
	
	
	

	private void generarPokemones() {
		Random rand = new Random();
		Integer cont2 = 0;
		while(cont2 < 11) {
			 Integer nroNodo = rand.nextInt(29) + 1;
			if(!nroNodoPokemones.contains(nroNodo) && !nroNodoPokebolas.contains(nroNodo) && nroNodo != Datos.nodoMaestro) {
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
			if(!nroNodoPokebolas.contains(nroNodo) && nroNodo != Datos.nodoMaestro) {
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
		maestro.setEnergia(Datos.energiaMaestro);
		maestro.setVivo(true);
		maestro.setCiclosParaMoverse(0);
		maestro.setActual(null);
		maestro.setEsMaestro(true);
		grafo.getVertex(Datos.nodoMaestro).setTienePokemon(true);
		grafo.getVertex(Datos.nodoMaestro).setPokemon(maestro);
		
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


	public Double getEnergia() {
		return energia;
	}


	public void setEnergia(Double energia) {
		this.energia = energia;
	}



}