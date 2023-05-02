package domain;

public class Pokemon {

	Nodo actual; //nodo en el que se encuentra el pokemon adversario
	Double energia; //energia que tiene el pokemon adversario
	Boolean esMaestro;
	Integer ciclosParaMoverse;
	Boolean vivo; //indica si el pokemon esta vivo o no
	public Nodo getActual() {
		return actual;
	}
	public void setActual(Nodo actual) {
		this.actual = actual;
	}
	public Double getEnergia() {
		return energia;
	}
	public void setEnergia(Double energia) {
		this.energia = energia;
	}
	public Boolean getVivo() {
		return vivo;
	}
	public void setVivo(Boolean vivo) {
		this.vivo = vivo;
	}
	public Boolean getEsMaestro() {
		return esMaestro;
	}
	public void setEsMaestro(Boolean esMaestro) {
		this.esMaestro = esMaestro;
	}
	public Integer getCiclosParaMoverse() {
		return ciclosParaMoverse;
	}
	public void setCiclosParaMoverse(Integer ciclosParaMoverse) {
		this.ciclosParaMoverse = ciclosParaMoverse;
	}
	
	public Pokemon clone() {
		Pokemon retorno = new Pokemon();
		retorno.setCiclosParaMoverse(this.getCiclosParaMoverse());
		retorno.setVivo(this.getVivo());
		retorno.setEnergia(this.getEnergia());
		retorno.setEsMaestro(this.esMaestro);
		//si anda agregar posicion
		return retorno;
	}
	
	
	
	

	
}
