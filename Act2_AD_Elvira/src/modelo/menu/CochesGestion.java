package modelo.menu;

import java.util.List;
import java.util.Scanner;

import modelo.dao.CocheDaoImpl;
import modelo.dao.CochesConPasajerosDaoImpl;
import modelo.dao.PasajeroDaoImpl;
import modelo.entidad.Coche;
import modelo.entidad.CochesConPasajeros;
import modelo.entidad.Pasajero;


public class CochesGestion {

	public static void main(String[] args) {
		
		Coche c = new Coche();
		CocheDaoImpl cdao = new CocheDaoImpl();
		
		Pasajero p = new Pasajero();
		PasajeroDaoImpl pdao = new PasajeroDaoImpl();
		
		CochesConPasajeros cdp = new CochesConPasajeros();
		CochesConPasajerosDaoImpl cpdao= new CochesConPasajerosDaoImpl();
		
		Scanner sc = new Scanner (System.in);
		int id, año, idPa, edad;
		String marca, modelo, nombre;
		double km, peso;
		
		int opcion = 0;
		opcion = pintarMenu();
		
		
		while(opcion !=7) {
			
			switch(opcion) {
			case 1://Alta coche
				System.out.println("Alta Coche");
				System.out.println("Introduzca el id, marca, modelo, año y km del coche");
				System.out.println(cdao.altaCoche(c = new Coche(id = sc.nextInt(), marca=sc.next(), modelo=sc.next(), año=sc.nextInt(), km=sc.nextDouble())));
				break;
			case 2://Borrar coche
				System.out.println("Eliminar Coche");
				System.out.println("Introduzca el id del coche a borrar");
				System.out.println(cdao.eliminarCoche(id=sc.nextInt()));	
				break;
			case 3://Consultar coche por ID
				System.out.println("Consultar coche por Id");
				System.out.println("Introduzca el id a consular");
				System.out.println(cdao.buscarCoche(id=sc.nextInt()));
				break;
			case 4://Modificar Coche por ID
				
				break;
			case 5://Listado de Coches
				System.out.println("Listado de coches");
				System.out.println(cdao.listaCoches());
				break;
			case 6: //Submenu Gestion Pasajeros
				 
				int opcion2 = 0;
				opcion = pintarSubmenu();
				
				while(opcion !=8) {
				
					switch(opcion) {
					case 1: 
						//Crear nuevo pasajero
						System.out.println("Alta Pasajero");
						System.out.println("Introduzca el id, nombre, edad y peso del pasajero");
						System.out.println(pdao.altaPasajero(p = new Pasajero(idPa=sc.nextInt(), nombre=sc.next(), edad=sc.nextInt(), peso=sc.nextDouble())));
						break;
					case 2:
						//Borrar pasajero
						System.out.println("Eliminar Pasajero");
						System.out.println("Introduzca el id del pasajero a borrar");
						System.out.println(pdao.eliminarPasajero(idPa=sc.nextInt()));
						break;
					case 3:
						//Consultar pasajero por id
						System.out.println("Consultar pasajero por Id");
						System.out.println("Introduzca el id a consular");
						System.out.println(pdao.buscarPasajero(idPa=sc.nextInt()));
						break;
					case 4:
						//Listado de pasajeros
						System.out.println("Listado de pasajeros");
						System.out.println(pdao.listaPasajeros());
						break;
					case 5:
						coches();
						pasajeros();
						//Añadir pasajero a coche
						System.out.println("Introduce el id del pasajeroo y el id del coche para asignar");
						System.out.println(cpdao.asignarPasajeroCoche(idPa=sc.nextInt(), id=sc.nextInt()));
						break;
					case 6:
						//Eliminar pasajero del coche 
						coches();
						pasajeros();
						cochespasajeros();
						System.out.println("Introcue el id del pasajero a borrar");
						System.out.println(cpdao.EliminarPasajeroDeCoche(idPa=sc.nextInt()));
						break;
					case 7:
						//Listar pasajeros por coche
						System.out.println(cpdao.pasajerosDelCoche(id=sc.nextInt()));
						break;
					case 8:
						opcion = pintarMenu();
						break;
			
					default:
						System.out.println("opcion erronea ...");
					}
					opcion = pintarSubmenu();
				}

				
				
				break;
			case 7:
				
				break;
	
	
			default:
				System.out.println("opcion erronea ...");
			}
			opcion = pintarMenu();
		}

		System.out.println("FIN DE PROGRAMA");
}		

	
	public static int pintarMenu() {
		
		Scanner leer= new Scanner (System.in);
		int opcion = 0;
		System.out.println("--------- MENU ---------");
		System.out.println("1.-Alta Coche");
		System.out.println("2.-Borrar Coche");
		System.out.println("3.-Consultar Coche por ID");
		System.out.println("4.-Modificar Coche por ID");
		System.out.println("5.-Listado de Coches");
		System.out.println("6.-Gestion de Pasajeros");
		System.out.println("7.-Terminar Programa");
		System.out.println("teclea una opn del 1 al 6, 7 para salir");
		opcion = leer.nextInt();
		
		while(opcion<1 || opcion > 7) {
			System.out.println("del 1 al 7");
			opcion = leer.nextInt();
		}
		
		return opcion;
		}
	
