package pilas;

public class EditorTexto {
    private StringBuilder contenido;
    private Pila<String> deshacer;
    private Pila<String> rehacer;

    public EditorTexto() {
        contenido = new StringBuilder();
        deshacer = new Pila<>();
        rehacer = new Pila<>();
    }

    public void escribir(String texto) {
        deshacer.push(contenido.toString());
        contenido.append(texto);
        rehacer.limpiar(); // Si se hace una acción nueva, limpiar pila de rehacer
    }

    public void borrar(int n) {
        if (n > 0 && n <= contenido.length()) {
            deshacer.push(contenido.toString());
            contenido.delete(contenido.length() - n, contenido.length());
            rehacer.limpiar();
        }
    }

    public void deshacer() {
        if (!deshacer.estaVacia()) {
            rehacer.push(contenido.toString());
            contenido = new StringBuilder(deshacer.pop());
        }
    }

    public void rehacer() {
        if (!rehacer.estaVacia()) {
            deshacer.push(contenido.toString());
            contenido = new StringBuilder(rehacer.pop());
        }
    }

    public String getContenido() {
        return contenido.toString();
    }
}

