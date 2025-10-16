/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import Repositorio.*;
import Vista.ObservadorVista;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Controlador que gestiona la logica de negocio del caso de uso "Registrar Cita
 * Medica". Implementa el patrón MVC y el patrón Observer como que notifica
 * cambios a los observadores.
 *
 */
public class ControladorRegistroCita {

    private PacienteRepository pacienteRepo;
    private MedicoRepository medicoRepo;
    private CitaRepository citaRepo;

    // Lista de observadores registrados 
    private List<ObservadorVista> observadores;

    private Paciente pacienteActual;
    private Medico medicoSeleccionado;

    /**
     * Constructor que inicializa el controlador con los repositorios.
     */
    public ControladorRegistroCita(PacienteRepository pacienteRepo,
            MedicoRepository medicoRepo,
            CitaRepository citaRepo) {
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
        this.citaRepo = citaRepo;
        this.observadores = new ArrayList<>();
    }

    // metodos del patron observer
    /**
     * Registra un observador para recibir notificaciones
     *
     * @param observador Vista que implementa ObservadorVista
     */
    public void agregarObservador(ObservadorVista observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
            System.out.println("Observador agregado: " + observador.getClass().getSimpleName());
        }
    }

    /**
     * Elimina un observador de la lista
     *
     * @param observador Vista a eliminar
     */
    public void eliminarObservador(ObservadorVista observador) {
        observadores.remove(observador);
        System.out.println("Observador eliminado: " + observador.getClass().getSimpleName());
    }

    /**
     * Notifica a todos los observadores cuando se encuentra un paciente.
     */
    private void notificarPacienteEncontrado(Paciente paciente, List<Medico> medicos) {
        for (ObservadorVista obs : observadores) {
            obs.notificarPacienteEncontrado(paciente, medicos);
        }
    }

    /**
     * Notifica a todos los observadores cuando se selecciona un mdico.
     */
    private void notificarMedicoSeleccionado(Medico medico) {
        for (ObservadorVista obs : observadores) {
            obs.notificarMedicoSeleccionado(medico);
        }
    }

    /**
     * Notifica a todos los observadores cuando se registra una cita.
     */
    private void notificarCitaRegistrada(Cita cita) {
        for (ObservadorVista obs : observadores) {
            obs.notificarCitaRegistrada(cita);
        }
    }

    /**
     * Notifica a todos los observadores cuando ocurre un error.
     */
    private void notificarError(String mensaje) {
        for (ObservadorVista obs : observadores) {
            obs.notificarError(mensaje);
        }
    }

    //metodos de la logica de negocio
    /**
     * Busca un paciente por NSS y notifica a los observadores.
     *
     * @param nss Número de Seguridad Social
     */
    public void buscarPacientePorNSS(String nss) {
        if (nss == null || nss.trim().isEmpty()) {
            notificarError("Por favor ingrese un NSS válido");
            return;
        }

        // Buscar paciente en el repositorio
        pacienteActual = pacienteRepo.buscarPorNSS(nss.trim());

        if (pacienteActual == null) {
            notificarError("Paciente no encontrado con NSS: " + nss);
            return;
        }

        // Validar NSS del paciente
        if (!pacienteActual.validarNSS()) {
            notificarError("El NSS del paciente no es válido");
            return;
        }

        // Cargar lista de médicos disponibles
        List<Medico> medicos = medicoRepo.obtenerTodos();

        // Notificar a los observadores (patrón Observer)
        notificarPacienteEncontrado(pacienteActual, medicos);

        System.out.println("Paciente encontrado: " + pacienteActual.obtenerDatos());
    }

    /**
     * Maneja la seleccion de un medico desde la lista.
     *
     * @param idMedico ID del medico seleccionado
     */
    public void seleccionarMedico(int idMedico) {
        // Obtener el medico del repositorio
        medicoSeleccionado = medicoRepo.obtenerPorId(idMedico);

        if (medicoSeleccionado == null) {
            notificarError("Médico no encontrado");
            return;
        }

        // Verificar disponibilidad
        if (!medicoSeleccionado.verificarDisponibilidad()) {
            notificarError("El médico no está disponible actualmente");
            return;
        }

        // Notificar a los observadores (patrón Observer)
        notificarMedicoSeleccionado(medicoSeleccionado);

        System.out.println("Médico seleccionado: " + medicoSeleccionado.obtenerDatos());
    }

    /**
     * Registra una nueva cita medica con la fecha y hora.
     *
     * @param fecha Fecha de la cita
     * @param hora Hora de la cita
     */
    public void registrarCita(LocalDate fecha, LocalTime hora) {
        // Validar que haya un paciente y medico seleccionados
        if (pacienteActual == null) {
            notificarError("Primero debe buscar un paciente");
            return;
        }

        if (medicoSeleccionado == null) {
            notificarError("Primero debe seleccionar un médico");
            return;
        }

        // Validar fecha y hora
        if (fecha == null || hora == null) {
            notificarError("Debe seleccionar fecha y hora para la cita");
            return;
        }

        // Validar que la fecha no sea pasada
        if (fecha.isBefore(LocalDate.now())) {
            notificarError("No se pueden agendar citas en fechas pasadas");
            return;
        }

        // Crear nueva cita
        Cita nuevaCita = new Cita(pacienteActual, medicoSeleccionado, fecha, hora);

        // Registrar la cita
        if (!nuevaCita.registrar()) {
            notificarError("No se pudo registrar la cita");
            return;
        }

        // Guardar en el repositorio
        Cita citaGuardada = citaRepo.guardar(nuevaCita);

        // Notificar a los observadores ------------------------------------
        notificarCitaRegistrada(citaGuardada);

        System.out.println("Cita registrada exitosamente - ID: " + citaGuardada.getIdCita());
    }

    /**
     * Reinicia el formulario para registrar una nueva cita.
     */
    public void nuevaCita() {
        pacienteActual = null;
        medicoSeleccionado = null;
        System.out.println("Formulario reiniciado para nueva cita");
    }
}
