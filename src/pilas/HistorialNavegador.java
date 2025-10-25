package pilas;

public class HistorialNavegador {
    private Pila<String> atras;
    private Pila<String> adelante;
    private String actual;

    public HistorialNavegador() {
        atras = new Pila<>();
        adelante = new Pila<>();
    }

    public void visitar(String url) {
        if (actual != null)
            atras.push(actual);
        actual = url;
        adelante.limpiar(); // Si visitas una nueva URL, limpia adelante
        System.out.println("Visitando: " + actual);
    }

    public void retroceder() {
        if (!atras.estaVacia()) {
            adelante.push(actual);
            actual = atras.pop();
            System.out.println("Retrocediendo a: " + actual);
        } else {
            System.out.println("No hay páginas anteriores.");
        }
    }

    public void avanzar() {
        if (!adelante.estaVacia()) {
            atras.push(actual);
            actual = adelante.pop();
            System.out.println("Avanzando a: " + actual);
        } else {
            System.out.println("No hay páginas siguientes.");
        }
    }

    public void mostrarEstado() {
        System.out.println("Actual: " + actual);
        System.out.println("Atrás: " + atras);
        System.out.println("Adelante: " + adelante);
    }
}

