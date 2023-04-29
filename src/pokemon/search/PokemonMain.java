package pokemon.search;


import frsf.cidisi.faia.exceptions.PrologConnectorException;
import frsf.cidisi.faia.simulator.SearchBasedAgentSimulator;

public class PokemonMain {
    public static void main(String[] args) throws PrologConnectorException {
        Jugador agent = new Jugador();

        AmbientePokemon environment = new AmbientePokemon();
        SearchBasedAgentSimulator simulator =
                new SearchBasedAgentSimulator(environment, agent);
        
        simulator.start();
    }
}
