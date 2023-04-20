package pokemon.search;

import frsf.cidisi.faia.agent.Action;
import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.agent.search.SearchBasedAgent;

public class Agente extends SearchBasedAgent {
	public Agente() {
		/* TODO
		 * Crear el objetivo del agente (ObjetivoPokemon)
		 * Crear el estado del agente
		 * Crear vector de operadores del agente
		 * Crear el problema con el objetivo, estado y operadores
		 * 
		 */
	}
	
	@Override
    public void see(Perception p) {
        this.getAgentState().updateState(p);
    }

	@Override
	public Action selectAction() {
		// TODO Generar logica de como tomar la decision (supongo q podemos seguir la de la aspiradora)
		return null;
	}

}
