/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.serviceapis.UsuariosAPI;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @GET
    @Path("/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioPorId(@PathParam("idUsuario") String idUsuario) {
        Integer idUser = Integer.parseInt(idUsuario);
        return usuarioSrv.getUsuarioById(idUser);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void guardarUsuario(Usuario usuario) {
        usuarioSrv.guardarUsuario(usuario);
    }

}
