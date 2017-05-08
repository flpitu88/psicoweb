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
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.mail.internet.AddressException;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author flavio
 */
@Configuration
public class MailsFactory {    

    public static Mail getMail(
            Turno turno,
            List<String> direccionesMail,
            String tipo) throws AddressException {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            String filename = "contenidoMails.properties";
            input = MailsFactory.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                throw new RuntimeException("##### ERROR: No encontre el property file!");
            }

            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        switch (tipo) {
            case "nuevoTurno":
                return new MailNotificacionTurnoNuevo(turno, direccionesMail, prop);
            case "cancelarTurno":
                return new MailNotificacionCancelarTurno(turno, direccionesMail, prop);
            default:
                throw new RuntimeException("### NYI! No existe este tipo de mail!");
        }
    }

}
