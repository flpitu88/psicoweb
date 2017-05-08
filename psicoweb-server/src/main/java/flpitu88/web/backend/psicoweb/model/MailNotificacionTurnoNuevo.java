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
import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author flpitu88
 */
public class MailNotificacionTurnoNuevo extends Mail {

    private Turno turnoNuevo;

    public MailNotificacionTurnoNuevo(
            Turno turno,
            List<String> direccionesMail,
            Properties prop) throws AddressException {
        super(direccionesMail, prop);
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
        return MessageFormat.format(getProp().getProperty("mailNotificacionNuevoTurno.cuerpo"),
                FormatterFecha.crearStringDesdeLocalDate(turnoNuevo.getDia()),
                FormatterHora.crearStringDesdeLocalTime(turnoNuevo.getHorario()),
                getProp().getProperty("direccionConsultorio"));
    }

    @Override
    public String getAsunto() {
        return getProp().getProperty("mailNotificacionNuevoTurno.asunto");
    }

}
