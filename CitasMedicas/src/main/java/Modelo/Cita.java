/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Entidad de una cita. Conecta un paciente con un medico en una fecha y hora
 * específica.
 */
public class Cita {

    private static int contadorId = 1; // Contador para generar IDs facilones.

    private int idCita;
    private Paciente paciente;
    private Medico medico;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado; // "Programada", "Completada", "Cancelada"

    /**
     * Constructor de Cita que genera id.
     */
    public Cita(Paciente paciente, Medico medico, LocalDate fecha, LocalTime hora) {
        this.idCita = contadorId++;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "Programada";
    }

    /**
     * Registra la cita en el sistema.
     *
     * @return true si se registra correctamente
     */
    public boolean registrar() {
        return this.estado.equals("Programada");
    }

    /**
     * Obtiene los detalles de la cita.
     *
     * @return Informacion de la cita .
     */
    public String obtenerDetalles() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        StringBuilder sb = new StringBuilder();
        sb.append("-----CITA REGISTRADA-----\n\n");
        sb.append("ID Cita: ").append(idCita).append("\n");
        sb.append("Paciente: ").append(paciente.getNombre()).append(" ")
                .append(paciente.getApellidos()).append("\n");
        sb.append("NSS: ").append(paciente.getNss()).append("\n\n");
        sb.append("Médico: Dr(a). ").append(medico.getNombre()).append(" ")
                .append(medico.getApellidos()).append("\n");
        sb.append("Especialidad: ").append(medico.getEspecialidad()).append("\n");
        sb.append("Consultorio: ").append(medico.getConsultorio().obtenerUbicacion()).append("\n\n");
        sb.append("Fecha: ").append(fecha.format(formatoFecha)).append("\n");
        sb.append("Hora: ").append(hora.format(formatoHora)).append("\n");
        sb.append("Estado: ").append(estado);

        return sb.toString();
    }

    /**
     * Cancela la cita.
     *
     * @return true si se cancela correctamente
     */
    public boolean cancelar() {
        this.estado = "Cancelada";
        return true;
    }

    // Getters y Setters
    public int getIdCita() {
        return idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
