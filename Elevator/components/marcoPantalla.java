package Elevator.components;

import javax.swing.*;
import java.awt.*;

public class MarcoPantalla extends JFrame {

    private JButton btnInicio, btnParar, btnReset, btnAgregarSolicitud;
    private JTextArea logArea;

    public MarcoPantalla() {
        super("Elevator Manager");
        Toolkit miPantalla = Toolkit.getDefaultToolkit(); // Alacenar nuestro sistema natvo de pantallas
        /* Dimension tamanoPantalla = miPantalla.getScreenSize(); // averiguamos nuestra resolucion de nuestra pantalla.
        int anchoPantalla = tamanoPantalla.width; // Extraemos el ancho de pantalla
        int largoPantalla = tamanoPantalla.height; // Extraemos en largo de mi pantalla
        setSize(anchoPantalla / 2, largoPantalla / 2); // Tamaño de la pantalla
        setLocation(anchoPantalla / 4, largoPantalla / 4); // Centrar la pantalla
        setTitle("Proyecto #1 - Elevator Manager");
        Image icono = miPantalla.getImage("./img/logoUGalileo.png");
        setIconImage(icono);
        PantallaPresentacion hoja1 = new PantallaPresentacion();
        add(hoja1);*/

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,4,10,10));

        btnInicio = new JButton("Iniciar");
        btnParar = new JButton("Parar");
        btnReset = new JButton("Reset");
        btnAgregarSolicitud = new JButton("Agregar Solicitud");

        controlPanel.add(btnInicio);
        controlPanel.add(btnParar);
        controlPanel.add(btnReset);
        controlPanel.add(btnAgregarSolicitud);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        setLayout(new BorderLayout(10,10));
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnInicio.addActionListener(e -> log("Simulación iniciada..."));
        btnParar.addActionListener(e -> log("Simulación detenida."));
        btnReset.addActionListener(e-> log("Elevadores reseteados."));
        btnAgregarSolicitud.addActionListener(e -> {
            String piso = JOptionPane.showInputDialog(this, "Ingrese el piso de destino:");
            logArea.append("Solicitud agregada para el piso: " + piso + "\n");
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void log(String mensaje) {
        logArea.append(mensaje + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MarcoPantalla());
    }
}
