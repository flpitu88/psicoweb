/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.factory;

import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.dtos.UsuarioDTO;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author flavio
 */
@Configuration
public class BeansFactory {

// ##########################################
//    FACTORY PARA CONVERSION DE DTO TURNOS
// ##########################################
    public static Turno convertirDTOATurno(TurnoDTO turnoBean, Usuario usuario) {
        Turno turno = new Turno(
                null,
                FormatterFecha.crearFechaDesdeString(turnoBean.getDia()),
                FormatterHora.crearHoraDesdeString(turnoBean.getHora()),
                usuario,
                null);
        return turno;
    }

    public static TurnoDTO convertirTurnoADTO(Turno t) {
        TurnoDTO turnoDTO = new TurnoDTO(
                t.getId(),
                FormatterFecha.crearStringDesdeLocalDateISO(t.getDia()),
                FormatterHora.crearStringDesdeLocalTimeISO(t.getHorario()),
                (t.getUsuario() != null) ? t.getUsuario().getNombreCompleto() : "Disponible",
                t.getMotivo());
        return turnoDTO;
    }

// ##########################################
//    FACTORY PARA CONVERSION DE DTO USUARIOS
// ##########################################
    public static Usuario convertirDTOAUsuario(UsuarioDTO bean) {
        Usuario usuario = new Usuario(
                null,
                bean.getDni(),
                bean.getNombre(),
                bean.getApellido(),
                bean.getEmail(),
                FormatterFecha.crearFechaNacimientoDesdeString(bean.getFechaNacimiento()),
                bean.getAdministrador(),
                bean.getPassword());
        return usuario;
    }

    public static UsuarioDTO convertirUsuarioADTO(Usuario user) {
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                user.getId(),
                user.getDni(),
                user.getNombre(),
                user.getApellido(),
                null,
                user.getMail(),
                FormatterFecha.crearStringDesdeLocalDateISO(user.getFechaNacimiento()),
                user.getAdministrador());
        return usuarioDTO;
    }
}
