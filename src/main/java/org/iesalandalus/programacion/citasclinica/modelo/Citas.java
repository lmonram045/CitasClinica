package org.iesalandalus.programacion.citasclinica.modelo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Citas {
    private int capacidad;
    private int tamano;
    private Cita[] coleccionCitas;

    public Citas(int capacidad) {
        if (capacidad < 1)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        tamano = 0;
        this.capacidad = capacidad;
        coleccionCitas = new Cita[capacidad];
    }

    /** Método para devolver una copia de las citas
     *
     * @return
     */
    public Cita[] getCitas() {
        Cita[] citasCopia = new Cita[capacidad];

        int i = 0;
        while (!tamanoSuperado(i)) {
            citasCopia[i] = new Cita(coleccionCitas[i]);
            i++;
        }

        return citasCopia;
    }

    /** VOY POR AQUI REVISAR BIEN */
    public Cita[] getCitas(LocalDate fecha) {
        if (fecha == null)
            throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");

        // Array para guardar salida auxiliar de prestamos
        Cita[] auxCitas = new Cita[capacidad];
        // Variable para controlar el array auxiliar creado anteriormente
        int j = 0;
        LocalDateTime inicioDia = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), 0, 0, 0);
        LocalDateTime finDia = LocalDateTime.of(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth(), 23, 59, 59);

        for (int i = 0; !tamanoSuperado(i); i++) {
            // Obtenemos la citas para el día correspondiente
            if (coleccionCitas[i].getFechaHora().isAfter(inicioDia)
                    && coleccionCitas[i].getFechaHora().isBefore(finDia)) {
                auxCitas[j] = new Cita(coleccionCitas[i]);
                j++;
            }
        }
        return auxCitas;
    }
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

    /** Método para borrar una cita */
    public void borrar(Cita cita) throws OperationNotSupportedException {
        // Comprobamos que no sea nula
        if (cita == null)
            throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");

        // guardamos el índice de la cita
        int indice = buscarIndice(cita);

        // comprobamos que la cita existe
        if (tamanoSuperado(indice))
            throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");

        // La borramos y desplazando el array hacia la izquierda.
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    /** Método para desplazar una posción el array hacia la izquierda (se usará al borrar un índice). */
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionCitas[i] = coleccionCitas[i + 1];
            if (tamanoSuperado(i + 1))
                coleccionCitas[i] = null;
        }

        tamano--;
    }
}
