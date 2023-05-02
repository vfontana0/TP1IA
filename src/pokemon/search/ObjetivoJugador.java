package pokemon.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoJugador extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		EstadoJugador estado = (EstadoJugador) agentState;
		// condicion de ganar: que este en el nodo 11 y que el pokemon maestro no este vivo.
		//System.out.println("Energia: " + estado.getEnergia() + " Nodo: " + estado.getUbicacion().toString() + "Muerto maestro? " + estado.getMaestroMuerto());

		return estado.getEnergia() > 0 && estado.getUbicacion().getNumero()==11 && estado.getMaestroMuerto();
	}


}
