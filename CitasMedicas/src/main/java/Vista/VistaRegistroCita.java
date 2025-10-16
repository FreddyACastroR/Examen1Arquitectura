/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.ControladorRegistroCita;
import Modelo.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Freddy Ali Castro Roman - 252191
 */
/**
 * Vista principal para el registro de citas
 */
public class VistaRegistroCita extends JFrame implements ObservadorVista {

    // Referencia al controlador
    private ControladorRegistroCita controlador;

    // Componentes del Panel NSS
    private JTextField txtNSS;
    private JButton btnBuscar;

    // Componentes del Panel Datos Paciente
    private JTextArea txtAreaPaciente;

    // Componentes del Panel Lista Médicos
    private JList<Medico> listaMedicos;
    private DefaultListModel<Medico> modeloListaMedicos;

    // Componentes del Panel Detalles (Médico o Cita)
    private JTextArea txtAreaDetalles;
    private JPanel panelDetallesContenido;

    // Componentes para selección de fecha y hora
    private JSpinner spinnerFecha;
    private JSpinner spinnerHora;
    private JButton btnConfirmarCita;
    private JButton btnNuevaCita;

    // Panel principal derecho
    private JPanel panelDerecho;

    /**
     * Constructor que inicializa la interfaz .
     */
    public VistaRegistroCita() {
        configurarVentana();
        inicializarComponentes();
        construirInterfaz();
    }

