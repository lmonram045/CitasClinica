package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cita {
    private static final String FORMATO_FECHA_HORA = "dd/mm/yyyy hh:mm";
    private LocalDateTime fechaHora;
    private Paciente paciente;

    /** Constructor con parametros */
    public Cita(Paciente paciente, LocalDateTime fechaHora) {
        setPaciente(paciente);
        setFechaHora(fechaHora);
    }

    /** Contructor copia */
    public Cita(Cita cita) {
        if (cita == null)
            throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
    }

    private void setPaciente(Paciente paciente) {
        if (paciente == null)
            throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");

        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return new Paciente(paciente);
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null)
            throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");

        this.fechaHora = fechaHora;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

}
/*
    // Variable para poder mostrar la fecha y hora en el formato que queremos
    DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/mm/yy hh:mm");

        fechaHora = fechaHora.format(f);*/