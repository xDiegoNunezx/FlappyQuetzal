package flappybird;
import java.io.*;

/**
 * Clase que maneja si el puntaje obtenido por el jugador ha superado puntajes previos.
 * @author Proyecto POO
 */
public class Score implements Comparable<Score>, Serializable {
    
    private int puntos;

    /**
     * Constructor de la clase, se inicializa con un valor dado.
     * @param puntos Puntos para ser inicializado.
     */
    public Score(int puntos) {
        this.puntos = puntos;
    }

    /**
     * Getter para obtener los puntos.
     * @return Puntos.
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Setter para asignar los puntos.
     * @param puntos Los puntos a ser asignados.
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public int compareTo(Score nuevoPuntaje) {
        if (this.puntos < nuevoPuntaje.puntos) {
            return 1;
        } else if (this.puntos > nuevoPuntaje.puntos) {
            return -1;
        } else {
            return 0;
        }
    }
        
}