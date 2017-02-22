    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.repository.TurnosDAO;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.serviceapis.TurnosAPI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flpitu88
 */
@Service
public class TurnosService implements TurnosAPI {

    @Autowired
    private TurnosDAO turnosDAO;

    @Autowired
    private UsuariosDAO usuariosDAO;

    @Override
    @Transactional(readOnly = false)
    public void registrarTurno(TurnoDTO tBean, String email) {
        Usuario usuario = usuariosDAO.getUsuarioByMail(email);
        Turno turno = new Turno(tBean, usuario);
        turnosDAO.guardarTurno(turno);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Turno> getTurnos() {
        return turnosDAO.getTurnos();
    }

}
