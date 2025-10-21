package Elevator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;

public class ElevatorManager {
    
    public static void str(String texto) {
        System.out.println(texto);
    }
    public static void str(String texto, String variable) {
        System.out.printf(" %s %s", texto, variable);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader textL = new BufferedReader(new InputStreamReader(System.in));
        Scanner textC = new Scanner(System.in);
        str("Favor decida cuantos elevadores tendra el edificio");
        int nElevadores = Integer.parseInt(textL.readLine());
        //TODO: Pendiente crear instanciaciones de cantidad de elevadores.

        Render render = new Render();
        render.setVisible(true);
        render.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}