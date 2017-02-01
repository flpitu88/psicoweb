/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.serviceapis;

import flpitu88.web.backend.psicoweb.model.Usuario;
import java.util.List;

/**
 *
 * @author flpitu88
 */
public interface UsuariosAPI {

    public void guardarUsuario(Usuario u);

    public Usuario getUsuarioById(Integer id);

    public List<Usuario> getUsuarios();

}
