package anim;

import java.awt.Rectangle;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isHigh; // true para obstáculos altos, false para bajos
    private int speed = 5;
    private int personajeYBase=400;
    
    public Obstacle(int x, boolean isHigh) {
        this.x = x;
        this.isHigh = isHigh;
        this.width = 50;
        this.height = isHigh ? 80 : 40;
        // Ajustamos las posiciones Y según el tipo de obstáculo
        this.y = isHigh ? personajeYBase - 80 : personajeYBase + 40; // Ajusta estos valores
    }

    public void move() {
        x -= speed;
    }

    public boolean isOffScreen() {
        return x < -width;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isHigh() { return isHigh; }
}