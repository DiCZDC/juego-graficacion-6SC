package anim;

import javax.swing.*;

public class PruebaFondo {

    public JPanel parallax_bg(){
        JPanel background = new JPanel();
        // Cargar la imagen en un buffer
        ImageIcon icon = new ImageIcon("assets/background/glacial_mountains.png");
        java.awt.Image bufferedImage = icon.getImage();

        JLabel label = new JLabel(new ImageIcon(bufferedImage));
        label.setLocation(0, 0);
        label.setText("HOLA");
        background.add(label);
        

        return background;
    }
    

    public static void main(String[] args) {
        JFrame frame = new JFrame("Proyecto final");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centra el JFrame en la pantalla
        frame.setVisible(true);
        frame.setResizable(false);
        PruebaFondo fondo = new PruebaFondo();
        frame.add(fondo.parallax_bg());
    }
}
