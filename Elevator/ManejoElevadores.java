package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ManejoElevadores {
    private final ConcurrentLinkedQueue<SolicitudElevador> solicitudes = new ConcurrentLinkedQueue<SolicitudElevador>();
    private final Elevator[] elevadores;
    private volatile boolean elevadorEnEjecucion = true;

    public ManejoElevadores(Elevator[] elevadores) {
        this.elevadores = elevadores;
    }

    public void solicitarElevador(int piso, Direccion direccion) { 
        solicitudes.offer(new SolicitudElevador(piso, direccion)); 
    }

}
