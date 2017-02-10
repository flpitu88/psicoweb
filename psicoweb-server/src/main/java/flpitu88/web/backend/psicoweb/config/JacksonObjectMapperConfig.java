/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.config;
 
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
 
@Provider
public class JacksonObjectMapperConfig
        implements ContextResolver<ObjectMapper> {
 
    private static final ObjectMapper OBJECT_MAPPER
        = new ObjectMapper()               
        .disable(MapperFeature.AUTO_DETECT_CREATORS)
        .disable(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
 
    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return OBJECT_MAPPER;
    }
}