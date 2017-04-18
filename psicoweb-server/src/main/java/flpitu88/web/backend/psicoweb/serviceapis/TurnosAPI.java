/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.serviceapis;

import flpitu88.web.backend.psicoweb.dtos.TurnoDTO;
import flpitu88.web.backend.psicoweb.model.Turno;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author flpitu88
 */
public interface TurnosAPI {

    public void registrarTurno(TurnoDTO tBean, String usuario);

    public List<Turno> getTurnosRegistrados();

    public List<Turno> getDiasConTurnosDisponibles();

    public void generarTurnosDisponibles();

    public List<Turno> getTurnosDisponiblesDelDia(LocalDate dia);

    public List<Turno> getTurnosDelUsuario(String mailUsuario);

}
