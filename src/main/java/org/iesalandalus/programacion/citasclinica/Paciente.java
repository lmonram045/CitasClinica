package org.iesalandalus.programacion.citasclinica;

import java.util.Locale;

public class Paciente {
    private static final String ER_DNI = "/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/i";
    private static final String ER_TELEFONO = "(\\+34|0034|34)?[ -]*(6|7|8|9)[ -]*([0-9][ -]*){8}";
    private String nombre;
    private String dni;
    private String telefono;

    /** Método para formatear los nombres, eliminando caracteres en blanco, y poniendo las primeras letras en mayúscula */
    private String formateaNombre(String nombre) {
        // Primero quitamos espacios al principio y al final
        nombre = nombre.trim();

        // Sustituimos las secuencias de dos o mas espacios por un solo espacio
        nombre = nombre.replaceAll("\\s{2,}", " ");

        // Divido el nombre en un array, para separar por palabras, separado por espacios en blanco
        String[] partesNombre = nombre.split(" ");

        // Vacío el nombre para reconstruirlo
        nombre = "";

        // Bucle para recorrer el array partesNombre
        for (String partes : partesNombre) {
            // Por cada parte, pongo en mayúscula la posición 0 y en minúscula las demás, añado un espacio al final.
            partes = partes.substring(0, 1).toUpperCase() + partes.substring(1).toLowerCase() + " ";

            // Reconstruyo el nombre
            nombre += partes;
        }

        // Devuelvo el nombre borrándole el espacio final que se creó en el bucle
        return nombre.trim();
    }
}
