package org.iesalandalus.programacion.citasclinica;

public class Paciente {
    private static final String ER_DNI = "/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/i";
    private static final String ER_TELEFONO = "(\\+34|0034|34)?[ -]*(6|7|8|9)[ -]*([0-9][ -]*){8}";
    private String nombre;
    private String dni;
    private String telefono;

    private String formatearNombre(String nombre) {

    }
}
