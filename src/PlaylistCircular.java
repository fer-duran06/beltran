import java.util.LinkedList;
import java.util.Scanner;

public class PlaylistCircular {
    private static LinkedList<Cancion> canciones = new LinkedList<>();
    private static int indiceActual = 0;

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== PLAYLIST CIRCULAR ===");
            System.out.println("1. Agregar canción");
            System.out.println("2. Siguiente canción");
            System.out.println("3. Anterior canción");
            System.out.println("4. Mostrar canción actual");
            System.out.println("5. Mostrar todas");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Artista: ");
                    String artista = sc.nextLine();
                    canciones.add(new Cancion(titulo, artista));
                    break;
                case 2:
                    if (!canciones.isEmpty()) {
                        indiceActual = (indiceActual + 1) % canciones.size();
                        System.out.println("▶️ " + canciones.get(indiceActual));
                    }
                    break;
                case 3:
                    if (!canciones.isEmpty()) {
                        indiceActual = (indiceActual - 1 + canciones.size()) % canciones.size();
                        System.out.println("◀️ " + canciones.get(indiceActual));
                    }
                    break;
                case 4:
                    if (!canciones.isEmpty())
                        System.out.println("🎵 Actual: " + canciones.get(indiceActual));
                    else
                        System.out.println("Playlist vacía.");
                    break;
                case 5:
                    canciones.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}

class Cancion {
    private String titulo;
    private String artista;

    public Cancion(String titulo, String artista) {
        this.titulo = titulo;
        this.artista = artista;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista;
    }
}
