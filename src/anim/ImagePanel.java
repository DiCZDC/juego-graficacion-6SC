package anim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;



public class ImagePanel extends JPanel{
    private Vector <Background_image> images = new Vector<Background_image>();
    private int scale = 2;
    

    private class Background_image{
        private int x=0;
        private int y=0;
        private int speed_p_s;
        private BufferedImage image;
        
        private int scale;
        
        public Background_image(BufferedImage image, int scale,int speed_p_s){
            this.image = image;
            this.scale = scale;
            this.speed_p_s = speed_p_s;
        }
        


        private void move_image() {
            x += speed_p_s * gameSpeed;
            x = x % (image.getWidth()*scale);
        }
        public BufferedImage getBufferedImage(){
            return image;
        }
    } 
    
    
    public void update_images(){
        for(Background_image image : images)
            image.move_image();
    }

    private Personaje personaje;
    private boolean isPaused = false;
    private PanelVida panelVida;

    // Modifica el constructor para cargar los sprites y agregar el KeyListener
    public ImagePanel(int scale) {
        this.scale = scale;
        gameStartTime = System.currentTimeMillis();
        setLayout(null); // Para poder posicionar el panel de vidas
        
        // Crear y agregar el panel de vidas
        panelVida = new PanelVida();
        panelVida.setBounds(10, 10, 150, 40);
        add(panelVida);
        
        try {                
            images.add(new Background_image(loadImage("/assets/background/sky.png"), scale, 0));
            images.add(new Background_image(loadImage("/assets/background/clouds_bg.png"), scale, -2));
            images.add(new Background_image(loadImage("/assets/background/glacial_mountains.png"), scale, -5));
            images.add(new Background_image(loadImage("/assets/background/clouds_mg_1.png"), scale, -6));

            personaje = new Personaje(scale, 200, 400);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    personaje.saltar();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    personaje.agacharse();
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    personaje.atacar();
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isPaused = !isPaused;
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    personaje.dejarDeAgacharse();
                }
            }
        });
        
       
        initializeObstacleTimer();
    }

    private BufferedImage loadImage(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null) {
            throw new IOException("No se pudo encontrar el recurso: " + path);
        }
        return ImageIO.read(is);
    }
    private MQuestion questionM= new MQuestion();
    
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    
    
    private Timer obstacleTimer;
    private Random random = new Random();
    
    

    

    // Agregar nueva variable para controlar el tiempo mínimo entre obstáculos
    private static final int MIN_OBSTACLE_DISTANCE = 400; // Aumentado para más espacio
    private static final int BASE_SPAWN_DELAY = 3000; // 3 segundos base entre obstáculos
    private static final int MIN_SPAWN_DELAY = 1500; // Mínimo delay permitido
    private long lastObstacleTime = 0;
    private boolean lastWasHigh = false;

    private void initializeObstacleTimer() {
        obstacleTimer = new Timer(BASE_SPAWN_DELAY, e -> {
            if (!isPaused) {
                long currentTime = System.currentTimeMillis();
                // Verificar si ha pasado suficiente tiempo desde el último obstáculo
                if (currentTime - lastObstacleTime < BASE_SPAWN_DELAY / gameSpeed) {
                    return;
                }

                // Verificar si hay obstáculos muy cercanos
                boolean canSpawn = true;
                for (Obstacle obstacle : obstacles) {
                    if (obstacle.getX() > getWidth() - MIN_OBSTACLE_DISTANCE) {
                        canSpawn = false;
                        break;
                    }
                }

                // Limitar el número máximo de obstáculos en pantalla
                if (obstacles.size() >= 3) {
                    canSpawn = false;
                }

                if (canSpawn) {
                    boolean isHigh = random.nextBoolean();
                    // Evitar dos obstáculos altos seguidos
                    if (isHigh && lastWasHigh) {
                        isHigh = false;
                    }
                    obstacles.add(new Obstacle(getWidth(), isHigh));
                    lastWasHigh = isHigh;
                    lastObstacleTime = currentTime;
                }
            }
        });
        
        // Ajustar el delay inicial
        obstacleTimer.setDelay((int)(BASE_SPAWN_DELAY / gameSpeed));
        obstacleTimer.start();
    }

    // Método para actualizar el timer según la velocidad del juego
    private void updateObstacleTimer() {
        if (obstacleTimer != null) {
            int newDelay = Math.max(MIN_SPAWN_DELAY, 
                                  (int)(BASE_SPAWN_DELAY / gameSpeed));
            obstacleTimer.setDelay(newDelay);
        }
    }

   
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Background_image layer : images){
            BufferedImage image = layer.getBufferedImage();
            for (int i = 0; i < 6; i++)
                g.drawImage(image, layer.x + (image.getWidth() * scale * i), layer.y, 
                           image.getWidth() * scale, image.getHeight() * scale, this);
        }
        // Dibuja el personaje
        BufferedImage spriteActual = personaje.getSprite();
        g.drawImage(spriteActual, personaje.getX(), personaje.getY(), 
                    spriteActual.getWidth() * personaje.getScale(), 
                    spriteActual.getHeight() * personaje.getScale(), this);
        
        // Dibujar obstáculos
        for (Obstacle obstacle : obstacles) {
            g.setColor(Color.RED);
            g.fillRect(obstacle.getX(), obstacle.getY(), 
                      obstacle.getWidth(), obstacle.getHeight());
        }
      
    }

    // Actualizar el método checkCollisions
    private void checkCollisions() {
        BufferedImage spriteActual = personaje.getSprite();
        Rectangle playerBounds = new Rectangle(
            personaje.getX(), 
            personaje.getY(), 
            spriteActual.getWidth() * personaje.getScale(),
            spriteActual.getHeight() * personaje.getScale()
        );

        for (Obstacle obstacle : new ArrayList<>(obstacles)) {
            if (!obstacle.hasCollided() && playerBounds.intersects(obstacle.getBounds())) {
                boolean colision = false;
                
                if (obstacle.isHigh()) {
                    if (!personaje.isAgachado()) {
                        colision = true;
                    }
                } else {
                    if (!personaje.isSaltando()) {
                        colision = true;
                    }
                }

                if (colision) {
                    obstacle.setCollided();
                    mostrarPregunta();
                    obstacles.remove(obstacle);
                }
            }
        }
    }

    // Actualizar el método mostrarPregunta para usar el panel de vidas
    private void mostrarPregunta() {
        Question question = questionM.getRandomQuestion();
        QuestionPanel questionPanel = new QuestionPanel(
            (JFrame) SwingUtilities.getWindowAncestor(this), 
            question
        );
        
        int respuesta = questionPanel.showDialog();
        
        if (respuesta == -1 || !question.checkAnswer(respuesta)) {
            personaje.perderVida();
            panelVida.setVidas(personaje.getVidas());
            if (personaje.getVidas() <= 0) {
                JOptionPane.showMessageDialog(
                    this, 
                    "¡Juego terminado!", 
                    "Fin del juego", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                System.exit(0);
            }
        }
    }

    public void move_background() {
        while (true) {
            if (!isPaused) {
                updateGameSpeed(); // Actualizar la velocidad del juego
                update_images();
                repaint();
            }
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    // Hilo para animar el personaje
    public void moverPersonaje() {
        long lastTime = System.currentTimeMillis();
        int frameDelay = 80;
        int saltoPosicion = 0;
        boolean completandoSalto = false;

        while (true) {
            if (!isPaused) {
                long currentTime = System.currentTimeMillis();
                
                // Actualizar animación del personaje
                if (currentTime - lastTime > frameDelay) {
                    personaje.actualizar(frameDelay);
                    lastTime = currentTime;
                }

                // Actualizar salto si está saltando
                if (personaje.isSaltando() || completandoSalto) {
                    personaje.actualizarSalto(saltoPosicion);
                    saltoPosicion++;
                    
                    // Si el salto completa su ciclo
                    if (saltoPosicion >= 60) {
                        saltoPosicion = 0;
                        completandoSalto = false;
                        personaje.finalizarSalto(); // Necesitamos agregar este método en la clase Personaje
                    } else {
                        completandoSalto = true;
                    }
                }

                // Mover obstáculos
                for (Obstacle obstacle : new ArrayList<>(obstacles)) {
                    obstacle.move();
                    if (obstacle.isOffScreen()) {
                        obstacles.remove(obstacle);
                    }
                }
                
                checkCollisions();
                repaint();
            }
            try { 
                Thread.sleep(16); // Aproximadamente 60 FPS
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    private static final float SPEED_INCREMENT = 0.1f;
    private static final float MAX_SPEED = 5.0f;
    private float gameSpeed = 1.0f;
    private long gameStartTime;
    private static final int SPEED_INCREASE_INTERVAL = 5000; // 10 segundos
    
    private void updateGameSpeed() {
        if (isPaused) return;
        
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - gameStartTime) / 1000; // Convertir a segundos
        
        float targetSpeed = 1.0f + (elapsedTime / 30) * SPEED_INCREMENT;
        gameSpeed = Math.min(targetSpeed, MAX_SPEED);
        
        updateObstacleTimer();
    }
}
