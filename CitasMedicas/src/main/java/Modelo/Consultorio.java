/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * @author freddy Ali Castro Roman - 252191
 */
/**
 * Entidad que representa un consultorio medico. Contiene información sobre la
 * ubicación del consultorio.
 */
public class Consultorio {

    private String numero;
    private int piso;
    private String edificio;

    /**
     * Constructor de Consultorio.
     */
    public Consultorio(String numero, int piso, String edificio) {
        this.numero = numero;
        this.piso = piso;
        this.edificio = edificio;
    }

    /**
     * Obtiene la ubicacio del consultorio.
     *
     * @return Ubicacion del consultorio
     */
    public String obtenerUbicacion() {
        return String.format("Consultorio %s - Piso %d - Edificio %s",
                numero, piso, edificio);
    }

    // Getters y Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return obtenerUbicacion();
    }
}
