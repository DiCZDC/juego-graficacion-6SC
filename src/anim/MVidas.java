package anim;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class MVidas {

    private BufferedImage[][] sprites; // [estado][frame]

    public MVidas(){
     cargarSprites();

    }
    

    private void cargarSprites() {
        try {
            SpriteLoader spriteLoader = new SpriteLoader("/assets/tools/heart.png", 36, 37, 1, 2);
            sprites = new BufferedImage[1][];
            sprites[0] = spriteLoader.getAnimation(0);
        } catch (IOException ex) {
            System.out.println("Error cargando sprites de corazones: " + ex);
        }
    }


    public BufferedImage getCorazonLLeno() {
        return sprites[0][0];
    }
    public BufferedImage getCorazonVacio() {
        return sprites[0][1];
    }
}
