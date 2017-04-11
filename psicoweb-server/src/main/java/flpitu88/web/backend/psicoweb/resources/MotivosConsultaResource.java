/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.config.Secured;
import flpitu88.web.backend.psicoweb.model.MotivoConsulta;
import flpitu88.web.backend.psicoweb.serviceapis.MotivosConsultaAPI;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author flpitu88
 */
@Path("/motivosConsulta")
@Component
public class MotivosConsultaResource {

    private final MotivosConsultaAPI motivosConsultaSrv;

    private static final Logger logger
            = Logger.getLogger(MotivosConsultaResource.class.getName());

    @Inject
    public MotivosConsultaResource(MotivosConsultaAPI motivosConsultaSrv) {
        this.motivosConsultaSrv = motivosConsultaSrv;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public List<MotivoConsulta> getMotivosDeConsulta() {
        logger.info("Se solicitan los motivos posibles de una consulta");
        return motivosConsultaSrv.getMotivosDeConsulta();
    }

}
