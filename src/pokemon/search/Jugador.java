package pokemon.search;


import java.util.Vector;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.Problem;
import frsf.cidisi.faia.agent.search.SearchAction;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;
import pokemon.search.actions.IrANodoN;

public class Jugador extends SearchBasedAgent {

	public Jugador() {
	ObjetivoJugador jugadorGoal = new ObjetivoJugador();
	EstadoJugador jugadorState = new EstadoJugador();
	this.setAgentState(jugadorState);
	Vector<SearchAction> operators = new Vector<SearchAction>();
	operators.add(new IrANodoN(11));
	//TODO: agregar operadores
	Problem problem = new Problem(jugadorGoal, jugadorState, operators);
	 this.setProblem(problem);
	}

	@Override
	public void see(Perception p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Action selectAction() {
		// TODO Auto-generated method stub
		return null;
	}


}