    /**
     * Configura las propiedades de la ventana.
     */
    private void configurarVentana() {
        setTitle("Sistema de Registro de Citas Médicas");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    /**
     * Inicializa todos los componentes de la interfaz.
     */
    private void inicializarComponentes() {
        // Panel NSS
        txtNSS = new JTextField(15);
        btnBuscar = new JButton("Buscar Paciente");

        // Panel Datos Paciente
        txtAreaPaciente = new JTextArea(4, 30);
        txtAreaPaciente.setEditable(false);
        txtAreaPaciente.setFont(new Font("Arial", Font.PLAIN, 12));

        // Lista de Médicos
        modeloListaMedicos = new DefaultListModel<>();
        listaMedicos = new JList<>(modeloListaMedicos);
        listaMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMedicos.setFont(new Font("Arial", Font.PLAIN, 12));

        // Panel Detalles
        txtAreaDetalles = new JTextArea(15, 35);
        txtAreaDetalles.setEditable(false);
        txtAreaDetalles.setFont(new Font("Arial", Font.PLAIN, 12));
        txtAreaDetalles.setLineWrap(true);
        txtAreaDetalles.setWrapStyleWord(true);

        // Spinners para fecha y hora
        spinnerFecha = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy");
        spinnerFecha.setEditor(editorFecha);

        spinnerHora = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorHora = new JSpinner.DateEditor(spinnerHora, "HH:mm");
        spinnerHora.setEditor(editorHora);

        btnConfirmarCita = new JButton("Confirmar Cita");
        btnNuevaCita = new JButton("Nueva Cita");
        btnNuevaCita.setEnabled(false);

        configurarEventos();
    }

    private void configurarEventos() {

        btnBuscar.addActionListener(e -> {
            String nss = txtNSS.getText().trim();
            if (controlador != null) {
                controlador.buscarPacientePorNSS(nss);
            }
        });

        txtNSS.addActionListener(e -> btnBuscar.doClick());

        listaMedicos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Medico medicoSeleccionado = listaMedicos.getSelectedValue();
                if (medicoSeleccionado != null && controlador != null) {
                    controlador.seleccionarMedico(medicoSeleccionado.getIdMedico());
                }
            }
        });

        btnConfirmarCita.addActionListener(e -> {
            if (controlador != null) {
                LocalDate fecha = convertirALocalDate((java.util.Date) spinnerFecha.getValue());
                LocalTime hora = convertirALocalTime((java.util.Date) spinnerHora.getValue());
                controlador.registrarCita(fecha, hora);
            }
        });

        btnNuevaCita.addActionListener(e -> {
            if (controlador != null) {
                controlador.nuevaCita();
                limpiarFormulario();
            }
        });
    }

    private void construirInterfaz() {

        JPanel panelSuperior = crearPanelBusquedaNSS();
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelIzquierdo = crearPanelIzquierdo();
        add(panelIzquierdo, BorderLayout.WEST);

        panelDerecho = crearPanelDerecho();
        add(panelDerecho, BorderLayout.CENTER);

        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanelBusquedaNSS() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panel.setBorder(new TitledBorder("1. Ingresar Número de Seguridad Social"));
        panel.setBackground(new Color(230, 240, 255));

        JLabel lblNSS = new JLabel("NSS (11 dígitos):");
        lblNSS.setFont(new Font("Arial", Font.BOLD, 12));

        panel.add(lblNSS);
        panel.add(txtNSS);
        panel.add(btnBuscar);

        return panel;
    }

    private JPanel crearPanelIzquierdo() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelPaciente = new JPanel(new BorderLayout(5, 5));
        panelPaciente.setBorder(new TitledBorder("2. Datos del Paciente"));
        JScrollPane scrollPaciente = new JScrollPane(txtAreaPaciente);
        scrollPaciente.setPreferredSize(new Dimension(350, 100));
        panelPaciente.add(scrollPaciente, BorderLayout.CENTER);

        JPanel panelMedicos = new JPanel(new BorderLayout(5, 5));
        panelMedicos.setBorder(new TitledBorder("3. Seleccionar Médico"));
        JScrollPane scrollMedicos = new JScrollPane(listaMedicos);
        scrollMedicos.setPreferredSize(new Dimension(350, 400));
        panelMedicos.add(scrollMedicos, BorderLayout.CENTER);

        panelPrincipal.add(panelPaciente);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelMedicos);

        return panelPrincipal;
    }

    private JPanel crearPanelDerecho() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelDetallesContenido = new JPanel(new BorderLayout(10, 10));
        panelDetallesContenido.setBorder(new TitledBorder("4. Detalles"));

        JScrollPane scrollDetalles = new JScrollPane(txtAreaDetalles);
        panelDetallesContenido.add(scrollDetalles, BorderLayout.CENTER);

        // Panel para fecha y hora
        JPanel panelFechaHora = crearPanelFechaHora();
        panelDetallesContenido.add(panelFechaHora, BorderLayout.SOUTH);

        panel.add(panelDetallesContenido, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelFechaHora() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new TitledBorder("Seleccionar Fecha y Hora"));
        panel.setBackground(new Color(245, 245, 245));

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel lblHora = new JLabel("Hora:");
        lblHora.setFont(new Font("Arial", Font.BOLD, 12));

        panel.add(lblFecha);
        panel.add(spinnerFecha);
        panel.add(lblHora);
        panel.add(spinnerHora);
        panel.add(btnConfirmarCita);
        panel.add(btnNuevaCita);

        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBorder(BorderFactory.createEtchedBorder());

        JLabel lblInfo = new JLabel("Sistema de Registro de Citas Médicas - Arquitectura MVC + Patrón Observer");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 10));
        panel.add(lblInfo);

        return panel;
    }

    // metodos del patron observer
    /**
     * Establece el controlador y se registra como observador. Implementa el
     * patrón Observer.
     */
    public void setControlador(ControladorRegistroCita controlador) {
        this.controlador = controlador;
        // Registrarse como observador (patrón Observer)
        this.controlador.agregarObservador(this);
        System.out.println("Vista registrada como observadora del controlador");
    }

    /**
     * Implementación de ObservadorVista - Notificación de paciente encontrado.
     * El controlador llama a este método cuando encuentra un paciente.
     */
    @Override
    public void notificarPacienteEncontrado(Paciente paciente, List<Medico> medicos) {
        System.out.println("Observer: Notificación recibida - Paciente encontrado");
        mostrarDatosPaciente(paciente);
        cargarListaMedicos(medicos);
    }

    /**
     * Implementacin de ObservadorVista - Notificacion de medico seleccionado.
     * El controlador llama a este método cuando se selecciona un médico.
     */
    @Override
    public void notificarMedicoSeleccionado(Medico medico) {
        System.out.println("Observer: Notificación recibida - Médico seleccionado");
        mostrarDetallesMedico(medico);
    }

    /**
     * Implementación de ObservadorVista - Notificacion de cita registrada. El
     * controlador llama a este método cuando se registra una cita.
     */
    @Override
    public void notificarCitaRegistrada(Cita cita) {
        System.out.println("Observer: Notificación recibida - Cita registrada");
        mostrarConfirmacionCita(cita);
    }

    /**
     * Implementacion de ObservadorVista - Notificación de error. El controlador
     * llama a este método cuando ocurre un error.
     */
    @Override
    public void notificarError(String mensaje) {
        System.out.println("Observer: Notificación recibida - Error: " + mensaje);
        mostrarError(mensaje);
    }

    // metodos privados
    /**
     * Muestra los datos del paciente en el panel correspondiente.
     */
    private void mostrarDatosPaciente(Paciente paciente) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(paciente.getNombre()).append(" ")
                .append(paciente.getApellidos()).append("\n");
        sb.append("NSS: ").append(paciente.getNss()).append("\n");
        sb.append("Teléfono: ").append(paciente.getTelefono()).append("\n");
        sb.append("Fecha Nacimiento: ").append(paciente.getFechaNacimiento());

        txtAreaPaciente.setText(sb.toString());
    }

    /**
     * Carga la lista de medicos disponibles.
     */
    private void cargarListaMedicos(List<Medico> medicos) {
        modeloListaMedicos.clear();
        for (Medico medico : medicos) {
            modeloListaMedicos.addElement(medico);
        }
    }

    /**
     * Muestra los detalles del médico seleccionado.
     */
    private void mostrarDetallesMedico(Medico medico) {
        panelDetallesContenido.setBorder(new TitledBorder("4. Detalles del Médico"));
        txtAreaDetalles.setText(medico.obtenerDetallesCompletos());
        btnConfirmarCita.setEnabled(true);
    }

    /**
     * Muestra la confirmacion de la cita registrada.
     */
    private void mostrarConfirmacionCita(Cita cita) {
        panelDetallesContenido.setBorder(new TitledBorder("5. Confirmación de Cita"));
        txtAreaDetalles.setText(cita.obtenerDetalles());

        btnConfirmarCita.setEnabled(false);
        btnNuevaCita.setEnabled(true);

        JOptionPane.showMessageDialog(this,
                "¡Cita registrada exitosamente!\nID de Cita: " + cita.getIdCita(),
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de error al usuario.
     */
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarFormulario() {
        txtNSS.setText("");
        txtAreaPaciente.setText("");
        txtAreaDetalles.setText("");
        modeloListaMedicos.clear();
        listaMedicos.clearSelection();

        panelDetallesContenido.setBorder(new TitledBorder("4. Detalles"));

        btnConfirmarCita.setEnabled(false);
        btnNuevaCita.setEnabled(false);

        txtNSS.requestFocus();
    }

    // extra transformadores.
    /**
     * Convierte java.util.Date a LocalDate.
     */
    private LocalDate convertirALocalDate(java.util.Date fecha) {
        return fecha.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Convierte java.util.Date a LocalTime.
     */
    private LocalTime convertirALocalTime(java.util.Date fecha) {
        return fecha.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalTime();
    }
}
