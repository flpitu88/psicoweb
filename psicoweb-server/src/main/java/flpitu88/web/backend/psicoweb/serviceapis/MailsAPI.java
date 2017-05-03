/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.serviceapis;

import flpitu88.web.backend.psicoweb.model.Mail;
import flpitu88.web.backend.psicoweb.model.MailNotificacionCancelarTurno;
import flpitu88.web.backend.psicoweb.model.MailNotificacionTurnoNuevo;
import flpitu88.web.backend.psicoweb.model.Turno;
import java.util.List;
import javax.mail.internet.AddressException;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
public interface MailsAPI {

    public void enviarMail(Mail mail);

    public String getMailDeAdministradora();

    public MailNotificacionCancelarTurno crearMailNotificacionCancelarTurno(Turno turnoCancelado,
            List<String> direccionesMail,
            Environment env)
            throws AddressException;

    public MailNotificacionTurnoNuevo crearMailNotificacionNuevoTurno(
            Turno turnoCancelado,
            List<String> direccionesMail,
            Environment env)
            throws AddressException;
}
