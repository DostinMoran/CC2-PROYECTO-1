package Elevator.components;

import javax.swing.*;
import java.awt.*;

import Elevator.*;

public class MarcoPantalla extends JFrame {

    private JButton btnInicio, btnParar, btnReset, btnAgregarSolicitud;
    private JTextArea logArea;

    private ManejoElevadores manejador;
    private ElevatorStandard[] elevadores;

    public MarcoPantalla() {
        super("Proyecto #1 - Elevator Manager");
        Toolkit miPantalla = Toolkit.getDefaultToolkit(); // Alacenar nuestro sistema natvo de pantallas
        Dimension tamanoPantalla = miPantalla.getScreenSize(); // averiguamos nuestra resolucion de nuestra pantalla.
        int anchoPantalla = tamanoPantalla.width; // Extraemos el ancho de pantalla
        int largoPantalla = tamanoPantalla.height; // Extraemos en largo de mi pantalla
        Image icono = miPantalla.getImage("./img/logoUGalileo.png");
        setIconImage(icono);

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

        btnInicio.addActionListener(e -> {
            log("Simulación iniciada...");
            mostrarVentanaConfiguracion();
        });

        /*btnParar.addActionListener(e -> log("Simulación detenida."));
        btnReset.addActionListener(e-> log("Elevadores reseteados."));
        btnAgregarSolicitud.addActionListener(e -> {
            String piso = JOptionPane.showInputDialog(this, "Ingrese el piso de destino:");
            logArea.append("Solicitud agregada para el piso: " + piso + "\n");
        });*/

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(anchoPantalla / 2, largoPantalla / 2);
        setLocation(anchoPantalla / 4, largoPantalla / 4);
        setVisible(true);
    }

    private void mostrarVentanaConfiguracion() {

        VentanaConfiguracion vconfig = new VentanaConfiguracion(this);
        vconfig.setVisible(true);
    }

    public void inicialicializarElevadores(int nElevadores, double tiempoEspera, int cantidadPisos, double tiempoTransporte) {
        elevadores = new ElevatorStandard[nElevadores];
        for (int i = 0; i < nElevadores; i++) {
            elevadores[i] = new ElevatorStandard(tiempoEspera, cantidadPisos, tiempoTransporte);
            Thread asyncElevador = new Thread(elevadores[i], "Elevador-" + (i + 1));
            asyncElevador.start();
            log("Elevador #" + (i + 1) + " iniciado.");
        }
        manejador = new ManejoElevadores(elevadores);
        Thread hiloManejador = new Thread(manejador, "ManejadorElevadores");
        hiloManejador.start();
        log("Manejador de elevadores iniciado.");
    }

    public void log(String mensaje) {
        logArea.append(mensaje + "\n");
    }
}