package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ElevatorExpress extends Elevator {
    public ElevatorExpress(int tiempoEspera, int cantidadPisos, int duracionMovimiento) {
        super(tiempoEspera, cantidadPisos, duracionMovimiento);
    }

    // Debido a que la funcionamiento del elevador express es diferente al estandar,
    // tengo pendiente revisar este metodo.
    @Override
    public void mover(int duracionMovimiento) {

    }

    @Override
    public void parada(int tiempoEspera) {
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
