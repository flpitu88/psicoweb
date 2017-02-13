/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.config;

import org.springframework.stereotype.Service;

/**
 *
 * @author flavio
 */
@Service
public class ProveedorUsuarioRequest implements ProveedorUsuarioRequestFilter {

    private String usuario;

    @Override
    public String getNombreUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
