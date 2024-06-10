package biblioteca.oscar;

import java.io.*;
import biblioteca.oscar.Niño;
import biblioteca.oscar.Nodo;

public class Lista {
    
public Nodo cabeza;
    private Nodo cola;
    private int tamaño = 0;

    public void addNiño(Niño niño) {
        Nodo nuevo = new Nodo(niño);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            cabeza.setSiguiente(cola);
            cabeza.setAnterior(cola);
            cola.setSiguiente(cabeza);
            cola.setAnterior(cabeza);
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cola = nuevo;
        }
        tamaño++;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int obtenerTamaño() {
        return tamaño;
    }
}
