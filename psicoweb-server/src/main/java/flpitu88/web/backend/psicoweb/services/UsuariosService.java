/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.serviceapis.UsuariosAPI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flpitu88
 */
@Service
public class UsuariosService implements UsuariosAPI {

    @Autowired
    private UsuariosDAO usuariosDAO;

    @Override
    @Transactional(readOnly = false)
    public void guardarUsuario(Usuario u) {
        usuariosDAO.guardarUsuario(u);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioById(Integer id) {
        return usuariosDAO.getUsuarioById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuariosNoAdministradores() {
        return usuariosDAO.getUsuariosNoAdministradores();
    }

}
