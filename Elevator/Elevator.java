package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Elevator implements Movimientos{

    static int cantidadElevadores = 1;
    protected int[] tableroInterno;
    protected String direccion;
    protected int id, tiempoEspera, pisoActual, pisoDestino;
    protected ConcurrentLinkedQueue<Integer> cola;

    public Elevator(int tiempoEspera, int cantidadPisos){
        this.tiempoEspera = tiempoEspera;
        this.direccion = "up";
        this.pisoActual = 1;
        this.id = cantidadElevadores;
        cantidadElevadores++;
        this.tableroInterno = new int[cantidadPisos];
    }

    public abstract void mover();
    public abstract void esperandoCarga();

    public int getId(){
        return this.id;
    }

    public int getPisoActual(){
        return this.pisoActual;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public void resetElevador(){
        if (pisoActual >= 1) {
            this.direccion = "down";
            this.pisoDestino = 1;    
        }
    }

    public void solicitud(int x){
        this.cola.add(x);
    }

    public ConcurrentLinkedQueue<Integer> solicitudes(){
        return this.cola;
    }
}