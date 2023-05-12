package pokemon.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import datastructures.Graph;
import domain.Nodo;
import domain.PercepcionNodo;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class AmbientePokemon extends Environment{
	Integer cantCiclos;

		public AmbientePokemon(Graph grafoAmbiente) {
			this.environmentState = new EstadoAmbiente(grafoAmbiente);
		}

		@Override
		public Perception getPercept() {
	        this.ciclosPokemones(); //muevo los pokemones antes de percibir
	        
			PokemonPerception perception = new PokemonPerception();
	        Nodo actual = ((EstadoAmbiente) environmentState).getUbicacion();
	        perception.setActual(actual);
	        List<Nodo> vecinos =((EstadoAmbiente) environmentState).getGrafo().getNeighbors(actual);
	        for(Nodo vecino : vecinos) {
	        	perception.getPercepcionesAdyacentes().put(vecino.getNumero(), new PercepcionNodo(vecino));
	        }
	        perception.getPercepcionesAdyacentes().put(actual.getNumero(), new PercepcionNodo(actual));
	        return perception;
		}
		
		private void ciclosPokemones() {
			/* 
			 * Recorrer todos los nodos del ambiente
			 * Obtener cada pokemon de cada nodo si es que lo tiene
			 * Si cantCiclos > 0 --> decrementar en uno la variable cantCiclos
			 * Si cantCiclos == 0 --> Mover pokemon a un nodo aleatorio
			 * 					  --> Volver a poner variables cantCiclos en un num aleatorio
			 * 							entre 1 y 3. 
			 * 
			 */
			for(Nodo n : ((EstadoAmbiente )this.environmentState).getGrafo().getAllVertices()) { //para cada nodo
					if(n.getTienePokemon() && n.getNumero() != Datos.nodoMaestro) { // si tiene pokemon y no es el pokemon maestro
						if(n.getPokemon().getCiclosParaMoverse() > 0) //si todav no se tiene q mover, resto uno
							n.getPokemon().setCiclosParaMoverse(n.getPokemon().getCiclosParaMoverse()-1);
						else { //si se tiene que mover
							Integer s =this.moverPokemones(n); //lo muevo a un nodo vecino
							Random r = new Random(); 
							if(s != -1) { //si pudo mover seteo entre 1 y 3 la cant de ciclos y lo saco de donde estaba
								n.getPokemon().setCiclosParaMoverse(r.nextInt(3)+1);//seteo un random entre 1 y 3
								n.setTienePokemon(false);
								n.setPokemon(null);
							}
						}
					}
			}
		
		}
		

		private int moverPokemones(Nodo n) {
			
			/*
			 * Recibe el nodo donde se encuentra el pokemon q hay que mover
			 * 1 Obtener vecinos del nodo y elegir uno al azar.
			 * 2 Verificar que en el nodo del vecino no haya pokemones
			 * 3 Si no hay ninguno --> Mover pokemon al elegido (nodoNuevo.setPokemon y tienePokemon = true)
			 * 4 Si hay pokemon en el nodo --> Volver a 1 eligiendo otro nodo al azar (tener listas con nodos probados)
			 * 5 Si se recorre toda la lista de nodos adyacentes y no se pudo mover a ninguno no se mueve.
			 * 6 Se va a probar la proxima vez pq la cantCiclos estara en 0.
			 */
			List<Nodo> vecinos = ((EstadoAmbiente) this.environmentState).getGrafo().getNeighbors(n);
			Random r = new Random();
			ArrayList<Nodo> vecinosSinPokemones = new ArrayList<>();
			for(Nodo vecino : vecinos) {
				if(!vecino.getTienePokemon())
					vecinosSinPokemones.add(vecino);
			}
				if(vecinosSinPokemones.size() == 0)
					return -1; //si no se puede mover a ninguno, salgo sin hacer nada
				Nodo elegido = vecinosSinPokemones.get(r.nextInt(vecinosSinPokemones.size()));
				System.out.println("\u001B[36m" + "Se movio pokemon de " + n.toString() + " a " + elegido.toString() + "\u001B[0m");
				elegido.setTienePokemon(true);
				elegido.setPokemon(n.getPokemon());
				return 1;
		
			}



		@Override
		public String toString() {
			return this.getEnvironmentState().toString();
		}

		@Override
		public boolean agentFailed(Action actionReturned) {
			return (((EstadoAmbiente) this.getEnvironmentState()).getEnergia()) <= 0;
		}
		
}