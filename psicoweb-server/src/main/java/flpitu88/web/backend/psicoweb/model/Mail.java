/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.model;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
@PropertySource({"classpath:contenidoMails.properties"})
public abstract class Mail {

    @Autowired
    private Environment env;

    private List<String> destinatario;

    public List<String> getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(List<String> destinatario) {
        this.destinatario = destinatario;
    }

    public Environment getEnv() {
        return env;
    }

    public abstract String getAsunto();

    public abstract String getMensaje(Turno turno);

}
