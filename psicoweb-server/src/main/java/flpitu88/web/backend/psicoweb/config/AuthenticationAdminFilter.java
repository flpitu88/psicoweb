/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.config;

import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.serviceapis.AutenticacionUtilsAPI;
import java.io.IOException;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author flpitu88
 */
@AdminSecured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationAdminFilter implements ContainerRequestFilter {

    private final AutenticacionUtilsAPI autentUtilsSrv;

    @Autowired
    private ProveedorUsuarioRequestFilter proveedorUsuario;

    @Inject
    public AuthenticationAdminFilter(AutenticacionUtilsAPI autentUtilsSrv) {
        this.autentUtilsSrv = autentUtilsSrv;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Get the HTTP Authorization header from the request
        String authorizationHeader
                = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            // Validate the token
            autentUtilsSrv.validateToken(token);

            Usuario user = autentUtilsSrv.getUsuarioByToken(
                    autentUtilsSrv.decodeToken(token));

            ((ProveedorUsuarioRequest) proveedorUsuario)
                    .setEmailUsuario(user.getMail());

            if (!user.getAdministrador()) {
                throw new NotAuthorizedException("Solo el usuario administrador tiene acceso al recurso");
            }

        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                    .type(MediaType.TEXT_PLAIN)
                    .entity(e.getMessage())
                    .build());
        }
    }

}
