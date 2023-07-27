/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.servicios;

import java.util.List;
import jpa_mysql.entidades.Prestamo;
import jpa_mysql.persistencia.PrestamoDAO;

/**
 *
 * @author Max
 */
public class PrestamoServicio {
    
    private final PrestamoDAO pdao;

    public PrestamoServicio() {
        this.pdao = new PrestamoDAO();
    }
    
    public void cargarPrestamo(Prestamo prestamo){
        pdao.guardar(prestamo);
    }
    
    public List<Prestamo> mostrarPrestamos(){
        return pdao.mostrarPrestamos();
    }
    
}
