/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.servicios;

import java.util.List;
import java.util.Scanner;
import jpa_mysql.entidades.Autor;
import jpa_mysql.persistencia.AutorDAO;

/**
 *
 * @author Max
 */
public class AutorServicio {

    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final AutorDAO auDAO;
    
    public AutorServicio(){
        this.auDAO = new AutorDAO();
    }

    public Autor crearAutor() {

        System.out.println("Ingrese el nombre del autor");
        String nombreA = leer.next();
        System.out.println("¿Dar de alta? S/N");
        Boolean alta = false;
        if (leer.next().equalsIgnoreCase("s")) {
            alta = true;
        }
        Autor au = new Autor(nombreA, alta);
        auDAO.guardar(au);
        return au;
    }

    public void editarAutor() {
        Autor autor;
        System.out.println("Seleccione la opcion que desee para buscar el autor"
                + "\n1- Por nombre." + "\n2- Por ID");
        int opc = leer.nextInt();

        if (opc == 1) {
            System.out.println("Ingrese el nombre del autor:");
            String nombre = leer.next();
            autor = buscarAutorPorNombre(nombre);
        } else {
            System.out.println("Ingrese el ID del autor:");
            Long id = leer.nextLong();
            autor = buscarAutorPorID(id);
        }

        System.out.println("Desea modificar el nombre S/N");
        String modN;
        do {
            modN = leer.next();
            if (modN.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la modificación");
                autor.setNombre(leer.next());
                auDAO.editar(autor);
            }
        } while (!modN.equalsIgnoreCase("s") && !modN.equalsIgnoreCase("n"));

        System.out.println("Modificar el estado de alta S/N");
        String modE;
        do {
            modE = leer.next();
            if (modE.equalsIgnoreCase("s")) {
                modificarAlta(autor);
            }
        } while (!modE.equalsIgnoreCase("s") && !modE.equalsIgnoreCase("n"));
    }

    public void modificarAlta() {
        Autor autor;
        System.out.println("Seleccione la opcion que desee para buscar el autor que desea modificar el alta:"
                + "\n1- Por nombre." + "\n2- Por ID");
        int opc = leer.nextInt();

        if (opc == 1) {
            System.out.println("Ingrese el nombre del autor:");
            String nombre = leer.next();
            autor = buscarAutorPorNombre(nombre);
        } else {
            System.out.println("Ingrese el ID del autor:");
            Long id = leer.nextLong();
            autor = buscarAutorPorID(id);
        }
        
        if (autor.getAlta()) {
            autor.setAlta(false);
        } else {
            autor.setAlta(true);
        }
        auDAO.editar(autor);
    }
    
    public void modificarAlta(Autor autor) {
        if (autor.getAlta()) {
            autor.setAlta(false);
        } else {
            autor.setAlta(true);
        }
        auDAO.editar(autor);
    }
    
    public List<Autor> mostrarAutores(){
        return auDAO.mostrarAutores();
    }
    
    public Autor buscarAutorPorNombre(String nombre){
       return auDAO.buscarAutorPorNombre(nombre);
    }
    
    public Autor buscarAutorPorID(Long id){
        return auDAO.buscarAutorPorID(id);
    }
    
    public void eliminarAutor(){
        System.out.println("Aclaracion: Al eliminar el autor de la base de datos,"
                + " eliminara tambien los libros que tenga relacionados.");
        System.out.println("Seleccione la opcion que desee para buscar el autor a eliminar:"
                + "\n1- Por nombre." + "\n2- Por ID");
        int opc = leer.nextInt();

        if (opc == 1) {
            System.out.println("Ingrese el nombre del autor:");
            String nombre = leer.next();
            auDAO.eliminar(buscarAutorPorNombre(nombre));
        } else {
            System.out.println("Ingrese el ID del autor:");
            Long id = leer.nextLong();
            auDAO.eliminar(buscarAutorPorID(id));
        }      
        
        System.out.println("Autor eliminado correctamente.");

    }

}
