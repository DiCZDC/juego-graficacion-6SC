package anim;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Personaje {
    private BufferedImage[][] sprites; // [estado][frame]
    private int frameActual = 0;
    private static final int FRAMES_PER_STATE = 5;
    private int estado = 0; // 0: correr, 1: saltar, 2: atacar, 3: agachado
    private boolean atacando = false;
    private int x, y;
    private int yBase;
    private boolean saltando = false;
    private boolean agachado = false;
    private int scale;
    private int vidas = 3;

    public Personaje(int scale, int startX, int startY) {
        this.scale = scale;
        this.x = startX;
        this.yBase = startY;
        this.y = startY;
        cargarSprites();
    }

    private void cargarSprites() {
        try {
            SpriteLoader spriteLoader = new SpriteLoader("/Sprite_graficacion/Azteca8.png", 74, 82, 2, 7);
            SpriteLoader spriteLoader2 = new SpriteLoader("/Sprite_graficacion/Azteca9.png", 74, 82, 1, 7);
            sprites = new BufferedImage[4][];
            sprites[0] = spriteLoader.getAnimation(0); // Animación de correr
            sprites[1] = spriteLoader.getAnimation(1); // Animación de salto
            sprites[3] = spriteLoader2.getAnimation(0); // Animación de agacharse
        } catch (IOException ex) {
            System.out.println("Error cargando sprites: " + ex);
        }
    }

    public void actualizar(int frameDelay) {
        if (estado == 0) {
            frameActual = (frameActual + 1) % 6;
        } else {
            frameActual = (frameActual + 1) % sprites[estado].length;
            
            if (atacando && frameActual == 0) {
                atacando = false;
                estado = 0;
            }
        }
    }

    public void saltar() {
        if (!saltando && !agachado) {
            saltando = true;
            estado = 1;
            frameActual = 0;
        }
    }

    public void finalizarSalto() {
        saltando = false;
        estado = 0; // Volver al estado de correr
        frameActual = 0;
        y = yBase; // Asegurarse de que el personaje vuelve a su posición base
    }

    public void agacharse() {
        if (!saltando) {
            agachado = true;
            estado = 3;
            frameActual = 0;
        }
    }

    public void dejarDeAgacharse() {
        agachado = false;
        estado = 0;
        frameActual = 0;
    }

    public void atacar() {
        if (!saltando && !agachado && !atacando) {
            atacando = true;
            estado = 2;
            frameActual = 0;
        }
    }

    public void actualizarSalto(int saltoPosicion) {
        if (saltando) {
            if (saltoPosicion < 30) {
                y -= 5; // Subir
            } else if (saltoPosicion < 60) {
                y += 5; // Bajar
            }
        }
    }

    // Getters y setters necesarios
    public BufferedImage getSprite() {
        return sprites[estado][frameActual];
    }

    

    public int getX() { return x; }
    public int getY() { return y; }
    public int getScale() { return scale; }
    public boolean isAgachado() { return agachado; }
    public boolean isSaltando() { return saltando; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public int getFrameActual() { return frameActual; } 
    public void setFrameActual(int frameActual) { this.frameActual = frameActual; } 
    public void perderVida() {
        this.vidas = Math.max(0, this.vidas - 1);
    }
    public int getVidas() {
        return vidas;
    }
}
