/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;

/**
 * @author Freddy Ali Castro Roman -252191
 */
/**
 * Entidad que representa aun medico. Incluye información profesional y horarios
 * de consulta.
 */
public class Medico {

    private int idMedico;
    private String nombre;
    private String apellidos;
    private String especialidad;
    private String[] diasConsulta; // dias de la semana
    private String horasConsulta; // horas del dia formato 24horas
    private Consultorio consultorio;

    /**
     * Constructor de medico.
     */
    public Medico(int idMedico, String nombre, String apellidos,
            String especialidad, String[] diasConsulta,
            String horasConsulta, Consultorio consultorio) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.diasConsulta = diasConsulta;
        this.horasConsulta = horasConsulta;
        this.consultorio = consultorio;
    }

    /**
     * Obtiene los datos del medico.
     *
     * @return Informacon del médico
     */
    public String obtenerDatos() {
        return String.format("Dr(a). %s %s - %s",
                nombre, apellidos, especialidad);
    }

    /**
     * Obtiene los detalles completos incluyendo horarios.
     *
     * @return Informacion mas detallada del medico
     */
    public String obtenerDetallesCompletos() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dr(a). ").append(nombre).append(" ").append(apellidos).append("\n");
        sb.append("Especialidad: ").append(especialidad).append("\n");
        sb.append("Consultorio: ").append(consultorio.obtenerUbicacion()).append("\n");
        sb.append("Días de consulta: ").append(String.join(", ", diasConsulta)).append("\n");
        sb.append("Horario: ").append(horasConsulta);
        return sb.toString();
    }

    /**
     * Verifica si el medico tiene disponibilidad.
     *
     * @return true si si la tiene disponible
     */
    public boolean verificarDisponibilidad() {
        return true; // Simplificado
    }

    // Getters y Setters
    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String[] getDiasConsulta() {
        return diasConsulta;
    }

    public void setDiasConsulta(String[] diasConsulta) {
        this.diasConsulta = diasConsulta;
    }

    public String getHorasConsulta() {
        return horasConsulta;
    }

    public void setHorasConsulta(String horasConsulta) {
        this.horasConsulta = horasConsulta;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    @Override
    public String toString() {
        return obtenerDatos();
    }
}
