package modelo.entidad;

import java.util.Objects;

public class Coche {
	
	private int id;
	private String marca, modelo;
	private int añofabricacion;
	private double km;

	
	public Coche() {
		super();
		
	}


	public Coche(int id, String marca, String modelo, int añofabricacion, double km) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.añofabricacion = añofabricacion;
		this.km = km;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getAñofabricacion() {
		return añofabricacion;
	}


	public void setAñofabricacion(int añofabricacion) {
		this.añofabricacion = añofabricacion;
	}


	public double getKm() {
		return km;
	}


	public void setKm(double km) {
		this.km = km;
	}


	@Override
	public String toString() {
		return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", añofabricacion=" + añofabricacion
				+ ", km=" + km + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(añofabricacion, id, km, marca, modelo);
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
		return añofabricacion == other.añofabricacion && id == other.id
				&& Double.doubleToLongBits(km) == Double.doubleToLongBits(other.km)
				&& Objects.equals(marca, other.marca) && Objects.equals(modelo, other.modelo);
	}



}
