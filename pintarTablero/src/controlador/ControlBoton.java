/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import util.Ventana;

/**
 *
 * @author DiegoCV
 */
public class ControlBoton {

    //hash barcos
    private HashBarcos_Gui hashBarcos;
    //mar de cuadros
    Cuadro_Gui[][] cuadros;
    //De acuerdo a su tamaño indica el nivel del juego
    int tamañoTablero;
    //Define el instante en que deben pintarse los barcos
    private boolean control;
    //Controla la posicion
    Integer x = null;
    Integer y = null;
    //Permite controlar el barco que vamos a pintar
    private Integer tamañoBarco;
    //Permite identificar la llave del barco en el que vamos a guardar nuestro cuadro
    private String keyBarco = "";

    public ControlBoton(int tamaño, Cuadro_Gui[][] cuadros, HashBarcos_Gui hashBarcos) {
        this.control = true;
        this.tamañoTablero = tamaño;
        this.cuadros = cuadros;
        this.hashBarcos = hashBarcos;
    }

    public void pintarOpcion(Cuadro_Gui cuadro) {
        int z;
        byte var = 0;
        try {
            z = tamañoBarco - 1;
            if (cuadro.isBarco()) {
                Ventana.imp("Esta casilla pertenece a un barco", "Batalla Naval");
                return;
            }
            this.x = cuadro.getColumna_x();
            this.y = cuadro.getFila_y();

            //Fila ←
            if (x - z >= 0) {
                int x_aux = x - z;
                if (!this.buscarIsBarco(this.x, this.y, 0, x_aux)) {
                    this.cuadros[y][x_aux].setBackground(Color.red);
                    this.cuadros[y][x_aux].setCuadroCorrecto(true);
                } else {
                    var++;
                }
            }
            //Fila →
            if (x + z < this.tamañoTablero) {
                int x_aux = x + z;
                if (!this.buscarIsBarco(this.x, this.y, 1, x_aux)) {
                    this.cuadros[y][x_aux].setBackground(Color.red);
                    this.cuadros[y][x_aux].setCuadroCorrecto(true);
                } else {
                    var++;
                }
            }

            //Columna ↑
            if (y - z >= 0) {
                int y_aux = y - z;
                if (!this.buscarIsBarco(this.x, this.y, 2, y_aux)) {
                    this.cuadros[y_aux][x].setBackground(Color.red);
                    this.cuadros[y_aux][x].setCuadroCorrecto(true);
                } else {
                    var++;
                }
            }
            //Columna ↓
            if (y + z < this.tamañoTablero) {
                int y_aux = y + z;
                if (!this.buscarIsBarco(this.x, this.y, 3, y_aux)) {
                    this.cuadros[y_aux][x].setBackground(Color.red);
                    this.cuadros[y_aux][x].setCuadroCorrecto(true);
                } else {
                    var++;
                }
            }
            if (var == 4) {
                Ventana.imp("No puede pintar un barco en esta posicion", "Batalla Naval");
            }else{
                this.cuadros[y][x].setBackground(Color.red);
                this.control = false;
            }

        } catch (NullPointerException e) {
            Ventana.imp("Debe Elejir un barco para pintar", "Batalla Naval");
        }
    }

    private boolean buscarIsBarco(int x_var, int y_var, int direccion, int aux) {
        switch (direccion) {
            case 0:
                for (int i = x_var; i >= aux; i--) {
                    if (this.cuadros[y_var][i].isBarco()) {
                        return true;
                    }
                }
                break;
            case 1:
                for (int i = x_var; i <= aux; i++) {
                    if (this.cuadros[y_var][i].isBarco()) {
                        return true;
                    }

                }
                break;
            case 2:
                for (int j = y_var; j >= aux; j--) {
                    if (this.cuadros[j][x_var].isBarco()) {
                        return true;
                    }
                }
                break;
            default:
                for (int j = y_var; j <= aux; j++) {
                    if (this.cuadros[j][x_var].isBarco()) {
                        return true;
                    }
                }
                break;
        }

        return false;
    }

    public void pintarDefinitivo(Cuadro_Gui cuadro) {

        if (cuadro.isCuadroCorrecto()) {
            int x_var = cuadro.getColumna_x();
            int y_var = cuadro.getFila_y();
            switch (this.definirDireccion(x_var, y_var)) {
                case 0:
                    for (int i = this.x; i >= x_var; i--) {
                        this.cuadros[this.y][i].setKey(keyBarco);
                        this.cuadros[this.y][i].setBarco(true);
                        this.cuadros[this.y][i].setBackground(Color.BLUE);
                        this.hashBarcos.agregarCuadro(this.cuadros[this.y][i]);
                    }
                    break;
                case 1:
                    for (int i = this.x; i <= x_var; i++) {
                        this.cuadros[this.y][i].setKey(keyBarco);
                        this.cuadros[this.y][i].setBarco(true);
                        this.cuadros[this.y][i].setBackground(Color.BLUE);
                        this.hashBarcos.agregarCuadro(this.cuadros[this.y][i]);
                    }
                    break;
                case 2:
                    for (int j = this.y; j >= y_var; j--) {
                        this.cuadros[j][this.x].setKey(keyBarco);
                        this.cuadros[j][this.x].setBarco(true);
                        this.cuadros[j][this.x].setBackground(Color.BLUE);
                        this.hashBarcos.agregarCuadro(this.cuadros[j][this.x]);
                    }
                    break;
                default:
                    for (int j = this.y; j <= y_var; j++) {
                        this.cuadros[j][this.x].setKey(keyBarco);
                        this.cuadros[j][this.x].setBarco(true);
                        this.cuadros[j][this.x].setBackground(Color.BLUE);
                        this.hashBarcos.agregarCuadro(this.cuadros[j][this.x]);
                    }
                    break;
            }
            this.control = true;
            this.limpiar();
        } else {
            Ventana.imp("Por favor seleccione un barco disponible", "Batalla Naval");
        }
    }

    public void limpiar() {
        for (int i = 0; i < this.tamañoTablero; i++) {
            for (int j = 0; j < this.tamañoTablero; j++) {
                this.cuadros[i][j].setCuadroCorrecto(false);
                if (!this.cuadros[i][j].isBarco()) {
                    this.cuadros[i][j].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
        this.x = null;
        this.y = null;
        this.tamañoBarco = null;
        this.keyBarco = "";
    }

    // (0:←)(1:→)(2:↑)(3:↓)
    private int definirDireccion(int x_var, int y_var) {
        if (y_var == this.y) {
            if (x_var < this.x) {
                return 0;
            } else {
                return 1;
            }
        } else if (y_var < this.y) {
            return 2;
        } else {
            return 3;
        }
    }

    public boolean isControl() {
        return control;
    }

    public int getTamañoBarco() {
        return tamañoBarco;
    }

    public void setTamañoBarco(int tamañoBarco) {
        this.tamañoBarco = tamañoBarco;
    }

    public String getKeyBarco() {
        return keyBarco;
    }

    public void setKeyBarco(String keyBarco) {
        this.keyBarco = keyBarco;
    }

}
