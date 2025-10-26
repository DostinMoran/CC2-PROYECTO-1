package Elevator;
import java.util.Scanner;
import javax.swing.JFrame;
import Elevator.components.marcoPantalla;

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

                str("Favor colocar el tiempo de espera de los elevadores (en milisegundos):");
                tiempoEspera = Integer.parseInt(esc.nextLine().trim());

                str("Favor colocar la cantidad de pisos que tendrá el edificio:");
                cantidadPisos = Integer.parseInt(esc.nextLine().trim());

                str("Favor colocar la duración de transporte por piso de cada elevador (ms):");
                tiempoTransporte = Integer.parseInt(esc.nextLine().trim());

                if (nElevadores <= 0 || tiempoEspera <= 0 || cantidadPisos <= 0 || tiempoTransporte <= 0) {
                    str("ERROR: Todos los valores deben ser mayores a 0. Inténtalo de nuevo.\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                str("ERROR: NO ES UN NUMERO\n");
            }
        }
        marcoPantalla pantallaCentrada = new marcoPantalla();
        pantallaCentrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaCentrada.setVisible(true);
        Elevator[] elevadores = new Elevator[nElevadores];
        for (int i = 0; i < nElevadores; i++) {
            elevadores[i] = new Elevator(tiempoEspera, cantidadPisos, tiempoTransporte);
            Thread asyncElevador = new Thread(elevadores[i]);
            asyncElevador.start();
        }
        esc.close();
        str("Elevadores iniciados: " + nElevadores);    
    }
}
