/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author flpitu88
 */
public class FormatterFecha {

    private static DateTimeFormatter formatterISO_DATE = DateTimeFormatter.ISO_DATE;
    private static DateTimeFormatter formatterISO_DATETIME = DateTimeFormatter.ISO_DATE_TIME;
    private static DateTimeFormatter formatterPers = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate crearFechaDesdeString(String diaString) {
        try {
            return LocalDate.parse(diaString, formatterISO_DATE);
        } catch (DateTimeParseException ex) {
            return LocalDate.parse(diaString, formatterPers);
        }
    }

    public static LocalDate crearFechaNacimientoDesdeString(String diaString) {
        return LocalDate.parse(diaString, formatterISO_DATETIME);
    }

    public static String crearStringDesdeLocalDateISO(LocalDate ld) {
        return ld.format(formatterISO_DATE);
    }

    public static String crearStringDesdeLocalDate(LocalDate ld) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ld.plusDays(1).format(format);
    }

}
