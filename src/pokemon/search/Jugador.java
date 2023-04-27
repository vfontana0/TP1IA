package pokemon.search;

import frsf.cidisi.faia.agent.search.SearchBasedAgent;

public class Jugador extends SearchBasedAgent {

	ObjetivoJugador jugadorGoal = new ObjetivoJugador();
	
	EstadoJugador jugadorState = new EstadoJugador();
	this.setAgentState(jugadorState);
	
	Vector<SearchAction> operators = new Vector<SearchAction>();
	//TODO: agregar operadores
	
	Problem problem = new Problem(jugadorGoal, jugadorState, operators);
	this.setProblem(problem);
}
