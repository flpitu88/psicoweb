/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.serviceapis;

import flpitu88.web.backend.psicoweb.excepciones.AlgorithmException;
import flpitu88.web.backend.psicoweb.excepciones.VerifyException;
import flpitu88.web.backend.psicoweb.model.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 *
 * @author flavio
 */
public interface AutenticacionUtilsAPI {

    public void validateToken(String token) throws Exception;

    public void authenticate(String username, String password) throws Exception;

    public String issueToken(String username);

    public Map<String, Object> decodeToken(String token)
            throws IllegalStateException, VerifyException,
            IllegalArgumentException, NoSuchAlgorithmException,
            UnsupportedEncodingException, InvalidKeyException,
            AlgorithmException;

    public Usuario getUsuarioByToken(Map<String, Object> tokenParams);

    public String getEmailByToken(String token) throws IllegalStateException, VerifyException,
            IllegalArgumentException, NoSuchAlgorithmException, UnsupportedEncodingException,
            InvalidKeyException, AlgorithmException;

}
