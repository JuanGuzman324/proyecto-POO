package resources;

import java.util.*;

// Clase padre Persona
public class Persona {
    protected String id;
    protected String nombre;
    protected String email;
    protected String telefono;

    public Persona(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public void mostrarInformacion() {
        System.out.println("=== INFORMACIÓN PERSONAL ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + telefono);
    }

    public String getTipoPersona() {
        return "Persona";
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}

