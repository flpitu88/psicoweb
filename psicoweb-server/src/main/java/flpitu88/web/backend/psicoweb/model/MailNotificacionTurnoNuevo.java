/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.model;

import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;
import java.text.MessageFormat;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
public class MailNotificacionTurnoNuevo extends Mail {

    private Turno turnoNuevo;

    public MailNotificacionTurnoNuevo(
            Turno turno,
            List<String> direccionesMail,
            Environment env) throws AddressException {
        super(direccionesMail, env);
        this.turnoNuevo = turno;
    }

    public MailNotificacionTurnoNuevo(Turno turnoNuevo, InternetAddress[] destinatarios) {
        super(destinatarios);
        this.turnoNuevo = turnoNuevo;
    }

    public Turno getTurnoNuevo() {
        return turnoNuevo;
    }

    public void setTurnoNuevo(Turno turnoNuevo) {
        this.turnoNuevo = turnoNuevo;
    }

    @Override
    public String getMensaje() {
        return MessageFormat.format(getEnv().getProperty("mailNotificacionNuevoTurno.cuerpo"),
                FormatterFecha.crearStringDesdeLocalDate(turnoNuevo.getDia()),
                FormatterHora.crearStringDesdeLocalTime(turnoNuevo.getHorario()),
                getEnv().getProperty("direccionConsultorio"));
    }

    @Override
    public String getAsunto() {
        return getEnv().getProperty("mailNotificacionNuevoTurno.asunto");
    }

}
