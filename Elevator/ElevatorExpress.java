package Elevator;

public class ElevatorExpress extends Elevator {
    public ElevatorExpress(int tiempoEspera){
        super(tiempoEspera);
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
