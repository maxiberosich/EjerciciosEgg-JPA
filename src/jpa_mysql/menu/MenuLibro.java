/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.util.Scanner;
import jpa_mysql.entidades.Libro;
import jpa_mysql.servicios.LibroServicio;

/**
 *
 * @author Max
 */
public class MenuLibro {

    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final LibroServicio ls;

    public MenuLibro() {
        this.ls = new LibroServicio();
    }

    public void ejecutarMenuLibro() {
        
            int opcLibro;
            do {
                System.out.println(" 1- Cargar un libro nuevo \n 2- Mostrar libro"
                        + "\n 3- Modificar Libro " + "\n 4- Eliminar libro"
                        + "\n 0- Volver al menu principal");
                opcLibro = leer.nextByte();
                switch (opcLibro) {
                    case 1:
                        ls.guardarLibro();
                        break;

                    case 2:
                        opcionMostrar();
                        break;

                    case 3:
                        modificacion();
                        break;
                    case 4:
                        eliminacion();
                        break;
                }
            } while (opcLibro != 0);
        
    }

    private void opcionMostrar() {
        int opcM;
        do {
            System.out.println(" 1- Mostrar todos los libros existentes"
                    + "\n 2- Buscar libro por ISBN" + "\n 3- Buscar libros por editorial"
                    + "\n 4- Buscar libros por autor" + "\n 5- Buscar libro por titulo"
                    + "\n 6- Volver al menu principal");
            opcM = leer.nextByte();
            switch (opcM) {
                case 1:
                    for (Libro l : ls.mostrarLibros()) {
                        System.out.println(l);
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el isbn del libro:");
                    String isbn = leer.next();
                    System.out.println(ls.buscarLibroPorISBN(isbn));
                    break;

                case 3:
                    mostrarLibroPorEditorial();
                    break;

                case 4:
                    mostrarLibroPorAutor();
                    break;

                case 5:
                    System.out.println("Ingrese el titulo del libro que desea buscar:");
                    String titulo = leer.next();
                    System.out.println(ls.buscarLibroPorTitulo(titulo));
                    break;
                default:
                    System.out.println("Seleccione una opcion correcta");
            }
        } while (opcM <= 5);
    }

    private void modificacion() {
        int opcL;
        Libro libro = null;
        System.out.println("Ingresar libro por:\n1 - Titulo\n2 - ID\n3 - ISBN");
        do {
            opcL = leer.nextByte();
            switch (opcL) {
                case 1:
                    System.out.print("Titulo del libro a modificar: ");
                    String titulo = leer.next();
                    libro = ls.buscarLibroPorTitulo(titulo);
                    break;
                case 2:
                    System.out.print("ID del libro a modificar: ");
                    Long id = leer.nextLong();
                    libro = ls.buscarLibroPorID(id);
                    break;
                case 3:
                    System.out.print("ISBN del libro a modificar: ");
                    String isbn = leer.next();
                    libro = ls.buscarLibroPorISBN(isbn);
                    break;
                default:
                    System.out.println("Debe elegir una opcion valida");
            }
        } while (opcL > 3);
        int opcMod;
        do {
            System.out.println("Modificar:\n 1- Atributos del libro" + "\n 2- ISBN"
                    + "\n 3- Titulo\n 4- Año de publicación\n 5- Alta\n 6- Cantidad de ejemplares"
                    + "\n 7- Cantidad de ejemplares prestados\n 8- Cantidad de ejemplares restantes"
                    + "\n 9- Autor\n 10- Editorial/es\n 11- Volver al menu principal");
            opcMod = leer.nextByte();
            switch (opcMod) {
                case 1:
                    ls.modificarLibro(libro);
                    break;
                case 2:
                    ls.modificarISBN(libro);
                    break;
                case 3:
                    ls.modificarTitulo(libro);
                    break;
                case 4:
                    ls.modificarAnioPublicacion(libro);
                    break;
                case 5:
                    ls.modificarAlta(libro);
                    break;
                case 6:
                    ls.modificarCantidadEjemplares(libro);
                    break;
                case 7:
                    ls.modificarCantidadEjemplaresPrestados(libro);
                    break;
                case 8:
                    ls.modificarCantidadEjemplaresRestantes(libro);
                    break;
                case 9:
                    ls.modificarAutor(libro);
                    break;
                case 10:
                    ls.modificarEditorial(libro);
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        } while (opcMod <= 10);
    }

    private void mostrarLibroPorEditorial() {
        System.out.println("Buscar editorial por: \n1- ID \n2- Nombre");
        int opcEd;
        do {
            opcEd = leer.nextByte();
            switch (opcEd) {
                case 1:
                    System.out.println("Ingrese el id de la editorial:");
                    Long id = leer.nextLong();
                    for (Libro libro : ls.buscarPorIDEditorial(id)) {
                        System.out.println(libro);
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el nombre de la editorial:");
                    String nombre = leer.next();
                    for (Libro l : ls.buscarPorNombreEditorial(nombre)) {
                        System.out.println(l);
                    }
                    break;
                default:
                    System.out.println("Ingresar una opcion valida");
            }
        } while (opcEd > 2);
    }

    private void mostrarLibroPorAutor() {
        System.out.println("Buscar Autor por: \n1- ID \n2- Nombre");
        int opcAu;
        do {
            opcAu = leer.nextByte();
            switch (opcAu) {
                case 1:
                    System.out.println("Ingrese el id del autor:");
                    Long id = leer.nextLong();
                    for (Libro libro : ls.buscarPorIDAutor(id)) {
                        System.out.println(libro);
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del ator:");
                    String nombre = leer.next();
                    for (Libro l : ls.buscarPorNombreAutor(nombre)) {
                        System.out.println(l);
                    }
                    break;
                default:
                    System.out.println("Ingresar una opcion valida");
            }
        } while (opcAu > 2);
    }

    private void eliminacion() {
        int opcL;
        Libro libro = null;
        System.out.println("Buscar libro a eliminar por:\n1 - Titulo\n2 - ID\n3 - ISBN");
        do {
            opcL = leer.nextByte();
            switch (opcL) {
                case 1:
                    System.out.print("Titulo del libro a eliminar: ");
                    String titulo = leer.next();
                    libro = ls.buscarLibroPorTitulo(titulo);
                    break;
                case 2:
                    System.out.print("ID del libro a eliminar: ");
                    Long id = leer.nextLong();
                    libro = ls.buscarLibroPorID(id);
                    break;
                case 3:
                    System.out.print("ISBN del libro a eliminar: ");
                    String isbn = leer.next();
                    libro = ls.buscarLibroPorISBN(isbn);
                    break;
                default:
                    System.out.println("Debe elegir una opcion valida");

            }
        } while (opcL > 3);

        if (libro != null && !libro.getAlta()) {
            ls.eliminarLibro(libro);
            System.out.println("Libro eliminado correctamente");
        } else {
            if (libro != null) {
                System.out.println("No se puede eliminar el libro, ya que sigue dado de alta."
                        + "Primero dar de baja.");
            }
        }

    }

}