	public static int pintarSubmenu() {
		
		Scanner leer= new Scanner (System.in);
		int opcion = 0;
		System.out.println("-------- SUBMENU --------");
		System.out.println("1.-Crear Nuevo Pasajero");
		System.out.println("2.-Borrar Pasajero");
		System.out.println("3.-Consultar Pasajero por ID");
		System.out.println("4.-Listar Pasajeros");
		System.out.println("5.-Añadir Pasajero a Coche");
		System.out.println("6.-Eliminar Pasajero de Coche");
		System.out.println("7.-Listar Pasajeros de Coche con el ID");
		System.out.println("8.-Volver al menu");
		System.out.println("teclea una opn del 1 al 7, 8 para salir al menu principal");
		opcion = leer.nextInt();
		
		while(opcion<1 || opcion > 8) {
			System.out.println("del 1 al 8");
			opcion = leer.nextInt();
		}
		return opcion;
		}	
	
	public static void coches() {
		//Listar los coches disponibles
		CocheDaoImpl cdao = new CocheDaoImpl();
		System.out.println("Lista de coches disponibles:");
		System.out.println("-----------------------------------------------------------------------------");
		List<Coche> listaCoches = cdao.listaCoches();
		//recorremos la lista con un for, para pintar bien la tabla
		for (Coche c : listaCoches) {
		    System.out.println("Id: " + c.getId() + ", Marca: " + c.getMarca() + ", Modelo: " + c.getModelo() + ", Año de fabricación: " + c.getAñofabricacion() + ", Kilometraje: " + c.getKm());
		}
	}
	
	public static void pasajeros() {
		//Listamos los pasajeros
		PasajeroDaoImpl pdao = new PasajeroDaoImpl();
		System.out.println("Lista de pasajeros disponibles:");
		System.out.println("-----------------------------------------------------------------------------");
		List<Pasajero> listaPasajeros = pdao.listaPasajeros();
		//recorremos la lista
		for(Pasajero p :listaPasajeros) {
			System.out.println("Id: " + p.getId()+ ", Nombre: " + p.getNombre() + ", Edad: " + p.getEdad() + ", Peso: " + p.getPeso());
		}
	}
	
	public static void cochespasajeros() {
		//Listamos la tabla de los pasajeros con los coches asociados
		CochesConPasajerosDaoImpl cpdao= new CochesConPasajerosDaoImpl();
		System.out.println("Tabla de asociaciones");
		List<CochesConPasajeros> cplista = cpdao.listaCochesPasajeros();
		//recorremos la lista
		for(CochesConPasajeros cp : cplista) {
			System.out.println("Id_pasajero: " + cp.getId() + ", Id_coche: " + cp.getId());
		}
	}
	}
