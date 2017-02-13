/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.serviceapis.AutenticacionUtilsAPI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author flpitu88
 */
@Path("/autenticacion")
public class AutenticacionResource {

    private final AutenticacionUtilsAPI autentUtilsSrv;

    private static final Logger logger
            = Logger.getLogger(AutenticacionResource.class.getName());

    @Inject
    public AutenticacionResource(AutenticacionUtilsAPI autentUtilsSrv) {
        this.autentUtilsSrv = autentUtilsSrv;
    }

    @POST
    @Produces("text/plain")
    @Consumes("application/x-www-form-urlencoded")
    public String authenticateUser(@FormParam("email") String email,
            @FormParam("password") String password) throws Exception {

        logger.log(Level.INFO,
                "############## El usuario {0} solicita iniciar sesion ##############",
                email);

        // Authenticate the user using the credentials provided
        autentUtilsSrv.authenticate(email, password);

        // Issue a token for the user
        String token = autentUtilsSrv.issueToken(email);

        // Return the token on the response
        return token;

    }

}
