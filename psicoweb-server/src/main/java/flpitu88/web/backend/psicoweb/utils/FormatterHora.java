/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.utils;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author flpitu88
 */
public class FormatterHora {

    private static final String zoneId = "America/Buenos_Aires";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public static LocalTime crearHoraDesdeString(String horaString) {
        formatter.withZone(ZoneId.of(zoneId));
        return LocalTime.parse(horaString, formatter);
    }

    public static String crearStringDesdeLocalTimeISO(LocalTime lt) {
        formatter.withZone(ZoneId.of(zoneId));
        return lt.format(formatter);
    }
    
    public static String crearStringDesdeLocalTime(LocalTime ld){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        return ld.format(format);
    }
}
