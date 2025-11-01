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
            SolicitudElevador s = solicitudes.poll();
            if (s != null) {
                asignarSolicitud(s);
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void asignarSolicitud(SolicitudElevador s) {
        int mejorIdx = -1;
        long mejorCosto = Long.MAX_VALUE;
        int mejorCarga = Integer.MAX_VALUE;
        int mejorId = Integer.MAX_VALUE;

        for (int i = 0; i < elevadores.length; i++) {
            Elevator e = elevadores[i];
            long costo = costoParaElevador(e, s);
            int carga = contarParadasPendientes(e);
            int id = e.getId();

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
            elevadores[mejorIdx].agregarSolicitud(s.getPiso());
            System.out.println("Asignado " + s + " al elevador #" + elevadores[mejorIdx].getId() +
                    " (costo=" + mejorCosto + ", carga=" + mejorCarga + ", id=" + mejorId + ")");
        } else {
            solicitudes.offer(s);
        }
    }

    private long costoParaElevador(Elevator e, SolicitudElevador s) {
        int pisoE = e.getPisoActual();
        Direccion dirE = e.getDireccion();
        int pisoS = s.getPiso();
        Direccion dirS = s.getDireccion();

        int distancia = Math.abs(pisoE - pisoS);
        boolean tieneParadas = hayParadasPendientes(e);
        int pendientes = contarParadasPendientes(e);
        int pisosMax = e.getTableroInterno().length;

        if (!tieneParadas) {
            long base = distancia;
            long bonoIdle = 1;
            return Math.max(0, base - bonoIdle);
        }

        if (dirE == Direccion.UP && dirS == Direccion.UP && pisoS >= pisoE && hayParadasArribaDesde(e, pisoE)) {
            return distancia + pendientes;
        }
        if (dirE == Direccion.DOWN && dirS == Direccion.DOWN && pisoS <= pisoE && hayParadasAbajoDesde(e, pisoE)) {
            return distancia + pendientes;
        }

        int penalCambio = Math.max(3, pisosMax / 3);
        return distancia + penalCambio + 2L * pendientes;
    }

    private boolean hayParadasPendientes(Elevator e) {
        for (boolean b : e.getTableroInterno())
            if (b)
                return true;
        return !e.getsolicitudes().isEmpty();
    }

    private int contarParadasPendientes(Elevator e) {
        int c = 0;
        for (boolean b : e.getTableroInterno())
            if (b)
                c++;
        c += e.getsolicitudes().size();
        return c;
    }

    private boolean hayParadasArribaDesde(Elevator e, int desde) {
        boolean[] t = e.getTableroInterno();
        for (int i = Math.max(0, desde - 1); i < t.length; i++)
            if (t[i])
                return true;
        return false;
    }

    private boolean hayParadasAbajoDesde(Elevator e, int desde) {
        boolean[] t = e.getTableroInterno();
        for (int i = Math.min(t.length - 1, desde - 2); i >= 0; i--)
            if (t[i])
                return true;
        return false;
    }
}
