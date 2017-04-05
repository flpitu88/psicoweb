/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.serviceapis;

import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.model.Turno;
import java.util.List;

/**
 *
 * @author flpitu88
 */
public interface TurnosAPI {

    public void registrarTurno(TurnoDTO tBean, String usuario);

    public List<Turno> getTurnos();

    public List<Turno> getTurnosDisponibles();

    public void generarTurnosDisponibles();

}
