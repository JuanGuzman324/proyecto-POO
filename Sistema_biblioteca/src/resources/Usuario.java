package resources;

import java.util.ArrayList;
import java.util.List;

// Clase Usuario hereda de Persona
public class Usuario extends Persona {
    private final List<String> materialesPrestados;
    private final int limiteLibros;

    public Usuario(String id, String nombre, String email, String telefono) {
        super(id, nombre, email, telefono);
        this.materialesPrestados = new ArrayList<>();
        this.limiteLibros = 3;
    }

    public boolean solicitarPrestamo(Material material) {
        if (materialesPrestados.size() >= limiteLibros) {
            System.out.println("El usuario " + nombre + " ha alcanzado el límite de préstamos (" + limiteLibros + ")");
            return false;
        }

        if (material.prestar()) {
            materialesPrestados.add(material.getId());
            System.out.println("Usuario " + nombre + " tiene " + materialesPrestados.size() + " material(es) prestado(s)");
            return true;
        }
        return false;
    }

    public void devolverMaterial(Material material) {
        if (materialesPrestados.remove(material.getId())) {
            material.devolver();
            System.out.println("Usuario " + nombre + " devolvió: " + material.getTitulo());
        } else {
            System.out.println("El usuario no tiene prestado este material");
        }
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: " + getTipoPersona());
        System.out.println("Materiales prestados: " + materialesPrestados.size() + "/" + limiteLibros);
    }

    @Override
    public String getTipoPersona() {
        return "Usuario de Biblioteca";
    }

    public List<String> getMaterialesPrestados() { return materialesPrestados; }
    public int getLimiteLibros() { return limiteLibros; }
}
