package flappybird;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Clase en la cual se escribirá en el archivo highscores.dat los puntajes máximos con un límite de 3 puntajes almacenados.
 * @author Proyecto POO
 */
public class ScoresFile {

    private final File highScoreFile;

    /**
     * Constructor de la clase, se elige la ruta para el archivo highscores.dat debido a que es un tipo genérico más fácil de manipular.
     */
    public ScoresFile() {
        highScoreFile = new File("highscores.dat");
    }
    
    /**
     *Mediante este método se cargaran los puntajes al arreglo para leerse.
     * @param scores Puntajes para ser cargados.
     */
    public void cargarPuntaje(Scores scores) {
        FileInputStream ficheroTableroPuntos = null;
        try {
            ficheroTableroPuntos = new FileInputStream(highScoreFile);
            ObjectInputStream objTab = new ObjectInputStream(ficheroTableroPuntos);
            scores.setListaPuntajes((ArrayList<Score>)objTab.readObject());
        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ficheroTableroPuntos != null) {
                   ficheroTableroPuntos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Mediante este método se escribirá el arreglo de puntajes al archivo highscores.dat.
     * @param scores Puntajes para ser escritos en el archivo.
     */
    public void guardarPuntos(Scores scores) {
        FileOutputStream ficheroGuardarPuntos = null;
        try {
            ficheroGuardarPuntos = new FileOutputStream(highScoreFile);
            ObjectOutputStream objPuntaje = new ObjectOutputStream(ficheroGuardarPuntos);
            objPuntaje.writeObject((ArrayList<Score>)scores.getListaPuntajes());
            objPuntaje.close();
        } catch (IOException ex) {
            Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ficheroGuardarPuntos != null) {
                   ficheroGuardarPuntos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ScoresFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}