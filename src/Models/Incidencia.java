package Models;

public class Incidencia implements java.io.Serializable {

    private String idincidencia;
    private Empleado empleadoByOrigen;
    private Empleado empleadoByDestino;
    private String fechahora;
    private String detalle;
    private String tipo;

    public Incidencia() {
    }

    public Incidencia(String idincidencia) {
        this.idincidencia = idincidencia;
    }

    public Incidencia(Empleado empleadoByOrigen, Empleado empleadoByDestino, String fechahora, String detalle, String tipo) {
        this.empleadoByOrigen = empleadoByOrigen;
        this.empleadoByDestino = empleadoByDestino;
        this.fechahora = fechahora;
        this.detalle = detalle;
        this.tipo = tipo;
    }

    public String getIdincidencia() {
        return this.idincidencia;
    }

    public void setIdincidencia(String idincidencia) {
        this.idincidencia = idincidencia;
    }

    public Empleado getEmpleadoByOrigen() {
        return this.empleadoByOrigen;
    }

    public void setEmpleadoByOrigen(Empleado empleadoByOrigen) {
        this.empleadoByOrigen = empleadoByOrigen;
    }

    public Empleado getEmpleadoByDestino() {
        return this.empleadoByDestino;
    }

    public void setEmpleadoByDestino(Empleado empleadoByDestino) {
        this.empleadoByDestino = empleadoByDestino;
    }

    public String getFechahora() {
        return this.fechahora;
    }

    public void setFechahora(String fechahora) {
        this.fechahora = fechahora;
    }

    public String getDetalle() {
        return this.detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Incidencia{" + "idincidencia=" + idincidencia + ", empleadoByOrigen=" + empleadoByOrigen + ", empleadoByDestino=" + empleadoByDestino + ", fechahora=" + fechahora + ", detalle=" + detalle + ", tipo=" + tipo + '}';
    }

    public String toStringDestino() {
        return "Incidencia{" + "idincidencia=" + idincidencia + ", empleadoByOrigen=" + empleadoByOrigen + ", fechahora=" + fechahora + ", detalle=" + detalle + ", tipo=" + tipo + '}';
    }

}
