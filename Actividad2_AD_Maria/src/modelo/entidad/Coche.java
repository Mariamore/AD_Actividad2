package modelo.entidad;

import java.util.ArrayList;
import java.util.Objects;

public class Coche {
	
	//atributos
	
	private int id, añoFabricacion;
	private String marca, modelo;
	private double kms;
	private ArrayList<Pasajero> listaPasajeros;
	
	//Constructor

	public Coche(int id, int añoFabricacion, String marca, String modelo, double kms, ArrayList<Pasajero> listaPasajeros) {
		super();
		this.id = id;
		this.añoFabricacion = añoFabricacion;
		this.marca = marca;
		this.modelo = modelo;
		this.kms = kms;
		this.listaPasajeros = listaPasajeros;
	}

	public Coche() {
		super();
	}
	
	//toString
	
	@Override
	public String toString() {
		return "Coche [id=" + id + ", añoFabricacion=" + añoFabricacion + ", marca=" + marca + ", modelo=" + modelo
				+ ", kms=" + kms + ", p=" + listaPasajeros + "]\n";
	}
	

	//getters y setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getAñoFabricacion() {
		return añoFabricacion;
	}

	public void setAñoFabricacion(int añoFabricacion) {
		this.añoFabricacion = añoFabricacion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getKms() {
		return kms;
	}

	public void setKms(double kms) {
		this.kms = kms;
	}
	
	public ArrayList<Pasajero> getP() {
		return listaPasajeros;
	}

	public void setP(ArrayList<Pasajero> p) {
		this.listaPasajeros = p;
	}

	//equals y hashCode
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return id == other.id;
	}
	
	
}
