package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ManejoElevadores implements Runnable {
    private final ConcurrentLinkedQueue<SolicitudElevador> solicitudes = new ConcurrentLinkedQueue<>();
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
            SolicitudElevador solicitud = solicitudes.poll();

            if (solicitud != null) {
                Elevator elegido = elegirElevador(solicitud.getPiso(), solicitud.getDireccion());

                if (elegido != null) {
                    elegido.agregarSolicitud(solicitud.getPiso());
                    System.out.println("Asignado " + solicitud +
                            " al elevador #" + elegido.getId() +
                            " (piso actual: " + elegido.getPisoActual() + ")");
                } else {
                    solicitudes.offer(solicitud);
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private Elevator elegirElevador(int piso, Direccion direccion) {
        Elevator mejorElevador = null;
        int menorDistancia = Integer.MAX_VALUE;

        for (Elevator e : elevadores) {
            int distancia = Math.abs(e.getPisoActual() - piso);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                mejorElevador = e;
            }
        }

        return mejorElevador;
    }

    public void detener() {
        enEjecucion = false;
    }
}
