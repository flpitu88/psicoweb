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
public class AutenticacionDTO {

    private String token;
    private Boolean administrador;

    public AutenticacionDTO() {
    }

    public AutenticacionDTO(String token, Boolean administrador) {
        this.token = token;
        this.administrador = administrador;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

}
