/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import jpa_mysql.entidades.Cliente;
import jpa_mysql.servicios.ClienteServicio;

/**
 *
 * @author Max
 */
public class MenuCliente {

    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final ClienteServicio cs;

    public MenuCliente() {
        this.cs = new ClienteServicio();
    }

    public void ejecutarMenuCliente() throws Exception {
        int opcCliente;
        do {
            System.out.println("1- Cargar Cliente"
                    + "\n2- Mostrar Clientes"
                    + "\n3- Buscar Cliente"
                    + "\n4- Modificar Cliente"
                    + "\n5- Eliminar Cliente"
                    + "\n0-Volver al menu principal");
            opcCliente = leer.nextByte();
            switch (opcCliente) {
                case 1:
                    cs.crearCliente();
                    break;
                case 2:
                    for (Cliente c : cs.mostrarTodosLosClientes()) {
                        System.out.println(c);
                    }
                    break;
                case 3:
                    buscarCliente();
                    break;
                case 4:
                    modificarCliente();
                    break;
                case 5:
                    eliminarCliente();
                    break;
            }
        } while (opcCliente != 0);
    }

    private Cliente buscarCliente() throws Exception {
        int opcM;
        Cliente cl = null;
        do {
            System.out.println("Seleccione la opcion que desee:"
                    + "\n1- Buscar cliente por dni"
                    + "\n2- Buscar cliente por id"
                    + "\n0- Volver al menu anterior");            
            opcM = leer.nextByte();
            switch (opcM) {
                case 1:
                    try {
                    System.out.print("Ingrese el dni del cliente que desea buscar: ");
                    Long dni = leer.nextLong();
                        if (!dni.toString().trim().isEmpty()) {
                            cl = cs.buscarClientePorDNI(dni);
                            if (cl != null) {
                                return cl;
                            }else{
                                System.out.println("No se encontro cliente");
                            }
                        } else {
                            System.out.println("Se debe ingresar datos");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Debe ingresar un numero valido\n\n");
                    }
                    break;
                case 2:
                    try{
                        System.out.print("Ingrese el id del cliente que desea buscar: ");
                        Long id = leer.nextLong();
                        if (!id.toString().trim().isEmpty()) {
                            cl = cs.buscarClientePorID(id);
                            if (cl != null) {
                                return cl;
                            }else{
                                System.out.println("No se encontro cliente");
                            }
                        } else {
                            System.out.println("No ingreso datos");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Debe ingresar un numero valido\n\n");
                    }
                    break;
            }
        } while (opcM != 0);
        return cl;
    }
    
    private void modificarCliente() throws Exception{
        Cliente cliente= buscarCliente();
        System.out.println("Desea modificar el DNI del cliente S/N");
        String mod = leer.next();
        if(mod.equalsIgnoreCase("s")){
            System.out.print("Ingrese el nuevo DNI: ");
            Long dni = leer.nextLong();
            cliente.setDni(dni);
        }
        System.out.println("Desea modificar el nombre del cliente S/N");
        mod = leer.next();
        if(mod.equalsIgnoreCase("s")){
            System.out.print("Ingrese el nuevo nombre: ");
            String nombre = leer.next();
            cliente.setNombre(nombre);
        }
        System.out.println("Desea modificar el apellido del cliente S/N");
        mod = leer.next();
        if(mod.equalsIgnoreCase("s")){
            System.out.print("Ingrese el nuevo apellido: ");
            String apellido = leer.next();
            cliente.setApellido(apellido);
        }
        System.out.println("Desea modificar el telefono del cliente S/N");
        mod = leer.next();
        if(mod.equalsIgnoreCase("s")){
            System.out.print("Ingrese el nuevo telefono: ");
            String telefono = leer.next();
            cliente.setTelefono(telefono);
        }
        cs.modificarCliente(cliente);
        System.out.println("Cliente modificado correctamente");
    }
    
    private void eliminarCliente() throws Exception{
        Cliente client = buscarCliente();
        cs.eliminarCliente(client);
        System.out.println("Cliente eliminado correctamente");
    }

}
