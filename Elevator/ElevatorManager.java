package Elevator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

import Elevator.components.LectorSolicitudes;
import Elevator.components.MarcoPantalla;

public class ElevatorManager {
    public static void mostrar(String texto) {
        System.out.println(texto);
    }

    public static void main(String[] args) {
        Scanner esc = new Scanner(System.in);
        double tiempoEspera, tiempoTransporte;
        int nElevadores, cantidadPisos;

        while (true) {
            try {
                mostrar("Ingrese el número de elevadores que tendrá el edificio:");
                nElevadores = Integer.parseInt(esc.nextLine().trim());
                mostrar("Ingrese el tiempo de espera de los elevadores (en segundos):");
                tiempoEspera = Double.parseDouble(esc.nextLine().trim());
                mostrar("Ingrese la cantidad de pisos que tendrá el edificio:");
                cantidadPisos = Integer.parseInt(esc.nextLine().trim());
                mostrar("Ingrese la duración de transporte por piso de cada elevador (en segundos):");
                tiempoTransporte = Double.parseDouble(esc.nextLine().trim());
                if (nElevadores <= 0 || tiempoEspera <= 0 || cantidadPisos <= 0 || tiempoTransporte <= 0) {
                    mostrar("ERROR: Todos los valores deben ser mayores a 0.\n");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                mostrar("ERROR: Ingrese únicamente números válidos.\n");
            }
        }

        MarcoPantalla pantallaCentrada = new MarcoPantalla();
        pantallaCentrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaCentrada.setVisible(true);

        ElevatorStandard[] elevadores = new ElevatorStandard[nElevadores];
        for (int i = 0; i < nElevadores; i++) {
            elevadores[i] = new ElevatorStandard(tiempoEspera, cantidadPisos, tiempoTransporte);
            Thread hilo = new Thread(elevadores[i], "Elevador-" + (i + 1));
            hilo.start();
        }

        ManejoElevadores manejador = new ManejoElevadores(elevadores);
        Thread hiloManejador = new Thread(manejador, "ManejadorElevadores");
        hiloManejador.start();

        try {
            List<SolicitudElevador> reqs = LectorSolicitudes.cargar("solicitudes.txt");
            for (SolicitudElevador s : reqs)
                manejador.solicitarElevador(s.getPiso(), s.getDireccion());
            mostrar("Archivo de solicitudes cargado correctamente.");
        } catch (IOException e) {
            mostrar("No se pudo leer el archivo de solicitudes: " + e.getMessage());
        }

        mostrar("Elevadores iniciados: " + nElevadores);
        mostrar("Comandos disponibles:");
        mostrar("SOLICITAR <piso> <SUBIR|BAJAR>");
        mostrar("ELEGIR <idElevador> <piso>");
        mostrar("REINICIAR");
        mostrar("CARGAR <ruta>");
        mostrar("DETENER");
        mostrar("AYUDA");
        mostrar("SALIR");

        boolean salir = false;
        while (!salir) {
            String linea = esc.nextLine().trim();
            if (linea.isEmpty())
                continue;
            String[] p = linea.split("\\s+");
            String cmd = p[0].toUpperCase();

            switch (cmd) {
                case "SOLICITAR":
                    if (p.length == 3) {
                        try {
                            int piso = Integer.parseInt(p[1]);
                            String dirTxt = p[2].toUpperCase();
                            Direccion dir = dirTxt.equals("SUBIR") ? Direccion.UP
                                    : dirTxt.equals("BAJAR") ? Direccion.DOWN : null;
                            if (dir != null) {
                                manejador.solicitarElevador(piso, dir);
                                mostrar("Solicitud registrada correctamente.");
                            } else
                                mostrar("Dirección no válida. Use SUBIR o BAJAR.");
                        } catch (Exception ex) {
                            mostrar("Uso correcto: SOLICITAR <piso> <SUBIR|BAJAR>");
                        }
                    } else
                        mostrar("Uso correcto: SOLICITAR <piso> <SUBIR|BAJAR>");
                    break;

                case "ELEGIR":
                    if (p.length == 3) {
                        try {
                            int id = Integer.parseInt(p[1]);
                            int piso = Integer.parseInt(p[2]);
                            manejador.agregarSolicitudInterna(id, piso);
                            mostrar("Piso interno agregado al elevador #" + id);
                        } catch (Exception ex) {
                            mostrar("Uso correcto: ELEGIR <idElevador> <piso>");
                        }
                    } else
                        mostrar("Uso correcto: ELEGIR <idElevador> <piso>");
                    break;

                case "REINICIAR":
                    manejador.resetSistema();
                    mostrar("Todos los elevadores fueron reiniciados correctamente.");
                    break;

                case "CARGAR":
                    if (p.length >= 2) {
                        String ruta = linea.substring(linea.indexOf(' ') + 1);
                        try {
                            List<SolicitudElevador> reqs2 = LectorSolicitudes.cargar(ruta);
                            for (SolicitudElevador s : reqs2)
                                manejador.solicitarElevador(s.getPiso(), s.getDireccion());
                            mostrar("Archivo cargado correctamente desde: " + ruta);
                        } catch (IOException ex) {
                            mostrar("Error al cargar archivo: " + ex.getMessage());
                        }
                    } else
                        mostrar("Uso correcto: CARGAR <ruta>");
                    break;

                case "DETENER":
                    manejador.detener();
                    for (Elevator e : elevadores)
                        e.setDetenerElevadores();
                    mostrar("Sistema detenido correctamente.");
                    break;

                case "AYUDA":
                    mostrar("Comandos disponibles:");
                    mostrar("SOLICITAR <piso> <SUBIR|BAJAR>");
                    mostrar("ELEGIR <idElevador> <piso>");
                    mostrar("REINICIAR");
                    mostrar("CARGAR <ruta>");
                    mostrar("DETENER");
                    mostrar(" SALIR");
                    break;

                case "SALIR":
                    salir = true;
                    break;

                default:
                    mostrar("Comando no reconocido. Escriba AYUDA para ver la lista de comandos.");
            }
        }

        esc.close();
    }
}
