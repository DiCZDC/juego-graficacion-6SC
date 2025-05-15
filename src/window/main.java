package window;
import  anim.*;
import javax.swing.JFrame;

public class main {
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
