/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.dtos.FiltroInforme;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.reportes.ItemTurno;
import flpitu88.web.backend.psicoweb.reportes.ModeloInforme;
import flpitu88.web.backend.psicoweb.repository.TurnosDAO;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.serviceapis.GeneradorInformeAPI;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
@PropertySource({"classpath:informes.properties"})
public class GeneradorInformeService implements GeneradorInformeAPI {

    @Autowired
    private TurnosDAO turnosDAO;

    @Autowired
    private UsuariosDAO usuariosDAO;

    @Autowired
    private Environment env;

    @Override
    @Transactional(readOnly = true)
    public byte[] generarInformeDeTurnosPDF(FiltroInforme filtro) throws JRException {
        ModeloInforme modeloInforme = generarModeloParaInforme(filtro);

        JasperReport report = JasperCompileManager
                .compileReport(env.getProperty("pathJasper") + "informeTurnos.jrxml");

        JasperPrint print = JasperFillManager.fillReport(
                report, modeloInforme.getParametros(),
                new JRBeanCollectionDataSource(modeloInforme.getItems()));
        return JasperExportManager.exportReportToPdf(print);
    }

    private ModeloInforme generarModeloParaInforme(FiltroInforme filtro) {
        List<Turno> turnosInforme = turnosDAO.getTurnosConFiltro(filtro);

        // Creo cada item del informe de turnos
        List<ItemTurno> listaItemsTurnos = new ArrayList<>();
        for (Turno t : turnosInforme) {
            ItemTurno itemTurno = new ItemTurno(
                    FormatterFecha.crearStringDesdeLocalDate(t.getDia()),
                    FormatterHora.crearStringDesdeLocalTime(t.getHorario()),
                    t.getUsuario().getNombreCompleto(),
                    t.getMotivo().getMotivo(),
                    "-");
            listaItemsTurnos.add(itemTurno);
        }

        // Armo los parámetros del informe obtenidos del filtro
        Map<String, String> parametros = new HashMap<>();
        if (filtro.getMapa().get("fechaDesde") != null) {
            parametros.put("fechaDesde",
                    FormatterFecha.crearStringDesdeLocalDate(
                            (LocalDate) filtro.getMapa().get("fechaDesde")));
        }
        if (filtro.getMapa().get("fechaHasta") != null) {
            parametros.put("fechaHasta",
                    FormatterFecha.crearStringDesdeLocalDate(
                            (LocalDate) filtro.getMapa().get("fechaHasta")));
        }
        if (filtro.getMapa().get("paciente") != null) {
            parametros.put("paciente",
                    (usuariosDAO.getUsuarioById(
                            (Integer) filtro.getMapa().get("paciente")))
                            .getNombreCompleto());
        }

        ModeloInforme modeloInforme = new ModeloInforme(parametros, listaItemsTurnos);

        return modeloInforme;
    }

}
