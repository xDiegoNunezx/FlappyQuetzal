package flappybird;
import java.util.*;
import java.text.*;

/**
 * Clase usada para verificar los puntajes obtenidos.
 * @author Proyecto POO
 */
public class Scores  {

    /**
     * El número máximo de puntajes almacenados será de 3.
     */
    public static final int MAX_NUM_PUNTAJES = 3;
    private ArrayList<Score> listaPuntajes = new ArrayList();

    /**
     * Getter para obtener la lista de puntajes.
     * @return Lista de puntajes.
     */
    public ArrayList<Score> getListaPuntajes() {
        return listaPuntajes;
    }

    /**
     * Setter para asignar una lista de puntajes.
     * @param listaPuntajes ArrayList con los puntajes para asignar.
     */
    public void setListaPuntajes(ArrayList<Score> listaPuntajes) {
        this.listaPuntajes = listaPuntajes;
    }
    //esta parte añadirá los puntos pero cuidando que no sean mayores a 3 puntuaciones

    /**
     * Método para añadir puntajes, además se verifica si se ha sobrepasado el límite de puntajes a almacenar (3).
     * @param puntuacion Se añade la puntuación al arreglo.
     */
    public void anadirPuntaje(Score puntuacion) {
        listaPuntajes.add(puntuacion);
        Collections.sort(listaPuntajes);
        if(listaPuntajes.size() > MAX_NUM_PUNTAJES) {
            listaPuntajes.remove(listaPuntajes.size() - 1);
        }
    }

    /**
     * Getter para obtener la posición que ocupa el puntaje especificado en el arreglo de puntajes.
     * @param puntuacion Puntuación a buscar.
     * @return Índice de la puntuación a buscar en el arreglo.
     */
    public int getPosicion(Score puntuacion) {
        return listaPuntajes.indexOf(puntuacion);
    }
    //mostrar la evolución de los resultados, es decir, lo que se ha ido obteniendo de acuerdo a cada jugada
    @Override
    public String toString() {
        String resultado = "";
        String strDateFormat="dd/MMM/yy  hh:mm:ss";
        Date fechaCompleta = new Date();
        SimpleDateFormat fechaCorta=new SimpleDateFormat(strDateFormat);
        for(int i=0; i<=listaPuntajes.size()-1; i++) {
            Score puntuacion = listaPuntajes.get(i);
            resultado += (i+1)+ "  : " + puntuacion.getPuntos() + " pts\n";
        }
        return resultado;
    }
    
    /**
     *Método para obtener la puntuación máxima almacenada.
     * @return Puntuación máxima almacenada.
     */
    public int maxScore(){
        Score puntuacion = listaPuntajes.get(0);
        return puntuacion.getPuntos();
    }
       
}
