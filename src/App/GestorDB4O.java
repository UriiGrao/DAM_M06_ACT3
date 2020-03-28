/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

import Models.Empleado;
import Models.Historial;
import Models.Incidencia;
import Utils.Colors;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;

/**
 *
 * @author uriishii
 */
public class GestorDB4O {

    private ObjectContainer db;

    public static List<Empleado> empleados;
    public static List<Historial> historiales;
    public static List<Incidencia> incidencias;

    public GestorDB4O() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "bdPR3");
    }

    public void cerrar() {
        db.close();
    }

    /* EMPLEADOS */
    public void addEmpleado(Empleado e) {
        db.store(e);
        Colors.printBlue("Empleado generado");
    }

    public void getAllEmpleados() {
        empleados = db.query(Empleado.class);
    }

    public Empleado getEmpleado(Empleado empleado) {
        ObjectSet result = db.queryByExample(empleado);
        if (result.hasNext()) {
            return (Empleado) result.next();
        }
        return null;
    }

    public void deleteEmpleado(Empleado empleado) {
        ObjectSet result = db.queryByExample(empleado);
        while (result.hasNext()) {
            Empleado found = (Empleado) result.next();
            db.delete(found);
            Colors.printBlue("Empleado borrado " + found);
        }
    }

    public void modifyEmpleado(Empleado empleado, String tipoCambio, String cambio) {
        ObjectSet result = db.queryByExample(empleado);
        if (!result.hasNext()) {
            return;
        }
        empleado = (Empleado) result.next();
        switch (tipoCambio) {
            case "nombrecompleto":
                empleado.setNombrecompleto(cambio);
                break;
            case "telefono":
                empleado.setTelefono(cambio);
                break;
            case "password":
                empleado.setPassword(cambio);
        }
        db.store(empleado);
        Colors.printBlue("Modificado correctamente! ");
    }

    /* INCIDENCIAS */
    public Incidencia getIncidencia(Incidencia incidencia) {
        ObjectSet result = db.queryByExample(incidencia);
        if (result.hasNext()) {
            return (Incidencia) result.next();
        }
        return null;
    }

    public void getAllIncidencias() {
        ObjectSet result = db.queryByExample(Incidencia.class);
        System.out.println(result.size());
        incidencias = db.query(Incidencia.class);
    }

    public void addIncidencia(Incidencia i) {
        db.store(i);
        Colors.printBlue("Incidencia generada");
    }

    /* HISTORIALES */
    public void getAllHistoriales() {
        ObjectSet result = db.queryByExample(Historial.class);
        System.out.println(result.size());
        historiales = db.query(Historial.class);
    }

    public void addHistorial(Historial h) {
        db.store(h);
        Colors.printBlue("Historial generado");
    }
}
