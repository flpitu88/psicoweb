/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.serviceapis;

import flpitu88.web.backend.psicoweb.dtos.FiltroTurnos;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author flpitu88
 */
public interface GeneradorInformeAPI {

    public String generarInformeDeTurnosPDF(FiltroTurnos filtro) throws JRException;
}
