
package window;
import java.awt.event.*;
import javax.swing.*;


public class Menu extends JPanel{
    private class customButton extends JButton{
        JButton button = new JButton();
        public customButton(int x, int y, int width, int height, String image_location,String text) {
            button.setText(text);
            ImageIcon icon = new ImageIcon(image_location);
            button.setBounds(x, y, width, height);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.CENTER);
            button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
            button.setOpaque(false);
            button.setContentAreaFilled(false); // Hacer el botón transparente
            //button.setBorderPainted(false);  button.setContentAreaFilled(false); // Hacer el botón transparente
        }
        public JButton getButton(){
            return button;
        }
        public void addActionListener(ActionListener listener) {
            button.addActionListener(listener);
        }
    }
    public Menu() {
        super();
        
        customButton btn_start = new customButton(100,100,300,100,"assets/ui_elements/buttons/newBtn.png","Jugar");
        customButton btn_exit = new customButton(100,250,300,100,"assets/ui_elements/buttons/newBtn.png","Salir");
        add(btn_start.getButton()); // Agregar el botón al panel
        add(btn_exit.getButton()); // Agregar el botón al panel
        
        setLayout(null); // Usar layout absoluto para setBounds
        
        btn_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Botón Jugar presionado");
            }
        });
        
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn_exit.getButton()) 
                    System.exit(0);
            }
        });
         
    
    }

    public static void main(String[] args) {
        // Create a new instance of the MainWindow class
        JFrame frame = new JFrame("Proyecto final");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centra el JFrame en la pantalla
        frame.add(new Menu());
        frame.setVisible(true);
        frame.setResizable(false);
        
        new Thread(() -> {
            Menu panel = (Menu) frame.getContentPane().getComponent(0);
        }).start();
    }
}
