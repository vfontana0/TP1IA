package domain;

public class Poder {

private String nombre;
private Integer cantCiclos;
private Boolean puedoUsar;
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public Integer getCantCiclos() {
	return cantCiclos;
}
public void setCantCiclos(Integer cantCiclos) {
	this.cantCiclos = cantCiclos;
}
public Boolean getPuedoUsar() {
	return puedoUsar;
}
public void setPuedoUsar(Boolean puedoUsar) {
	this.puedoUsar = puedoUsar;
}

public Poder(String nombre2, int cantCiclos2, boolean puedoUsar2) {
	this.nombre = nombre;
	this.cantCiclos = cantCiclos;
	this.puedoUsar = puedoUsar;// TODO Auto-generated constructor stub
}

public Poder clone() {
	return new Poder(this.getNombre(), this.getCantCiclos(), this.getPuedoUsar());
}

}
