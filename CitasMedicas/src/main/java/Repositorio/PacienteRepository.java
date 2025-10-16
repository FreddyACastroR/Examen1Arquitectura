/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import Modelo.Paciente;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Repositorio para gestionar pacientes. Para simula una base de datos aca.
 */
public class PacienteRepository {

    private Map<String, Paciente> pacientes; // NSS 

    /**
     * Constructor que inicializa el repositorio con datos de ejemplo. hardcode
     */
    public PacienteRepository() {
        pacientes = new HashMap<>();
        cargarDatosEjemplo();
    }

    /**
     * Carga pacientes de ejemplo en el sistema.
     */
    private void cargarDatosEjemplo() {
        pacientes.put("12345678901", new Paciente(
                "12345678901", "Freddy Ali", "Castro Roman",
                "686-102-4567", LocalDate.of(2002, 4, 17)
        ));

        pacientes.put("98765432109", new Paciente(
                "98765432109", "Maria Juana", "Chona Hernández",
                "644-987-6543", LocalDate.of(2000, 8, 22)
        ));

        pacientes.put("11111111111", new Paciente(
                "11111111111", "Carlos", "Chipocludo Ruiz",
                "644-111-1111", LocalDate.of(1978, 3, 10) //este ta viejo
        ));
    }

    /**
     * Busca un paciente por su NSS.
     *
     * @param nss Nmero de Seguridad Social
     * @return Paciente encontrado o null si no existe
     */
    public Paciente buscarPorNSS(String nss) {
        return pacientes.get(nss);
    }

    /**
     * Guarda o actualiza un paciente en el repositorio.
     */
    public void guardar(Paciente paciente) {
        pacientes.put(paciente.getNss(), paciente);
    }

    /**
     * Obtiene todos los pacientes registrados.
     *
     * @return Lista de todos los pacientes
     */
    public List<Paciente> obtenerTodos() {
        return new ArrayList<>(pacientes.values());
    }
}
