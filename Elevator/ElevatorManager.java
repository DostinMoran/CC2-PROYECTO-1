package Elevator;

import java.util.Scanner;
import javax.swing.JFrame;
import Elevator.components.MarcoPantalla;

public class ElevatorManager {
    public static void str(String texto) {
        System.out.println(texto);
    }

    public static void main(String[] args) {
        Scanner esc = new Scanner(System.in);
        int nElevadores, tiempoEspera, cantidadPisos, tiempoTransporte;
        while (true) {
            try {
                str("Favor decida cuántos elevadores tendrá el edificio:");
                nElevadores = Integer.parseInt(esc.nextLine().trim());
                str("Favor colocar el tiempo de espera de los elevadores (en Segundos):");
                tiempoEspera = (Integer.parseInt(esc.nextLine().trim()) * 1000);
                str("Favor colocar la cantidad de pisos que tendrá el edificio:");
                cantidadPisos = Integer.parseInt(esc.nextLine().trim());
                str("Favor colocar la duración de transporte por piso de cada elevador (en Segundos):");
                tiempoTransporte = (Integer.parseInt(esc.nextLine().trim()) * 1000);
                if (nElevadores <= 0 || tiempoEspera <= 0 || cantidadPisos <= 0 || tiempoTransporte <= 0) {
                    str("ERROR: Todos los valores deben ser mayores a 0. Inténtalo de nuevo.\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                str("ERROR: NO ES UN NUMERO\n");
            }
        }
        MarcoPantalla pantallaCentrada = new MarcoPantalla();
        pantallaCentrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaCentrada.setVisible(true);
        Elevator[] elevadores = new Elevator[nElevadores];
        for (int i = 0; i < nElevadores; i++) {
            elevadores[i] = new Elevator(tiempoEspera, cantidadPisos, tiempoTransporte);
            Thread asyncElevador = new Thread(elevadores[i], "Elevador-" + (i + 1));
            asyncElevador.start();
        }
        ManejoElevadores manejador = new ManejoElevadores(elevadores);
        Thread hiloManejador = new Thread(manejador, "ManejadorElevadores");
        hiloManejador.start();

        esc.close();
        str("Elevadores iniciados: " + nElevadores);
    }
}
