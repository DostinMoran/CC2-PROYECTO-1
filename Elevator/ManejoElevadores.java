package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ManejoElevadores implements Runnable {
    private final ConcurrentLinkedQueue<SolicitudElevador> solicitudes = new ConcurrentLinkedQueue<SolicitudElevador>();
    private final Elevator[] elevadores;
    private volatile boolean enEjecucion = true;

    public ManejoElevadores(Elevator[] elevadores) {
        this.elevadores = elevadores;
    }

    public void solicitarElevador(int piso, Direccion direccion) {
        solicitudes.offer(new SolicitudElevador(piso, direccion));
    }

    @Override
    public String toString() {
        return "Solicitudes en cola: " + solicitudes.size();
    }

    @Override
    public void run() {
        while (enEjecucion) {
            if (!solicitudes.isEmpty()) {
                SolicitudElevador solicitud = solicitudes.poll();
                if (solicitud != null) {
                    Elevator elevadorCercano = null;
                    int distanciaMinima = Integer.MAX_VALUE;
                    for (Elevator elevador : elevadores) {
                        int distanciaElevadores = Math.abs(elevador.getPisoActual() - solicitud.getPiso());
                        if (distanciaElevadores < distanciaMinima) {
                            distanciaMinima = distanciaElevadores;
                            elevadorCercano = elevador;
                        }
                    }
                    if (elevadorCercano != null) {
                        elevadorCercano.agregarSolicitud(solicitud.getPiso());
                        System.out.println("Asignado " + solicitud +
                                " al elevador #" + elevadorCercano.getId() +
                                " (distancia=" + distanciaMinima + ")");
                    }
                }
            } else {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    public void detener() {
        enEjecucion = false;
    }
}
