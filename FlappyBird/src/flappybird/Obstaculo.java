package flappybird;

import java.awt.*;
import javax.swing.*;

/**
 * Clase en la que se definirá, según sea el caso, los árboles, nubes y límites superior e inferior.
 * @author Proyecto POO
 */
public class Obstaculo {
    private ImageIcon obs;
    private int x;
    private int y;
    private Rectangle rectangle;
    private final int HEIGHT;
    private final int WIDTH;   

    /**
     * Dependiendo del entero recibido se iniciará la correspondiente imagen
     * @param n Entero para elegir la imagen que representará el objeto. 0 para árbol, 1 para nube, 2 para cesped, 3 para techo.
     */
    public Obstaculo(int n) {  
        
        switch(n){
            case 0:
                obs = new ImageIcon(Main.URL+"tree0.png");
                break;
            case 1:
                obs = new ImageIcon(Main.URL+"nube2.png");
                break;
            case 2:
                obs = new ImageIcon(Main.URL+"grass.png");
                break;
            case 3:
                obs = new ImageIcon(Main.URL+"roof.png");
                break;
            default:
                System.exit(-1);
        }
        
        
        WIDTH = obs.getIconWidth();
        HEIGHT = obs.getIconHeight();
    }  
    
    /**
     * Getter de la imagen obstáculo.
     * @return Imagen del obstáculo.
     */
    public ImageIcon getObstaculo() {
        return obs;
    }

    /**
     * Getter del rectángulo del obstáculo.
     * @return Rectángulo del obstáculo
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Setter del rectángulo.
     * @param rectangle Rectángulo para poner.
     */
    public void setRectangle(Rectangle rectangle){
        this.rectangle = rectangle;                              
    }

    /**
     * Getter de la posición X del obstáculo.
     * @return Posición en X del obstáculo
     */
    public int getX() {
        return x;
    }

    /**
     * Setter de la posición X del obstáculo.
     * @param x Posición en X del obstáculo para cambiar.
     */
    public void setX(int x) {
        this.x = x;
    }

    /** 
     * Getter de la posición Y del obstáculo.
     * @return Posición en Y del obstáculo
     */
    public int getY() {
        return y;
    }

    /**
     * Setter de la posición Y del obstáculo.
     * @param y Posición en Y del obstáculo para cambiar.
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Getter de la altura del obstáculo.
     * @return Altura del obstáculo.
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Getter del ancho del obstáculo.
     * @return Ancho del obstáculo.
     */
    public int getWidth() {
        return WIDTH;
    }                             
}