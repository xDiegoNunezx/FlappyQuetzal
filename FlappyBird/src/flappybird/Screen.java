package flappybird;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Clase que maneja lo relacionado con la pantalla de juego: los personajes, obstáculos y sus interacciones.
 * @author Proyecto POO
 */
public class Screen extends JFrame{
    private JLabel pajaro;
    private JLabel arbol1;
    private JLabel arbol2;
    private JLabel nube1;
    private JLabel nube2;
    private JLabel fondo;
    private JLabel menu;
    private JLabel gif;
    private JLabel titulo;
    private JLabel pasto;
    private JLabel techo;    
    private JLabel max;
    private JLabel puntaje;
    private JLabel puntajeFinal;
    private JLabel info;
    private JButton BInfo;
    private JButton Bplay;
    private JButton Brestart;
    private JButton BleaderBoard;
    private int puntos;
    private JLayeredPane layeredPane = new JLayeredPane();    
    
    private final int HEIGHT=724;
    private final int WIDTH=518;
    
    /**
     * Constructor de la clase, en él se definen las propiedades del marco y del panel por capas.
     */
    public Screen() {
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));        
        setSize(WIDTH, HEIGHT);
        setTitle("Flappy Quetzal");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        getContentPane().add(layeredPane);    
        menuInicio();
        puntos=0;                
    }
    
    /**
     * Método donde se mostrará el menú de inicio.
     */
    public void menuInicio(){          
        ImageIcon imgP = new ImageIcon(Main.URL+"BPlay.png");   
        Bplay = new JButton(new ImageIcon(imgP.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
        Bplay.setBorder(BorderFactory.createEmptyBorder());
        Bplay.setContentAreaFilled(false);
        Bplay.setBounds(215, 350,70, 70);
        Bplay.setEnabled(true);
        Bplay.setVisible(true);
        layeredPane.add(Bplay, new Integer(100));
        Bplay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ImageIcon bg = new ImageIcon(Main.URL+"fondomenur.jpg");
        menu = new JLabel();
        menu.setIcon(bg);
        menu.setSize(WIDTH, HEIGHT);            
        layeredPane.add(menu,new Integer(99));
        
        Icon gf = new ImageIcon(Main.URL+"quetzalMov.gif");
        gif = new JLabel(gf);
        gif.setBounds(100, 120, 239, 184);
        gif.setVisible(true);
        layeredPane.add(gif,new Integer(100));
        
        ImageIcon title = new ImageIcon(Main.URL+"logo.png");
        
        titulo = new JLabel(new ImageIcon(title.getImage().getScaledInstance(300, 100, Image.SCALE_SMOOTH)));
        titulo.setBounds(100, 40, 300,100);
        titulo.setVisible(true);
        layeredPane.add(titulo, new Integer(100));
    }   

    
    /**
     *Método para colocar los personajes en nuestro panel por capas.En este método se definirán sus tamaños y posiciones.
     * @param bird Objeto tipo bird que representará el quetzal.
     * @param tree1 Objeto tipo Obstaculo que representará el árbol 1.
     * @param tree2 Objeto tipo Obstaculo que representará el árbol 2.
     * @param cloud1 Objeto tipo Obstaculo que representará la nube 1.
     * @param cloud2 Objeto tipo Obstaculo que representará la nube 2.
     * @param grass Objeto tipo Obstaculo que actuará como el piso.
     * @param roof Objeto tipo Obstaculo que actuará como el límite superior.
     * @param score Entero que mostrará en pantalla el puntaje del jugador.
     */
    public void juego(Bird bird, Obstaculo tree1, Obstaculo tree2, Obstaculo cloud1, Obstaculo cloud2, Obstaculo grass,Obstaculo roof,int score){                   
        ImageIcon bg = new ImageIcon(Main.URL+"back10.jpg");
        fondo = new JLabel();
        fondo.setIcon(bg);
        fondo.setSize(WIDTH, HEIGHT);            
        layeredPane.add(fondo,new Integer(1));
        
        pajaro = new JLabel(new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH))) ;
        pajaro.setBounds(0, 0, 85, 85);
        layeredPane.add(pajaro, new Integer(4));
        bird.setX(50);
        bird.setY(50);
        bird.setRectangle(pajaro.getBounds());
        
        pasto = new JLabel(new ImageIcon(grass.getObstaculo().getImage().getScaledInstance(WIDTH, 50, Image.SCALE_SMOOTH)));
        pasto.setBounds(0,645, WIDTH,40);
        layeredPane.add(pasto,new Integer(2));
        grass.setRectangle(pasto.getBounds());
        
        techo = new JLabel(new ImageIcon(roof.getObstaculo().getImage().getScaledInstance(WIDTH, 50, Image.SCALE_SMOOTH)));
        techo.setBounds(0,0, WIDTH,40);
        layeredPane.add(techo,new Integer(2));
        roof.setRectangle(techo.getBounds());
        
        arbol1 = new JLabel(new ImageIcon(tree1.getObstaculo().getImage().getScaledInstance(250, 420, Image.SCALE_SMOOTH)));       
        arbol1.setBounds(WIDTH, 270, 250, 420); 
        layeredPane.add(arbol1, new Integer(3));
        tree1.setX(WIDTH);
        tree1.setY(270);
        tree1.setRectangle(arbol1.getBounds());          
               
        arbol2 = new JLabel(new ImageIcon(tree2.getObstaculo().getImage().getScaledInstance(250, 420, Image.SCALE_SMOOTH)));    
        arbol2.setBounds(0, 0, 250, 420);   
        layeredPane.add(arbol2, new Integer(3));
        tree2.setX(WIDTH*2);
        tree2.setY(560);
        tree2.setRectangle(arbol2.getBounds());
        
        
        nube1 = new JLabel(new ImageIcon(cloud1.getObstaculo().getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH)));       
        nube1.setBounds(0, 0, 120, 90);  
        layeredPane.add(nube1, new Integer(3));
        cloud1.setX(tree1.getX()+65);
        cloud1.setY(0);
        cloud1.setRectangle(nube1.getBounds());     
                              
        nube2 = new JLabel(new ImageIcon(cloud2.getObstaculo().getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH)));    
        nube2.setBounds(0, 0, 120, 90); 
        layeredPane.add(nube2, new Integer(3));
        cloud2.setX(tree2.getX()+65);
        cloud2.setY(150);
        cloud2.setRectangle(nube2.getBounds());
        
        puntos=0;
        puntaje = new JLabel(String.valueOf(puntos));
        Color color = new Color(229,255,205);
        puntaje.setBounds(WIDTH/2,65,30,30);
        puntaje.setFont(new Font("Serif", Font.BOLD, 25));
        puntaje.setForeground(color);
        layeredPane.add(puntaje, new Integer(6));
        
    }
    
    /**
     * Método para mostrar puntaje en pantalla.
     * @param score Valor a mostrar en pantalla
     */
    public void sumarPunto(int score){
        puntos=score;
        puntaje.setText(String.valueOf(puntos));
    }
    
    /**
     * Método para mostrar o no la información.
     * @param show Boolean, true o false.
     * @return !show
     */
    public boolean showInfo(boolean show){
        info.setVisible(show);
        return !show;
    }
    
    /**
     * Getter del botón de información
     * @return Botón de información.
     */
    public JButton getInfoButton(){
        return BInfo;
    }
    
    /**
     * Método para mostrar los puntos obtenidos en el juego, con opción para reiniciar la partida o abrir los puntajes almacenados.
     * @param score Puntaje obtenido en la partida.
     * @param maxPuntaje Máximo puntaje almacenado.
     */
    public void mostrarPuntaje(int score, int maxPuntaje){
        ImageIcon imgS = new ImageIcon(Main.URL+"WResult.png");
        
        puntajeFinal = new JLabel(new ImageIcon(imgS.getImage().getScaledInstance(220, 270, Image.SCALE_SMOOTH)));
        puntajeFinal.setBounds(137, 100, 220, 270);
        puntajeFinal.setLocation((this.getWidth()-puntajeFinal.getWidth())/2,100);
        layeredPane.add(puntajeFinal, new Integer(5));
        
        puntaje.setBounds(240,186,30,30);
        Color color = new Color(92,60,6);
        puntaje.setForeground(color);
        puntaje.setLocation((this.getWidth()-puntaje.getWidth())/2,186);
        
        max = new JLabel(String.valueOf(maxPuntaje));
        max.setBounds(235,285,30,30);
        max.setFont(new Font("Serif", Font.BOLD, 25));
        max.setForeground(color);
        max.setLocation((this.getWidth()-max.getWidth())/2,285);
        layeredPane.add(max, new Integer(6));
        
        
        ImageIcon imgR = new ImageIcon(Main.URL+"BRestart.png");
        ImageIcon imgL = new ImageIcon(Main.URL+"BLeaderboard.png");                
        
        Brestart = new JButton(new ImageIcon(imgR.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH)));
        Brestart.setBorder(BorderFactory.createEmptyBorder());
        Brestart.setContentAreaFilled(false);
        Brestart.setBounds(100, 400,120, 70);        
        Brestart.setVisible(true);
        Brestart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BleaderBoard = new JButton(new ImageIcon(imgL.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH)));
        BleaderBoard.setBorder(BorderFactory.createEmptyBorder());
        BleaderBoard.setContentAreaFilled(false);
        BleaderBoard.setBounds(270, 400,120, 70);        
        BleaderBoard.setVisible(true);
        BleaderBoard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        layeredPane.add(Brestart, new Integer(5));
        layeredPane.add(BleaderBoard, new Integer(5));    
        
        ImageIcon imgIn = new ImageIcon(Main.URL+"BInfo.png");     
        BInfo = new JButton(new ImageIcon(imgIn.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH)));
        BInfo.setBorder(BorderFactory.createEmptyBorder());
        BInfo.setContentAreaFilled(false);
        BInfo.setBounds(WIDTH-55, 5,35, 35);        
        BInfo.setVisible(true);
        layeredPane.add(BInfo, new Integer(6));
        
        ImageIcon imgI = new ImageIcon(Main.URL+"WInfo.png");
        info = new JLabel(new ImageIcon(imgI.getImage().getScaledInstance(190, 170, Image.SCALE_SMOOTH)));
        info.setBounds(WIDTH, WIDTH-200, 190, 170);
        info.setLocation(WIDTH-225,50);
        info.setVisible(false);
        layeredPane.add(info, new Integer(10));
    }
    /**
     * Método encargado del vuelo del quetzal, se define su imagen así como cuánto se eleva en la coordenada Y.
     * @param bird Objeto tipo Bird (que representa el quetzal).
     */
    public void volar(Bird bird){                       
        bird.setY(bird.getY()-60);
        bird.setX(bird.getX());
        pajaro.setLocation(bird.getX(), bird.getY());   
        bird.setRectangle(pajaro.getBounds());
    }
    
    /**
     * Método encargado de la caída del quetzal, se define su imagen así como cuánto disminuye en la coordenada Y.
     * @param bird Objeto tipo Bird (que representa el quetzal).
     */
    public void caer(Bird bird){                
        bird.setY(bird.getY()+10);
        bird.setX(bird.getX());
        pajaro.setLocation(bird.getX(), bird.getY());       
        bird.setRectangle(pajaro.getBounds());
    }          
    
    /**
     * Método para la animación del quetzal, utilizando hilos. Modifica la imagen que representa Bird cada cierto tiempo.
     * @param bird Objeto tipo Bird para modificar la imagen que lo representa.
     */
    public void movimientoBird(Bird bird){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!bird.isMuerto()){
                    ImageIcon imagen = new ImageIcon(Main.URL+"quetzal11.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal12.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal13.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal21.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal22.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal23.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal31.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal32.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    imagen = new ImageIcon(Main.URL+"quetzal33.png");         
                    bird.setBird(imagen);        
                    imagen = new ImageIcon(bird.getBird().getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH));        
                    pajaro.setIcon(imagen);     
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                }                                                
            }
        });
        t.start();
    }
    
    /**
     * Método encargado de mover los árboles por la pantalla decrementando su valor en X.
     * @param tree1 Objeto tipo Obstaculo que representa el árbol 1.
     * @param tree2 Objeto tipo Obstaculo que representa el árbol 2.
     */
    public void moverArboles(Obstaculo tree1, Obstaculo tree2){
        Random rand = new Random();
        int randomTree = rand.nextInt(220);
        randomTree += 270;
        
        if(tree1.getX()<=-arbol1.getWidth()){
            tree1.setX(WIDTH+arbol1.getWidth());
            tree1.setY(randomTree);
            arbol1.setLocation(tree1.getX(), tree1.getY());
            tree1.setRectangle(arbol1.getBounds());
        }
        
       if(tree2.getX()<=-arbol2.getWidth()){
            tree2.setX(WIDTH+arbol1.getWidth());
            tree2.setY(randomTree);
            arbol2.setLocation(tree2.getX(), tree2.getY());
            tree2.setRectangle(arbol2.getBounds());
        }
        
        tree1.setX(tree1.getX()-20);
        arbol1.setLocation(tree1.getX(), tree1.getY());
        tree1.setRectangle(arbol1.getBounds());
        
        tree2.setX(tree2.getX()-20);
        arbol2.setLocation(tree2.getX(), tree2.getY());
        tree2.setRectangle(arbol2.getBounds());
    }
    
    /**
     * Método encargado de mover las nubes por la pantalla decrementando su valor en X y calculando su altura en Y.
     * @param cloud1 Objeto tipo Obstaculo que representa la nube 1.
     * @param cloud2 Objeto tipo Obstaculo que representa la nube 2.
     */
    public void moverNubes(Obstaculo cloud1, Obstaculo cloud2){
        if(cloud1.getX()<=-arbol1.getWidth()){
            cloud1.setX(WIDTH+arbol1.getWidth());
            cloud1.setY(arbol1.getY()-300);
            nube1.setLocation(cloud1.getX(), cloud1.getY());
            cloud1.setRectangle(nube1.getBounds());
        }
        
        if(cloud2.getX()<=-arbol2.getWidth()){
            cloud2.setX(WIDTH+arbol2.getWidth());
            cloud2.setY(arbol2.getY()-300);
            nube2.setLocation(cloud2.getX(), cloud2.getY());
            cloud2.setRectangle(nube2.getBounds());
        }
        
        cloud1.setX(cloud1.getX()-20);
        nube1.setLocation(cloud1.getX(), cloud1.getY());
        cloud1.setRectangle(nube1.getBounds());
        
        cloud2.setX(cloud2.getX()-20);
        nube2.setLocation(cloud2.getX(), cloud2.getY());   
        cloud2.setRectangle(nube2.getBounds());
    }
            
    /**
     * Getter de JLabel que representa el Pájaro.
     * @return JLabel de pájaro.
     */
    public JLabel getPajaro() {
        return pajaro;
    }

    /**
     * Setter de JLabel que representa el Pájaro.
     * @param pajaro JLabel de pájaro para asignar.
     */
    public void setPajaro(JLabel pajaro) {
        this.pajaro = pajaro;
    }

    /**
     * Getter de JLabel que representa el árbol 1.
     * @return JLabel del árbol 1.
     */
    public JLabel getArbol1() {
        return arbol1;
    }

    /**
     * Setter de JLabel que representa el árbol 1.
     * @param arbol1 JLabel de árbol 1 para asignar.
     */
    public void setArbol1(JLabel arbol1) {
        this.arbol1 = arbol1;
    }

    /**
     * Getter de JLabel que representa el árbol 2.
     * @return JLabel del árbol 2.
     */
    public JLabel getArbol2() {
        return arbol2;
    }

    /**
     * Setter de JLabel que representa el árbol 2.
     * @param arbol2 JLabel de árbol 2 para asignar.
     */
    public void setArbol2(JLabel arbol2) {
        this.arbol2 = arbol2;
    }

    /**
     * Getter de JLabel que representa la nube 1.
     * @return JLabel de la nube 1.
     */
    public JLabel getNube1() {
        return nube1;
    }

    /**
     * Setter de JLabel que representa la nube 1.
     * @param nube1 JLabel de nube 1 para asignar.
     */
    public void setNube1(JLabel nube1) {
        this.nube1 = nube1;
    }

    /**
     * Getter de JLabel que representa la nube 2.
     * @return JLabel de la nube 2.
     */
    public JLabel getNube2() {
        return nube2;
    }

    /**
     * Setter de JLabel que representa la nube 2.
     * @param nube2 JLabel de la nube 2 para asignar.
     */
    public void setNube2(JLabel nube2) {
        this.nube2 = nube2;
    }

    /**
     * Getter de JLabel que representa el fondo.
     * @return JLabel del fondo.
     */
    public JLabel getFondo() {
        return fondo;
    }

    /**
     * Setter de JLabel que representa el fondo.
     * @param fondo JLabel del fondo para asignar.
     */
    public void setFondo(JLabel fondo) {
        this.fondo = fondo;
    }

    /**
     * Getter de JLabel que representa el pasto.
     * @return JLabel del pasto.
     */
    public JLabel getPasto() {
        return pasto;
    }

    /**
     * Setter de JLabel que representa el pasto.
     * @param pasto JLabel del pasto para asignar.
     */
    public void setPasto(JLabel pasto) {
        this.pasto = pasto;
    }

    /**
     * Getter de JLabel que representa el techo.
     * @return JLabel del techo.
     */
    public JLabel getTecho() {
        return techo;
    }

    /**
     * Setter de JLabel que representa el techo.
     * @param techo JLabel del techo para asignar.
     */
    public void setTecho(JLabel techo) {
        this.techo = techo;
    }

    /**
     * Getter de JLabel que representa el puntaje.
     * @return JLabel del puntaje.
     */
    public JLabel getPuntaje() {
        return puntaje;
    }

    /**
     * Setter de JLabel que representa el puntaje.
     * @param puntaje JLabel del puntaje para asignar.
     */
    public void setPuntaje(JLabel puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Getter de JLabel que representa el puntaje final.
     * @return JLabel del puntaje final.
     */
    public JLabel getPuntajeFinal() {
        return puntajeFinal;
    }

    /**
     * Setter de JLabel que representa el puntaje final.
     * @param puntajeFinal JLabel del puntaje final para asignar.
     */
    public void setPuntajeFinal(JLabel puntajeFinal) {
        this.puntajeFinal = puntajeFinal;
    }

    /**
     * Getter de JButton que representa el botón de jugar.
     * @return JButton del botón de jugar.
     */
    public JButton getBplay() {
        return Bplay;
    }

    /**
     * Setter de JButton que representa el botón de jugar.
     * @param Bplay JButton del botón de jugar para asignar.
     */
    public void setBplay(JButton Bplay) {
        this.Bplay = Bplay;
    }

    /**
     * Getter de JButton que representa el botón de reiniciar partida.
     * @return JButton del botón de reiniciar partida.
     */
    public JButton getBrestart() {
        return Brestart;
    }

    /**
     * Setter de JButton que representa el botón de reiniciar partida.
     * @param Brestart JButton del botón de reiniciar partida para asignar.
     */
    public void setBrestart(JButton Brestart) {
        this.Brestart = Brestart;
    }

    /**
     * Getter de JButton que representa el botón de leaderboards.
     * @return JButton que representa el botón de leaderboards.
     */
    public JButton getBleaderBoard() {
        return BleaderBoard;
    }

    /**
     * Setter de JButton que representa el botón de leaderboards.
     * @param BleaderBoard JButton que representa el botón de leaderboards para asignar.
     */
    public void setBleaderBoard(JButton BleaderBoard) {
        this.BleaderBoard = BleaderBoard;
    }

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    @Override
    public void setLayeredPane(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
    }
    
    /**
     * Getter de JLabel que representa el fondo de menú principal.
     * @return JLabel que representa el fondo de menú principal.
     */
    public JLabel getMenu() {
        return menu;
    }

    /**
     * Setter de JButton que representa el fondo de menú principal.
     * @param menu JLabel que representa el fondo de menú principal para asignar.
     */
    public void setMenu(JLabel menu) {
        this.menu = menu;
    }
    
    /**
     * Getter de JLabel que representa gif del quetzal en el menú principal.
     * @return JLabel que representa gif del quetzal en el menú principal.
     */
    public JLabel getGif() {
         return gif;
     }

    /** 
     * Setter de JLabel que representa gif del quetzal en el menú principal.
     * @param gif JLabel que representa gif del quetzal en el menú principal para asignar.
     */
    public void setGif(JLabel gif) {
         this.gif = gif;
     }

    /**
     *  Getter de JLabel que representa el logo del juego.
     * @return JLabel que representa el logo del juego.
     */
    public JLabel getTitulo() {
         return titulo;
     }

    /**
     * Setter de JLabel que representa el logo del juego.
     * @param titulo JLabel que representa el logo del juego para asignar.
     */
    public void setTitulo(JLabel titulo) {
         this.titulo = titulo;
     }

    /**
     * Getter de la variable encargada de los puntos en la partida.
     * @return Puntos en la partida.
     */
    public int getPuntos() {
         return puntos;
     }

    /**
     * Setter de la variable encargada de los puntos en la partida.
     * @param puntos Nueva asignación para los puntos de la partida.
     */
    public void setPuntos(int puntos) {
         this.puntos = puntos;
     }
    
     // coloca personajes en punto original 

    /**
     * Método para volver a mostrar y colocar adecuadamente los objetos donde pertenecen.
     * @param bird Objeto tipo bird que representará el quetzal.
     * @param tree1 Objeto tipo Obstaculo que representará el árbol 1.
     * @param tree2 Objeto tipo Obstaculo que representará el árbol 2.
     * @param cloud1 Objeto tipo Obstaculo que representará la nube 1.
     * @param cloud2 Objeto tipo Obstaculo que representará la nube 2.
     * @param grass Objeto tipo Obstaculo que actuará como el piso.
     * @param roof Objeto tipo Obstaculo que actuará como el límite superior.
     * @param score Entero que mostrará en pantalla el puntaje del jugador.
     */
    public void setOriginalPositions(Bird bird, Obstaculo tree1, Obstaculo tree2, Obstaculo cloud1, Obstaculo cloud2, Obstaculo grass, Obstaculo roof, int score){
        layeredPane.remove(max);
        layeredPane.remove(BInfo);
        pajaro.setBounds(0, 0, 85, 85);
        bird.setX(50);
        bird.setY(50);
        bird.setRectangle(pajaro.getBounds());

        pasto.setBounds(0,645, WIDTH,40);
        grass.setRectangle(pasto.getBounds());
        
        techo.setBounds(0,0, WIDTH,40);
        roof.setRectangle(techo.getBounds());
        
        arbol1.setBounds(WIDTH, 270, 250, 420);
        tree1.setX(WIDTH);
        tree1.setY(270);
        tree1.setRectangle(arbol1.getBounds());
                  
        arbol2.setBounds(0, 0, 250, 420);
        tree2.setX(WIDTH*2);
        tree2.setY(560);
        tree2.setRectangle(arbol2.getBounds());
           
        nube1.setBounds(0, 0, 120, 90);
        cloud1.setX(tree1.getX()+65);
        cloud1.setY(0);
        cloud1.setRectangle(nube1.getBounds());
        
        nube2.setBounds(0, 0, 120, 90);
        cloud2.setX(tree2.getX()+65);
        cloud2.setY(150);
        cloud2.setRectangle(nube2.getBounds());
        
        
        puntos=0;
        puntaje.setText(String.valueOf(puntos));
        Color color = new Color(229,255,205);
        puntaje.setBounds(WIDTH/2,65,30,30);
        puntaje.setFont(new Font("Serif", Font.BOLD, 25));
        puntaje.setForeground(color);
    } 
    
    /**
     * Método para mostrar los puntajes máximos almacenados.
     * @param scores Objeto tipo Scores para leer los puntajes.
     */
    public void showHighscores(Scores scores){
        tableroPuntaje tablero = new tableroPuntaje(scores);
        
        JFrame frame = new JFrame("Puntaje");
        frame.setUndecorated(true);
        //frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Screen.class.getResource("quetzal.png")));
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frame.setSize(240, 180); 
        JPanel panel = new JPanel(); 
        
        ImageIcon imgRegreso = new ImageIcon(Main.URL+"Button.png");   
        
        
        JButton back = new JButton("Regresar");
        //Por si se quiere agregar mejor una imagen
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.add(back);
        frame.add(tablero);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setLocationRelativeTo(null);
        
        frame.setVisible(true);  
        back.addActionListener(e->{
           frame.setVisible(false);
       });
    }
  
    
}