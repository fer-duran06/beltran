package com.vetfinder.repository;

import com.vetfinder.config.DatabaseConfig;
import com.vetfinder.model.Raza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RazaRepository {

    public List<Raza> findAll() throws SQLException {
        List<Raza> razas = new ArrayList<>();
        String query = "SELECT idRaza, nombre FROM RAZA ORDER BY nombre";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Raza raza = new Raza();
                raza.setIdRaza(rs.getInt("idRaza"));
                raza.setNombre(rs.getString("nombre"));
                razas.add(raza);
            }
        }
        return razas;
    }

    public Raza findById(int idRaza) throws SQLException {
        String query = "SELECT idRaza, nombre FROM RAZA WHERE idRaza = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idRaza);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Raza raza = new Raza();
                    raza.setIdRaza(rs.getInt("idRaza"));
                    raza.setNombre(rs.getString("nombre"));
                    return raza;
                }
            }
        }
        return null;
    }

    public List<Raza> findByNombreContaining(String nombre) throws SQLException {
        List<Raza> razas = new ArrayList<>();
        String query = "SELECT idRaza, Nombre_raza FROM raza ORDER BY Nombre_raza";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + nombre + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Raza raza = new Raza();
                    raza.setIdRaza(rs.getInt("idRaza"));
                    raza.setNombre(rs.getString("nombre"));
                    razas.add(raza);
                }
            }
        }
        return razas;
    }

    /**
     * Guarda una nueva raza en la base de datos.
     * @param raza Objeto Raza con el nombre a guardar.
     * @return El objeto Raza con el ID asignado.
     * @throws SQLException si ocurre un error en la base de datos.
     */
    public Raza save(Raza raza) throws SQLException {
        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            int newId = raza.post(conn);
            if (newId != -1) {
                return raza; // El objeto raza ya tiene el nuevo ID asignado
            } else {
                throw new SQLException("No se pudo insertar la raza en la base de datos.");
            }
        }
    }
}




