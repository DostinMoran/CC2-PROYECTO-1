package Elevator;

public abstract class Elevator implements Movimientos{

    static int cantidadElevadores = 1;
    protected int[] tableroInterno;
    protected String direccion;
    protected byte pisoActual, pisoDestino;
    protected int id, tiempoEspera;
    // protected boolean reset;

    public Elevator(int tiempoEspera, int cantidadPisos){
        this.tiempoEspera = tiempoEspera;
        this.direccion = "up";
        this.pisoActual = 1;
        this.id = cantidadElevadores;
        cantidadElevadores++;
        this.tableroInterno = new int[cantidadPisos];
        this.pisoDestino = 1;
    }

    public abstract void mover();
    public abstract void esperandoCarga();

    public int getId(){
        return this.id;
    }

    public int getPisoActual() {
        return this.pisoActual;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void resetElevador(){
        this.pisoDestino = 1;
        
    }


}