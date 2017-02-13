/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.dtos.UsuarioDTO;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.serviceapis.UsuariosAPI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuariosRegistrados() {
        return usuarioSrv.getUsuarios();
    }

    @POST
    @Produces("text/plain")
    @Consumes("application/x-www-form-urlencoded")
    public void guardarUsuario(@FormParam("nombre") String nombre,
            @FormParam("apellido") String apellido,
            @FormParam("dni") String dni,
            @FormParam("email") String email,
            @FormParam("fechaNacimiento") String fechaNac,
            @FormParam("password") String password) {

        UsuarioDTO bean = new UsuarioDTO(dni, nombre, apellido,
                password, email, fechaNac, Boolean.FALSE);

        Usuario usuario = new Usuario(bean);
        usuarioSrv.guardarUsuario(usuario);

        logger.log(Level.INFO,
                "############## Se registra el nuevo usuario {0} {1} ##############",
                new Object[]{nombre, apellido});
    }

}
