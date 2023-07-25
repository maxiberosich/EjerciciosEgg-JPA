/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.servicios;

import java.util.List;
import java.util.Scanner;
import jpa_mysql.entidades.Editorial;
import jpa_mysql.persistencia.EditorialDAO;

/**
 *
 * @author Max
 */
public class EditorialServicio {
    
    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final EditorialDAO editorialDAO;

    public EditorialServicio() {
        this.editorialDAO = new EditorialDAO();
    }
    
    public Editorial crearEditorial(){        
        
        System.out.println("Ingrese el nombre de la editorial");
        String nombreEd = leer.next();
        System.out.println("¿Dar de alta? S/N");
        Boolean alta = false;
        if(leer.next().equalsIgnoreCase("s")){
            alta = true;
        }
        Editorial ed = new Editorial(nombreEd,alta);
        editorialDAO.guardar(ed);
        return ed;
    }
    
    public void editarEditorial() {
        Editorial editorial;
        System.out.println("Seleccione la opcion que desee para buscar la editorial"
                + "\n1- Por nombre." + "\n2-Por ID");
        int opc = leer.nextInt();

        if (opc == 1) {
            System.out.println("Ingrese el nombre de la editorial:");
            String nombre = leer.next();
            editorial = buscarEditorialPorNombre(nombre);
        } else {
            System.out.println("Ingrese el ID de la editorial:");
            Long id = leer.nextLong();
            editorial = buscarEditorialPorID(id);
        }

        System.out.println("Desea modificar el nombre S/N");
        String modN;
        do {
            modN = leer.next();
            if (modN.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la modificación");
                editorial.setNombre(leer.next());
                editorialDAO.editar(editorial);
            }
        } while (!modN.equalsIgnoreCase("s") && !modN.equalsIgnoreCase("n"));

        System.out.println("Modificar el estado de alta S/N");
        String modE;
        do {
            modE = leer.next();
            if (modE.equalsIgnoreCase("s")) {
                modificarAlta(editorial);
            }
        } while (!modE.equalsIgnoreCase("s") && !modE.equalsIgnoreCase("n"));
    }
    
    public void modificarAlta() {
        Editorial editorial;
        System.out.println("Seleccione la opcion que desee para buscar la editorial que desea modificar el alta:"
                + "\n1- Por nombre." + "\n2-Por ID");
        int opc = leer.nextInt();

        if (opc == 1) {
            System.out.println("Ingrese el nombre de la editorial:");
            String nombre = leer.next();
            editorial = buscarEditorialPorNombre(nombre);
        } else {
            System.out.println("Ingrese el ID de la editorial:");
            Long id = leer.nextLong();
            editorial = buscarEditorialPorID(id);
        }
        
        if (editorial.getAlta()) {
            editorial.setAlta(false);
        } else {
            editorial.setAlta(true);
        }
        editorialDAO.editar(editorial);
    }
    
    public void modificarAlta(Editorial editorial) {
        if (editorial.getAlta()) {
            editorial.setAlta(false);
        } else {
            editorial.setAlta(true);
        }
        editorialDAO.editar(editorial);
    }
    
    public Editorial buscarEditorialPorID(Long id){
        return editorialDAO.buscarEditorialPorID(id);
    }
    
    public Editorial buscarEditorialPorNombre(String nombre){
        return editorialDAO.buscarEditorialPorNombre(nombre);
    }
    
    public List<Editorial> devolverEditoriales(){
        return editorialDAO.devolverEditoriales();
    }
    
    public void eliminarEditorial(){
        
        System.out.println("Seleccione la opcion que desee para buscar la editorial a eliminar:"
                + "\n1- Por nombre." + "\n2-Por ID");
        int opc = leer.nextInt();

        if (opc == 1) {
            System.out.println("Ingrese el nombre de la editorial:");
            String nombre = leer.next();
            editorialDAO.eliminar(buscarEditorialPorNombre(nombre));
        } else {
            System.out.println("Ingrese el ID de la editorial:");
            Long id = leer.nextLong();
            editorialDAO.eliminar(buscarEditorialPorID(id));
        }      
        
        System.out.println("Autor eliminado correctamente.");

    }
    
}
