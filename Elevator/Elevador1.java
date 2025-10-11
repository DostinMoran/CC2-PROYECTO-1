package Elevator;
import java.util.ArrayList;

public class Elevador1 {
    private int id;
    private int pisoActual;
    private String direccion;
    private boolean reset;
    private int cantidadPisos;
    private int pisoDestino;
    // private String logger;

    public Elevador1(int id, int pisoActual, String direccion, boolean reset, int cantidadPisos, int pisoDestino) {
        this.id = id;
        this.pisoActual = 1;
        this.direccion = "Arriba";
        this.reset = false;
        this.cantidadPisos = cantidadPisos;
        this.pisoDestino = pisoDestino;
    }
    
    // ----------------> GETTERS <------------------------
    public int getId() {
        return this.id;
    }

    public int getPisoActual() {
        return this.pisoActual;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public Boolean getResetElevador(){
        return this.reset;
    }

// ----------------> SETERS <----------------------


public String setSolicitudDePiso(int pisoDestino) {
        ArrayList<Integer> TotalNiveles = new ArrayList<Integer>(this.cantidadPisos);
        if (this.pisoActual == pisoDestino) {
            return String.format("Abriendo puerta en el piso %s", pisoActual);
        }
        if(this.getClass)

        if (this.direccion == "Arriba")
            for (int i = this.pisoActual; i != pisoDestino ; i++) {
                
                return String.format("Subiendo al piso %d", i);
            }       
        
        return "Pormientras";

    }

    public void setResetEvator(boolean reset) {
        if (reset == true) {
            this.direccion = "Arriba";
            this.pisoActual = 1;
        }
    }

};