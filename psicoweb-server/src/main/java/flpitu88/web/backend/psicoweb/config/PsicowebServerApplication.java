/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.config;

import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author flpitu88
 */
@javax.ws.rs.ApplicationPath("webresources")
public class PsicowebServerApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(JacksonFeature.class);
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(flpitu88.web.backend.psicoweb.config.AuthenticationAdminFilter.class);
        resources.add(flpitu88.web.backend.psicoweb.config.AuthenticationFilter.class);
        resources.add(flpitu88.web.backend.psicoweb.config.JacksonObjectMapperConfig.class);
        resources.add(flpitu88.web.backend.psicoweb.resources.AutenticacionResource.class);
        resources.add(flpitu88.web.backend.psicoweb.resources.NoticiasResource.class);
        resources.add(flpitu88.web.backend.psicoweb.resources.TurnosResource.class);
        resources.add(flpitu88.web.backend.psicoweb.resources.UsuariosResource.class);
    }

}
