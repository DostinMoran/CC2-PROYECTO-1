package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ManejoElevadores implements Runnable {

    private final ConcurrentLinkedQueue<SolicitudElevador> solicitudes = new ConcurrentLinkedQueue<>();
    private final Elevator[] elevadores;
    private volatile boolean enEjecucion = true;

    public ManejoElevadores(Elevator[] elevadores) {
        if (elevadores == null || elevadores.length == 0) {
            throw new IllegalArgumentException("Debe existir al menos un elevador");
        }
        this.elevadores = elevadores;
    }

    public void solicitarElevador(int piso, Direccion direccion) {
        solicitudes.offer(new SolicitudElevador(piso, direccion));
    }

    public void detener() {
        enEjecucion = false;
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
                asignarSolicitud(solicitud);
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void asignarSolicitud(SolicitudElevador solicitud) {
        int mejorIdx = -1;
        long mejorCosto = Long.MAX_VALUE;
        int mejorCarga = Integer.MAX_VALUE;
        int mejorId = Integer.MAX_VALUE;

        for (int i = 0; i < elevadores.length; i++) {
            Elevator elevador = elevadores[i];
            long costo = costoParaElevador(elevador, solicitud);
            int carga = contarParadasPendientes(elevador);
            int id = elevador.getId();

            if (costo < mejorCosto
                    || (costo == mejorCosto && carga < mejorCarga)
                    || (costo == mejorCosto && carga == mejorCarga && id < mejorId)) {
                mejorCosto = costo;
                mejorCarga = carga;
                mejorId = id;
                mejorIdx = i;
            }
        }

        if (mejorIdx >= 0) {
            elevadores[mejorIdx].agregarSolicitud(solicitud.getPiso());
            System.out.println("Asignado " + solicitud + " al elevador #" + elevadores[mejorIdx].getId() +
                    " (costo=" + mejorCosto + ", carga=" + mejorCarga + ", id=" + mejorId + ")");
        } else {
            solicitudes.offer(solicitud);
        }
    }

    private long costoParaElevador(Elevator elevador, SolicitudElevador solicitud) {
        int pisoElevador = elevador.getPisoActual();
        Direccion dirElevador = elevador.getDireccion();
        int pisoSolicitud = solicitud.getPiso();
        Direccion dirSolicitud = solicitud.getDireccion();

        int distancia = Math.abs(pisoElevador - pisoSolicitud);
        boolean tieneParadas = hayParadasPendientes(elevador);
        int pendientes = contarParadasPendientes(elevador);
        int pisosMax = elevador.getTableroInterno().length;

        if (!tieneParadas) {
            long base = distancia;
            long bonoIdle = 1;
            return Math.max(0, base - bonoIdle);
        }

        if (dirElevador == Direccion.UP && dirSolicitud == Direccion.UP && pisoSolicitud >= pisoElevador
                && hayParadasArribaDesde(elevador, pisoElevador)) {
            return distancia + pendientes;
        }
        if (dirElevador == Direccion.DOWN && dirSolicitud == Direccion.DOWN && pisoSolicitud <= pisoElevador
                && hayParadasAbajoDesde(elevador, pisoElevador)) {
            return distancia + pendientes;
        }

        int penalCambio = Math.max(3, pisosMax / 3);
        return distancia + penalCambio + 2L * pendientes;
    }

    private boolean hayParadasPendientes(Elevator elevador) {
        for (boolean b : elevador.getTableroInterno())
            if (b)
                return true;
        return !elevador.getsolicitudes().isEmpty();
    }

    private int contarParadasPendientes(Elevator elevador) {
        int c = 0;
        for (boolean b : elevador.getTableroInterno())
            if (b)
                c++;
        c += elevador.getsolicitudes().size();
        return c;
    }

    private boolean hayParadasArribaDesde(Elevator elevador, int desde) {
        boolean[] t = elevador.getTableroInterno();
        for (int i = Math.max(0, desde - 1); i < t.length; i++)
            if (t[i])
                return true;
        return false;
    }

    private boolean hayParadasAbajoDesde(Elevator elevador, int desde) {
        boolean[] t = elevador.getTableroInterno();
        for (int i = Math.min(t.length - 1, desde - 2); i >= 0; i--)
            if (t[i])
                return true;
        return false;
    }
}
