/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.*;
import java.util.List;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Interfaz Observer
 */

public interface ObservadorVista {

    /**
     * Notifica cuando se encuentra un paciente y se cargan los médicos.
     *
     * @param paciente Paciente encontrado
     * @param medicos Lista de médicos disponibles
     */
    //notificarPacienteEncontrado(in Paciente, in List[Medico]): void
    void notificarPacienteEncontrado(Paciente paciente, List<Medico> medicos);

    /**
     * Notifica cuando se selecciona un medico.
     *
     * @param medico Medico seleccionado
     */
    //notificarMedicoSeleccionado(in Medico): void
    void notificarMedicoSeleccionado(Medico medico);

    /**
     * Notifica cuando se registra una cita de maner correta
     *
     * @param cita Cita registrada
     */
    //notificarCitaRegistrada(in Cita): void
    void notificarCitaRegistrada(Cita cita);

    /**
     * Notifica cuando ocurre un error.
     *
     * @param mensaje Mensaje de error
     */
    void notificarError(String mensaje);
}
