package src.pokemon.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoJugador extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		EstadoJugador estado = (EstadoJugador) agentState;
		return estado.getEnergia() > 0 && estado.getUbicacion().getNumero()==11 && !estado.getMapa().getVertex(11).getTienePokemon();
	}
	@Override
	public String toString() {
		return "El jugador vencio al mestro";
	}
	


}