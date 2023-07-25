/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.persistencia;

import java.util.List;
import jpa_mysql.entidades.Cliente;

/**
 *
 * @author Max
 */
public class ClienteDAO extends DAO<Cliente>{
    
    @Override
    public void guardar(Cliente cliente){
        super.guardar(cliente);
    }
    
    public Cliente buscarClientePorID(Long id){
        conectar();
        Cliente cliente = em.find(Cliente.class, id);
        desconectar();
        return cliente;
    }
    
    public Cliente buscarClientePorDni(Long dni){
        conectar();
        Cliente cliente = em.find(Cliente.class, dni);
        desconectar();
        return cliente;
    }
    
    public List<Cliente> mostrarClientes(){
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();
        desconectar();
        return clientes;
    }
    
    @Override
    public void editar(Cliente cliente){
        super.editar(cliente);
    }
    
    @Override
    public void eliminar(Cliente cliente){
        super.eliminar(cliente);
    }
    
}
