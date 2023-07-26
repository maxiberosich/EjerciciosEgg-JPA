/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.servicios;

import java.util.List;
import java.util.Scanner;
import jpa_mysql.entidades.Cliente;
import jpa_mysql.persistencia.ClienteDAO;

/**
 *
 * @author Max
 */
public class ClienteServicio {
    
    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final ClienteDAO cdao;

    public ClienteServicio() {
        this.cdao = new ClienteDAO();
    }
    
    public Cliente crearCliente(){
        System.out.println("Ingrese el DNI del cliente:");
        Long dni = leer.nextLong();
        
        System.out.println("Ingrese el/los nombre/s del cliente");
        String nombre = leer.next();
        
        System.out.println("Ingrese el/los apellido/s del cliente");
        String apellido = leer.next();
        
        System.out.println("Ingrese el telefono del cliente");
        String telefono = leer.next();
        
        Cliente cliente = new Cliente(dni, nombre, apellido, telefono);
        cdao.guardar(cliente);
        
        return cliente;
    }
    
    public List<Cliente> mostrarTodosLosClientes(){
        return cdao.mostrarClientes();
    }
    
    public Cliente buscarClientePorDNI(Long dni) throws Exception{
        return cdao.buscarClientePorDni(dni);
    }
    
    public Cliente buscarClientePorID(Long id) throws Exception{
        return cdao.buscarClientePorID(id);
    }
    
    public void modificarCliente(Cliente cliente){
        cdao.editar(cliente);
    }
    
    public void eliminarCliente(Cliente cliente){
        cdao.eliminar(cliente);
    }
    
}
