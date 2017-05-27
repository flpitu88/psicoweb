/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.config.AdminSecured;
import flpitu88.web.backend.psicoweb.config.ProveedorUsuarioRequestFilter;
import flpitu88.web.backend.psicoweb.dtos.FiltroTurnos;
import flpitu88.web.backend.psicoweb.serviceapis.GeneradorInformeAPI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Component;

/**
 *
 * @author flpitu88
 */
@Path("/informes")
@Component
public class InformesResource {

    private final GeneradorInformeAPI generadorInformesSrv;

    private final ProveedorUsuarioRequestFilter proveedorSrv;

    private static final Logger logger
            = Logger.getLogger(NoticiasResource.class.getName());

    @Inject
    public InformesResource(GeneradorInformeAPI generadorInformesSrv,
            ProveedorUsuarioRequestFilter proveedorSrv) {
        this.generadorInformesSrv = generadorInformesSrv;
        this.proveedorSrv = proveedorSrv;
    }

    @PUT
    @AdminSecured
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerInformeConFiltro(FiltroTurnos filtro) {
        try {
            String username = proveedorSrv.getEmailUsuario();
            logger.log(Level.INFO, "############## El usuario {0} solicita un informe de turnos ##############", username);

            String datos = generadorInformesSrv.generarInformeDeTurnosPDF(filtro);

            return Response
                    .ok(datos, MediaType.TEXT_PLAIN)
                    .build();
        } catch (JRException ex) {
            Logger.getLogger(InformesResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

}
