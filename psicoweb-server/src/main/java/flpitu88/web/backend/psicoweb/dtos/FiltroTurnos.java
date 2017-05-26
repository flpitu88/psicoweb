/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.dtos;

/**
 *
 * @author flpitu88
 */
public class FiltroTurnos {

    String fechaDesde;
    String fechaHasta;
    Integer paciente;

    public FiltroTurnos() {
    }

    public FiltroTurnos(String fechaDesde, String fechaHasta, Integer paciente) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.paciente = paciente;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Integer getPaciente() {
        return paciente;
    }

    public void setPaciente(Integer paciente) {
        this.paciente = paciente;
    }

}
