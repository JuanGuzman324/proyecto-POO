import java.util.*;
import java.text.SimpleDateFormat;

class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private boolean disponible;

    public Libro(String isbn, String titulo, String autor, String editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.disponible = true;
    }

    public boolean prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("Libro prestado: " + titulo);
            return true;
        } else {
            System.out.println("El libro " + titulo + " no está disponible");
            return false;
        }
    }

    public void devolver() {
        disponible = true;
        System.out.println("Libro devuelto: " + titulo);
    }

    public String obtenerInformacion() {
        return "Libro: " + titulo + " | Autor: " + autor + " | ISBN: " + isbn +
                " | Disponible: " + (disponible ? "Sí" : "No");
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponible() { return disponible; }
}

class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private List<String> librosPrestados; // Lista de ISBNs

    public Usuario(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.librosPrestados = new ArrayList<>();
    }

    public boolean solicitarPrestamo(Libro libro) {
        if (libro.prestar()) {
            librosPrestados.add(libro.getIsbn());
            System.out.println("Usuario " + nombre + " tiene " + librosPrestados.size() + " libro(s) prestado(s)");
            return true;
        }
        return false;
    }

    public void devolverLibro(Libro libro) {
        if (librosPrestados.remove(libro.getIsbn())) {
            libro.devolver();
            System.out.println("Usuario " + nombre + " devolvió: " + libro.getTitulo());
        } else {
            System.out.println("El usuario no tiene prestado este libro");
        }
    }

    public void consultarHistorial() {
        System.out.println("=== HISTORIAL DE " + nombre.toUpperCase() + " ===");
        System.out.println("ID: " + id);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Libros prestados actualmente: " + librosPrestados.size());
        if (!librosPrestados.isEmpty()) {
            System.out.println("ISBNs de libros prestados: " + librosPrestados);
        }
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public List<String> getLibrosPrestados() { return librosPrestados; }
}

class Prestamo {
    private String idPrestamo;
    private Libro libro;
    private Usuario usuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;

    // Constructor
    public Prestamo(String idPrestamo, Libro libro, Usuario usuario) {
        this.idPrestamo = idPrestamo;
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = new Date();

        // Calcular fecha de devolución (14 días después)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 14);
        this.fechaDevolucion = cal.getTime();

        this.estado = "ACTIVO";
    }

    public double calcularMulta() {
        Date hoy = new Date();
        if (hoy.after(fechaDevolucion) && estado.equals("ACTIVO")) {
            long diasRetraso = (hoy.getTime() - fechaDevolucion.getTime()) / (1000 * 60 * 60 * 24);
            double multa = diasRetraso * 1000; // $1000 por día
            System.out.println("Multa calculada: $" + multa + " por " + diasRetraso + " días de retraso");
            return multa;
        }
        return 0.0;
    }

    public boolean extenderPlazo(int dias) {
        if (estado.equals("ACTIVO")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaDevolucion);
            cal.add(Calendar.DAY_OF_MONTH, dias);
            fechaDevolucion = cal.getTime();
            System.out.println("Plazo extendido " + dias + " días");
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
        System.out.println("ID Préstamo: " + idPrestamo);
        System.out.println("Libro: " + libro.getTitulo());
        System.out.println("Usuario: " + usuario.getNombre());
        System.out.println("Fecha préstamo: " + sdf.format(fechaPrestamo));
        System.out.println("Fecha devolución: " + sdf.format(fechaDevolucion));
        System.out.println("Estado: " + estado);
    }

    public String getEstado() { return estado; }
    public Date getFechaDevolucion() { return fechaDevolucion; }
}

class BibliotecaSimple {
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("    SISTEMA DE BIBLIOTECA");
        System.out.println("=".repeat(50));

        System.out.println("\n1. CREANDO OBJETOS...");

        Libro libro1 = new Libro("978-84-376-0494-7",
                "Cien años de soledad",
                "Gabriel García Márquez",
                "Editorial Sudamericana");

        Libro libro2 = new Libro("978-84-376-0495-8",
                "El amor en los tiempos del cólera",
                "Gabriel García Márquez",
                "Editorial Sudamericana");

        Usuario usuario1 = new Usuario("U001", "María González", "maria@email.com", "300-123-4567");
        Usuario usuario2 = new Usuario("U002", "Carlos Ruiz", "carlos@email.com", "300-765-4321");

        System.out.println("✓ 2 Libros creados");
        System.out.println("✓ 2 Usuarios creados");

        System.out.println("\n2. ESTADO INICIAL:");
        System.out.println(libro1.obtenerInformacion());
        System.out.println(libro2.obtenerInformacion());

        System.out.println("\n" + "=".repeat(50));
        System.out.println("3. OPERACIONES DEL SISTEMA");
        System.out.println("=".repeat(50));

        System.out.println("\n--- CREAR PRÉSTAMO ---");
        usuario1.solicitarPrestamo(libro1);
        Prestamo prestamo1 = new Prestamo("P001", libro1, usuario1);
        prestamo1.mostrarInformacion();

        System.out.println("\n--- SEGUNDO USUARIO INTENTA MISMO LIBRO ---");
        usuario2.solicitarPrestamo(libro1);

        System.out.println("\n--- SEGUNDO USUARIO SOLICITA OTRO LIBRO ---");
        usuario2.solicitarPrestamo(libro2);
        Prestamo prestamo2 = new Prestamo("P002", libro2, usuario2);

        System.out.println("\n--- CONSULTAR HISTORIAL ---");
        usuario1.consultarHistorial();
        System.out.println();
        usuario2.consultarHistorial();

        System.out.println("\n--- EXTENDER PLAZO ---");
        prestamo1.extenderPlazo(7);

        System.out.println("\n--- CALCULAR MULTA ---");
        prestamo1.calcularMulta();

        System.out.println("\n--- DEVOLVER LIBRO ---");
        usuario1.devolverLibro(libro1);
        prestamo1.registrarDevolucion();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("4. ESTADO FINAL");
        System.out.println("=".repeat(50));

        System.out.println("\nLibros:");
        System.out.println("• " + libro1.getTitulo() + " - " + (libro1.isDisponible() ? "Disponible" : "Prestado"));
        System.out.println("• " + libro2.getTitulo() + " - " + (libro2.isDisponible() ? "Disponible" : "Prestado"));

        System.out.println("\nUsuarios:");
        System.out.println("• " + usuario1.getNombre() + ": " + usuario1.getLibrosPrestados().size() + " libro(s)");
        System.out.println("• " + usuario2.getNombre() + ": " + usuario2.getLibrosPrestados().size() + " libro(s)");

        System.out.println("\n¡SISTEMA EJECUTADO EXITOSAMENTE!");
    }
}