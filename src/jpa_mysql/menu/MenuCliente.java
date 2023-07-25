/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

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

    public void ejecutarMenuCliente() {
        int opcCliente;
        do {      
            System.out.println("1-Cargar Cliente\n2-Mostrar Clientes\n0-Volver al menu principal");
            opcCliente = leer.nextByte();
            switch (opcCliente) {
                case 1:
                    cs.crearCliente();
                    break;
                case 2:
                    for(Cliente c: cs.mostrarTodosLosClientes()){
                        System.out.println(c);
                    }
                    break;
            }
        } while (opcCliente != 0);
    }
    
}
