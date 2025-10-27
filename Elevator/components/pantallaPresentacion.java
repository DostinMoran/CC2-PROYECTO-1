package Elevator.components;
import javax.swing.*;
import java.awt.*;
public class PantallaPresentacion extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Elevadores en funcionamiento", 10, 20);
    }
}
