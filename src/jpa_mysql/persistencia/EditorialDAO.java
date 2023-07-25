/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.persistencia;

import java.util.List;
import jpa_mysql.entidades.Editorial;

/**
 *
 * @author Max
 */
public class EditorialDAO extends DAO<Editorial> {

    @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }

    public Editorial buscarEditorialPorID(Long id){
        try {
            conectar();
            Editorial ed = em.find(Editorial.class, id);
            desconectar();
            return ed;
        } catch (java.lang.Exception e) {
            System.out.println("El id de la editorial ingresada no existe en la base de datos");
            return null;
        }

    }

    public Editorial buscarEditorialPorNombre(String nombre){
        try {
            conectar();
            Editorial ed = (Editorial) em.createQuery("SELECT e FROM Editorial e"
                    + " WHERE e.nombre LIKE :nombre").
                    setParameter("nombre", nombre).
                    getSingleResult();
            desconectar();
            return ed;
        } catch (java.lang.Exception e) {
            System.out.println("El nombre de la editorial ingresada no existe en la base de datos");
            return null;
        }

    }

    public List<Editorial> devolverEditoriales() {
        conectar();
        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();
        return editoriales;
    }

    @Override
    public void editar(Editorial editorial) {
        super.editar(editorial);
    }

    @Override
    public void eliminar(Editorial editorial) {
        super.eliminar(editorial);
    }

}
