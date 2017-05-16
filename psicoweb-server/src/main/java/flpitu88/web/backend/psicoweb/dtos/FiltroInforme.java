/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author flpitu88
 */
public class FiltroInforme {

    private Map<String, Object> mapa;

    public FiltroInforme() {
        mapa = new HashMap<>();
    }

    public FiltroInforme(Map<String, Object> mapa) {
        this.mapa = mapa;
    }

    public Map<String, Object> getMapa() {
        return mapa;
    }

    public void setMapa(Map<String, Object> mapa) {
        this.mapa = mapa;
    }

}
