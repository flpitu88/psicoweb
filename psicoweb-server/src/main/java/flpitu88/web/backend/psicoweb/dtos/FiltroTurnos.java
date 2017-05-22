/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.dtos;

import java.time.LocalDate;

/**
 *
 * @author flpitu88
 */
public class FiltroTurnos {

    LocalDate fechaDesde;
    LocalDate fechaHasta;
    Integer paciente;

    public FiltroTurnos() {
    }

    public FiltroTurnos(LocalDate fechaDesde, LocalDate fechaHasta, Integer paciente) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.paciente = paciente;
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Integer getPaciente() {
        return paciente;
    }

    public void setPaciente(Integer paciente) {
        this.paciente = paciente;
    }

}
