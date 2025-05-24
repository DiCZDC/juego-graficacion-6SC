package anim;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteLoader {

    private BufferedImage spriteSheet;
    private BufferedImage[][] sprites;

    private final int frameWidth;
    private final int frameHeight;
    private final int rows; // estados
    private final int cols; // frames por estado

    public SpriteLoader( String path,int frameWidth, int frameHeight, int rows, int cols) throws IOException {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.rows = rows;
        this.cols = cols;

        spriteSheet = ImageIO.read(getClass().getResourceAsStream(path));
        
        System.out.println("Ancho imagen: " + spriteSheet.getWidth());
System.out.println("Alto imagen: " + spriteSheet.getHeight());
System.out.println("Ancho esperado: " + (cols * frameWidth));
System.out.println("Alto esperado: " + (rows * frameHeight));


        sprites = new BufferedImage[rows][cols];

        loadSprites();
    }

    private void loadSprites() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                sprites[y][x] = spriteSheet.getSubimage(
                        x * frameWidth,
                        y * frameHeight,
                        frameWidth,
                        frameHeight
                );
            }
        }
    }

    public BufferedImage[] getAnimation(int stateIndex) {
        return sprites[stateIndex];
    }
}