/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Models.Empleado;
import Models.Incidencia;
import Utils.Colors;
import Utils.EntradaDatos;
import Utils.MiExcepcion;
import java.util.Scanner;

/**
 *
 * @author uriishii
 */
public class IncidenciasBDOR {

    private static final String ERROR_MESSAGE = "Error Option try again.";
    private static final GestorDB4O gestor = new GestorDB4O();

    public static void main(String[] args) {
        startIncidenciasEmpleados();
    }

    public static void startIncidenciasEmpleados() {
        boolean exit = false;
        while (!exit) {
            switch (menu()) {
                case 1:
                    workWithIncidencias();
                    break;
                case 2:
                    workWithEmpleados();
                    break;
                case 3:
                    System.out.println("See You!!");
                    exit = true;
                    System.exit(0);
                    break;
                default:
                    Colors.printRed(ERROR_MESSAGE);
            }
        }
    }

    private static int menu() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("1. Incidencias");
        System.out.println("2. Empleados");
        System.out.println("3. Exit");
        System.out.println("----");
        return entrada.nextInt();
    }

    /* INCIDENCIAS */
    private static void workWithIncidencias() {
        boolean salirInci = false;
        String table = "";

        while (!salirInci) {
            try {
                switch (menuInci()) {
                    case "a":
                        getIncidenciaByID();
                        break;
                    case "b":
                        break;
                    case "c":
                        break;
                    case "d":
                        table = "destino";
                        break;
                    case "e":
                        table = "origen";
                        break;
                    case "f":
                        salirInci = true;
                        break;
                    default:
                        Colors.printRed(ERROR_MESSAGE);
                }
            } catch (MiExcepcion mx) {
                Colors.printRed(mx.toString());
            }
        }
    }

    private static String menuInci() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("a. Mostrar 1 Incidencia");
        System.out.println("b. Mostrar Incidencias");
        System.out.println("c. Insertar Incidencia");
        System.out.println("d. Mostrar Incidencia gestionadas por Empleado");
        System.out.println("e. Mostrar Incidencia creadas por Empleado");
        System.out.println("f. Exit");
        return entrada.next();
    }

    private static void getIncidenciaByID() throws MiExcepcion {
        int idInci = EntradaDatos.pedirEntero("Numero de incidencia: ");
        Incidencia incidencia = new Incidencia(idInci);
        incidencia = gestor.getIncidencia(incidencia);
        if (incidencia == null) {
            throw new MiExcepcion("Error no existe empleado con ese usuario");
        }
        System.out.println(incidencia.toString());
    }

    /* EMPLEADOS */
    private static void workWithEmpleados() {
        boolean salirEmp = false;
        while (!salirEmp) {
            try {
                switch (menuEmp()) {
                    case "a":
                        createEmpleado();
                        break;
                    case "b":
                        loginEmpleado();
                        break;
                    case "c":
                        modifyEmpleado();
                        break;
                    case "d":
                        changePasswordEmpleado();
                        break;
                    case "e":
                        deleteEmpleado();
                        break;
                    case "f":
                        salirEmp = true;
                        break;
                    default:
                        Colors.printRed(ERROR_MESSAGE);
                }
            } catch (MiExcepcion mx) {
                Colors.printRed(mx.toString());
            }
        }
    }

    private static String menuEmp() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- MENU --");
        System.out.println("a. Insertar");
        System.out.println("b. Login");
        System.out.println("c. Modificar");
        System.out.println("d. Cambiar password");
        System.out.println("e. Eliminar ");
        System.out.println("f. Exit");
        System.out.println("g. ver todo");
        return entrada.next();
    }

    private static void createEmpleado() throws MiExcepcion {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        String password = EntradaDatos.pedirCadena("Password: ");
        String completName = EntradaDatos.pedirCadena("Nombre completo: ");
        String phone = EntradaDatos.pedirCadena("Telefono:");
        Empleado empleado = new Empleado(userName);
        if (gestor.getEmpleado(empleado) != null) {
            throw new MiExcepcion("Error empleado ya existente");
        }
        empleado.setPassword(password);
        empleado.setNombrecompleto(completName);
        empleado.setTelefono(phone);
        gestor.addEmpleado(empleado);
    }

    private static void deleteEmpleado() throws MiExcepcion {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario a eliminar: ");
        Empleado empleado = new Empleado(userName);

        if (gestor.getEmpleado(empleado) == null) {
            throw new MiExcepcion("Error no existe empleado con ese usuario");
        }
        gestor.deleteEmpleado(empleado);
    }

    private static void loginEmpleado() throws MiExcepcion {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        String password = EntradaDatos.pedirCadena("Password: ");
        Empleado empleado = new Empleado(userName);
        empleado = gestor.getEmpleado(empleado);
        if (empleado == null) {
            throw new MiExcepcion("Error no existe empleado con ese usuario");
        }
        if (empleado.getPassword().equalsIgnoreCase(password)) {
            Colors.printBlue("you entered correctly!");
        } else {
            Colors.printPurple("wrong password!");
        }

    }

    private static void modifyEmpleado() throws MiExcepcion {
        boolean salirEdit = false;

        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        Empleado empleado = new Empleado(userName);
        empleado = gestor.getEmpleado(empleado);
        if (empleado == null) {
            throw new MiExcepcion("Error no existe empleado con ese usuario");
        }
        while (!salirEdit) {
            switch (menuEdit(empleado)) {
                case 1:
                    String completName = EntradaDatos.pedirCadena("Nuevo Nombre Completo: ");
                    gestor.modifyEmpleado(empleado, "nombrecompleto", completName);
                    break;
                case 2:
                    String telefono = EntradaDatos.pedirCadena("Nuevo Telefono: ");
                    gestor.modifyEmpleado(empleado, "telefono", telefono);
                    break;
                case 3:
                    salirEdit = true;
                    break;
                default:
                    Colors.printRed(ERROR_MESSAGE);
            }

        }
    }

    private static void changePasswordEmpleado() throws MiExcepcion {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario: ");
        Empleado empleado = new Empleado(userName);
        empleado = gestor.getEmpleado(empleado);
        if (empleado == null) {
            throw new MiExcepcion("Error no existe empleado con ese usuario");
        }
        String pass = EntradaDatos.pedirCadena("Nueva Contraseña: ");
        gestor.modifyEmpleado(empleado, "pasword", pass);
    }

    private static int menuEdit(Empleado empleado) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("-- EDIT --");
        System.out.println("1. Nombre: " + Colors.returnBlue(empleado.getNombrecompleto()));
        System.out.println("2. Telefono: " + Colors.returnBlue(empleado.getTelefono()));
        System.out.println("3. Exit");
        System.out.println("----");
        return entrada.nextInt();
    }

}
