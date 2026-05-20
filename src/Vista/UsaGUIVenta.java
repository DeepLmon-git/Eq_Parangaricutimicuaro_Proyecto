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
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class UsaGUIVenta extends javax.swing.JFrame {

    /**
     * Creates new form UsaGUIVenta
     */
    public UsaGUIVenta() {
        initComponents();
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
        if (datos == null) {
            resultado += "No hay datos en el sistema";
        } else {
            for (Venta objVenta : datos) {
                resultado += objVenta.toString();
            }

        }
        return resultado;
    }

    public String consultarVentaDadoNumero(ArrayList<Venta> datos, int numeroVenta) {
        String resultado = "Ventas segun su Estado\n";
        if (datos == null) {
            resultado += "No hay datos en el sistema";
        } else {
            int i = 0;
            for (Venta objVenta : datos) {
                if (objVenta.getNumero() == numeroVenta) {
                    resultado += objVenta.toString();
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
        if (datos == null) {
            resultado += "No hay datos en el sistema";
        } else {
            if (posicionVenta == 'p' || posicionVenta == 'P') {
                resultado += datos.get(0).toString();
            } else {
                if (posicionVenta == 'u' || posicionVenta == 'U') {
                    resultado += datos.get(datos.size()-1).toString();
                }
                else{
                    
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
                    resultado += objVenta.toString();
                    i++;
                }
            }
            if (i == 0) {
                resultado += "No se encontraron Datos";
            }
        }
        return resultado;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    // End of variables declaration//GEN-END:variables
}
