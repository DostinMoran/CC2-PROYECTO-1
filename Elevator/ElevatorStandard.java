package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ElevatorStandard extends Elevator {
    
    public ElevatorStandard(int tiempoEspera, int cantidadPisos, int duracionMovimiento ){
        super(tiempoEspera,cantidadPisos, duracionMovimiento);
    }
// Chatito me ayudo con este metodo :D, tengo que revisarlo despues.
    @Override
    public void mover(int duracionMovimiento){
        for (int i = 0; i < this.tableroInterno.length; i++) {
            if (this.tableroInterno[i] == true) {
                this.pisoDestino = i+1;
                if (this.pisoActual < this.pisoDestino) {
                    this.direccion = "up";
                    for (int j = this.pisoActual; j < pisoDestino ; j++) {
                        try {
                            Thread.sleep(duracionMovimiento);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.pisoActual++;
                    }    
                } else if (this.pisoActual > this.pisoDestino) {
                    this.direccion = "down";
                    for (int j = this.pisoActual; j > pisoDestino ; j--) {
                        try {
                            Thread.sleep(duracionMovimiento);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.pisoActual--;
                    }    
                }
            }
        }
    }

    @Override 
    public void parada(int tiempoEspera){
        for (int i = 0; i < this.tableroInterno.length; i++) {
            if (this.tableroInterno[i] == true) {
                this.tableroInterno[i] = false;
            }
        }
    }

    @Override
    public int getId(){
        return super.getId();
    }   
    @Override
    public int getPisoActual(){
        return super.getPisoActual();
    }
    @Override
    public int getPisoDestino(){
        return super.getPisoDestino();
    }  
    @Override
    public String getDireccion(){
        return super.getDireccion();
    }   
    @Override
    public void resetElevador(){
        super.resetElevador();
    }
    @Override
    public void agregarSolicitud(int x){
        super.agregarSolicitud(x);
    }
    @Override
    public ConcurrentLinkedQueue<Integer> getsolicitudes(){
        return super.getsolicitudes();
    }
    @Override
    public boolean[] getTableroInterno(){
        return super.getTableroInterno();
    }

}
