/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.repository;

import flpitu88.web.backend.psicoweb.model.Usuario;
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
public class UsuariosDAODB implements UsuariosDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void guardarUsuario(Usuario u) {
        sessionFactory.getCurrentSession()
                .save(u);
    }

    @Override
    public Usuario getUsuarioById(Integer id) {
        return sessionFactory.getCurrentSession()
                .get(Usuario.class, id);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Usuario")
                .list();
    }

    @Override
    public Usuario getUsuarioByMail(String email) {
        return (Usuario) sessionFactory.getCurrentSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("mail", email))
                .uniqueResult();
    }

}
