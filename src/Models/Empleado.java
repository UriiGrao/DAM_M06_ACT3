package Models;

import java.util.HashSet;
import java.util.Set;

public class Empleado implements java.io.Serializable {

    private String nombreusuario;
    private String password;
    private String nombrecompleto;
    private String telefono;
    private Set incidenciasForOrigen = new HashSet(0);
    private Set historials = new HashSet(0);
    private Set incidenciasForDestino = new HashSet(0);

    public Empleado() {
    }

    public Empleado(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public Empleado(String nombreusuario, String password) {
        this.nombreusuario = nombreusuario;
        this.password = password;
    }

    public Empleado(String nombreusuario, String password, String nombrecompleto, String telefono) {
        this.nombreusuario = nombreusuario;
        this.password = password;
        this.nombrecompleto = nombrecompleto;
        this.telefono = telefono;
    }

    public Empleado(String nombreusuario, String password, String nombrecompleto, String telefono, Set incidenciasForOrigen, Set historials, Set incidenciasForDestino) {
        this.nombreusuario = nombreusuario;
        this.password = password;
        this.nombrecompleto = nombrecompleto;
        this.telefono = telefono;
        this.incidenciasForOrigen = incidenciasForOrigen;
        this.historials = historials;
        this.incidenciasForDestino = incidenciasForDestino;
    }

    public String getNombreusuario() {
        return this.nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombrecompleto() {
        return this.nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set getIncidenciasForOrigen() {
        return this.incidenciasForOrigen;
    }

    public void setIncidenciasForOrigen(Set incidenciasForOrigen) {
        this.incidenciasForOrigen = incidenciasForOrigen;
    }

    public Set getHistorials() {
        return this.historials;
    }

    public void setHistorials(Set historials) {
        this.historials = historials;
    }

    public Set getIncidenciasForDestino() {
        return this.incidenciasForDestino;
    }

    public void setIncidenciasForDestino(Set incidenciasForDestino) {
        this.incidenciasForDestino = incidenciasForDestino;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombreusuario=" + nombreusuario + ", password=" + password + ", nombrecompleto=" + nombrecompleto + ", telefono=" + telefono + ", incidenciasForOrigen=" + incidenciasForOrigen + ", historials=" + historials + ", incidenciasForDestino=" + incidenciasForDestino + '}';
    }

}
