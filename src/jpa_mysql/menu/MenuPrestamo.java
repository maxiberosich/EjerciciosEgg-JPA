/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpa_mysql.menu;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
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
                    + "\n 3- Buscar prestamo"
                    + "\n 0- Volver al menu principal");
            opcPrestamo = leer.nextByte();
            switch (opcPrestamo) {
                case 1:
                    cargarPrestamo();
                    break;
                case 2:
                    for (Prestamo p : ps.mostrarPrestamos()) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    buscarPrestamo();
                    break;
            }
        } while (opcPrestamo != 0);
    }

    private void cargarPrestamo() throws Exception {
        System.out.println("Fecha de prestamo cargada. Ingrese fecha de devolucion:");
        Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();
        System.out.print("Dia: ");
        int dia = leer.nextInt();
        System.out.print("Mes: ");
        int mes = leer.nextInt();
        System.out.print("Año: ");
        int anio = leer.nextInt();
        calendar.set(anio, mes, dia);
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
        Prestamo prestamo = new Prestamo(fecha, fechaEspecifica, libro, cliente);
        ps.cargarPrestamo(prestamo);
        System.out.println("Prestamo cargado correctamente.");
    }

    private void buscarPrestamo() throws Exception {
        int opcPres;
        do {
            System.out.println("Seleccione la opcion que desee:"
                    + "\n1 - Buscar prestamo por ID"
                    + "\n2 - Buscar prestamos por fecha de prestamo"
                    + "\n3 - Buscar prestamo por Cliente"
                    + "\n0 - Volver al menu anterior");
            opcPres = leer.nextByte();
            switch (opcPres) {
                case 1:
                    System.out.print("Ingrese el id del prestamo: ");
                    Long id = leer.nextLong();
                    System.out.println(ps.buscarPrestamoPorID(id));
                    break;
                case 2:
                    System.out.print("Ingrese el dia: ");
                    int dia = leer.nextInt();
                    System.out.print("Ingrese el mes: ");
                    int mes = leer.nextInt();
                    System.out.print("Ingrese el año: ");
                    int anio = leer.nextInt();
                    Date fechaEspecifica = new Date();
                    fechaEspecifica.setYear(anio-1900);
                    fechaEspecifica.setMonth(mes-1);
                    fechaEspecifica.setDate(dia);
                    for (Prestamo p : ps.buscarPrestamosPorFecha(fechaEspecifica)) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    int opcM;
                    Cliente cl = null;
                    System.out.println("Seleccione la opcion que desee:"
                            + "\n1- Buscar cliente por dni"
                            + "\n2- Buscar cliente por id");
                    opcM = leer.nextByte();
                    switch (opcM) {
                        case 1:
                    try {
                            System.out.print("Ingrese el dni del cliente que desea buscar: ");
                            Long dni = leer.nextLong();
                            if (!dni.toString().trim().isEmpty()) {
                                cl = cs.buscarClientePorDNI(dni);
                            } else {
                                System.out.println("Se debe ingresar datos");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Debe ingresar un numero valido\n\n");
                        }
                        break;
                        case 2:
                    try {
                            System.out.print("Ingrese el id del cliente que desea buscar: ");
                            Long idCliente = leer.nextLong();
                            if (!idCliente.toString().trim().isEmpty()) {
                                cl = cs.buscarClientePorID(idCliente);
                            } else {
                                System.out.println("No ingreso datos");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Debe ingresar un numero valido\n\n");
                        }
                        break;
                    }
                    System.out.println(ps.buscarPrestamoPorCliente(cl));
                    break;
            }
        } while (opcPres != 0);
    }

}
