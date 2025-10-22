package Elevator;
import java.util.Scanner;
import javax.swing.JFrame;
import java.lang.Thread;

public class ElevatorManager {

    public static void str(String texto) {
        System.out.println(texto + "\n");
    }

    public static void str(String texto, String variable) {
        System.out.printf("%s %s\n", texto, variable);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int nElevadores = 0;
        int tiempoEspera = 0;
        int cantidadPisos = 0;
        int tiempoTransporte = 0;

        while (true) {
            try {
                str("Favor decida cuántos elevadores tendrá el edificio:");
                nElevadores = Integer.parseInt(in.nextLine().trim());

                str("Favor colocar el tiempo de espera de los elevadores (en milisegundos):");
                tiempoEspera = Integer.parseInt(in.nextLine().trim());

                str("Favor colocar la cantidad de pisos que tendrá el edificio:");
                cantidadPisos = Integer.parseInt(in.nextLine().trim());

                str("Favor colocar la duración de transporte por piso de cada elevador (ms):");
                tiempoTransporte = Integer.parseInt(in.nextLine().trim());

                if (nElevadores <= 0 || tiempoEspera <= 0 || cantidadPisos <= 0 || tiempoTransporte <= 0) {
                    str("ERROR: Todos los valores deben ser mayores a 0. Inténtalo de nuevo.\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                str("ERROR: NO ES UN NUMERO\n");
            }
        }
        Elevator[] elevadores = new Elevator[nElevadores];
        for (int i = 0; i < elevadores.length; i++) {
            // elevadores[i] = new Elevator(tiempoEspera, cantidadPisos, tiempoTransporte); //Todo: ERROR por abstract en Elevator
        }
        Render render = new Render();
        render.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        render.setVisible(true);
        in.close();
    }
}