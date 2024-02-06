package presentacion;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;


public class Main {

	private static Scanner sc;
	static {
		sc = new Scanner(System.in);
	}
	public static void main(String[] args) {
		GestorCoche gestorCoche = new GestorCoche();
		GestorPasajero gestorPasajero = new GestorPasajero();
		
		int opcion = cargarMenu();
		
		while(opcion!=7) {
			switch(opcion) {
			case 1:
				Coche coche = pedirDatosCoche();
				int alta = gestorCoche.altaCoche(coche);
				if (alta == 1) {
					System.out.println("El coche se ha dado de alta.");
				} else if(alta == 2) {
					System.out.println("El coche no se ha podido dar de alta.");
				} else if (alta ==3) {
					System.out.println("El coche no se ha dado de alta porque no se ha introducido el modelo y/o la marca");
				}
				break;
			case 2:
				System.out.println("Introduce el ID del coche a borrar:");
				int id = sc.nextInt();
				boolean borrado = gestorCoche.bajaCoche(id);
				if(borrado)
					System.out.println("El coche se ha eliminado");
				else
					System.out.println("El coche no se ha podido eliminar");
				break;
			case 3:
				System.out.println("Introduce el ID del coche a buscar");
				id = sc.nextInt();
				coche = gestorCoche.consultar(id);
				if (coche !=null)
					System.out.println(coche);
				else 
					System.out.println("No existe un coche con ese ID");
				break;
			case 4:
				System.out.println("Introduce el id del coche a modificar:");
				id = sc.nextInt();
				coche = pedirDatosCoche();
				coche.setId(id);
				int modificar = gestorCoche.modificar(coche);
				if (modificar == 1) {
					System.out.println("El coche se ha dado de modificado.");
				} else if(modificar == 2) {
					System.out.println("El coche no se ha podido modificar.");
				} else if (modificar ==3) {
					System.out.println("El coche no se ha modificado porque no se ha introducido el modelo y/o la marca");
				}
				break;
			case 5:
				ArrayList<Coche> listaCoches = gestorCoche.listar();
				System.out.println(listaCoches);
				break;
			case 6:
				int opcion1 = cargarMenuPasajeros();
				while(opcion1!=8) {
					switch(opcion1) {
					case 1:
						Pasajero pasajero = pedirDatosPasajero();
						boolean altaP = gestorPasajero.alta(pasajero);
						if(altaP)
							System.out.println("El pasajero se ha dado de alta.");
						else
							System.out.println("El pasajero no se ha podido dar de alta.");
						break;
					case 2:
						System.out.println("Introduce el ID del pasajero a borrar:");
						id = sc.nextInt();
						borrado = gestorPasajero.borrar(id);
						if (borrado)
							System.out.println("El pasajero se ha borrado de la base de datos.");
						else
							System.out.println("No se ha podido borrar al pasajero.");
						break;
					case 3:
						System.out.println("Introduce el ID del pasajero que quieres obtener:");
						id = sc.nextInt();
						pasajero = gestorPasajero.consultar(id);
						if (pasajero != null)
							System.out.println(pasajero);
						else
							System.out.println("No existe un pasajero con ese ID.");
						break;
					case 4:
						ArrayList<Pasajero> listaPasajeros = gestorPasajero.listar();
						if(listaPasajeros.isEmpty())
							System.out.println("No existen pasajeros en la base de datos");
						else
							System.out.println(listaPasajeros);
						break;
					case 5:
						System.out.println("Estos son los coches disponibles:");
						listaCoches = gestorCoche.cochesDisponibles();
						System.out.println(listaCoches);
						System.out.println("Introduce el ID del pasajero:");
						int idPasajero = sc.nextInt();
						System.out.println("Introduce el ID del coche:");
						int idCoche = sc.nextInt();
						boolean añadido = gestorPasajero.añadirPasajero(idPasajero, idCoche);
						if (añadido)
							System.out.println("Se ha añadido el pasajero al coche.");
						else
							System.out.println("No se ha podido añadir el pasajero al coche.");
						break;
					case 6:
						System.out.println("Los siguientes coches tienen pasajeros:");
						listaCoches = gestorCoche.listarCochesConPasajero();
						System.out.println(listaCoches);
						System.out.println("Introduce el ID del pasajero a eliminar del coche:");
						id = sc.nextInt();
						borrado = gestorPasajero.eliminarPasajeroCoche(id);
						if(borrado)
							System.out.println("El pasajero se ha eliminado correctamente del coche.");
						else
							System.out.println("No se ha podido eliminar el pasajero del coche");
						break;
					case 7:
						System.out.println("Introduce el ID del coche cuyos pasajeros quiere ver:");
						id = sc.nextInt();
						listaPasajeros = gestorPasajero.listarPasajerosCoche(id);
						if(listaPasajeros.isEmpty())
							System.out.println("El coche con ID " + id + " no tiene asignados pasajeros o no está almacenado en la base de datos.");	
						else
							System.out.println(listaPasajeros);
						break;
					default:
							System.out.println("Error");
					}
					opcion1 = cargarMenuPasajeros();
				}
					
				break;
			default:
				System.out.println("Error");
			}
			opcion = cargarMenu();
		}
		if(opcion==7)
			System.out.println("Cerrando programa...");
		
	}
	/**
	 * Método que carga por consola un menú para que el usuario pueda elegir qué acción llevar
	 * a cabo
	 * @return el número de la opción elegida
	 */
	public static int cargarMenu() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		 System.out.println(" ------ MENÚ ------- ");
		 System.out.println("1. Añadir nuevo coche");
		 System.out.println("2. Borrar un coche por ID");
		 System.out.println("3. Obtener un coche por ID");
		 System.out.println("4. Modificar coche por ID");
		 System.out.println("5. Listar coches");
		 System.out.println("6. Gestión de pasajeros");
		 System.out.println("7. Salir");
		 System.out.println("---------------------");
		 
