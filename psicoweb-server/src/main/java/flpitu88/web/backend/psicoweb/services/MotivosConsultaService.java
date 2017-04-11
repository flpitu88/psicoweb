/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.model.MotivoConsulta;
import flpitu88.web.backend.psicoweb.repository.MotivosConsultaDAO;
import flpitu88.web.backend.psicoweb.serviceapis.MotivosConsultaAPI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flpitu88
 */
@Service
public class MotivosConsultaService implements MotivosConsultaAPI {

    @Autowired
    private MotivosConsultaDAO motivosConsultaDAO;

    @Override
    @Transactional(readOnly = true)
    public List<MotivoConsulta> getMotivosDeConsulta() {
        return motivosConsultaDAO.getMotivosDeConsulta();
    }

}
