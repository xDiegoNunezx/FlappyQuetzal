 package flappybird;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Controller, encargada de controlar el estado del juego, a través de
 * ella se iniciará el juego y se verificarán las colisiones en todo momento.
 *
 * @author Proyecto POO
 */
public class Controller {

    private Bird bird;
    private Obstaculo tree1;
    private Obstaculo tree2;
    private Obstaculo nube1;
    private Obstaculo nube2;
    private Obstaculo pasto;
    private Obstaculo techo;
    private Screen screen;
    private int puntaje = 0;
    private Sonidos sonido;
    boolean contado = true;
    boolean running = true;
    boolean show = true;
    private int maxPuntaje = 0;    
    
    /**
     * Constructor de la clase.
     *
     * @param bird Objeto tipo bird (Quetzal).
     * @param tree1 Objeto tipo Obstaculo que representará el árbol 1.
     * @param tree2 Objeto tipo Obstaculo que representará el árbol 2.
     * @param nube1 Objeto tipo Obstaculo que representará la nube 1.
     * @param nube2 Objeto tipo Obstaculo que representará la nube 2.
     * @param screen Objeto tipo Screen para mostrarla en pantalla.
     * @param sonidos Objeto tipo Sonidos para la ambientación musical.
     * @param pasto Objeto tipo Obstaculo que representará el límite inferior.
     * @param techo Objeto tipo Obstaculo que representará el límite superior.
     */
    public Controller(Bird bird, Obstaculo tree1, Obstaculo tree2, Obstaculo nube1, Obstaculo nube2, Screen screen, Sonidos sonidos, Obstaculo pasto, Obstaculo techo) {
        this.bird = bird;
        this.tree1 = tree1;
        this.tree2 = tree2;
        this.nube1 = nube1;
        this.nube2 = nube2;
        this.screen = screen;
        this.sonido = sonidos;
        this.pasto = pasto;
        this.techo = techo;
        screen.setVisible(true);
        screen.juego(bird, tree1, tree2, nube1, nube2, pasto, techo, puntaje);
    }

    /**
     * Método para verificar la entrada por ratón.
     */
    public void initController() {
        MouseListener ml = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.volar(bird);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        screen.addMouseListener(ml);

        screen.getBplay().addActionListener(e -> {
            screen.getBplay().setVisible(false);
            screen.getMenu().setVisible(false);
            screen.getTitulo().setVisible(false);
            screen.getGif().setVisible(false);
            start();           
            musica();            
        });
        
        
        
    }

