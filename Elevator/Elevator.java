package Elevator;

import java.util.concurrent.ConcurrentLinkedQueue;
public class Elevator implements Movimientos, Runnable {
    static int cantidadElevadores = 1;
    protected boolean[] tableroInterno;
    protected int id, tiempoEspera, duracionMovimiento, pisoActual, pisoDestino;
    protected boolean detenerElevadores;
    protected ConcurrentLinkedQueue<Integer> cola;
    protected Direccion direccion;

    public Elevator(int tiempoEspera, int cantidadPisos, int duracionMovimiento){
        this.tiempoEspera = tiempoEspera;
        this.direccion = Direccion.UP;
        this.duracionMovimiento = duracionMovimiento;
        this.pisoActual = 1;
        this.id = cantidadElevadores;
        cantidadElevadores++;
        this.cola = new ConcurrentLinkedQueue<>();
        this.tableroInterno = new boolean[cantidadPisos];
        for (int i = 0; i < this.tableroInterno.length; i++) {
            this.tableroInterno[i] = false;
        }
        this.detenerElevadores = false;
    }

    public void mover(int duracionMovimiento){
        for (int i = 0; i < this.tableroInterno.length; i++) {
            if (this.tableroInterno[i] == true) {
                this.pisoDestino = i+1;
                if (this.pisoActual < this.pisoDestino) {
                    this.direccion = Direccion.UP;
                    for (int j = this.pisoActual; j < pisoDestino ; j++) {
                        try {
                            Thread.sleep(duracionMovimiento);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.pisoActual++;
                    }    
                } else if (this.pisoActual > this.pisoDestino) {
                    this.direccion = Direccion.DOWN;
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
    public void parada(int tiempoEspera){
        for (int i = 0; i < this.tableroInterno.length; i++) {
            if (this.tableroInterno[i] == true) {
                this.tableroInterno[i] = false;
            }
        }
    }

    public int getId(){
        return this.id;
    }

    public int getPisoActual(){
        return this.pisoActual;
    }

    public int getPisoDestino(){
        return this.pisoDestino;
    }

    public Direccion getDireccion(){
        return this.direccion;
    }

    public void resetElevador(){
        if (pisoActual >= 1) {
            this.direccion = Direccion.DOWN;
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

    public boolean[] getTableroInterno() {
        return this.tableroInterno;
    }

    public void setDetenerElevadores() {
        this.detenerElevadores = true;
    }

    @Override
    public void run() {
        while (!this.detenerElevadores) {
            
        }
    }
}