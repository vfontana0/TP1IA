package pokemon.search;


import java.util.Vector;
import datastructures.Graph;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.Search;
import pokemon.search.actions.ElegirHuir;
import pokemon.search.actions.ElegirPelear;
import pokemon.search.actions.ElegirUsarRayoAurora;
import pokemon.search.actions.ElegirUsarRayoMeteorico;
import pokemon.search.actions.ElegirUsarRayoSolar;
import pokemon.search.actions.IrANodoN;
import pokemon.search.actions.JuntarPokebola;

public class Jugador extends SearchBasedAgent {

	public Jugador(Graph grafo) {
	ObjetivoJugador jugadorGoal = new ObjetivoJugador();
	EstadoJugador jugadorState = new EstadoJugador(grafo);
	this.setAgentState(jugadorState);
	Vector<SearchAction> operators = new Vector<SearchAction>();
	for(int i=1; i<=29; i++) {
		operators.add(new IrANodoN(i));
	}
	operators.add(new JuntarPokebola());
	operators.add(new ElegirPelear());
	operators.add(new ElegirHuir());
	operators.add(new ElegirUsarRayoSolar());
	operators.add(new ElegirUsarRayoMeteorico());
	operators.add(new ElegirUsarRayoAurora());
	
	System.out.println("Operadores: " + operators.toString());
	Problem problem = new Problem(jugadorGoal, jugadorState, operators);
	 this.setProblem(problem);
	}


	@Override
	public void see(Perception p) {
		this.getAgentState().updateState(p);
	}

	
	@Override
	public Action selectAction() {
		BreathFirstSearch estrategiaBusqueda = new BreathFirstSearch();
		//DepthFirstSearch estrategiaBusqueda = new DepthFirstSearch();
		Search busqueda = new Search(estrategiaBusqueda);
		 this.setSolver(busqueda);
		 Action accionSeleccionada = null;
	        try {
	            accionSeleccionada = this.getSolver().solve(new Object[]{this.getProblem()});
	            System.out.println("Accion seleccionada: " + accionSeleccionada.toString());
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	        return accionSeleccionada;
		
	}


}

