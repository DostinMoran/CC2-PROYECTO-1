package Elevator;

public abstract class Elevator implements Movimientos{

    static int cantidadElevadores = 0;
    protected byte[] tableroInterno;
    protected String direccion;
    protected byte pisoActual;
    protected int id, tiempoEspera;

    public Elevator(int tiempoEspera){
        this.tiempoEspera = tiempoEspera;
        this.direccion = "up";
        this.pisoActual = 1;
        cantidadElevadores++;
        this.id = cantidadElevadores;
    }

    public abstract void mover();
    public abstract void esperandoCarga();

    public byte getPiso(){
        return this.pisoActual;
    }

}