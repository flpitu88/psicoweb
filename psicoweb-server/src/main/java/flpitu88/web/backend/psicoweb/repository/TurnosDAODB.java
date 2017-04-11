/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.repository;

import flpitu88.web.backend.psicoweb.model.Turno;
import java.time.LocalDate;
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
    public List<Turno> getTurnosRegistrados() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Turno t where t.usuario is not null")
                .list();
    }

    @Override
    public List<Turno> getDiasConTurnosDisponibles() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Turno.class)
                .add(Restrictions.isNull("usuario"))
                .list();
    }

    @Override
    public List<Turno> getTurnosDisponiblesDelDia(LocalDate ld) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Turno.class)
                .add(Restrictions.and(
                        Restrictions.eq("dia", ld),
                        Restrictions.isNull("usuario")))
                .list();
    }

    @Override
    public void actualizarTurno(Turno t) {
        sessionFactory.getCurrentSession()
                .update(t);
    }

    @Override
    public Turno getTurnoLibre(Turno t) {
        return (Turno) sessionFactory.getCurrentSession()
                .createCriteria(Turno.class)
                .add(Restrictions.and(
                        Restrictions.eq("dia", t.getDia()),
                        Restrictions.eq("horario", t.getHorario())))
                .uniqueResult();
    }

    @Override
    public void guardarTurno(Turno t) {
        sessionFactory.getCurrentSession().save(t);
    }
}
