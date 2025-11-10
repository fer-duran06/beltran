import java.util.ArrayList;
import java.util.Scanner;

class Tarea {
    String descripcion;
    boolean completada;
    int prioridad; // 1 = Alta, 2 = Media, 3 = Baja

    public Tarea(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.completada = false;
    }

    @Override
    public String toString() {
        return descripcion + " | Prioridad: " + prioridad + " | Estado: " + (completada ? "✔ Completada" : "❌ Pendiente");
    }
}

public class ListaTareas {
    private ArrayList<Tarea> tareas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;
        do {
            System.out.println("\n=== LISTA DE TAREAS PENDIENTES ===");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Completar tarea");
            System.out.println("3. Mostrar todas las tareas");
            System.out.println("4. Buscar tarea por descripción");
            System.out.println("5. Mostrar tareas de alta prioridad");
            System.out.println("6. Mostrar tareas completadas");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarTarea();
                    break;
                case 2:
                    completarTarea();
                    break;
                case 3:
                    mostrarTareas();
                    break;
                case 4:
                    buscarTarea();
                    break;
                case 5:
                    mostrarAltaPrioridad();
                    break;
                case 6:
                    mostrarCompletadas();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregarTarea() {
        System.out.print("Descripción: ");
        String desc = sc.nextLine();
        System.out.print("Prioridad (1=Alta, 2=Media, 3=Baja): ");
        int prioridad = sc.nextInt();
        sc.nextLine();
        tareas.add(new Tarea(desc, prioridad));
        System.out.println("✅ Tarea agregada correctamente.");
    }

    private void completarTarea() {
        mostrarTareas();
        System.out.print("Número de tarea a completar: ");
        int num = sc.nextInt();
        sc.nextLine();
        if (num > 0 && num <= tareas.size()) {
            tareas.get(num - 1).completada = true;
            System.out.println("✅ Tarea marcada como completada.");
        } else {
            System.out.println("Número inválido.");
        }
    }

    private void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        System.out.println("\n--- LISTA DE TAREAS ---");
        int i = 1;
        for (Tarea t : tareas) {
            System.out.println(i++ + ". " + t);
        }
    }

    private void buscarTarea() {
        System.out.print("Buscar descripción: ");
        String texto = sc.nextLine().toLowerCase();
        boolean encontrado = false;
        for (Tarea t : tareas) {
            if (t.descripcion.toLowerCase().contains(texto)) {
                System.out.println("🔎 " + t);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No se encontraron coincidencias.");
    }

    private void mostrarAltaPrioridad() {
        System.out.println("\n--- TAREAS DE ALTA PRIORIDAD ---");
        boolean hay = false;
        for (Tarea t : tareas) {
            if (t.prioridad == 1) {
                System.out.println(t);
                hay = true;
            }
        }
        if (!hay) System.out.println("No hay tareas de alta prioridad.");
    }

    private void mostrarCompletadas() {
        System.out.println("\n--- TAREAS COMPLETADAS ---");
        boolean hay = false;
        for (Tarea t : tareas) {
            if (t.completada) {
                System.out.println(t);
                hay = true;
            }
        }
        if (!hay) System.out.println("Aún no has completado ninguna tarea.");
    }
}
