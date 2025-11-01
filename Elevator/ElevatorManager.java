package Elevator;

import java.util.Scanner;
import javax.swing.JFrame;
import Elevator.components.MarcoPantalla;

public class ElevatorManager {
    /*public static void str(String texto) {
        System.out.println(texto);
    }*/

    public static void main(String[] args) {
        
        /*Scanner esc = new Scanner(System.in);
        double tiempoEspera, tiempoTransporte;
        int nElevadores, cantidadPisos;*/

        /*while (true) {
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
        }*/

        /* ------------------------ Implementación Interfaz gráfica ------------------------ */
        MarcoPantalla pantallaCentrada = new MarcoPantalla();
        /* --------------------------------------------------------------------------------- */

        /*ElevatorStandard[] elevadores = new ElevatorStandard[nElevadores];

        for (int i = 0; i < nElevadores; i++) {
            elevadores[i] = new ElevatorStandard(tiempoEspera, cantidadPisos, tiempoTransporte);
            Thread asyncElevador = new Thread(elevadores[i], "Elevador-" + (i + 1));
            asyncElevador.start();
        }
        ManejoElevadores manejador = new ManejoElevadores(elevadores);
        Thread hiloManejador = new Thread(manejador, "ManejadorElevadores");
        hiloManejador.start();

        manejador.solicitarElevador(5, Direccion.UP);
        manejador.solicitarElevador(7, Direccion.UP);
        manejador.solicitarElevador(8, Direccion.UP);
        manejador.solicitarElevador(13, Direccion.UP);
        manejador.solicitarElevador(1, Direccion.DOWN);
        manejador.solicitarElevador(5, Direccion.UP);
        manejador.solicitarElevador(2, Direccion.UP);
        manejador.solicitarElevador(1, Direccion.UP);
        manejador.solicitarElevador(18, Direccion.DOWN);

        esc.close();
        str("Elevadores iniciados: " + nElevadores);*/
    }
}
