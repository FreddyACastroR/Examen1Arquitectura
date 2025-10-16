/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Entidad que representa a un paciente.
 */
public class Paciente {

    private String nss; // el numero de Seguridad Social
    private String nombre;
    private String apellidos;
    private String telefono;
    private LocalDate fechaNacimiento;

    /**
     * Constructor
     */
    public Paciente(String nss, String nombre, String apellidos,
            String telefono, LocalDate fechaNacimiento) {
        this.nss = nss;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene los datos completos del pacient.
     *
     * @return Info del paciente
     */
    public String obtenerDatos() {
        return String.format("%s %s - NSS: %s - Tel: %s",
                nombre, apellidos, nss, telefono);
    }

    /**
     * Validacion pa que el NSS tenga el formato correcto.
     *
     * @return true si el NSS es corretco
     */
    public boolean validarNSS() {
        return nss != null && nss.length() == 11;
    }

    // Getters y Setters
    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return obtenerDatos();
    }
}
