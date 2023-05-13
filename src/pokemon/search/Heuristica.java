package pokemon.search;

import frsf.cidisi.faia.solver.search.IEstimatedCostFunction;
import frsf.cidisi.faia.solver.search.NTree;

public class Heuristica implements IEstimatedCostFunction {

/* La heuristica se calcula como la distancia mínima del nodo actual al final 
 * 
 */
		@Override
		public double getEstimatedCost(NTree node) {
			EstadoJugador estado = (EstadoJugador) node.getAgentState();
			Double heuristica = estado.getMapa().getHeuristica(estado);
			System.out.println("Heuristica: " + heuristica);
			return heuristica;
		}
    
	}
