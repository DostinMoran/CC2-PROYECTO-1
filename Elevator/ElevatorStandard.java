package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ElevatorStandard extends Elevator {

    public ElevatorStandard(int tiempoEspera, int cantidadPisos, int duracionMovimiento) {
        super(tiempoEspera, cantidadPisos, duracionMovimiento);
    }

    public void mover(int duracionMovimiento) {
        super.mover(duracionMovimiento);
    }

    public void parada(int tiempoEspera) {
        super.parada(tiempoEspera);
    }

    public int getId() {
        return super.getId();
    }

    @Override
    public int getPisoActual() {
        return super.getPisoActual();
    }

    @Override
    public int getPisoDestino() {
        return super.getPisoDestino();
    }

    @Override
    public Direccion getDireccion() {
        return super.getDireccion();
    }

    @Override
    public void resetElevador() {
        super.resetElevador();
    }

    @Override
    public void agregarSolicitud(int x) {
        super.agregarSolicitud(x);
    }

    @Override
    public ConcurrentLinkedQueue<Integer> getsolicitudes() {
        return super.getsolicitudes();
    }

    @Override
    public boolean[] getTableroInterno() {
        return super.getTableroInterno();
    }

}
