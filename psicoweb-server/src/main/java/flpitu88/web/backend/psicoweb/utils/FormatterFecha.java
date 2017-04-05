/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author flpitu88
 */
public class FormatterFecha {

    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public static LocalDate crearFechaDesdeString(String horaString) {
        return LocalDateTime.parse(horaString, formatter).toLocalDate();
    }

    public static String crearStringDesdeLocalDateISO(LocalDate ld) {
        return ld.format(formatter);
    }
    
    public static String crearStringDesdeLocalDate(LocalDate ld){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return ld.format(format);
    }

}
