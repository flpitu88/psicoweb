/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.model;

/**
 *
 * @author flpitu88
 */
public class Noticia {

    private Integer numero;
    private String titulo;
    private String contenido;

    public Noticia() {
    }

    public Noticia(Integer numero, String titulo, String contenido) {
        this.numero = numero;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
