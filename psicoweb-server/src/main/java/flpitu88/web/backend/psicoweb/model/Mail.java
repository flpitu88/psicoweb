/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.model;

import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
public abstract class Mail {

    private Environment env;

    private InternetAddress[] destinatarios;

    public Mail(InternetAddress[] destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Mail(List<String> direccionesMail, Environment env) throws AddressException {
        destinatarios = new InternetAddress[direccionesMail.size()];
        int i = 0;
        for (String dirMail : direccionesMail) {
            destinatarios[i] = new InternetAddress(dirMail);
            i++;
        }
        this.env = env;
    }

    public InternetAddress[] getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(InternetAddress[] destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Environment getEnv() {
        return env;
    }

    public abstract String getAsunto();

    public abstract String getMensaje();
}
