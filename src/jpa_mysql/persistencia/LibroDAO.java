/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.persistencia;

import java.util.List;
import jpa_mysql.entidades.Autor;
import jpa_mysql.entidades.Editorial;
import jpa_mysql.entidades.Libro;

/**
 *
 * @author Max
 */
public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    public Libro buscarLibroPorID(Long n){
        try {
            conectar();
            Libro libro = em.find(Libro.class, n);
            desconectar();
            return libro;
        } catch (java.lang.Exception e) {
            System.out.println("El id del libro ingresado no existe en la base de datos");
            return null;
        }

    }

    public Libro buscarLibroPorISBN(String isbn){
        try {
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l"
                    + " WHERE l.isbn LIKE :isbn").
                    setParameter("isbn", isbn).
                    getSingleResult();
            desconectar();
            return libro;
        } catch (java.lang.Exception e) {
            System.out.println("El ISBN del libro ingresado no existe en la base de datos");
            return null;
        }

    }

    public Libro buscarLibroPorTitulo(String titulo){
        try {
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l"
                    + " WHERE l.titulo LIKE :titulo").
                    setParameter("titulo", titulo).
                    getSingleResult();
            desconectar();
            return libro;
        } catch (java.lang.Exception e) {
            System.out.println("El titulo del libro ingresado no existe en la base de datos");
            return null;
        }
    }

    public List<Libro> buscarPorEditorial(Editorial editorial) {
        conectar();
        String jpql = "SELECT l FROM Libro l"
                + " JOIN l.editorial e WHERE e = :editorial";
        List<Libro> libros = em.createQuery(jpql, Libro.class).
                setParameter("editorial", editorial).
                getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarPorAutor(Autor autor) {
        conectar();
        String jpql = "SELECT l FROM Libro l"
                + " JOIN l.autor a WHERE a = :autor";
        List<Libro> libros = em.createQuery(jpql, Libro.class).
                setParameter("autor", autor).
                getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> mostrarLibros() {
        conectar();
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        desconectar();
        return libros;
    }

    @Override
    public void editar(Libro libro) {
        super.editar(libro);
    }

    @Override
    public void eliminar(Libro libro) {
        super.eliminar(libro);
    }

}
