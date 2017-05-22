/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.services;

import flpitu88.web.backend.psicoweb.dtos.FiltroTurnos;
import flpitu88.web.backend.psicoweb.model.MotivoConsulta;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.repository.TurnosDAO;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.services.GeneradorInformeService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import net.sf.jasperreports.engine.JRException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

/**
 *
 * @author flpitu88
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneradorReportesServiceTest {

    @Mock
    private TurnosDAO turnosDAO;

    @Mock
    private UsuariosDAO usuariosDAO;

    @Mock
    private Environment env;

    @InjectMocks
    private GeneradorInformeService generadorReporteService;

    @Test
    public void generarInformeTest() throws JRException {
        FiltroTurnos filtro = new FiltroTurnos();
        filtro.getMapa().put("fechaDesde", LocalDate.now().minusDays(5));
        filtro.getMapa().put("fechaHasta", LocalDate.now().plusDays(2));
        filtro.getMapa().put("paciente", 1);

        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNombre("Luis");
        usuario1.setApellido("Lopez");
        MotivoConsulta motivo1 = new MotivoConsulta(1, "Motivo1");
        Turno turno1 = new Turno(1, LocalDate.now(), LocalTime.now(), usuario1, motivo1);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNombre("Roberto");
        usuario2.setApellido("Perez");
        MotivoConsulta motivo2 = new MotivoConsulta(2, "Motivo2");
        Turno turno2 = new Turno(2, LocalDate.now(), LocalTime.now(), usuario2, motivo2);

        when(env.getProperty(eq("pathJasper")))
                .thenReturn("src/main/java/flpitu88/web/backend/psicoweb/reportes/");
        when(turnosDAO.getTurnosConFiltro(filtro))
                .thenReturn(Arrays.asList(turno1, turno2));
        when(usuariosDAO.getUsuarioById(eq(1)))
                .thenReturn(usuario1);
        when(usuariosDAO.getUsuarioById(eq(2)))
                .thenReturn(usuario2);

        byte[] informe = generadorReporteService.generarInformeDeTurnosPDF(filtro);

        Assert.assertNotNull(informe);
    }

}
