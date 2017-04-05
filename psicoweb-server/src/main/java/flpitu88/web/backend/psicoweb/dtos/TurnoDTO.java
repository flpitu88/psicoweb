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
    private String usuario;

    public TurnoDTO() {
    }

    public TurnoDTO(String dia, String hora, String usuario) {
        this.dia = dia;
        this.hora = hora;
        this.usuario = usuario;
    }

    public TurnoDTO(Turno t) {
        this.dia = FormatterFecha.crearStringDesdeLocalDateISO(t.getDia());
        this.hora = FormatterHora.crearStringDesdeLocalTimeISO(t.getHorario());
        this.usuario = (t.getUsuario() != null) ? t.getUsuario().getNombreCompleto() : "Disponible";
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
