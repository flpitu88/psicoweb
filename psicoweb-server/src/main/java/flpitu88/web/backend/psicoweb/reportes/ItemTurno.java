/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.reportes;

/**
 *
 * @author flpitu88
 */
public class ItemTurno {

    private String fecha;
    private String hora;
    private String nombrePaciente;
    private String motivo;
    private String pago;

    public ItemTurno() {
    }

    public ItemTurno(String fecha, String hora, String nombrePaciente,
            String motivo, String pago) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombrePaciente = nombrePaciente;
        this.motivo = motivo;
        this.pago = pago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

}