		 while(opcion > 7 || opcion < 1) {
			 System.out.println("Introduce el número de la opción elegida:");
			 try {
			 opcion = sc.nextInt();
			 } catch (NumberFormatException e) {
				 System.out.println("Error -> Introduce un dato de tipo numérico");
			 } catch (Exception e) {
				 System.out.println("Error genérico");
			 }
			 
		 }
		 return opcion;
	}
	
	/**
	 * Método que carga por consola un menú para que el usuario pueda elegir qué acción llevar
	 * a cabo
	 * @return el número de la opción elegida
	 */
	public static int cargarMenuPasajeros() {
		int opcion = 0;
		
		 System.out.println(" ------ GESTIÓN DE PASAJEROS ------- ");
		 System.out.println("1. Añadir nuevo pasajero");
		 System.out.println("2. Borrar un pasajero por ID");
		 System.out.println("3. Obtener un pasajero por ID");
		 System.out.println("4. Listar pasajeros");
		 System.out.println("5. Añadir pasajero a coche");
		 System.out.println("6. Eliminar pasajero de coche");
		 System.out.println("7. Listar todos los pasajeros de un coche");
		 System.out.println("8. Volver al menú principal");
		 System.out.println("--------------------------------------");
		
		 while(opcion > 8 || opcion < 1) {
			 System.out.println("Introduce el número de la opción elegida:");
			 try {
			 opcion = sc.nextInt();
			 } catch (NumberFormatException e) {
				 System.out.println("Error -> Introduce un dato de tipo numérico");
			 } catch (Exception e) {
				 System.out.println("Error genérico");
			 }
			 
		 }
		 return opcion;
	}
	
	/**
	 * Método que permite al usuario introducir los valores de los atributos de un objeto de 
	 * tipo coche
	 * @return el objeto de tipo coche con los valores de los atributos asignados por 
	 * el usuario
	 */
	public static Coche pedirDatosCoche() {
		Coche c = new Coche();
		System.out.println("Introduce el año de fabricación:");
		int año = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce la marca:");
		String marca = sc.nextLine() ;
		System.out.println("Introduce el modelo:");
		String modelo = sc.nextLine() ;
		System.out.println("Introduce los kilómetros:");
		double kms = sc.nextDouble();
		//No se pide el id porque lo genera automáticamente la base de datos
		
		c.setAñoFabricacion(año);
		c.setMarca(marca);
		c.setModelo(modelo);
		c.setKms(kms);
		
		return c;
		
	}
	
	/**
	 * Método que permite al usuario introducir los valores de los atributos de un objeto de 
	 * tipo pasajero
	 * @return el objeto de tipo pasajero con los valores de los atributos asignados por 
	 * el usuario
	 */
	public static Pasajero pedirDatosPasajero() {
		Pasajero p = new Pasajero();
		System.out.println("Introduce la edad del pasajero:");
		int edad = sc.nextInt();
		System.out.println("Introduce el peso del pasajero:");
		double peso = sc.nextDouble();
		System.out.println("Introduce el nombre del pasajero:");
		String nombre = sc.next() + sc.nextLine();
		//No se pide el id porque lo genera automáticamente la base de datos
		
		p.setEdad(edad);
		p.setNombre(nombre);
		p.setPeso(peso);
		return p;
	}
}
