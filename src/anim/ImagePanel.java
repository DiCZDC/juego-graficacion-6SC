package anim;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;



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

    public ImagePanel(int scale) {
        this.scale = scale;
       try {                
        images.add(new Background_image(ImageIO.read(new File("assets/background/sky.png")), scale, 0));
        images.add(new Background_image(ImageIO.read(new File("assets/background/clouds_bg.png")), scale, -2));
        images.add(new Background_image(ImageIO.read(new File("assets/background/glacial_mountains.png")), scale, -5));
        images.add(new Background_image(ImageIO.read(new File("assets/background/clouds_mg_1.png")), scale, -6));
       } catch (IOException ex) {
          System.out.println("Error: " + ex);
       }
    }
    
    public void setScale(int scale) {
        this.scale = scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Background_image layer : images){
            BufferedImage image = layer.getBufferedImage();
            for (int i = 0; i < 4; i++)
                g.drawImage(image, layer.x + (image.getWidth() * scale * i), layer.y, image.getWidth() * scale, image.getHeight() * scale, this);
        }
    }

    
    
    public void move_background(){
        while (true) {
                update_images(); // Puedes ajustar la velocidad aquí
                repaint();
                try {
                    Thread.sleep(1000 / 60);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
        }
    }
    
    public static void main(String[] args) {
        // Create a new instance of the MainWindow class
        JFrame frame = new JFrame("Proyecto final");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centra el JFrame en la pantalla
        frame.add(new ImagePanel(2));
        frame.setVisible(true);
        frame.setResizable(false);
        
        new Thread(() -> {
            ImagePanel panel = (ImagePanel) frame.getContentPane().getComponent(0);
            panel.move_background(); // Puedes ajustar la velocidad aquí
        }).start();

    }
}
