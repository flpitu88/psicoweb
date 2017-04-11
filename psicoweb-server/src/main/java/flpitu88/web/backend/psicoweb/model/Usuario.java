/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.model;

import flpitu88.web.backend.psicoweb.dtos.UsuarioDTO;
import flpitu88.web.backend.psicoweb.utils.FormatterFecha;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author flpitu88
 */
@Entity
@Table(name = "Usuario")
public class Usuario {

    private Integer id;
    private String dni;
    private String nombre;
    private String apellido;
    private String mail;
    private LocalDate fechaNacimiento;
    private Boolean administrador;
    private String password;

    public Usuario() {
    }

    public Usuario(Integer id, String dni, String nombre, String apellido,
            String mail, LocalDate fechaNacimiento, Boolean administrador, String password) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.fechaNacimiento = fechaNacimiento;
        this.administrador = administrador;
        this.password = password;
    }

    public Usuario(UsuarioDTO bean) {
        this.dni = bean.getDni();
        this.nombre = bean.getNombre();
        this.apellido = bean.getApellido();
        this.mail = bean.getEmail();
        this.fechaNacimiento = FormatterFecha
                .crearFechaNacimientoDesdeString(bean.getFechaNacimiento());
        this.administrador = bean.getAdministrador();
        this.password = bean.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "dni")
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "apellido")
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "fechaNacimiento")
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "administrador")
    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getNombreCompleto() {
        return this.getNombre() + " " + this.getApellido();
    }
}
