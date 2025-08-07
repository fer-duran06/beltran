package com.vetfinder.routes;

import com.vetfinder.controller.RazaController;
import io.javalin.Javalin;

public class RazaRoutes {

    private final RazaController razaController;

    public RazaRoutes(RazaController razaController) {
        this.razaController = razaController;
    }

    public void register(Javalin app) {
        // Endpoint para obtener todas las razas
        app.get("/api/raza", razaController.getAll);

        // NUEVO: Endpoint para crear una nueva raza
        app.post("/api/raza", razaController.createRaza);
    }
}