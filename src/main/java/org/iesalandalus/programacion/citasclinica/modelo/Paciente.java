package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
    private static final String ER_DNI = "[0-9]{7,8}[A-Z a-z]"; // Se puede mejorar
    private static final String ER_TELEFONO = "(\\+34|0034|34)?[ -]*(6|8|9)[ -]*([0-9][ -]*){8}";
    private String nombre;
    private String dni;
    private String telefono;

    /** Constructor por parámetros */
    public Paciente(String nombre, String dni, String telefono) {
        // Llamo a los métodos set, que ya hacen las comprobaciones necesarias
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    /** Constructor copia */
    public Paciente(Paciente paciente) {
        // Primero comprobamos que no se recibe un paciente, dni o teléfono nulo
        if (paciente == null)
            throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");

        setNombre(paciente.nombre);
        setDni(paciente.dni);
        setTelefono(paciente.telefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // Primero comprobamos si es nulo o vacío
        if (nombre == null || nombre.trim().equals(""))
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
        if (dni == null || dni.trim().isEmpty())
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

        // Declaro un array, en el que coincide el resto, con la posición en el array.
        String[] arrayDniLetra = {"t","r","w","a","g","m","y","f","p","d","x","b","n","j","z","s","q","v","h","l","c","k","e"};

        // Convierto el numero del dni en un entero
        int numDni = Integer.parseInt(dniAux);

        // Compruebo la letra
        String letra = dni.substring(dni.length() - 1).toLowerCase(); // Primero saco la letra de el string de el dni a una variable y la paso a minuscula
        // La letra del dni se asigna a partir de el resto de dividir el numero entre 23
        int resto = numDni % 23;

        // Compruebo que la letra introducida es correcta
        if (!arrayDniLetra[resto].equals(letra)) // Si no es correcta salta la excepción
            throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");

        // Si no salta ninguna excepción quiere decir que la letra está bien, asi que devuelvo "true"
        return true;

    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        // Comprobamos si es nulo o vacio
        if (telefono == null || telefono.trim().equals(""))
            throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");

        // Eliminiamos los posibles espacios en blanco del principio y del final
        telefono = telefono.trim();

        Pattern pat = Pattern.compile(ER_TELEFONO);
        Matcher mat = pat.matcher(telefono);

        if (!mat.matches())
            throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");

        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(nombre, paciente.nombre) && Objects.equals(dni, paciente.dni) && Objects.equals(telefono, paciente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono);
    }

    /** Método getIniciales */
    private String getIniciales() {
        // Creo variable para guardar iniciales
        String iniciales = "";
        // pongo el nombre en formato correcto
        String nombreFormateado = formateaNombre(nombre);
        // Creo un array para dividir el nombre
        String[] partesNombre = nombreFormateado.split(" ");
        // Recorro el array para que vaya guardando las iniciales del nombre.
        for (String partes : partesNombre) {
            iniciales += partes.substring(0, 1);
        }
        return iniciales;
    }

    @Override
    public String toString() {
        return String.format("nombre=%s (%s), DNI=%s, teléfono=%s", formateaNombre(nombre), getIniciales(), dni,
                telefono);
    }

}
