/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import jpa_mysql.entidades.Autor;
import jpa_mysql.entidades.Editorial;
import jpa_mysql.entidades.Libro;
import jpa_mysql.persistencia.LibroDAO;

/**
 *
 * @author Max
 */
public class LibroServicio {

    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private AutorServicio as;
    private EditorialServicio es;
    private final LibroDAO libroDao;

    public LibroServicio() {
        this.libroDao = new LibroDAO();
        this.as = new AutorServicio();
        this.es = new EditorialServicio();
    }

    public Libro guardarLibro() {
        Long isbn;
        do {
            System.out.println("Ingrese el isbn del libro (13 digitos):");
            isbn = leer.nextLong();
            if (isbn < 0 || String.valueOf(isbn).length() != 13) {
                System.out.println("Ingresaste un ISBN nulo o invalido");
            }
        } while (isbn < 0 || String.valueOf(isbn).length() != 13);

        System.out.println("Ingrese el titulo del libro:");
        String titulo = leer.next();

        System.out.println("Ingrese el año que fue publicado el libro:");
        Integer anio = leer.nextInt();

        System.out.println("Ingrese la cantidad de ejemplares que existen:");
        Integer ejemplares = leer.nextInt();

        System.out.println("Cantidad de ejemplares prestados:");
        Integer ejemplaresPrestados = leer.nextInt();

        System.out.println("Cantidad de ejemplares restantes:");
        Integer ejemplaresRestantes = leer.nextInt();

        System.out.println("Alta S/N:");
        Boolean alta = false;
        if (leer.next().equalsIgnoreCase("s")) {
            alta = true;
        }

        Autor autor = cargarAutor();

        Editorial editorial = cargarEditorial();
        List<Editorial> editoriales = new ArrayList<>();
        editoriales.add(editorial);

        Libro libro = new Libro(isbn, titulo, anio, ejemplares, ejemplaresPrestados,
                ejemplaresRestantes, alta, autor, editoriales);
        libroDao.guardar(libro);

        return libro;
    }

    public Libro buscarLibroPorID(Long n) {
        return libroDao.buscarLibroPorID(n);
    }

    public Libro buscarLibroPorISBN(String isbn) {
        return libroDao.buscarLibroPorISBN(isbn);
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        return libroDao.buscarLibroPorTitulo(titulo);
    }

    public List<Libro> buscarPorIDEditorial(Long idEditorial) {
        Editorial editorial = es.buscarEditorialPorID(idEditorial);
        return libroDao.buscarPorEditorial(editorial);
    }

    public List<Libro> buscarPorNombreEditorial(String nombreEditorial) {
        Editorial editorial = es.buscarEditorialPorNombre(nombreEditorial);
        return libroDao.buscarPorEditorial(editorial);
    }

    public List<Libro> buscarPorNombreAutor(String nombreAutor) {
        Autor autor = as.buscarAutorPorNombre(nombreAutor);
        return libroDao.buscarPorAutor(autor);
    }

    public List<Libro> buscarPorIDAutor(Long idAutor) {
        Autor autor = as.buscarAutorPorID(idAutor);
        return libroDao.buscarPorAutor(autor);
    }

    public List<Libro> mostrarLibros() {
        return libroDao.mostrarLibros();
    }

    private Autor cargarAutor() {
        System.out.println("Autor: \n 1- Seleccionar uno existente \n 2- Cargar nuevo autor");
        Autor autor = null;
        int opcAutor;
        do {
            opcAutor = leer.nextByte();
            switch (opcAutor) {
                case 1:
                    System.out.println("1 - Nombre \n2 - ID Autor");
                    int opcEx;
                    do {
                        opcEx = leer.nextByte();
                        switch (opcEx) {
                            case 1:
                                System.out.println("Ingrese el nombre del autor");
                                String nombreAutor = leer.next();
                                autor = as.buscarAutorPorNombre(nombreAutor);
                                break;
                            case 2:
                                System.out.println("Ingrese el ID del autor");
                                Long id = leer.nextLong();
                                autor = as.buscarAutorPorID(id);
                                break;
                        }
                    } while (opcEx > 2);
                    break;
                case 2:
                    autor = as.crearAutor();
                    break;
                default:
                    System.out.println("Debe seleccionar una opcion correcta");
            }
        } while (opcAutor > 2);

        return autor;
    }

