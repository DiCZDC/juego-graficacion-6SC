package anim;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class QuestionPanel extends JDialog {
    private Question question;
    private int selectedAnswer;
    private boolean answered;

    private BufferedImage backgroundImage;

    private static final Color BORDER_COLOR = new Color(0, 0, 139);
    private static final Color BUTTON_COLOR = new Color(139, 0, 0);
    private static final Color BUTTON_HOVER_COLOR = new Color(165, 0, 0);
    private static final Color TEXT_COLOR = Color.WHITE;

    public QuestionPanel(JFrame parent, Question question) {
        super(parent, "¡Pregunta!", true);
        this.question = question;
        loadBackgroundImage();
        setupUI();
    }

    private void loadBackgroundImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/assets/tools/marco.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("Error cargando imagen de fondo: " + e.getMessage());
        }
    }

    private void setupUI() {
        setLayout(new BorderLayout());
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        setUndecorated(true);
        
        // Panel principal con imagen de fondo
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setOpaque(false);

        // Panel de pregunta en la parte superior
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setOpaque(false);
        JLabel questionLabel = new JLabel(question.getPregunta());
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 15));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        questionPanel.add(questionLabel, BorderLayout.CENTER);

        // Panel de opciones con diseño mejorado
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setOpaque(false);
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        String[] opciones = question.getOpciones();
        for (int i = 0; i < opciones.length; i++) {
            JButton optionButton = createOptionButton(opciones[i], i);
            optionsPanel.add(optionButton);
        }

        // Organizamos los componentes
        mainPanel.add(questionPanel, BorderLayout.NORTH);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Hacer que el diálogo sea arrastrable
        MouseAdapter dragAdapter = new MouseAdapter() {
            private int x, y;

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(getX() + e.getX() - x, getY() + e.getY() - y);
            }
        };

        addMouseListener(dragAdapter);
        addMouseMotionListener(dragAdapter);
    }

    public int showDialog() {
        selectedAnswer = -1;
        answered = false;

        // Centrar el diálogo en relación con su padre
        setLocationRelativeTo(getOwner());

        // Mostrar el diálogo y esperar hasta que se cierre
        setVisible(true);

        // Retornar la respuesta seleccionada (-1 si no se seleccionó ninguna)
        return selectedAnswer;
    }

    private JButton createOptionButton(String text, final int index) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setForeground(Color.WHITE);
        button.setBackground(BUTTON_COLOR);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.blue, 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
            }
        });

        button.addActionListener(e -> {
            selectedAnswer = index;
            answered = true;
            dispose();
        });

        return button;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public boolean isAnswered() {
        return answered;
    }
}
