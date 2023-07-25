/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.util.Scanner;
import jpa_mysql.servicios.PrestamoServicio;

/**
 *
 * @author Max
 */
public class MenuPrestamo {
    
    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final PrestamoServicio ps;

    public MenuPrestamo() {
        this.ps = new PrestamoServicio();
    }

    public void ejecutarMenuPrestamo() {
        int opcPrestamo;
        do {            
            System.out.println("Seleccione una opcion:\n 1- Cargar prestamo nuevo\n 2-Mostrar prestamos"
                    + "\n 0-Volver al menu principal");
            opcPrestamo = leer.nextByte();
        } while (opcPrestamo != 0);
    }
    
}
