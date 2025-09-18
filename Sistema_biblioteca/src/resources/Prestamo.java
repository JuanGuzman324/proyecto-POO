package resources;
import java.util.*;
import java.text.SimpleDateFormat;

public class Prestamo {
    private final String idPrestamo;
    private final Material material;
    private final Usuario usuario;
    private final Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;
    private double multaAcumulada;

    public Prestamo(String idPrestamo, Material material, Usuario usuario) {
        this.idPrestamo = idPrestamo;
        this.material = material;
        this.usuario = usuario;
        this.fechaPrestamo = new Date();
        this.estado = "ACTIVO";
        this.multaAcumulada = 0.0;

        // Fecha de devolución según tipo de material (polimorfismo)
        Calendar cal = Calendar.getInstance();
        if (material instanceof Libro) {
            cal.add(Calendar.DAY_OF_MONTH, 14); // 14 días para libros
        } else if (material instanceof Revista) {
            cal.add(Calendar.DAY_OF_MONTH, 7);  // 7 días para revistas
        } else {
            cal.add(Calendar.DAY_OF_MONTH, 10); // 10 días por defecto
        }
        this.fechaDevolucion = cal.getTime();
    }

    public double calcularMulta() {
        Date hoy = new Date();
        if (hoy.after(fechaDevolucion) && estado.equals("ACTIVO")) {
            long diasRetraso = (hoy.getTime() - fechaDevolucion.getTime()) / (1000 * 60 * 60 * 24);

            // Multa diferente según tipo (polimorfismo)
            double multaDiaria;
            if (material instanceof Libro) {
                multaDiaria = 1000; // $1000 por día para libros
            } else if (material instanceof Revista) {
                multaDiaria = 500;  // $500 por día para revistas
            } else {
                multaDiaria = 750;  // $750 por defecto
            }

            multaAcumulada = diasRetraso * multaDiaria;
            System.out.println("Multa calculada: $" + multaAcumulada + " (" + diasRetraso + " días de retraso)");
            return multaAcumulada;
        }
        return 0.0;
    }

    public boolean extenderPlazo(int dias) {
        if (estado.equals("ACTIVO")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaDevolucion);
            cal.add(Calendar.DAY_OF_MONTH, dias);
            fechaDevolucion = cal.getTime();
            System.out.println("Plazo extendido " + dias + " días para: " + material.getTitulo());
            return true;
        }
        return false;
    }

    public void registrarDevolucion() {
        estado = "DEVUELTO";
        System.out.println("Préstamo " + idPrestamo + " registrado como devuelto");
    }

    public void mostrarInformacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("=== INFORMACIÓN DEL PRÉSTAMO ===");
        System.out.println("ID: " + idPrestamo);
        System.out.println("Material: " + material.getTitulo() + " (" + material.getClass().getSimpleName() + ")");
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Fecha préstamo: " + sdf.format(fechaPrestamo));
        System.out.println("Fecha devolución: " + sdf.format(fechaDevolucion));
        System.out.println("Estado: " + estado);
        if (multaAcumulada > 0) {
            System.out.println("Multa: $" + multaAcumulada);
        }
    }

    // Getters
    public String getIdPrestamo() { return idPrestamo; }
    public Material getMaterial() { return material; }
    public Usuario getUsuario() { return usuario; }
    public String getEstado() { return estado; }
    public Date getFechaDevolucion() { return fechaDevolucion; }
    public double getMultaAcumulada() { return multaAcumulada; }
}
