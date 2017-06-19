package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JButton;
import java.awt.event.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DiegoCV
 */
public class Cuadro_Gui extends JButton {

    //True si el boton hace parte de un barco
    private boolean barco;
    //false si el cuadro ha caido sino esta en juego true
    private boolean play;
    //True si es el boton correcto
    private boolean cuadroCorrecto;
    //llave principal para identificar a que barco pertenece   
    private String key = "";
    //Conector con el Controlador de botones
    private ControlBoton controlBoton;
    //Variables de control
    private final int fila_y;
    private final int columna_x;
     //la posicion del ultimo ataque
    private int[] posicion_ataque;    

    public Cuadro_Gui(int fila_y, int columna_x, ControlBoton controlBoton) {
        super();       
        this.barco = false;
        this.fila_y = fila_y;
        this.columna_x = columna_x;
        this.cuadroCorrecto = false;
        this.controlBoton = controlBoton;
        this.setBackground(Color.LIGHT_GRAY);
        ActionListener accion = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(getColumna_x() + "-" + getFila_y());
                Cuadro_Gui aux = (Cuadro_Gui) e.getSource();
                try {
                    aux.detectarAccion();
                } catch (Exception ex) {
                    Logger.getLogger(Cuadro_Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        };
        this.addActionListener(accion);
        
    }

    private void detectarAccion() throws Exception {      
            if (this.controlBoton.isControl()) {
            this.controlBoton.pintarOpcion(this);
            }else{
            this.controlBoton.pintarDefinitivo(this);           
            }
    }

    public boolean isBarco() {
        return barco;
    }

    public void setBarco(boolean barco) {
        this.barco = barco;
    }
    
    public int getFila_y() {
        return fila_y;
    }

    public int getColumna_x() {
        return columna_x;
    }

    public boolean isCuadroCorrecto() {
        return cuadroCorrecto;
    }

    public void setCuadroCorrecto(boolean cuadroCorrecto) {
        this.cuadroCorrecto = cuadroCorrecto;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public int[] getPosicion_ataque() {
        return posicion_ataque;
    }

    public void setPosicion_ataque(int[] posicion_ataque) {
        this.posicion_ataque = posicion_ataque;
    }

    
    
    @Override
    public String toString() {
        return "Cuadro{" + "key=" + key + ", columna_x=" + columna_x + ",fila_y =" + fila_y + '}';
    }
    
    
    
    
}
