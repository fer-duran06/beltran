package com.vetfinder.di;

import io.javalin.Javalin;
import com.vetfinder.controller.*;
import com.vetfinder.repository.*;
import com.vetfinder.routes.*;
import com.vetfinder.service.*;

/**
 * Módulo de configuración de dependencias de la aplicación.
 * Implementa un patrón de inyección de dependencias manual.
 * Crea e inicializa todas las capas de la aplicación incluyendo el nuevo módulo RAZA.
 */
public class AppModule {

    /**
     * NUEVO: Inicializa el módulo de razas
     * @return Rutas configuradas para razas
     */
    public static RazaRoutes initRazas() {
        RazaRepository razaRepository = new RazaRepository();
        RazaService razaService = new RazaService(razaRepository);
        RazaController razaController = new RazaController(razaService);
        return new RazaRoutes(razaController);
    }

    public static RolRoutes initRoles() {
        RolRepository rolRepository = new RolRepository();
        RolService rolService = new RolService(rolRepository);
        RolController rolController = new RolController(rolService);
        return new RolRoutes(rolController);
    }

    public static UsuarioRoutes initUsuarios() {
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        UsuarioController usuarioController = new UsuarioController(usuarioService);
        return new UsuarioRoutes(usuarioController);
    }

    public static SexoRoutes initSexos() {
        SexoRepository sexoRepository = new SexoRepository();
        SexoService sexoService = new SexoService(sexoRepository);
        SexoController sexoController = new SexoController(sexoService);
        return new SexoRoutes(sexoController);
    }

    public static DireccionRoutes initDirecciones() {
        DireccionRepository direccionRepository = new DireccionRepository();
        DireccionService direccionService = new DireccionService(direccionRepository);
        DireccionController direccionController = new DireccionController(direccionService);
        return new DireccionRoutes(direccionController);
    }

    public static EspecialidadRoutes initEspecialidades() {
        EspecialidadRepository especialidadRepository = new EspecialidadRepository();
        EspecialidadService especialidadService = new EspecialidadService(especialidadRepository);
        EspecialidadController especialidadController = new EspecialidadController(especialidadService);
        return new EspecialidadRoutes(especialidadController);
    }

    public static ServicioRoutes initServicios() {
        ServicioRepository servicioRepository = new ServicioRepository();
        ServicioService servicioService = new ServicioService(servicioRepository);
        ServicioController servicioController = new ServicioController(servicioService);
        return new ServicioRoutes(servicioController);
    }

    public static MascotaRoutes initMascotas() {
        MascotaRepository mascotaRepository = new MascotaRepository();
        MascotaService mascotaService = new MascotaService(mascotaRepository);
        MascotaController mascotaController = new MascotaController(mascotaService);
        return new MascotaRoutes(mascotaController);
    }

    public static DatoVeterinarioRoutes initDatosVeterinarios() {
        DatoVeterinarioRepository datoVeterinarioRepository = new DatoVeterinarioRepository();
        DatoVeterinarioService datoVeterinarioService = new DatoVeterinarioService(datoVeterinarioRepository);
        DatoVeterinarioController datoVeterinarioController = new DatoVeterinarioController(datoVeterinarioService);
        return new DatoVeterinarioRoutes(datoVeterinarioController);
    }

    public static ConsultorioRoutes initConsultorios() {
        ConsultorioRepository consultorioRepository = new ConsultorioRepository();
        ConsultorioService consultorioService = new ConsultorioService(consultorioRepository);
        ConsultorioController consultorioController = new ConsultorioController(consultorioService);
        return new ConsultorioRoutes(consultorioController);
    }

    public static CitaRoutes initCitas() {
        CitaRepository citaRepository = new CitaRepository();
        CitaService citaService = new CitaService(citaRepository);
        CitaController citaController = new CitaController(citaService);
        return new CitaRoutes(citaController);
    }

    public static FacturaRoutes initFacturas() {
        FacturaRepository facturaRepository = new FacturaRepository();
        FacturaService facturaService = new FacturaService(facturaRepository);
        FacturaController facturaController = new FacturaController(facturaService);
        return new FacturaRoutes(facturaController);
    }

    public static EstadisticasRoutes initEstadisticas() {
        EstadisticasRepository estadisticasRepository = new EstadisticasRepository();
        EstadisticasService estadisticasService = new EstadisticasService(estadisticasRepository);
        EstadisticasController estadisticasController = new EstadisticasController(estadisticasService);
        return new EstadisticasRoutes(estadisticasController);
    }

    /**
     * MÉTODO PRINCIPAL: Configura todos los módulos de la aplicación
     * @param app Instancia de Javalin donde registrar las rutas
     */
    public static void configureAllRoutes(Javalin app) {
        try {
            // ========== MÓDULOS BÁSICOS (CATÁLOGOS) ==========
            System.out.println("📝 Registrando ROL...");
            initRoles().register(app);
            System.out.println("✅ ROL registrado");

            System.out.println("⚥ Registrando SEXO...");
            initSexos().register(app);
            System.out.println("✅ SEXO registrado");

            System.out.println("🎓 Registrando ESPECIALIDAD...");
            initEspecialidades().register(app);
            System.out.println("✅ ESPECIALIDAD registrado");

            System.out.println("💼 Registrando SERVICIO...");
            initServicios().register(app);
            System.out.println("✅ SERVICIO registrado");

            System.out.println("📍 Registrando DIRECCION...");
            initDirecciones().register(app);
            System.out.println("✅ DIRECCION registrado");

            // ========== NUEVO MÓDULO DE RAZAS ==========
            System.out.println("🐕 Registrando RAZA...");
            initRazas().register(app);
            System.out.println("✅ RAZA registrado");

            // ========== MÓDULOS PRINCIPALES ==========
            System.out.println("👥 Registrando USUARIO...");
            initUsuarios().register(app);
            System.out.println("✅ USUARIO registrado");

            System.out.println("🐕 Registrando MASCOTA...");
            initMascotas().register(app);
            System.out.println("✅ MASCOTA registrado");

            System.out.println("🩺 Registrando DATO VETERINARIO...");
            initDatosVeterinarios().register(app);
            System.out.println("✅ DATO VETERINARIO registrado");

            System.out.println("🏥 Registrando CONSULTORIO...");
            initConsultorios().register(app);
            System.out.println("✅ CONSULTORIO registrado");

            System.out.println("📊 Registrando ESTADÍSTICAS...");
            initEstadisticas().register(app);
            System.out.println("✅ ESTADÍSTICAS registrado");

            // ========== MÓDULOS OPCIONALES (COMENTADOS SI NO ESTÁN LISTOS) ==========
            /*
            System.out.println("📅 Registrando CITA...");
            initCitas().register(app);
            System.out.println("✅ CITA registrado");

            System.out.println("🧾 Registrando FACTURA...");
            initFacturas().register(app);
            System.out.println("✅ FACTURA registrado");
            */

        } catch (Exception e) {
            System.err.println("❌ Error al registrar módulo: " + e.getMessage());
            e.printStackTrace();
            // Continuar con el servidor aunque falle un módulo
        }
    }
}