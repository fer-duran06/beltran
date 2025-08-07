package com.vetfinder;

import io.javalin.Javalin;
import com.vetfinder.di.AppModule;
import com.vetfinder.config.DatabaseConfig;

import java.util.Map;

/**
 * Main.java - Aplicación principal VetFinder
 * Incluye todos los endpoints existentes + catálogo de razas
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== VetFinder API - Iniciando servidor ===");

            // Inicializar base de datos
            System.out.println("Inicializando base de datos...");
            DatabaseConfig.initialize();
            System.out.println("✅ Base de datos inicializada");

            // Obtener puerto del entorno o usar 7000 por defecto
            int port = Integer.parseInt(System.getenv().getOrDefault("SERVER_PORT", "7000"));

            // Crear aplicación Javalin
            Javalin app = Javalin.create(config -> {
                config.plugins.enableCors(cors -> {
                    cors.add(it -> {
                        it.anyHost();
                        it.allowCredentials = true;
                    });
                });
            });

            // Shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Cerrando aplicación...");
                DatabaseConfig.closeDataSource();
                app.stop();
            }));

            System.out.println("=== Registrando endpoints de la API ===");

            // ========== ENDPOINTS BÁSICOS ==========
            app.get("/", ctx -> {
                System.out.println("✅ Endpoint / ejecutado correctamente");
                ctx.json(Map.of(
                        "message", "VetFinder API está funcionando",
                        "version", "1.0.0",
                        "status", "OK",
                        "timestamp", System.currentTimeMillis()
                ));
            });

            app.get("/test", ctx -> {
                System.out.println("✅ Endpoint /test ejecutado - probando conexión DB");
                try {
                    DatabaseConfig.getDataSource().getConnection().close();
                    ctx.json(Map.of(
                            "message", "Conexión a base de datos OK",
                            "status", "success"
                    ));
                } catch (Exception e) {
                    ctx.status(500).json(Map.of(
                            "message", "Error de conexión a base de datos",
                            "error", e.getMessage()
                    ));
                }
            });

            // ========== REGISTRAR TODOS LOS MÓDULOS DE LA APLICACIÓN ==========
            System.out.println("Registrando módulos de la aplicación...");

            AppModule.configureAllRoutes(app);

            System.out.println("✅ Módulos registrados correctamente");

            System.out.println("=== DEBUG: Iniciando servidor ===");

            // Iniciar servidor
            app.start("0.0.0.0", port);

            System.out.println("=================================================");
            System.out.println("✅ VetFinder API iniciada CORRECTAMENTE");
            System.out.println("🌐 Servidor: http://localhost:" + port);
            System.out.println("=================================================");
            System.out.println("🧪 Endpoints principales disponibles:");
            System.out.println("- GET  http://localhost:" + port + "/ (test básico)");
            System.out.println("- GET  http://localhost:" + port + "/test (test con DB)");

            System.out.println("📋 - Endpoints de Catálogo de Razas:");
            System.out.println("- GET  http://localhost:" + port + "/api/raza (todas las raza)");
            System.out.println("- GET  http://localhost:" + port + "/api/raza/{id} (raza por ID)");
            System.out.println("- GET  http://localhost:" + port + "/api/raza/buscar?nombre=texto (buscar raza)");

            System.out.println("🔗 Endpoints existentes del sistema:");
            System.out.println("- POST http://localhost:" + port + "/api/usuarios/login");
            System.out.println("- GET  http://localhost:" + port + "/api/especialidades");
            System.out.println("- GET  http://localhost:" + port + "/api/direcciones");
            System.out.println("- GET  http://localhost:" + port + "/api/servicios");
            System.out.println("- GET  http://localhost:" + port + "/api/usuarios/veterinarios");
            System.out.println("- GET  http://localhost:" + port + "/api/estadisticas/horarios-concurridos");
            System.out.println("=================================================");
            System.out.println("🎯 FRONTEND READY - Todos los endpoints activos");
            System.out.println("📋 CATÁLOGO DE RAZAS - Listo para usar");
            System.out.println("=================================================");
// ...
            System.out.println("📋 - Endpoints de Catálogo de Razas:");
            System.out.println("- GET  http://localhost:" + port + "/api/raza (todas las razas)");
            System.out.println("- GET  http://localhost:" + port + "/api/raza/{id} (raza por ID)");
            System.out.println("- GET  http://localhost:" + port + "/api/raza/buscar?nombre=texto (buscar raza)");
            System.out.println("- POST http://localhost:" + port + "/api/raza (crear una nueva raza)"); // NUEVO
// ...
        } catch (Exception e) {
            System.err.println("❌ ERROR CRÍTICO:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}