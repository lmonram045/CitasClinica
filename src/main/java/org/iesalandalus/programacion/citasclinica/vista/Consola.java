package org.iesalandalus.programacion.citasclinica.vista;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {
    /** Constructor por defecto */
    private Consola() {}

    /** Método para mostrar menú */
    public static void mostrarMenu() {
        System.out.println("||===||=========================||");
        System.out.println("|| 0 ||          Salir          ||");
        System.out.println("|| 1 ||   Insertar nueva cita   ||");
        System.out.println("|| 2 ||       Buscar cita       ||");
        System.out.println("|| 3 ||       Borrar cita       ||");
        System.out.println("|| 4 || Mostrar citas de un dia ||");
        System.out.println("|| 5 ||     Todas las citas     ||");
        System.out.println("||===||=========================||");
    }

    /** Método para elegir una opción */
    public static int elegirOpcion() {
        int opcion = 0;

        do {
            System.out.print("\nElija una opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > 5);

        return opcion;
    }

    public static Cita leerCita() { return new Cita(leerPaciente(), leerFechaHora()); }

    public static Paciente leerPaciente() {
        System.out.println("\nIntroduzca el nombre del paciente: ");
        String nombre = Entrada.cadena();

        System.out.println("\nIntroduzca el dni de " + nombre + ": ");
        String dni = Entrada.cadena();

        System.out.println("\nIntroduzca el teléfono de " + nombre + ": ");
        String telefono = Entrada.cadena();

        return new Paciente(nombre, dni, telefono);
    }

    public static LocalDateTime leerFechaHora() {
        LocalDateTime fecha = null;

        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA);
        System.out.println("\nIntroduzca la fecha y hora de la cita (dd/MM/yyyy hh:mm): ");
        String fechaCadena = Entrada.cadena();

        try {
            fecha = LocalDateTime.parse(fechaCadena, formateador);
        } catch (DateTimeParseException e) {
            System.out.println("Error formato de fecha incorrecto");
        }

        return fecha;
    }

    public static LocalDate leerFecha() {
        LocalDate fecha = null;

        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(Cita.FORMATO_FECHA_HORA.replace(" HH:mm", ""));

        System.out.println("\nIntroduza una fecha (dd/MM/yyyy): ");
        String fechaCadena = Entrada.cadena();
        try {
            fecha = LocalDate.parse(fechaCadena, formateador);
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: formato de fecha incorrecto");
        }

        return fecha;
    }
}
