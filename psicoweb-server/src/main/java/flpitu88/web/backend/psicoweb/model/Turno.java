/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author flpitu88
 */
@Entity
@Table(name = "Turno")
public class Turno {

    private Integer id;
    private LocalDate dia;
    private LocalTime horario;
    private Usuario usuario;
    private MotivoConsulta motivo;

    public Turno() {
    }

    public Turno(Integer id, LocalDate dia, LocalTime horario,
            Usuario usuario, MotivoConsulta motivo) {
        this.id = id;
        this.dia = dia;
        this.horario = horario;
        this.usuario = usuario;
        this.motivo = motivo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTurno")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "dia")
    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    @Column(name = "horario")
    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    @OneToOne
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @OneToOne
    @JoinColumn(name = "idMotivo")
    public MotivoConsulta getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoConsulta motivo) {
        this.motivo = motivo;
    }

}
