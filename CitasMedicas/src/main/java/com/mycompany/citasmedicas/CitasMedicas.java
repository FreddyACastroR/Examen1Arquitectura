/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.citasmedicas;

import Vista.VistaRegistroCita;
import Controlador.ControladorRegistroCita;
import Repositorio.PacienteRepository;
import Repositorio.MedicoRepository;
import Repositorio.CitaRepository;
import javax.swing.*;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
public class CitasMedicas {
    public static void main(String[] args) {
        // Ejecutar la aplicación en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            System.out.println("=== Iniciando Sistema de Registro de Citas Médicas ===");
            System.out.println("Arquitectura: MVC + Patrón Observer\n");
            
            // Inicializar repositorios (capa de datos)
            PacienteRepository pacienteRepo = new PacienteRepository();
            MedicoRepository medicoRepo = new MedicoRepository();
            CitaRepository citaRepo = new CitaRepository();
            
            // Crear el controlador del patrón Observer)
            ControladorRegistroCita controlador = new ControladorRegistroCita(
                pacienteRepo, medicoRepo, citaRepo
            );
            
            // Crear la vista (Observer de Observer)
            VistaRegistroCita vista = new VistaRegistroCita();
            
            // Conectar vista con controlador usando el patrón Observer
            // La vista se registra como observadora del controlador
            vista.setControlador(controlador);
            
            // Mostrar la ventana
            vista.setVisible(true);
            
            System.out.println("\n Sistema iniciado correctamente");
        });
    }
}