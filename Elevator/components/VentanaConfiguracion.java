package Elevator.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaConfiguracion extends JDialog {

    private JTextField txtElevadores, txtTiempoEspera, txtPisos, txtTiempoTransporte;
    private JButton btnAceptar, btnCancelar;
    private ActionListener listenerAceptar;

    public VentanaConfiguracion(JFrame parent) {
        super(parent, "Configuración del edificio", true);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Número de elevadores:"));
        txtElevadores = new JTextField();
        add(txtElevadores);

        add(new JLabel("Tiempo de espera (segundos):"));
        txtTiempoEspera = new JTextField();
        add(txtTiempoEspera);

        add(new JLabel("Cantidad de pisos:"));
        txtPisos = new JTextField();
        add(txtPisos);

        add(new JLabel("Duración transporte por piso (segundos):"));
        txtTiempoTransporte = new JTextField();
        add(txtTiempoTransporte);

        btnAceptar = new JButton("Aceptar");
        add(btnAceptar);
        btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        btnAceptar.addActionListener(e -> {
            listenerAceptar.actionPerformed(e);
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        setSize(400, 250);
        setLocationRelativeTo(parent);
    }

    public void setlistenerAceptar() {
        this.listenerAceptar = e -> {
             try {
                
                int nElevadores = this.getNumElevadores();
                double tiempoEspera = this.getTiempoEspera();
                int cantidadPisos = this.getCantidadPisos();
                double tiempoTransporte = this.getTiempoTransporte();

                inicialicializarElevadores(nElevadores, tiempoEspera, cantidadPisos, tiempoTransporte);
                log("Configuración establecida");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados " + ex.getMessage());
            }
        };
    }

    public void inicialicializarElevadores(int nElevadores, double tiempoEspera, int cantidadPisos, double tiempoTransporte) {
        ((MarcoPantalla) getParent()).inicialicializarElevadores(nElevadores, tiempoEspera, cantidadPisos, tiempoTransporte);
    }

    public void log(String mensaje) {
        ((MarcoPantalla) getParent()).log(mensaje);
    }

    public int getNumElevadores() { return Integer.parseInt(txtElevadores.getText()); }
    public double getTiempoEspera() { return Double.parseDouble(txtTiempoEspera.getText()); }
    public int getCantidadPisos() { return Integer.parseInt(txtPisos.getText()); }
    public double getTiempoTransporte() { return Double.parseDouble(txtTiempoTransporte.getText()); }
}
