/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.model.Mail;
import flpitu88.web.backend.psicoweb.model.MailNotificacionCancelarTurno;
import flpitu88.web.backend.psicoweb.model.MailNotificacionTurnoNuevo;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.serviceapis.MailsAPI;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *
 * @author flpitu88
 */
@Service
@PropertySource({"classpath:mail.properties"})
public class MailsService implements MailsAPI {

    @Autowired
    private Environment env;

    private static final Logger logger
            = Logger.getLogger(MailsService.class.getName());

    @Override
    public void enviarMail(Mail mail) {
        final String username = env.getProperty("direccionMail");
        final String password = env.getProperty("clave");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    mail.getDestinatarios());
            message.setSubject(mail.getAsunto());
            message.setText(mail.getMensaje());

            Transport.send(message);

            logger.log(Level.INFO,
                    "Notificacion {0} enviada a la direccion {1}",
                    new Object[]{mail.getClass().getSimpleName(), mail.getDestinatarios()});
        } catch (MessagingException e) {
            logger.log(Level.SEVERE,
                    "######### ERROR: Al enviar Notificacion {0} a la direccion {1}",
                    new Object[]{mail.getClass().getSimpleName(), mail.getDestinatarios()});
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getMailDeAdministradora() {
        return env.getProperty("direccionMail");
    }

    @Override
    public MailNotificacionCancelarTurno crearMailNotificacionCancelarTurno(
            Turno turnoCancelado,
            List<String> direccionesMail,
            Environment env) throws AddressException {
        return new MailNotificacionCancelarTurno(turnoCancelado, direccionesMail, env);
    }

    @Override
    public MailNotificacionTurnoNuevo crearMailNotificacionNuevoTurno(Turno turnoCancelado,
            List<String> direccionesMail,
            Environment env) throws AddressException {
        return new MailNotificacionTurnoNuevo(turnoCancelado, direccionesMail, env);
    }

}
