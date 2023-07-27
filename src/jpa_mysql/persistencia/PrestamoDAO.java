/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.persistencia;

import java.time.LocalDate;
import java.util.List;
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

}
