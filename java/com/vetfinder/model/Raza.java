package com.vetfinder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Modelo de datos para la tabla RAZA
 * Catálogo simple de razas con ID y nombre
 */
public class Raza {
    private int idRaza;
    private String nombre;

    // Constructor vacío
    public Raza() {}

    // Constructor completo
    public Raza(int idRaza, String nombre) {
        this.idRaza = idRaza;
        this.nombre = nombre;
    }

    // Constructor para creación (sin ID)
    public Raza(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Raza{" +
                "idRaza=" + idRaza +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    /**
     * Inserts a new Raza into the database.
     *
     * @param connection The database connection.
     * @return The ID of the newly created Raza, or -1 if the insertion fails.
     * @throws SQLException if a database access error occurs.
     */
    public int post(Connection connection) throws SQLException {
        String sql = "INSERT INTO Raza (nombre) VALUES (?)";
        int newId = -1;

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, this.nombre);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        newId = generatedKeys.getInt(1);
                        this.idRaza = newId; // Set the ID of the current object
                    }
                }
            }
        }
        return newId;
    }
}