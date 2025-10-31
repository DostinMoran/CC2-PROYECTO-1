package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ElevatorExpress extends Elevator {
    public ElevatorExpress(double tiempoEspera, int cantidadPisos, double duracionMovimiento) {
        super(tiempoEspera, cantidadPisos, duracionMovimiento);
    }

    // Debido a que la funcionamiento del elevador express es diferente al estandar,
    // tengo pendiente revisar este metodo.
    @Override
    public void mover(double duracionMovimiento) {

    }

    @Override
    public void parada(double tiempoEspera) {
        for (int i = 0; i < this.tableroInterno.length; i++) {
            if (this.tableroInterno[i] == true) {
                this.tableroInterno[i] = false;
            }
        }
    }

    @Override
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
    public boolean[] getTableroInterno() {
        return super.getTableroInterno();
    }

    @Override
    public ConcurrentLinkedQueue<Integer> getsolicitudes() {
        return super.getsolicitudes();
    }

}
