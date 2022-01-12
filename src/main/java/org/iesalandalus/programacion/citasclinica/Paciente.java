package org.iesalandalus.programacion.citasclinica;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
    private static final String ER_DNI = "[0-9]{7,8}[A-Z a-z]"; // Se puede mejorar
    private static final String ER_TELEFONO = "(\\+34|0034|34)?[ -]*(6|7|8|9)[ -]*([0-9][ -]*){8}";
    private String nombre;
    private String dni;
    private String telefono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // Primero comprobamos si es nulo o vacío
        if (nombre == null || nombre.equals(""))
            throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");

        // Formateamos el nombre y lo asignamos.
        this.nombre = formateaNombre(nombre);
    }

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

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        // Primero comprobamos si es nulo
        if (dni == null)
            throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");

        // Quitamos los posibles espacios en blanco del dni
        dni = dni.trim();

        // Creamos las variables pat y mat para comprobar si el dni coincide con la expresión regular
        Pattern pat = Pattern.compile(ER_DNI);
        Matcher mat = pat.matcher(dni);

        // Comprobamos que el dni coincide con la expresión regular y tiene el tamaño adecuado
        if (dni.length() != 9 || !mat.matches())
            throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");

        // Comprobamos que la letra del dni es correcta llamando al método correspondiente
        comprobarLetraDni(dni); // Si no es true, debería saltar una excepción en el método.

        this.dni = dni;
    }

    /** Método para comprobar la letra del dni */
    private boolean comprobarLetraDni(String dni) {
        // Asigno el dni a un dni auxiliar para sacarle el número
        String dniAux = dni;
        dniAux = dniAux.replaceAll("[^0-9]", "");

        // Convierto el numero del dni en un entero
        int numDni = Integer.parseInt(dniAux);

        // Compruebo la letra
        String letra = dni.substring(dni.length() - 1); // Primero saco la letra de el string de el dni a una variable
        // La letra del dni se asigna a partir de el resto de dividir el numero entre 23
        int resto = numDni % 23;
        switch (resto) {
            case 0:
                if (!letra.equals("T") && !letra.equals("t"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 1:
                if (!letra.equals("R") && !letra.equals("r"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 2:
                if (!letra.equals("W") && !letra.equals("w"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 3:
                if (!letra.equals("A") && !letra.equals("a"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 4:
                if (!letra.equals("G") && !letra.equals("g"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 5:
                if (!letra.equals("M") && !letra.equals("m"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 6:
                if (!letra.equals("Y") && !letra.equals("y"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 7:
                if (!letra.equals("F") && !letra.equals("f"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 8:
                if (!letra.equals("P") && !letra.equals("p"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 9:
                if (!letra.equals("D") && !letra.equals("d"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 10:
                if (!letra.equals("X") && !letra.equals("x"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 11:
                if (!letra.equals("B") && !letra.equals("b"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 12:
                if (!letra.equals("N") && !letra.equals("n"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 13:
                if (!letra.equals("J") && !letra.equals("j"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 14:
                if (!letra.equals("Z") && !letra.equals("z"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 15:
                if (!letra.equals("S") && !letra.equals("s"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 16:
                if (!letra.equals("Q") && !letra.equals("q"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 17:
                if (!letra.equals("V") && !letra.equals("v"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 18:
                if (!letra.equals("H") && !letra.equals("h"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 19:
                if (!letra.equals("L") && !letra.equals("l"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 20:
                if (!letra.equals("C") && !letra.equals("c"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 21:
                if (!letra.equals("K") && !letra.equals("k"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            case 22:
                if (!letra.equals("E") && !letra.equals("e"))
                    throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
                break;

            default:
                break;
        }

        // Si no salta ninguna excepción quiere decir que la letra está bien, asi que devuelvo "true"
        return true;

    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        // Comprobamos si es nulo o vacio
        if (telefono == null || telefono.equals(""))
            throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");

        // Eliminiamos los posibles espacios en blanco del principio y del final
        telefono = telefono.trim();

        Pattern pat = Pattern.compile(ER_TELEFONO);
        Matcher mat = pat.matcher(telefono);

        if (!mat.matches())
            throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");

        this.telefono = telefono;
    }
}
