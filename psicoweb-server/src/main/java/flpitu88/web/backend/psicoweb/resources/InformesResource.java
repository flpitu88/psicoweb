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
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
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

    @GET
    @AdminSecured
    @Produces("application/pdf")
    public Response obtenerInformeConFiltro(FiltroTurnos filtro) {
        String username = proveedorSrv.getEmailUsuario();
        logger.log(Level.INFO, "############## El usuario {0} solicita un informe de turnos ##############", username);
        try {
            StreamingOutput fileStream = new StreamingOutput() {

                public void write(java.io.OutputStream output) throws IOException, WebApplicationException {

                    byte[] datos = null;
                    try {
                        datos = Base64.getDecoder().decode(generadorInformesSrv.generarInformeDeTurnosPDF(filtro));
                    } catch (JRException ex) {
                        Logger.getLogger(InformesResource.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    output.write(datos);
                    output.flush();
                }
            };
            return Response
                    .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "attachment; filename = factura.pdf")
                    .build();
        } catch (Exception ex) {
            logger.severe("############## Error al generar informe ##############");
            logger.log(Level.SEVERE, "---### Exception ###---: {0}", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al generar informe de turnos").build();
        }
    }

}
