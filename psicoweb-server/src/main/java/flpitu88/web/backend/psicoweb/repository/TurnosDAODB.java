/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.repository;

import flpitu88.web.backend.psicoweb.model.Turno;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author flpitu88
 */
@Repository
public class TurnosDAODB implements TurnosDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void guardarTurno(Turno t) {
        sessionFactory.getCurrentSession()
                .save(t);
    }

    @Override
    public List<Turno> getTurnos() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Turno")
                .list();
    }

    @Override
    public List<Turno> getTurnosDisponibles() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Turno.class)
                .add(Restrictions.isNull("usuario"))
                .list();
    }
}