    private Editorial cargarEditorial() {
        System.out.println("Editorial: \n 1- Seleccionar editorial existente \n 2- Nueva editorial");
        Editorial editorial = null;
        int opcEditorial;
        do {
            opcEditorial = leer.nextByte();
            switch (opcEditorial) {
                case 1:
                    System.out.println("1 - Nombre \n2 - ID Autor");
                    int opcEx;
                    do {
                        opcEx = leer.nextByte();
                        switch (opcEx) {
                            case 1:
                                System.out.println("Ingrese el nombre de la editorial");
                                String nombreEditorial = leer.next();
                                editorial = es.buscarEditorialPorNombre(nombreEditorial);
                                break;
                            case 2:
                                System.out.println("Ingrese el ID de la editorial");
                                Long id = leer.nextLong();
                                editorial = es.buscarEditorialPorID(id);
                                break;
                        }
                    } while (opcEx > 2);
                    break;
                case 2:
                    editorial = es.crearEditorial();
                    break;
                default:
                    System.out.println("Debe seleccionar una opcion correcta");
            }
        } while (opcEditorial > 2);
        return editorial;
    }

    public void modificarLibro(Libro libro) {
        String opc;
        System.out.println("Modificar ISBN. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            Long isbn;
            do {
                System.out.println("Ingrese el isbn del libro (13 digitos):");
                isbn = leer.nextLong();
                if (isbn < 0 || String.valueOf(isbn).length() != 13) {
                    System.out.println("Ingresaste un ISBN nulo o invalido");
                }
            } while (isbn < 0 || String.valueOf(isbn).length() != 13);
        }

        System.out.println("Modificar Titulo. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el titulo del libro:");
            String titulo = leer.next();
            libro.setTitulo(titulo);
        }

