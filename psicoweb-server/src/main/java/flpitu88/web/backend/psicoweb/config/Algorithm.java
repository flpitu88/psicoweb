/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.config;

/**
 *
 * @author flavio
 */
public enum Algorithm {

    HS256("HmacSHA256"),
    HS384("HmacSHA384"),
    HS512("HmacSHA512");

    private Algorithm(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
