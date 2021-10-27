package flappybird;
import java.io.*;
/**
 * Clase principal, en esta clase se tendrá al método main para dar inicio al programa.
 * @author Proyecto POO
 */
public class Main {

    // La carpeta multimedia se debe de encontrar en el mismo directorio que el jar
    public static String ruta = new File("").getAbsolutePath();
    final public static String URL = ruta + "/multimedia/";
    /**
     * Método principal, en él se instanciarán los personajes del juego y el controlador.Además, se iniciará el juego.
     * @param args the command line arguments
     * @throws java.lang.InterruptedException Excepción en caso de no poder iniciar el juego.
     */ 
    public static void main(String[] args) throws InterruptedException {                
        Bird bird = new Bird();
        Obstaculo arbol1 = new Obstaculo(0);
        Obstaculo arbol2 = new Obstaculo(0);
        Obstaculo nube1 = new Obstaculo(1);
        Obstaculo nube2 = new Obstaculo(1);
        Obstaculo pasto = new Obstaculo(2);
        Obstaculo techo = new Obstaculo(3);
        Screen screen = new Screen();
        Sonidos sonidos = new Sonidos();
        Controller controller = new Controller(bird, arbol1, arbol2,nube1, nube2, screen,sonidos,pasto,techo);
        controller.initController();
         
    }    
}