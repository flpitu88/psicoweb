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
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
public class MailNotificacionCancelarTurno extends Mail {

    private Turno turnoCancelado;

    public MailNotificacionCancelarTurno(
            Turno turnoCancelado,
            List<String> direccionesMail,
            Environment env) throws AddressException {
        super(direccionesMail, env);
        this.turnoCancelado = turnoCancelado;
    }

    public Turno getTurnoCancelado() {
        return turnoCancelado;
    }

    public void setTurnoCancelado(Turno turnoCancelado) {
        this.turnoCancelado = turnoCancelado;
    }

    @Override
    public String getMensaje() {
        return MessageFormat.format(getEnv().getProperty("mailNotificacionCancelarTurno.cuerpo"),
                FormatterFecha.crearStringDesdeLocalDate(turnoCancelado.getDia()),
                FormatterHora.crearStringDesdeLocalTime(turnoCancelado.getHorario()));
    }

    @Override
    public String getAsunto() {
        return getEnv().getProperty("mailNotificacionCancelarTurno.asunto");
    }

}
