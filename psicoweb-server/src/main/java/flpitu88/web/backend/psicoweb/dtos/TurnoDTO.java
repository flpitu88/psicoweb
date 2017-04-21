/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.dtos;

import flpitu88.web.backend.psicoweb.model.MotivoConsulta;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;

/**
 *
 * @author flpitu88
 */
public class TurnoDTO {

    private Integer id;
    private String dia;
    private String hora;
    private String usuario;
    private MotivoConsulta motivo;

    public TurnoDTO() {
    }

    public TurnoDTO(Integer id, String dia, String hora, String usuario, MotivoConsulta motivo) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.usuario = usuario;
        this.motivo = motivo;
    }

    public TurnoDTO(Turno t) {
        this.id = t.getId();
        this.dia = FormatterFecha.crearStringDesdeLocalDateISO(t.getDia());
        this.hora = FormatterHora.crearStringDesdeLocalTimeISO(t.getHorario());
        this.usuario = (t.getUsuario() != null) ? t.getUsuario().getNombreCompleto() : "Disponible";
        this.motivo = t.getMotivo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public MotivoConsulta getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoConsulta motivo) {
        this.motivo = motivo;
    }

}
