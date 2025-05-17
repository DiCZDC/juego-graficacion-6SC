package anim;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelVida extends JPanel {
    private int vidas;
    private static final int maxVidas = 3;
    private MVidas mv;

    public PanelVida() {
        this.vidas = 3;
        this.mv = new MVidas();
        setPreferredSize(new Dimension(150, 40));
        setOpaque(false);
    }

    public void setVidas(int nuevasVidas) {
        this.vidas = Math.max(0, Math.min(nuevasVidas, maxVidas));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < maxVidas; i++) {
            if (i < vidas) {
                g.drawImage(mv.getCorazonLLeno(), 10 + i * 40, 10, 30, 30, this);
            } else {
                g.drawImage(mv.getCorazonVacio(), 10 + i * 40, 10, 30, 30, this);
            }
        }
    }
}

