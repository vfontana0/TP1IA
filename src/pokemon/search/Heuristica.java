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
			Integer heuristica = estado.getMapa().getHeuristica(estado.getUbicacion());
			return heuristica;
		}
    
	}
