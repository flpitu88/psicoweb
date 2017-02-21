/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.dtos;

import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;

/**
 *
 * @author flpitu88
 */
public class TurnoDTO {

    private String dia;
    private String hora;

    public TurnoDTO() {
    }

    public TurnoDTO(String dia, String hora) {
        this.dia = dia;
        this.hora = hora;
    }

    public TurnoDTO(Turno t) {
        this.dia = FormatterFecha.crearStringDesdeLocalDate(t.getDia());
        this.hora = FormatterHora.crearStringDesdeLocalTime(t.getHorario());
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

}
