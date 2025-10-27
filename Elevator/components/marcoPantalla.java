package Elevator.components;

import javax.swing.*;
import java.awt.*;

public class MarcoPantalla extends JFrame {
    public MarcoPantalla() {
        Toolkit miPantalla = Toolkit.getDefaultToolkit(); // Alacenar nuestro sistema natvo de pantallas
        Dimension tamanoPantalla = miPantalla.getScreenSize(); // averiguamos nuestra resolucion de nuestra pantalla.
        int anchoPantalla = tamanoPantalla.width; // Extraemos el ancho de pantalla
        int largoPantalla = tamanoPantalla.height; // Extraemos en largo de mi pantalla
        setSize(anchoPantalla / 2, largoPantalla / 2); // Tamaño de la pantalla
        setLocation(anchoPantalla / 4, largoPantalla / 4); // Centrar la pantalla
        setTitle("Proyecto #2 - Elevator Manager");
        Image icono = miPantalla.getImage("./img/logoUGalileo.png");
        setIconImage(icono);
        PantallaPresentacion hoja1 = new PantallaPresentacion();
        add(hoja1);
    }
}
