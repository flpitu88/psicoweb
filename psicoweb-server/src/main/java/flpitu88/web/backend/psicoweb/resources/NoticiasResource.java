/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.config.Secured;
import flpitu88.web.backend.psicoweb.model.Noticia;
import flpitu88.web.backend.psicoweb.serviceapis.NoticiasAPI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author flpitu88
 */
@Path("/noticias")
@Component
public class NoticiasResource {

    private final NoticiasAPI noticiasSrv;

    private static final Logger logger
            = Logger.getLogger(NoticiasResource.class.getName());

    @Inject
    public NoticiasResource(NoticiasAPI noticiasSrv) {
        this.noticiasSrv = noticiasSrv;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{nroNoticia}")
    public Noticia getNoticiaPorNumero(@PathParam("nroNoticia") Integer numero) {
        logger.log(Level.INFO, "Se pide la noticia numero {0}", numero);
        return noticiasSrv.getNoticiaPorNumero(numero);
    }

}
