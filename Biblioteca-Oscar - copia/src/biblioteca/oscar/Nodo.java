package biblioteca.oscar;

public class Nodo {
    private Niño niño;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo(Niño niño) {
        this.niño = niño;
    }

    public Niño getNiño() {
        return niño;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo anterior) {
        this.anterior = anterior;
    }
}
