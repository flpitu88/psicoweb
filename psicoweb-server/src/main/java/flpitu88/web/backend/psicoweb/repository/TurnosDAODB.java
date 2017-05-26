/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.repository;

import flpitu88.web.backend.psicoweb.dtos.FiltroTurnos;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
                .createQuery("from Turno t where t.usuario is null")
                .list();
    }

    @Override
    public List<Turno> getTurnosDisponiblesDelDia(LocalDate ld) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Turno t where t.dia = :dia and t.usuario is null")
                .setParameter("dia", ld)
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
                .createQuery("from Turno t where t.dia = :dia and t.horario = :horario")
                .setParameter("dia", t.getDia())
                .setParameter("horario", t.getHorario())
                .uniqueResult();
    }

    @Override
    public void guardarTurno(Turno t) {
        sessionFactory.getCurrentSession().save(t);
    }

    @Override
    public List<Turno> obtenerTurnosDeUsuario(Usuario u) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Turno t where t.usuario = :usuario order by t.dia asc")
                .setParameter("usuario", u)
                .list();
    }

    @Override
    public Turno getTurnoPorId(Integer id) {
        return (Turno) sessionFactory.getCurrentSession()
                .createQuery("from Turno t where t.id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Turno> getTurnosConFiltro(FiltroTurnos filtro) {
        String query = "from Turno t ";

        if (filtro.getFechaDesde() != null || filtro.getFechaHasta() != null || filtro.getPaciente() != null) {
            query += "where ";
            int i = 0;

            if (filtro.getFechaDesde() != null) {
                query += "t.dia > :fechaDesde";
                i++;
            }
            if (filtro.getFechaHasta() != null) {
                if (i > 0) {
                    query += " and ";
                }
                query += "t.dia < :fechaHasta";
                i++;
            }
            if (filtro.getPaciente() != null) {
                if (i > 0) {
                    query += " and ";
                }
                query += "t.usuario.id = :paciente";
            }
        }

        query += " order by t.id";

        return sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("fechaDesde", FormatterFecha.crearFechaNacimientoDesdeString(filtro.getFechaDesde()))
                .setParameter("fechaHasta", FormatterFecha.crearFechaNacimientoDesdeString(filtro.getFechaHasta()))
                .setParameter("paciente", filtro.getPaciente())
                .list();
    }
}
