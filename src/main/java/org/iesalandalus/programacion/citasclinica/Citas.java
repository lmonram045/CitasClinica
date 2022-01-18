package org.iesalandalus.programacion.citasclinica;

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
}
