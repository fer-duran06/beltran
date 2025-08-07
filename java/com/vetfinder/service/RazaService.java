package com.vetfinder.service;

import com.vetfinder.model.Raza;
import com.vetfinder.repository.RazaRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio para la lógica de negocio del catálogo de razas
 * Maneja las operaciones relacionadas con razas
 */
public class RazaService {

    private final RazaRepository razaRepository;

    // Constructor corregido
    public RazaService(RazaRepository razaRepository) {
        this.razaRepository = razaRepository; // Asigna la instancia que se recibe
    }

    /**
     * Obtiene todas las razas disponibles
     * @return Lista de todas las razas
     * @throws Exception Error al obtener las razas
     */
    public List<Raza> obtenerTodasLasRazas() throws Exception {
        try {
            return razaRepository.findAll();
        } catch (SQLException e) {
            throw new Exception("Error al obtener las razas: " + e.getMessage());
        }
    }

    /**
     * Busca una raza por su ID
     * @param idRaza ID de la raza a buscar
     * @return Raza encontrada
     * @throws Exception Error al buscar la raza
     */
    public Raza obtenerRazaPorId(int idRaza) throws Exception {
        try {
            Raza raza = razaRepository.findById(idRaza);
            if (raza == null) {
                throw new Exception("No se encontró la raza con ID: " + idRaza);
            }
            return raza;
        } catch (SQLException e) {
            throw new Exception("Error al buscar la raza: " + e.getMessage());
        }
    }

    /**
     * Busca razas por nombre para autocompletado
     * @param nombre Texto a buscar
     * @return Lista de razas que coinciden
     * @throws Exception Error al buscar razas
     */
    public List<Raza> buscarRazasPorNombre(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacío");
        }

        try {
            return razaRepository.findByNombreContaining(nombre.trim());
        } catch (SQLException e) {
            throw new Exception("Error al buscar razas: " + e.getMessage());
        }
    }

    /**
     * Valida si existe una raza con el ID especificado
     * @param idRaza ID a validar
     * @return true si existe, false si no
     */
    public boolean existeRaza(int idRaza) {
        try {
            Raza raza = razaRepository.findById(idRaza);
            return raza != null;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Crea una nueva raza.
     * @param raza Objeto Raza a crear.
     * @return La raza creada con su ID.
     * @throws Exception si el nombre está vacío o si ocurre un error en la base de datos.
     */
    public Raza crearRaza(Raza raza) throws Exception {
        if (raza == null || raza.getNombre() == null || raza.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la raza no puede estar vacío.");
        }
        try {
            return razaRepository.save(raza);
        } catch (SQLException e) {
            throw new Exception("Error al crear la raza: " + e.getMessage());
        }
    }
}