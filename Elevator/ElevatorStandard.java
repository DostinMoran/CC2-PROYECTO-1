package Elevator;

public class ElevatorStandard extends Elevator {
    
    public ElevatorStandard(int tiempoEspera){
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
