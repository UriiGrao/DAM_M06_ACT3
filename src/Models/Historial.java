package Models;

public class Historial implements java.io.Serializable {

    private Integer idevento;
    private Empleado empleado;
    private String tipo;
    private String fechahora;

    public Historial() {
    }

    public Historial(Empleado empleado, String tipo, String fechahora) {
        this.empleado = empleado;
        this.tipo = tipo;
        this.fechahora = fechahora;
    }

    public Integer getIdevento() {
        return this.idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechahora() {
        return this.fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    @Override
    public String toString() {
        return "Historial{" + "idevento=" + idevento + ", empleado=" + empleado + ", tipo=" + tipo + ", fechahora=" + fechahora + '}';
    }

    public String toStringFechaHora() {
        return "La fecha y hora es: " + fechahora;
    }

}
