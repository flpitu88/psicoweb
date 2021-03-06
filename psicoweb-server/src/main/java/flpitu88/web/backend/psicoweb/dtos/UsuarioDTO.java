/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.dtos;

import java.io.Serializable;

/**
 *
 * @author flpitu88
 */
public class UsuarioDTO implements Serializable {

    private String dni;
    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private String fechaNacimiento;
    private Boolean administrador;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String dni, String nombre, String apellido, String password,
            String email, String fechaNacimiento, Boolean administrador) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.administrador = administrador;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
