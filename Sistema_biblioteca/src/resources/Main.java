package resources;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("SISTEMA BIBLIOTECA");
        System.out.println("=".repeat(50));

        System.out.println("CREANDO OBJETOS...");

        Material libro1 = new Libro("L001", "Cien años de soledad",
                "Gabriel García Márquez",
                "978-84-376-0494-7", "Sudamericana");

        Material revista1 = new Revista("R001", "National Geographic",
                "Nat Geo", 145, "Enero 2024");

        Material libro2 = new Libro("L002", "El amor en los tiempos del cólera",
                "Gabriel García Márquez",
                "978-84-376-0495-8", "Sudamericana");

        Usuario usuario1 = new Usuario("U001", "María González",
                "maria@email.com", "300-123-4567");
        Usuario usuario2 = new Usuario("U002", "Carlos Ruiz",
                "carlos@email.com", "300-765-4321");

        Bibliotecario biblio = new Bibliotecario("B001", "Ana López",
                "ana@biblio.com", "300-111-2222", "Mañana");

        System.out.println("Materiales creados");
        System.out.println("Personas creadas");

        Material[] inventario = {libro1, revista1, libro2};

        System.out.println("\n--- Información de inventario ---");
        for (Material material : inventario) {
            System.out.println(material.obtenerInformacion());
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("OPERACIONES DEL SISTEMA");
        System.out.println("=".repeat(50));

        System.out.println("--- INFORMACIÓN DE PERSONAS ---");
        usuario1.mostrarInformacion();
        System.out.println();
        biblio.mostrarInformacion();

        System.out.println("--- PRÉSTAMOS ---");
        biblio.registrarPrestamo(usuario1, libro1);
        biblio.registrarPrestamo(usuario2, revista1);
        biblio.registrarPrestamo(usuario1, libro2);

        System.out.println("--- CREAR PRÉSTAMOS ---");
        Prestamo prestamo1 = new Prestamo("P001", libro1, usuario1);
        Prestamo prestamo2 = new Prestamo("P002", revista1, usuario2);

        System.out.println("--- INFORMACIÓN DE PRÉSTAMOS ---");
        prestamo1.mostrarInformacion();
        System.out.println();
        prestamo2.mostrarInformacion();

        System.out.println("--- OPERACIONES AVANZADAS ---");
        prestamo1.extenderPlazo(5);
        prestamo2.calcularMulta();

        System.out.println("--- DEVOLUCIONES ---");
        biblio.registrarDevolucion(usuario1, libro1);
        prestamo1.registrarDevolucion();

        System.out.println("--- TAREAS DEL BIBLIOTECARIO ---");
        biblio.mostrarTareas();


        System.out.println("ESTADO FINAL:");
        for (Material material : inventario) {
            String tipo = material.getClass().getSimpleName();
            System.out.println("• " + tipo + ": " + material.getTitulo() +
                    " - " + (material.isDisponible() ? "Disponible" : "Prestado"));
        }
    }
}