/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import static App.GestorDB4O.empleados;
import static App.GestorDB4O.incidencias;
import Models.Empleado;
import Models.Incidencia;
import Utils.Colors;
import Utils.EntradaDatos;
import Utils.MiExcepcion;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author uriishii
 */
public class IncidenciasBDOR {

    private static final String ERROR_MESSAGE = "Error Option try again.";
    private static final GestorDB4O gestor = new GestorDB4O();

    public static void main(String[] args) {
        gestor.getAllEmpleados();
        if (0 == empleados.size()) {
            generateTestEmpleados();
        }
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

    private static void generateTestEmpleados() {
        Empleado empleado = new Empleado("admin", "1234");
        Empleado empleado2 = new Empleado("test", "1234");
        gestor.addEmpleado(empleado);
        gestor.addEmpleado(empleado2);
        Colors.printBlue("Se te han genearo dos usuarios de test con username: admin, test"
                + "la pass para los dos es 1234");
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
                        getAllIncidencias();
                        break;
                    case "c":
                        createIncidencia();
                        break;
                    case "d":
                        table = "destino";
                        showAllIncidenciasFrom(table);
                        break;
                    case "e":
                        table = "origen";
                        showAllIncidenciasFrom(table);
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
        String idInci = EntradaDatos.pedirCadena("Numero de incidencia: ");
        Incidencia incidencia = new Incidencia(idInci);
        incidencia = gestor.getIncidencia(incidencia);
        if (incidencia == null) {
            throw new MiExcepcion("Error no existe Incidencia con ese id");
        }
        System.out.println(incidencia.toString());
    }

    private static void getAllIncidencias() throws MiExcepcion {
        gestor.getAllIncidencias();
        if (0 == incidencias.size()) {
            throw new MiExcepcion("No Incidencias a mostrar inserte una antes");
        }
        incidencias.forEach((incidencia) -> {
            System.out.println(incidencia.toString());
        });

    }

    private static void createIncidencia() throws MiExcepcion {
        String idIncidencia = EntradaDatos.pedirCadena("Id de la incidencia: ");
        String fechahora = EntradaDatos.pedirCadena("Fecha de la incidencias: YY/MM/DD ");
        String detalle = EntradaDatos.pedirCadena("Detalles de la incidencia: ");
        String userOrigen = EntradaDatos.pedirCadena("Nombre Usuario del origen: ");
        String userDestino = EntradaDatos.pedirCadena("Nombre Usuario del destino: ");
        ArrayList<String> wordsAccepted = new ArrayList<>();
        wordsAccepted.add("Urgente");
        wordsAccepted.add("Normal");
        String tipo = EntradaDatos.askString("Tipo de incidencia: ", wordsAccepted);
        Empleado empleadoOrigen = new Empleado(userOrigen);
        Empleado empleadoDestino = new Empleado(userDestino);
        Incidencia incidencia = new Incidencia(idIncidencia);
        if (gestor.getIncidencia(incidencia) != null) {
            throw new MiExcepcion("Error el id incidencia ya existente");
        }

        if (gestor.getEmpleado(empleadoOrigen) == null || gestor.getEmpleado(empleadoDestino) == null) {
            throw new MiExcepcion("ERROR: user Origen or user Destino no exist");
        }
        empleadoOrigen = gestor.getEmpleado(empleadoOrigen);
        empleadoDestino = gestor.getEmpleado(empleadoDestino);
        incidencia.setDetalle(detalle);
        incidencia.setEmpleadoByDestino(empleadoDestino);
        incidencia.setEmpleadoByOrigen(empleadoOrigen);
        incidencia.setFechahora(fechahora);
        incidencia.setTipo(tipo);
        gestor.addIncidencia(incidencia);
    }

    private static void showAllIncidenciasFrom(String table) throws MiExcepcion {
        String userName = EntradaDatos.pedirCadena("Nombre Usuario del buscado: ");
        Empleado empleado = new Empleado(userName);
        if (gestor.getEmpleado(empleado) == null) {
            throw new MiExcepcion("No hay empleado con ese nombre de usuario");
        }
        Incidencia incidencia = new Incidencia();

        if (table.equalsIgnoreCase("destino")) {
            incidencia.setEmpleadoByDestino(empleado);
        } else {
            incidencia.setEmpleadoByOrigen(empleado);
        }
        gestor.getAllIncidencias();
        incidencias.forEach((i) -> {
            System.out.println(i.toString());
        });

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

    private static Exception MiExcepcion(String no_Incidencias_a_mostrar_inserte_una_ante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
