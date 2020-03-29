/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import static App.GestorDB4O.empleados;
import static App.GestorDB4O.historiales;
import Models.Empleado;
import Models.Historial;
import Utils.Colors;
import Utils.EntradaDatos;
import Utils.MiExcepcion;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author uriishii
 */
public class HistorialBDOR {

    private static final String ERROR_MESSAGE = "Error Option try again.";
    private static final GestorDB4O gestor = new GestorDB4O();

    public static void main(String[] args) {
        startWithHistoriales();
    }

    public static void startWithHistoriales() {
        boolean salir = false;

        gestor.getAllEmpleados();
        if (0 == empleados.size()) {
            gestor.generateTestEmpleados();
        }

        while (!salir) {
            try {
                switch (menu()) {
                    case 1:
                        createHistorial();
                        break;
                    case 2:
                        getLastConnect();
                        break;
                    case 3:
                        System.out.println("En mantenimiento");
                        break;
                    case 4:
                        System.out.println("En mantenimiento");
                        break;
                    case 5:
                        System.out.println("See You!!");
                        System.exit(0);
                        break;
                    default:
                        Colors.printRed(ERROR_MESSAGE);
                }
            } catch (MiExcepcion mx) {
                Colors.printRed(mx.toString());
            }
        }
    }

    private static int menu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("1. Insertar evento");
        System.out.println("2. Obtener ultima conexion");
        System.out.println("3. Obtener ranking");
        System.out.println("4. Obtener posicion dentro del ranking");
        System.out.println("5. Exit");
        System.out.println("----");
        return entrada.nextInt();
    }

    private static void getLastConnect() throws MiExcepcion {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");

        Empleado empleado = new Empleado(userName);
        if (gestor.getEmpleado(empleado) == null) {
            throw new MiExcepcion("Este usuario no existe");
        }
        Historial historial = new Historial();
        historial.setEmpleado(empleado);
        historial = gestor.getLastConnectEmpleado(historial);
        if (historial == null) {
            Colors.printRed("Este empleado no tiene una hora de conexion");
            return;
        }
        System.out.println(historial.toStringFechaHora());
    }

    private static void createHistorial() throws MiExcepcion {
        String idHistorial = EntradaDatos.pedirCadena("Id del eveto (es unico): ");
        String tipo = EntradaDatos.pedirCadena("tipo de incidencia: ");
        String fechahora = EntradaDatos.pedirCadena("Fecha de la incidencias: YY/MM/DD ");
        ArrayList<String> usernameEmpleados = gestor.getAllEmpleadosUsername();
        String userNameEmpleado = EntradaDatos.askString("Nombre Usuario:", usernameEmpleados);
        Historial historial = new Historial(idHistorial);
        if (gestor.getHistorial(historial) != null) {
            throw new MiExcepcion("Error el id Historial ya existente");
        }
        Empleado empleado = new Empleado(userNameEmpleado);
        empleado = gestor.getEmpleado(empleado);
        historial.setTipo(tipo);
        historial.setFechahora(fechahora);
        historial.setEmpleado(empleado);
        gestor.addHistorial(historial);
    }
}
