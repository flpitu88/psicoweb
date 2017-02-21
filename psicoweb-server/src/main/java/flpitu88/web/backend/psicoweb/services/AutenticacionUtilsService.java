/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import flpitu88.web.backend.psicoweb.config.Algorithm;
import flpitu88.web.backend.psicoweb.config.Jwt;
import flpitu88.web.backend.psicoweb.excepciones.AlgorithmException;
import flpitu88.web.backend.psicoweb.excepciones.VerifyException;
import flpitu88.web.backend.psicoweb.model.Usuario;
import flpitu88.web.backend.psicoweb.repository.UsuariosDAO;
import flpitu88.web.backend.psicoweb.serviceapis.AutenticacionUtilsAPI;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author flavio
 */
@Service
public class AutenticacionUtilsService implements AutenticacionUtilsAPI {

    private static final String key = "sel-secret";
    private static final Logger logger = Logger.getLogger(AutenticacionUtilsService.class.getName());

    @Autowired
    private UsuariosDAO usDao;

    @Override
    public void validateToken(String token) throws Exception {

        String[] segments = StringUtils.split(token, ".");
        if (segments.length != 3) {
            throw new IllegalStateException("Bad number of segments: " + segments.length);
        }

        // All segment should be base64
        String headerSeg = segments[0];
        String payloadSeg = segments[1];
        String signatureSeg = segments[2];

        Type stringObjectMap = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        Gson gson = new Gson();
        HashMap<String, Object> header = gson.fromJson(Jwt.base64Decode(headerSeg), stringObjectMap);

        HashMap<String, Object> payload = gson.fromJson(Jwt.base64Decode(payloadSeg), stringObjectMap);
        Long expirationDate = ((Double) payload.get("exp")).longValue();

        if (System.currentTimeMillis() > expirationDate) {
            throw new VerifyException("Expired token");
        }

        Algorithm algorithm = Jwt.getAlgorithm(header.get("alg").toString());

        // Verify signature. `sign` will return base64 String
        String signinInput = StringUtils.join(new String[]{headerSeg, payloadSeg}, ".");

        if (!Jwt.verify(signinInput, key, algorithm.getValue(), signatureSeg)) {
            throw new VerifyException("Bad signature");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void authenticate(String email, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
        Usuario user = usDao.getUsuarioByMail(email);

        if (user != null) {

            if (user.getPassword().equals(password)) {
                // TODO SALIO OK, se valida la clave
            } else {
                throw new NotAuthorizedException("Clave incorrecta del usuario " + email);
            }
        } else {
            throw new InternalServerErrorException("No existe el usuario " + email);
        }
    }

    @Override
    public String issueToken(String email) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
        try {
            // Encode a JWT with default algorithm
            HashMap<String, Object> payload = new HashMap<>();

            long iat = System.currentTimeMillis();
            long expiracion = TimeUnit.MILLISECONDS.convert(30, TimeUnit.DAYS);

            payload.put("email", email);
            payload.put("iat", new Long(iat));
            payload.put("exp", new Long(iat + expiracion));
//            payload.put("state", new HashMap<>());

            String token = Jwt.encode(payload, key);

            return token;

        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "No se ha podido generar el token para el usuario {0}", email);
        }
        return null;
    }

    @Override
    public Map<String, Object> decodeToken(String token) throws IllegalStateException, VerifyException,
            IllegalArgumentException, NoSuchAlgorithmException, UnsupportedEncodingException,
            InvalidKeyException, AlgorithmException {
        Map<String, Object> decoded = Jwt.decode(token, key, true);
        return decoded;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioByToken(Map<String, Object> tokenParams) {
        String email = (String) tokenParams.get("email");
        return usDao.getUsuarioByMail(email);
    }

    @Override
    public String getEmailByToken(String token) throws IllegalStateException, VerifyException,
            IllegalArgumentException, NoSuchAlgorithmException, UnsupportedEncodingException,
            InvalidKeyException, AlgorithmException {
        Map<String, Object> map = decodeToken(token);
        return (String) map.get("email");
    }

}
