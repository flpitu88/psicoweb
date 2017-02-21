/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.config.ProveedorUsuarioRequestFilter;
import flpitu88.web.backend.psicoweb.config.Secured;
import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.serviceapis.TurnosAPI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author flpitu88
 */
@Path("/turnos")
@Component
public class TurnosResource {

    private final TurnosAPI turnosSrv;

    private final ProveedorUsuarioRequestFilter proveedorUsuarioSrv;

    private static final Logger logger
            = Logger.getLogger(TurnosResource.class.getName());

    @Inject
    public TurnosResource(TurnosAPI turnosSrv,
            ProveedorUsuarioRequestFilter proveedorUsuarioSrv) {
        this.turnosSrv = turnosSrv;
        this.proveedorUsuarioSrv = proveedorUsuarioSrv;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured
    public void registrarTurno(TurnoDTO turnoBean) {
        String emailUser = proveedorUsuarioSrv.getEmailUsuario();
        turnosSrv.registrarTurno(turnoBean, emailUser);
        logger.log(Level.INFO, "------ El usuario {0} registra un turno para el dia {1} en el horario {2} ---------",
                new Object[]{emailUser, turnoBean.getDia(), turnoBean.getHora()});
    }
    
    

}
