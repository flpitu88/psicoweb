/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.reportes;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * Compilador de reportes jasper en jrxml, util en el caso de que no se tenga el
 * plugin de jasper report.
 */
public class JRXMLCompiler {

    public JasperReport compilarJRXML() {
        try {
            String path = "src/main/java/flpitu88/web/backend/psicoweb/reportes/";
            String name = "informeTurnos";
            String compilePath = path + name + ".jrxml";
            return JasperCompileManager.compileReport(compilePath);
        } catch (JRException ex) {
            Logger.getLogger(JRXMLCompiler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
