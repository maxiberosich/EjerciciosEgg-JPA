/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.persistencia;

import java.util.Date;
import java.util.List;
import jpa_mysql.entidades.Cliente;
import jpa_mysql.entidades.Prestamo;

/**
 *
 * @author Max
 */
public class PrestamoDAO extends DAO<Prestamo> {

    @Override
    public void guardar(Prestamo prestamo) {
        super.guardar(prestamo);
    }

    public List<Prestamo> mostrarPrestamos() {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p").getResultList();
        desconectar();
        return prestamos;
    }
    
    public List<Prestamo> buscarPrestamosPorFecha(Date fecha) {
        conectar();
        String jpql = "SELECT p FROM Prestamo p"
                + " WHERE p.fechaPrestamo = :fecha";
        List<Prestamo> prestamos = em.createQuery(jpql, Prestamo.class).
                setParameter("fecha", fecha)
                .getResultList();
        desconectar();
        return prestamos;
    }
    
    public Prestamo buscarPrestamoPorID(Long id){
        conectar();
        Prestamo p = em.find(Prestamo.class, id);
        desconectar();
        return p;
    }
    
    public Prestamo buscarPrestamoPorCliente(Cliente cliente){
        conectar();
        String jpql = "SELECT p FROM Prestamo p"
                + " WHERE p.cliente = :cliente";
        Prestamo p = (Prestamo) em.createQuery(jpql,Prestamo.class).
                setParameter("cliente", cliente).
                getSingleResult();
        desconectar();

        return p;
    }
    
    @Override
    public void editar(Prestamo prestamo){
        super.editar(prestamo);
    }

}
