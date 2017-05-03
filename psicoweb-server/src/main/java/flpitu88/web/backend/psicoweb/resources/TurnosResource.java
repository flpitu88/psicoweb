/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.resources;

import flpitu88.web.backend.psicoweb.config.AdminSecured;
import flpitu88.web.backend.psicoweb.config.ProveedorUsuarioRequestFilter;
import flpitu88.web.backend.psicoweb.config.Secured;
import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.factory.BeansFactory;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.serviceapis.MailsAPI;
import flpitu88.web.backend.psicoweb.serviceapis.TurnosAPI;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author flpitu88
 */
@Path("/turnos")
@Component
public class TurnosResource {

    private final TurnosAPI turnosSrv;

    private final MailsAPI mailsSrv;
    
    private final BeansFactory factoryBeans;

    private final ProveedorUsuarioRequestFilter proveedorUsuarioSrv;

    private static final Logger logger
            = Logger.getLogger(TurnosResource.class.getName());

    @Inject
    public TurnosResource(
            TurnosAPI turnosSrv,
            MailsAPI mailsSrv,
            BeansFactory factoryBeans,
            ProveedorUsuarioRequestFilter proveedorUsuarioSrv) {
        this.turnosSrv = turnosSrv;
        this.mailsSrv = mailsSrv;
        this.proveedorUsuarioSrv = proveedorUsuarioSrv;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured
    public void registrarTurno(TurnoDTO turnoBean) throws AddressException {
        String emailUser = proveedorUsuarioSrv.getEmailUsuario();
        turnosSrv.registrarTurno(turnoBean, emailUser);
        logger.log(Level.INFO,
                "------ El usuario {0} registra un turno para el dia {1} en el horario {2} ---------",
                new Object[]{emailUser, turnoBean.getDia(), turnoBean.getHora()});
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    @AdminSecured
    public List<TurnoDTO> getTurnosRegistrados() {
        logger.log(Level.INFO,
                "------ El administrador solicita conocer los turnos registrados ---------");
        List<TurnoDTO> turnosBean = new ArrayList<>();
        List<Turno> turnos = turnosSrv.getTurnosRegistrados();
        turnos.stream().forEach((t) -> {
            TurnoDTO bean = BeansFactory.convertirTurnoADTO(t);
            turnosBean.add(bean);
        });
        return turnosBean;
    }

    @GET
    @Path("/dias/disponibles")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Set<String> getDiasConTurnosDisponibles() {
        logger.log(Level.INFO,
                "------ Se solicita conocer la lista de dias con turnos disponibles ---------");
        Set<String> diasTurnosDisponibles = new HashSet<>();
        List<Turno> turnosDisponibles = turnosSrv.getDiasConTurnosDisponibles();
        turnosDisponibles.stream().forEach(
                t -> diasTurnosDisponibles
                        .add(FormatterFecha.crearStringDesdeLocalDate(t.getDia())));
        return diasTurnosDisponibles;
    }

    @GET
    @Path("/horas/disponibles/{dia}/{mes}/{ano}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public List<String> getHorasDisponiblesDelDia(
            @PathParam("dia") String dia,
            @PathParam("mes") String mes,
            @PathParam("ano") String ano) {
        logger.log(Level.INFO,
                "------ Se solicita conocer la lista de horarios del dia {0}/{1}/{2} ---------",
                new Object[]{dia, mes, ano});
        List<String> horasDeDiasDisponibles = new ArrayList<>();
        List<Turno> turnosDelDiaDisponibles = turnosSrv
                .getTurnosDisponiblesDelDia(
                        LocalDate.of(
                                Integer.parseInt(ano),
                                Integer.parseInt(mes),
                                Integer.parseInt(dia)));
        turnosDelDiaDisponibles.stream().forEach(
                t -> horasDeDiasDisponibles.add(FormatterHora.crearStringDesdeLocalTime(t.getHorario())));
        return horasDeDiasDisponibles;
    }

    @PUT
    public void generarTurnosProximos() {
        logger.log(Level.INFO,
                "------ Se solicita generar los nuevos turnos proximos ---------");
        turnosSrv.generarTurnosDisponibles();
    }

    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public List<TurnoDTO> getTurnosDelUsuario() {
        String emailUser = proveedorUsuarioSrv.getEmailUsuario();
        logger.log(Level.INFO,
                "------ El usuario {0} solicita su listado de turnos asignados ---------",
                emailUser);
        List<Turno> turnosModelo = turnosSrv.getTurnosDelUsuario(emailUser);
        List<TurnoDTO> turnosBean = new ArrayList<>();
        turnosModelo.forEach(t -> turnosBean.add(new TurnoDTO(t)));
        return turnosBean;
    }

    @DELETE
    @Secured
    public void cancelarTurnoPorId(@QueryParam("id") Integer id) throws AddressException {
        String emailUser = proveedorUsuarioSrv.getEmailUsuario();
        logger.log(Level.INFO,
                "------ El usuario {0} solicita cancelar su turno de id {1} ---------",
                new Object[]{emailUser, id});
        turnosSrv.cancelarReservaDeTurno(id);
    }

}
