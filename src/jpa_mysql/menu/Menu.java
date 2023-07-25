/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.util.Scanner;

/**
 *
 * @author Max
 */
public class Menu {

    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");

    public void ejecucion() {       
            int opcionMenu;
            do {
                System.out.println("Seleccione el menu que desee ingresar: \n 1- Autor \n 2- Editorial"
                        + "\n 3- Libro \n 4- Prestamo\n 5- Cliente\n 0- Salir");
                opcionMenu = leer.nextShort();
                if (opcionMenu <= 5) {
                    switch (opcionMenu) {
                        case 1:
                            MenuAutor mA = new MenuAutor();
                            mA.ejecucion();
                            break;
                        case 2:
                            MenuEditorial menuEditorial = new MenuEditorial();
                            menuEditorial.ejecucion();
                            break;
                        case 3:
                            MenuLibro menuLibro = new MenuLibro();
                            menuLibro.ejecutarMenuLibro();
                            break;
                        case 4:
                            MenuPrestamo menuPrestamo = new MenuPrestamo();
                            menuPrestamo.ejecutarMenuPrestamo();
                            break;
                        case 5:
                            MenuCliente menuCliente = new MenuCliente();
                            menuCliente.ejecutarMenuCliente();
                            break;
                    }
                }
            } while (opcionMenu != 0);
    }

}
