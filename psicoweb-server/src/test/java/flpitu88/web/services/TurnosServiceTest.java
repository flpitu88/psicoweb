/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.services;

import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.model.MailNotificacionCancelarTurno;
import flpitu88.web.backend.psicoweb.model.MailNotificacionTurnoNuevo;
import flpitu88.web.backend.psicoweb.model.MotivoConsulta;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.repository.MotivosConsultaDAO;
import flpitu88.web.backend.psicoweb.repository.TurnosDAO;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.serviceapis.MailsAPI;
import flpitu88.web.backend.psicoweb.services.TurnosService;
import javax.mail.internet.AddressException;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
@RunWith(MockitoJUnitRunner.class)
public class TurnosServiceTest {

    @Mock
    private TurnosDAO turnosDAO;

    @Mock
    private UsuariosDAO usuariosDAO;

    @Mock
    private MotivosConsultaDAO motivosConsultaDAO;

    @Mock
    private MailsAPI mailsService;

    @Mock
    private Environment env;

    @InjectMocks
    private TurnosService turnosService;

    @Test
    public void registrarTurnoOkTest() throws AddressException {
        MotivoConsulta motivo = new MotivoConsulta(1, "tenia ganas");
        String emailUsuario = "mail@usuario.com";
        TurnoDTO turnoDTO = new TurnoDTO(1, "10/12/2010", "14:00", "Disponible", motivo);

        Usuario usuarioModel = new Usuario();
        usuarioModel.setId(1);
        when(usuariosDAO.getUsuarioByMail(eq(emailUsuario)))
                .thenReturn(usuarioModel);

        when(motivosConsultaDAO.guardarMotivo(eq(motivo)))
                .thenReturn(1);

        Turno buscado = new Turno();
        when(turnosDAO.getTurnoLibre(any(Turno.class)))
                .thenReturn(buscado);

        when(mailsService.getMailDeAdministradora())
                .thenReturn("mail@alexandra.com");

        Mockito.doNothing()
                .when(turnosDAO)
                .actualizarTurno(eq(buscado));

        Mockito.doNothing()
                .when(mailsService)
                .enviarMail(any());

        turnosService.registrarTurno(turnoDTO, emailUsuario);

        Assert.assertEquals(usuarioModel, buscado.getUsuario());
        Assert.assertEquals(motivo, buscado.getMotivo());
        Mockito.verify(mailsService, times(1))
                .enviarMail(any(MailNotificacionTurnoNuevo.class));
        Mockito.verify(turnosDAO, times(1))
                .actualizarTurno(eq(buscado));
        Mockito.verify(motivosConsultaDAO, times(0))
                .guardarMotivo(any());
    }

    @Test
    public void registrarTurnoNuevoMotivoOkTest() throws AddressException {
        MotivoConsulta motivo = new MotivoConsulta(null, "tenia ganas");
        String emailUsuario = "mail@usuario.com";
        TurnoDTO turnoDTO = new TurnoDTO(1, "10/12/2010", "14:00", "Disponible", motivo);

        when(motivosConsultaDAO.guardarMotivo(eq(motivo)))
                .thenReturn(1);

        Usuario usuarioModel = new Usuario();
        usuarioModel.setId(1);
        when(usuariosDAO.getUsuarioByMail(eq(emailUsuario)))
                .thenReturn(usuarioModel);

        when(motivosConsultaDAO.guardarMotivo(eq(motivo)))
                .thenReturn(1);

        Turno buscado = new Turno();
        when(turnosDAO.getTurnoLibre(any(Turno.class)))
                .thenReturn(buscado);

        when(mailsService.getMailDeAdministradora())
                .thenReturn("mail@alexandra.com");

        Mockito.doNothing()
                .when(turnosDAO)
                .actualizarTurno(eq(buscado));

        Mockito.doNothing()
                .when(mailsService)
                .enviarMail(any());

        turnosService.registrarTurno(turnoDTO, emailUsuario);

        Assert.assertEquals(usuarioModel, buscado.getUsuario());
        Assert.assertEquals(motivo, buscado.getMotivo());
        Mockito.verify(mailsService, times(1))
                .enviarMail(any(MailNotificacionTurnoNuevo.class));
        Mockito.verify(turnosDAO, times(1))
                .actualizarTurno(eq(buscado));
        Mockito.verify(motivosConsultaDAO, times(1))
                .guardarMotivo(any(MotivoConsulta.class));
    }

    @Test
    public void cancelarTurnoOkTest() throws AddressException {

        Usuario usuario = new Usuario();
        usuario.setMail("mail@usuario.com");
        Turno turno = new Turno();
        turno.setId(1);
        turno.setUsuario(usuario);
        when(turnosDAO.getTurnoPorId(eq(1)))
                .thenReturn(turno);
        when(mailsService.getMailDeAdministradora())
                .thenReturn("alexandra@mail.com");
        Mockito.doNothing()
                .when(turnosDAO)
                .actualizarTurno(eq(turno));
        Mockito.doNothing()
                .when(mailsService)
                .enviarMail(any(MailNotificacionCancelarTurno.class));

        turnosService.cancelarReservaDeTurno(1);

        Assert.assertNull(turno.getUsuario());

        verify(turnosDAO, times(1))
                .getTurnoPorId(eq(1));
        verify(mailsService, times(1))
                .getMailDeAdministradora();
        verify(turnosDAO, times(1))
                .actualizarTurno(eq(turno));
        verify(mailsService, times(1))
                .enviarMail(any(MailNotificacionCancelarTurno.class));
    }

}
