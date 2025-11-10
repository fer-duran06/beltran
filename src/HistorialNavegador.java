import java.util.Scanner;
import java.util.Stack;

public class HistorialNavegador {
    private static Stack<String> atras = new Stack<>();
    private static Stack<String> adelante = new Stack<>();
    private static String paginaActual = null;

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== HISTORIAL DE NAVEGADOR ===");
            System.out.println("1. Visitar página");
            System.out.println("2. Atrás");
            System.out.println("3. Adelante");
            System.out.println("4. Mostrar historial completo");
            System.out.println("5. Página actual");
            System.out.println("0. Volver al menú principal");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese URL: ");
                    String nuevaPagina = sc.nextLine();
                    if (paginaActual != null)
                        atras.push(paginaActual);
                    paginaActual = nuevaPagina;
                    adelante.clear(); // si visitas una nueva, se borra el futuro
                    System.out.println("🌐 Visitando: " + paginaActual);
                    break;

                case 2:
                    if (!atras.isEmpty()) {
                        adelante.push(paginaActual);
                        paginaActual = atras.pop();
                        System.out.println("⬅️ Retrocediendo a: " + paginaActual);
                    } else {
                        System.out.println("⚠️ No hay páginas anteriores.");
                    }
                    break;

                case 3:
                    if (!adelante.isEmpty()) {
                        atras.push(paginaActual);
                        paginaActual = adelante.pop();
                        System.out.println("➡️ Avanzando a: " + paginaActual);
                    } else {
                        System.out.println("⚠️ No hay páginas siguientes.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- HISTORIAL ---");
                    System.out.println("Atrás: " + atras);
                    System.out.println("Actual: " + paginaActual);
                    System.out.println("Adelante: " + adelante);
                    break;

                case 5:
                    System.out.println("📍 Página actual: " + (paginaActual != null ? paginaActual : "Ninguna"));
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
