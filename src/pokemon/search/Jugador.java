package pokemon.search;
import java.util.Vector;
import java.util.ArrayList;
import datastructures.Graph;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.GreedySearch;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.UniformCostSearch;
import javafx.util.Pair;

import pokemon.search.actions.*;


public class Jugador extends SearchBasedAgent {
	private ArrayList<Pair<Action, Double>> searchActions;
	EstadoJugador jugadorState;
	public ArrayList<Pair<Action, Double>> getSearchActions() {
		return searchActions;
	}
	
	public Boolean gano() {
		return jugadorState.gano;
	}
	
	public void setSearchActions(ArrayList<Pair<Action, Double>> searchActions) {
		this.searchActions = searchActions;
	}


	public Jugador(Graph grafo, Integer nodoInicio, Double energia) {
		searchActions = new ArrayList<>();
		ObjetivoJugador jugadorGoal = new ObjetivoJugador();
		jugadorState = new EstadoJugador(grafo, nodoInicio, energia);
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
		operators.add(new ElegirUsarSatelite());
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
	
		

        /**
         * Another search strategy examples:
         * 
         * Depth First Search:
         * DepthFirstSearch strategy = new DepthFirstSearch();
         * 
         * Breath First Search:
         * BreathFirstSearch strategy = new BreathFirstSearch();
         
         IStepCostFunction costFunction = new FuncionCosto();
         UniformCostSearch strategy = new UniformCostSearch(costFunction);

         
         * A Star Search:
         * IStepCostFunction cost = new CostFunction();
         * IEstimatedCostFunction heuristic = new Heuristic();
         * AStarSearch strategy = new AStarSearch(cost, heuristic);
         *          **/ 
          IEstimatedCostFunction heuristic = new Heuristica();
          GreedySearch strategy = new GreedySearch(heuristic);
         
		
		Search busqueda = new Search(strategy);
		 busqueda.setVisibleTree(Search.EFAIA_TREE);
		 this.setSolver(busqueda);
		 Action accionSeleccionada = null;
	        try {
	            accionSeleccionada = this.getSolver().solve(new Object[]{this.getProblem()});
	            searchActions.add(new Pair(accionSeleccionada, jugadorState.getEnergia()));
	            System.out.println("Accion seleccionadaa: " + accionSeleccionada.toString());
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	        return accionSeleccionada;
		
	}

	
}
