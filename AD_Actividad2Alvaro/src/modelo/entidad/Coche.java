package modelo.entidad;

import java.util.ArrayList;
import java.util.List;

public class Coche {
	
	private int id;
	private String marca;
	private String modelo;
	private int year;
	private int km;
	private ArrayList<Pasajero> listaPasajeros;
	
	
	public Coche() {
		super();
	}

	public Coche(int id, String marca, String modelo, int year, int km) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.year = year;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public ArrayList<Pasajero> getListaPasajeros() {
		return listaPasajeros;
	}

	public void setListaPasajeros(List<Pasajero> list) {
		this.listaPasajeros = (ArrayList<Pasajero>) list;
	}

	@Override
	public String toString() {
		if(this.listaPasajeros != null) {
			return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", year=" + year
					+ ", km=" + km + "Pasajeros: " + listaPasajeros + "]";
		}else {
			return "Coche [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", year=" + year + ", km=" + km + "]";
		}
	}
	
	

}
