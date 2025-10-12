package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Elevator implements Movimientos{

    static int cantidadElevadores = 1;
    protected boolean[] tableroInterno;
    protected String direccion;
    protected int id, tiempoEspera, duracionMovimiento, pisoActual, pisoDestino;
    protected ConcurrentLinkedQueue<Integer> cola;

    public Elevator(int tiempoEspera, int cantidadPisos, int duracionMovimiento){
        this.tiempoEspera = tiempoEspera;
        this.duracionMovimiento = duracionMovimiento;
        this.direccion = "up";
        this.pisoActual = 1;
        this.id = cantidadElevadores;
        cantidadElevadores++;
        this.cola = new ConcurrentLinkedQueue<>();
        this.tableroInterno = new boolean[cantidadPisos];
        for (int i = 0; i < this.tableroInterno.length; i++) {
            this.tableroInterno[i] = false;
        }
    }

    public abstract void mover();
    public abstract void parada();

    public int getId(){
        return this.id;
    }

    public int getPisoActual(){
        return this.pisoActual;
    }

    public int getPisoDestino(){
        return this.pisoDestino;
    }

    public String getDireccion(){
        return this.direccion;
    }

    public void resetElevador(){
        if (pisoActual >= 1) {
            this.direccion = "down";
            this.pisoDestino = 1;
            for (int i = this.pisoActual; i >= pisoDestino ; i--) {
                this.pisoActual--;
            }    
        }
    }

    public void agregarSolicitud(int x){
        for (int i = 0; i < this.tableroInterno.length; i++) {
            if (x == i+1) {
                this.tableroInterno[i] = true;
                this.cola.add(x);
            }
        }
    }

    public ConcurrentLinkedQueue<Integer> getsolicitudes(){
        return this.cola;
    }

    public boolean[] getTableroInterno(){
        return this.tableroInterno;
    }
}