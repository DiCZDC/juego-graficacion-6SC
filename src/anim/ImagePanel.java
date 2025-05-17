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
        


        private void move_image(){
            x += speed_p_s;
            x = x % (image.getWidth()*scale); // Wrap around the image
        }
        public BufferedImage getBufferedImage(){
            return image;
        }
    } 
    
    
    public void update_images(){
        for(Background_image image : images)
            image.move_image();
    }

    // Modificar la declaración de personajeSprites para manejar múltiples frames por estado
    private BufferedImage[][] personajeSprites; // [estado][frame]
    private int frameActual = 0;
    private static final int FRAMES_PER_STATE = 5; // Número de frames por estado de animación
    private int personajeEstado = 0; // 0: correr, 1: saltar, 2: atacar, 3: agachado
    private boolean atacando = false;
    private int personajeX, personajeY;
    private int personajeYBase;
    private boolean saltando = false;
    private boolean agachado = false;
    private boolean isPaused=false;
    

    // Cargar sprites del personaje
    private void cargarPersonajeSprites() throws IOException {
        SpriteLoader spriteLoader = new SpriteLoader("/Sprite_graficacion/Azteca8.png", 74, 82, 2, 7);
        SpriteLoader spriteLoader2 = new SpriteLoader("/Sprite_graficacion/Azteca9.png", 74, 82, 1, 7);
        personajeSprites = new BufferedImage[4][]; // Aumentamos a 4 estados
        personajeSprites[0] = spriteLoader.getAnimation(0); // Animación de correr (frames 1-6)
        personajeSprites[1] = spriteLoader.getAnimation(1); // Animación de salto
        //personajeSprites[2] = spriteLoader.getAnimation(2); // Animación de ataque
        personajeSprites[3] = spriteLoader2.getAnimation(0); // Animación de agacharse
    }

    // Modifica el constructor para cargar los sprites y agregar el KeyListener
    public ImagePanel(int scale) {
        this.scale = scale;
        try {                
            images.add(new Background_image(loadImage("/assets/background/sky.png"), scale, 0));
            images.add(new Background_image(loadImage("/assets/background/clouds_bg.png"), scale, -2));
            images.add(new Background_image(loadImage("/assets/background/glacial_mountains.png"), scale, -5));
            images.add(new Background_image(loadImage("/assets/background/clouds_mg_1.png"), scale, -6));
            cargarPersonajeSprites();
            personajeX = 200;
            personajeYBase = 400;
            personajeY = personajeYBase;
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W && !saltando) {
                    saltando = true;
                    personajeEstado = 1;
                    frameActual = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_S && !saltando) {
                    agachado = true;
                    personajeEstado = 3; // Cambiamos a 3 para agacharse
                    frameActual = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_A && !saltando && !agachado && !atacando) {
                    atacando = true;
                    personajeEstado = 2;
                    frameActual = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE ) {
                    isPaused=!isPaused;
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    agachado = false;
                    personajeEstado = 0;
                    frameActual = 0;
                }
            }
        });
        
        initializeQuestions();
        initializeObstacleTimer();
    }

    private BufferedImage loadImage(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null) {
            throw new IOException("No se pudo encontrar el recurso: " + path);
        }
        return ImageIO.read(is);
    }
    
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<Question> questions = new ArrayList<>();
    private Timer obstacleTimer;
    private Random random = new Random();
    private int vidas = 3;
    

    private void initializeQuestions() {
        questions.add(new Question(
            "¿Cuál es la capital de México?",
            new String[]{"Ciudad de México", "Guadalajara", "Monterrey", "Puebla"},
            0
        ));
        // Agrega más preguntas aquí
    }

    // Agregar nueva variable para controlar el tiempo mínimo entre obstáculos
    private static final int MIN_OBSTACLE_DISTANCE = 300; // píxeles
    private long lastObstacleTime = 0;
    private boolean lastWasHigh = false;

    private void initializeObstacleTimer() {
        obstacleTimer = new Timer(2000, e -> {
            if (!isPaused) {
                // Verificar si hay obstáculos existentes muy cercanos
                boolean canSpawn = true;
                for (Obstacle obstacle : obstacles) {
                    if (obstacle.getX() > getWidth() - MIN_OBSTACLE_DISTANCE) {
                        canSpawn = false;
                        break;
                    }
                }

                if (canSpawn) {
                    // Alternar entre obstáculos altos y bajos
                    boolean isHigh = random.nextBoolean();
                   
                    // Crear nuevo obstáculo
                    obstacles.add(new Obstacle(getWidth(), isHigh));
                }
            }
        });
        obstacleTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Background_image layer : images){
            BufferedImage image = layer.getBufferedImage();
            for (int i = 0; i < 6; i++)
                g.drawImage(image, layer.x + (image.getWidth() * scale * i), layer.y, image.getWidth() * scale, image.getHeight() * scale, this);
        }
        // Dibuja el personaje
        if (personajeSprites[personajeEstado] != null) {
            g.drawImage(personajeSprites[personajeEstado][frameActual], personajeX, personajeY, personajeSprites[personajeEstado][frameActual].getWidth() * scale, personajeSprites[personajeEstado][frameActual].getHeight() * scale, this);
        }
        
        // Dibujar obstáculos
        for (Obstacle obstacle : obstacles) {
            g.setColor(Color.RED);
            g.fillRect(obstacle.getX(), obstacle.getY(), 
                      obstacle.getWidth(), obstacle.getHeight());
        }
        
        // Dibujar vidas
        g.setColor(Color.red);
        g.drawString("Vidas: " + vidas, 20, 20);
    }

    private void checkCollisions() {
        Rectangle playerBounds = new Rectangle(
            personajeX, 
            personajeY, 
            personajeSprites[personajeEstado][frameActual].getWidth() * scale,
            personajeSprites[personajeEstado][frameActual].getHeight() * scale
        );

        for (Obstacle obstacle : new ArrayList<>(obstacles)) {
            if (playerBounds.intersects(obstacle.getBounds())) {
                boolean colision = false;
                
                // Lógica para obstáculos altos
                if (obstacle.isHigh()) {
                    // Si el personaje NO está agachado cuando hay obstáculo alto = colisión
                    if (!agachado) {
                        colision = true;
                    }
                } 
                // Lógica para obstáculos bajos
                else {
                    // Si el personaje NO está saltando cuando hay obstáculo bajo = colisión
                    if (!saltando) {
                        colision = true;
                    }
                }

                // Solo muestra la pregunta si hubo colisión
                if (colision) {
                    mostrarPregunta();
                    obstacles.remove(obstacle);
                }
            }
        }
    }

    private void mostrarPregunta() {
        Question question = questions.get(random.nextInt(questions.size()));
        String[] opciones = question.getOpciones();
        
        int respuesta = JOptionPane.showOptionDialog(
            this,
            question.getPregunta(),
            "¡Responde correctamente!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (!question.checkAnswer(respuesta)) {
            vidas--;
            if (vidas <= 0) {
                JOptionPane.showMessageDialog(this, "¡Juego terminado!");
                System.exit(0);
            }
        }
    }

    public void move_background(){
        while (true) {
            if (!isPaused) {
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
        int frameDelay = 100;
        int saltoPosicion = 0; // Para controlar la posición del salto

        while (true) {
            if (!isPaused) {
                long currentTime = System.currentTimeMillis();
                
                if (currentTime - lastTime > frameDelay) {
                    if (personajeEstado == 0) {
                        frameActual = (frameActual + 1) % 6;
                    } else {
                        frameActual = (frameActual + 1) % personajeSprites[personajeEstado].length;
                        
                        if (atacando && frameActual == 0) {
                            atacando = false;
                            personajeEstado = 0;
                        }
                    }
                    lastTime = currentTime;
                }

                if (saltando) {
                    // Nuevo sistema de salto que respeta la pausa
                    if (saltoPosicion < 30) {
                        personajeY -= 5;
                    } else if (saltoPosicion < 60) {
                        personajeY += 5;
                    } else {
                        personajeY = personajeYBase;
                        saltando = false;
                        personajeEstado = 0;
                        saltoPosicion = -1; // Se reinicia en la siguiente iteración
                    }
                    saltoPosicion++;
                    repaint();
                    try { 
                        Thread.sleep(0); 
                    } catch (InterruptedException e) { }
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
                Thread.sleep(10); 
            } catch (InterruptedException e) { }
        }
    }
    
    
}
