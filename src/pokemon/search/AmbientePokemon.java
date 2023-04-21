package pokemon.search;

import frsf.cidisi.faia.agent.Perception;
import frsf.cidisi.faia.environment.Environment;

public class AmbientePokemon extends Environment{
	    public AmbientePokemon() {
	        // Create the environment state
	        this.environmentState = new EstadoAmbiente();
	    }

		@Override
		public Perception getPercept() {
			// TODO Auto-generated method stub
			return null;
		}
		
}
