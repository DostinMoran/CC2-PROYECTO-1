package Elevator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

import Elevator.components.LectorSolicitudes;
import Elevator.components.MarcoPantalla;

public class ElevatorManager {
    public static void str(String texto) {
        System.out.println(texto);
    }

    public static void main(String[] args) {

        Scanner esc = new Scanner(System.in);
        double tiempoEspera, tiempoTransporte;
        int nElevadores, cantidadPisos;

        while (true) {
            try {
                str("Ingrese el número de elevadores tendrá el edificio:");
                nElevadores = Integer.parseInt(esc.nextLine().trim());
                str("Ingrese el tiempo de espera de los elevadores (en Segundos):");
                tiempoEspera = Double.parseDouble(esc.nextLine().trim());
                str("Ingrese la cantidad de pisos que tendrá el edificio:");
                cantidadPisos = Integer.parseInt(esc.nextLine().trim());
                str("Ingrese la duración de transporte por piso de cada elevador (Segundos):");
                tiempoTransporte = Double.parseDouble(esc.nextLine().trim());
                if (nElevadores <= 0 || tiempoEspera <= 0 || cantidadPisos <= 0 || tiempoTransporte <= 0) {
                    str("ERROR: Todos los valores deben ser mayores a 0. Inténtalo de nuevo.\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                str("ERROR: NO ES UN NUMERO\n");
            }
        }

        /* ------------------------ Interfaz gráfica ------------------------ */
        MarcoPantalla pantallaCentrada = new MarcoPantalla();
        pantallaCentrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaCentrada.setVisible(true);
        /* ------------------------------------------------------------------- */

        ElevatorStandard[] elevadores = new ElevatorStandard[nElevadores];
        for (int i = 0; i < nElevadores; i++) {
            elevadores[i] = new ElevatorStandard(tiempoEspera, cantidadPisos, tiempoTransporte);
            Thread asyncElevador = new Thread(elevadores[i], "Elevador-" + (i + 1));
            asyncElevador.start();
        }
        ManejoElevadores manejador = new ManejoElevadores(elevadores);
        Thread hiloManejador = new Thread(manejador, "ManejadorElevadores");
        hiloManejador.start();
        try {
            List<SolicitudElevador> reqs = LectorSolicitudes.cargar("solicitudes.txt");
            for (SolicitudElevador s : reqs) {
                manejador.solicitarElevador(s.getPiso(), s.getDireccion());
            }
            System.out.println("Archivo de solicitudes cargado correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de solicitudes: " + e.getMessage());
        }
        manejador.solicitarElevador(5, Direccion.UP);
        manejador.solicitarElevador(3, Direccion.UP);
        manejador.solicitarElevador(2, Direccion.DOWN);
        manejador.solicitarElevador(7, Direccion.UP);
        manejador.solicitarElevador(8, Direccion.UP);
        manejador.solicitarElevador(5, Direccion.UP);
        manejador.solicitarElevador(1, Direccion.DOWN);

        esc.close();
        str("Elevadores iniciados: " + nElevadores);
    }
}
