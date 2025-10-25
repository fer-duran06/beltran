package pilas;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMO HISTORIAL ===");
        HistorialNavegador nav = new HistorialNavegador();
        nav.visitar("google.com");
        nav.visitar("youtube.com");
        nav.visitar("wikipedia.org");
        nav.retroceder();
        nav.avanzar();
        nav.mostrarEstado();

        System.out.println("\n=== DEMO EDITOR ===");
        EditorTexto editor = new EditorTexto();
        editor.escribir("Hola ");
        editor.escribir("mundo");
        System.out.println("Contenido: " + editor.getContenido());

        editor.deshacer();
        System.out.println("Después de deshacer: " + editor.getContenido());

        editor.rehacer();
        System.out.println("Después de rehacer: " + editor.getContenido());
    }
}
