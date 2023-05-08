package domain;

public class Pokebola {

	Nodo posicion; //nodo en el que esta la pokebola
	Double puntos; //puntos que gana el agente al juntarla.
	public Nodo getPosicion() {
		return posicion;
	}
	public void setPosicion(Nodo posicion) {
		this.posicion = posicion;
	}
	public Double getPuntos() {
		return puntos;
	}
	public void setPuntos(Double puntos) {
		this.puntos = puntos;
	}
	
	public Pokebola clone() {
		Pokebola retorno = new Pokebola();
		retorno.setPuntos(this.getPuntos());
		//si anda agregar posicion
		return retorno;
	}

}

