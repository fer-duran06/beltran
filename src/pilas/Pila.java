package pilas;

import java.util.LinkedList;
import java.util.EmptyStackException;

public class Pila<T> {
    private LinkedList<T> elementos;

    public Pila() {
        elementos = new LinkedList<>();
    }

    public void push(T dato) {
        elementos.push(dato);
    }

    public T pop() {
        if (estaVacia())
            throw new EmptyStackException();
        return elementos.pop();
    }

    public T peek() {
        if (estaVacia())
            throw new EmptyStackException();
        return elementos.peek();
    }

    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    public void limpiar() {
        elementos.clear();
    }

    @Override
    public String toString() {
        return elementos.toString();
    }
}