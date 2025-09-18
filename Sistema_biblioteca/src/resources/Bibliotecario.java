package resources;

import java.util.ArrayList;
import java.util.List;

// Clase Bibliotecario hereda de Persona
public class Bibliotecario extends Persona {
    private String turno;
    private List<String> tareas;

    public Bibliotecario(String id, String nombre, String email, String telefono, String turno) {
        super(id, nombre, email, telefono);
        this.turno = turno;
        this.tareas = new ArrayList<>();
    }

    public void registrarPrestamo(Usuario usuario, Material material) {
        if (usuario.solicitarPrestamo(material)) {
            String tarea = "Préstamo: " + material.getTitulo() + " a " + usuario.getNombre();
            tareas.add(tarea);
            System.out.println("Bibliotecario " + nombre + " registró el préstamo");
        }
    }

    public void registrarDevolucion(Usuario usuario, Material material) {
        usuario.devolverMaterial(material);
        String tarea = "Devolución: " + material.getTitulo() + " de " + usuario.getNombre();
        tareas.add(tarea);
        System.out.println("Bibliotecario " + nombre + " registró la devolución");
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: " + getTipoPersona());
        System.out.println("Turno: " + turno);
        System.out.println("Tareas realizadas: " + tareas.size());
    }

    @Override
    public String getTipoPersona() {
        return "Bibliotecario";
    }

    public void mostrarTareas() {
        System.out.println("=== TAREAS REALIZADAS ===");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }
    }

    public String getTurno() { return turno; }
}
