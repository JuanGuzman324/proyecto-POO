package resources;

// Clase padre Material
class Material {
    protected String id;
    protected String titulo;
    protected String autor;
    protected boolean disponible;

    public Material(String id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public boolean prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("Material prestado: " + titulo);
            return true;
        } else {
            System.out.println("El material " + titulo + " no está disponible");
            return false;
        }
    }

    public void devolver() {
        disponible = true;
        System.out.println("Material devuelto: " + titulo);
    }

    public String obtenerInformacion() {
        return "Material: " + titulo + " | Autor: " + autor + " | ID: " + id +
                " | Disponible: " + (disponible ? "Sí" : "No");
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponible() { return disponible; }
}

// Clase Libro hereda de Material
class Libro extends Material {
    private String isbn;
    private String editorial;

    public Libro(String id, String titulo, String autor, String isbn, String editorial) {
        super(id, titulo, autor);
        this.isbn = isbn;
        this.editorial = editorial;
    }

    @Override
    public boolean prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("Libro prestado: " + titulo + " (ISBN: " + isbn + ")");
            return true;
        } else {
            System.out.println("El libro " + titulo + " no está disponible");
            return false;
        }
    }

    @Override
    public String obtenerInformacion() {
        return "Libro: " + titulo + " | Autor: " + autor + " | ISBN: " + isbn +
                " | Editorial: " + editorial + " | Disponible: " + (disponible ? "Sí" : "No");
    }

    public String getIsbn() { return isbn; }
    public String getEditorial() { return editorial; }
}

// Clase Revista hereda de Material
class Revista extends Material {
    private int numeroEdicion;
    private String fechaPublicacion;

    public Revista(String id, String titulo, String autor, int numeroEdicion, String fechaPublicacion) {
        super(id, titulo, autor);
        this.numeroEdicion = numeroEdicion;
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public boolean prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("Revista prestada: " + titulo + " - Edición " + numeroEdicion);
            return true;
        } else {
            System.out.println("La revista " + titulo + " no está disponible");
            return false;
        }
    }

    @Override
    public String obtenerInformacion() {
        return "Revista: " + titulo + " | Editor: " + autor + " | Edición: " + numeroEdicion +
                " | Fecha: " + fechaPublicacion + " | Disponible: " + (disponible ? "Sí" : "No");
    }

    public int getNumeroEdicion() { return numeroEdicion; }
    public String getFechaPublicacion() { return fechaPublicacion; }
}