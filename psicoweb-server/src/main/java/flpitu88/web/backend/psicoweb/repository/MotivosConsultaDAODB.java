/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.repository;

import flpitu88.web.backend.psicoweb.model.MotivoConsulta;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author flpitu88
 */
@Repository
public class MotivosConsultaDAODB implements MotivosConsultaDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<MotivoConsulta> getMotivosDeConsulta() {
        return sessionFactory.getCurrentSession()
                .createQuery("from MotivoConsulta")
                .list();
    }

}
