package pokemon.search;
import java.util.Vector;
import java.util.ArrayList;
import datastructures.Graph;
import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import frsf.cidisi.faia.solver.search.AStarSearch;
import frsf.cidisi.faia.solver.search.BreathFirstSearch;
import frsf.cidisi.faia.solver.search.DepthFirstSearch;
import frsf.cidisi.faia.solver.search.GreedySearch;
import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.IStepCostFunction;
import frsf.cidisi.faia.solver.search.Search;
import frsf.cidisi.faia.solver.search.Strategy;
import frsf.cidisi.faia.solver.search.UniformCostSearch;
import javafx.util.Pair;

import pokemon.search.actions.*;


public class Jugador extends SearchBasedAgent {
	private ArrayList<Pair<Action, Double>> searchActions;
	private Integer nroEstrategia;
	EstadoJugador jugadorState;
	private Vector<SearchAction> operators = new Vector<SearchAction>();
	public ArrayList<Pair<Action, Double>> getSearchActions() {
		return searchActions;
	}
	
	public Boolean gano() {
		return jugadorState.gano;
	}
	
	public void setSearchActions(ArrayList<Pair<Action, Double>> searchActions) {
		this.searchActions = searchActions;
	}


	public Jugador(Graph grafo) {
		searchActions = new ArrayList<>();
		ObjetivoJugador jugadorGoal = new ObjetivoJugador();
		this.nroEstrategia = Datos.nroEstrategia;
		jugadorState = new EstadoJugador(grafo);
		this.setAgentState(jugadorState);
		operators.add(new JuntarPokebola());
		operators.add(new ElegirHuir());
		operators.add(new ElegirUsarRayoSolar());
		operators.add(new ElegirUsarRayoMeteorico());
		operators.add(new ElegirUsarRayoAurora());
		operators.add(new ElegirUsarSatelite());
		operators.add(new ElegirPelear());
		for(int i=1; i<=29; i++) {
			operators.add(new IrANodoN(i));
		}
		
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
	
		/*
		 * 1 --> dfs
		 * 2 --> bfs
		 * 3 --> cu
		 * 4 --> greedy
		 * 5 --> a*
		 */
		Strategy strategy = this.elegirEstrategia(this.nroEstrategia);
		Search busqueda = new Search(strategy);
		busqueda.setVisibleTree(Search.GRAPHVIZ_TREE);
		 this.setSolver(busqueda);
		 Action accionSeleccionada = null;
	        try {
	            accionSeleccionada = this.getSolver().solve(new Object[]{this.getProblem()});
	            searchActions.add(new Pair<Action, Double>(accionSeleccionada, jugadorState.getEnergia()));
	            if (Datos.niveles == null) Datos.niveles = new ArrayList<>();
	            Datos.niveles.add(((EstadoJugador) this.getAgentState()).getNivel());
	           System.out.println("Accion seleccionadaa: " + accionSeleccionada.toString());
	           System.out.println("Costo: " + jugadorState.getCosto());
	        } catch (Exception ex) {
	            System.out.println(ex.getMessage());
	        }
	        return accionSeleccionada;
		
	}
	
	public Strategy elegirEstrategia(Integer estrategia) {
		Strategy retorno = null;
		switch(estrategia) { 
		case 1: {//dfs
		    retorno = new DepthFirstSearch();
			break; 
		}
		case 2: {//bfs
			retorno = new BreathFirstSearch();
			break;
		}
		case 3: {//costo uniforme
			 IStepCostFunction costFunction = new FuncionCosto();
		     retorno = new UniformCostSearch(costFunction);
			break;
		}
		case 4:{ //greedy
			Heuristica heuristica = new Heuristica();
			retorno = new GreedySearch(heuristica);
			break;
		}
		case 5: {//a+
			Heuristica heuristica = new Heuristica();
			((JuntarPokebola) operators.get(0)).setCost(0.0);
			((ElegirHuir) operators.get(1)).setCost(2.0);
			((ElegirUsarRayoSolar) operators.get(2)).setCost(0.0);
			((ElegirUsarRayoMeteorico) operators.get(3)).setCost(1.0);
			((ElegirUsarRayoAurora) operators.get(4)).setCost(2.0);
			((ElegirUsarSatelite) operators.get(5)).setCost(0.0);
			((ElegirPelear) operators.get(6)).setCost(4.0);
			for(int i=7; i<operators.size(); i++) {
				((IrANodoN) operators.get(i)).setCost(1.0);
			}
			
			IStepCostFunction costFunction = new FuncionCosto();
			retorno = new AStarSearch(costFunction, heuristica);
		}
			
			break;
		}
		//problema del dfs: se queda iterando entre dos nodos.
		
		return retorno;
	}

	
}
