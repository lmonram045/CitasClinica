package org.iesalandalus.programacion.citasclinica;

import javax.naming.OperationNotSupportedException;

public class Citas {
    private int capacidad;
    private int tamano;
    private Cita[] coleccionCitas;


    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    /** Método para insertar una nueva cita */
    public void insertar(Cita cita) throws OperationNotSupportedException {
        // Comprobamos que no sea nula la cita
        if (cita == null)
            throw new NullPointerException("ERROR: No se puede insertar una cita nula.");

        // Utilizo el método capacidadSuperada para no dejar métodos de esta clase sin usar, pero con comprobar si las
        // variables tamaño y capacidad son iguales, es suficiente
        if (capacidadSuperada(tamano))
            throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");

        // Comprobamos que no existe esa cita
        if (!tamanoSuperado(buscarIndice(cita)))
            throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");

        coleccionCitas[tamano] = new Cita(cita);
        tamano++;
    }

    /** Método para buscar un índice */
    private int buscarIndice(Cita cita) {
        int indice = 0;
        // For each para recorrer las citas y sacar buscar un índice
        for (Cita citas : coleccionCitas) {
            if (cita.equals(citas))
                return indice;

            indice++;
        }

        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        return (tamano <= indice);
    }

    private boolean capacidadSuperada(int tamano) {
        return (tamano >= capacidad);
    }

    /** Método para buscar una cita */
    public Cita buscar(Cita cita) {
        if (cita == null)
            throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");

        if (!tamanoSuperado(buscarIndice(cita)))
            return new Cita(coleccionCitas[buscarIndice(cita)]);

        return null;
    }
}
