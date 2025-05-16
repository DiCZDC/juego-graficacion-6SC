package anim;


import java.awt.*;
import javax.swing.*;

public class FrameInicio extends JFrame {

    private JButton botonIniciar;
    private JButton botonPuntuacion;
    private JPanel panelPrincipal;
    private JLabel titulo;

    public FrameInicio() {
        setTitle("Bienvenido al Juego");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        // Panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(33, 33, 33));
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título
        titulo = new JLabel("Bienvenido al Juego");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Consolas", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón Iniciar Juego
        botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.setFont(new Font("Consolas", Font.PLAIN, 16));
        botonIniciar.setBackground(new Color(76, 175, 80));
        botonIniciar.setForeground(Color.WHITE);
        botonIniciar.setFocusPainted(false);
        botonIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonIniciar.setMaximumSize(new Dimension(200, 40));
        botonIniciar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIniciar.addActionListener(e -> {
            // Aquí puedes abrir la siguiente ventana del juego
            System.out.println("Iniciando juego...");
           
            JFrame frame = new JFrame("Proyecto final");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            ImagePanel juegoPanel = new ImagePanel(2);
            frame.add(juegoPanel);
            frame.setVisible(true);
            frame.setResizable(false);

            // Hilo para el fondo
            new Thread(() -> {
                juegoPanel.move_background();
            }).start();

            // Hilo para el personaje
            new Thread(() -> {
                juegoPanel.moverPersonaje();
            }).start();
        
        });

        // Botón Ver Puntuación
        botonPuntuacion = new JButton("Ver Puntuación");
        botonPuntuacion.setFont(new Font("Consolas", Font.PLAIN, 16));
        botonPuntuacion.setBackground(new Color(33, 150, 243));
        botonPuntuacion.setForeground(Color.WHITE);
        botonPuntuacion.setFocusPainted(false);
        botonPuntuacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonPuntuacion.setMaximumSize(new Dimension(200, 40));
        botonPuntuacion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonPuntuacion.addActionListener(e -> {
            // Aquí puedes mostrar la puntuación
            JOptionPane.showMessageDialog(this, "Puntuación no disponible todavía.");
        });

        // Espaciado y orden
        panelPrincipal.add(Box.createRigidArea(new Dimension(0,50)));
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 100)));
        panelPrincipal.add(botonIniciar);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(botonPuntuacion);

        add(panelPrincipal);
    }

    // Método principal para ejecutar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrameInicio().setVisible(true);
        });
    }
}
