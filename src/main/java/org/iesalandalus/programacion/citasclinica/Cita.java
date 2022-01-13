package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDateTime;

public class Cita {
    // por lo pronto voy a fomratear la hora como dd/mm/yyyy hh:mm o dd-mm-yyyy hh:mm
    private static final String FORMATO_FECHA_HORA = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})(\\s)([0-1][0-9]|2[0-3])(:)([0-5][0-9])$";
    private LocalDateTime fechaHora;

}
