package pokemon.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoJugador extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		EstadoJugador estado = (EstadoJugador) agentState;
		if(estado.getEnergia() > 0 && estado.getUbicacion().getNumero()==18 && !estado.getMapa().getVertex(18).getTienePokemon()) {
			estado.gano = true;
			return true;
		} else 
			return false;
	}
	@Override
	public String toString() {
		return "El jugador vencio al mestro";
	}
	


}