/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import jpa_mysql.entidades.Cliente;
import jpa_mysql.entidades.Libro;
import jpa_mysql.entidades.Prestamo;
import jpa_mysql.servicios.ClienteServicio;
import jpa_mysql.servicios.LibroServicio;
import jpa_mysql.servicios.PrestamoServicio;

/**
 *
 * @author Max
 */
public class MenuPrestamo {
    
    private final Scanner leer = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
    private final PrestamoServicio ps;
    private final LibroServicio ls;
    private final ClienteServicio cs;

    public MenuPrestamo() {
        this.ps = new PrestamoServicio();
        this.cs = new ClienteServicio();
        this.ls = new LibroServicio();
    }

    public void ejecutarMenuPrestamo() throws Exception {
        int opcPrestamo;
        do {            
            System.out.println("Seleccione una opcion:"
                    + "\n 1- Cargar prestamo nuevo"
                    + "\n 2- Mostrar prestamos"
                    + "\n 0- Volver al menu principal");
            opcPrestamo = leer.nextByte();
            switch (opcPrestamo) {
                case 1:
                    cargarPrestamo();
                    break;
                case 2:
                    for(Prestamo p: ps.mostrarPrestamos()){
                        System.out.println(p);
                    }
                    break;
            }
        } while (opcPrestamo != 0);
    }
    
    private void cargarPrestamo() throws Exception{
        System.out.println("Fecha de prestamo cargada. Ingrese fecha de devolucion:");
        Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();        
        System.out.print("Dia: ");
        int dia = leer.nextInt();
        System.out.print("Mes: ");
        int mes = leer.nextInt();
        System.out.print("Año: ");
        int anio = leer.nextInt();
        calendar.set(anio, Calendar.AUGUST, dia);
        Date fechaEspecifica = calendar.getTime();
        Libro libro = null;
        System.out.println("Ingresar libro por:\n1- ID.\n2- ISBN.\n3- Titulo.");
        int opcL = leer.nextByte();
        switch (opcL) {
            case 1:
                System.out.println("Ingrese el ID del libro:");
                Long id = leer.nextLong();
                libro = ls.buscarLibroPorID(id);
                break;
            case 2:
                System.out.println("Ingrese el ISBN del libro:");
                String isbn = leer.next();
                libro = ls.buscarLibroPorISBN(isbn);
                break;
            case 3:
                System.out.println("Ingrese el titulo del libro:");
                String titulo = leer.next();
                libro = ls.buscarLibroPorTitulo(titulo);
                break;
        }
        System.out.println("Libro cargado correctamente.");
        Cliente cliente = null;
        System.out.println("\nIngresar Cliente por:\n1- ID\n2- DNI");
        int opcC = leer.nextByte();
        switch (opcC) {
            case 1:
                System.out.println("Ingrese el ID del cliente:");
                Long idCliente = leer.nextLong();
                cliente = cs.buscarClientePorID(idCliente);
                break;
            case 2:
                System.out.println("Ingrese el DNI del cliente:");
                Long dniCliente = leer.nextLong();
                cliente = cs.buscarClientePorDNI(dniCliente);
                break;            
        }
        System.out.println("Cliente cargado correctamente.");
        Prestamo prestamo = new Prestamo(fecha,fechaEspecifica, libro, cliente);
        ps.cargarPrestamo(prestamo);
        System.out.println("Prestamo cargado correctamente.");
    }
    
}
