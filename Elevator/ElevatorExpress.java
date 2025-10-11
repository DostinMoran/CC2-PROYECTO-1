package Elevator;

public class ElevatorExpress extends Elevator {
    public ElevatorExpress(int tiempoEspera, int cantidadPisos){
        super(tiempoEspera, cantidadPisos);
    }

    @Override
    public void mover(){

    }

    @Override 
    public void esperandoCarga(){

    }

    @Override
    public byte getPiso(){
        return super.getPiso();
    }

}
