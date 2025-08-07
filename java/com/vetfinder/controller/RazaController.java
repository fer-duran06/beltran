package com.vetfinder.controller;

import com.vetfinder.model.Raza;
import com.vetfinder.service.RazaService;
import com.vetfinder.util.ApiResponse;
import io.javalin.http.Handler;

public class RazaController {

    private RazaService razaService = null;

    public RazaController(RazaService razaService) {
        this.razaService = razaService;
    }

    public Handler getAll = ctx -> {
        ctx.json(razaService.obtenerTodasLasRazas());
    };

    /**
     * Handler para crear una nueva raza.
     * Lee el cuerpo de la petición, crea la raza y retorna el objeto creado.
     */
    public Handler createRaza = ctx -> {
        try {
            // Se asume que el cuerpo de la petición es un JSON con un campo 'nombre'
            Raza newRaza = ctx.bodyAsClass(Raza.class);
            Raza createdRaza = razaService.crearRaza(newRaza);

            ctx.status(201).json(ApiResponse.success("Raza creada correctamente", createdRaza));
        } catch (IllegalArgumentException e) {
            ctx.status(400).json(ApiResponse.error("Error en los datos de entrada: " + e.getMessage()));
        } catch (Exception e) {
            ctx.status(500).json(ApiResponse.error("Error al crear la raza: " + e.getMessage()));
        }
    };
}