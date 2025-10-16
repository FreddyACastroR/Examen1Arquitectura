/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Modelo.Medico;
import Modelo.Consultorio;
import java.util.*;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Repositorio para gestionar medicos. Simula una base de datos .
 */
public class MedicoRepository {

    private Map<Integer, Medico> medicos; // ID como clave

    /**
     * Constructor que inicializa el repositorio con datos de ejemplo.
     */
    public MedicoRepository() {
        medicos = new HashMap<>();
        cargarDatosEjemplo();
    }

    /**
     * Carga médicos de ejemplo con sus consultorios.
     */
    private void cargarDatosEjemplo() {
        medicos.put(1, new Medico(
                1, "Jose", "Luis", "Cardiología",
                new String[]{"Lunes", "Miércoles", "Viernes"},
                "09:00 - 14:00",
                new Consultorio("101", 1, "A")
        ));

        medicos.put(2, new Medico(
                2, "Pepe", "Pecas Pica Papas", "Pediatría",
                new String[]{"Martes", "Jueves"},
                "10:00 - 15:00",
                new Consultorio("205", 2, "A")
        ));

        medicos.put(3, new Medico(
                3, "Nombre", "Generico tres", "Medicina General",
                new String[]{"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"},
                "08:00 - 13:00",
                new Consultorio("102", 1, "B")
        ));

        medicos.put(4, new Medico(
                4, "Freddy Ali", "Castro Roman", "Traumatología",
                new String[]{"Miércoles", "Viernes"},
                "14:00 - 18:00",
                new Consultorio("301", 3, "A")
        ));

        medicos.put(5, new Medico(
                5, "José Gamaliel", "Rivera Ibarra", "Dermatología",
                new String[]{"Lunes", "Jueves"},
                "11:00 - 16:00",
                new Consultorio("203", 2, "B")
        ));
    }

    /**
     * Obtiene todos los meddicos disponibles.
     *
     * @return Lista de todos los medicos
     */
    //obtenerTodos(): List<Medico>
    public List<Medico> obtenerTodos() {
        return new ArrayList<>(medicos.values());
    }

    /**
     * Busca un medico por su ID.
     *
     * @param id ID del mdico
     * @return Medico encontrado o null si no existe
     */
    //obtenerPorID(in int): Medico
    public Medico obtenerPorId(int id) {
        return medicos.get(id);
    }

    /**
     * Guarda o actualiza un medico en el repositorio.
     *
     * @param medico Medico a guardar
     */
    public void guardar(Medico medico) {
        medicos.put(medico.getIdMedico(), medico);
    }
}
