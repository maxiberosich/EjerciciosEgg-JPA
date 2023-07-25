/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.util.Scanner;
import jpa_mysql.entidades.Autor;
import jpa_mysql.servicios.AutorServicio;

/**
 *
 * @author Max
 */
public class MenuAutor {

    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final AutorServicio as;

    public MenuAutor() {
        this.as = new AutorServicio();
    }
    
    public void ejecucion(){
        int opcionAutor;

        do {
            System.out.println("Que accion desea realizar:"
                    + "\n 1 - Cargar un nuevo autor a la base de datos."
                    + "\n 2 - Modificar un autor ya existente."
                    + "\n 3 - Eliminar un autor de la base de datos."
                    + "\n 4 - Cambiar el estado de alta del autor."
                    + "\n 5 - Buscar un autor por el nombre."
                    + "\n 6 - Buscar el autor por su ID."
                    + "\n 7 - Mostrar todos los autores cargados."
                    + "\n 0 - Volver al menu principal.");
            opcionAutor = leer.nextInt();
            switch (opcionAutor) {
                case 1:
                    as.crearAutor();
                    break;
                case 2:
                    as.editarAutor();
                    break;
                case 3:
                    as.eliminarAutor();
                    break;
                case 4:
                    as.modificarAlta();
                    break;
                case 5:
                    System.out.println("Ingrese el nombre del autor que desea buscar:");
                    String nombre = leer.next();
                    System.out.println(as.buscarAutorPorNombre(nombre));
                    break;
                case 6:
                    System.out.println("Ingrese el ID del autor que busca:");
                    Long id = leer.nextLong();
                    System.out.println(as.buscarAutorPorID(id));
                    break;
                case 7:
                    for (Autor au : as.mostrarAutores()) {
                        System.out.println(au);
                    }
                    break;
            }
        } while (opcionAutor != 0);
    }
    
}
