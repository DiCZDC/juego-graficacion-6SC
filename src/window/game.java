package window;
import anim.*;
import java.awt.event.*;
import javax.swing.*;



public class game extends JPanel{
    
    public game() {
        super();
        ImagePanel panel = new ImagePanel(2);
        add(panel);
        panel.move_background(); // Puedes ajustar la velocidad aquí
       
    }
    
    public static void main(String[] args) {
        // Create a new instance of the MainWindow class
        JFrame frame = new JFrame("Proyecto final");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centra el JFrame en la pantalla
        game menu = new game();
        //frame.add(menu);
        frame.setVisible(true);
        frame.setResizable(false);

        
    }    
}
