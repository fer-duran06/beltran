import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ GENERAL ===");
            System.out.println("1. Lista de Tareas Pendientes");
            System.out.println("2. Playlist Circular");
            System.out.println("3. Historial del Navegador");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    new ListaTareas().menu();
                    break;
                case 2:
                    new PlaylistCircular().menu();
                    break;

                case 3:
                    HistorialNavegador.menu();
                    break;
                case 0:
                    System.out.println("👋 Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}
