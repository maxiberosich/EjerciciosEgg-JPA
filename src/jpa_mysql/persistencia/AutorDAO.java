/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.persistencia;

import java.util.List;
import jpa_mysql.entidades.Autor;

/**
 *
 * @author Max
 */
public class AutorDAO extends DAO<Autor> {

    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }

    public Autor buscarAutorPorID(Long id){
        try {
            conectar();
            Autor au = em.find(Autor.class, id);
            desconectar();
            return au;
        } catch (java.lang.Exception e) {
            System.out.println("El id del autor ingresado no se encuentra en la base de datos");
            return null;
        }

    }

    public Autor buscarAutorPorNombre(String nombre){
        try {
            conectar();
            Autor au = (Autor) em.createQuery("SELECT a FROM Autor a"
                    + " WHERE a.nombre LIKE :nombre").
                    setParameter("nombre", nombre).
                    getSingleResult();
            desconectar();
            return au;
        } catch (java.lang.Exception e) {
            System.out.println("El nombre del autor ingresado no se encuentra en la base de datos");
            return null;
        }

    }

    public List<Autor> mostrarAutores() {
        conectar();
        List<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }

    @Override
    public void editar(Autor autor) {
        super.editar(autor);
    }

    @Override
    public void eliminar(Autor autor) {
        super.eliminar(autor);
    }

}
