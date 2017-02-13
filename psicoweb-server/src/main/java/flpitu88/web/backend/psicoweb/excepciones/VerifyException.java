/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.excepciones;

/**
 *
 * @author flavio
 */
public class VerifyException extends Exception {

    private static final long serialVersionUID = -4911506451239107610L;

    public VerifyException() {
    }

    public VerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyException(String message) {
        super(message);
    }

}
