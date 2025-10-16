/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.citasmedicas;

import Controlador.ControladorRegistroCita;
import Repositorio.CitaRepository;
import Repositorio.MedicoRepository;
import Repositorio.PacienteRepository;
import Vista.VistaRegistroCita;

import javax.swing.*;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
public class CitasMedicas {
    public static void main(String[] args) {
        // Ejecutar la app
        SwingUtilities.invokeLater(() -> {
            // Inicializar repositorios 
            PacienteRepository pacienteRepo = new PacienteRepository();
            MedicoRepository medicoRepo = new MedicoRepository();
            CitaRepository citaRepo = new CitaRepository();
            
            // Crear la vista
            VistaRegistroCita vista = new VistaRegistroCita();
            
            // Crear el controlador conectando vista y repositorios
            ControladorRegistroCita controlador = new ControladorRegistroCita(
                vista, pacienteRepo, medicoRepo, citaRepo
            );
            
            // Mostrar la ventana
            vista.setVisible(true);
            
            System.out.println("Sistema de Registro de Citas Médicas iniciado correctamente.");
        });
    }
}