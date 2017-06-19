   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author DiegoCV
 */
public class HashBarcos_Gui {

    private HashMap<String, ArrayList<Cuadro_Gui>> tablaBarcos;

    public HashBarcos_Gui() {
        this.tablaBarcos = new HashMap<>(8);
        this.iniciarBarcos();
    }

    public void iniciarBarcos() {
        this.tablaBarcos.put("A2", new ArrayList<>());
        this.tablaBarcos.put("B2", new ArrayList<>());
        this.tablaBarcos.put("C2", new ArrayList<>());
        this.tablaBarcos.put("A3", new ArrayList<>());
        this.tablaBarcos.put("B3", new ArrayList<>());
        this.tablaBarcos.put("A4", new ArrayList<>());
        this.tablaBarcos.put("B4", new ArrayList<>());
        this.tablaBarcos.put("A5", new ArrayList<>());
    }

    public void agregarCuadro(Cuadro_Gui cuadro) {
        try {
            System.out.println(cuadro.getKey() + " Posicion: " + cuadro.getColumna_x() + "-" + cuadro.getFila_y());
            this.tablaBarcos.get(cuadro.getKey()).add(cuadro);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public ArrayList<Cuadro_Gui> retornarBarco(Cuadro_Gui cuadro) {
        if(this.tablaBarcos.get(cuadro.getKey()) != null){
            return this.tablaBarcos.get(cuadro.getKey());
        }else {
            return null;
        }
    }

    public ArrayList<Cuadro_Gui> retornarBarcoKey(String key) {
        if(this.tablaBarcos.get(key) != null){
            return this.tablaBarcos.get(key);
        }else {
            return null;
        }
    }

    public String toStringArrayList(String key) {
        ArrayList<Cuadro_Gui> aux = this.tablaBarcos.get(key);
        String men = "";
        for (Cuadro_Gui c : aux) {
            men += c.toString();
        }
        return men;
    }

    @Override
    public String toString() {
        String men = "";
        Iterator iterator = this.tablaBarcos.keySet().iterator();
        while (iterator.hasNext()) {
            ArrayList<Cuadro_Gui> aux = this.tablaBarcos.get((String)iterator.next());
            for (Cuadro_Gui c : aux) {
                men += c.toString();
            }
            men += "\n";
        }
       return men;
    }

}
