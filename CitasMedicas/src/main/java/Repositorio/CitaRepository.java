/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Modelo.Cita;
import Modelo.Paciente;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Freddy Ali Castro Roman -252191
 */
/**
 * Repositorio para gestionar las citas. Simula una base de datos
 */
public class CitaRepository {

    private List<Cita> citas;

    /**
     * Constructor que inicializa el repositorio vacio.
     */
    public CitaRepository() {
        citas = new ArrayList<>();
    }

    /**
     * Guarda una nueva cita en el sistema.
     *
     * @param cita Cita a guardar
     * @return La cita guardada con su ID
     */
    public Cita guardar(Cita cita) {
        citas.add(cita);
        System.out.println("Cita guardada con ID: " + cita.getIdCita());
        return cita;
    }

    /**
     * Obtiene todas las citas de un paciente .
     *
     * @param nss NSS del paciente
     * @return Lista de citas del paciente
     */
    public List<Cita> obtenerPorPaciente(String nss) {
        return citas.stream()
                .filter(cita -> cita.getPaciente().getNss().equals(nss))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las citas registradas.
     *
     * @return Lista de todas las citas
     */
    public List<Cita> obtenerTodas() {
        return new ArrayList<>(citas);
    }

    /**
     * Elimina una cita del sistema.
     *
     * @param idCita ID de la cita a eliminar
     * @return true si se elimina correctamente
     */
    public boolean eliminar(int idCita) {
        return citas.removeIf(cita -> cita.getIdCita() == idCita);
    }
}
