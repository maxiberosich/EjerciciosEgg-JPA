/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.util.Scanner;
import jpa_mysql.entidades.Editorial;
import jpa_mysql.servicios.EditorialServicio;

/**
 *
 * @author Max
 */
public class MenuEditorial {
    
    private final Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private final EditorialServicio es;
    
    public MenuEditorial(){
        this.es = new EditorialServicio();
    }
    
    public void ejecucion(){
        int opcionEditorial;
        do {
            System.out.println("Que accion desea realizar:"
                    + "\n 1 - Cargar una nueva editorial a la base de datos."
                    + "\n 2 - Modificar una editorial ya existente."
                    + "\n 3 - Eliminar una editorial de la base de datos."
                    + "\n 4 - Cambiar el estado de alta de la editorial."
                    + "\n 5 - Buscar una editorial por el nombre."
                    + "\n 6 - Buscar la editorial por su ID."
                    + "\n 7 - Mostrar todas las editoriales cargadas."
                    + "\n 0 - Volver al menu principal.");
            opcionEditorial = leer.nextInt();
            switch (opcionEditorial) {
                case 1:
                    es.crearEditorial();
                    break;
                case 2:
                    es.editarEditorial();
                    break;
                case 3:
                    es.eliminarEditorial();
                    break;
                case 4:
                    es.modificarAlta();
                    break;
                case 5:
                    System.out.println("Ingrese el nombre de la editorial que desea buscar:");
                    String nombre = leer.next();
                    System.out.println(es.buscarEditorialPorNombre(nombre));
                    break;
                case 6:
                    System.out.println("Ingrese el ID de la editorial que busca:");
                    Long id = leer.nextLong();
                    System.out.println(es.buscarEditorialPorID(id));
                    break;
                case 7:
                    for (Editorial ed : es.devolverEditoriales()) {
                        System.out.println(ed);
                    }
                    break;
            }
        } while (opcionEditorial != 0);
    }
    
}
