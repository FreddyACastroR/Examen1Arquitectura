/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.*;
import Repositorio.CitaRepository;
import Repositorio.MedicoRepository;
import Repositorio.PacienteRepository;
import Repositorio.*;
import Vista.VistaRegistroCita;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Controlador que gestiona la lógica de negocio del caso de uso "Registrar Cita
 * Médica". Implementa el patrón MVC coordinando la Vista con los Repositorios
 * del Modelo.
 */
public class ControladorRegistroCita {

    private VistaRegistroCita vista;
    private PacienteRepository pacienteRepo;
    private MedicoRepository medicoRepo;
    private CitaRepository citaRepo;

    private Paciente pacienteActual;
    private Medico medicoSeleccionado;

    /**
     * Constructor que conecta el controlador con vista y repositorios.
     */
    public ControladorRegistroCita(VistaRegistroCita vista,
            PacienteRepository pacienteRepo,
            MedicoRepository medicoRepo,
            CitaRepository citaRepo) {
        this.vista = vista;
        this.pacienteRepo = pacienteRepo;
        this.medicoRepo = medicoRepo;
        this.citaRepo = citaRepo;

        inicializarEventos();
    }

    /**
     * Configura los eventos de la vista para que llamen a los métodos del
     * controlador.
     */
    private void inicializarEventos() {
        vista.setControlador(this);
    }

    /**
     * Busca un paciente por NSS y actualiza la vista con su información.
     *
     * @param nss Número de Seguridad Social
     */
    public void buscarPacientePorNSS(String nss) {
        if (nss == null || nss.trim().isEmpty()) {
            vista.mostrarError("Por favor ingrese un NSS válido");
            return;
        }

        // Buscar paciente en el repositorio
        pacienteActual = pacienteRepo.buscarPorNSS(nss.trim());

        if (pacienteActual == null) {
            vista.mostrarError("Paciente no encontrado con NSS: " + nss);
            return;
        }

        // Validar NSS del paciente
        if (!pacienteActual.validarNSS()) {
            vista.mostrarError("El NSS del paciente no es válido");
            return;
        }

        // Cargar lista de médicos disponibles
        List<Medico> medicos = medicoRepo.obtenerTodos();

        // Actualizar la vista
        vista.mostrarDatosPaciente(pacienteActual);
        vista.cargarListaMedicos(medicos);

        System.out.println("Paciente encontrado: " + pacienteActual.obtenerDatos());
    }

    /**
     * Maneja la selección de un médico desde la lista.
     *
     * @param idMedico ID del médico seleccionado
     */
    public void seleccionarMedico(int idMedico) {
        // Obtener el médico del repositorio
        medicoSeleccionado = medicoRepo.obtenerPorId(idMedico);

        if (medicoSeleccionado == null) {
            vista.mostrarError("Médico no encontrado");
            return;
        }

        // Verificar disponibilidad (simplificado)
        if (!medicoSeleccionado.verificarDisponibilidad()) {
            vista.mostrarError("El médico no está disponible actualmente");
            return;
        }

        // Mostrar detalles del médico en la vista
        vista.mostrarDetallesMedico(medicoSeleccionado);

        System.out.println("Médico seleccionado: " + medicoSeleccionado.obtenerDatos());
    }

    /**
     * Registra una nueva cita médica con la fecha y hora especificadas.
     *
     * @param fecha Fecha de la cita
     * @param hora Hora de la cita
     */
    public void registrarCita(LocalDate fecha, LocalTime hora) {
        // Validar que haya un paciente y médico seleccionados
        if (pacienteActual == null) {
            vista.mostrarError("Primero debe buscar un paciente");
            return;
        }

        if (medicoSeleccionado == null) {
            vista.mostrarError("Primero debe seleccionar un médico");
            return;
        }

        // Validar fecha y hora
        if (fecha == null || hora == null) {
            vista.mostrarError("Debe seleccionar fecha y hora para la cita");
            return;
        }

        // Validar que la fecha no sea pasada
        if (fecha.isBefore(LocalDate.now())) {
            vista.mostrarError("No se pueden agendar citas en fechas pasadas");
            return;
        }

        // Crear nueva cita
        Cita nuevaCita = new Cita(pacienteActual, medicoSeleccionado, fecha, hora);

        // Registrar la cita
        if (!nuevaCita.registrar()) {
            vista.mostrarError("No se pudo registrar la cita");
            return;
        }

        // Guardar en el repositorio
        Cita citaGuardada = citaRepo.guardar(nuevaCita);

        // Mostrar confirmación
        vista.mostrarConfirmacionCita(citaGuardada);

        System.out.println("Cita registrada exitosamente - ID: " + citaGuardada.getIdCita());
    }

    /**
     * Reinicia el formulario para registrar una nueva cita.
     */
    public void nuevaCita() {
        pacienteActual = null;
        medicoSeleccionado = null;
        vista.limpiarFormulario();
        System.out.println("Formulario reiniciado para nueva cita");
    }
}
