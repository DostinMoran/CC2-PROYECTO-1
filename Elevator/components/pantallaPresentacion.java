package Elevator.components;
import javax.swing.*;
import java.awt.*;

public class pantallaPresentacion extends JPanel{
    public pantallaPresentacion(Graphics g) {
        super.paintComponent(g);
        g.drawString("Estamos aprendiendo", 100, 100);
    }
}
