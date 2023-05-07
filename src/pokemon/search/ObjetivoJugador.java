package pokemon.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoJugador extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		EstadoJugador estado = (EstadoJugador) agentState;
		//System.out.println("Chequeo que en el nodo 11 haya pokemon. Hay? --> " + estado.getMapa().getVertex(11).getTienePokemon());
		return estado.getEnergia() > 0 && estado.getUbicacion().getNumero()==11 && !estado.getMapa().getVertex(11).getTienePokemon();
		//se chequea antes de percibir entonces el nodo 11 no tiene pokemon
	}
	@Override
	public String toString() {
		return "El jugador vencio al maestro";
	}


}
