package modelo.entidad;

import java.util.Objects;

public class Pasajero {
	
	//atributos
	private int id, edad;
	private String nombre;
	private double peso;
	private Coche coche;
	
	//constructor
	public Pasajero(int id, int edad, String nombre, double peso, Coche coche) {
		super();
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
		this.peso = peso;
		this.coche = coche;
	}
	
	public Pasajero() {
		super();
	}
	
	
	//toString
	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", edad=" + edad + ", nombre=" + nombre + ", peso=" + peso + ", coche=" + coche
				+ "]\n";
	}
	
	//getters y setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
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
		Pasajero other = (Pasajero) obj;
		return id == other.id;
	}
	
	
}
