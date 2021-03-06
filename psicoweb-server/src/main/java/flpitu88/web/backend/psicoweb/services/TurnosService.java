/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.factory.BeansFactory;
import flpitu88.web.backend.psicoweb.factory.MailsFactory;
import flpitu88.web.backend.psicoweb.model.Mail;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.repository.MotivosConsultaDAO;
import flpitu88.web.backend.psicoweb.repository.TurnosDAO;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.serviceapis.MailsAPI;
import flpitu88.web.backend.psicoweb.serviceapis.TurnosAPI;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flpitu88
 */
@Service
@PropertySource({"classpath:generadorTurnos.properties"})
public class TurnosService implements TurnosAPI {

    @Autowired
    private TurnosDAO turnosDAO;

    @Autowired
    private UsuariosDAO usuariosDAO;

    @Autowired
    private MotivosConsultaDAO motivosConsultaDAO;

    @Autowired
    private MailsAPI mailsService;

    @Autowired
    private Environment env;

    private static final Logger logger
            = Logger.getLogger(TurnosService.class.getName());

    @Override
    @Transactional(readOnly = false)
    public void registrarTurno(TurnoDTO tBean, String email) throws AddressException {
        Usuario usuario = usuariosDAO.getUsuarioByMail(email);
        Turno turno = BeansFactory.convertirDTOATurno(tBean, usuario);
        Turno buscado = turnosDAO.getTurnoLibre(turno);
        if (tBean.getMotivo().getId() == null) {
            Integer idNuevo = motivosConsultaDAO.guardarMotivo(tBean.getMotivo());
            tBean.getMotivo().setId(idNuevo);
        }
        buscado.setUsuario(usuario);
        buscado.setMotivo(tBean.getMotivo());
        turnosDAO.actualizarTurno(buscado);
        List<String> destinatarios = Arrays.asList(email, mailsService.getMailDeAdministradora());
        Mail mailNuevoTurno = MailsFactory.getMail(buscado, destinatarios, "nuevoTurno");
        try {
            mailsService.enviarMail(mailNuevoTurno);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "######### ERROR: Al enviar Notificacion {0} a la direccion {1}",
                    new Object[]{
                        mailNuevoTurno.getClass().getSimpleName(),
                        mailNuevoTurno.getDestinatarios()});
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Turno> getTurnosRegistrados() {
        return turnosDAO.getTurnosRegistrados();
    }

    @Override
    @Transactional(readOnly = false)
    public void generarTurnosDisponibles() {
        String horaInicioLunesString = env.getProperty("HORA_INICIO_LUNES");
        String horaInicioMiercolesString = env.getProperty("HORA_INICIO_MIERCOLES");
        String minutosDuracionTurnoString = env.getProperty("MINUTOS_DURACION_TURNO");
        String minutosEntreTurnosString = env.getProperty("MINUTOS_ENTRE_TURNOS");

        LocalDate ultimoDia = LocalDate.now();

        TemporalAdjuster adjLunes = TemporalAdjusters.next(DayOfWeek.MONDAY);
        TemporalAdjuster adjMiercoles = TemporalAdjusters.next(DayOfWeek.WEDNESDAY);

        LocalDate proxLunes = ultimoDia.with(adjLunes);
        LocalDate proxMiercoles = ultimoDia.with(adjMiercoles);

        do {
            crearTurnosDeLunes(
                    proxLunes,
                    Integer.parseInt(horaInicioLunesString),
                    Long.parseLong(minutosDuracionTurnoString),
                    Long.parseLong(minutosEntreTurnosString));

            crearTurnosDeMiercoles(
                    proxMiercoles,
                    Integer.parseInt(horaInicioMiercolesString),
                    Long.parseLong(minutosDuracionTurnoString),
                    Long.parseLong(minutosEntreTurnosString));

            proxLunes = proxLunes.with(adjLunes);
            proxMiercoles = proxMiercoles.with(adjMiercoles);

        } while (DAYS.between(ultimoDia, proxLunes) < 15 && DAYS.between(ultimoDia, proxMiercoles) < 15);
    }

    private void crearTurnosDeLunes(LocalDate proxLunes, Integer horaInicio, Long duracionTurno, Long minEntreTurnos) {
        LocalTime primerHora = LocalTime.of(horaInicio, 0);
        Turno turnoNuevo = new Turno(null, proxLunes, primerHora, null, null);
        imprimirTurnoAGuardar(turnoNuevo);
        turnosDAO.guardarTurno(turnoNuevo);
        generarTurnosDelDiaLunes(turnoNuevo, duracionTurno, minEntreTurnos);
    }

    private void crearTurnosDeMiercoles(LocalDate proxMiercoles, Integer horaInicio, Long duracionTurno, Long minEntreTurnos) {
        LocalTime primerHora = LocalTime.of(horaInicio, 0);
        Turno turnoNuevo = new Turno(null, proxMiercoles, primerHora, null, null);
        imprimirTurnoAGuardar(turnoNuevo);
        turnosDAO.guardarTurno(turnoNuevo);
        generarTurnosDelDiaMiercoles(turnoNuevo, duracionTurno, minEntreTurnos);
    }

    private void generarTurnosDelDiaLunes(Turno turno, Long duracionTurno, Long minEntreTurno) {
        LocalTime proxHora = turno.getHorario().plusMinutes(duracionTurno + minEntreTurno);
        while (proxHora.getHour() < 18) {
            proxHora = generarTurno(turno, proxHora, duracionTurno, minEntreTurno);
        }
    }

    private void generarTurnosDelDiaMiercoles(Turno turno, Long duracionTurno, Long minEntreTurno) {
        LocalTime proxHora = turno.getHorario().plusMinutes(duracionTurno + minEntreTurno);
        while (proxHora.getHour() < 19) {
            proxHora = generarTurno(turno, proxHora, duracionTurno, minEntreTurno);
        }
    }

    private LocalTime generarTurno(Turno turno, LocalTime proxHora, Long duracionTurno, Long minEntreTurno) {
        Turno nuevoTurno = new Turno(null, turno.getDia(), proxHora, null, null);
        imprimirTurnoAGuardar(nuevoTurno);
        turnosDAO.guardarTurno(nuevoTurno);
        return nuevoTurno.getHorario().plusMinutes(duracionTurno + minEntreTurno);
    }

    private void imprimirTurnoAGuardar(Turno t) {
        System.out.println("Guardando turno del "
                + FormatterFecha.crearStringDesdeLocalDate(t.getDia())
                + " a las "
                + FormatterHora.crearStringDesdeLocalTime(t.getHorario()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Turno> getDiasConTurnosDisponibles() {
        return turnosDAO.getDiasConTurnosDisponibles();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Turno> getTurnosDisponiblesDelDia(LocalDate dia) {
        return turnosDAO.getTurnosDisponiblesDelDia(dia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Turno> getTurnosDelUsuario(String mailUsuario) {
        Usuario u = usuariosDAO.getUsuarioByMail(mailUsuario);
        return turnosDAO.obtenerTurnosDeUsuario(u);
    }

    @Override
    @Transactional(readOnly = false)
    public void cancelarReservaDeTurno(Integer idTurno) throws AddressException {
        Turno t = turnosDAO.getTurnoPorId(idTurno);
        List<String> destinatarios = Arrays.asList(
                t.getUsuario().getMail(),
                mailsService.getMailDeAdministradora());
        t.setUsuario(null);
        turnosDAO.actualizarTurno(t);
        Mail mailCancelarTurno = MailsFactory.getMail(t, destinatarios, "cancelarTurno");
        try {
            mailsService.enviarMail(mailCancelarTurno);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "######### ERROR: Al enviar Notificacion {0} a la direccion {1}",
                    new Object[]{
                        mailCancelarTurno.getClass().getSimpleName(),
                        mailCancelarTurno.getDestinatarios()});
        }
    }

}
