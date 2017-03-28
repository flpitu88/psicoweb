/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.services;

import flpitu88.web.backend.psicoweb.model.Noticia;
import flpitu88.web.backend.psicoweb.serviceapis.NoticiasAPI;
import org.springframework.stereotype.Service;

/**
 *
 * @author flpitu88
 */
@Service
public class NoticiasService implements NoticiasAPI {

    @Override
    public Noticia getNoticiaPorNumero(Integer nroNoticia) {
        Noticia pedida = null;
        switch (nroNoticia) {
            case 1:
                pedida = new Noticia(1, "Titulo de la 1", "Contenido de la 1");
                break;
            case 2:
                pedida = new Noticia(2, "Titulo de la 2", "Contenido de la 2");
                break;
            default:
                pedida = new Noticia(10, "Error", "Error");
                break;
        }
        return pedida;
    }

}
