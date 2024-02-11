package modelo.entidad;

import java.util.Objects;

public class CochesConPasajeros {
	
	private int idPa;
	private int id;
	
	
	
	public CochesConPasajeros() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CochesConPasajeros(int idPa, int id) {
		super();
		this.idPa = idPa;
		this.id = id;
	}



	public int getIdPa() {
		return idPa;
	}



	public void setIdPa(int idPa) {
		this.idPa = idPa;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "CochesConPasajeros [idPa=" + idPa + ", id=" + id + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, idPa);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CochesConPasajeros other = (CochesConPasajeros) obj;
		return id == other.id && idPa == other.idPa;
	}


	
}
