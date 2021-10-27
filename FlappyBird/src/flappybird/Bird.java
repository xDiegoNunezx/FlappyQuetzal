package flappybird;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 * Clase encargada de los parámetros del quetzal.
 * @author Proyecto POO
 */
public class Bird {    
    private Rectangle rectangle;
    private ImageIcon bird;
    private int x;
    private int y;
    private boolean muerto = false;
    private final int WIDTH=358;
    private final int HEIGHT=259;    
     
    /**
     * Constructor de la clase. Instancia la imagen que representará el quetzal y el rectángulo para las colisiones.
     */
    public Bird(){              
        bird = new ImageIcon(Main.URL+"quetzal.png");   
        this.rectangle = new Rectangle(0, 0, 0, 0);
    }
    
    /**
     * Getter de bird (ImageIcon)
     * @return Regresa la imagen del quetzal.
     */
    public ImageIcon getBird() {
        return bird;
    }

    /**
     * Setter de bird (ImageIcon)
     * @param bird La imagen para asignar a bird.
     */
    public void setBird(ImageIcon bird) {
        this.bird = bird;
    }
    
    /**
     * Getter del ancho del quetzal
     * @return Anchura de quetzal
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Getter de la altura del quetzal
     * @return Altura del quetzal
     */
    public int getHeight() {
        return HEIGHT;
    }
        
    /**
     * Método para verificar el parámetro muerto.
     * @return Estado de muerto.
     */
    public boolean isMuerto() {
        return muerto;
    }

    /**
     * Cambia el estado de muerto a verdadero.
     */
    public void Matar() {
        this.muerto = true;
    }
    
    /**
     * Cambia el estado de muerto a falso.
     */
    public void Vivir(){
        this.muerto = false;
    }

    /**
     * Método para obtener el rectángulo del quetzal.
     * @return Objeto tipo Rectangle.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * Setter del rectángulo. Se cambian sus valores de ser necesarios.
     * @param rectangle Rectángulo del cual se obtendrán los tamaños.
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle.height = rectangle.height-50;
        this.rectangle.width = rectangle.width-50;
        this.rectangle.x = rectangle.x;
        this.rectangle.y = rectangle.y;
    }

    /**
     * Getter de la posición X del quetzal.
     * @return Posición del quetzal en X.
     */
    public int getX() {        
        return x;
    }

    /**
     * Setter de la posición X del quetzal.
     * @param x Nuevo valor para la posición X del quetzal.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter de la posición Y del quetzal.
     * @return Posición del quetzal en Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Setter de la posición Y del quetzal.
     * @param y Nuevo valor para la posición Y del quetzal.
     */
    public void setY(int y) {
        this.y = y;
    }        
}
