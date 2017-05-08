/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.model;

import java.util.List;
import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
public abstract class Mail {

    private Properties prop;

    private InternetAddress[] destinatarios;

    public Mail(InternetAddress[] destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Mail(List<String> direccionesMail, Properties prop) throws AddressException {
        destinatarios = new InternetAddress[direccionesMail.size()];
        int i = 0;
        for (String dirMail : direccionesMail) {
            destinatarios[i] = new InternetAddress(dirMail);
            i++;
        }
        this.prop = prop;
    }

    public InternetAddress[] getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(InternetAddress[] destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Properties getProp() {
        return prop;
    }

    public abstract String getAsunto();

    public abstract String getMensaje();
}