    /**
     * Mediante este método se inicia el juego: se colocan los componentes y
     * personajes, se valida, mientras el quetzal esté vivo, si ocurrió una
     * colisión o si se aumenta el puntaje.
     */
    public void start() {   
        screen.movimientoBird(bird);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {               
                while (!bird.isMuerto()) {                    
                    screen.caer(bird);
                    screen.moverArboles(tree1, tree2);
                    screen.moverNubes(nube1, nube2);
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (colision()) {
                        //sonido.stop();
                        sonido.stop();
                        bird.Matar();
                        sonido.reproducirSonido(Main.URL+"muerte.wav");                        
                        gameOver();
                        
                    } else {
                        // si todavia no se cuenta el arbol 1, suma un punto por haberlo pasado
                        if (tree1.getX() + 40 <= bird.getX() & contado) {
                            puntaje++;
                            screen.sumarPunto(puntaje);
                            contado = false;
                            sonido.reproducirSonido(Main.URL+"puntito.wav");
                        }
                        // si todavia no se cuenta el arbol 2, suma un punto por haberlo pasado
                        if (tree2.getX() + 40 <= bird.getX() & !contado) {
                            puntaje++;
                            screen.sumarPunto(puntaje);
                            contado = true;
                            sonido.reproducirSonido(Main.URL+"puntito.wav");
                        }
                    }
                }
            }
        });                                                
        t1.start();                
    }
    
    /**
     * Método para reproducir, mediante el uso de un hilo, la música ambiental del juego.
     */
    public void musica(){
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                sonido.reproducirLoop(Main.URL+"fondo.wav");
                sonido.reproducirLoop(Main.URL+"volando.wav");                                                 
            }
        });
        t2.start();
    }

    /**
     * Método después de morir, con el cual podemos reiniciar el juego y mostrar los puntajes.
     */
    public void gameOver() {
        screen.getPajaro().setVisible(false);
        Scores scores = puntaje(puntaje);
        screen.mostrarPuntaje(puntaje, maxPuntaje);
        screen.getInfoButton().addActionListener(e-> show = screen.showInfo(show));
        screen.getBrestart().addActionListener(e -> {
            screen.getPajaro().setVisible(true);
            screen.getPuntajeFinal().setVisible(false);
            screen.getBrestart().setVisible(false);
            screen.getBleaderBoard().setVisible(false);
            puntaje=0;
            screen.setOriginalPositions(bird, tree1, tree2, nube1, nube2, pasto,techo,puntaje);
            bird.Vivir();
            start();
        });
        
       screen.getBleaderBoard().addActionListener(e->{
           screen.showHighscores(scores); 
       });
    }        

    /**
     * Método para revisar las colisiones
     *
     * @return Si ocurrió una colisión regresará true; caso contrario, false.
     */
    public boolean colision() {
        // si choca con alguno regresa verdadero
        if (collisionA() || collisionB() || collisionC()) {
            return true;
        }
        return false;
    }

    /**
     * Método para revisar las colisiones entre el quetzal y los árboles
     *
     * @return True si ocurrió una colisión, false si no ocurrió ninguna.
     * colisión.
     */
    public boolean collisionA() {
        if (bird.getRectangle().intersects(tree1.getRectangle()) || bird.getRectangle().intersects(tree2.getRectangle())) {
            return true;
        }
        return false;
    }

    /**
     * Método para revisar las colisiones entre el quetzal y las nubes
     *
     * @return True si ocurrió una colisión, false si no ocurrió ninguna.
     * colisión.
     */
    public boolean collisionB() {
        if (bird.getRectangle().intersects(nube1.getRectangle()) || bird.getRectangle().intersects(nube2.getRectangle())) {
            return true;
        }
        return false;
    }
    /**
     * Método para revisar las colisiones entre el quetzal y los límites superior e inferior.
     *
     * @return True si ocurrió una colisión, false si no ocurrió ninguna.
     * colisión.
     */
    public boolean collisionC() {
        if (bird.getRectangle().intersects(pasto.getRectangle()) || bird.getRectangle().intersects(techo.getRectangle())) {
            return true;
        }
        return false;
    }

    /**
     * Mediante este método se verificará y escribirá el puntaje.
     *
     * @param nuevoPuntaje Puntaje obtenido durante la partida.
     * @return Objeto tipo Score.
     */
    
    public Scores puntaje(int nuevoPuntaje) {
        Scores scores = new Scores();
        ScoresFile scoresFile = new ScoresFile();
        scoresFile.cargarPuntaje(scores);
        Score score = new Score(nuevoPuntaje);
        scores.anadirPuntaje(score);
        System.out.println(scores.toString());
        scoresFile.guardarPuntos(scores);
        maxPuntaje = scores.maxScore();
        return scores;
    }
    

    /**
     * Getter del quetzal.
     *
     * @return Objeto tipo Bird (Quetzal).
     */
    public Bird getBird() {
        return bird;
    }

    /**
     * Setter del quetzal.
     *
     * @param bird Objeto tipo Bird (Quetzal) para asignarse.
     */
    public void setBird(Bird bird) {
        this.bird = bird;
    }

    /**
     * Getter del árbol 1.
     *
     * @return Objeto tipo Obstaculo (tree1).
     */
    public Obstaculo getTree1() {
        return tree1;
    }

    /**
     * Setter del árbol 1.
     *
     * @param tree1 Objeto tipo Obstaculo.
     */
    public void setTree1(Obstaculo tree1) {
        this.tree1 = tree1;
    }

    /**
     * Getter del árbol 2.
     *
     * @return Objeto tipo Obstaculo (tree2).
     */
    
    public Obstaculo getTree2() {
        return tree2;
    }

    /**
     * Setter del árbol 2.
     *
     * @param tree2 Objeto tipo Obstaculo.
     */
    public void setTree2(Obstaculo tree2) {
        this.tree2 = tree2;
    }

    /**
     * Getter de la pantalla (objeto tipo Screen).
     *
     * @return Pantalla (screen).
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Setter de la pantalla.
     *
     * @param screen Pantalla a asignarse a screen.
     */
    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
