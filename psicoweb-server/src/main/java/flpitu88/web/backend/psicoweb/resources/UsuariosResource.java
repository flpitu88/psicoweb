/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.dtos.UsuarioDTO;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.serviceapis.UsuariosAPI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.stereotype.Component;

/**
 *
 * @author flpitu88
 */
@Path("/usuarios")
@Component
public class UsuariosResource {

    private final UsuariosAPI usuarioSrv;

    private static final Logger logger
            = Logger.getLogger(UsuariosResource.class.getName());

    @Inject
    public UsuariosResource(UsuariosAPI usuarioSrv) {
        this.usuarioSrv = usuarioSrv;
    }

    @POST
    @Produces("text/plain")
    @Consumes("application/json")
    public void guardarUsuario(UsuarioDTO bean) {

        Usuario usuario = new Usuario(bean);
        usuarioSrv.guardarUsuario(usuario);

        logger.log(Level.INFO,
                "############## Se registra el nuevo usuario {0} {1} ##############",
                new Object[]{bean.getNombre(), bean.getApellido()});
    }

}
