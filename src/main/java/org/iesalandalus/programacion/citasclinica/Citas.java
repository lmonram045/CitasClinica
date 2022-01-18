package org.iesalandalus.programacion.citasclinica;

public class Citas {
    private int capacidad;
    private int tamano;

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    private boolean tamanoSuperado(int indice) {
        return (tamano <= indice);
    }

    private boolean capacidadSuperada(int tamano) {
        return (tamano >= capacidad);
    }
}
