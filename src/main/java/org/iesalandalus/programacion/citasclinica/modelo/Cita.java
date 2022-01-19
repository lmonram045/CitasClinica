package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {
    public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy hh:mm";
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

        setPaciente(cita.paciente);
        setFechaHora(cita.fechaHora);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cita cita = (Cita) o;
        return Objects.equals(fechaHora, cita.fechaHora) && Objects.equals(paciente, cita.paciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fechaHora, paciente);
    }

    @Override
    public String toString() {
        return paciente + ", fechaHora=" + getFechaHora().format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA));
    }
}

