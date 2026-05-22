/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Venta;
import Modelo.Cliente;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import Modelo.*;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.*;

/**
 *
 * @author admin
 */
public class UsaGUIVenta extends javax.swing.JFrame {

    /**
     * Creates new form UsaGUIVenta
     */
    private LinkedList<String> atractivos = new LinkedList<>();
    private ArrayList<Destino> susDestinos = new ArrayList<>();
    ArrayList<Venta> datos = new ArrayList<>();
    Venta actualizaVenta;

    public UsaGUIVenta() {
        initComponents();
        jLabelContacto.setVisible(false);
        jFieldNombreContacto.setVisible(false);
        jLabelObsequio.setVisible(false);
        jFieldObsequio.setVisible(false);
        jSeparatorOculto.setVisible(false);
        jPanelUnico.setVisible(false);
        ocultarComponentes(jPanelOpcionesFiltros);

    }
/**
     * Genera un archivo de texto con la información de los clientes (Persistencia).
     * Corresponde a la tarea S2-T3.
     * @param listaClientes El ArrayList que contiene los clientes registrados.
     */
    public void generarArchivoClientes(ArrayList<Cliente> listaClientes) {
        String nombreArchivo = "clientes.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            
            writer.write("--- REGISTRO DE CLIENTES ---");
            writer.newLine(); 

            for (Cliente cliente : listaClientes) {
                // Formato: Tipo ID, Numero ID, Nombre, Email, Telefono
                String linea = cliente.getTipoIdentificacion() + "," + 
                               cliente.getNumeroIdentificacion() + "," + 
                               cliente.getNombre() + "," + 
                               cliente.getEmail() + "," + 
                               cliente.getTelefono();
                
                writer.write(linea);
                writer.newLine(); 
            }

            System.out.println("Archivo de clientes generado exitosamente en: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Ocurrió un error al generar el archivo de clientes: " + e.getMessage());
        }
    }
    public String consultarTodasVentas(ArrayList<Venta> datos) {
        String resultado = "Todas las Ventas\n";
        if (datos.isEmpty()) {
            resultado += "No hay datos en el sistema";
        } else {
            for (Venta objVenta : datos) {
                resultado += objVenta.toString() + "\n"
                        + "========================================================";
            }

        }
        return resultado;
    }

    public String consultarVentaDadoNumero(ArrayList<Venta> datos, int numeroVenta) {
        String resultado = "Ventas segun su Numero de venta: " + numeroVenta + "\n";
        if (datos == null) {
            resultado += "No hay datos en el sistema";
        } else {
            int i = 0;
            for (Venta objVenta : datos) {
                if (objVenta.getNumero() == numeroVenta) {
                    resultado += objVenta.toString() + "\n"
                            + "========================================================";
                    i++;
                }
            }
            if (i == 0) {
                resultado += "No se encontraron Datos";
            }
        }
        return resultado;
    }

    public String consultarVentaDadaPosicion(ArrayList<Venta> datos, char posicionVenta) {
        String resultado = "Ventas segun su Posicion\n";
        if (datos.isEmpty()) {
            resultado += "No hay datos en el sistema";
        } else {
            if (posicionVenta == 'p' || posicionVenta == 'P') {
                resultado += datos.get(0).toString();
            } else {
                if (posicionVenta == 'u' || posicionVenta == 'U') {
                    resultado += datos.get(datos.size() - 1).toString();
                } else {

                }
            }
        }
        return resultado;
    }

    public String consultarVentasDadoEstado(ArrayList<Venta> datos, char estadoVenta) {
        String resultado = "Ventas segun su Estado: " + estadoVenta + "\n";
        if (datos == null) {
            resultado += "No hay datos en el sistema";
        } else {
            int i = 0;
            for (Venta objVenta : datos) {
                if (objVenta.getEstado() == estadoVenta) {
                    resultado += objVenta.toString() + "\n"
                            + "========================================================";
                    i++;
                }
            }
            if (i == 0) {
                resultado += "No se encontraron Datos";
            }
        }
        return resultado;
    }

    public String consultarVentaDadaCategoriaPaquete(ArrayList<Venta> datos, String categoriaPaquete) {

        String resultado = "Ventas segun la categoria del paquete: "
                + categoriaPaquete + "\n";

        if (datos == null || datos.isEmpty()) {
            return resultado + "No hay ventas registradas";
        }

        int contador = 0;

        for (Venta venta : datos) {

            boolean encontrada = false;

            for (PaqueteTuristico paquete : venta.getSusPaquetesTuristicos()) {

                // VALIDAR CATEGORÍA ÚNICO
                if (categoriaPaquete.equalsIgnoreCase("Unico")
                        && paquete instanceof PaqueteTuristicoUnico) {

                    encontrada = true;
                } // VALIDAR CATEGORÍA MÚLTIPLE
                else if (categoriaPaquete.equalsIgnoreCase("Multiple")
                        && paquete instanceof PaqueteTuristicoMultiple) {

                    encontrada = true;
                }
            }

            // SI LA VENTA TIENE ALGÚN PAQUETE DE ESA CATEGORÍA
            if (encontrada) {

                contador++;

                resultado += "====================================\n";
                resultado += "Número Venta: " + venta.getNumero() + "\n";
                resultado += "Fecha Generación: "
                        + venta.getFechaHoraGeneracion() + "\n";

                resultado += "Fecha Actualización: "
                        + venta.getFechaHoraActualizacion() + "\n";

                resultado += "Estado: " + venta.getEstado() + "\n";

                resultado += "Cliente: "
                        + venta.getSuCliente().getNombre() + "\n";

                resultado += "Cantidad Paquetes: "
                        + venta.getSusPaquetesTuristicos().size() + "\n";

                resultado += "Cantidad Total Unidades: "
                        + venta.calcularCantidadTotalUnidadesPaquetes() + "\n";

                resultado += "Valor Total Paquetes: "
                        + venta.calcularValorTotalPaquetes() + "\n";

                resultado += "Valor Descuento: "
                        + venta.calcularValorDescuento() + "\n";

                resultado += "Valor Total Pagar: "
                        + venta.calcularValorTotalPagar() + "\n\n";

                resultado += "----- PAQUETES -----\n";

                for (PaqueteTuristico paquete : venta.getSusPaquetesTuristicos()) {

                    boolean mostrar = false;

                    if (categoriaPaquete.equalsIgnoreCase("Unico")
                            && paquete instanceof PaqueteTuristicoUnico) {

                        mostrar = true;
                    }

                    if (categoriaPaquete.equalsIgnoreCase("Multiple")
                            && paquete instanceof PaqueteTuristicoMultiple) {

                        mostrar = true;
                    }

                    if (mostrar) {

                        resultado += "Código: " + paquete.getCodigo() + "\n";
                        resultado += "Nombre: " + paquete.getNombre() + "\n";
                        resultado += "Tipología: "
                                + paquete.getTipologiaTurismo() + "\n";

                        resultado += "Origen: "
                                + paquete.getOrigen() + "\n";

                        resultado += "Valor Unidad: "
                                + paquete.calcularValorUnidad() + "\n";

                        resultado += "Valor Total: "
                                + paquete.calcularValorTotal() + "\n";

                        // SI ES MÚLTIPLE
                        if (paquete instanceof PaqueteTuristicoMultiple multiple) {

                            resultado += "Destino Inicial: "
                                    + multiple.obtenerDestinoInicial()
                                            .getNombreLugar() + "\n";

                            resultado += "Destino Final: "
                                    + multiple.obtenerDestinoFinal()
                                            .getNombreLugar() + "\n";
                        }

                        resultado += "\n";
                    }
                }
            }
        }

        if (contador == 0) {
            resultado += "No se encontraron ventas para esa categoría";
        }

        return resultado;
    }

    public void actualizarVenta(ArrayList<Venta> datos, int numeroVenta, char operacion) {
    }

    
    public void recuperarVentasDesdeArchivoObjetos() {
    try {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ventas.dat"));
        datos.clear();
        datos = (ArrayList<Venta>) ois.readObject();
        ois.close();
        JOptionPane.showMessageDialog(this, "Ventas recuperadas: " + datos.size());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}
    
    
    
    private void LimpiarCamposVenta(JPanel panel, int boton) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
            if (component instanceof JRadioButton) {
                ((JRadioButton) component).setSelected(false);
                ((JRadioButton) component).setEnabled(true);
            }
            if (component instanceof JCheckBox) {
                ((JCheckBox) component).setSelected(false);
            }
            if (component instanceof JTextArea) {
                ((JTextArea) component).setText(null);
            }
            if (panel == jPanelDestinos && boton == 1) {
                susDestinos.clear();
                atractivos.clear();
            }
            if (panel == jPanelPlanTuristico) {
                jCheckBoxHotel.setSelected(true);
                jCheckBoxComida.setSelected(true);
                jCheckBoxVuelo.setSelected(true);
                if (component instanceof JPanel) {
                    LimpiarCamposVenta((JPanel) component, 0);
                }

            }
            jLabelContacto.setVisible(false);
            jFieldNombreContacto.setVisible(false);
            jLabelObsequio.setVisible(false);
            jFieldObsequio.setVisible(false);
            jSeparatorOculto.setVisible(false);
            jPanelUnico.setVisible(false);
            jAreaDestinos.setText(null);
        }
    }

    private void ocultarComponentes(JLayeredPane panel) {
        for (Component component : panel.getComponents()) {
            component.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        filtros = new javax.swing.ButtonGroup();
        filtrosPosicion = new javax.swing.ButtonGroup();
        filtrosEstado = new javax.swing.ButtonGroup();
        filtroCategoria = new javax.swing.ButtonGroup();
        tipoArchivo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonNuevaVenta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        panelCards = new javax.swing.JPanel();
        panelNuevaVenta = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelForm = new javax.swing.JPanel();
        jPanelCliente = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jFieldEmail = new javax.swing.JTextField();
        jFieldNumeroID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jRadioNIT = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSpinnerDescuento = new javax.swing.JSpinner();
        jRadioCC = new javax.swing.JRadioButton();
        jFieldNombre = new javax.swing.JTextField();
        jFieldTelefono = new javax.swing.JTextField();
        jLabelContacto = new javax.swing.JLabel();
        jFieldNombreContacto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanelPlanTuristico = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jFieldTipologia = new javax.swing.JTextField();
        jFieldNombrePaquete = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jRadioMultiple = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jRadioUnico = new javax.swing.JRadioButton();
        jFieldOrigen = new javax.swing.JTextField();
        jFieldTarifa = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jFieldCantidadU = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDescripcion = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanelUnico = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jRadioBuffet = new javax.swing.JRadioButton();
        jRadioAmericano = new javax.swing.JRadioButton();
        jSeparatorOculto = new javax.swing.JSeparator();
        jPanelPaqueteAds = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jCheckBoxHotel = new javax.swing.JCheckBox();
        jCheckBoxComida = new javax.swing.JCheckBox();
        jCheckBoxVuelo = new javax.swing.JCheckBox();
        jCheckBoxAsistencia = new javax.swing.JCheckBox();
        jRadioComidaComp = new javax.swing.JRadioButton();
        jRadioDesayunoOnly = new javax.swing.JRadioButton();
        jLabelObsequio = new javax.swing.JLabel();
        jFieldObsequio = new javax.swing.JTextField();
        jPanelDestinos = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jFieldNombreDestino = new javax.swing.JTextField();
        jFieldDiasDestino = new javax.swing.JTextField();
        jFieldAtractivoDestino = new javax.swing.JTextField();
        jRadioAtractivoIncluido = new javax.swing.JRadioButton();
        jRadioAtractivoNoIncluido = new javax.swing.JRadioButton();
        jButtonAñadirDestino = new javax.swing.JButton();
        jButtonAñadirAtractivo = new javax.swing.JButton();
        jButtonVerAtractivo = new javax.swing.JButton();
        jButtonLimpiarAtractivo = new javax.swing.JButton();
        jButtonLimpiarDestinos = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jAreaDestinos = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jButtonAgregarVenta = new javax.swing.JButton();
        jButtonLimpiarVenta = new javax.swing.JButton();
        panelConsulta = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioFiltroTodos = new javax.swing.JRadioButton();
        jRadioFiltroID = new javax.swing.JRadioButton();
        jRadioFiltroPosicion = new javax.swing.JRadioButton();
        jRadioFiltroEstado = new javax.swing.JRadioButton();
        jRadioFiltroCategoria = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jAreaConsultas = new javax.swing.JTextArea();
        jPanelOpcionesFiltros = new javax.swing.JLayeredPane();
        jFieldDatos = new javax.swing.JTextField();
        jRadioPrimero = new javax.swing.JRadioButton();
        jRadioUltimo = new javax.swing.JRadioButton();
        jRadioDefault = new javax.swing.JRadioButton();
        jRadioPagado = new javax.swing.JRadioButton();
        jRadioCancelado = new javax.swing.JRadioButton();
        jRadioFiltroUnico = new javax.swing.JRadioButton();
        jRadioFiltroMultiple = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        panelActualizar = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jFieldNumVentaEdit = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jButtonBusqueda = new javax.swing.JButton();
        jLabelIDVenta = new javax.swing.JLabel();
        jLabelNombreVenta = new javax.swing.JLabel();
        jLabelFechaVenta = new javax.swing.JLabel();
        jLabelEstadoVenta = new javax.swing.JLabel();
        jLabelTelefonoVenta = new javax.swing.JLabel();
        jLabelEmailVenta = new javax.swing.JLabel();
        jLabelPagoVenta = new javax.swing.JLabel();
        jButtonPagar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        panelArchivo = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jFieldNombreArchivo = new javax.swing.JTextField();
        jRadioCliente = new javax.swing.JRadioButton();
        jRadioVenta = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jAreaArchivos = new javax.swing.JTextArea();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gestión Ventas Paquetes Turísticos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonNuevaVenta.setText("Nueva Venta");
        jButtonNuevaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaVentaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Menu");

        jButton2.setText("Consultar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar venta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Archivos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonNuevaVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addComponent(jButtonNuevaVenta)
                .addGap(31, 31, 31)
                .addComponent(jButton2)
                .addGap(31, 31, 31)
                .addComponent(jButton3)
                .addGap(31, 31, 31)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCards.setLayout(new java.awt.CardLayout());

        panelNuevaVenta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);

        jPanelCliente.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jLabel14.setText("Descuento");

        jLabel12.setText("Telefono");

        jLabel11.setText("Nombre");

        jLabel10.setText("Numero ID");

        jFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldEmailActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo ID");

        jRadioNIT.setText("NIT");
        jRadioNIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioNITActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel9.setText("Datos del cliente");

        jLabel13.setText("Email");

        jSpinnerDescuento.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerDescuentoStateChanged(evt);
            }
        });

        jRadioCC.setText("CC");
        jRadioCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioCCActionPerformed(evt);
            }
        });

        jLabelContacto.setText("Nombre Contacto");

        javax.swing.GroupLayout jPanelClienteLayout = new javax.swing.GroupLayout(jPanelCliente);
        jPanelCliente.setLayout(jPanelClienteLayout);
        jPanelClienteLayout.setHorizontalGroup(
            jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClienteLayout.createSequentialGroup()
                        .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelClienteLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelClienteLayout.createSequentialGroup()
                        .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelClienteLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jFieldEmail))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelClienteLayout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioCC, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelClienteLayout.createSequentialGroup()
                                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFieldTelefono)
                                    .addComponent(jFieldNombre)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelClienteLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jSpinnerDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelClienteLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(jFieldNumeroID, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelClienteLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioNIT, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelClienteLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabelContacto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jFieldNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))))
        );
        jPanelClienteLayout.setVerticalGroup(
            jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioCC)
                    .addComponent(jRadioNIT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldNumeroID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabelContacto)
                    .addComponent(jFieldNombreContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jSpinnerDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel8.setText("Registro de ventas");

        jPanelPlanTuristico.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jLabel15.setText("Tarifa");

        jLabel16.setText("Origen");

        jLabel17.setText("Descripcion");

        jLabel18.setText("Nombre del Paquete");

        jFieldTipologia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFieldTipologiaActionPerformed(evt);
            }
        });

        jLabel19.setText("Tipo de Paquete");

        jRadioMultiple.setText("Multiple");
        jRadioMultiple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioMultipleActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        jLabel20.setText("Paquete turistico");

        jLabel21.setText("Tipologia");

        jRadioUnico.setText("Unico");
        jRadioUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioUnicoActionPerformed(evt);
            }
        });

        jLabel22.setText("Cantidad de Unidades");

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDescripcion);

        jSeparator4.setForeground(new java.awt.Color(102, 102, 102));

        jLabel24.setText("Hotel");

        jLabel26.setText("Tipo de Desayuno");

        jRadioBuffet.setText("Buffet");
        jRadioBuffet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBuffetActionPerformed(evt);
            }
        });

        jRadioAmericano.setText("Americano");
        jRadioAmericano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAmericanoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUnicoLayout = new javax.swing.GroupLayout(jPanelUnico);
        jPanelUnico.setLayout(jPanelUnicoLayout);
        jPanelUnicoLayout.setHorizontalGroup(
            jPanelUnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUnicoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelUnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUnicoLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioBuffet, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioAmericano, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelUnicoLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanelUnicoLayout.setVerticalGroup(
            jPanelUnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUnicoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelUnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanelUnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jRadioBuffet)
                    .addComponent(jRadioAmericano))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jSeparatorOculto.setForeground(new java.awt.Color(102, 102, 102));

        jLabel25.setText("El plan incluye:");

        jCheckBoxHotel.setSelected(true);
        jCheckBoxHotel.setText("Hotel");

        jCheckBoxComida.setSelected(true);
        jCheckBoxComida.setText("Alimentacion");
        jCheckBoxComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxComidaActionPerformed(evt);
            }
        });

        jCheckBoxVuelo.setSelected(true);
        jCheckBoxVuelo.setText("Vuelo");

        jCheckBoxAsistencia.setText("Asistencia");

        jRadioComidaComp.setText("Completa");

        jRadioDesayunoOnly.setText("solo Desayuno");
        jRadioDesayunoOnly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDesayunoOnlyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPaqueteAdsLayout = new javax.swing.GroupLayout(jPanelPaqueteAds);
        jPanelPaqueteAds.setLayout(jPanelPaqueteAdsLayout);
        jPanelPaqueteAdsLayout.setHorizontalGroup(
            jPanelPaqueteAdsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPaqueteAdsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPaqueteAdsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPaqueteAdsLayout.createSequentialGroup()
                        .addGroup(jPanelPaqueteAdsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxVuelo)
                            .addComponent(jCheckBoxAsistencia))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelPaqueteAdsLayout.createSequentialGroup()
                        .addGroup(jPanelPaqueteAdsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxHotel)
                            .addGroup(jPanelPaqueteAdsLayout.createSequentialGroup()
                                .addComponent(jCheckBoxComida)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioComidaComp, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioDesayunoOnly, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(117, Short.MAX_VALUE))))
        );
        jPanelPaqueteAdsLayout.setVerticalGroup(
            jPanelPaqueteAdsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPaqueteAdsLayout.createSequentialGroup()
                .addComponent(jLabel25)
                .addGap(17, 17, 17)
                .addComponent(jCheckBoxHotel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPaqueteAdsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxComida)
                    .addComponent(jRadioComidaComp)
                    .addComponent(jRadioDesayunoOnly))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxVuelo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxAsistencia)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jLabelObsequio.setText("Obsequio");

        javax.swing.GroupLayout jPanelPlanTuristicoLayout = new javax.swing.GroupLayout(jPanelPlanTuristico);
        jPanelPlanTuristico.setLayout(jPanelPlanTuristicoLayout);
        jPanelPlanTuristicoLayout.setHorizontalGroup(
            jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                        .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                                        .addComponent(jRadioUnico, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(jRadioMultiple, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jFieldTipologia, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFieldOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFieldTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFieldCantidadU, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3))
                                    .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                                        .addComponent(jFieldNombrePaquete, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelObsequio, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFieldObsequio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                        .addComponent(jPanelPaqueteAds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186)
                        .addComponent(jSeparator3))))
            .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparatorOculto, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                        .addComponent(jSeparator4))
                    .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelUnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelPlanTuristicoLayout.setVerticalGroup(
            jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jRadioUnico)
                    .addComponent(jRadioMultiple))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldNombrePaquete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabelObsequio)
                    .addComponent(jFieldObsequio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldTipologia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldCantidadU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelPlanTuristicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPlanTuristicoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelPaqueteAds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparatorOculto, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelUnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelDestinos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jLabel23.setText("Destinos");

        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));

        jLabel27.setText("Nombre del lugar");

        jLabel28.setText("Dias de permanecia");

        jLabel29.setText("Atractivos");

        jLabel30.setText("Atractivos Estan Incluidos");

        jRadioAtractivoIncluido.setText("si");
        jRadioAtractivoIncluido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAtractivoIncluidoActionPerformed(evt);
            }
        });

        jRadioAtractivoNoIncluido.setText("no");
        jRadioAtractivoNoIncluido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioAtractivoNoIncluidoActionPerformed(evt);
            }
        });

        jButtonAñadirDestino.setText("Añadir Destino");
        jButtonAñadirDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAñadirDestinoActionPerformed(evt);
            }
        });

        jButtonAñadirAtractivo.setText("Añadir ");
        jButtonAñadirAtractivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAñadirAtractivoActionPerformed(evt);
            }
        });

        jButtonVerAtractivo.setText("Ver");
        jButtonVerAtractivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerAtractivoActionPerformed(evt);
            }
        });

        jButtonLimpiarAtractivo.setText("Limpiar");
        jButtonLimpiarAtractivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarAtractivoActionPerformed(evt);
            }
        });

        jButtonLimpiarDestinos.setText("Borrar Destinos");
        jButtonLimpiarDestinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarDestinosActionPerformed(evt);
            }
        });

        jAreaDestinos.setColumns(20);
        jAreaDestinos.setRows(5);
        jScrollPane4.setViewportView(jAreaDestinos);

        jLabel7.setText("Lista de destinos");

        javax.swing.GroupLayout jPanelDestinosLayout = new javax.swing.GroupLayout(jPanelDestinos);
        jPanelDestinos.setLayout(jPanelDestinosLayout);
        jPanelDestinosLayout.setHorizontalGroup(
            jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(jPanelDestinosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDestinosLayout.createSequentialGroup()
                        .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFieldDiasDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFieldAtractivoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFieldNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAñadirAtractivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonVerAtractivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimpiarAtractivo))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDestinosLayout.createSequentialGroup()
                        .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonLimpiarDestinos)
                            .addComponent(jLabel30))
                        .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDestinosLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jRadioAtractivoIncluido, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioAtractivoNoIncluido, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDestinosLayout.createSequentialGroup()
                                .addGap(192, 192, 192)
                                .addComponent(jButtonAñadirDestino))))
                    .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDestinosLayout.setVerticalGroup(
            jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDestinosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jFieldNombreDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldDiasDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldAtractivoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jButtonAñadirAtractivo)
                    .addComponent(jButtonVerAtractivo)
                    .addComponent(jButtonLimpiarAtractivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jRadioAtractivoIncluido)
                    .addComponent(jRadioAtractivoNoIncluido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDestinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAñadirDestino)
                    .addComponent(jButtonLimpiarDestinos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonAgregarVenta.setText("Agregar venta");
        jButtonAgregarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarVentaActionPerformed(evt);
            }
        });

        jButtonLimpiarVenta.setText("Limpiar datos");
        jButtonLimpiarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFormLayout = new javax.swing.GroupLayout(jPanelForm);
        jPanelForm.setLayout(jPanelFormLayout);
        jPanelFormLayout.setHorizontalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel8))
                    .addGroup(jPanelFormLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelPlanTuristico, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanelDestinos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFormLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonAgregarVenta)
                .addGap(84, 84, 84)
                .addComponent(jButtonLimpiarVenta)
                .addGap(124, 124, 124))
        );
        jPanelFormLayout.setVerticalGroup(
            jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanelPlanTuristico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiarVenta)
                    .addComponent(jButtonAgregarVenta))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanelForm);

        javax.swing.GroupLayout panelNuevaVentaLayout = new javax.swing.GroupLayout(panelNuevaVenta);
        panelNuevaVenta.setLayout(panelNuevaVentaLayout);
        panelNuevaVentaLayout.setHorizontalGroup(
            panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelNuevaVentaLayout.setVerticalGroup(
            panelNuevaVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelCards.add(panelNuevaVenta, "cardNuevaVenta");

        panelConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Consultas");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Filtros");

        filtros.add(jRadioFiltroTodos);
        jRadioFiltroTodos.setText("Todos");
        jRadioFiltroTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFiltroTodosActionPerformed(evt);
            }
        });

        filtros.add(jRadioFiltroID);
        jRadioFiltroID.setText("Numero");
        jRadioFiltroID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFiltroIDActionPerformed(evt);
            }
        });

        filtros.add(jRadioFiltroPosicion);
        jRadioFiltroPosicion.setText("Posicion");
        jRadioFiltroPosicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFiltroPosicionActionPerformed(evt);
            }
        });

        filtros.add(jRadioFiltroEstado);
        jRadioFiltroEstado.setText("Estado");
        jRadioFiltroEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFiltroEstadoActionPerformed(evt);
            }
        });

        filtros.add(jRadioFiltroCategoria);
        jRadioFiltroCategoria.setText("Categoria");
        jRadioFiltroCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioFiltroCategoriaActionPerformed(evt);
            }
        });

        jAreaConsultas.setColumns(20);
        jAreaConsultas.setRows(5);
        jScrollPane2.setViewportView(jAreaConsultas);

        jPanelOpcionesFiltros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelOpcionesFiltros.add(jFieldDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 13, 71, -1));

        filtrosPosicion.add(jRadioPrimero);
        jRadioPrimero.setText("Primero");
        jPanelOpcionesFiltros.setLayer(jRadioPrimero, javax.swing.JLayeredPane.DRAG_LAYER);
        jPanelOpcionesFiltros.add(jRadioPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 70, -1));

        filtrosPosicion.add(jRadioUltimo);
        jRadioUltimo.setText("Ultimo");
        jPanelOpcionesFiltros.setLayer(jRadioUltimo, javax.swing.JLayeredPane.DRAG_LAYER);
        jPanelOpcionesFiltros.add(jRadioUltimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 70, -1));

        filtrosEstado.add(jRadioDefault);
        jRadioDefault.setText("Predeterminado");
        jPanelOpcionesFiltros.add(jRadioDefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        filtrosEstado.add(jRadioPagado);
        jRadioPagado.setText("Pagado");
        jRadioPagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioPagadoActionPerformed(evt);
            }
        });
        jPanelOpcionesFiltros.add(jRadioPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        filtrosEstado.add(jRadioCancelado);
        jRadioCancelado.setText("cancelado");
        jPanelOpcionesFiltros.add(jRadioCancelado, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        filtroCategoria.add(jRadioFiltroUnico);
        jRadioFiltroUnico.setText("Plan unico");
        jPanelOpcionesFiltros.add(jRadioFiltroUnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        filtroCategoria.add(jRadioFiltroMultiple);
        jRadioFiltroMultiple.setText("Plan Multiple");
        jPanelOpcionesFiltros.add(jRadioFiltroMultiple, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConsultaLayout = new javax.swing.GroupLayout(panelConsulta);
        panelConsulta.setLayout(panelConsultaLayout);
        panelConsultaLayout.setHorizontalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioFiltroCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioFiltroEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioFiltroPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioFiltroID, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioFiltroTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(panelConsultaLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4)
                        .addGap(25, 25, 25)))
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelOpcionesFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelConsultaLayout.setVerticalGroup(
            panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jPanelOpcionesFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsultaLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioFiltroTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioFiltroID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioFiltroPosicion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioFiltroEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioFiltroCategoria)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        panelCards.add(panelConsulta, "cardConsulta");

        panelActualizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel31.setText("Actualizar Venta");

        jLabel32.setText("Numero de venta");

        jButtonBusqueda.setText("Buscar");
        jButtonBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBusquedaActionPerformed(evt);
            }
        });

        jLabelIDVenta.setText("Numero de venta: ");

        jLabelNombreVenta.setText("Nombre Cliente:");

        jLabelFechaVenta.setText("Fecha de actualizacion:");

        jLabelEstadoVenta.setText("Estado Actual:");

        jLabelTelefonoVenta.setText("telefono:");

        jLabelEmailVenta.setText("Email:");

        jLabelPagoVenta.setText("Total a pagar:");

        jButtonPagar.setText("Pagar venta");
        jButtonPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPagarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar venta");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelActualizarLayout = new javax.swing.GroupLayout(panelActualizar);
        panelActualizar.setLayout(panelActualizarLayout);
        panelActualizarLayout.setHorizontalGroup(
            panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActualizarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelActualizarLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelActualizarLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelActualizarLayout.createSequentialGroup()
                                .addComponent(jLabelIDVenta)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelActualizarLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(27, 27, 27)
                                .addComponent(jFieldNumVentaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonBusqueda)
                                .addGap(40, 40, 40))
                            .addGroup(panelActualizarLayout.createSequentialGroup()
                                .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNombreVenta)
                                    .addComponent(jLabelFechaVenta)
                                    .addComponent(jLabelEstadoVenta)
                                    .addComponent(jLabelTelefonoVenta)
                                    .addComponent(jLabelEmailVenta)
                                    .addComponent(jLabelPagoVenta)
                                    .addGroup(panelActualizarLayout.createSequentialGroup()
                                        .addGap(107, 107, 107)
                                        .addComponent(jButtonPagar)
                                        .addGap(80, 80, 80)
                                        .addComponent(jButtonCancelar)))
                                .addGap(0, 108, Short.MAX_VALUE))))))
        );
        panelActualizarLayout.setVerticalGroup(
            panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActualizarLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFieldNumVentaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jButtonBusqueda))
                .addGap(34, 34, 34)
                .addComponent(jLabelIDVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelNombreVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFechaVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEstadoVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTelefonoVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEmailVenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPagoVenta)
                .addGap(18, 18, 18)
                .addGroup(panelActualizarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPagar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        panelCards.add(panelActualizar, "cardActualizar");

        panelArchivo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Archivos");

        jSeparator6.setForeground(new java.awt.Color(102, 102, 102));

        jButton5.setText("Generar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Importar");

        jLabel34.setText("Nombre del archivo:");

        tipoArchivo.add(jRadioCliente);
        jRadioCliente.setText("Cliente");

        tipoArchivo.add(jRadioVenta);
        jRadioVenta.setText("Venta");

        jLabel35.setText("Tipo de archivo");

        jLabel36.setText("Informacion Cargada:");

        jAreaArchivos.setColumns(20);
        jAreaArchivos.setRows(5);
        jScrollPane5.setViewportView(jAreaArchivos);

        javax.swing.GroupLayout panelArchivoLayout = new javax.swing.GroupLayout(panelArchivo);
        panelArchivo.setLayout(panelArchivoLayout);
        panelArchivoLayout.setHorizontalGroup(
            panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelArchivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArchivoLayout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(panelArchivoLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5)
                            .addGroup(panelArchivoLayout.createSequentialGroup()
                                .addComponent(jFieldNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioCliente)))
                        .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelArchivoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jRadioVenta)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelArchivoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addContainerGap())))))
            .addGroup(panelArchivoLayout.createSequentialGroup()
                .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelArchivoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel33))
                    .addGroup(panelArchivoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel36)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelArchivoLayout.setVerticalGroup(
            panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArchivoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jFieldNombreArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioCliente)
                    .addComponent(jRadioVenta)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelArchivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(24, 24, 24)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelCards.add(panelArchivo, "cardArchivo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNuevaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaVentaActionPerformed
        CardLayout cl = (CardLayout) (panelCards.getLayout());

        cl.show(panelCards, "cardNuevaVenta");
    }//GEN-LAST:event_jButtonNuevaVentaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout cl = (CardLayout) (panelCards.getLayout());

        cl.show(panelCards, "cardConsulta");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CardLayout cl = (CardLayout) (panelCards.getLayout());

        cl.show(panelCards, "cardActualizar");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CardLayout cl = (CardLayout) (panelCards.getLayout());

        cl.show(panelCards, "cardArchivo");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jRadioNITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioNITActionPerformed
        jFieldNombreContacto.setVisible(true);
        jLabelContacto.setVisible(true);
        jRadioCC.setSelected(false);
    }//GEN-LAST:event_jRadioNITActionPerformed

    private void jFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldEmailActionPerformed

    private void jRadioCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioCCActionPerformed
        jLabelContacto.setVisible(false);
        jFieldNombreContacto.setVisible(false);
        jRadioNIT.setSelected(false);
    }//GEN-LAST:event_jRadioCCActionPerformed

    private void jSpinnerDescuentoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerDescuentoStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jSpinnerDescuentoStateChanged

    private void jFieldTipologiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFieldTipologiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFieldTipologiaActionPerformed

    private void jRadioMultipleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioMultipleActionPerformed
        jSeparatorOculto.setVisible(false);
        jPanelUnico.setVisible(false);
        jLabelObsequio.setVisible(true);
        jFieldObsequio.setVisible(true);
        jRadioUnico.setSelected(false);

    }//GEN-LAST:event_jRadioMultipleActionPerformed


    private void jRadioUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioUnicoActionPerformed
        jSeparatorOculto.setVisible(true);
        jPanelUnico.setVisible(true);
        jLabelObsequio.setVisible(false);
        jFieldObsequio.setVisible(false);
        jRadioMultiple.setSelected(false);

    }//GEN-LAST:event_jRadioUnicoActionPerformed

    private void jCheckBoxComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxComidaActionPerformed
        if (jCheckBoxComida.isSelected()) {
            jRadioAmericano.setEnabled(true);
            jRadioBuffet.setEnabled(true);
            jRadioDesayunoOnly.setVisible(false);
            jRadioComidaComp.setVisible(false);
        } else {
            jRadioAmericano.setEnabled(false);
            jRadioBuffet.setEnabled(false);
            jRadioDesayunoOnly.setVisible(true);
            jRadioComidaComp.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxComidaActionPerformed

    private void jRadioDesayunoOnlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDesayunoOnlyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioDesayunoOnlyActionPerformed

    private void jRadioBuffetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBuffetActionPerformed
        jRadioAmericano.setSelected(false);

    }//GEN-LAST:event_jRadioBuffetActionPerformed

    private void jRadioAmericanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAmericanoActionPerformed
        jRadioBuffet.setSelected(false);
    }//GEN-LAST:event_jRadioAmericanoActionPerformed

    private void jButtonAñadirAtractivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAñadirAtractivoActionPerformed
        this.atractivos.add(jFieldAtractivoDestino.getText());
        jFieldAtractivoDestino.setText(null);
    }//GEN-LAST:event_jButtonAñadirAtractivoActionPerformed

    private void jButtonAñadirDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAñadirDestinoActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        String listaDestinos = "Lista de destinos agregados: \n";
        if (confirmacion == 0) {
            if (jRadioUnico.isSelected() && susDestinos.isEmpty()) {
                try {
                    int dias = Integer.valueOf(jFieldDiasDestino.getText());
                    boolean atractivoIncluido;
                    atractivoIncluido = jRadioAtractivoIncluido.isSelected();
                    Destino destino = new Destino(jFieldNombreDestino.getText(), dias, atractivos, atractivoIncluido);
                    susDestinos.add(destino);

                    jRadioUnico.setEnabled(false);
                    jRadioMultiple.setEnabled(false);
                    atractivos.clear();
                    JOptionPane.showMessageDialog(null, "Destino Agregado correctamente");
                    LimpiarCamposVenta(jPanelDestinos, 0);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Digite un valor correcto en el campo de dias", "Error Al digitar datos", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (jRadioMultiple.isSelected()) {
                    try {
                        int dias = Integer.valueOf(jFieldDiasDestino.getText());
                        boolean atractivoIncluido;
                        atractivoIncluido = jRadioAtractivoIncluido.isSelected();
                        Destino destino = new Destino(jFieldNombreDestino.getText(), dias, atractivos, atractivoIncluido);
                        susDestinos.add(destino);
                        jRadioUnico.setEnabled(false);
                        jRadioMultiple.setEnabled(false);
                        atractivos.clear();
                        JOptionPane.showMessageDialog(null, "Destino Agregado correctamente");
                        LimpiarCamposVenta(jPanelDestinos, 0);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Digite un valor correcto en el campo de dias", "Error Al digitar datos", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (!jRadioUnico.isSelected() && !jRadioMultiple.isSelected()) {
                        JOptionPane.showMessageDialog(null, "Elija un tipo de paquete turistico", "Accion no admitida", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Su plan no admite ingresar mas destinos", "Accion no admitida", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            for (Destino objD : susDestinos) {
                listaDestinos += objD.toString() + "\n";
            }
            jAreaDestinos.setText(listaDestinos);
        }

    }//GEN-LAST:event_jButtonAñadirDestinoActionPerformed

    private void jButtonVerAtractivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerAtractivoActionPerformed
        String mensaje = "Atractivos Registrados:\n";
        for (String atractivo : atractivos) {
            mensaje += atractivo + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }//GEN-LAST:event_jButtonVerAtractivoActionPerformed

    private void jButtonLimpiarAtractivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarAtractivoActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea borrar los actractivos registrados?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            atractivos.clear();
            jFieldAtractivoDestino.setText(null);
        }
    }//GEN-LAST:event_jButtonLimpiarAtractivoActionPerformed

    private void jRadioAtractivoIncluidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAtractivoIncluidoActionPerformed
        jRadioAtractivoNoIncluido.setSelected(false);
    }//GEN-LAST:event_jRadioAtractivoIncluidoActionPerformed

    private void jRadioAtractivoNoIncluidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioAtractivoNoIncluidoActionPerformed
        jRadioAtractivoIncluido.setSelected(false);
    }//GEN-LAST:event_jRadioAtractivoNoIncluidoActionPerformed

    private void jButtonAgregarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarVentaActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea guardar los datos actuales?", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            for (Component component : jPanelForm.getComponents()) {
                if (component instanceof JPanel) {
                    LimpiarCamposVenta((JPanel) component, 1);
                }
            }
        }
    }//GEN-LAST:event_jButtonAgregarVentaActionPerformed

    private void jButtonLimpiarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarVentaActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea borrar los datos actuales? (Se limpiaran todos los campos)", "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            for (Component component : jPanelForm.getComponents()) {
                if (component instanceof JPanel) {
                    LimpiarCamposVenta((JPanel) component, 1);
                }
            }
        }
    }//GEN-LAST:event_jButtonLimpiarVentaActionPerformed

    private void jRadioPagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioPagadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioPagadoActionPerformed

    private void jRadioFiltroIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFiltroIDActionPerformed
        if (jRadioFiltroID.isSelected()) {
            ocultarComponentes(jPanelOpcionesFiltros);
            jFieldDatos.setVisible(true);
        } else {
            ocultarComponentes(jPanelOpcionesFiltros);
        }
    }//GEN-LAST:event_jRadioFiltroIDActionPerformed

    private void jRadioFiltroPosicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFiltroPosicionActionPerformed
        if (jRadioFiltroPosicion.isSelected()) {
            ocultarComponentes(jPanelOpcionesFiltros);
            jRadioPrimero.setVisible(true);
            jRadioUltimo.setVisible(true);
        } else {
            ocultarComponentes(jPanelOpcionesFiltros);
        }
    }//GEN-LAST:event_jRadioFiltroPosicionActionPerformed

    private void jRadioFiltroEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFiltroEstadoActionPerformed
        if (jRadioFiltroEstado.isSelected()) {
            ocultarComponentes(jPanelOpcionesFiltros);
            jRadioDefault.setVisible(true);
            jRadioPagado.setVisible(true);
            jRadioCancelado.setVisible(true);
        } else {
            ocultarComponentes(jPanelOpcionesFiltros);
        }
    }//GEN-LAST:event_jRadioFiltroEstadoActionPerformed

    private void jRadioFiltroCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFiltroCategoriaActionPerformed
        if (jRadioFiltroCategoria.isSelected()) {
            ocultarComponentes(jPanelOpcionesFiltros);
            System.out.println("HOla");
            jRadioFiltroUnico.setVisible(true);
            jRadioFiltroMultiple.setVisible(true);
            System.out.println("adios");
        } else {
            ocultarComponentes(jPanelOpcionesFiltros);
        }
    }//GEN-LAST:event_jRadioFiltroCategoriaActionPerformed

    private void jRadioFiltroTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioFiltroTodosActionPerformed
        ocultarComponentes(jPanelOpcionesFiltros);
    }//GEN-LAST:event_jRadioFiltroTodosActionPerformed

    @SuppressWarnings("UnusedAssignment")
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // CONSULTAR TODAS
        if (jRadioFiltroTodos.isSelected()) {

            jAreaConsultas.setText(consultarTodasVentas(datos));
            return;
        }

        // CONSULTAR POR ID
        if (jRadioFiltroID.isSelected()) {

            try {

                int codigo = Integer.parseInt(jFieldDatos.getText());

                jAreaConsultas.setText(
                        consultarVentaDadoNumero(datos, codigo)
                );

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        null,
                        "Digite un valor correcto en el campo del Numero de Venta",
                        "Error Al digitar datos",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            return;
        }

        // CONSULTAR POR ESTADO
        if (jRadioFiltroEstado.isSelected()) {

            if (jRadioDefault.isSelected()) {

                jAreaConsultas.setText(
                        consultarVentasDadoEstado(datos, 'A')
                );

            } else if (jRadioPagado.isSelected()) {

                jAreaConsultas.setText(
                        consultarVentasDadoEstado(datos, 'P')
                );

            } else if (jRadioCancelado.isSelected()) {

                jAreaConsultas.setText(
                        consultarVentasDadoEstado(datos, 'C')
                );

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un estado por favor"
                );
            }

            return;
        }

        // CONSULTAR POR CATEGORÍA
        if (jRadioFiltroCategoria.isSelected()) {

            if (jRadioFiltroUnico.isSelected()) {

                jAreaConsultas.setText(
                        consultarVentaDadaCategoriaPaquete(datos, "Unico")
                );

            } else if (jRadioFiltroMultiple.isSelected()) {

                jAreaConsultas.setText(
                        consultarVentaDadaCategoriaPaquete(datos, "Multiple")
                );
            }

            return;
        }

        // CONSULTAR POR POSICIÓN
        if (jRadioFiltroPosicion.isSelected()) {

            if (jRadioPrimero.isSelected()) {

                jAreaConsultas.setText(
                        consultarVentaDadaPosicion(datos, 'p')
                );
                return;

            } else if (jRadioUltimo.isSelected()) {

                jAreaConsultas.setText(
                        consultarVentaDadaPosicion(datos, 'u')
                );
                return;
            } else {
                JOptionPane.showMessageDialog(null, "Por favos seleccione la posicion que desea buscar",
                        "Error de busqueda", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Por favor seleccione un filtro",
                "Error de busqueda", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonLimpiarDestinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarDestinosActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar todos los destinos anteriormente agregados?",
                "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            susDestinos.clear();
            jAreaDestinos.setText(null);
        }
    }//GEN-LAST:event_jButtonLimpiarDestinosActionPerformed

    private void jButtonBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBusquedaActionPerformed
        try {

            int codigo = Integer.valueOf(jFieldNumVentaEdit.getText());
            if (datos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay ventas registradas aun");
            }
            for (Venta objV : datos) {
                if (objV.getNumero() == codigo) {
                    actualizaVenta = objV;
                }
            }
            if (actualizaVenta != null) {
                int i = 0;
                int totalPago = 0;
                ArrayList<String> listaAtributos = new ArrayList<>();
                listaAtributos.add(String.valueOf(actualizaVenta.getNumero()));
                listaAtributos.add(actualizaVenta.getSuCliente().getNombre());
                listaAtributos.add(String.valueOf(actualizaVenta.getFechaHoraActualizacion()));
                listaAtributos.add(String.valueOf(actualizaVenta.getEstado()));
                listaAtributos.add(actualizaVenta.getSuCliente().getTelefono());
                listaAtributos.add(actualizaVenta.getSuCliente().getEmail());
                for (PaqueteTuristico objP : actualizaVenta.getSusPaquetesTuristicos()) {
                    totalPago += objP.calcularValorTotal();
                }
                listaAtributos.add(String.valueOf(totalPago));
                for (Component component : panelActualizar.getComponents()) {
                    if (component instanceof JLabel) {
                        ((JLabel) component).setText(((JLabel) component).getText() + " " + listaAtributos.get(i));
                        i++;
                    }
                }
                jFieldNumVentaEdit.setEnabled(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Digite un valor valido para el numero de venta",
                    "Error de datos", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonBusquedaActionPerformed

    private void jButtonPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPagarActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "Desea confirmar el pago?",
                "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            try {
                int codigo = Integer.valueOf(jFieldNumVentaEdit.getText());
                actualizarVenta(datos, codigo, 'p');
                for (Venta objV : datos) {
                    if (objV.getNumero() == codigo) {
                        actualizaVenta = objV;
                    }
                }
                jLabelFechaVenta.setText("Fecha de actualizacion: " + actualizaVenta.getFechaHoraActualizacion());
                jLabelEstadoVenta.setText("Estado actual: " + actualizaVenta.getEstado());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Digite un valor valido para el numero de venta",
                        "Error de datos", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButtonPagarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(null, "Desea confirmar el pago?",
                "Confirmacion", JOptionPane.YES_NO_OPTION);
        if (confirmacion == 0) {
            try {
                int codigo = Integer.valueOf(jFieldNumVentaEdit.getText());
                actualizarVenta(datos, codigo, 'C');
                for (Venta objV : datos) {
                    if (objV.getNumero() == codigo) {
                        actualizaVenta = objV;
                    }
                }
                jLabelFechaVenta.setText("Fecha de actualizacion: " + actualizaVenta.getFechaHoraActualizacion());
                jLabelEstadoVenta.setText("Estado actual: " + actualizaVenta.getEstado());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Digite un valor valido para el numero de venta",
                        "Error de datos", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsaGUIVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsaGUIVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup filtroCategoria;
    private javax.swing.ButtonGroup filtros;
    private javax.swing.ButtonGroup filtrosEstado;
    private javax.swing.ButtonGroup filtrosPosicion;
    private javax.swing.JTextArea jAreaArchivos;
    private javax.swing.JTextArea jAreaConsultas;
    private javax.swing.JTextArea jAreaDestinos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonAgregarVenta;
    private javax.swing.JButton jButtonAñadirAtractivo;
    private javax.swing.JButton jButtonAñadirDestino;
    private javax.swing.JButton jButtonBusqueda;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonLimpiarAtractivo;
    private javax.swing.JButton jButtonLimpiarDestinos;
    private javax.swing.JButton jButtonLimpiarVenta;
    private javax.swing.JButton jButtonNuevaVenta;
    private javax.swing.JButton jButtonPagar;
    private javax.swing.JButton jButtonVerAtractivo;
    private javax.swing.JCheckBox jCheckBoxAsistencia;
    private javax.swing.JCheckBox jCheckBoxComida;
    private javax.swing.JCheckBox jCheckBoxHotel;
    private javax.swing.JCheckBox jCheckBoxVuelo;
    private javax.swing.JTextField jFieldAtractivoDestino;
    private javax.swing.JTextField jFieldCantidadU;
    private javax.swing.JTextField jFieldDatos;
    private javax.swing.JTextField jFieldDiasDestino;
    private javax.swing.JTextField jFieldEmail;
    private javax.swing.JTextField jFieldNombre;
    private javax.swing.JTextField jFieldNombreArchivo;
    private javax.swing.JTextField jFieldNombreContacto;
    private javax.swing.JTextField jFieldNombreDestino;
    private javax.swing.JTextField jFieldNombrePaquete;
    private javax.swing.JTextField jFieldNumVentaEdit;
    private javax.swing.JTextField jFieldNumeroID;
    private javax.swing.JTextField jFieldObsequio;
    private javax.swing.JTextField jFieldOrigen;
    private javax.swing.JTextField jFieldTarifa;
    private javax.swing.JTextField jFieldTelefono;
    private javax.swing.JTextField jFieldTipologia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelContacto;
    private javax.swing.JLabel jLabelEmailVenta;
    private javax.swing.JLabel jLabelEstadoVenta;
    private javax.swing.JLabel jLabelFechaVenta;
    private javax.swing.JLabel jLabelIDVenta;
    private javax.swing.JLabel jLabelNombreVenta;
    private javax.swing.JLabel jLabelObsequio;
    private javax.swing.JLabel jLabelPagoVenta;
    private javax.swing.JLabel jLabelTelefonoVenta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelCliente;
    private javax.swing.JPanel jPanelDestinos;
    private javax.swing.JPanel jPanelForm;
    private javax.swing.JLayeredPane jPanelOpcionesFiltros;
    private javax.swing.JPanel jPanelPaqueteAds;
    private javax.swing.JPanel jPanelPlanTuristico;
    private javax.swing.JPanel jPanelUnico;
    private javax.swing.JRadioButton jRadioAmericano;
    private javax.swing.JRadioButton jRadioAtractivoIncluido;
    private javax.swing.JRadioButton jRadioAtractivoNoIncluido;
    private javax.swing.JRadioButton jRadioBuffet;
    private javax.swing.JRadioButton jRadioCC;
    private javax.swing.JRadioButton jRadioCancelado;
    private javax.swing.JRadioButton jRadioCliente;
    private javax.swing.JRadioButton jRadioComidaComp;
    private javax.swing.JRadioButton jRadioDefault;
    private javax.swing.JRadioButton jRadioDesayunoOnly;
    private javax.swing.JRadioButton jRadioFiltroCategoria;
    private javax.swing.JRadioButton jRadioFiltroEstado;
    private javax.swing.JRadioButton jRadioFiltroID;
    private javax.swing.JRadioButton jRadioFiltroMultiple;
    private javax.swing.JRadioButton jRadioFiltroPosicion;
    private javax.swing.JRadioButton jRadioFiltroTodos;
    private javax.swing.JRadioButton jRadioFiltroUnico;
    private javax.swing.JRadioButton jRadioMultiple;
    private javax.swing.JRadioButton jRadioNIT;
    private javax.swing.JRadioButton jRadioPagado;
    private javax.swing.JRadioButton jRadioPrimero;
    private javax.swing.JRadioButton jRadioUltimo;
    private javax.swing.JRadioButton jRadioUnico;
    private javax.swing.JRadioButton jRadioVenta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparatorOculto;
    private javax.swing.JSpinner jSpinnerDescuento;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panelActualizar;
    private javax.swing.JPanel panelArchivo;
    private javax.swing.JPanel panelCards;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JPanel panelNuevaVenta;
    private javax.swing.ButtonGroup tipoArchivo;
    // End of variables declaration//GEN-END:variables
}
