/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.dtos.FiltroTurnos;
import flpitu88.web.backend.psicoweb.model.Turno;
import flpitu88.web.backend.psicoweb.reportes.ItemTurno;
import flpitu88.web.backend.psicoweb.reportes.ModeloInforme;
import flpitu88.web.backend.psicoweb.repository.TurnosDAO;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.serviceapis.GeneradorInformeAPI;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import flpitu88.web.backend.psicoweb.utils.FormatterHora;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String generarInformeDeTurnosPDF(FiltroTurnos filtro) throws JRException {
        ModeloInforme modeloInforme = generarModeloParaInforme(filtro);

        JasperReport report = JasperCompileManager
                .compileReport(env.getProperty("pathJasper") + "informeTurnos.jrxml");

        JasperPrint print = JasperFillManager.fillReport(
                report, modeloInforme.getParametros(),
                new JRBeanCollectionDataSource(modeloInforme.getItems()));
        String pathFileTemp = env.getProperty("pathTemp") + "informeTurnos.pdf";
//        JasperExportManager.exportReportToPdfFile(print, pathFileTemp);
        try {
            File archivoPdf = new File(pathFileTemp);
            byte[] content = cargarArchivo(archivoPdf);
            return Base64.getEncoder().encodeToString(content);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorInformeService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorInformeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static byte[] cargarArchivo(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    private ModeloInforme generarModeloParaInforme(FiltroTurnos filtro) {
        List<Turno> turnosInforme = turnosDAO.getTurnosConFiltro(filtro);

        // Creo cada item del informe de turnos
        List<ItemTurno> listaItemsTurnos = new ArrayList<>();
        for (Turno t : turnosInforme) {
            ItemTurno itemTurno = new ItemTurno(
                    FormatterFecha.crearStringDesdeLocalDate(t.getDia()),
                    FormatterHora.crearStringDesdeLocalTime(t.getHorario()),
                    (t.getUsuario() != null)
                    ? t.getUsuario().getNombreCompleto() : "",
                    t.getMotivo().getMotivo(),
                    "-");
            listaItemsTurnos.add(itemTurno);
        }

        // Armo los parámetros del informe obtenidos del filtro
        Map<String, String> parametros = new HashMap<>();
        if (filtro.getFechaDesde() != null) {
            parametros.put("fechaDesde",
                    filtro.getFechaDesde());
        }
        if (filtro.getFechaHasta() != null) {
            parametros.put("fechaHasta",
                    filtro.getFechaHasta());
        }
        if (filtro.getPaciente() != null) {
            parametros.put("paciente",
                    (usuariosDAO.getUsuarioById(
                            filtro.getPaciente())).getNombreCompleto());
        }

        ModeloInforme modeloInforme = new ModeloInforme(parametros, listaItemsTurnos);

        return modeloInforme;
    }

}
