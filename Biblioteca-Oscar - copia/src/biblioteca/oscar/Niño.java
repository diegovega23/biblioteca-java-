package biblioteca.oscar;

public class Niño {
    private String nombre;
    private String apellido;
    private boolean haLeido;

    public Niño(String nombre, String apellido, boolean haLeido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.haLeido = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

     public boolean isHaLeido() {
        return haLeido;
    }

    public void setHaLeido(boolean haLeido) {
        this.haLeido = haLeido;
    }
    
    @Override
    public String toString() {
        return nombre + " " + apellido;
    }
}
