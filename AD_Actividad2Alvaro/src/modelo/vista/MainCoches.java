package modelo.vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoches;
import modelo.negocio.GestorPasajeros;

public class MainCoches {

	public static void main(String[] args) {
		
		GestorCoches gestorCoches = new GestorCoches();
		GestorPasajeros gestorPasajeros = new GestorPasajeros();
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		Coche coche = new Coche();
		Pasajero pasajero = new Pasajero();
		System.out.println("Bienvenido al gestor de la base de datos de coches.");
		do {
			menu();
			opcion = sc.nextInt();
			switch(opcion) {
			case 1:
				coche = new Coche();
				System.out.println("Introduzca la marca del coche que quiere dar de alta: ");
				coche.setMarca(sc.next());
				System.out.println("Introduzca el modelo del coche que quiere dar de alta: ");
				coche.setModelo(sc.next());
				System.out.println("Introduzca el año del coche que quiere dar de alta: ");
				coche.setYear(sc.nextInt());
				System.out.println("Introduzca el número de kilómetros del coche que quiere dar de alta: ");
				coche.setKm(sc.nextInt());
				if(gestorCoches.alta(coche)) {
					System.out.println("El coche ha sido añadido correctamente a la base de datos.");
				}else {
					System.out.println("El coche no se ha podido dar de alta en la base de datos.");
				}
				break;
			case 2:
				System.out.println("Introduzca el número de ID del coche a eliminar: ");
				int idCoche = sc.nextInt();
				if(gestorCoches.baja(idCoche)) {
					System.out.println("El coche ha sido dado de baja correctamente de la base de datos.");
				}else {
					System.out.println("El coche no se ha podido eliminar de la base de datos.");
				}
				break;
			case 3:
				System.out.println("Introduzca el número de ID del coche que está buscando: ");
				idCoche = sc.nextInt();
				coche = gestorCoches.buscar(idCoche);
				if(coche!=null) {
					System.out.println(coche);
				}else {
					System.out.println("No se ha encontrado el coche en la base de datos.");
				}
				break;
			case 4:
				System.out.println("Introduzca el ID del coche que desea modificar: ");
				coche.setId(sc.nextInt());
				System.out.println("Introduzca la nueva marca del coche modificado: ");
				coche.setMarca(sc.next());
				System.out.println("Introduzca el nuevo modelo del coche modificado: ");
				coche.setModelo(sc.next());
				System.out.println("Introduzca el nuevo año del coche modificado: ");
				coche.setYear(sc.nextInt());
				System.out.println("Introduzca el nuevo número de kilómetros del coche modificado: ");
				coche.setKm(sc.nextInt());
				if(gestorCoches.modificar(coche)) {
					System.out.println("El coche ha sido modificado correctamente en la base de datos.");
				}else {
					System.out.println("El coche no se ha podido modificar en la base de datos.");
				}
				break;
			case 5:
				System.out.println("Obteniendo el listado de todos los coches de la base de datos...");
				List<Coche> listaCoches = new ArrayList<Coche>();
				listaCoches = gestorCoches.listar();
				if(listaCoches!=null) {
					for(int i=0;i<listaCoches.size();i++) {
						System.out.println(listaCoches.get(i));
					}
				}else {
					System.out.println("No se han podido listar los coches de la base de datos.");
				}
				break;
			case 6:
				int opcionSubmenu = 0;
				do {
					submenu();
					opcionSubmenu = sc.nextInt();
					switch(opcionSubmenu) {
					case 1:
						pasajero = new Pasajero();
						System.out.println("Introduzca el nombre del pasajero que quiere dar de alta: ");
						pasajero.setNombre(sc.next());
						System.out.println("Introduzca la edad del pasajero que quiere dar de alta: ");
						pasajero.setEdad(sc.nextInt());
						System.out.println("Introduzca el peso del pasajero que quiere dar de alta: ");
						pasajero.setPeso(sc.nextDouble());
						
						if(gestorPasajeros.alta(pasajero)) {
							System.out.println("El pasajero ha sido añadido correctamente a la base de datos.");
						}else {
							System.out.println("El pasajero no se ha podido dar de alta en la base de datos.");
						}
						break;
					case 2:
						System.out.println("Introduzca el número de ID del pasajero a eliminar: ");
						int idPasajero = sc.nextInt();
						if(gestorPasajeros.baja(idPasajero)) {
							System.out.println("El pasajero ha sido dado de baja correctamente de la base de datos.");
						}else {
							System.out.println("El pasajero no se ha podido eliminar de la base de datos.");
						}
						break;
					case 3:
						System.out.println("Introduzca el número de ID del pasajero que está buscando: ");
						idPasajero = sc.nextInt();
						pasajero = gestorPasajeros.buscar(idPasajero);
						if(pasajero!=null) {
							System.out.println(pasajero);
						}else {
							System.out.println("No se ha encontrado el pasajero en la base de datos.");
						}
						break;
					case 4:
						System.out.println("Obteniendo el listado de todos los pasajeros de la base de datos...");
						List<Pasajero> listaPasajeros = new ArrayList<Pasajero>();
						listaPasajeros = gestorPasajeros.listar();
						if(listaPasajeros!=null) {
							for(int i=0;i<listaPasajeros.size();i++) {
								System.out.println(listaPasajeros.get(i));
							}
						}else {
							System.out.println("No se han podido listar los pasajeros de la base de datos.");
						}
						break;
					case 5:
						System.out.println("Introduzca el número de ID del pasajero que quiere añadir a un coche:");
						idPasajero = sc.nextInt();
						System.out.println("Introduzca el número de ID del coche al que quiere añadir el pasajero:");
						idCoche = sc.nextInt();
						if(gestorPasajeros.pasajeroACoche(idPasajero, idCoche)) {
							System.out.println("El pasajero ha sido añadido correctamente al coche indicado.");
						}else {
							System.out.println("El pasajero no ha podido ser añadido al coche indicado.");
						}
						break;
					case 6:
						System.out.println("Indroduzca el número de ID del pasajero que quiera eliminar de un coche:");
						idPasajero = sc.nextInt();
						if(gestorPasajeros.pasajeroEliminarCoche(idPasajero)) {
							System.out.println("El pasajero ha sido eliminado correctamente del coche que tenía asignado.");
						}else {
							System.out.println("El pasajero no ha podido ser eliminado del coche.");
						}
						break;
					case 7:
						System.out.println("Introduzca el ID del coche del que quiere listar sus pasajeros: ");
						idCoche = sc.nextInt();
						listaPasajeros = gestorPasajeros.listarPasajerosDeCoche(idCoche);
						if(listaPasajeros!=null) {
							for(int i=0;i<listaPasajeros.size();i++) {
								System.out.println(listaPasajeros.get(i));
							}
						}else {
							System.out.println("No se han podido listar los pasajeros de la base de datos.");
						}
						break;
					case 0:
						break;
					default:
						System.out.println("El número de opción introducido no es válido");
						break;
					}
				}while(opcionSubmenu!=0);
				break;
			case 0:
				break;
			default:
				System.out.println("El número de opción introducido no es válido.");
				break;
			}
		}while(opcion!=0);
		
		System.out.println("Gracias por utilizar el gestor de la base de datos de coches. Hasta pronto.");
		sc.close();

	}

	private static void menu() {
		System.out.println("Escriba el número de la opción elegida:");
		System.out.println("1. Añadir nuevo coche.");
		System.out.println("2. Eliminar coche.");
		System.out.println("3. Consultar datos de un coche.");
		System.out.println("4. Modificar datos de coche.");
		System.out.println("5. Listar todos los coches.");
		System.out.println("6. Gestión de pasajeros.");
		System.out.println("0. Salir del programa.");
	}
	
	private static void submenu() {
		System.out.println("Para gestionar los pasajeros, escriba el número de la opción elegida:");
		System.out.println("1. Crear nuevo pasajero.");
		System.out.println("2. Eliminar pasajero.");
		System.out.println("3. Consultar datos de un pasajero.");
		System.out.println("4. Listar todos los pasajeros.");
		System.out.println("5. Añadir un pasajero a un coche.");
		System.out.println("6. Eliminar un pasajero de un coche.");
		System.out.println("7. Listar todos los pasajeros de un coche.");
		System.out.println("0. Volver al menú anterior.");
	}
}
