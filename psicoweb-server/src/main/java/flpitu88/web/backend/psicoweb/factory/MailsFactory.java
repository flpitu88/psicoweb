/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.factory;

import flpitu88.web.backend.psicoweb.model.Mail;
import flpitu88.web.backend.psicoweb.model.MailNotificacionCancelarTurno;
import flpitu88.web.backend.psicoweb.model.MailNotificacionTurnoNuevo;
import flpitu88.web.backend.psicoweb.model.Turno;
import java.util.List;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author flavio
 */
@Configuration
@PropertySource({"classpath:mail.properties"})
public class MailsFactory {

    @Autowired
    private static Environment env;

    public static Mail getMail(
            Turno turno,
            List<String> direccionesMail,
            String tipo) throws AddressException {
        switch (tipo) {
            case "nuevoTurno":
                return new MailNotificacionTurnoNuevo(turno, direccionesMail, env);
            case "cancelarTurno":
                return new MailNotificacionCancelarTurno(turno, direccionesMail, env);
            default:
                throw new RuntimeException("### NYI! No existe este tipo de mail!");
        }
    }

}