        System.out.println("Modificar año que fue publicado. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el año que fue publicado el libro:");
            Integer anio = leer.nextInt();
            libro.setAnio(anio);
        }

        System.out.println("Modificar cantidad de ejemplares que existen. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            System.out.println("Ingrese la cantidad de ejemplares que existen:");
            Integer ejemplares = leer.nextInt();
            libro.setEjemplares(ejemplares);
        }

        System.out.println("Modificar ejemplares prestados. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            System.out.println("Cantidad de ejemplares prestados:");
            Integer ejemplaresPrestados = leer.nextInt();
            libro.setEjemplaresPrestados(ejemplaresPrestados);
        }

        System.out.println("Modificar cantidad de ejemplares restantes. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            System.out.println("Cantidad de ejemplares restantes:");
            Integer ejemplaresRestantes = leer.nextInt();
            libro.setEjemplaresRestantes(ejemplaresRestantes);
        }

        System.out.println("Modificar Alta S/N:");
        if (leer.next().equalsIgnoreCase("s")) {
            modificarAlta(libro);
        }

        System.out.println("Modificar Autor. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            Autor autor = cargarAutor();
            libro.setAutor(autor);
        }

        System.out.println("Modificar Editoriales. S/N");
        opc = leer.next();
        if (opc.equalsIgnoreCase("s")) {
            Editorial editorial = cargarEditorial();
            List<Editorial> editoriales = new ArrayList<>();
            editoriales.add(editorial);
            libro.setEditorial(editoriales);
        }

        libroDao.editar(libro);
        System.out.println("Libro modificado correctamente.");
    }

    public void modificarISBN(Libro libro) {
        try {
            System.out.println("El isbn del libro actualmente es: " + libro.getIsbn());
            Long isbn;
            do {
                System.out.println("Ingrese el nuevo isbn del libro (13 digitos):");
                isbn = leer.nextLong();
                if (isbn < 0 || String.valueOf(isbn).length() != 13) {
                    System.out.println("Ingresaste un ISBN nulo o invalido");
                }
            } while (isbn < 0 || String.valueOf(isbn).length() != 13);
            libro.setIsbn(isbn);
            libroDao.editar(libro);
            System.out.println("ISBN modificado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo modificar ISBN. " + e.getMessage());
        }
    }

    public void modificarTitulo(Libro libro) {
        try {
            System.out.println("Titulo actual: " + libro.getTitulo());
            System.out.print("Nuevo titulo: ");
            String titulo = leer.next();
            libro.setTitulo(titulo);
            libroDao.editar(libro);
            System.out.println("Titulo actualizado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar titulo. " + e.getMessage());
        }
    }

    public void modificarAnioPublicacion(Libro libro) {
        try {
            System.out.println("Año de publicación actual: " + libro.getAnio());
            System.out.print("Nuevo año de publicación: ");
            Integer anio = leer.nextInt();
            libro.setAnio(anio);
            libroDao.editar(libro);
            System.out.println("Año de publicación actualizado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar año de publicación. " + e.getMessage());
        }
    }

    public void modificarCantidadEjemplares(Libro libro) {
        try {
            System.out.println("Cantidad de ejemplares actual: " + libro.getEjemplares());
            System.out.print("Nueva cantidad de ejemplares: ");
            Integer cantidadEjemplares = leer.nextInt();
            libro.setEjemplares(cantidadEjemplares);
            libroDao.editar(libro);
            System.out.println("Cantidad de ejemplares actualizado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar cantidad de ejemplares. " + e.getMessage());
        }
    }

    public void modificarCantidadEjemplaresPrestados(Libro libro) {
        try {
            System.out.println("Cantidad de ejemplares prestados actualmente: " + libro.getEjemplaresPrestados());
            System.out.print("Nueva cantidad de ejemplares prestados: ");
            Integer cantidadEjemplares = leer.nextInt();
            libro.setEjemplaresPrestados(cantidadEjemplares);
            libroDao.editar(libro);
            System.out.println("Cantidad de ejemplares prestados actualizado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar cantidad de ejemplares prestados. " + e.getMessage());
        }
    }

    public void modificarCantidadEjemplaresRestantes(Libro libro) {
        try {
            System.out.println("Cantidad de ejemplares restantes actualmente: " + libro.getEjemplaresRestantes());
            System.out.print("Nueva cantidad de ejemplares restantes: ");
            Integer cantidadEjemplares = leer.nextInt();
            libro.setEjemplaresRestantes(cantidadEjemplares);
            libroDao.editar(libro);
            System.out.println("Cantidad de ejemplares restantes actualizado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar cantidad de ejemplares restantes. " + e.getMessage());
        }
    }

    public void modificarAlta(Libro libro) {
        if (libro.getAlta()) {
            libro.setAlta(false);
        } else {
            libro.setAlta(true);
        }
    }

    public void modificarAutor(Libro libro) {
        try {
            System.out.print("Nuevo Autor: ");
            Autor autor = cargarAutor();
            libro.setAutor(autor);
            libroDao.editar(libro);
            System.out.println("Autor actualizado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar Autor. " + e.getMessage());
        }
    }

    public void modificarEditorial(Libro libro) {
        try {
            System.out.print("Nueva Editorial: ");
            Editorial editorial = cargarEditorial();
            List<Editorial> editoriales = new ArrayList<>();
            editoriales.add(editorial);
            libro.setEditorial(editoriales);
            libroDao.editar(libro);
            System.out.println("Editorial/es actualizada correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo actualizar Editorial/es. " + e.getMessage());
        }
    }

    public void eliminarLibro(Libro libro) {
        libroDao.eliminar(libro);
    }

}
