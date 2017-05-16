/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flpitu88.web.backend.psicoweb.reportes;

import java.util.List;
import java.util.Map;

/**
 *
 * @author flpitu88
 */
public class ModeloInforme {

    private Map<String,String> parametros;
    private List<ItemTurno> items;

    public ModeloInforme() {
    }

    public ModeloInforme(Map parametros, List<ItemTurno> items) {
        this.parametros = parametros;
        this.items = items;
    }

    public Map getParametros() {
        return parametros;
    }

    public void setParametros(Map parametros) {
        this.parametros = parametros;
    }

    public List<ItemTurno> getItems() {
        return items;
    }

    public void setItems(List<ItemTurno> items) {
        this.items = items;
    }

}
