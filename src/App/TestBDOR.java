/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import static App.HistorialBDOR.startWithHistoriales;
import static App.IncidenciasBDOR.startIncidenciasEmpleados;
import Utils.Colors;
import java.util.Scanner;

/**
 *
 * @author uriishii
 */
public class TestBDOR {

    private static final String ERROR_MESSAGE = "Error Option try again.";

    public static void main(String[] args) {

        boolean exit = false;
        while (!exit) {
            switch (menu()) {
                case 1:
                    startIncidenciasEmpleados();
                    break;
                case 2:
                    startWithHistoriales();
                    break;
                default:
                    Colors.printRed(ERROR_MESSAGE);
            }

        }
    }

    private static int menu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("1. Incidencias & Empleados");
        System.out.println("2. Historiales");
        System.out.println("3. Exit");
        System.out.println("----");
        return entrada.nextInt();
    }
}
