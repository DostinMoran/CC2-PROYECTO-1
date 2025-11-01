package Elevator.components;

import java.util.logging.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


public final class ElevatorLogger {
    
    private ElevatorLogger() {

    }

    public static Logger cache(int id) {
        Logger logger = Logger.getLogger("Elevador-" + id);
        logger.setUseParentHandlers(true);

        if (logger.getHandlers().length == 0) {
            try {
                FileHandler archivo = new FileHandler("Elevador-" + id + ".log", true);
                archivo.setFormatter(new SimpleFormatter());
                logger.addHandler(archivo);
            } catch (IOException | NullPointerException e) {
                throw new Error("ERROR: " + e.getMessage());
            }
        }
        return logger;
    }

    
}