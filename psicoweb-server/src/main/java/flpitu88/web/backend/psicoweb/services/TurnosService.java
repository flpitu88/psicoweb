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
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.DAYS;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
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
    private Environment env;

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
        Turno turnoNuevo = new Turno(null, proxLunes, primerHora, null);
        imprimirTurnoAGuardar(turnoNuevo);
        turnosDAO.guardarTurno(turnoNuevo);
        generarTurnosDelDia(turnoNuevo, duracionTurno, minEntreTurnos);
    }

    private void crearTurnosDeMiercoles(LocalDate proxMiercoles, Integer horaInicio, Long duracionTurno, Long minEntreTurnos) {
        LocalTime primerHora = LocalTime.of(horaInicio, 0);
        Turno turnoNuevo = new Turno(null, proxMiercoles, primerHora, null);
        imprimirTurnoAGuardar(turnoNuevo);
        turnosDAO.guardarTurno(turnoNuevo);
        generarTurnosDelDia(turnoNuevo, duracionTurno, minEntreTurnos);
    }

    private void generarTurnosDelDia(Turno turno, Long duracionTurno, Long minEntreTurno) {
        LocalTime proxHora = turno.getHorario().plusMinutes(duracionTurno + minEntreTurno);
        while (proxHora.getHour() < 21) {
            Turno nuevoTurno = new Turno(null, turno.getDia(), proxHora, null);
            imprimirTurnoAGuardar(nuevoTurno);
            turnosDAO.guardarTurno(nuevoTurno);
            proxHora = nuevoTurno.getHorario().plusMinutes(duracionTurno + minEntreTurno);
        }
    }

    private void imprimirTurnoAGuardar(Turno t) {
        System.out.println("Guardando turno del "
                + FormatterFecha.crearStringDesdeLocalDate(t.getDia())
                + " a las "
                + FormatterHora.crearStringDesdeLocalTime(t.getHorario()));
    }

}
