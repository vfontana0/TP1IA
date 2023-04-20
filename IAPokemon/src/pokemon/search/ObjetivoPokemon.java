package pokemon.search;

import frsf.cidisi.faia.agent.search.GoalTest;
import frsf.cidisi.faia.state.AgentState;

public class ObjetivoPokemon extends GoalTest {

	@Override
	public boolean isGoalState(AgentState agentState) {
		//condicion de ganar, sería que el nodo sea el 11 y haya vencido el maestroS
		return false;
	}

}
